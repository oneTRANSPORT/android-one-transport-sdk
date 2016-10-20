/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;

import net.uk.onetransport.android.modules.clearviewsilverstone.R;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceRetriever;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroup;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneDevice;
import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneLatestDevice;
import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneLatestTraffic;
import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneTraffic;

public class CvsProviderModule implements ProviderModule {

    public static final String TAG = "CvsProviderModule";

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri DEVICE_URI;
    public static Uri LATEST_DEVICE_URI;
    public static Uri TRAFFIC_URI;
    public static Uri LATEST_TRAFFIC_URI;
    // Sync adapter extras.
    private static final String EXTRAS_DEVICES =
            "net.uk.onetransport.android.modules.clearviewsilverstone.sync.DEVICES";
    private static final String EXTRAS_TRAFFIC =
            "net.uk.onetransport.android.modules.clearviewsilverstone.sync.TRAFFIC";

    private static int DEVICES;
    private static int DEVICE_ID;
    private static int LATEST_DEVICES;
    private static int LATEST_DEVICE_ID;
    private static int TRAFFIC;
    private static int TRAFFIC_ID;
    private static int LATEST_TRAFFIC;
    private static int LATEST_TRAFFIC_ID;

    private Context context;

    public CvsProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CvsContract.CREATE_CLEARVIEW_SILVERSTONE_DEVICE_TABLE);
        sqLiteDatabase.execSQL(CvsContract.CREATE_CLEARVIEW_SILVERSTONE_LATEST_DEVICE_TABLE);
        sqLiteDatabase.execSQL(CvsContract.CREATE_CLEARVIEW_SILVERSTONE_TRAFFIC_TABLE);
        sqLiteDatabase.execSQL(CvsContract.CREATE_CLEARVIEW_SILVERSTONE_LATEST_TRAFFIC_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        DEVICE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                ClearviewSilverstoneDevice.TABLE_NAME);
        LATEST_DEVICE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                CvsContract.ClearviewSilverstoneLatestDevice.TABLE_NAME);
        TRAFFIC_URI = Uri.withAppendedPath(AUTHORITY_URI,
                ClearviewSilverstoneTraffic.TABLE_NAME);
        LATEST_TRAFFIC_URI = Uri.withAppendedPath(AUTHORITY_URI,
                ClearviewSilverstoneLatestTraffic.TABLE_NAME);

        DEVICES = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneDevice.TABLE_NAME, DEVICES);
        providerModules.add(this);
        DEVICE_ID = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneDevice.TABLE_NAME + "/#", DEVICE_ID);
        providerModules.add(this);
        LATEST_DEVICES = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneLatestDevice.TABLE_NAME, LATEST_DEVICES);
        providerModules.add(this);
        LATEST_DEVICE_ID = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneLatestDevice.TABLE_NAME + "/#",
                LATEST_DEVICE_ID);
        providerModules.add(this);
        TRAFFIC = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneTraffic.TABLE_NAME, TRAFFIC);
        providerModules.add(this);
        TRAFFIC_ID = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneTraffic.TABLE_NAME + "/#", TRAFFIC_ID);
        providerModules.add(this);
        LATEST_TRAFFIC = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneLatestTraffic.TABLE_NAME, LATEST_TRAFFIC);
        providerModules.add(this);
        LATEST_TRAFFIC_ID = providerModules.size();
        uriMatcher.addURI(authority, ClearviewSilverstoneLatestTraffic.TABLE_NAME + "/#",
                LATEST_TRAFFIC_ID);
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
        if (match == LATEST_DEVICES) {
            return mimeDirPrefix + ClearviewSilverstoneLatestDevice.TABLE_NAME;
        }
        if (match == LATEST_DEVICE_ID) {
            return mimeItemPrefix + ClearviewSilverstoneLatestDevice.TABLE_NAME;
        }
        if (match == TRAFFIC) {
            return mimeDirPrefix + ClearviewSilverstoneTraffic.TABLE_NAME;
        }
        if (match == TRAFFIC_ID) {
            return mimeItemPrefix + ClearviewSilverstoneTraffic.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC) {
            return mimeDirPrefix + ClearviewSilverstoneLatestTraffic.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_ID) {
            return mimeItemPrefix + ClearviewSilverstoneLatestTraffic.TABLE_NAME;
        }
        return null;
    }

    @Override
    public int bulkInsert(int match, ContentValues[] contentValues, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int numInserted = 0;
        if (match == DEVICES) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + ClearviewSilverstoneDevice.TABLE_NAME + "("
                                + ClearviewSilverstoneDevice.COLUMN_SENSOR_ID + ","
                                + ClearviewSilverstoneDevice.COLUMN_TITLE + ","
                                + ClearviewSilverstoneDevice.COLUMN_DESCRIPTION + ","
                                + ClearviewSilverstoneDevice.COLUMN_TYPE + ","
                                + ClearviewSilverstoneDevice.COLUMN_LATITUDE + ","
                                + ClearviewSilverstoneDevice.COLUMN_LONGITUDE + ","
                                + ClearviewSilverstoneDevice.COLUMN_CHANGED + ","
                                + ClearviewSilverstoneDevice.COLUMN_CIN_ID + ","
                                + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    Long sensorId = value.getAsLong(ClearviewSilverstoneDevice.COLUMN_SENSOR_ID);
                    String title = value.getAsString(ClearviewSilverstoneDevice.COLUMN_TITLE);
                    String description = value.getAsString(ClearviewSilverstoneDevice.COLUMN_DESCRIPTION);
                    String type = value.getAsString(ClearviewSilverstoneDevice.COLUMN_TYPE);
                    Double latitude = value.getAsDouble(ClearviewSilverstoneDevice.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(ClearviewSilverstoneDevice.COLUMN_LONGITUDE);
                    String changed = value.getAsString(ClearviewSilverstoneDevice.COLUMN_CHANGED);
                    String cinId = value.getAsString(ClearviewSilverstoneDevice.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(ClearviewSilverstoneDevice.COLUMN_CREATION_TIME);
                    if (sensorId != null) {
                        insert.bindLong(1, sensorId);
                    }
                    if (title != null) {
                        insert.bindString(2, title);
                    }
                    if (description != null) {
                        insert.bindString(3, description);
                    }
                    if (type != null) {
                        insert.bindString(4, type);
                    }
                    if (latitude != null) {
                        insert.bindDouble(5, latitude);
                    }
                    if (longitude != null) {
                        insert.bindDouble(6, longitude);
                    }
                    if (changed != null) {
                        insert.bindString(7, changed);
                    }
                    if (cinId != null) {
                        insert.bindString(8, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(9, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(DEVICE_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_DEVICES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + ClearviewSilverstoneDevice.TABLE_NAME + "("
                                + ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + ","
                                + ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP + ","
                                + ClearviewSilverstoneTraffic.COLUMN_LANE + ","
                                + ClearviewSilverstoneTraffic.COLUMN_DIRECTION + ","
                                + ClearviewSilverstoneTraffic.COLUMN_CIN_ID + ","
                                + ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    Long sensorId = value.getAsLong(ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID);
                    String timestamp = value.getAsString(ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP);
                    Long lane = value.getAsLong(ClearviewSilverstoneTraffic.COLUMN_LANE);
                    Long direction = value.getAsLong(ClearviewSilverstoneTraffic.COLUMN_DIRECTION);
                    String cinId = value.getAsString(ClearviewSilverstoneTraffic.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME);
                    if (sensorId != null) {
                        insert.bindLong(1, sensorId);
                    }
                    if (timestamp != null) {
                        insert.bindString(2, timestamp);
                    }
                    if (lane != null) {
                        insert.bindLong(3, lane);
                    }
                    if (direction != null) {
                        insert.bindLong(4, direction);
                    }
                    if (cinId != null) {
                        insert.bindString(5, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(6, creationTime);
                    }
                    sqLiteDatabase.setTransactionSuccessful();
                    numInserted = contentValues.length;
                    contentResolver.notifyChange(TRAFFIC_URI, null);
                }
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_TRAFFIC) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        return numInserted;
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
        if (match == LATEST_DEVICES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + ClearviewSilverstoneLatestDevice.TABLE_NAME);
        }
        if (match == TRAFFIC) {
            id = sqLiteDatabase.insert(ClearviewSilverstoneTraffic.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_URI, id);
        }
        if (match == LATEST_TRAFFIC) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + ClearviewSilverstoneLatestTraffic.TABLE_NAME);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[]
            selectionArgs,
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
        if (match == LATEST_DEVICES) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneLatestDevice.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_DEVICE_URI);
            return cursor;
        }
        if (match == LATEST_DEVICE_ID) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneLatestDevice.TABLE_NAME,
                    projection, ClearviewSilverstoneLatestDevice._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_DEVICE_URI);
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
        if (match == LATEST_TRAFFIC) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneLatestTraffic.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_ID) {
            Cursor cursor = sqLiteDatabase.query(ClearviewSilverstoneLatestTraffic.TABLE_NAME,
                    projection, ClearviewSilverstoneLatestTraffic._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_URI);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[]
            selectionArgs,
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
        if (match == LATEST_DEVICES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + ClearviewSilverstoneLatestDevice.TABLE_NAME);
        }
        if (match == LATEST_DEVICE_ID) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + ClearviewSilverstoneLatestDevice.TABLE_NAME);
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
        if (match == LATEST_TRAFFIC) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + ClearviewSilverstoneLatestTraffic.TABLE_NAME);
        }
        if (match == LATEST_TRAFFIC_ID) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + ClearviewSilverstoneTraffic.TABLE_NAME);
        }
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase
            sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int rows = 0;
        if (match == DEVICES) {
            rows = sqLiteDatabase.delete(ClearviewSilverstoneDevice.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(DEVICE_URI, null);
        }
        if (match == LATEST_DEVICES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + ClearviewSilverstoneLatestDevice.TABLE_NAME);
        }
        if (match == TRAFFIC) {
            rows = sqLiteDatabase.delete(ClearviewSilverstoneTraffic.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_URI, null);
        }
        if (match == LATEST_TRAFFIC) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + ClearviewSilverstoneLatestTraffic.TABLE_NAME);
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
                    ArrayList<Device> devices = new DeviceRetriever(context).retrieve();
                    CvsContentHelper.insertDevicesIntoProvider(context, devices);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Clearview Silverstone device dynamic counters, car activity.
            if (extras.getBoolean(EXTRAS_TRAFFIC, false)) {
                try {
                    ArrayList<TrafficGroup> trafficGroups = new TrafficGroupRetriever(context).retrieve();
                    CvsContentHelper.insertTrafficGroupsIntoProvider(context, trafficGroups);
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

}
