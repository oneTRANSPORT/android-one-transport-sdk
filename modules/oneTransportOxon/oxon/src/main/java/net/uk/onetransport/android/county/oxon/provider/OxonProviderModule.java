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
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;

import net.uk.onetransport.android.county.oxon.R;
import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.events.EventRetriever;
import net.uk.onetransport.android.county.oxon.roadworks.Roadworks;
import net.uk.onetransport.android.county.oxon.roadworks.RoadworksRetriever;
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
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestRoadworks;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficFlow;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficQueue;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficScoot;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficSpeed;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestTrafficTravelTime;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonLatestVariableMessageSign;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonRoadworks;
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
    public static Uri ROADWORKS_URI;
    public static Uri LATEST_ROADWORKS_URI;
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
    private static final String EXTRAS_ROADWORKS =
            "net.uk.onetransport.android.county.oxon.sync.ROADWORKS";
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
    private static int ROADWORKS;
    private static int LATEST_ROADWORKS;
    private static int ROADWORKS_ID;
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
        sqLiteDatabase.execSQL(OxonContract.CREATE_ROADWORKS_TABLE);
        sqLiteDatabase.execSQL(OxonContract.CREATE_LATEST_ROADWORKS_TABLE);
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
        ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, OxonRoadworks.TABLE_NAME);
        LATEST_ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI,
                OxonLatestRoadworks.TABLE_NAME);
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
        ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, OxonRoadworks.TABLE_NAME, ROADWORKS);
        providerModules.add(this);
        LATEST_ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, OxonLatestRoadworks.TABLE_NAME, LATEST_ROADWORKS);
        providerModules.add(this);
        ROADWORKS_ID = providerModules.size();
        uriMatcher.addURI(authority, OxonRoadworks.TABLE_NAME + "/#", ROADWORKS_ID);
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
        if (match == ROADWORKS) {
            return mimeDirPrefix + OxonRoadworks.TABLE_NAME;
        }
        if (match == LATEST_ROADWORKS) {
            return mimeDirPrefix + OxonLatestRoadworks.TABLE_NAME;
        }
        if (match == ROADWORKS_ID) {
            return mimeItemPrefix + OxonRoadworks.TABLE_NAME;
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
    public int bulkInsert(int match, ContentValues[] contentValues, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int numInserted = 0;
        if (match == CAR_PARKS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + OxonCarPark.TABLE_NAME + "("
                                + OxonCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                                + OxonCarPark.COLUMN_LATITUDE + ","
                                + OxonCarPark.COLUMN_LONGITUDE + ","
                                + OxonCarPark.COLUMN_OCCUPANCY + ","
                                + OxonCarPark.COLUMN_OCCUPANCY_TREND + ","
                                + OxonCarPark.COLUMN_TOTAL_PARKING_CAPACITY + ","
                                + OxonCarPark.COLUMN_FILL_RATE + ","
                                + OxonCarPark.COLUMN_EXIT_RATE + ","
                                + OxonCarPark.COLUMN_ALMOST_FULL_INCREASING + ","
                                + OxonCarPark.COLUMN_ALMOST_FULL_DECREASING + ","
                                + OxonCarPark.COLUMN_FULL_DECREASING + ","
                                + OxonCarPark.COLUMN_FULL_INCREASING + ","
                                + OxonCarPark.COLUMN_STATUS + ","
                                + OxonCarPark.COLUMN_STATUS_TIME + ","
                                + OxonCarPark.COLUMN_QUEUING_TIME + ","
                                + OxonCarPark.COLUMN_PARKING_AREA_NAME + ","
                                + OxonCarPark.COLUMN_ENTRANCE_FULL + ","
                                + OxonCarPark.COLUMN_CIN_ID + ","
                                + OxonCarPark.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String identity = value.getAsString(OxonCarPark.COLUMN_CAR_PARK_IDENTITY);
                    Double latitude = value.getAsDouble(OxonCarPark.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(OxonCarPark.COLUMN_LONGITUDE);
                    Double occupancy = value.getAsDouble(OxonCarPark.COLUMN_OCCUPANCY);
                    String occupancyTrend = value.getAsString(OxonCarPark.COLUMN_OCCUPANCY_TREND);
                    Double totalParkingCapacity = value.getAsDouble(OxonCarPark.COLUMN_TOTAL_PARKING_CAPACITY);
                    Double fillRate = value.getAsDouble(OxonCarPark.COLUMN_FILL_RATE);
                    Double exitRate = value.getAsDouble(OxonCarPark.COLUMN_EXIT_RATE);
                    Double almostFullIncreasing = value.getAsDouble(OxonCarPark.COLUMN_ALMOST_FULL_INCREASING);
                    Double almostFullDecreasing = value.getAsDouble(OxonCarPark.COLUMN_ALMOST_FULL_DECREASING);
                    Double fullDecreasing = value.getAsDouble(OxonCarPark.COLUMN_FULL_DECREASING);
                    Double fullIncreasing = value.getAsDouble(OxonCarPark.COLUMN_FULL_INCREASING);
                    String status = value.getAsString(OxonCarPark.COLUMN_STATUS);
                    String statusTime = value.getAsString(OxonCarPark.COLUMN_STATUS_TIME);
                    Double queuingTime = value.getAsDouble(OxonCarPark.COLUMN_QUEUING_TIME);
                    String parkingAreaName = value.getAsString(OxonCarPark.COLUMN_PARKING_AREA_NAME);
                    Double entranceFull = value.getAsDouble(OxonCarPark.COLUMN_ENTRANCE_FULL);
                    String cinId = value.getAsString(OxonCarPark.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonCarPark.COLUMN_CREATION_TIME);
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
                        "INSERT INTO " + OxonEvent.TABLE_NAME + "("
                                + OxonEvent.COLUMN_ID + ","
                                + OxonEvent.COLUMN_START_OF_PERIOD + ","
                                + OxonEvent.COLUMN_END_OF_PERIOD + ","
                                + OxonEvent.COLUMN_OVERALL_START_TIME + ","
                                + OxonEvent.COLUMN_OVERALL_END_TIME + ","
                                + OxonEvent.COLUMN_LATITUDE + ","
                                + OxonEvent.COLUMN_LONGITUDE + ","
                                + OxonEvent.COLUMN_DESCRIPTION + ","
                                + OxonEvent.COLUMN_IMPACT_ON_TRAFFIC + ","
                                + OxonEvent.COLUMN_VALIDITY_STATUS + ","
                                + OxonEvent.COLUMN_CIN_ID + ","
                                + OxonEvent.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(OxonEvent.COLUMN_ID);
                    String startOfPeriod = value.getAsString(OxonEvent.COLUMN_START_OF_PERIOD);
                    String endOfPeriod = value.getAsString(OxonEvent.COLUMN_END_OF_PERIOD);
                    String overallStartTime = value.getAsString(OxonEvent.COLUMN_OVERALL_START_TIME);
                    String overallEndTime = value.getAsString(OxonEvent.COLUMN_OVERALL_END_TIME);
                    Double latitude = value.getAsDouble(OxonEvent.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(OxonEvent.COLUMN_LONGITUDE);
                    String description = value.getAsString(OxonEvent.COLUMN_DESCRIPTION);
                    String impactOnTraffic = value.getAsString(OxonEvent.COLUMN_IMPACT_ON_TRAFFIC);
                    String validityStatus = value.getAsString(OxonEvent.COLUMN_VALIDITY_STATUS);
                    String cinId = value.getAsString(OxonEvent.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonEvent.COLUMN_CREATION_TIME);
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
                        "INSERT INTO " + OxonRoadworks.TABLE_NAME + "("
                                + OxonRoadworks.COLUMN_ID + ","
                                + OxonRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT + ","
                                + OxonRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE + ","
                                + OxonRoadworks.COLUMN_COMMENT + ","
                                + OxonRoadworks.COLUMN_IMPACT_ON_TRAFFIC + ","
                                + OxonRoadworks.COLUMN_LATITUDE + ","
                                + OxonRoadworks.COLUMN_LONGITUDE + ","
                                + OxonRoadworks.COLUMN_VALIDITY_STATUS + ","
                                + OxonRoadworks.COLUMN_OVERALL_START_TIME + ","
                                + OxonRoadworks.COLUMN_OVERALL_END_TIME + ","
                                + OxonRoadworks.COLUMN_START_OF_PERIOD + ","
                                + OxonRoadworks.COLUMN_END_OF_PERIOD + ","
                                + OxonRoadworks.COLUMN_CIN_ID + ","
                                + OxonRoadworks.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(OxonRoadworks.COLUMN_ID);
                    String effectOnRoadLayout = value.getAsString(OxonRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT);
                    String roadMaintenanceType = value.getAsString(OxonRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE);
                    String comment = value.getAsString(OxonRoadworks.COLUMN_COMMENT);
                    String impactOnTraffic = value.getAsString(OxonRoadworks.COLUMN_IMPACT_ON_TRAFFIC);
                    Double latitude = value.getAsDouble(OxonRoadworks.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(OxonRoadworks.COLUMN_LONGITUDE);
                    String validityStatus = value.getAsString(OxonRoadworks.COLUMN_VALIDITY_STATUS);
                    String overallStartTime = value.getAsString(OxonRoadworks.COLUMN_OVERALL_START_TIME);
                    String overallEndTime = value.getAsString(OxonRoadworks.COLUMN_OVERALL_END_TIME);
                    String startOfPeriod = value.getAsString(OxonRoadworks.COLUMN_START_OF_PERIOD);
                    String endOfPeriod = value.getAsString(OxonRoadworks.COLUMN_END_OF_PERIOD);
                    String cinId = value.getAsString(OxonRoadworks.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonRoadworks.COLUMN_CREATION_TIME);
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
                        "INSERT INTO " + OxonTrafficFlow.TABLE_NAME + "("
                                + OxonTrafficFlow.COLUMN_ID + ","
                                + OxonTrafficFlow.COLUMN_TPEG_DIRECTION + ","
                                + OxonTrafficFlow.COLUMN_FROM_TYPE + ","
                                + OxonTrafficFlow.COLUMN_FROM_DESCRIPTOR + ","
                                + OxonTrafficFlow.COLUMN_FROM_LATITUDE + ","
                                + OxonTrafficFlow.COLUMN_FROM_LONGITUDE + ","
                                + OxonTrafficFlow.COLUMN_TO_TYPE + ","
                                + OxonTrafficFlow.COLUMN_TO_DESCRIPTOR + ","
                                + OxonTrafficFlow.COLUMN_TO_LATITUDE + ","
                                + OxonTrafficFlow.COLUMN_TO_LONGITUDE + ","
                                + OxonTrafficFlow.COLUMN_TIME + ","
                                + OxonTrafficFlow.COLUMN_VEHICLE_FLOW + ","
                                + OxonTrafficFlow.COLUMN_CIN_ID + ","
                                + OxonTrafficFlow.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(OxonTrafficFlow.COLUMN_ID);
                    String tpegDirection = value.getAsString(OxonTrafficFlow.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(OxonTrafficFlow.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(OxonTrafficFlow.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(OxonTrafficFlow.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(OxonTrafficFlow.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(OxonTrafficFlow.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(OxonTrafficFlow.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(OxonTrafficFlow.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(OxonTrafficFlow.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(OxonTrafficFlow.COLUMN_TIME);
                    Double vehicleFlow = value.getAsDouble(OxonTrafficFlow.COLUMN_VEHICLE_FLOW);
                    String cinId = value.getAsString(OxonTrafficFlow.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonTrafficFlow.COLUMN_CREATION_TIME);
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
        if (match == TRAFFIC_QUEUES) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + OxonTrafficQueue.TABLE_NAME + "("
                                + OxonTrafficQueue.COLUMN_ID + ","
                                + OxonTrafficQueue.COLUMN_TPEG_DIRECTION + ","
                                + OxonTrafficQueue.COLUMN_FROM_TYPE + ","
                                + OxonTrafficQueue.COLUMN_FROM_DESCRIPTOR + ","
                                + OxonTrafficQueue.COLUMN_FROM_LATITUDE + ","
                                + OxonTrafficQueue.COLUMN_FROM_LONGITUDE + ","
                                + OxonTrafficQueue.COLUMN_TO_TYPE + ","
                                + OxonTrafficQueue.COLUMN_TO_DESCRIPTOR + ","
                                + OxonTrafficQueue.COLUMN_TO_LATITUDE + ","
                                + OxonTrafficQueue.COLUMN_TO_LONGITUDE + ","
                                + OxonTrafficQueue.COLUMN_TIME + ","
                                + OxonTrafficQueue.COLUMN_SEVERITY + ","
                                + OxonTrafficQueue.COLUMN_PRESENT + ","
                                + OxonTrafficQueue.COLUMN_CIN_ID + ","
                                + OxonTrafficQueue.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(OxonTrafficQueue.COLUMN_ID);
                    String tpegDirection = value.getAsString(OxonTrafficQueue.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(OxonTrafficQueue.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(OxonTrafficQueue.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(OxonTrafficQueue.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(OxonTrafficQueue.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(OxonTrafficQueue.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(OxonTrafficQueue.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(OxonTrafficQueue.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(OxonTrafficQueue.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(OxonTrafficQueue.COLUMN_TIME);
                    Double severity = value.getAsDouble(OxonTrafficQueue.COLUMN_SEVERITY);
                    String present = value.getAsString(OxonTrafficQueue.COLUMN_PRESENT);
                    String cinId = value.getAsString(OxonTrafficQueue.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonTrafficQueue.COLUMN_CREATION_TIME);
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
                    if (severity != null) {
                        insert.bindDouble(12, severity);
                    }
                    if (present != null) {
                        insert.bindString(13, present);
                    }
                    if (cinId != null) {
                        insert.bindString(14, cinId);
                    }
                    if (creationTime != null) {
                        insert.bindLong(15, creationTime);
                    }
                    insert.executeInsert();
                    insert.clearBindings();
                }
                sqLiteDatabase.setTransactionSuccessful();
                numInserted = contentValues.length;
                contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
            } finally {
                sqLiteDatabase.endTransaction();
            }
            return numInserted;
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed));
        }
        if (match == TRAFFIC_SCOOTS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + OxonTrafficScoot.TABLE_NAME + "("
                                + OxonTrafficScoot.COLUMN_ID + ","
                                + OxonTrafficScoot.COLUMN_TPEG_DIRECTION + ","
                                + OxonTrafficScoot.COLUMN_FROM_TYPE + ","
                                + OxonTrafficScoot.COLUMN_FROM_DESCRIPTOR + ","
                                + OxonTrafficScoot.COLUMN_FROM_LATITUDE + ","
                                + OxonTrafficScoot.COLUMN_FROM_LONGITUDE + ","
                                + OxonTrafficScoot.COLUMN_TO_TYPE + ","
                                + OxonTrafficScoot.COLUMN_TO_DESCRIPTOR + ","
                                + OxonTrafficScoot.COLUMN_TO_LATITUDE + ","
                                + OxonTrafficScoot.COLUMN_TO_LONGITUDE + ","
                                + OxonTrafficScoot.COLUMN_TIME + ","
                                + OxonTrafficScoot.COLUMN_CURRENT_FLOW + ","
                                + OxonTrafficScoot.COLUMN_AVERAGE_SPEED + ","
                                + OxonTrafficScoot.COLUMN_LINK_STATUS_TYPE + ","
                                + OxonTrafficScoot.COLUMN_LINK_STATUS + ","
                                + OxonTrafficScoot.COLUMN_LINK_TRAVEL_TIME + ","
                                + OxonTrafficScoot.COLUMN_CONGESTION_PERCENT + ","
                                + OxonTrafficScoot.COLUMN_CIN_ID + ","
                                + OxonTrafficScoot.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(OxonTrafficScoot.COLUMN_ID);
                    String tpegDirection = value.getAsString(OxonTrafficScoot.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(OxonTrafficScoot.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(OxonTrafficScoot.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(OxonTrafficScoot.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(OxonTrafficScoot.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(OxonTrafficScoot.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(OxonTrafficScoot.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(OxonTrafficScoot.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(OxonTrafficScoot.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(OxonTrafficScoot.COLUMN_TIME);
                    Double currentFlow = value.getAsDouble(OxonTrafficScoot.COLUMN_CURRENT_FLOW);
                    Double averageSpeed = value.getAsDouble(OxonTrafficScoot.COLUMN_AVERAGE_SPEED);
                    Double linkStatusType = value.getAsDouble(OxonTrafficScoot.COLUMN_LINK_STATUS_TYPE);
                    Double linkStatus = value.getAsDouble(OxonTrafficScoot.COLUMN_LINK_STATUS);
                    Double linkTravelTime = value.getAsDouble(OxonTrafficScoot.COLUMN_LINK_TRAVEL_TIME);
                    Double congestionPercent = value.getAsDouble(OxonTrafficScoot.COLUMN_CONGESTION_PERCENT);
                    String cinId = value.getAsString(OxonTrafficScoot.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonTrafficScoot.COLUMN_CREATION_TIME);
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
                        "INSERT INTO " + OxonTrafficSpeed.TABLE_NAME + "("
                                + OxonTrafficSpeed.COLUMN_ID + ","
                                + OxonTrafficSpeed.COLUMN_TPEG_DIRECTION + ","
                                + OxonTrafficSpeed.COLUMN_FROM_TYPE + ","
                                + OxonTrafficSpeed.COLUMN_FROM_DESCRIPTOR + ","
                                + OxonTrafficSpeed.COLUMN_FROM_LATITUDE + ","
                                + OxonTrafficSpeed.COLUMN_FROM_LONGITUDE + ","
                                + OxonTrafficSpeed.COLUMN_TO_TYPE + ","
                                + OxonTrafficSpeed.COLUMN_TO_DESCRIPTOR + ","
                                + OxonTrafficSpeed.COLUMN_TO_LATITUDE + ","
                                + OxonTrafficSpeed.COLUMN_TO_LONGITUDE + ","
                                + OxonTrafficSpeed.COLUMN_TIME + ","
                                + OxonTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED + ","
                                + OxonTrafficSpeed.COLUMN_CIN_ID + ","
                                + OxonTrafficSpeed.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(OxonTrafficSpeed.COLUMN_ID);
                    String tpegDirection = value.getAsString(OxonTrafficSpeed.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(OxonTrafficSpeed.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(OxonTrafficSpeed.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(OxonTrafficSpeed.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(OxonTrafficSpeed.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(OxonTrafficSpeed.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(OxonTrafficSpeed.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(OxonTrafficSpeed.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(OxonTrafficSpeed.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(OxonTrafficSpeed.COLUMN_TIME);
                    Double averageVehicleSpeed = value.getAsDouble(OxonTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED);
                    String cinId = value.getAsString(OxonTrafficSpeed.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonTrafficSpeed.COLUMN_CREATION_TIME);
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
                        "INSERT INTO " + OxonTrafficTravelTime.TABLE_NAME + "("
                                + OxonTrafficTravelTime.COLUMN_ID + ","
                                + OxonTrafficTravelTime.COLUMN_TPEG_DIRECTION + ","
                                + OxonTrafficTravelTime.COLUMN_FROM_TYPE + ","
                                + OxonTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + ","
                                + OxonTrafficTravelTime.COLUMN_FROM_LATITUDE + ","
                                + OxonTrafficTravelTime.COLUMN_FROM_LONGITUDE + ","
                                + OxonTrafficTravelTime.COLUMN_TO_TYPE + ","
                                + OxonTrafficTravelTime.COLUMN_TO_DESCRIPTOR + ","
                                + OxonTrafficTravelTime.COLUMN_TO_LATITUDE + ","
                                + OxonTrafficTravelTime.COLUMN_TO_LONGITUDE + ","
                                + OxonTrafficTravelTime.COLUMN_TIME + ","
                                + OxonTrafficTravelTime.COLUMN_TRAVEL_TIME + ","
                                + OxonTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + ","
                                + OxonTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + ","
                                + OxonTrafficTravelTime.COLUMN_CIN_ID + ","
                                + OxonTrafficTravelTime.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(OxonTrafficTravelTime.COLUMN_ID);
                    String tpegDirection = value.getAsString(OxonTrafficTravelTime.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(OxonTrafficTravelTime.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(OxonTrafficTravelTime.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(OxonTrafficTravelTime.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(OxonTrafficTravelTime.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(OxonTrafficTravelTime.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(OxonTrafficTravelTime.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(OxonTrafficTravelTime.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(OxonTrafficTravelTime.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(OxonTrafficTravelTime.COLUMN_TIME);
                    Double travelTime = value.getAsDouble(OxonTrafficTravelTime.COLUMN_TRAVEL_TIME);
                    Double freeFlowTravelTime = value.getAsDouble(OxonTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME);
                    Double freeFlowSpeed = value.getAsDouble(OxonTrafficTravelTime.COLUMN_FREE_FLOW_SPEED);
                    String cinId = value.getAsString(OxonTrafficTravelTime.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonTrafficTravelTime.COLUMN_CREATION_TIME);
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
                        "INSERT INTO " + OxonVariableMessageSign.TABLE_NAME + "("
                                + OxonVariableMessageSign.COLUMN_LOCATION_ID + ","
                                + OxonVariableMessageSign.COLUMN_DESCRIPTION + ","
                                + OxonVariableMessageSign.COLUMN_VMS_TYPE + ","
                                + OxonVariableMessageSign.COLUMN_LATITUDE + ","
                                + OxonVariableMessageSign.COLUMN_LONGITUDE + ","
                                + OxonVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + ","
                                + OxonVariableMessageSign.COLUMN_NUMBER_OF_ROWS + ","
                                + OxonVariableMessageSign.COLUMN_VMS_LEGENDS + ","
                                + OxonVariableMessageSign.COLUMN_CIN_ID + ","
                                + OxonVariableMessageSign.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String locationId = value.getAsString(OxonVariableMessageSign.COLUMN_LOCATION_ID);
                    String description = value.getAsString(OxonVariableMessageSign.COLUMN_DESCRIPTION);
                    String vmsType = value.getAsString(OxonVariableMessageSign.COLUMN_VMS_TYPE);
                    Double latitude = value.getAsDouble(OxonVariableMessageSign.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(OxonVariableMessageSign.COLUMN_LONGITUDE);
                    Long numberOfCharacters = value.getAsLong(OxonVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS);
                    Long numberOfRows = value.getAsLong(OxonVariableMessageSign.COLUMN_NUMBER_OF_ROWS);
                    String vmsLegends = value.getAsString(OxonVariableMessageSign.COLUMN_VMS_LEGENDS);
                    String cinId = value.getAsString(OxonVariableMessageSign.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(OxonVariableMessageSign.COLUMN_CREATION_TIME);
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
        if (match == ROADWORKS) {
            id = sqLiteDatabase.insert(OxonRoadworks.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return ContentUris.withAppendedId(ROADWORKS_URI, id);
        }
        if (match == LATEST_ROADWORKS) {
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
        if (match == ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(OxonRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
            return cursor;
        }
        if (match == LATEST_ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(OxonLatestRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_ROADWORKS_URI);
            return cursor;
        }
        if (match == ROADWORKS_ID) {
            Cursor cursor = sqLiteDatabase.query(OxonRoadworks.TABLE_NAME, projection,
                    OxonRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
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
        if (match == ROADWORKS) {
            int rows = sqLiteDatabase.update(OxonRoadworks.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return rows;
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed));
        }
        if (match == ROADWORKS_ID) {
            int rows = sqLiteDatabase.update(OxonRoadworks.TABLE_NAME, values,
                    OxonRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(ROADWORKS_URI, null);
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
        if (match == ROADWORKS) {
            rows = sqLiteDatabase.delete(OxonRoadworks.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
        }
        if (match == LATEST_ROADWORKS) {
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
            if (extras.getBoolean(EXTRAS_ROADWORKS, false)) {
                try {
                    Roadworks[] roadworkses = new RoadworksRetriever(context).retrieve();
                    OxonContentHelper.insertIntoProvider(context, roadworkses);
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

    public static void refresh(Context context, boolean carParks, boolean events, boolean roadworks,
                               boolean trafficFlow, boolean trafficQueue, boolean trafficScoot, boolean trafficSpeed,
                               boolean trafficTravelTime, boolean variableMessageSigns) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_CAR_PARKS, carParks);
        settingsBundle.putBoolean(EXTRAS_EVENTS, events);
        settingsBundle.putBoolean(EXTRAS_ROADWORKS, roadworks);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_FLOW, trafficFlow);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_QUEUE, trafficQueue);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_SCOOT, trafficScoot);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_SPEED, trafficSpeed);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, trafficTravelTime);
        settingsBundle.putBoolean(EXTRAS_VMS, variableMessageSigns);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }
}
