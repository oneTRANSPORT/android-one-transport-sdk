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
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.bucks.events.Event;
import net.uk.onetransport.android.county.bucks.events.EventRetriever;
import net.uk.onetransport.android.county.bucks.roadworks.Roadworks;
import net.uk.onetransport.android.county.bucks.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.bucks.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.bucks.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksCarPark;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksEvent;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestCarPark;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestEvent;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestRoadworks;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestTrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestTrafficQueue;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestTrafficScoot;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestTrafficSpeed;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestTrafficTravelTime;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksLatestVariableMessageSign;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksRoadworks;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficQueue;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficScoot;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficSpeed;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficTravelTime;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksVariableMessageSign;

public class BucksProviderModule implements ProviderModule {

    private static final String TAG = "BucksProviderModule";

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
            "net.uk.onetransport.android.county.bucks.sync.CAR_PARKS";
    private static final String EXTRAS_EVENTS =
            "net.uk.onetransport.android.county.bucks.sync.EVENTS";
    private static final String EXTRAS_ROADWORKS =
            "net.uk.onetransport.android.county.bucks.sync.ROADWORKS";
    private static final String EXTRAS_TRAFFIC_FLOW =
            "net.uk.onetransport.android.county.bucks.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_TRAFFIC_QUEUE =
            "net.uk.onetransport.android.county.bucks.sync.TRAFFIC_QUEUE";
    private static final String EXTRAS_TRAFFIC_SCOOT =
            "net.uk.onetransport.android.county.bucks.sync.TRAFFIC_SCOOT";
    private static final String EXTRAS_TRAFFIC_SPEED =
            "net.uk.onetransport.android.county.bucks.sync.TRAFFIC_SPEED";
    private static final String EXTRAS_TRAFFIC_TRAVEL_TIME =
            "net.uk.onetransport.android.county.bucks.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_VMS = "net.uk.onetransport.android.county.bucks.sync.VMS";
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

    public BucksProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BucksContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_EVENT_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_EVENT_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_ROADWORKS_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_ROADWORKS_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_QUEUE_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_TRAFFIC_QUEUE_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_SCOOT_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_TRAFFIC_SCOOT_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_SPEED_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_TRAFFIC_SPEED_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI, BucksCarPark.TABLE_NAME);
        LATEST_CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestCarPark.TABLE_NAME);
        EVENT_URI = Uri.withAppendedPath(AUTHORITY_URI, BucksEvent.TABLE_NAME);
        LATEST_EVENT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestEvent.TABLE_NAME);
        ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, BucksRoadworks.TABLE_NAME);
        LATEST_ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestRoadworks.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI, BucksTrafficFlow.TABLE_NAME);
        LATEST_TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestTrafficFlow.TABLE_NAME);
        TRAFFIC_QUEUE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksTrafficQueue.TABLE_NAME);
        LATEST_TRAFFIC_QUEUE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestTrafficQueue.TABLE_NAME);
        TRAFFIC_SCOOT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksTrafficScoot.TABLE_NAME);
        LATEST_TRAFFIC_SCOOT_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestTrafficScoot.TABLE_NAME);
        TRAFFIC_SPEED_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksTrafficSpeed.TABLE_NAME);
        LATEST_TRAFFIC_SPEED_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestTrafficSpeed.TABLE_NAME);
        TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksTrafficTravelTime.TABLE_NAME);
        LATEST_TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestTrafficTravelTime.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksVariableMessageSign.TABLE_NAME);
        LATEST_VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BucksLatestVariableMessageSign.TABLE_NAME);

        CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, BucksCarPark.TABLE_NAME, CAR_PARKS);
        providerModules.add(this);
        LATEST_CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestCarPark.TABLE_NAME, LATEST_CAR_PARKS);
        providerModules.add(this);
        CAR_PARK_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksCarPark.TABLE_NAME + "/#", CAR_PARK_ID);
        providerModules.add(this);
        EVENTS = providerModules.size();
        uriMatcher.addURI(authority, BucksEvent.TABLE_NAME, EVENTS);
        providerModules.add(this);
        LATEST_EVENTS = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestEvent.TABLE_NAME, LATEST_EVENTS);
        providerModules.add(this);
        EVENT_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksEvent.TABLE_NAME + "/#", EVENT_ID);
        providerModules.add(this);
        ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, BucksRoadworks.TABLE_NAME, ROADWORKS);
        providerModules.add(this);
        LATEST_ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestRoadworks.TABLE_NAME, LATEST_ROADWORKS);
        providerModules.add(this);
        ROADWORKS_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksRoadworks.TABLE_NAME + "/#", ROADWORKS_ID);
        providerModules.add(this);
        TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
        providerModules.add(this);
        LATEST_TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestTrafficFlow.TABLE_NAME, LATEST_TRAFFIC_FLOWS);
        providerModules.add(this);
        TRAFFIC_FLOW_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
        providerModules.add(this);
        TRAFFIC_QUEUES = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficQueue.TABLE_NAME, TRAFFIC_QUEUES);
        providerModules.add(this);
        LATEST_TRAFFIC_QUEUES = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestTrafficQueue.TABLE_NAME, LATEST_TRAFFIC_QUEUES);
        providerModules.add(this);
        TRAFFIC_QUEUE_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficQueue.TABLE_NAME + "/#", TRAFFIC_QUEUE_ID);
        providerModules.add(this);
        TRAFFIC_SCOOTS = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficScoot.TABLE_NAME, TRAFFIC_SCOOTS);
        providerModules.add(this);
        LATEST_TRAFFIC_SCOOTS = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestTrafficScoot.TABLE_NAME, LATEST_TRAFFIC_SCOOTS);
        providerModules.add(this);
        TRAFFIC_SCOOT_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficScoot.TABLE_NAME + "/#", TRAFFIC_SCOOT_ID);
        providerModules.add(this);
        TRAFFIC_SPEEDS = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficSpeed.TABLE_NAME, TRAFFIC_SPEEDS);
        providerModules.add(this);
        LATEST_TRAFFIC_SPEEDS = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestTrafficSpeed.TABLE_NAME, LATEST_TRAFFIC_SPEEDS);
        providerModules.add(this);
        TRAFFIC_SPEED_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficSpeed.TABLE_NAME + "/#", TRAFFIC_SPEED_ID);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficTravelTime.TABLE_NAME, TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        LATEST_TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestTrafficTravelTime.TABLE_NAME,
                LATEST_TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksTrafficTravelTime.TABLE_NAME + "/#",
                TRAFFIC_TRAVEL_TIME_ID);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, BucksVariableMessageSign.TABLE_NAME,
                VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        LATEST_VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, BucksLatestVariableMessageSign.TABLE_NAME,
                LATEST_VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGN_ID = providerModules.size();
        uriMatcher.addURI(authority, BucksVariableMessageSign.TABLE_NAME + "/#",
                VARIABLE_MESSAGE_SIGN_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == CAR_PARKS) {
            return mimeDirPrefix + BucksCarPark.TABLE_NAME;
        }
        if (match == LATEST_CAR_PARKS) {
            return mimeDirPrefix + BucksLatestCarPark.TABLE_NAME;
        }
        if (match == CAR_PARK_ID) {
            return mimeItemPrefix + BucksCarPark.TABLE_NAME;
        }
        if (match == EVENTS) {
            return mimeDirPrefix + BucksEvent.TABLE_NAME;
        }
        if (match == LATEST_EVENTS) {
            return mimeDirPrefix + BucksLatestEvent.TABLE_NAME;
        }
        if (match == EVENT_ID) {
            return mimeItemPrefix + BucksEvent.TABLE_NAME;
        }
        if (match == ROADWORKS) {
            return mimeDirPrefix + BucksRoadworks.TABLE_NAME;
        }
        if (match == LATEST_ROADWORKS) {
            return mimeDirPrefix + BucksLatestRoadworks.TABLE_NAME;
        }
        if (match == ROADWORKS_ID) {
            return mimeItemPrefix + BucksRoadworks.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOWS) {
            return mimeDirPrefix + BucksTrafficFlow.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            return mimeDirPrefix + BucksLatestTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOW_ID) {
            return mimeItemPrefix + BucksTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_QUEUES) {
            return mimeDirPrefix + BucksTrafficQueue.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            return mimeDirPrefix + BucksLatestTrafficQueue.TABLE_NAME;
        }
        if (match == TRAFFIC_QUEUE_ID) {
            return mimeItemPrefix + BucksTrafficQueue.TABLE_NAME;
        }
        if (match == TRAFFIC_SCOOTS) {
            return mimeDirPrefix + BucksTrafficScoot.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            return mimeDirPrefix + BucksLatestTrafficScoot.TABLE_NAME;
        }
        if (match == TRAFFIC_SCOOT_ID) {
            return mimeItemPrefix + BucksTrafficScoot.TABLE_NAME;
        }
        if (match == TRAFFIC_SPEEDS) {
            return mimeDirPrefix + BucksTrafficSpeed.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            return mimeDirPrefix + BucksLatestTrafficSpeed.TABLE_NAME;
        }
        if (match == TRAFFIC_SPEED_ID) {
            return mimeItemPrefix + BucksTrafficSpeed.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + BucksTrafficTravelTime.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + BucksLatestTrafficTravelTime.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            return mimeItemPrefix + BucksTrafficTravelTime.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + BucksVariableMessageSign.TABLE_NAME;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + BucksLatestVariableMessageSign.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            return mimeItemPrefix + BucksVariableMessageSign.TABLE_NAME;
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
                        "INSERT INTO " + BucksCarPark.TABLE_NAME + "("
                                + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                                + BucksCarPark.COLUMN_LATITUDE + ","
                                + BucksCarPark.COLUMN_LONGITUDE + ","
                                + BucksCarPark.COLUMN_OCCUPANCY + ","
                                + BucksCarPark.COLUMN_OCCUPANCY_TREND + ","
                                + BucksCarPark.COLUMN_TOTAL_PARKING_CAPACITY + ","
                                + BucksCarPark.COLUMN_FILL_RATE + ","
                                + BucksCarPark.COLUMN_EXIT_RATE + ","
                                + BucksCarPark.COLUMN_ALMOST_FULL_INCREASING + ","
                                + BucksCarPark.COLUMN_ALMOST_FULL_DECREASING + ","
                                + BucksCarPark.COLUMN_FULL_DECREASING + ","
                                + BucksCarPark.COLUMN_FULL_INCREASING + ","
                                + BucksCarPark.COLUMN_STATUS + ","
                                + BucksCarPark.COLUMN_STATUS_TIME + ","
                                + BucksCarPark.COLUMN_QUEUING_TIME + ","
                                + BucksCarPark.COLUMN_PARKING_AREA_NAME + ","
                                + BucksCarPark.COLUMN_ENTRANCE_FULL + ","
                                + BucksCarPark.COLUMN_CIN_ID + ","
                                + BucksCarPark.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String identity = value.getAsString(BucksCarPark.COLUMN_CAR_PARK_IDENTITY);
                    Double latitude = value.getAsDouble(BucksCarPark.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(BucksCarPark.COLUMN_LONGITUDE);
                    Double occupancy = value.getAsDouble(BucksCarPark.COLUMN_OCCUPANCY);
                    String occupancyTrend = value.getAsString(BucksCarPark.COLUMN_OCCUPANCY_TREND);
                    Double totalParkingCapacity = value.getAsDouble(BucksCarPark.COLUMN_TOTAL_PARKING_CAPACITY);
                    Double fillRate = value.getAsDouble(BucksCarPark.COLUMN_FILL_RATE);
                    Double exitRate = value.getAsDouble(BucksCarPark.COLUMN_EXIT_RATE);
                    Double almostFullIncreasing = value.getAsDouble(BucksCarPark.COLUMN_ALMOST_FULL_INCREASING);
                    Double almostFullDecreasing = value.getAsDouble(BucksCarPark.COLUMN_ALMOST_FULL_DECREASING);
                    Double fullDecreasing = value.getAsDouble(BucksCarPark.COLUMN_FULL_DECREASING);
                    Double fullIncreasing = value.getAsDouble(BucksCarPark.COLUMN_FULL_INCREASING);
                    String status = value.getAsString(BucksCarPark.COLUMN_STATUS);
                    String statusTime = value.getAsString(BucksCarPark.COLUMN_STATUS_TIME);
                    Double queuingTime = value.getAsDouble(BucksCarPark.COLUMN_QUEUING_TIME);
                    String parkingAreaName = value.getAsString(BucksCarPark.COLUMN_PARKING_AREA_NAME);
                    Double entranceFull = value.getAsDouble(BucksCarPark.COLUMN_ENTRANCE_FULL);
                    String cinId = value.getAsString(BucksCarPark.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksCarPark.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestCarPark.TABLE_NAME);
        }
        if (match == EVENTS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksEvent.TABLE_NAME + "("
                                + BucksEvent.COLUMN_ID + ","
                                + BucksEvent.COLUMN_START_OF_PERIOD + ","
                                + BucksEvent.COLUMN_END_OF_PERIOD + ","
                                + BucksEvent.COLUMN_OVERALL_START_TIME + ","
                                + BucksEvent.COLUMN_OVERALL_END_TIME + ","
                                + BucksEvent.COLUMN_LATITUDE + ","
                                + BucksEvent.COLUMN_LONGITUDE + ","
                                + BucksEvent.COLUMN_DESCRIPTION + ","
                                + BucksEvent.COLUMN_IMPACT_ON_TRAFFIC + ","
                                + BucksEvent.COLUMN_VALIDITY_STATUS + ","
                                + BucksEvent.COLUMN_CIN_ID + ","
                                + BucksEvent.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(BucksEvent.COLUMN_ID);
                    String startOfPeriod = value.getAsString(BucksEvent.COLUMN_START_OF_PERIOD);
                    String endOfPeriod = value.getAsString(BucksEvent.COLUMN_END_OF_PERIOD);
                    String overallStartTime = value.getAsString(BucksEvent.COLUMN_OVERALL_START_TIME);
                    String overallEndTime = value.getAsString(BucksEvent.COLUMN_OVERALL_END_TIME);
                    Double latitude = value.getAsDouble(BucksEvent.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(BucksEvent.COLUMN_LONGITUDE);
                    String description = value.getAsString(BucksEvent.COLUMN_DESCRIPTION);
                    String impactOnTraffic = value.getAsString(BucksEvent.COLUMN_IMPACT_ON_TRAFFIC);
                    String validityStatus = value.getAsString(BucksEvent.COLUMN_VALIDITY_STATUS);
                    String cinId = value.getAsString(BucksEvent.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksEvent.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestEvent.TABLE_NAME);
        }
        if (match == ROADWORKS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksRoadworks.TABLE_NAME + "("
                                + BucksRoadworks.COLUMN_ID + ","
                                + BucksRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT + ","
                                + BucksRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE + ","
                                + BucksRoadworks.COLUMN_COMMENT + ","
                                + BucksRoadworks.COLUMN_IMPACT_ON_TRAFFIC + ","
                                + BucksRoadworks.COLUMN_LATITUDE + ","
                                + BucksRoadworks.COLUMN_LONGITUDE + ","
                                + BucksRoadworks.COLUMN_VALIDITY_STATUS + ","
                                + BucksRoadworks.COLUMN_OVERALL_START_TIME + ","
                                + BucksRoadworks.COLUMN_OVERALL_END_TIME + ","
                                + BucksRoadworks.COLUMN_START_OF_PERIOD + ","
                                + BucksRoadworks.COLUMN_END_OF_PERIOD + ","
                                + BucksRoadworks.COLUMN_CIN_ID + ","
                                + BucksRoadworks.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(BucksRoadworks.COLUMN_ID);
                    String effectOnRoadLayout = value.getAsString(BucksRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT);
                    String roadMaintenanceType = value.getAsString(BucksRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE);
                    String comment = value.getAsString(BucksRoadworks.COLUMN_COMMENT);
                    String impactOnTraffic = value.getAsString(BucksRoadworks.COLUMN_IMPACT_ON_TRAFFIC);
                    Double latitude = value.getAsDouble(BucksRoadworks.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(BucksRoadworks.COLUMN_LONGITUDE);
                    String validityStatus = value.getAsString(BucksRoadworks.COLUMN_VALIDITY_STATUS);
                    String overallStartTime = value.getAsString(BucksRoadworks.COLUMN_OVERALL_START_TIME);
                    String overallEndTime = value.getAsString(BucksRoadworks.COLUMN_OVERALL_END_TIME);
                    String startOfPeriod = value.getAsString(BucksRoadworks.COLUMN_START_OF_PERIOD);
                    String endOfPeriod = value.getAsString(BucksRoadworks.COLUMN_END_OF_PERIOD);
                    String cinId = value.getAsString(BucksRoadworks.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksRoadworks.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestRoadworks.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOWS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksTrafficFlow.TABLE_NAME + "("
                                + BucksTrafficFlow.COLUMN_ID + ","
                                + BucksTrafficFlow.COLUMN_TPEG_DIRECTION + ","
                                + BucksTrafficFlow.COLUMN_FROM_TYPE + ","
                                + BucksTrafficFlow.COLUMN_FROM_DESCRIPTOR + ","
                                + BucksTrafficFlow.COLUMN_FROM_LATITUDE + ","
                                + BucksTrafficFlow.COLUMN_FROM_LONGITUDE + ","
                                + BucksTrafficFlow.COLUMN_TO_TYPE + ","
                                + BucksTrafficFlow.COLUMN_TO_DESCRIPTOR + ","
                                + BucksTrafficFlow.COLUMN_TO_LATITUDE + ","
                                + BucksTrafficFlow.COLUMN_TO_LONGITUDE + ","
                                + BucksTrafficFlow.COLUMN_TIME + ","
                                + BucksTrafficFlow.COLUMN_VEHICLE_FLOW + ","
                                + BucksTrafficFlow.COLUMN_CIN_ID + ","
                                + BucksTrafficFlow.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(BucksTrafficFlow.COLUMN_ID);
                    String tpegDirection = value.getAsString(BucksTrafficFlow.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(BucksTrafficFlow.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(BucksTrafficFlow.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(BucksTrafficFlow.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(BucksTrafficFlow.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(BucksTrafficFlow.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(BucksTrafficFlow.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(BucksTrafficFlow.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(BucksTrafficFlow.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(BucksTrafficFlow.COLUMN_TIME);
                    Double vehicleFlow = value.getAsDouble(BucksTrafficFlow.COLUMN_VEHICLE_FLOW);
                    String cinId = value.getAsString(BucksTrafficFlow.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksTrafficFlow.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_QUEUES) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksTrafficQueue.TABLE_NAME + "("
                                + BucksTrafficQueue.COLUMN_ID + ","
                                + BucksTrafficQueue.COLUMN_TPEG_DIRECTION + ","
                                + BucksTrafficQueue.COLUMN_FROM_TYPE + ","
                                + BucksTrafficQueue.COLUMN_FROM_DESCRIPTOR + ","
                                + BucksTrafficQueue.COLUMN_FROM_LATITUDE + ","
                                + BucksTrafficQueue.COLUMN_FROM_LONGITUDE + ","
                                + BucksTrafficQueue.COLUMN_TO_TYPE + ","
                                + BucksTrafficQueue.COLUMN_TO_DESCRIPTOR + ","
                                + BucksTrafficQueue.COLUMN_TO_LATITUDE + ","
                                + BucksTrafficQueue.COLUMN_TO_LONGITUDE + ","
                                + BucksTrafficQueue.COLUMN_TIME + ","
                                + BucksTrafficQueue.COLUMN_SEVERITY + ","
                                + BucksTrafficQueue.COLUMN_PRESENT + ","
                                + BucksTrafficQueue.COLUMN_CIN_ID + ","
                                + BucksTrafficQueue.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(BucksTrafficQueue.COLUMN_ID);
                    String tpegDirection = value.getAsString(BucksTrafficQueue.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(BucksTrafficQueue.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(BucksTrafficQueue.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(BucksTrafficQueue.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(BucksTrafficQueue.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(BucksTrafficQueue.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(BucksTrafficQueue.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(BucksTrafficQueue.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(BucksTrafficQueue.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(BucksTrafficQueue.COLUMN_TIME);
                    Double severity = value.getAsDouble(BucksTrafficQueue.COLUMN_SEVERITY);
                    String present = value.getAsString(BucksTrafficQueue.COLUMN_PRESENT);
                    String cinId = value.getAsString(BucksTrafficQueue.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksTrafficQueue.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficQueue.TABLE_NAME);
        }
        if (match == TRAFFIC_SCOOTS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksTrafficScoot.TABLE_NAME + "("
                                + BucksTrafficScoot.COLUMN_ID + ","
                                + BucksTrafficScoot.COLUMN_TPEG_DIRECTION + ","
                                + BucksTrafficScoot.COLUMN_FROM_TYPE + ","
                                + BucksTrafficScoot.COLUMN_FROM_DESCRIPTOR + ","
                                + BucksTrafficScoot.COLUMN_FROM_LATITUDE + ","
                                + BucksTrafficScoot.COLUMN_FROM_LONGITUDE + ","
                                + BucksTrafficScoot.COLUMN_TO_TYPE + ","
                                + BucksTrafficScoot.COLUMN_TO_DESCRIPTOR + ","
                                + BucksTrafficScoot.COLUMN_TO_LATITUDE + ","
                                + BucksTrafficScoot.COLUMN_TO_LONGITUDE + ","
                                + BucksTrafficScoot.COLUMN_TIME + ","
                                + BucksTrafficScoot.COLUMN_CURRENT_FLOW + ","
                                + BucksTrafficScoot.COLUMN_AVERAGE_SPEED + ","
                                + BucksTrafficScoot.COLUMN_LINK_STATUS_TYPE + ","
                                + BucksTrafficScoot.COLUMN_LINK_STATUS + ","
                                + BucksTrafficScoot.COLUMN_LINK_TRAVEL_TIME + ","
                                + BucksTrafficScoot.COLUMN_CONGESTION_PERCENT + ","
                                + BucksTrafficScoot.COLUMN_CIN_ID + ","
                                + BucksTrafficScoot.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(BucksTrafficScoot.COLUMN_ID);
                    String tpegDirection = value.getAsString(BucksTrafficScoot.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(BucksTrafficScoot.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(BucksTrafficScoot.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(BucksTrafficScoot.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(BucksTrafficScoot.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(BucksTrafficScoot.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(BucksTrafficScoot.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(BucksTrafficScoot.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(BucksTrafficScoot.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(BucksTrafficScoot.COLUMN_TIME);
                    Double currentFlow = value.getAsDouble(BucksTrafficScoot.COLUMN_CURRENT_FLOW);
                    Double averageSpeed = value.getAsDouble(BucksTrafficScoot.COLUMN_AVERAGE_SPEED);
                    Double linkStatusType = value.getAsDouble(BucksTrafficScoot.COLUMN_LINK_STATUS_TYPE);
                    Double linkStatus = value.getAsDouble(BucksTrafficScoot.COLUMN_LINK_STATUS);
                    Double linkTravelTime = value.getAsDouble(BucksTrafficScoot.COLUMN_LINK_TRAVEL_TIME);
                    Double congestionPercent = value.getAsDouble(BucksTrafficScoot.COLUMN_CONGESTION_PERCENT);
                    String cinId = value.getAsString(BucksTrafficScoot.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksTrafficScoot.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficScoot.TABLE_NAME);
        }
        if (match == TRAFFIC_SPEEDS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksTrafficSpeed.TABLE_NAME + "("
                                + BucksTrafficSpeed.COLUMN_ID + ","
                                + BucksTrafficSpeed.COLUMN_TPEG_DIRECTION + ","
                                + BucksTrafficSpeed.COLUMN_FROM_TYPE + ","
                                + BucksTrafficSpeed.COLUMN_FROM_DESCRIPTOR + ","
                                + BucksTrafficSpeed.COLUMN_FROM_LATITUDE + ","
                                + BucksTrafficSpeed.COLUMN_FROM_LONGITUDE + ","
                                + BucksTrafficSpeed.COLUMN_TO_TYPE + ","
                                + BucksTrafficSpeed.COLUMN_TO_DESCRIPTOR + ","
                                + BucksTrafficSpeed.COLUMN_TO_LATITUDE + ","
                                + BucksTrafficSpeed.COLUMN_TO_LONGITUDE + ","
                                + BucksTrafficSpeed.COLUMN_TIME + ","
                                + BucksTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED + ","
                                + BucksTrafficSpeed.COLUMN_CIN_ID + ","
                                + BucksTrafficSpeed.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(BucksTrafficSpeed.COLUMN_ID);
                    String tpegDirection = value.getAsString(BucksTrafficSpeed.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(BucksTrafficSpeed.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(BucksTrafficSpeed.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(BucksTrafficSpeed.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(BucksTrafficSpeed.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(BucksTrafficSpeed.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(BucksTrafficSpeed.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(BucksTrafficSpeed.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(BucksTrafficSpeed.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(BucksTrafficSpeed.COLUMN_TIME);
                    Double averageVehicleSpeed = value.getAsDouble(BucksTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED);
                    String cinId = value.getAsString(BucksTrafficSpeed.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksTrafficSpeed.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficSpeed.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksTrafficTravelTime.TABLE_NAME + "("
                                + BucksTrafficTravelTime.COLUMN_ID + ","
                                + BucksTrafficTravelTime.COLUMN_TPEG_DIRECTION + ","
                                + BucksTrafficTravelTime.COLUMN_FROM_TYPE + ","
                                + BucksTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + ","
                                + BucksTrafficTravelTime.COLUMN_FROM_LATITUDE + ","
                                + BucksTrafficTravelTime.COLUMN_FROM_LONGITUDE + ","
                                + BucksTrafficTravelTime.COLUMN_TO_TYPE + ","
                                + BucksTrafficTravelTime.COLUMN_TO_DESCRIPTOR + ","
                                + BucksTrafficTravelTime.COLUMN_TO_LATITUDE + ","
                                + BucksTrafficTravelTime.COLUMN_TO_LONGITUDE + ","
                                + BucksTrafficTravelTime.COLUMN_TIME + ","
                                + BucksTrafficTravelTime.COLUMN_TRAVEL_TIME + ","
                                + BucksTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + ","
                                + BucksTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + ","
                                + BucksTrafficTravelTime.COLUMN_CIN_ID + ","
                                + BucksTrafficTravelTime.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(BucksTrafficTravelTime.COLUMN_ID);
                    String tpegDirection = value.getAsString(BucksTrafficTravelTime.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(BucksTrafficTravelTime.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(BucksTrafficTravelTime.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(BucksTrafficTravelTime.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(BucksTrafficTravelTime.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(BucksTrafficTravelTime.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(BucksTrafficTravelTime.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(BucksTrafficTravelTime.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(BucksTrafficTravelTime.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(BucksTrafficTravelTime.COLUMN_TIME);
                    Double travelTime = value.getAsDouble(BucksTrafficTravelTime.COLUMN_TRAVEL_TIME);
                    Double freeFlowTravelTime = value.getAsDouble(BucksTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME);
                    Double freeFlowSpeed = value.getAsDouble(BucksTrafficTravelTime.COLUMN_FREE_FLOW_SPEED);
                    String cinId = value.getAsString(BucksTrafficTravelTime.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksTrafficTravelTime.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + BucksVariableMessageSign.TABLE_NAME + "("
                                + BucksVariableMessageSign.COLUMN_LOCATION_ID + ","
                                + BucksVariableMessageSign.COLUMN_DESCRIPTION + ","
                                + BucksVariableMessageSign.COLUMN_VMS_TYPE + ","
                                + BucksVariableMessageSign.COLUMN_LATITUDE + ","
                                + BucksVariableMessageSign.COLUMN_LONGITUDE + ","
                                + BucksVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + ","
                                + BucksVariableMessageSign.COLUMN_NUMBER_OF_ROWS + ","
                                + BucksVariableMessageSign.COLUMN_VMS_LEGENDS + ","
                                + BucksVariableMessageSign.COLUMN_CIN_ID + ","
                                + BucksVariableMessageSign.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String locationId = value.getAsString(BucksVariableMessageSign.COLUMN_LOCATION_ID);
                    String description = value.getAsString(BucksVariableMessageSign.COLUMN_DESCRIPTION);
                    String vmsType = value.getAsString(BucksVariableMessageSign.COLUMN_VMS_TYPE);
                    Double latitude = value.getAsDouble(BucksVariableMessageSign.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(BucksVariableMessageSign.COLUMN_LONGITUDE);
                    Long numberOfCharacters = value.getAsLong(BucksVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS);
                    Long numberOfRows = value.getAsLong(BucksVariableMessageSign.COLUMN_NUMBER_OF_ROWS);
                    String vmsLegends = value.getAsString(BucksVariableMessageSign.COLUMN_VMS_LEGENDS);
                    String cinId = value.getAsString(BucksVariableMessageSign.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(BucksVariableMessageSign.COLUMN_CREATION_TIME);
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
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestVariableMessageSign.TABLE_NAME);
        }
        return numInserted;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            id = sqLiteDatabase.insert(BucksCarPark.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return ContentUris.withAppendedId(CAR_PARK_URI, id);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestCarPark.TABLE_NAME);
        }
        if (match == EVENTS) {
            id = sqLiteDatabase.insert(BucksEvent.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(EVENT_URI, null);
            return ContentUris.withAppendedId(EVENT_URI, id);
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestEvent.TABLE_NAME);
        }
        if (match == ROADWORKS) {
            id = sqLiteDatabase.insert(BucksRoadworks.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return ContentUris.withAppendedId(ROADWORKS_URI, id);
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestRoadworks.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOWS) {
            id = sqLiteDatabase.insert(BucksTrafficFlow.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_QUEUES) {
            id = sqLiteDatabase.insert(BucksTrafficQueue.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_QUEUE_URI, id);
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficQueue.TABLE_NAME);
        }
        if (match == TRAFFIC_SCOOTS) {
            id = sqLiteDatabase.insert(BucksTrafficScoot.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_SCOOT_URI, id);
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficScoot.TABLE_NAME);
        }
        if (match == TRAFFIC_SPEEDS) {
            id = sqLiteDatabase.insert(BucksTrafficSpeed.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_SPEED_URI, id);
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficSpeed.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            id = sqLiteDatabase.insert(BucksTrafficTravelTime.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_TRAVEL_TIME_URI, id);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            id = sqLiteDatabase.insert(BucksVariableMessageSign.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return ContentUris.withAppendedId(VARIABLE_MESSAGE_SIGN_URI, id);
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BucksLatestVariableMessageSign.TABLE_NAME);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(BucksCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == LATEST_CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_CAR_PARK_URI);
            return cursor;
        }
        if (match == CAR_PARK_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksCarPark.TABLE_NAME, projection,
                    BucksCarPark._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == EVENTS) {
            Cursor cursor = sqLiteDatabase.query(BucksEvent.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, EVENT_URI);
            return cursor;
        }
        if (match == LATEST_EVENTS) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestEvent.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_EVENT_URI);
            return cursor;
        }
        if (match == EVENT_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksEvent.TABLE_NAME, projection,
                    BucksEvent._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, EVENT_URI);
            return cursor;
        }
        if (match == ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(BucksRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
            return cursor;
        }
        if (match == LATEST_ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_ROADWORKS_URI);
            return cursor;
        }
        if (match == ROADWORKS_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksRoadworks.TABLE_NAME, projection,
                    BucksRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOW_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficFlow.TABLE_NAME, projection,
                    BucksTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_QUEUES) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficQueue.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_QUEUE_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestTrafficQueue.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_QUEUE_URI);
            return cursor;
        }
        if (match == TRAFFIC_QUEUE_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficQueue.TABLE_NAME, projection,
                    BucksTrafficQueue._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_QUEUE_URI);
            return cursor;
        }
        if (match == TRAFFIC_SCOOTS) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficScoot.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestTrafficScoot.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == TRAFFIC_SCOOT_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficScoot.TABLE_NAME, projection,
                    BucksTrafficScoot._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SCOOT_URI);
            return cursor;
        }
        if (match == TRAFFIC_SPEEDS) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficSpeed.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestTrafficSpeed.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == TRAFFIC_SPEED_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficSpeed.TABLE_NAME, projection,
                    BucksTrafficSpeed._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_SPEED_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksTrafficTravelTime.TABLE_NAME, projection,
                    BucksTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(BucksVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(BucksLatestVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            Cursor cursor = sqLiteDatabase.query(BucksVariableMessageSign.TABLE_NAME, projection,
                    BucksVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
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
            int rows = sqLiteDatabase.update(BucksCarPark.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestCarPark.TABLE_NAME);
        }
        if (match == CAR_PARK_ID) {
            int rows = sqLiteDatabase.update(BucksCarPark.TABLE_NAME, values, BucksCarPark._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == EVENTS) {
            int rows = sqLiteDatabase.update(BucksEvent.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(EVENT_URI, null);
            return rows;
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestEvent.TABLE_NAME);
        }
        if (match == EVENT_ID) {
            int rows = sqLiteDatabase.update(BucksEvent.TABLE_NAME, values, BucksEvent._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(EVENT_URI, null);
            return rows;
        }
        if (match == ROADWORKS) {
            int rows = sqLiteDatabase.update(BucksRoadworks.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return rows;
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestRoadworks.TABLE_NAME);
        }
        if (match == ROADWORKS_ID) {
            int rows = sqLiteDatabase.update(BucksRoadworks.TABLE_NAME, values,
                    BucksRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return rows;
        }
        if (match == TRAFFIC_FLOWS) {
            int rows = sqLiteDatabase.update(BucksTrafficFlow.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOW_ID) {
            int rows = sqLiteDatabase.update(BucksTrafficFlow.TABLE_NAME, values,
                    BucksTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == TRAFFIC_QUEUES) {
            int rows = sqLiteDatabase.update(BucksTrafficQueue.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestTrafficQueue.TABLE_NAME);
        }
        if (match == TRAFFIC_QUEUE_ID) {
            int rows = sqLiteDatabase.update(BucksTrafficQueue.TABLE_NAME, values,
                    BucksTrafficQueue._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
            return rows;
        }
        if (match == TRAFFIC_SCOOTS) {
            int rows = sqLiteDatabase.update(BucksTrafficScoot.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestTrafficScoot.TABLE_NAME);
        }
        if (match == TRAFFIC_SCOOT_ID) {
            int rows = sqLiteDatabase.update(BucksTrafficScoot.TABLE_NAME, values,
                    BucksTrafficScoot._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
            return rows;
        }
        if (match == TRAFFIC_SPEEDS) {
            int rows = sqLiteDatabase.update(BucksTrafficSpeed.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestTrafficSpeed.TABLE_NAME);
        }
        if (match == TRAFFIC_SPEED_ID) {
            int rows = sqLiteDatabase.update(BucksTrafficSpeed.TABLE_NAME, values,
                    BucksTrafficSpeed._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
            return rows;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            int rows = sqLiteDatabase.update(BucksTrafficTravelTime.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            int rows = sqLiteDatabase.update(BucksTrafficTravelTime.TABLE_NAME, values,
                    BucksTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            int rows = sqLiteDatabase.update(BucksVariableMessageSign.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return rows;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BucksLatestVariableMessageSign.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            int rows = sqLiteDatabase.update(BucksVariableMessageSign.TABLE_NAME, values,
                    BucksVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()});
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
            rows = sqLiteDatabase.delete(BucksCarPark.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestCarPark.TABLE_NAME);
        }
        if (match == EVENTS) {
            rows = sqLiteDatabase.delete(BucksEvent.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(EVENT_URI, null);
        }
        if (match == LATEST_EVENTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestEvent.TABLE_NAME);
        }
        if (match == ROADWORKS) {
            rows = sqLiteDatabase.delete(BucksRoadworks.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestRoadworks.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOWS) {
            rows = sqLiteDatabase.delete(BucksTrafficFlow.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_QUEUES) {
            rows = sqLiteDatabase.delete(BucksTrafficQueue.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_QUEUE_URI, null);
        }
        if (match == LATEST_TRAFFIC_QUEUES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestTrafficQueue.TABLE_NAME);
        }
        if (match == TRAFFIC_SCOOTS) {
            rows = sqLiteDatabase.delete(BucksTrafficScoot.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SCOOT_URI, null);
        }
        if (match == LATEST_TRAFFIC_SCOOTS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestTrafficScoot.TABLE_NAME);
        }
        if (match == TRAFFIC_SPEEDS) {
            rows = sqLiteDatabase.delete(BucksTrafficSpeed.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_SPEED_URI, null);
        }
        if (match == LATEST_TRAFFIC_SPEEDS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestTrafficSpeed.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            rows = sqLiteDatabase.delete(BucksTrafficTravelTime.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            rows = sqLiteDatabase.delete(BucksVariableMessageSign.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BucksLatestVariableMessageSign.TABLE_NAME);
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
                    BucksContentHelper.insertIntoProvider(context, carParks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Events.
            if (extras.getBoolean(EXTRAS_EVENTS, false)) {
                try {
                    Event[] events = new EventRetriever(context).retrieve();
                    BucksContentHelper.insertIntoProvider(context, events);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Road works.
            if (extras.getBoolean(EXTRAS_ROADWORKS, false)) {
                try {
                    Roadworks[] roadworkses = new RoadworksRetriever(context).retrieve();
                    BucksContentHelper.insertIntoProvider(context, roadworkses);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic flows.
            if (extras.getBoolean(EXTRAS_TRAFFIC_FLOW, false)) {
                try {
                    TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
                    BucksContentHelper.insertIntoProvider(context, trafficFlows);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic queues.
            if (extras.getBoolean(EXTRAS_TRAFFIC_QUEUE, false)) {
                try {
                    TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
                    BucksContentHelper.insertIntoProvider(context, trafficQueues);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic scoots.
            if (extras.getBoolean(EXTRAS_TRAFFIC_SCOOT, false)) {
                try {
                    TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
                    BucksContentHelper.insertIntoProvider(context, trafficScoots);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic speeds.
            if (extras.getBoolean(EXTRAS_TRAFFIC_SPEED, false)) {
                try {
                    TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
                    BucksContentHelper.insertIntoProvider(context, trafficSpeeds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic travel times.
            if (extras.getBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, false)) {
                try {
                    TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
                    BucksContentHelper.insertIntoProvider(context, trafficTravelTimes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Variable message signs.
            if (extras.getBoolean(EXTRAS_VMS, false)) {
                try {
                    VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context)
                            .retrieve();
                    BucksContentHelper.insertIntoProvider(context, variableMessageSigns);
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
        Log.i(TAG, "Bucks refresh called.");
    }
}
