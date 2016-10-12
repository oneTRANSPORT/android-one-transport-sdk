package net.uk.onetransport.android.county.herts.provider;

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

import net.uk.onetransport.android.county.herts.R;
import net.uk.onetransport.android.county.herts.carparks.CarPark;
import net.uk.onetransport.android.county.herts.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.herts.events.Event;
import net.uk.onetransport.android.county.herts.events.EventRetriever;
import net.uk.onetransport.android.county.herts.roadworks.Roadworks;
import net.uk.onetransport.android.county.herts.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.herts.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.herts.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsCarPark;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsEvent;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestCarPark;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestEvent;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestRoadworks;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestTrafficFlow;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestTrafficScoot;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestTrafficSpeed;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestTrafficTravelTime;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsLatestVariableMessageSign;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsRoadworks;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsTrafficFlow;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsTrafficScoot;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsTrafficSpeed;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsTrafficTravelTime;
import static net.uk.onetransport.android.county.herts.provider.HertsContract.HertsVariableMessageSign;

public class HertsProviderModule implements ProviderModule {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri CAR_PARK_URI;
    public static Uri LATEST_CAR_PARK_URI;
    public static Uri EVENT_URI;
    public static Uri LATEST_EVENT_URI;
    public static Uri ROADWORKS_URI;
    public static Uri LATEST_ROADWORKS_URI;
    public static Uri TRAFFIC_FLOW_URI;
    public static Uri LATEST_TRAFFIC_FLOW_URI;
    public static Uri TRAFFIC_SCOOT_URI;
    public static Uri LATEST_TRAFFIC_SCOOT_URI;
    public static Uri TRAFFIC_SPEED_URI;
    public static Uri LATEST_TRAFFIC_SPEED_URI;
    public static Uri TRAFFIC_TRAVEL_TIME_URI;
    public static Uri LATEST_TRAFFIC_TRAVEL_TIME_URI;
    public static Uri VARIABLE_MESSAGE_SIGN_URI;
    public static Uri LATEST_VARIABLE_MESSAGE_SIGN_URI;
    // Sync adapter extras.
    private static final String EXTRAS_CAR_PARKS =
            "net.uk.onetransport.android.county.herts.sync.CAR_PARKS";
    private static final String EXTRAS_EVENTS =
            "net.uk.onetransport.android.county.herts.sync.EVENTS";
    private static final String EXTRAS_ROADWORKS =
            "net.uk.onetransport.android.county.herts.sync.ROADWORKS";
    private static final String EXTRAS_TRAFFIC_FLOW =
            "net.uk.onetransport.android.county.herts.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_TRAFFIC_SCOOT =
            "net.uk.onetransport.android.county.herts.sync.TRAFFIC_SCOOT";
    private static final String EXTRAS_TRAFFIC_SPEED =
            "net.uk.onetransport.android.county.herts.sync.TRAFFIC_SPEED";
    private static final String EXTRAS_TRAFFIC_TRAVEL_TIME =
            "net.uk.onetransport.android.county.herts.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_VMS = "net.uk.onetransport.android.county.herts.sync.VMS";
    // Uri matching
    private static int CAR_PARKS;
    private static int LATEST_CAR_PARKS;
    private static int CAR_PARK_ID;
    private static int EVENTS;
    private static int LATEST_EVENTS;
    private static int EVENT_ID;
    private static int ROADWORKS;
    private static int LATEST_ROADWORKS;
    private static int ROADWORKS_ID;
    private static int TRAFFIC_FLOWS;
    private static int LATEST_TRAFFIC_FLOWS;
    private static int TRAFFIC_FLOW_ID;
    private static int TRAFFIC_SCOOTS;
    private static int LATEST_TRAFFIC_SCOOTS;
    private static int TRAFFIC_SCOOT_ID;
    private static int TRAFFIC_SPEEDS;
    private static int LATEST_TRAFFIC_SPEEDS;
    private static int TRAFFIC_SPEED_ID;
    private static int TRAFFIC_TRAVEL_TIMES;
    private static int LATEST_TRAFFIC_TRAVEL_TIMES;
    private static int TRAFFIC_TRAVEL_TIME_ID;
    private static int VARIABLE_MESSAGE_SIGNS;
    private static int LATEST_VARIABLE_MESSAGE_SIGNS;
    private static int VARIABLE_MESSAGE_SIGN_ID;

    private Context context;

    public HertsProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HertsContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_EVENT_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_ROADWORKS_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_ROADWORKS_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_TRAFFIC_SCOOT_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_TRAFFIC_SCOOT_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_TRAFFIC_SPEED_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_TRAFFIC_SPEED_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(HertsContract.CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI, HertsCarPark.TABLE_NAME);
        LATEST_CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestCarPark.TABLE_NAME);
        EVENT_URI = Uri.withAppendedPath(AUTHORITY_URI, HertsEvent.TABLE_NAME);
        LATEST_EVENT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestEvent.TABLE_NAME);
        ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, HertsRoadworks.TABLE_NAME);
        LATEST_ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestRoadworks.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI, HertsTrafficFlow.TABLE_NAME);
        LATEST_TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestTrafficFlow.TABLE_NAME);
        TRAFFIC_SCOOT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsTrafficScoot.TABLE_NAME);
        LATEST_TRAFFIC_SCOOT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestTrafficScoot.TABLE_NAME);
        TRAFFIC_SPEED_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsTrafficSpeed.TABLE_NAME);
        LATEST_TRAFFIC_SPEED_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestTrafficSpeed.TABLE_NAME);
        TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsTrafficTravelTime.TABLE_NAME);
        LATEST_TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestTrafficTravelTime.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsVariableMessageSign.TABLE_NAME);
        LATEST_VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                HertsLatestVariableMessageSign.TABLE_NAME);

        CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, HertsCarPark.TABLE_NAME, CAR_PARKS);
        providerModules.add(this);
        LATEST_CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestCarPark.TABLE_NAME, LATEST_CAR_PARKS);
        providerModules.add(this);
        CAR_PARK_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsCarPark.TABLE_NAME + "/#", CAR_PARK_ID);
        providerModules.add(this);
        EVENTS = providerModules.size();
        uriMatcher.addURI(authority, HertsEvent.TABLE_NAME, EVENTS);
        providerModules.add(this);
        LATEST_EVENTS = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestEvent.TABLE_NAME, LATEST_EVENTS);
        providerModules.add(this);
        EVENT_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsEvent.TABLE_NAME + "/#", EVENT_ID);
        providerModules.add(this);
        ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, HertsRoadworks.TABLE_NAME, ROADWORKS);
        providerModules.add(this);
        LATEST_ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestRoadworks.TABLE_NAME, LATEST_ROADWORKS);
        providerModules.add(this);
        ROADWORKS_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsRoadworks.TABLE_NAME + "/#", ROADWORKS_ID);
        providerModules.add(this);
        TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
        providerModules.add(this);
        LATEST_TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestTrafficFlow.TABLE_NAME, LATEST_TRAFFIC_FLOWS);
        providerModules.add(this);
        TRAFFIC_FLOW_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
        providerModules.add(this);
        TRAFFIC_SCOOTS = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficScoot.TABLE_NAME, TRAFFIC_SCOOTS);
        providerModules.add(this);
        LATEST_TRAFFIC_SCOOTS = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestTrafficScoot.TABLE_NAME, LATEST_TRAFFIC_SCOOTS);
        providerModules.add(this);
        TRAFFIC_SCOOT_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficScoot.TABLE_NAME + "/#", TRAFFIC_SCOOT_ID);
        providerModules.add(this);
        TRAFFIC_SPEEDS = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficSpeed.TABLE_NAME, TRAFFIC_SPEEDS);
        providerModules.add(this);
        LATEST_TRAFFIC_SPEEDS = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestTrafficSpeed.TABLE_NAME, LATEST_TRAFFIC_SPEEDS);
        providerModules.add(this);
        TRAFFIC_SPEED_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficSpeed.TABLE_NAME + "/#", TRAFFIC_SPEED_ID);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficTravelTime.TABLE_NAME, TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        LATEST_TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestTrafficTravelTime.TABLE_NAME,
                LATEST_TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsTrafficTravelTime.TABLE_NAME + "/#",
                TRAFFIC_TRAVEL_TIME_ID);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, HertsVariableMessageSign.TABLE_NAME,
                VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        LATEST_VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, HertsLatestVariableMessageSign.TABLE_NAME,
                LATEST_VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGN_ID = providerModules.size();
        uriMatcher.addURI(authority, HertsVariableMessageSign.TABLE_NAME + "/#",
                VARIABLE_MESSAGE_SIGN_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == CAR_PARKS) {
            return mimeDirPrefix + HertsCarPark.TABLE_NAME;
        }
        if (match == LATEST_CAR_PARKS) {
            return mimeDirPrefix + HertsLatestCarPark.TABLE_NAME;
        }
        if (match == CAR_PARK_ID) {
            return mimeItemPrefix + HertsCarPark.TABLE_NAME;
        }
        if (match == EVENTS) {
            return mimeDirPrefix + HertsEvent.TABLE_NAME;
        }
        if (match == LATEST_EVENTS) {
            return mimeDirPrefix + HertsLatestEvent.TABLE_NAME;
        }
        if (match == EVENT_ID) {
            return mimeItemPrefix + HertsEvent.TABLE_NAME;
        }
        if (match == ROADWORKS) {
            return mimeDirPrefix + HertsRoadworks.TABLE_NAME;
        }
        if (match == LATEST_ROADWORKS) {
            return mimeDirPrefix + HertsLatestRoadworks.TABLE_NAME;
        }
        if (match == ROADWORKS_ID) {
            return mimeItemPrefix + HertsRoadworks.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOWS) {
            return mimeDirPrefix + HertsTrafficFlow.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            return mimeDirPrefix + HertsLatestTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOW_ID) {
            return mimeItemPrefix + HertsTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_SCOOTS) {
            return mimeDirPrefix + HertsTrafficScoot.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            return mimeDirPrefix + HertsLatestTrafficScoot.TABLE_NAME;
        }
        if (match == TRAFFIC_SCOOT_ID) {
            return mimeItemPrefix + HertsTrafficScoot.TABLE_NAME;
        }
        if (match == TRAFFIC_SPEEDS) {
            return mimeDirPrefix + HertsTrafficSpeed.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            return mimeDirPrefix + HertsLatestTrafficSpeed.TABLE_NAME;
        }
        if (match == TRAFFIC_SPEED_ID) {
            return mimeItemPrefix + HertsTrafficSpeed.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + HertsTrafficTravelTime.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + HertsLatestTrafficTravelTime.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            return mimeItemPrefix + HertsTrafficTravelTime.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + HertsVariableMessageSign.TABLE_NAME;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + HertsLatestVariableMessageSign.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            return mimeItemPrefix + HertsVariableMessageSign.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            id = sqLiteDatabase.insert(HertsCarPark.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return ContentUris.withAppendedId(CAR_PARK_URI, id);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == EVENTS) {
            id = sqLiteDatabase.insert(HertsEvent.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(EVENT_URI, null);
            return ContentUris.withAppendedId(EVENT_URI, id);
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == ROADWORKS) {
            id = sqLiteDatabase.insert(HertsRoadworks.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return ContentUris.withAppendedId(ROADWORKS_URI, id);
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_FLOWS) {
            id = sqLiteDatabase.insert(HertsTrafficFlow.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_SCOOTS) {
            id = sqLiteDatabase.insert(HertsTrafficScoot.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_SCOOT_URI, id);
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_SPEEDS) {
            id = sqLiteDatabase.insert(HertsTrafficSpeed.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_SPEED_URI, id);
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            id = sqLiteDatabase.insert(HertsTrafficTravelTime.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_TRAVEL_TIME_URI, id);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            id = sqLiteDatabase.insert(HertsVariableMessageSign.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return ContentUris.withAppendedId(VARIABLE_MESSAGE_SIGN_URI, id);
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(HertsCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == LATEST_CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_CAR_PARK_URI);
            return cursor;
        }
        if (match == CAR_PARK_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsCarPark.TABLE_NAME, projection,
                    HertsCarPark._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == EVENTS) {
            Cursor cursor = sqLiteDatabase.query(HertsEvent.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, EVENT_URI);
            return cursor;
        }
        if (match == LATEST_EVENTS) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestEvent.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_EVENT_URI);
            return cursor;
        }
        if (match == EVENT_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsEvent.TABLE_NAME, projection,
                    HertsEvent._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, EVENT_URI);
            return cursor;
        }
        if (match == ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(HertsRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
            return cursor;
        }
        if (match == LATEST_ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_ROADWORKS_URI);
            return cursor;
        }
        if (match == ROADWORKS_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsRoadworks.TABLE_NAME, projection,
                    HertsRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOW_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficFlow.TABLE_NAME, projection,
                    HertsTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_SCOOTS) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficScoot.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestTrafficScoot.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == TRAFFIC_SCOOT_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficScoot.TABLE_NAME, projection,
                    HertsTrafficScoot._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == TRAFFIC_SPEEDS) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficSpeed.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestTrafficSpeed.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == TRAFFIC_SPEED_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficSpeed.TABLE_NAME, projection,
                    HertsTrafficSpeed._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsTrafficTravelTime.TABLE_NAME, projection,
                    HertsTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(HertsVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(HertsLatestVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            Cursor cursor = sqLiteDatabase.query(HertsVariableMessageSign.TABLE_NAME, projection,
                    HertsVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
                      SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            int rows = sqLiteDatabase.update(HertsCarPark.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == CAR_PARK_ID) {
            int rows = sqLiteDatabase.update(HertsCarPark.TABLE_NAME, values, HertsCarPark._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == EVENTS) {
            int rows = sqLiteDatabase.update(HertsEvent.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(EVENT_URI, null);
            return rows;
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == EVENT_ID) {
            int rows = sqLiteDatabase.update(HertsEvent.TABLE_NAME, values, HertsEvent._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(EVENT_URI, null);
            return rows;
        }
        if (match == ROADWORKS) {
            int rows = sqLiteDatabase.update(HertsRoadworks.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return rows;
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == ROADWORKS_ID) {
            int rows = sqLiteDatabase.update(HertsRoadworks.TABLE_NAME, values,
                    HertsRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return rows;
        }
        if (match == TRAFFIC_FLOWS) {
            int rows = sqLiteDatabase.update(HertsTrafficFlow.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_FLOW_ID) {
            int rows = sqLiteDatabase.update(HertsTrafficFlow.TABLE_NAME, values,
                    HertsTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == TRAFFIC_SCOOTS) {
            int rows = sqLiteDatabase.update(HertsTrafficScoot.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_SCOOT_ID) {
            int rows = sqLiteDatabase.update(HertsTrafficScoot.TABLE_NAME, values,
                    HertsTrafficScoot._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return rows;
        }
        if (match == TRAFFIC_SPEEDS) {
            int rows = sqLiteDatabase.update(HertsTrafficSpeed.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_SPEED_ID) {
            int rows = sqLiteDatabase.update(HertsTrafficSpeed.TABLE_NAME, values,
                    HertsTrafficSpeed._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return rows;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            int rows = sqLiteDatabase.update(HertsTrafficTravelTime.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            int rows = sqLiteDatabase.update(HertsTrafficTravelTime.TABLE_NAME, values,
                    HertsTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            int rows = sqLiteDatabase.update(HertsVariableMessageSign.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return rows;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            int rows = sqLiteDatabase.update(HertsVariableMessageSign.TABLE_NAME, values,
                    HertsVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return rows;
        }
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int rows = 0;
        if (match == CAR_PARKS) {
            rows = sqLiteDatabase.delete(HertsCarPark.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == EVENTS) {
            rows = sqLiteDatabase.delete(HertsEvent.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(EVENT_URI, null);
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == ROADWORKS) {
            rows = sqLiteDatabase.delete(HertsRoadworks.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_FLOWS) {
            rows = sqLiteDatabase.delete(HertsTrafficFlow.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_SCOOTS) {
            rows = sqLiteDatabase.delete(HertsTrafficScoot.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_SPEEDS) {
            rows = sqLiteDatabase.delete(HertsTrafficSpeed.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            rows = sqLiteDatabase.delete(HertsTrafficTravelTime.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            rows = sqLiteDatabase.delete(HertsVariableMessageSign.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
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
                    CarPark[] carParks = new CarParkRetriever(context).retrieve();
                    HertsContentHelper.insertIntoProvider(context, carParks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Events.
            if (extras.getBoolean(EXTRAS_EVENTS, false)) {
                try {
                    Event[] events = new EventRetriever(context).retrieve();
                    HertsContentHelper.insertIntoProvider(context, events);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Road works.
            if (extras.getBoolean(EXTRAS_ROADWORKS, false)) {
                try {
                    Roadworks[] roadworkses = new RoadworksRetriever(context).retrieve();
                    HertsContentHelper.insertIntoProvider(context, roadworkses);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic flows.
            if (extras.getBoolean(EXTRAS_TRAFFIC_FLOW, false)) {
                try {
                    TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
                    HertsContentHelper.insertIntoProvider(context, trafficFlows);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic scoots.
            if (extras.getBoolean(EXTRAS_TRAFFIC_SCOOT, false)) {
                try {
                    TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
                    HertsContentHelper.insertIntoProvider(context, trafficScoots);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic speeds.
            if (extras.getBoolean(EXTRAS_TRAFFIC_SPEED, false)) {
                try {
                    TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
                    HertsContentHelper.insertIntoProvider(context, trafficSpeeds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic travel times.
            if (extras.getBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, false)) {
                try {
                    TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
                    HertsContentHelper.insertIntoProvider(context, trafficTravelTimes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Variable message signs.
            if (extras.getBoolean(EXTRAS_VMS, false)) {
                try {
                    VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context)
                            .retrieve();
                    HertsContentHelper.insertIntoProvider(context, variableMessageSigns);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refresh(Context context, boolean carParks, boolean events, boolean roadworks,
                               boolean trafficFlow, boolean trafficScoot, boolean trafficSpeed,
                               boolean trafficTravelTime, boolean variableMessageSigns) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_CAR_PARKS, carParks);
        settingsBundle.putBoolean(EXTRAS_EVENTS, events);
        settingsBundle.putBoolean(EXTRAS_ROADWORKS, roadworks);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_FLOW, trafficFlow);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_SCOOT, trafficScoot);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_SPEED, trafficSpeed);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, trafficTravelTime);
        settingsBundle.putBoolean(EXTRAS_VMS, variableMessageSigns);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }
}
