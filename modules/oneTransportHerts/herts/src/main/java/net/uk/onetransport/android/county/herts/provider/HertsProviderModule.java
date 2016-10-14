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
import android.database.sqlite.SQLiteStatement;
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
    public int bulkInsert(int match, ContentValues[] contentValues, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int numInserted = 0;
        if (match == CAR_PARKS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsCarPark.TABLE_NAME + "("
                                + HertsCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                                + HertsCarPark.COLUMN_LATITUDE + ","
                                + HertsCarPark.COLUMN_LONGITUDE + ","
                                + HertsCarPark.COLUMN_OCCUPANCY + ","
                                + HertsCarPark.COLUMN_OCCUPANCY_TREND + ","
                                + HertsCarPark.COLUMN_TOTAL_PARKING_CAPACITY + ","
                                + HertsCarPark.COLUMN_FILL_RATE + ","
                                + HertsCarPark.COLUMN_EXIT_RATE + ","
                                + HertsCarPark.COLUMN_ALMOST_FULL_INCREASING + ","
                                + HertsCarPark.COLUMN_ALMOST_FULL_DECREASING + ","
                                + HertsCarPark.COLUMN_FULL_DECREASING + ","
                                + HertsCarPark.COLUMN_FULL_INCREASING + ","
                                + HertsCarPark.COLUMN_STATUS + ","
                                + HertsCarPark.COLUMN_STATUS_TIME + ","
                                + HertsCarPark.COLUMN_QUEUING_TIME + ","
                                + HertsCarPark.COLUMN_PARKING_AREA_NAME + ","
                                + HertsCarPark.COLUMN_ENTRANCE_FULL + ","
                                + HertsCarPark.COLUMN_CIN_ID + ","
                                + HertsCarPark.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String identity = value.getAsString(HertsCarPark.COLUMN_CAR_PARK_IDENTITY);
                    Double latitude = value.getAsDouble(HertsCarPark.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(HertsCarPark.COLUMN_LONGITUDE);
                    Double occupancy = value.getAsDouble(HertsCarPark.COLUMN_OCCUPANCY);
                    String occupancyTrend = value.getAsString(HertsCarPark.COLUMN_OCCUPANCY_TREND);
                    Double totalParkingCapacity = value.getAsDouble(HertsCarPark.COLUMN_TOTAL_PARKING_CAPACITY);
                    Double fillRate = value.getAsDouble(HertsCarPark.COLUMN_FILL_RATE);
                    Double exitRate = value.getAsDouble(HertsCarPark.COLUMN_EXIT_RATE);
                    Double almostFullIncreasing = value.getAsDouble(HertsCarPark.COLUMN_ALMOST_FULL_INCREASING);
                    Double almostFullDecreasing = value.getAsDouble(HertsCarPark.COLUMN_ALMOST_FULL_DECREASING);
                    Double fullDecreasing = value.getAsDouble(HertsCarPark.COLUMN_FULL_DECREASING);
                    Double fullIncreasing = value.getAsDouble(HertsCarPark.COLUMN_FULL_INCREASING);
                    String status = value.getAsString(HertsCarPark.COLUMN_STATUS);
                    String statusTime = value.getAsString(HertsCarPark.COLUMN_STATUS_TIME);
                    Double queuingTime = value.getAsDouble(HertsCarPark.COLUMN_QUEUING_TIME);
                    String parkingAreaName = value.getAsString(HertsCarPark.COLUMN_PARKING_AREA_NAME);
                    Double entranceFull = value.getAsDouble(HertsCarPark.COLUMN_ENTRANCE_FULL);
                    String cinId = value.getAsString(HertsCarPark.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsCarPark.COLUMN_CREATION_TIME);
                    if (identity != null) {
                        insert.bindString(1, identity);
                    }
                    if (latitude != null) {
                        insert.bindDouble(2, latitude);
                    }
                    if (longitude != null) {
                        insert.bindDouble(3, longitude);
                    }
                    if (occupancy != null) {
                        insert.bindDouble(4, occupancy);
                    }
                    if (occupancyTrend != null) {
                        insert.bindString(5, occupancyTrend);
                    }
                    if (totalParkingCapacity != null) {
                        insert.bindDouble(6, totalParkingCapacity);
                    }
                    if (fillRate != null) {
                        insert.bindDouble(7, fillRate);
                    }
                    if (exitRate != null) {
                        insert.bindDouble(8, exitRate);
                    }
                    if (almostFullIncreasing != null) {
                        insert.bindDouble(9, almostFullIncreasing);
                    }
                    if (almostFullDecreasing != null) {
                        insert.bindDouble(10, almostFullDecreasing);
                    }
                    if (fullDecreasing != null) {
                        insert.bindDouble(11, fullDecreasing);
                    }
                    if (fullIncreasing != null) {
                        insert.bindDouble(12, fullIncreasing);
                    }
                    if (status != null) {
                        insert.bindString(13, status);
                    }
                    if (statusTime != null) {
                        insert.bindString(14, statusTime);
                    }
                    if (queuingTime != null) {
                        insert.bindDouble(15, queuingTime);
                    }
                    if (parkingAreaName != null) {
                        insert.bindString(16, parkingAreaName);
                    }
                    if (entranceFull != null) {
                        insert.bindDouble(17, entranceFull);
                    }
                    if (cinId != null) {
                        insert.bindString(18, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(19, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(CAR_PARK_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == EVENTS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsEvent.TABLE_NAME + "("
                                + HertsEvent.COLUMN_ID + ","
                                + HertsEvent.COLUMN_START_OF_PERIOD + ","
                                + HertsEvent.COLUMN_END_OF_PERIOD + ","
                                + HertsEvent.COLUMN_OVERALL_START_TIME + ","
                                + HertsEvent.COLUMN_OVERALL_END_TIME + ","
                                + HertsEvent.COLUMN_LATITUDE + ","
                                + HertsEvent.COLUMN_LONGITUDE + ","
                                + HertsEvent.COLUMN_DESCRIPTION + ","
                                + HertsEvent.COLUMN_IMPACT_ON_TRAFFIC + ","
                                + HertsEvent.COLUMN_VALIDITY_STATUS + ","
                                + HertsEvent.COLUMN_CIN_ID + ","
                                + HertsEvent.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(HertsEvent.COLUMN_ID);
                    String startOfPeriod = value.getAsString(HertsEvent.COLUMN_START_OF_PERIOD);
                    String endOfPeriod = value.getAsString(HertsEvent.COLUMN_END_OF_PERIOD);
                    String overallStartTime = value.getAsString(HertsEvent.COLUMN_OVERALL_START_TIME);
                    String overallEndTime = value.getAsString(HertsEvent.COLUMN_OVERALL_END_TIME);
                    Double latitude = value.getAsDouble(HertsEvent.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(HertsEvent.COLUMN_LONGITUDE);
                    String description = value.getAsString(HertsEvent.COLUMN_DESCRIPTION);
                    String impactOnTraffic = value.getAsString(HertsEvent.COLUMN_IMPACT_ON_TRAFFIC);
                    String validityStatus = value.getAsString(HertsEvent.COLUMN_VALIDITY_STATUS);
                    String cinId = value.getAsString(HertsEvent.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsEvent.COLUMN_CREATION_TIME);
                    if (id != null) {
                        insert.bindString(1, id);
                    }
                    if (startOfPeriod != null) {
                        insert.bindString(2, startOfPeriod);
                    }
                    if (endOfPeriod != null) {
                        insert.bindString(3, endOfPeriod);
                    }
                    if (overallStartTime != null) {
                        insert.bindString(4, overallStartTime);
                    }
                    if (overallEndTime != null) {
                        insert.bindString(5, overallEndTime);
                    }
                    if (latitude != null) {
                        insert.bindDouble(6, latitude);
                    }
                    if (longitude != null) {
                        insert.bindDouble(7, longitude);
                    }
                    if (description != null) {
                        insert.bindString(8, description);
                    }
                    if (impactOnTraffic != null) {
                        insert.bindString(9, impactOnTraffic);
                    }
                    if (validityStatus != null) {
                        insert.bindString(10, validityStatus);
                    }
                    if (cinId != null) {
                        insert.bindString(11, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(12, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(EVENT_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == ROADWORKS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsRoadworks.TABLE_NAME + "("
                                + HertsRoadworks.COLUMN_ID + ","
                                + HertsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT + ","
                                + HertsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE + ","
                                + HertsRoadworks.COLUMN_COMMENT + ","
                                + HertsRoadworks.COLUMN_IMPACT_ON_TRAFFIC + ","
                                + HertsRoadworks.COLUMN_LATITUDE + ","
                                + HertsRoadworks.COLUMN_LONGITUDE + ","
                                + HertsRoadworks.COLUMN_VALIDITY_STATUS + ","
                                + HertsRoadworks.COLUMN_OVERALL_START_TIME + ","
                                + HertsRoadworks.COLUMN_OVERALL_END_TIME + ","
                                + HertsRoadworks.COLUMN_START_OF_PERIOD + ","
                                + HertsRoadworks.COLUMN_END_OF_PERIOD + ","
                                + HertsRoadworks.COLUMN_CIN_ID + ","
                                + HertsRoadworks.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(HertsRoadworks.COLUMN_ID);
                    String effectOnRoadLayout = value.getAsString(HertsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT);
                    String roadMaintenanceType = value.getAsString(HertsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE);
                    String comment = value.getAsString(HertsRoadworks.COLUMN_COMMENT);
                    String impactOnTraffic = value.getAsString(HertsRoadworks.COLUMN_IMPACT_ON_TRAFFIC);
                    Double latitude = value.getAsDouble(HertsRoadworks.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(HertsRoadworks.COLUMN_LONGITUDE);
                    String validityStatus = value.getAsString(HertsRoadworks.COLUMN_VALIDITY_STATUS);
                    String overallStartTime = value.getAsString(HertsRoadworks.COLUMN_OVERALL_START_TIME);
                    String overallEndTime = value.getAsString(HertsRoadworks.COLUMN_OVERALL_END_TIME);
                    String startOfPeriod = value.getAsString(HertsRoadworks.COLUMN_START_OF_PERIOD);
                    String endOfPeriod = value.getAsString(HertsRoadworks.COLUMN_END_OF_PERIOD);
                    String cinId = value.getAsString(HertsRoadworks.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsRoadworks.COLUMN_CREATION_TIME);
                    if (id != null) {
                        insert.bindString(1, id);
                    }
                    if (effectOnRoadLayout != null) {
                        insert.bindString(2, effectOnRoadLayout);
                    }
                    if (roadMaintenanceType != null) {
                        insert.bindString(3, roadMaintenanceType);
                    }
                    if (comment != null) {
                        insert.bindString(4, comment);
                    }
                    if (impactOnTraffic != null) {
                        insert.bindString(5, impactOnTraffic);
                    }
                    if (latitude != null) {
                        insert.bindDouble(6, latitude);
                    }
                    if (longitude != null) {
                        insert.bindDouble(7, longitude);
                    }
                    if (validityStatus != null) {
                        insert.bindString(8, validityStatus);
                    }
                    if (overallStartTime != null) {
                        insert.bindString(9, overallStartTime);
                    }
                    if (overallEndTime != null) {
                        insert.bindString(10, overallEndTime);
                    }
                    if (startOfPeriod != null) {
                        insert.bindString(11, startOfPeriod);
                    }
                    if (endOfPeriod != null) {
                        insert.bindString(12, endOfPeriod);
                    }
                    if (cinId != null) {
                        insert.bindString(13, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(14, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(ROADWORKS_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_FLOWS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsTrafficFlow.TABLE_NAME + "("
                                + HertsTrafficFlow.COLUMN_ID + ","
                                + HertsTrafficFlow.COLUMN_TPEG_DIRECTION + ","
                                + HertsTrafficFlow.COLUMN_FROM_TYPE + ","
                                + HertsTrafficFlow.COLUMN_FROM_DESCRIPTOR + ","
                                + HertsTrafficFlow.COLUMN_FROM_LATITUDE + ","
                                + HertsTrafficFlow.COLUMN_FROM_LONGITUDE + ","
                                + HertsTrafficFlow.COLUMN_TO_TYPE + ","
                                + HertsTrafficFlow.COLUMN_TO_DESCRIPTOR + ","
                                + HertsTrafficFlow.COLUMN_TO_LATITUDE + ","
                                + HertsTrafficFlow.COLUMN_TO_LONGITUDE + ","
                                + HertsTrafficFlow.COLUMN_TIME + ","
                                + HertsTrafficFlow.COLUMN_VEHICLE_FLOW + ","
                                + HertsTrafficFlow.COLUMN_CIN_ID + ","
                                + HertsTrafficFlow.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(HertsTrafficFlow.COLUMN_ID);
                    String tpegDirection = value.getAsString(HertsTrafficFlow.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(HertsTrafficFlow.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(HertsTrafficFlow.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(HertsTrafficFlow.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(HertsTrafficFlow.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(HertsTrafficFlow.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(HertsTrafficFlow.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(HertsTrafficFlow.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(HertsTrafficFlow.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(HertsTrafficFlow.COLUMN_TIME);
                    Double vehicleFlow = value.getAsDouble(HertsTrafficFlow.COLUMN_VEHICLE_FLOW);
                    String cinId = value.getAsString(HertsTrafficFlow.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsTrafficFlow.COLUMN_CREATION_TIME);
                    if (id != null) {
                        insert.bindString(1, id);
                    }
                    if (tpegDirection != null) {
                        insert.bindString(2, tpegDirection);
                    }
                    if (fromType != null) {
                        insert.bindString(3, fromType);
                    }
                    if (fromDescriptor != null) {
                        insert.bindString(4, fromDescriptor);
                    }
                    if (fromLatitude != null) {
                        insert.bindDouble(5, fromLatitude);
                    }
                    if (fromLongitude != null) {
                        insert.bindDouble(6, fromLongitude);
                    }
                    if (toType != null) {
                        insert.bindString(7, toType);
                    }
                    if (toDescriptor != null) {
                        insert.bindString(8, toDescriptor);
                    }
                    if (toLatitude != null) {
                        insert.bindDouble(9, toLatitude);
                    }
                    if (toLongitude != null) {
                        insert.bindDouble(10, toLongitude);
                    }
                    if (time != null) {
                        insert.bindString(11, time);
                    }
                    if (vehicleFlow != null) {
                        insert.bindDouble(12, vehicleFlow);
                    }
                    if (cinId != null) {
                        insert.bindString(13, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(14, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_SCOOTS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsTrafficScoot.TABLE_NAME + "("
                                + HertsTrafficScoot.COLUMN_ID + ","
                                + HertsTrafficScoot.COLUMN_TPEG_DIRECTION + ","
                                + HertsTrafficScoot.COLUMN_FROM_TYPE + ","
                                + HertsTrafficScoot.COLUMN_FROM_DESCRIPTOR + ","
                                + HertsTrafficScoot.COLUMN_FROM_LATITUDE + ","
                                + HertsTrafficScoot.COLUMN_FROM_LONGITUDE + ","
                                + HertsTrafficScoot.COLUMN_TO_TYPE + ","
                                + HertsTrafficScoot.COLUMN_TO_DESCRIPTOR + ","
                                + HertsTrafficScoot.COLUMN_TO_LATITUDE + ","
                                + HertsTrafficScoot.COLUMN_TO_LONGITUDE + ","
                                + HertsTrafficScoot.COLUMN_TIME + ","
                                + HertsTrafficScoot.COLUMN_CURRENT_FLOW + ","
                                + HertsTrafficScoot.COLUMN_AVERAGE_SPEED + ","
                                + HertsTrafficScoot.COLUMN_LINK_STATUS_TYPE + ","
                                + HertsTrafficScoot.COLUMN_LINK_STATUS + ","
                                + HertsTrafficScoot.COLUMN_LINK_TRAVEL_TIME + ","
                                + HertsTrafficScoot.COLUMN_CONGESTION_PERCENT + ","
                                + HertsTrafficScoot.COLUMN_CIN_ID + ","
                                + HertsTrafficScoot.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(HertsTrafficScoot.COLUMN_ID);
                    String tpegDirection = value.getAsString(HertsTrafficScoot.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(HertsTrafficScoot.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(HertsTrafficScoot.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(HertsTrafficScoot.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(HertsTrafficScoot.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(HertsTrafficScoot.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(HertsTrafficScoot.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(HertsTrafficScoot.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(HertsTrafficScoot.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(HertsTrafficScoot.COLUMN_TIME);
                    Double currentFlow = value.getAsDouble(HertsTrafficScoot.COLUMN_CURRENT_FLOW);
                    Double averageSpeed = value.getAsDouble(HertsTrafficScoot.COLUMN_AVERAGE_SPEED);
                    Double linkStatusType = value.getAsDouble(HertsTrafficScoot.COLUMN_LINK_STATUS_TYPE);
                    Double linkStatus = value.getAsDouble(HertsTrafficScoot.COLUMN_LINK_STATUS);
                    Double linkTravelTime = value.getAsDouble(HertsTrafficScoot.COLUMN_LINK_TRAVEL_TIME);
                    Double congestionPercent = value.getAsDouble(HertsTrafficScoot.COLUMN_CONGESTION_PERCENT);
                    String cinId = value.getAsString(HertsTrafficScoot.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsTrafficScoot.COLUMN_CREATION_TIME);
                    if (id != null) {
                        insert.bindString(1, id);
                    }
                    if (tpegDirection != null) {
                        insert.bindString(2, tpegDirection);
                    }
                    if (fromType != null) {
                        insert.bindString(3, fromType);
                    }
                    if (fromDescriptor != null) {
                        insert.bindString(4, fromDescriptor);
                    }
                    if (fromLatitude != null) {
                        insert.bindDouble(5, fromLatitude);
                    }
                    if (fromLongitude != null) {
                        insert.bindDouble(6, fromLongitude);
                    }
                    if (toType != null) {
                        insert.bindString(7, toType);
                    }
                    if (toDescriptor != null) {
                        insert.bindString(8, toDescriptor);
                    }
                    if (toLatitude != null) {
                        insert.bindDouble(9, toLatitude);
                    }
                    if (toLongitude != null) {
                        insert.bindDouble(10, toLongitude);
                    }
                    if (time != null) {
                        insert.bindString(11, time);
                    }
                    if (currentFlow != null) {
                        insert.bindDouble(12, currentFlow);
                    }
                    if (averageSpeed != null) {
                        insert.bindDouble(13, averageSpeed);
                    }
                    if (linkStatusType != null) {
                        insert.bindDouble(14, linkStatusType);
                    }
                    if (linkStatus != null) {
                        insert.bindDouble(15, linkStatus);
                    }
                    if (linkTravelTime != null) {
                        insert.bindDouble(16, linkTravelTime);
                    }
                    if (congestionPercent != null) {
                        insert.bindDouble(17, congestionPercent);
                    }
                    if (cinId != null) {
                        insert.bindString(18, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(19, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_SPEEDS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsTrafficSpeed.TABLE_NAME + "("
                                + HertsTrafficSpeed.COLUMN_ID + ","
                                + HertsTrafficSpeed.COLUMN_TPEG_DIRECTION + ","
                                + HertsTrafficSpeed.COLUMN_FROM_TYPE + ","
                                + HertsTrafficSpeed.COLUMN_FROM_DESCRIPTOR + ","
                                + HertsTrafficSpeed.COLUMN_FROM_LATITUDE + ","
                                + HertsTrafficSpeed.COLUMN_FROM_LONGITUDE + ","
                                + HertsTrafficSpeed.COLUMN_TO_TYPE + ","
                                + HertsTrafficSpeed.COLUMN_TO_DESCRIPTOR + ","
                                + HertsTrafficSpeed.COLUMN_TO_LATITUDE + ","
                                + HertsTrafficSpeed.COLUMN_TO_LONGITUDE + ","
                                + HertsTrafficSpeed.COLUMN_TIME + ","
                                + HertsTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED + ","
                                + HertsTrafficSpeed.COLUMN_CIN_ID + ","
                                + HertsTrafficSpeed.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(HertsTrafficSpeed.COLUMN_ID);
                    String tpegDirection = value.getAsString(HertsTrafficSpeed.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(HertsTrafficSpeed.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(HertsTrafficSpeed.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(HertsTrafficSpeed.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(HertsTrafficSpeed.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(HertsTrafficSpeed.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(HertsTrafficSpeed.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(HertsTrafficSpeed.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(HertsTrafficSpeed.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(HertsTrafficSpeed.COLUMN_TIME);
                    Double averageVehicleSpeed = value.getAsDouble(HertsTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED);
                    String cinId = value.getAsString(HertsTrafficSpeed.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsTrafficSpeed.COLUMN_CREATION_TIME);
                    if (id != null) {
                        insert.bindString(1, id);
                    }
                    if (tpegDirection != null) {
                        insert.bindString(2, tpegDirection);
                    }
                    if (fromType != null) {
                        insert.bindString(3, fromType);
                    }
                    if (fromDescriptor != null) {
                        insert.bindString(4, fromDescriptor);
                    }
                    if (fromLatitude != null) {
                        insert.bindDouble(5, fromLatitude);
                    }
                    if (fromLongitude != null) {
                        insert.bindDouble(6, fromLongitude);
                    }
                    if (toType != null) {
                        insert.bindString(7, toType);
                    }
                    if (toDescriptor != null) {
                        insert.bindString(8, toDescriptor);
                    }
                    if (toLatitude != null) {
                        insert.bindDouble(9, toLatitude);
                    }
                    if (toLongitude != null) {
                        insert.bindDouble(10, toLongitude);
                    }
                    if (time != null) {
                        insert.bindString(11, time);
                    }
                    if (averageVehicleSpeed != null) {
                        insert.bindDouble(12, averageVehicleSpeed);
                    }
                    if (cinId != null) {
                        insert.bindString(13, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(14, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsTrafficTravelTime.TABLE_NAME + "("
                                + HertsTrafficTravelTime.COLUMN_ID + ","
                                + HertsTrafficTravelTime.COLUMN_TPEG_DIRECTION + ","
                                + HertsTrafficTravelTime.COLUMN_FROM_TYPE + ","
                                + HertsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + ","
                                + HertsTrafficTravelTime.COLUMN_FROM_LATITUDE + ","
                                + HertsTrafficTravelTime.COLUMN_FROM_LONGITUDE + ","
                                + HertsTrafficTravelTime.COLUMN_TO_TYPE + ","
                                + HertsTrafficTravelTime.COLUMN_TO_DESCRIPTOR + ","
                                + HertsTrafficTravelTime.COLUMN_TO_LATITUDE + ","
                                + HertsTrafficTravelTime.COLUMN_TO_LONGITUDE + ","
                                + HertsTrafficTravelTime.COLUMN_TIME + ","
                                + HertsTrafficTravelTime.COLUMN_TRAVEL_TIME + ","
                                + HertsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + ","
                                + HertsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + ","
                                + HertsTrafficTravelTime.COLUMN_CIN_ID + ","
                                + HertsTrafficTravelTime.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(HertsTrafficTravelTime.COLUMN_ID);
                    String tpegDirection = value.getAsString(HertsTrafficTravelTime.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(HertsTrafficTravelTime.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(HertsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(HertsTrafficTravelTime.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(HertsTrafficTravelTime.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(HertsTrafficTravelTime.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(HertsTrafficTravelTime.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(HertsTrafficTravelTime.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(HertsTrafficTravelTime.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(HertsTrafficTravelTime.COLUMN_TIME);
                    Double travelTime = value.getAsDouble(HertsTrafficTravelTime.COLUMN_TRAVEL_TIME);
                    Double freeFlowTravelTime = value.getAsDouble(HertsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME);
                    Double freeFlowSpeed = value.getAsDouble(HertsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED);
                    String cinId = value.getAsString(HertsTrafficTravelTime.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsTrafficTravelTime.COLUMN_CREATION_TIME);
                    if (id != null) {
                        insert.bindString(1, id);
                    }
                    if (tpegDirection != null) {
                        insert.bindString(2, tpegDirection);
                    }
                    if (fromType != null) {
                        insert.bindString(3, fromType);
                    }
                    if (fromDescriptor != null) {
                        insert.bindString(4, fromDescriptor);
                    }
                    if (fromLatitude != null) {
                        insert.bindDouble(5, fromLatitude);
                    }
                    if (fromLongitude != null) {
                        insert.bindDouble(6, fromLongitude);
                    }
                    if (toType != null) {
                        insert.bindString(7, toType);
                    }
                    if (toDescriptor != null) {
                        insert.bindString(8, toDescriptor);
                    }
                    if (toLatitude != null) {
                        insert.bindDouble(9, toLatitude);
                    }
                    if (toLongitude != null) {
                        insert.bindDouble(10, toLongitude);
                    }
                    if (time != null) {
                        insert.bindString(11, time);
                    }
                    if (travelTime != null) {
                        insert.bindDouble(12, travelTime);
                    }
                    if (freeFlowTravelTime != null) {
                        insert.bindDouble(13, freeFlowTravelTime);
                    }
                    if (freeFlowSpeed != null) {
                        insert.bindDouble(14, freeFlowSpeed);
                    }
                    if (cinId != null) {
                        insert.bindString(15, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(16, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + HertsVariableMessageSign.TABLE_NAME + "("
                                + HertsVariableMessageSign.COLUMN_LOCATION_ID + ","
                                + HertsVariableMessageSign.COLUMN_DESCRIPTION + ","
                                + HertsVariableMessageSign.COLUMN_VMS_TYPE + ","
                                + HertsVariableMessageSign.COLUMN_LATITUDE + ","
                                + HertsVariableMessageSign.COLUMN_LONGITUDE + ","
                                + HertsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + ","
                                + HertsVariableMessageSign.COLUMN_NUMBER_OF_ROWS + ","
                                + HertsVariableMessageSign.COLUMN_VMS_LEGENDS + ","
                                + HertsVariableMessageSign.COLUMN_CIN_ID + ","
                                + HertsVariableMessageSign.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String locationId = value.getAsString(HertsVariableMessageSign.COLUMN_LOCATION_ID);
                    String description = value.getAsString(HertsVariableMessageSign.COLUMN_DESCRIPTION);
                    String vmsType = value.getAsString(HertsVariableMessageSign.COLUMN_VMS_TYPE);
                    Double latitude = value.getAsDouble(HertsVariableMessageSign.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(HertsVariableMessageSign.COLUMN_LONGITUDE);
                    Long numberOfCharacters = value.getAsLong(HertsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS);
                    Long numberOfRows = value.getAsLong(HertsVariableMessageSign.COLUMN_NUMBER_OF_ROWS);
                    String vmsLegends = value.getAsString(HertsVariableMessageSign.COLUMN_VMS_LEGENDS);
                    String cinId = value.getAsString(HertsVariableMessageSign.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(HertsVariableMessageSign.COLUMN_CREATION_TIME);
                    if (locationId != null) {
                        insert.bindString(1, locationId);
                    }
                    if (description != null) {
                        insert.bindString(2, description);
                    }
                    if (vmsType != null) {
                        insert.bindString(3, vmsType);
                    }
                    if (latitude != null) {
                        insert.bindDouble(4, latitude);
                    }
                    if (locationId != null) {
                        insert.bindDouble(5, longitude);
                    }
                    if (numberOfCharacters != null) {
                        insert.bindLong(6, numberOfCharacters);
                    }
                    if (numberOfRows != null) {
                        insert.bindLong(7, numberOfRows);
                    }
                    if (vmsLegends != null) {
                        insert.bindString(8, vmsLegends);
                    }
                    if (cinId != null) {
                        insert.bindString(9, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(10, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        return numInserted;
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
