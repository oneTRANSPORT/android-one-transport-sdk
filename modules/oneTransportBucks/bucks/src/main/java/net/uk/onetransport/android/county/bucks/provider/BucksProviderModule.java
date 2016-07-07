package net.uk.onetransport.android.county.bucks.provider;

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

import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorksArray;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowArray;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignArray;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.CarPark;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.RoadWorks;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.TrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VariableMessageSign;

public class BucksProviderModule implements ProviderModule {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri CAR_PARK_URI;
    public static Uri VARIABLE_MESSAGE_SIGN_URI;
    public static Uri TRAFFIC_FLOW_URI;
    public static Uri ROAD_WORKS_URI;
    // Sync adapter extras.
    private static final String EXTRAS_VMS = "net.uk.onetransport.android.county.bucks.sync.VMS";
    private static final String EXTRAS_CAR_PARKS =
            "net.uk.onetransport.android.county.bucks.sync.CAR_PARKS";
    private static final String EXTRAS_TRAFFIC_FLOW =
            "net.uk.onetransport.android.county.bucks.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_ROAD_WORKS =
            "net.uk.onetransport.android.county.bucks.sync.ROAD_WORKS";
    // Uri matching
    private static int CAR_PARKS;
    private static int CAR_PARK_ID;
    private static int VARIABLE_MESSAGE_SIGNS;
    private static int VARIABLE_MESSAGE_SIGN_ID;
    private static int TRAFFIC_FLOWS;
    private static int TRAFFIC_FLOW_ID;
    private static int ROAD_WORKS;
    private static int ROAD_WORKS_ID;


    private Context context;

    public BucksProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BucksContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_ROAD_WORKS_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI, CarPark.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                VariableMessageSign.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI, TrafficFlow.TABLE_NAME);
        ROAD_WORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, RoadWorks.TABLE_NAME);

        CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, CarPark.TABLE_NAME, CAR_PARKS);
        providerModules.add(this);
        CAR_PARK_ID = providerModules.size();
        uriMatcher.addURI(authority, CarPark.TABLE_NAME + "/#", CAR_PARK_ID);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, VariableMessageSign.TABLE_NAME,
                VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGN_ID = providerModules.size();
        uriMatcher.addURI(authority, VariableMessageSign.TABLE_NAME + "/#",
                VARIABLE_MESSAGE_SIGN_ID);
        providerModules.add(this);
        TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, TrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
        providerModules.add(this);
        TRAFFIC_FLOW_ID = providerModules.size();
        uriMatcher.addURI(authority, TrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
        providerModules.add(this);
        ROAD_WORKS = providerModules.size();
        uriMatcher.addURI(authority, RoadWorks.TABLE_NAME, ROAD_WORKS);
        providerModules.add(this);
        ROAD_WORKS_ID = providerModules.size();
        uriMatcher.addURI(authority, RoadWorks.TABLE_NAME + "/#", ROAD_WORKS_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == CAR_PARKS) {
            return mimeDirPrefix + CarPark.TABLE_NAME;
        }
        if (match == CAR_PARK_ID) {
            return mimeItemPrefix + CarPark.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + VariableMessageSign.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            return mimeItemPrefix + VariableMessageSign.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOWS) {
            return mimeDirPrefix + TrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOW_ID) {
            return mimeItemPrefix + TrafficFlow.TABLE_NAME;
        }
        if (match == ROAD_WORKS) {
            return mimeDirPrefix + RoadWorks.TABLE_NAME;
        }
        if (match == ROAD_WORKS_ID) {
            return mimeItemPrefix + RoadWorks.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            id = sqLiteDatabase.insert(CarPark.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return ContentUris.withAppendedId(CAR_PARK_URI, id);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            id = sqLiteDatabase.insert(VariableMessageSign.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return ContentUris.withAppendedId(VARIABLE_MESSAGE_SIGN_URI, id);
        }
        if (match == TRAFFIC_FLOWS) {
            id = sqLiteDatabase.insert(TrafficFlow.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
        }
        if (match == ROAD_WORKS) {
            id = sqLiteDatabase.insert(RoadWorks.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
            return ContentUris.withAppendedId(ROAD_WORKS_URI, id);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(CarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == CAR_PARK_ID) {
            Cursor cursor = sqLiteDatabase.query(CarPark.TABLE_NAME, projection,
                    CarPark._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(VariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            Cursor cursor = sqLiteDatabase.query(VariableMessageSign.TABLE_NAME, projection,
                    VariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(TrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOW_ID) {
            Cursor cursor = sqLiteDatabase.query(TrafficFlow.TABLE_NAME, projection,
                    TrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == ROAD_WORKS) {
            Cursor cursor = sqLiteDatabase.query(RoadWorks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, ROAD_WORKS_URI);
            return cursor;
        }
        if (match == ROAD_WORKS_ID) {
            Cursor cursor = sqLiteDatabase.query(RoadWorks.TABLE_NAME, projection,
                    RoadWorks._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, ROAD_WORKS_URI);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
                      SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            int rows = sqLiteDatabase.update(CarPark.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == CAR_PARK_ID) {
            int rows = sqLiteDatabase.update(CarPark.TABLE_NAME, values, CarPark._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            int rows = sqLiteDatabase.update(VariableMessageSign.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return rows;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            int rows = sqLiteDatabase.update(VariableMessageSign.TABLE_NAME, values,
                    VariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return rows;
        }
        if (match == TRAFFIC_FLOWS) {
            int rows = sqLiteDatabase.update(TrafficFlow.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == TRAFFIC_FLOW_ID) {
            int rows = sqLiteDatabase.update(TrafficFlow.TABLE_NAME, values,
                    TrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == ROAD_WORKS) {
            int rows = sqLiteDatabase.update(RoadWorks.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
            return rows;
        }
        if (match == ROAD_WORKS_ID) {
            int rows = sqLiteDatabase.update(RoadWorks.TABLE_NAME, values,
                    RoadWorks._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
            return rows;
        }
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int rows = 0;
        if (match == CAR_PARKS) {
            rows = sqLiteDatabase.delete(CarPark.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            rows = sqLiteDatabase.delete(VariableMessageSign.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
        }
        if (match == TRAFFIC_FLOWS) {
            rows = sqLiteDatabase.delete(TrafficFlow.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
        }
        if (match == ROAD_WORKS) {
            rows = sqLiteDatabase.delete(RoadWorks.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
        }
        return rows;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient providerClient, SyncResult syncResult) {
        if (authority.equals(OneTransportProvider.AUTHORITY)) {
            // Car parks.
            if (extras.getBoolean(EXTRAS_CAR_PARKS, false)) {
                try {
                    CarParkArray carParkArray = CarParkArray.getCarParkArray(context);
                    BucksContentHelper.deleteFromProvider(context,
                            BucksContentHelper.DATA_TYPE_CAR_PARK);
                    BucksContentHelper.insertIntoProvider(context, carParkArray.getCarParks());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic flows.
            if (extras.getBoolean(EXTRAS_TRAFFIC_FLOW, false)) {
                try {
                    TrafficFlowArray trafficFlowArray = TrafficFlowArray.getTrafficFlowArray(context);
                    BucksContentHelper.deleteFromProvider(context,
                            BucksContentHelper.DATA_TYPE_TRAFFIC_FLOW);
                    BucksContentHelper.insertIntoProvider(context, trafficFlowArray.getTrafficFlows());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Variable message signs.
            if (extras.getBoolean(EXTRAS_VMS, false)) {
                try {
                    VariableMessageSignArray variableMessageSignArray = VariableMessageSignArray
                            .getVariableMessageSignArray(context);
                    BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_VMS);
                    BucksContentHelper.insertIntoProvider(context,
                            variableMessageSignArray.getVariableMessageSigns());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Road works.
            if (extras.getBoolean(EXTRAS_ROAD_WORKS, false)) {
                try {
                    RoadWorksArray roadWorksArray = RoadWorksArray.getRoadWorksArray(context);
                    BucksContentHelper.deleteFromProvider(context,
                            BucksContentHelper.DATA_TYPE_ROAD_WORKS);
                    BucksContentHelper.insertIntoProvider(context, roadWorksArray.getRoadWorks());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Signal refresh complete.
//            try {
//                BucksContentHelper.refreshLastUpdated(context);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void refresh(Context context, boolean variableMessageSigns, boolean carParks,
                               boolean trafficFlow, boolean roadWorks) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_VMS, variableMessageSigns);
        settingsBundle.putBoolean(EXTRAS_CAR_PARKS, carParks);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_FLOW, trafficFlow);
        settingsBundle.putBoolean(EXTRAS_ROAD_WORKS, roadWorks);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }
}
