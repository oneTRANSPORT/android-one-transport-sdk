package net.uk.onetransport.android.county.oxon.provider;

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

import net.uk.onetransport.android.county.oxon.R;
import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.events.EventRetriever;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorks;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorksRetriever;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonCarPark;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonEvent;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestCarPark;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestEvent;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestRoadWorks;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficFlow;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficQueue;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficScoot;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficSpeed;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficTravelTime;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestVariableMessageSign;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonRoadWorks;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficFlow;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficQueue;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficScoot;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficSpeed;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficTravelTime;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonVariableMessageSign;

public class OxonProviderModule implements ProviderModule {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri CAR_PARK_URI;
    public static Uri LATEST_CAR_PARK_URI;
    public static Uri EVENT_URI;
    public static Uri LATEST_EVENT_URI;
    public static Uri ROAD_WORKS_URI;
    public static Uri LATEST_ROAD_WORKS_URI;
    public static Uri TRAFFIC_FLOW_URI;
    public static Uri LATEST_TRAFFIC_FLOW_URI;
    public static Uri TRAFFIC_QUEUE_URI;
    public static Uri LATEST_TRAFFIC_QUEUE_URI;
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
            "net.uk.onetransport.android.county.oxon.sync.CAR_PARKS";
    private static final String EXTRAS_EVENTS =
            "net.uk.onetransport.android.county.oxon.sync.EVENTS";
    private static final String EXTRAS_ROAD_WORKS =
            "net.uk.onetransport.android.county.oxon.sync.ROAD_WORKS";
    private static final String EXTRAS_TRAFFIC_FLOW =
            "net.uk.onetransport.android.county.oxon.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_TRAFFIC_QUEUE =
            "net.uk.onetransport.android.county.oxon.sync.TRAFFIC_QUEUE";
    private static final String EXTRAS_TRAFFIC_SCOOT =
            "net.uk.onetransport.android.county.oxon.sync.TRAFFIC_SCOOT";
    private static final String EXTRAS_TRAFFIC_SPEED =
            "net.uk.onetransport.android.county.oxon.sync.TRAFFIC_SPEED";
    private static final String EXTRAS_TRAFFIC_TRAVEL_TIME =
            "net.uk.onetransport.android.county.oxon.sync.TRAFFIC_TRAVEL_TIME";
    private static final String EXTRAS_VMS = "net.uk.onetransport.android.county.oxon.sync.VMS";
    // Uri matching
    private static int CAR_PARKS;
    private static int LATEST_CAR_PARKS;
    private static int CAR_PARK_ID;
    private static int EVENTS;
    private static int LATEST_EVENTS;
    private static int EVENT_ID;
    private static int ROAD_WORKS;
    private static int LATEST_ROAD_WORKS;
    private static int ROAD_WORKS_ID;
    private static int TRAFFIC_FLOWS;
    private static int LATEST_TRAFFIC_FLOWS;
    private static int TRAFFIC_FLOW_ID;
    private static int TRAFFIC_QUEUES;
    private static int LATEST_TRAFFIC_QUEUES;
    private static int TRAFFIC_QUEUE_ID;
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

    public OxonProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(OxonContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_EVENT_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_ROAD_WORKS_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_ROAD_WORKS_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_TRAFFIC_QUEUE_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_TRAFFIC_QUEUE_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_TRAFFIC_SCOOT_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_TRAFFIC_SCOOT_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_TRAFFIC_SPEED_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_TRAFFIC_SPEED_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI, OxonCarPark.TABLE_NAME);
        LATEST_CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestCarPark.TABLE_NAME);
        EVENT_URI = Uri.withAppendedPath(AUTHORITY_URI, OxonEvent.TABLE_NAME);
        LATEST_EVENT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestEvent.TABLE_NAME);
        ROAD_WORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, OxonRoadWorks.TABLE_NAME);
        LATEST_ROAD_WORKS_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestRoadWorks.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI, OxonTrafficFlow.TABLE_NAME);
        LATEST_TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestTrafficFlow.TABLE_NAME);
        TRAFFIC_QUEUE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonTrafficQueue.TABLE_NAME);
        LATEST_TRAFFIC_QUEUE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestTrafficQueue.TABLE_NAME);
        TRAFFIC_SCOOT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonTrafficScoot.TABLE_NAME);
        LATEST_TRAFFIC_SCOOT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestTrafficScoot.TABLE_NAME);
        TRAFFIC_SPEED_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonTrafficSpeed.TABLE_NAME);
        LATEST_TRAFFIC_SPEED_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestTrafficSpeed.TABLE_NAME);
        TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonTrafficTravelTime.TABLE_NAME);
        LATEST_TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestTrafficTravelTime.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonVariableMessageSign.TABLE_NAME);
        LATEST_VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestVariableMessageSign.TABLE_NAME);

        CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, OxonCarPark.TABLE_NAME, CAR_PARKS);
        providerModules.add(this);
        LATEST_CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestCarPark.TABLE_NAME, LATEST_CAR_PARKS);
        providerModules.add(this);
        CAR_PARK_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonCarPark.TABLE_NAME + "/#", CAR_PARK_ID);
        providerModules.add(this);
        EVENTS = providerModules.size();
        uriMatcher.addURI(authority, OxonEvent.TABLE_NAME, EVENTS);
        providerModules.add(this);
        LATEST_EVENTS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestEvent.TABLE_NAME, LATEST_EVENTS);
        providerModules.add(this);
        EVENT_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonEvent.TABLE_NAME + "/#", EVENT_ID);
        providerModules.add(this);
        ROAD_WORKS = providerModules.size();
        uriMatcher.addURI(authority, OxonRoadWorks.TABLE_NAME, ROAD_WORKS);
        providerModules.add(this);
        LATEST_ROAD_WORKS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestRoadWorks.TABLE_NAME, LATEST_ROAD_WORKS);
        providerModules.add(this);
        ROAD_WORKS_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonRoadWorks.TABLE_NAME + "/#", ROAD_WORKS_ID);
        providerModules.add(this);
        TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
        providerModules.add(this);
        LATEST_TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestTrafficFlow.TABLE_NAME, LATEST_TRAFFIC_FLOWS);
        providerModules.add(this);
        TRAFFIC_FLOW_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
        providerModules.add(this);
        TRAFFIC_QUEUES = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficQueue.TABLE_NAME, TRAFFIC_QUEUES);
        providerModules.add(this);
        LATEST_TRAFFIC_QUEUES = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestTrafficQueue.TABLE_NAME, LATEST_TRAFFIC_QUEUES);
        providerModules.add(this);
        TRAFFIC_QUEUE_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficQueue.TABLE_NAME + "/#", TRAFFIC_QUEUE_ID);
        providerModules.add(this);
        TRAFFIC_SCOOTS = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficScoot.TABLE_NAME, TRAFFIC_SCOOTS);
        providerModules.add(this);
        LATEST_TRAFFIC_SCOOTS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestTrafficScoot.TABLE_NAME, LATEST_TRAFFIC_SCOOTS);
        providerModules.add(this);
        TRAFFIC_SCOOT_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficScoot.TABLE_NAME + "/#", TRAFFIC_SCOOT_ID);
        providerModules.add(this);
        TRAFFIC_SPEEDS = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficSpeed.TABLE_NAME, TRAFFIC_SPEEDS);
        providerModules.add(this);
        LATEST_TRAFFIC_SPEEDS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestTrafficSpeed.TABLE_NAME, LATEST_TRAFFIC_SPEEDS);
        providerModules.add(this);
        TRAFFIC_SPEED_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficSpeed.TABLE_NAME + "/#", TRAFFIC_SPEED_ID);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficTravelTime.TABLE_NAME, TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        LATEST_TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestTrafficTravelTime.TABLE_NAME,
                LATEST_TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonTrafficTravelTime.TABLE_NAME + "/#",
                TRAFFIC_TRAVEL_TIME_ID);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, OxonVariableMessageSign.TABLE_NAME,
                VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        LATEST_VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestVariableMessageSign.TABLE_NAME,
                LATEST_VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGN_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonVariableMessageSign.TABLE_NAME + "/#",
                VARIABLE_MESSAGE_SIGN_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == CAR_PARKS) {
            return mimeDirPrefix + OxonCarPark.TABLE_NAME;
        }
        if (match == LATEST_CAR_PARKS) {
            return mimeDirPrefix + OxonLatestCarPark.TABLE_NAME;
        }
        if (match == CAR_PARK_ID) {
            return mimeItemPrefix + OxonCarPark.TABLE_NAME;
        }
        if (match == EVENTS) {
            return mimeDirPrefix + OxonEvent.TABLE_NAME;
        }
        if (match == LATEST_EVENTS) {
            return mimeDirPrefix + OxonLatestEvent.TABLE_NAME;
        }
        if (match == EVENT_ID) {
            return mimeItemPrefix + OxonEvent.TABLE_NAME;
        }
        if (match == ROAD_WORKS) {
            return mimeDirPrefix + OxonRoadWorks.TABLE_NAME;
        }
        if (match == LATEST_ROAD_WORKS) {
            return mimeDirPrefix + OxonLatestRoadWorks.TABLE_NAME;
        }
        if (match == ROAD_WORKS_ID) {
            return mimeItemPrefix + OxonRoadWorks.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOWS) {
            return mimeDirPrefix + OxonTrafficFlow.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            return mimeDirPrefix + OxonLatestTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOW_ID) {
            return mimeItemPrefix + OxonTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_QUEUES) {
            return mimeDirPrefix + OxonTrafficQueue.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            return mimeDirPrefix + OxonLatestTrafficQueue.TABLE_NAME;
        }
        if (match == TRAFFIC_QUEUE_ID) {
            return mimeItemPrefix + OxonTrafficQueue.TABLE_NAME;
        }
        if (match == TRAFFIC_SCOOTS) {
            return mimeDirPrefix + OxonTrafficScoot.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            return mimeDirPrefix + OxonLatestTrafficScoot.TABLE_NAME;
        }
        if (match == TRAFFIC_SCOOT_ID) {
            return mimeItemPrefix + OxonTrafficScoot.TABLE_NAME;
        }
        if (match == TRAFFIC_SPEEDS) {
            return mimeDirPrefix + OxonTrafficSpeed.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            return mimeDirPrefix + OxonLatestTrafficSpeed.TABLE_NAME;
        }
        if (match == TRAFFIC_SPEED_ID) {
            return mimeItemPrefix + OxonTrafficSpeed.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + OxonTrafficTravelTime.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + OxonLatestTrafficTravelTime.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            return mimeItemPrefix + OxonTrafficTravelTime.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + OxonVariableMessageSign.TABLE_NAME;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + OxonLatestVariableMessageSign.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            return mimeItemPrefix + OxonVariableMessageSign.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            id = sqLiteDatabase.insert(OxonCarPark.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return ContentUris.withAppendedId(CAR_PARK_URI, id);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == EVENTS) {
            id = sqLiteDatabase.insert(OxonEvent.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(EVENT_URI, null);
            return ContentUris.withAppendedId(EVENT_URI, id);
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == ROAD_WORKS) {
            id = sqLiteDatabase.insert(OxonRoadWorks.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
            return ContentUris.withAppendedId(ROAD_WORKS_URI, id);
        }
        if (match == LATEST_ROAD_WORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_FLOWS) {
            id = sqLiteDatabase.insert(OxonTrafficFlow.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_QUEUES) {
            id = sqLiteDatabase.insert(OxonTrafficQueue.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_QUEUE_URI, id);
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_SCOOTS) {
            id = sqLiteDatabase.insert(OxonTrafficScoot.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_SCOOT_URI, id);
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_SPEEDS) {
            id = sqLiteDatabase.insert(OxonTrafficSpeed.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_SPEED_URI, id);
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            id = sqLiteDatabase.insert(OxonTrafficTravelTime.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_TRAVEL_TIME_URI, id);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            id = sqLiteDatabase.insert(OxonVariableMessageSign.TABLE_NAME, null, contentValues);
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
            Cursor cursor = sqLiteDatabase.query(OxonCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == LATEST_CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_CAR_PARK_URI);
            return cursor;
        }
        if (match == CAR_PARK_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonCarPark.TABLE_NAME, projection,
                    OxonCarPark._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == EVENTS) {
            Cursor cursor = sqLiteDatabase.query(OxonEvent.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, EVENT_URI);
            return cursor;
        }
        if (match == LATEST_EVENTS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestEvent.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_EVENT_URI);
            return cursor;
        }
        if (match == EVENT_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonEvent.TABLE_NAME, projection,
                    OxonEvent._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, EVENT_URI);
            return cursor;
        }
        if (match == ROAD_WORKS) {
            Cursor cursor = sqLiteDatabase.query(OxonRoadWorks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, ROAD_WORKS_URI);
            return cursor;
        }
        if (match == LATEST_ROAD_WORKS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestRoadWorks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_ROAD_WORKS_URI);
            return cursor;
        }
        if (match == ROAD_WORKS_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonRoadWorks.TABLE_NAME, projection,
                    OxonRoadWorks._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, ROAD_WORKS_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOW_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficFlow.TABLE_NAME, projection,
                    OxonTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_QUEUES) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficQueue.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_QUEUE_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestTrafficQueue.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_QUEUE_URI);
            return cursor;
        }
        if (match == TRAFFIC_QUEUE_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficQueue.TABLE_NAME, projection,
                    OxonTrafficQueue._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_QUEUE_URI);
            return cursor;
        }
        if (match == TRAFFIC_SCOOTS) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficScoot.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestTrafficScoot.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == TRAFFIC_SCOOT_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficScoot.TABLE_NAME, projection,
                    OxonTrafficScoot._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == TRAFFIC_SPEEDS) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficSpeed.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestTrafficSpeed.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == TRAFFIC_SPEED_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficSpeed.TABLE_NAME, projection,
                    OxonTrafficSpeed._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonTrafficTravelTime.TABLE_NAME, projection,
                    OxonTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(OxonVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonVariableMessageSign.TABLE_NAME, projection,
                    OxonVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
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
            int rows = sqLiteDatabase.update(OxonCarPark.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == CAR_PARK_ID) {
            int rows = sqLiteDatabase.update(OxonCarPark.TABLE_NAME, values, OxonCarPark._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == EVENTS) {
            int rows = sqLiteDatabase.update(OxonEvent.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(EVENT_URI, null);
            return rows;
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == EVENT_ID) {
            int rows = sqLiteDatabase.update(OxonEvent.TABLE_NAME, values, OxonEvent._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(EVENT_URI, null);
            return rows;
        }
        if (match == ROAD_WORKS) {
            int rows = sqLiteDatabase.update(OxonRoadWorks.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
            return rows;
        }
        if (match == LATEST_ROAD_WORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == ROAD_WORKS_ID) {
            int rows = sqLiteDatabase.update(OxonRoadWorks.TABLE_NAME, values,
                    OxonRoadWorks._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
            return rows;
        }
        if (match == TRAFFIC_FLOWS) {
            int rows = sqLiteDatabase.update(OxonTrafficFlow.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_FLOW_ID) {
            int rows = sqLiteDatabase.update(OxonTrafficFlow.TABLE_NAME, values,
                    OxonTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == TRAFFIC_QUEUES) {
            int rows = sqLiteDatabase.update(OxonTrafficQueue.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_QUEUE_ID) {
            int rows = sqLiteDatabase.update(OxonTrafficQueue.TABLE_NAME, values,
                    OxonTrafficQueue._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
            return rows;
        }
        if (match == TRAFFIC_SCOOTS) {
            int rows = sqLiteDatabase.update(OxonTrafficScoot.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_SCOOT_ID) {
            int rows = sqLiteDatabase.update(OxonTrafficScoot.TABLE_NAME, values,
                    OxonTrafficScoot._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return rows;
        }
        if (match == TRAFFIC_SPEEDS) {
            int rows = sqLiteDatabase.update(OxonTrafficSpeed.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_SPEED_ID) {
            int rows = sqLiteDatabase.update(OxonTrafficSpeed.TABLE_NAME, values,
                    OxonTrafficSpeed._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return rows;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            int rows = sqLiteDatabase.update(OxonTrafficTravelTime.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            int rows = sqLiteDatabase.update(OxonTrafficTravelTime.TABLE_NAME, values,
                    OxonTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            int rows = sqLiteDatabase.update(OxonVariableMessageSign.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return rows;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            int rows = sqLiteDatabase.update(OxonVariableMessageSign.TABLE_NAME, values,
                    OxonVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()});
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
            rows = sqLiteDatabase.delete(OxonCarPark.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == EVENTS) {
            rows = sqLiteDatabase.delete(OxonEvent.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(EVENT_URI, null);
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == ROAD_WORKS) {
            rows = sqLiteDatabase.delete(OxonRoadWorks.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(ROAD_WORKS_URI, null);
        }
        if (match == LATEST_ROAD_WORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_FLOWS) {
            rows = sqLiteDatabase.delete(OxonTrafficFlow.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_QUEUES) {
            rows = sqLiteDatabase.delete(OxonTrafficQueue.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_SCOOTS) {
            rows = sqLiteDatabase.delete(OxonTrafficScoot.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_SPEEDS) {
            rows = sqLiteDatabase.delete(OxonTrafficSpeed.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            rows = sqLiteDatabase.delete(OxonTrafficTravelTime.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed));
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            rows = sqLiteDatabase.delete(OxonVariableMessageSign.TABLE_NAME, selection, selectionArgs);
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
                    OxonContentHelper.insertIntoProvider(context, carParks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Events.
            if (extras.getBoolean(EXTRAS_EVENTS, false)) {
                try {
                    Event[] events = new EventRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, events);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Road works.
            if (extras.getBoolean(EXTRAS_ROAD_WORKS, false)) {
                try {
                    RoadWorks[] roadWorkses = new RoadWorksRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, roadWorkses);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic flows.
            if (extras.getBoolean(EXTRAS_TRAFFIC_FLOW, false)) {
                try {
                    TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, trafficFlows);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic queues.
            if (extras.getBoolean(EXTRAS_TRAFFIC_QUEUE, false)) {
                try {
                    TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, trafficQueues);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic scoots.
            if (extras.getBoolean(EXTRAS_TRAFFIC_SCOOT, false)) {
                try {
                    TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, trafficScoots);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic speeds.
            if (extras.getBoolean(EXTRAS_TRAFFIC_SPEED, false)) {
                try {
                    TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, trafficSpeeds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic travel times.
            if (extras.getBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, false)) {
                try {
                    TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, trafficTravelTimes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Variable message signs.
            if (extras.getBoolean(EXTRAS_VMS, false)) {
                try {
                    VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context)
                            .retrieve();
                    OxonContentHelper.insertIntoProvider(context, variableMessageSigns);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refresh(Context context, boolean carParks, boolean events, boolean roadWorks,
                               boolean trafficFlow, boolean trafficQueue, boolean trafficScoot, boolean trafficSpeed,
                               boolean trafficTravelTime, boolean variableMessageSigns) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_CAR_PARKS, carParks);
        settingsBundle.putBoolean(EXTRAS_EVENTS, events);
        settingsBundle.putBoolean(EXTRAS_ROAD_WORKS, roadWorks);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_FLOW, trafficFlow);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_QUEUE, trafficQueue);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_SCOOT, trafficScoot);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_SPEED, trafficSpeed);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, trafficTravelTime);
        settingsBundle.putBoolean(EXTRAS_VMS, variableMessageSigns);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }
}
