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
package net.uk.onetransport.android.county.northants.provider;

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

import net.uk.onetransport.android.county.northants.R;
import net.uk.onetransport.android.county.northants.carparks.CarPark;
import net.uk.onetransport.android.county.northants.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.northants.roadworks.Roadworks;
import net.uk.onetransport.android.county.northants.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.northants.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.northants.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsCarPark;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsLatestCarPark;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsLatestRoadworks;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsLatestTrafficFlow;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsLatestTrafficTravelTime;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsLatestVariableMessageSign;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsRoadworks;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsTrafficFlow;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsTrafficTravelTime;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsVariableMessageSign;

public class NorthantsProviderModule implements ProviderModule {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri CAR_PARK_URI;
    public static Uri LATEST_CAR_PARK_URI;
    public static Uri ROADWORKS_URI;
    public static Uri LATEST_ROADWORKS_URI;
    public static Uri TRAFFIC_FLOW_URI;
    public static Uri LATEST_TRAFFIC_FLOW_URI;
    public static Uri TRAFFIC_TRAVEL_TIME_URI;
    public static Uri LATEST_TRAFFIC_TRAVEL_TIME_URI;
    public static Uri VARIABLE_MESSAGE_SIGN_URI;
    public static Uri LATEST_VARIABLE_MESSAGE_SIGN_URI;
    // Sync adapter extras.
    private static final String EXTRAS_CAR_PARKS =
            "net.uk.onetransport.android.county.northants.sync.CAR_PARKS";
    private static final String EXTRAS_ROADWORKS =
            "net.uk.onetransport.android.county.northants.sync.ROADWORKS";
    private static final String EXTRAS_TRAFFIC_FLOW =
            "net.uk.onetransport.android.county.northants.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_TRAFFIC_TRAVEL_TIME =
            "net.uk.onetransport.android.county.northants.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_VMS = "net.uk.onetransport.android.county.northants.sync.VMS";
    // Uri matching
    private static int CAR_PARKS;
    private static int LATEST_CAR_PARKS;
    private static int CAR_PARK_ID;
    private static int ROADWORKS;
    private static int LATEST_ROADWORKS;
    private static int ROADWORKS_ID;
    private static int TRAFFIC_FLOWS;
    private static int LATEST_TRAFFIC_FLOWS;
    private static int TRAFFIC_FLOW_ID;
    private static int TRAFFIC_TRAVEL_TIMES;
    private static int LATEST_TRAFFIC_TRAVEL_TIMES;
    private static int TRAFFIC_TRAVEL_TIME_ID;
    private static int VARIABLE_MESSAGE_SIGNS;
    private static int LATEST_VARIABLE_MESSAGE_SIGNS;
    private static int VARIABLE_MESSAGE_SIGN_ID;

    private Context context;

    public NorthantsProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_LATEST_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_ROADWORKS_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_LATEST_ROADWORKS_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_LATEST_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI, NorthantsCarPark.TABLE_NAME);
        LATEST_CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI,
                NorthantsLatestCarPark.TABLE_NAME);
        ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, NorthantsRoadworks.TABLE_NAME);
        LATEST_ROADWORKS_URI = Uri.withAppendedPath(AUTHORITY_URI,
                NorthantsLatestRoadworks.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI, NorthantsTrafficFlow.TABLE_NAME);
        LATEST_TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI,
                NorthantsLatestTrafficFlow.TABLE_NAME);
        TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                NorthantsTrafficTravelTime.TABLE_NAME);
        LATEST_TRAFFIC_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                NorthantsLatestTrafficTravelTime.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                NorthantsVariableMessageSign.TABLE_NAME);
        LATEST_VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                NorthantsLatestVariableMessageSign.TABLE_NAME);

        CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsCarPark.TABLE_NAME, CAR_PARKS);
        providerModules.add(this);
        LATEST_CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsLatestCarPark.TABLE_NAME, LATEST_CAR_PARKS);
        providerModules.add(this);
        CAR_PARK_ID = providerModules.size();
        uriMatcher.addURI(authority, NorthantsCarPark.TABLE_NAME + "/#", CAR_PARK_ID);
        providerModules.add(this);
        ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsRoadworks.TABLE_NAME, ROADWORKS);
        providerModules.add(this);
        LATEST_ROADWORKS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsLatestRoadworks.TABLE_NAME, LATEST_ROADWORKS);
        providerModules.add(this);
        ROADWORKS_ID = providerModules.size();
        uriMatcher.addURI(authority, NorthantsRoadworks.TABLE_NAME + "/#", ROADWORKS_ID);
        providerModules.add(this);
        TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsTrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
        providerModules.add(this);
        LATEST_TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsLatestTrafficFlow.TABLE_NAME, LATEST_TRAFFIC_FLOWS);
        providerModules.add(this);
        TRAFFIC_FLOW_ID = providerModules.size();
        uriMatcher.addURI(authority, NorthantsTrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, NorthantsTrafficTravelTime.TABLE_NAME, TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        LATEST_TRAFFIC_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, NorthantsLatestTrafficTravelTime.TABLE_NAME,
                LATEST_TRAFFIC_TRAVEL_TIMES);
        providerModules.add(this);
        TRAFFIC_TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, NorthantsTrafficTravelTime.TABLE_NAME + "/#",
                TRAFFIC_TRAVEL_TIME_ID);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsVariableMessageSign.TABLE_NAME,
                VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        LATEST_VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, NorthantsLatestVariableMessageSign.TABLE_NAME,
                LATEST_VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGN_ID = providerModules.size();
        uriMatcher.addURI(authority, NorthantsVariableMessageSign.TABLE_NAME + "/#",
                VARIABLE_MESSAGE_SIGN_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == CAR_PARKS) {
            return mimeDirPrefix + NorthantsCarPark.TABLE_NAME;
        }
        if (match == LATEST_CAR_PARKS) {
            return mimeDirPrefix + NorthantsLatestCarPark.TABLE_NAME;
        }
        if (match == CAR_PARK_ID) {
            return mimeItemPrefix + NorthantsCarPark.TABLE_NAME;
        }
        if (match == ROADWORKS) {
            return mimeDirPrefix + NorthantsRoadworks.TABLE_NAME;
        }
        if (match == LATEST_ROADWORKS) {
            return mimeDirPrefix + NorthantsLatestRoadworks.TABLE_NAME;
        }
        if (match == ROADWORKS_ID) {
            return mimeItemPrefix + NorthantsRoadworks.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOWS) {
            return mimeDirPrefix + NorthantsTrafficFlow.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            return mimeDirPrefix + NorthantsLatestTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOW_ID) {
            return mimeItemPrefix + NorthantsTrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + NorthantsTrafficTravelTime.TABLE_NAME;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            return mimeDirPrefix + NorthantsLatestTrafficTravelTime.TABLE_NAME;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            return mimeItemPrefix + NorthantsTrafficTravelTime.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + NorthantsVariableMessageSign.TABLE_NAME;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + NorthantsLatestVariableMessageSign.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            return mimeItemPrefix + NorthantsVariableMessageSign.TABLE_NAME;
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
                        "INSERT INTO " + NorthantsCarPark.TABLE_NAME + "("
                                + NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                                + NorthantsCarPark.COLUMN_LATITUDE + ","
                                + NorthantsCarPark.COLUMN_LONGITUDE + ","
                                + NorthantsCarPark.COLUMN_OCCUPANCY + ","
                                + NorthantsCarPark.COLUMN_OCCUPANCY_TREND + ","
                                + NorthantsCarPark.COLUMN_TOTAL_PARKING_CAPACITY + ","
                                + NorthantsCarPark.COLUMN_FILL_RATE + ","
                                + NorthantsCarPark.COLUMN_EXIT_RATE + ","
                                + NorthantsCarPark.COLUMN_ALMOST_FULL_INCREASING + ","
                                + NorthantsCarPark.COLUMN_ALMOST_FULL_DECREASING + ","
                                + NorthantsCarPark.COLUMN_FULL_DECREASING + ","
                                + NorthantsCarPark.COLUMN_FULL_INCREASING + ","
                                + NorthantsCarPark.COLUMN_STATUS + ","
                                + NorthantsCarPark.COLUMN_STATUS_TIME + ","
                                + NorthantsCarPark.COLUMN_QUEUING_TIME + ","
                                + NorthantsCarPark.COLUMN_PARKING_AREA_NAME + ","
                                + NorthantsCarPark.COLUMN_ENTRANCE_FULL + ","
                                + NorthantsCarPark.COLUMN_CIN_ID + ","
                                + NorthantsCarPark.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String identity = value.getAsString(NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY);
                    Double latitude = value.getAsDouble(NorthantsCarPark.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(NorthantsCarPark.COLUMN_LONGITUDE);
                    Double occupancy = value.getAsDouble(NorthantsCarPark.COLUMN_OCCUPANCY);
                    String occupancyTrend = value.getAsString(NorthantsCarPark.COLUMN_OCCUPANCY_TREND);
                    Double totalParkingCapacity = value.getAsDouble(NorthantsCarPark.COLUMN_TOTAL_PARKING_CAPACITY);
                    Double fillRate = value.getAsDouble(NorthantsCarPark.COLUMN_FILL_RATE);
                    Double exitRate = value.getAsDouble(NorthantsCarPark.COLUMN_EXIT_RATE);
                    Double almostFullIncreasing = value.getAsDouble(NorthantsCarPark.COLUMN_ALMOST_FULL_INCREASING);
                    Double almostFullDecreasing = value.getAsDouble(NorthantsCarPark.COLUMN_ALMOST_FULL_DECREASING);
                    Double fullDecreasing = value.getAsDouble(NorthantsCarPark.COLUMN_FULL_DECREASING);
                    Double fullIncreasing = value.getAsDouble(NorthantsCarPark.COLUMN_FULL_INCREASING);
                    String status = value.getAsString(NorthantsCarPark.COLUMN_STATUS);
                    String statusTime = value.getAsString(NorthantsCarPark.COLUMN_STATUS_TIME);
                    Double queuingTime = value.getAsDouble(NorthantsCarPark.COLUMN_QUEUING_TIME);
                    String parkingAreaName = value.getAsString(NorthantsCarPark.COLUMN_PARKING_AREA_NAME);
                    Double entranceFull = value.getAsDouble(NorthantsCarPark.COLUMN_ENTRANCE_FULL);
                    String cinId = value.getAsString(NorthantsCarPark.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(NorthantsCarPark.COLUMN_CREATION_TIME);
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
                    + NorthantsLatestCarPark.TABLE_NAME);
        }
        if (match == ROADWORKS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + NorthantsRoadworks.TABLE_NAME + "("
                                + NorthantsRoadworks.COLUMN_ID + ","
                                + NorthantsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT + ","
                                + NorthantsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE + ","
                                + NorthantsRoadworks.COLUMN_COMMENT + ","
                                + NorthantsRoadworks.COLUMN_IMPACT_ON_TRAFFIC + ","
                                + NorthantsRoadworks.COLUMN_LATITUDE + ","
                                + NorthantsRoadworks.COLUMN_LONGITUDE + ","
                                + NorthantsRoadworks.COLUMN_VALIDITY_STATUS + ","
                                + NorthantsRoadworks.COLUMN_OVERALL_START_TIME + ","
                                + NorthantsRoadworks.COLUMN_OVERALL_END_TIME + ","
                                + NorthantsRoadworks.COLUMN_START_OF_PERIOD + ","
                                + NorthantsRoadworks.COLUMN_END_OF_PERIOD + ","
                                + NorthantsRoadworks.COLUMN_CIN_ID + ","
                                + NorthantsRoadworks.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(NorthantsRoadworks.COLUMN_ID);
                    String effectOnRoadLayout = value.getAsString(NorthantsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT);
                    String roadMaintenanceType = value.getAsString(NorthantsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE);
                    String comment = value.getAsString(NorthantsRoadworks.COLUMN_COMMENT);
                    String impactOnTraffic = value.getAsString(NorthantsRoadworks.COLUMN_IMPACT_ON_TRAFFIC);
                    Double latitude = value.getAsDouble(NorthantsRoadworks.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(NorthantsRoadworks.COLUMN_LONGITUDE);
                    String validityStatus = value.getAsString(NorthantsRoadworks.COLUMN_VALIDITY_STATUS);
                    String overallStartTime = value.getAsString(NorthantsRoadworks.COLUMN_OVERALL_START_TIME);
                    String overallEndTime = value.getAsString(NorthantsRoadworks.COLUMN_OVERALL_END_TIME);
                    String startOfPeriod = value.getAsString(NorthantsRoadworks.COLUMN_START_OF_PERIOD);
                    String endOfPeriod = value.getAsString(NorthantsRoadworks.COLUMN_END_OF_PERIOD);
                    String cinId = value.getAsString(NorthantsRoadworks.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(NorthantsRoadworks.COLUMN_CREATION_TIME);
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
                    + NorthantsLatestRoadworks.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOWS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + NorthantsTrafficFlow.TABLE_NAME + "("
                                + NorthantsTrafficFlow.COLUMN_ID + ","
                                + NorthantsTrafficFlow.COLUMN_TPEG_DIRECTION + ","
                                + NorthantsTrafficFlow.COLUMN_FROM_TYPE + ","
                                + NorthantsTrafficFlow.COLUMN_FROM_DESCRIPTOR + ","
                                + NorthantsTrafficFlow.COLUMN_FROM_LATITUDE + ","
                                + NorthantsTrafficFlow.COLUMN_FROM_LONGITUDE + ","
                                + NorthantsTrafficFlow.COLUMN_TO_TYPE + ","
                                + NorthantsTrafficFlow.COLUMN_TO_DESCRIPTOR + ","
                                + NorthantsTrafficFlow.COLUMN_TO_LATITUDE + ","
                                + NorthantsTrafficFlow.COLUMN_TO_LONGITUDE + ","
                                + NorthantsTrafficFlow.COLUMN_TIME + ","
                                + NorthantsTrafficFlow.COLUMN_VEHICLE_FLOW + ","
                                + NorthantsTrafficFlow.COLUMN_CIN_ID + ","
                                + NorthantsTrafficFlow.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(NorthantsTrafficFlow.COLUMN_ID);
                    String tpegDirection = value.getAsString(NorthantsTrafficFlow.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(NorthantsTrafficFlow.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(NorthantsTrafficFlow.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(NorthantsTrafficFlow.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(NorthantsTrafficFlow.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(NorthantsTrafficFlow.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(NorthantsTrafficFlow.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(NorthantsTrafficFlow.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(NorthantsTrafficFlow.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(NorthantsTrafficFlow.COLUMN_TIME);
                    Double vehicleFlow = value.getAsDouble(NorthantsTrafficFlow.COLUMN_VEHICLE_FLOW);
                    String cinId = value.getAsString(NorthantsTrafficFlow.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(NorthantsTrafficFlow.COLUMN_CREATION_TIME);
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
                    + NorthantsLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + NorthantsTrafficTravelTime.TABLE_NAME + "("
                                + NorthantsTrafficTravelTime.COLUMN_ID + ","
                                + NorthantsTrafficTravelTime.COLUMN_TPEG_DIRECTION + ","
                                + NorthantsTrafficTravelTime.COLUMN_FROM_TYPE + ","
                                + NorthantsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + ","
                                + NorthantsTrafficTravelTime.COLUMN_FROM_LATITUDE + ","
                                + NorthantsTrafficTravelTime.COLUMN_FROM_LONGITUDE + ","
                                + NorthantsTrafficTravelTime.COLUMN_TO_TYPE + ","
                                + NorthantsTrafficTravelTime.COLUMN_TO_DESCRIPTOR + ","
                                + NorthantsTrafficTravelTime.COLUMN_TO_LATITUDE + ","
                                + NorthantsTrafficTravelTime.COLUMN_TO_LONGITUDE + ","
                                + NorthantsTrafficTravelTime.COLUMN_TIME + ","
                                + NorthantsTrafficTravelTime.COLUMN_TRAVEL_TIME + ","
                                + NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + ","
                                + NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + ","
                                + NorthantsTrafficTravelTime.COLUMN_CIN_ID + ","
                                + NorthantsTrafficTravelTime.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String id = value.getAsString(NorthantsTrafficTravelTime.COLUMN_ID);
                    String tpegDirection = value.getAsString(NorthantsTrafficTravelTime.COLUMN_TPEG_DIRECTION);
                    String fromType = value.getAsString(NorthantsTrafficTravelTime.COLUMN_FROM_TYPE);
                    String fromDescriptor = value.getAsString(NorthantsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR);
                    Double fromLatitude = value.getAsDouble(NorthantsTrafficTravelTime.COLUMN_FROM_LATITUDE);
                    Double fromLongitude = value.getAsDouble(NorthantsTrafficTravelTime.COLUMN_FROM_LONGITUDE);
                    String toType = value.getAsString(NorthantsTrafficTravelTime.COLUMN_TO_TYPE);
                    String toDescriptor = value.getAsString(NorthantsTrafficTravelTime.COLUMN_TO_DESCRIPTOR);
                    Double toLatitude = value.getAsDouble(NorthantsTrafficTravelTime.COLUMN_TO_LATITUDE);
                    Double toLongitude = value.getAsDouble(NorthantsTrafficTravelTime.COLUMN_TO_LONGITUDE);
                    String time = value.getAsString(NorthantsTrafficTravelTime.COLUMN_TIME);
                    Double travelTime = value.getAsDouble(NorthantsTrafficTravelTime.COLUMN_TRAVEL_TIME);
                    Double freeFlowTravelTime = value.getAsDouble(NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME);
                    Double freeFlowSpeed = value.getAsDouble(NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED);
                    String cinId = value.getAsString(NorthantsTrafficTravelTime.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(NorthantsTrafficTravelTime.COLUMN_CREATION_TIME);
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
                    + NorthantsLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            sqLiteDatabase.beginTransaction();
            try {
                SQLiteStatement insert = sqLiteDatabase.compileStatement(
                        "INSERT INTO " + NorthantsVariableMessageSign.TABLE_NAME + "("
                                + NorthantsVariableMessageSign.COLUMN_LOCATION_ID + ","
                                + NorthantsVariableMessageSign.COLUMN_DESCRIPTION + ","
                                + NorthantsVariableMessageSign.COLUMN_VMS_TYPE + ","
                                + NorthantsVariableMessageSign.COLUMN_LATITUDE + ","
                                + NorthantsVariableMessageSign.COLUMN_LONGITUDE + ","
                                + NorthantsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + ","
                                + NorthantsVariableMessageSign.COLUMN_NUMBER_OF_ROWS + ","
                                + NorthantsVariableMessageSign.COLUMN_VMS_LEGENDS + ","
                                + NorthantsVariableMessageSign.COLUMN_CIN_ID + ","
                                + NorthantsVariableMessageSign.COLUMN_CREATION_TIME
                                + ") VALUES " + "(?,?,?,?,?,?,?,?,?,?);");
                for (ContentValues value : contentValues) {
                    String locationId = value.getAsString(NorthantsVariableMessageSign.COLUMN_LOCATION_ID);
                    String description = value.getAsString(NorthantsVariableMessageSign.COLUMN_DESCRIPTION);
                    String vmsType = value.getAsString(NorthantsVariableMessageSign.COLUMN_VMS_TYPE);
                    Double latitude = value.getAsDouble(NorthantsVariableMessageSign.COLUMN_LATITUDE);
                    Double longitude = value.getAsDouble(NorthantsVariableMessageSign.COLUMN_LONGITUDE);
                    Long numberOfCharacters = value.getAsLong(NorthantsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS);
                    Long numberOfRows = value.getAsLong(NorthantsVariableMessageSign.COLUMN_NUMBER_OF_ROWS);
                    String vmsLegends = value.getAsString(NorthantsVariableMessageSign.COLUMN_VMS_LEGENDS);
                    String cinId = value.getAsString(NorthantsVariableMessageSign.COLUMN_CIN_ID);
                    Long creationTime = value.getAsLong(NorthantsVariableMessageSign.COLUMN_CREATION_TIME);
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
                    + NorthantsLatestVariableMessageSign.TABLE_NAME);
        }
        return numInserted;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            id = sqLiteDatabase.insert(NorthantsCarPark.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return ContentUris.withAppendedId(CAR_PARK_URI, id);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + NorthantsLatestCarPark.TABLE_NAME);
        }
        if (match == ROADWORKS) {
            id = sqLiteDatabase.insert(NorthantsRoadworks.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return ContentUris.withAppendedId(ROADWORKS_URI, id);
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + NorthantsLatestRoadworks.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOWS) {
            id = sqLiteDatabase.insert(NorthantsTrafficFlow.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + NorthantsLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            id = sqLiteDatabase.insert(NorthantsTrafficTravelTime.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return ContentUris.withAppendedId(TRAFFIC_TRAVEL_TIME_URI, id);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + NorthantsLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            id = sqLiteDatabase.insert(NorthantsVariableMessageSign.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return ContentUris.withAppendedId(VARIABLE_MESSAGE_SIGN_URI, id);
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + NorthantsLatestVariableMessageSign.TABLE_NAME);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == LATEST_CAR_PARKS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsLatestCarPark.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_CAR_PARK_URI);
            return cursor;
        }
        if (match == CAR_PARK_ID) {
            Cursor cursor = sqLiteDatabase.query(NorthantsCarPark.TABLE_NAME, projection,
                    NorthantsCarPark._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
            return cursor;
        }
        if (match == ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
            return cursor;
        }
        if (match == LATEST_ROADWORKS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsLatestRoadworks.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_ROADWORKS_URI);
            return cursor;
        }
        if (match == ROADWORKS_ID) {
            Cursor cursor = sqLiteDatabase.query(NorthantsRoadworks.TABLE_NAME, projection,
                    NorthantsRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, ROADWORKS_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsLatestTrafficFlow.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_FLOW_ID) {
            Cursor cursor = sqLiteDatabase.query(NorthantsTrafficFlow.TABLE_NAME, projection,
                    NorthantsTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(NorthantsTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(NorthantsLatestTrafficTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(NorthantsTrafficTravelTime.TABLE_NAME, projection,
                    NorthantsTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAFFIC_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            Cursor cursor = sqLiteDatabase.query(NorthantsLatestVariableMessageSign.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_VARIABLE_MESSAGE_SIGN_URI);
            return cursor;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            Cursor cursor = sqLiteDatabase.query(NorthantsVariableMessageSign.TABLE_NAME, projection,
                    NorthantsVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
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
            int rows = sqLiteDatabase.update(NorthantsCarPark.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + NorthantsLatestCarPark.TABLE_NAME);
        }
        if (match == CAR_PARK_ID) {
            int rows = sqLiteDatabase.update(NorthantsCarPark.TABLE_NAME, values, NorthantsCarPark._ID + "=?",
                    new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(CAR_PARK_URI, null);
            return rows;
        }
        if (match == ROADWORKS) {
            int rows = sqLiteDatabase.update(NorthantsRoadworks.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return rows;
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + NorthantsLatestRoadworks.TABLE_NAME);
        }
        if (match == ROADWORKS_ID) {
            int rows = sqLiteDatabase.update(NorthantsRoadworks.TABLE_NAME, values,
                    NorthantsRoadworks._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(ROADWORKS_URI, null);
            return rows;
        }
        if (match == TRAFFIC_FLOWS) {
            int rows = sqLiteDatabase.update(NorthantsTrafficFlow.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + NorthantsLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOW_ID) {
            int rows = sqLiteDatabase.update(NorthantsTrafficFlow.TABLE_NAME, values,
                    NorthantsTrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
            return rows;
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            int rows = sqLiteDatabase.update(NorthantsTrafficTravelTime.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + NorthantsLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIME_ID) {
            int rows = sqLiteDatabase.update(NorthantsTrafficTravelTime.TABLE_NAME, values,
                    NorthantsTrafficTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            int rows = sqLiteDatabase.update(NorthantsVariableMessageSign.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
            return rows;
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + NorthantsLatestVariableMessageSign.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            int rows = sqLiteDatabase.update(NorthantsVariableMessageSign.TABLE_NAME, values,
                    NorthantsVariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()});
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
            rows = sqLiteDatabase.delete(NorthantsCarPark.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(CAR_PARK_URI, null);
        }
        if (match == LATEST_CAR_PARKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + NorthantsLatestCarPark.TABLE_NAME);
        }
        if (match == ROADWORKS) {
            rows = sqLiteDatabase.delete(NorthantsRoadworks.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(ROADWORKS_URI, null);
        }
        if (match == LATEST_ROADWORKS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + NorthantsLatestRoadworks.TABLE_NAME);
        }
        if (match == TRAFFIC_FLOWS) {
            rows = sqLiteDatabase.delete(NorthantsTrafficFlow.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
        }
        if (match == LATEST_TRAFFIC_FLOWS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + NorthantsLatestTrafficFlow.TABLE_NAME);
        }
        if (match == TRAFFIC_TRAVEL_TIMES) {
            rows = sqLiteDatabase.delete(NorthantsTrafficTravelTime.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(TRAFFIC_TRAVEL_TIME_URI, null);
        }
        if (match == LATEST_TRAFFIC_TRAVEL_TIMES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + NorthantsLatestTrafficTravelTime.TABLE_NAME);
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            rows = sqLiteDatabase.delete(NorthantsVariableMessageSign.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
        }
        if (match == LATEST_VARIABLE_MESSAGE_SIGNS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + NorthantsLatestVariableMessageSign.TABLE_NAME);
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
                    NorthantsContentHelper.insertIntoProvider(context, carParks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Road works.
            if (extras.getBoolean(EXTRAS_ROADWORKS, false)) {
                try {
                    Roadworks[] roadworkses = new RoadworksRetriever(context).retrieve();
                    NorthantsContentHelper.insertIntoProvider(context, roadworkses);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic flows.
            if (extras.getBoolean(EXTRAS_TRAFFIC_FLOW, false)) {
                try {
                    TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
                    NorthantsContentHelper.insertIntoProvider(context, trafficFlows);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic travel times.
            if (extras.getBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, false)) {
                try {
                    TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
                    NorthantsContentHelper.insertIntoProvider(context, trafficTravelTimes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Variable message signs.
            if (extras.getBoolean(EXTRAS_VMS, false)) {
                try {
                    VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context)
                            .retrieve();
                    NorthantsContentHelper.insertIntoProvider(context, variableMessageSigns);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refresh(Context context, boolean carParks, boolean roadworks, boolean trafficFlow,
                               boolean trafficTravelTime, boolean variableMessageSigns) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_CAR_PARKS, carParks);
        settingsBundle.putBoolean(EXTRAS_ROADWORKS, roadworks);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_FLOW, trafficFlow);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_TRAVEL_TIME, trafficTravelTime);
        settingsBundle.putBoolean(EXTRAS_VMS, variableMessageSigns);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }
}
