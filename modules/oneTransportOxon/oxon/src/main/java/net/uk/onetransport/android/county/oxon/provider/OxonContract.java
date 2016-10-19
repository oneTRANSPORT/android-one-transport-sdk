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

import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;

public class OxonContract {

    // Table initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonCarPark.TABLE_NAME + " ("
                    + OxonCarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonCarPark.COLUMN_CAR_PARK_IDENTITY + " TEXT NOT NULL,"
                    + OxonCarPark.COLUMN_LATITUDE + " REAL,"
                    + OxonCarPark.COLUMN_LONGITUDE + " REAL,"
                    + OxonCarPark.COLUMN_OCCUPANCY + " REAL,"
                    + OxonCarPark.COLUMN_OCCUPANCY_TREND + " TEXT,"
                    + OxonCarPark.COLUMN_TOTAL_PARKING_CAPACITY + " REAL,"
                    + OxonCarPark.COLUMN_FILL_RATE + " REAL,"
                    + OxonCarPark.COLUMN_EXIT_RATE + " REAL,"
                    + OxonCarPark.COLUMN_ALMOST_FULL_INCREASING + " REAL,"
                    + OxonCarPark.COLUMN_ALMOST_FULL_DECREASING + " REAL,"
                    + OxonCarPark.COLUMN_FULL_DECREASING + " REAL,"
                    + OxonCarPark.COLUMN_FULL_INCREASING + " REAL,"
                    + OxonCarPark.COLUMN_STATUS + " TEXT,"
                    + OxonCarPark.COLUMN_STATUS_TIME + " TEXT,"
                    + OxonCarPark.COLUMN_QUEUING_TIME + " REAL,"
                    + OxonCarPark.COLUMN_PARKING_AREA_NAME + " TEXT,"
                    + OxonCarPark.COLUMN_ENTRANCE_FULL + " REAL,"
                    + OxonCarPark.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonCarPark.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                    + OxonCarPark.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_CAR_PARK_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestCarPark.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonCarPark.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonCarPark.COLUMN_CAR_PARK_IDENTITY
                    + ", MAX("
                    + OxonCarPark.COLUMN_CREATION_TIME + ") AS "
                    + OxonCarPark.COLUMN_CREATION_TIME + " FROM "
                    + OxonCarPark.TABLE_NAME + " GROUP BY "
                    + OxonCarPark.COLUMN_CAR_PARK_IDENTITY + ") AS b ON a."
                    + OxonCarPark.COLUMN_CAR_PARK_IDENTITY + "=b."
                    + OxonCarPark.COLUMN_CAR_PARK_IDENTITY + " AND a."
                    + OxonCarPark.COLUMN_CREATION_TIME + "=b."
                    + OxonCarPark.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_EVENT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonEvent.TABLE_NAME + " ("
                    + OxonEvent._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonEvent.COLUMN_ID + " TEXT,"
                    + OxonEvent.COLUMN_START_OF_PERIOD + " TEXT,"
                    + OxonEvent.COLUMN_END_OF_PERIOD + " TEXT,"
                    + OxonEvent.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + OxonEvent.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + OxonEvent.COLUMN_LATITUDE + " REAL,"
                    + OxonEvent.COLUMN_LONGITUDE + " REAL,"
                    + OxonEvent.COLUMN_DESCRIPTION + " TEXT,"
                    + OxonEvent.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + OxonEvent.COLUMN_VALIDITY_STATUS + " TEXT,"
                    + OxonEvent.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonEvent.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonEvent.COLUMN_ID + ","
                    + OxonEvent.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_EVENT_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestEvent.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonEvent.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonEvent.COLUMN_ID
                    + ", MAX("
                    + OxonEvent.COLUMN_CREATION_TIME + ") AS "
                    + OxonEvent.COLUMN_CREATION_TIME + " FROM "
                    + OxonEvent.TABLE_NAME + " GROUP BY "
                    + OxonEvent.COLUMN_ID + ") AS b ON a."
                    + OxonEvent.COLUMN_ID + "=b."
                    + OxonEvent.COLUMN_ID + " AND a."
                    + OxonEvent.COLUMN_CREATION_TIME + "=b."
                    + OxonEvent.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_ROADWORKS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonRoadworks.TABLE_NAME + " ("
                    + OxonRoadworks.COLUMN_ID + " TEXT NOT NULL,"
                    + OxonRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT + " TEXT,"
                    + OxonRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE + " TEXT,"
                    + OxonRoadworks.COLUMN_COMMENT + " TEXT,"
                    + OxonRoadworks.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + OxonRoadworks.COLUMN_LATITUDE + " REAL,"
                    + OxonRoadworks.COLUMN_LONGITUDE + " REAL,"
                    + OxonRoadworks.COLUMN_VALIDITY_STATUS + " TEXT,"
                    + OxonRoadworks.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + OxonRoadworks.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + OxonRoadworks.COLUMN_START_OF_PERIOD + " TEXT,"
                    + OxonRoadworks.COLUMN_END_OF_PERIOD + " TEXT,"
                    + OxonRoadworks.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonRoadworks.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonRoadworks.COLUMN_ID + ","
                    + OxonRoadworks.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_ROADWORKS_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestRoadworks.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonRoadworks.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonRoadworks.COLUMN_ID
                    + ", MAX("
                    + OxonRoadworks.COLUMN_CREATION_TIME + ") AS "
                    + OxonRoadworks.COLUMN_CREATION_TIME + " FROM "
                    + OxonRoadworks.TABLE_NAME + " GROUP BY "
                    + OxonRoadworks.COLUMN_ID + ") AS b ON a."
                    + OxonRoadworks.COLUMN_ID + "=b."
                    + OxonRoadworks.COLUMN_ID + " AND a."
                    + OxonRoadworks.COLUMN_CREATION_TIME + "=b."
                    + OxonRoadworks.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_FLOW_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonTrafficFlow.TABLE_NAME + " ("
                    + OxonTrafficFlow._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonTrafficFlow.COLUMN_ID + " TEXT NOT NULL,"
                    + OxonTrafficFlow.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + OxonTrafficFlow.COLUMN_FROM_TYPE + " TEXT,"
                    + OxonTrafficFlow.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + OxonTrafficFlow.COLUMN_FROM_LATITUDE + " REAL,"
                    + OxonTrafficFlow.COLUMN_FROM_LONGITUDE + " REAL,"
                    + OxonTrafficFlow.COLUMN_TO_TYPE + " TEXT,"
                    + OxonTrafficFlow.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + OxonTrafficFlow.COLUMN_TO_LATITUDE + " REAL,"
                    + OxonTrafficFlow.COLUMN_TO_LONGITUDE + " REAL,"
                    + OxonTrafficFlow.COLUMN_TIME + " TEXT,"
                    + OxonTrafficFlow.COLUMN_VEHICLE_FLOW + " REAL,"
                    + OxonTrafficFlow.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonTrafficFlow.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonTrafficFlow.COLUMN_ID + ","
                    + OxonTrafficFlow.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_FLOW_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestTrafficFlow.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonTrafficFlow.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonTrafficFlow.COLUMN_ID
                    + ", MAX("
                    + OxonTrafficFlow.COLUMN_CREATION_TIME + ") AS "
                    + OxonTrafficFlow.COLUMN_CREATION_TIME + " FROM "
                    + OxonTrafficFlow.TABLE_NAME + " GROUP BY "
                    + OxonTrafficFlow.COLUMN_ID + ") AS b ON a."
                    + OxonTrafficFlow.COLUMN_ID + "=b."
                    + OxonTrafficFlow.COLUMN_ID + " AND a."
                    + OxonTrafficFlow.COLUMN_CREATION_TIME + "=b."
                    + OxonTrafficFlow.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_QUEUE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonTrafficQueue.TABLE_NAME + " ("
                    + OxonTrafficQueue._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonTrafficQueue.COLUMN_ID + " TEXT NOT NULL,"
                    + OxonTrafficQueue.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + OxonTrafficQueue.COLUMN_FROM_TYPE + " TEXT,"
                    + OxonTrafficQueue.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + OxonTrafficQueue.COLUMN_FROM_LATITUDE + " REAL,"
                    + OxonTrafficQueue.COLUMN_FROM_LONGITUDE + " REAL,"
                    + OxonTrafficQueue.COLUMN_TO_TYPE + " TEXT,"
                    + OxonTrafficQueue.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + OxonTrafficQueue.COLUMN_TO_LATITUDE + " REAL,"
                    + OxonTrafficQueue.COLUMN_TO_LONGITUDE + " REAL,"
                    + OxonTrafficQueue.COLUMN_TIME + " TEXT,"
                    + OxonTrafficQueue.COLUMN_SEVERITY + " REAL,"
                    + OxonTrafficQueue.COLUMN_PRESENT + " TEXT,"
                    + OxonTrafficQueue.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonTrafficQueue.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonTrafficQueue.COLUMN_ID + ","
                    + OxonTrafficQueue.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_QUEUE_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestTrafficQueue.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonTrafficQueue.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonTrafficQueue.COLUMN_ID
                    + ", MAX("
                    + OxonTrafficQueue.COLUMN_CREATION_TIME + ") AS "
                    + OxonTrafficQueue.COLUMN_CREATION_TIME + " FROM "
                    + OxonTrafficQueue.TABLE_NAME + " GROUP BY "
                    + OxonTrafficQueue.COLUMN_ID + ") AS b ON a."
                    + OxonTrafficQueue.COLUMN_ID + "=b."
                    + OxonTrafficQueue.COLUMN_ID + " AND a."
                    + OxonTrafficQueue.COLUMN_CREATION_TIME + "=b."
                    + OxonTrafficQueue.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SCOOT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonTrafficScoot.TABLE_NAME + " ("
                    + OxonTrafficScoot._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonTrafficScoot.COLUMN_ID + " TEXT NOT NULL,"
                    + OxonTrafficScoot.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + OxonTrafficScoot.COLUMN_FROM_TYPE + " TEXT,"
                    + OxonTrafficScoot.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + OxonTrafficScoot.COLUMN_FROM_LATITUDE + " REAL,"
                    + OxonTrafficScoot.COLUMN_FROM_LONGITUDE + " REAL,"
                    + OxonTrafficScoot.COLUMN_TO_TYPE + " TEXT,"
                    + OxonTrafficScoot.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + OxonTrafficScoot.COLUMN_TO_LATITUDE + " REAL,"
                    + OxonTrafficScoot.COLUMN_TO_LONGITUDE + " REAL,"
                    + OxonTrafficScoot.COLUMN_TIME + " TEXT,"
                    + OxonTrafficScoot.COLUMN_CURRENT_FLOW + " REAL,"
                    + OxonTrafficScoot.COLUMN_AVERAGE_SPEED + " REAL,"
                    + OxonTrafficScoot.COLUMN_LINK_STATUS_TYPE + " REAL,"
                    + OxonTrafficScoot.COLUMN_LINK_STATUS + " REAL,"
                    + OxonTrafficScoot.COLUMN_LINK_TRAVEL_TIME + " REAL,"
                    + OxonTrafficScoot.COLUMN_CONGESTION_PERCENT + " REAL,"
                    + OxonTrafficScoot.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonTrafficScoot.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonTrafficScoot.COLUMN_ID + ","
                    + OxonTrafficScoot.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SCOOT_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestTrafficScoot.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonTrafficScoot.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonTrafficScoot.COLUMN_ID
                    + ", MAX("
                    + OxonTrafficScoot.COLUMN_CREATION_TIME + ") AS "
                    + OxonTrafficScoot.COLUMN_CREATION_TIME + " FROM "
                    + OxonTrafficScoot.TABLE_NAME + " GROUP BY "
                    + OxonTrafficScoot.COLUMN_ID + ") AS b ON a."
                    + OxonTrafficScoot.COLUMN_ID + "=b."
                    + OxonTrafficScoot.COLUMN_ID + " AND a."
                    + OxonTrafficScoot.COLUMN_CREATION_TIME + "=b."
                    + OxonTrafficScoot.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SPEED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonTrafficSpeed.TABLE_NAME + " ("
                    + OxonTrafficSpeed._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonTrafficSpeed.COLUMN_ID + " TEXT NOT NULL,"
                    + OxonTrafficSpeed.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + OxonTrafficSpeed.COLUMN_FROM_TYPE + " TEXT,"
                    + OxonTrafficSpeed.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + OxonTrafficSpeed.COLUMN_FROM_LATITUDE + " REAL,"
                    + OxonTrafficSpeed.COLUMN_FROM_LONGITUDE + " REAL,"
                    + OxonTrafficSpeed.COLUMN_TO_TYPE + " TEXT,"
                    + OxonTrafficSpeed.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + OxonTrafficSpeed.COLUMN_TO_LATITUDE + " REAL,"
                    + OxonTrafficSpeed.COLUMN_TO_LONGITUDE + " REAL,"
                    + OxonTrafficSpeed.COLUMN_TIME + " TEXT,"
                    + OxonTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED + " REAL,"
                    + OxonTrafficSpeed.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonTrafficSpeed.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonTrafficSpeed.COLUMN_ID + ","
                    + OxonTrafficSpeed.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SPEED_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestTrafficSpeed.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonTrafficSpeed.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonTrafficSpeed.COLUMN_ID
                    + ", MAX("
                    + OxonTrafficSpeed.COLUMN_CREATION_TIME + ") AS "
                    + OxonTrafficSpeed.COLUMN_CREATION_TIME + " FROM "
                    + OxonTrafficSpeed.TABLE_NAME + " GROUP BY "
                    + OxonTrafficSpeed.COLUMN_ID + ") AS b ON a."
                    + OxonTrafficSpeed.COLUMN_ID + "=b."
                    + OxonTrafficSpeed.COLUMN_ID + " AND a."
                    + OxonTrafficSpeed.COLUMN_CREATION_TIME + "=b."
                    + OxonTrafficSpeed.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonTrafficTravelTime.TABLE_NAME + " ("
                    + OxonTrafficTravelTime._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonTrafficTravelTime.COLUMN_ID + " TEXT NOT NULL,"
                    + OxonTrafficTravelTime.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + OxonTrafficTravelTime.COLUMN_FROM_TYPE + " TEXT,"
                    + OxonTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + OxonTrafficTravelTime.COLUMN_FROM_LATITUDE + " REAL,"
                    + OxonTrafficTravelTime.COLUMN_FROM_LONGITUDE + " REAL,"
                    + OxonTrafficTravelTime.COLUMN_TO_TYPE + " TEXT,"
                    + OxonTrafficTravelTime.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + OxonTrafficTravelTime.COLUMN_TO_LATITUDE + " REAL,"
                    + OxonTrafficTravelTime.COLUMN_TO_LONGITUDE + " REAL,"
                    + OxonTrafficTravelTime.COLUMN_TIME + " TEXT,"
                    + OxonTrafficTravelTime.COLUMN_TRAVEL_TIME + " REAL,"
                    + OxonTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + " REAL,"
                    + OxonTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + " REAL,"
                    + OxonTrafficTravelTime.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonTrafficTravelTime.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonTrafficTravelTime.COLUMN_ID + ","
                    + OxonTrafficTravelTime.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestTrafficTravelTime.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonTrafficTravelTime.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonTrafficTravelTime.COLUMN_ID
                    + ", MAX("
                    + OxonTrafficTravelTime.COLUMN_CREATION_TIME + ") AS "
                    + OxonTrafficTravelTime.COLUMN_CREATION_TIME + " FROM "
                    + OxonTrafficTravelTime.TABLE_NAME + " GROUP BY "
                    + OxonTrafficTravelTime.COLUMN_ID + ") AS b ON a."
                    + OxonTrafficTravelTime.COLUMN_ID + "=b."
                    + OxonTrafficTravelTime.COLUMN_ID + " AND a."
                    + OxonTrafficTravelTime.COLUMN_CREATION_TIME + "=b."
                    + OxonTrafficTravelTime.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OxonVariableMessageSign.TABLE_NAME + " ("
                    + OxonVariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OxonVariableMessageSign.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + OxonVariableMessageSign.COLUMN_DESCRIPTION + " TEXT,"
                    + OxonVariableMessageSign.COLUMN_VMS_TYPE + " TEXT,"
                    + OxonVariableMessageSign.COLUMN_LATITUDE + " REAL,"
                    + OxonVariableMessageSign.COLUMN_LONGITUDE + " REAL,"
                    + OxonVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER,"
                    + OxonVariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER,"
                    + OxonVariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT,"
                    + OxonVariableMessageSign.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + OxonVariableMessageSign.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + OxonVariableMessageSign.COLUMN_LOCATION_ID + ","
                    + OxonVariableMessageSign.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE VIEW IF NOT EXISTS " + OxonLatestVariableMessageSign.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + OxonVariableMessageSign.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + OxonVariableMessageSign.COLUMN_LOCATION_ID
                    + ", MAX("
                    + OxonVariableMessageSign.COLUMN_CREATION_TIME + ") AS "
                    + OxonVariableMessageSign.COLUMN_CREATION_TIME + " FROM "
                    + OxonVariableMessageSign.TABLE_NAME + " GROUP BY "
                    + OxonVariableMessageSign.COLUMN_LOCATION_ID + ") AS b ON a."
                    + OxonVariableMessageSign.COLUMN_LOCATION_ID + "=b."
                    + OxonVariableMessageSign.COLUMN_LOCATION_ID + " AND a."
                    + OxonVariableMessageSign.COLUMN_CREATION_TIME + "=b."
                    + OxonVariableMessageSign.COLUMN_CREATION_TIME + ";";

    private OxonContract() {
    }

    public static final class OxonCarPark implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_car_park";
        public static final String COLUMN_CAR_PARK_IDENTITY = "car_park_identity";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_OCCUPANCY = "occupancy";
        public static final String COLUMN_OCCUPANCY_TREND = "occupancy_trend";
        public static final String COLUMN_TOTAL_PARKING_CAPACITY = "total_parking_capacity";
        public static final String COLUMN_FILL_RATE = "fill_rate";
        public static final String COLUMN_EXIT_RATE = "exit_rate";
        public static final String COLUMN_ALMOST_FULL_INCREASING = "almost_full_increasing";
        public static final String COLUMN_ALMOST_FULL_DECREASING = "almost_full_decreasing";
        public static final String COLUMN_FULL_INCREASING = "full_increasing";
        public static final String COLUMN_FULL_DECREASING = "full_decreasing";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_STATUS_TIME = "status_time";
        public static final String COLUMN_QUEUING_TIME = "queuing_time";
        public static final String COLUMN_PARKING_AREA_NAME = "parking_area_name";
        public static final String COLUMN_ENTRANCE_FULL = "entrance_full";
    }

    // Just a view, so all columns come from the car park table.
    public static final class OxonLatestCarPark {
        public static final String TABLE_NAME = "oxon_latest_car_park";
    }

    public static final class OxonEvent implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_event";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_START_OF_PERIOD = "start_of_period";
        public static final String COLUMN_END_OF_PERIOD = "end_of_period";
        public static final String COLUMN_OVERALL_START_TIME = "overall_start_time";
        public static final String COLUMN_OVERALL_END_TIME = "overall_end_time";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMPACT_ON_TRAFFIC = "impact_on_traffic";
        public static final String COLUMN_VALIDITY_STATUS = "validity_status";
    }

    public static final class OxonLatestEvent {
        public static final String TABLE_NAME = "oxon_latest_event";
    }

    public static final class OxonRoadworks implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_roadworks";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_EFFECT_ON_ROAD_LAYOUT = "effect_on_road_layout";
        public static final String COLUMN_ROAD_MAINTENANCE_TYPE = "road_maintenance_type";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_IMPACT_ON_TRAFFIC = "impact_on_traffic";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_VALIDITY_STATUS = "validity_status";
        public static final String COLUMN_OVERALL_START_TIME = "overall_start_time";
        public static final String COLUMN_OVERALL_END_TIME = "overall_end_time";
        public static final String COLUMN_START_OF_PERIOD = "start_of_period";
        public static final String COLUMN_END_OF_PERIOD = "end_of_period";
    }

    public static final class OxonLatestRoadworks {
        public static final String TABLE_NAME = "oxon_latest_roadworks";
    }

    public static final class OxonTrafficFlow implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_traffic_flow";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
        public static final String COLUMN_FROM_TYPE = "from_type";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_TYPE = "to_type";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_VEHICLE_FLOW = "vehicle_flow";
        public static final String COLUMN_TIME = "time";
    }

    public static final class OxonLatestTrafficFlow {
        public static final String TABLE_NAME = "oxon_latest_traffic_flow";
    }

    public static final class OxonTrafficQueue implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_traffic_queue";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
        public static final String COLUMN_FROM_TYPE = "from_type";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_TYPE = "to_type";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_SEVERITY = "severity";
        public static final String COLUMN_PRESENT = "present";
    }

    public static final class OxonLatestTrafficQueue {
        public static final String TABLE_NAME = "oxon_latest_traffic_queue";
    }

    public static final class OxonTrafficScoot implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_traffic_scoot";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
        public static final String COLUMN_FROM_TYPE = "from_type";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_TYPE = "to_type";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_CURRENT_FLOW = "current_flow";
        public static final String COLUMN_AVERAGE_SPEED = "average_speed";
        public static final String COLUMN_LINK_STATUS_TYPE = "link_status_type";
        public static final String COLUMN_LINK_STATUS = "link_status";
        public static final String COLUMN_LINK_TRAVEL_TIME = "link_travel_time";
        public static final String COLUMN_CONGESTION_PERCENT = "congestion_percent";
    }

    public static final class OxonLatestTrafficScoot {
        public static final String TABLE_NAME = "oxon_latest_traffic_scoot";
    }

    public static final class OxonTrafficSpeed implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_traffic_speed";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
        public static final String COLUMN_FROM_TYPE = "from_type";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_TYPE = "to_type";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_AVERAGE_VEHICLE_SPEED = "average_vehicle_speed";
    }

    public static final class OxonLatestTrafficSpeed {
        public static final String TABLE_NAME = "oxon_latest_traffic_speed";
    }

    public static final class OxonTrafficTravelTime implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_traffic_travel_time";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
        public static final String COLUMN_FROM_TYPE = "from_type";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_TYPE = "to_type";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_TRAVEL_TIME = "travel_time";
        public static final String COLUMN_FREE_FLOW_TRAVEL_TIME = "free_flow_travel_time";
        public static final String COLUMN_FREE_FLOW_SPEED = "free_flow_speed";
    }

    public static final class OxonLatestTrafficTravelTime {
        public static final String TABLE_NAME = "oxon_latest_traffic_travel_time";
    }

    public static final class OxonVariableMessageSign implements CommonBaseColumns {
        public static final String TABLE_NAME = "oxon_variable_message_sign";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_VMS_TYPE = "vms_type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
        public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
        public static final String COLUMN_VMS_LEGENDS = "vms_legends";
    }

    public static final class OxonLatestVariableMessageSign {
        public static final String TABLE_NAME = "oxon_latest_variable_message_sign";
    }
}
