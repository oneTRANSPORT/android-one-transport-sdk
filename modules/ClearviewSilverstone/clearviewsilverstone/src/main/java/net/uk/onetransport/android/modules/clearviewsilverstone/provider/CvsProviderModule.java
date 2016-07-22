package net.uk.onetransport.android.modules.clearviewsilverstone.provider;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import net.uk.onetransport.android.modules.clearviewsilverstone.R;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupArray;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneDevice;
import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneHistory;
import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneTraffic;

public class CvsProviderModule implements ProviderModule {

    public static final String TAG = "CvsProviderModule";

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri DEVICE_URI;
    public static Uri TRAFFIC_URI;
    // Sync adapter extras.
    private static final String EXTRAS_DEVICES =
            "net.uk.onetransport.android.modules.clearviewsilverstone.sync.DEVICES";
    private static final String EXTRAS_TRAFFIC =
            "net.uk.onetransport.android.modules.clearviewsilverstone.sync.TRAFFIC";

    private static int DEVICES;
    private static int DEVICE_ID;
    private static int TRAFFIC;
    private static int TRAFFIC_ID;

    private Context context;

    public CvsProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CvsContract.CREATE_CLEARVIEW_SILVERSTONE_DEVICE_TABLE);
        sqLiteDatabase.execSQL(CvsContract.CREATE_CLEARVIEW_SILVERSTONE_TRAFFIC_TABLE);
        sqLiteDatabase.execSQL(CvsContract.CREATE_CLEARVIEW_SILVERSTONE_HISTORY_TABLE);
        importHistory(sqLiteDatabase);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        DEVICE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                ClearviewSilverstoneDevice.TABLE_NAME);
        TRAFFIC_URI = Uri.withAppendedPath(AUTHORITY_URI,
                ClearviewSilverstoneTraffic.TABLE_NAME);

        DEVICES = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneDevice.TABLE_NAME, DEVICES);
        providerModules.add(this);
        DEVICE_ID = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneDevice.TABLE_NAME + "/#", DEVICE_ID);
        providerModules.add(this);
        TRAFFIC = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneTraffic.TABLE_NAME, TRAFFIC);
        providerModules.add(this);
        TRAFFIC_ID = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneTraffic.TABLE_NAME + "/#", TRAFFIC_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == DEVICES) {
            return mimeDirPrefix + ClearviewSilverstoneDevice.TABLE_NAME;
        }
        if (match == DEVICE_ID) {
            return mimeItemPrefix + ClearviewSilverstoneDevice.TABLE_NAME;
        }
        if (match == TRAFFIC) {
            return mimeDirPrefix + ClearviewSilverstoneTraffic.TABLE_NAME;
        }
        if (match == TRAFFIC_ID) {
            return mimeItemPrefix + ClearviewSilverstoneTraffic.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == DEVICES) {
            id = sqLiteDatabase.insert(ClearviewSilverstoneDevice.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(DEVICE_URI, null);
            return ContentUris.withAppendedId(DEVICE_URI, id);
        }
        if (match == TRAFFIC) {
            id = sqLiteDatabase.insert(ClearviewSilverstoneTraffic.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_URI, id);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == DEVICES) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneDevice.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, DEVICE_URI);
            return cursor;
        }
        if (match == DEVICE_ID) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneDevice.TABLE_NAME, projection,
                    ClearviewSilverstoneDevice._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, DEVICE_URI);
            return cursor;
        }
        if (match == TRAFFIC) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneTraffic.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_URI);
            return cursor;
        }
        if (match == TRAFFIC_ID) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneTraffic.TABLE_NAME, projection,
                    ClearviewSilverstoneTraffic._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_URI);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
                      SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == DEVICES) {
            int rows = sqLiteDatabase.update(ClearviewSilverstoneDevice.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(DEVICE_URI, null);
            return rows;
        }
        if (match == DEVICE_ID) {
            int rows = sqLiteDatabase.update(ClearviewSilverstoneDevice.TABLE_NAME, values,
                    ClearviewSilverstoneDevice._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(DEVICE_URI, null);
            return rows;
        }
        if (match == TRAFFIC) {
            int rows = sqLiteDatabase.update(ClearviewSilverstoneTraffic.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAFFIC_URI, null);
            return rows;
        }
        if (match == TRAFFIC_ID) {
            int rows = sqLiteDatabase.update(ClearviewSilverstoneTraffic.TABLE_NAME, values,
                    ClearviewSilverstoneTraffic._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_URI, null);
            return rows;
        }
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int rows = 0;
        if (match == DEVICES) {
            rows = sqLiteDatabase.delete(ClearviewSilverstoneDevice.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(DEVICE_URI, null);
        }
        if (match == TRAFFIC) {
            rows = sqLiteDatabase.delete(ClearviewSilverstoneTraffic.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_URI, null);
        }
        return rows;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient providerClient, SyncResult syncResult) {
        if (authority.equals(OneTransportProvider.AUTHORITY)) {
            // Clearview Silverstone devices, mainly static data.
            if (extras.getBoolean(EXTRAS_DEVICES, false)) {
                try {
                    DeviceArray deviceArray = DeviceArray.getDeviceArray(context);
                    CvsContentHelper.deleteFromProvider(context,
                            CvsContentHelper.DATA_TYPE_DEVICE);
                    Log.i(TAG, "Provider: Deleted device data.");
                    CvsContentHelper.insertIntoProvider(context, deviceArray.getDevices());
                    Log.i(TAG, "Provider: Inserted new device data.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Clearview Silverstone device dynamic counters, car activity.
            if (extras.getBoolean(EXTRAS_TRAFFIC, false)) {
                try {
                    TrafficGroupArray trafficGroupArray = TrafficGroupArray.getTrafficGroupArray(context);
                    CvsContentHelper.deleteFromProvider(context,
                            CvsContentHelper.DATA_TYPE_TRAFFIC);
                    CvsContentHelper.insertIntoProvider(context, trafficGroupArray.getTrafficGroups());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refresh(Context context, boolean devices, boolean traffic) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_DEVICES, devices);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC, traffic);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }

    private void importHistory(SQLiteDatabase sqLiteDatabase) {
        // Import historical data summary that would take too long to download.
        BufferedReader br = new BufferedReader(new InputStreamReader(
                context.getResources().openRawResource(R.raw.summary_import)));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                String insert = "INSERT INTO " + ClearviewSilverstoneHistory.TABLE_NAME
                        + " VALUES (NULL," + line + ");";
                sqLiteDatabase.execSQL(insert);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
