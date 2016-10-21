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
package net.uk.onetransport.android.county.herts.provider;

import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;

public class HertsContract {

    // Table initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsCarPark.TABLE_NAME + " ("
                    + HertsCarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HertsCarPark.COLUMN_CAR_PARK_IDENTITY + " TEXT NOT NULL,"
                    + HertsCarPark.COLUMN_LATITUDE + " REAL,"
                    + HertsCarPark.COLUMN_LONGITUDE + " REAL,"
                    + HertsCarPark.COLUMN_OCCUPANCY + " REAL,"
                    + HertsCarPark.COLUMN_OCCUPANCY_TREND + " TEXT,"
                    + HertsCarPark.COLUMN_TOTAL_PARKING_CAPACITY + " REAL,"
                    + HertsCarPark.COLUMN_FILL_RATE + " REAL,"
                    + HertsCarPark.COLUMN_EXIT_RATE + " REAL,"
                    + HertsCarPark.COLUMN_ALMOST_FULL_INCREASING + " REAL,"
                    + HertsCarPark.COLUMN_ALMOST_FULL_DECREASING + " REAL,"
                    + HertsCarPark.COLUMN_FULL_DECREASING + " REAL,"
                    + HertsCarPark.COLUMN_FULL_INCREASING + " REAL,"
                    + HertsCarPark.COLUMN_STATUS + " TEXT,"
                    + HertsCarPark.COLUMN_STATUS_TIME + " TEXT,"
                    + HertsCarPark.COLUMN_QUEUING_TIME + " REAL,"
                    + HertsCarPark.COLUMN_PARKING_AREA_NAME + " TEXT,"
                    + HertsCarPark.COLUMN_ENTRANCE_FULL + " REAL,"
                    + HertsCarPark.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsCarPark.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                    + HertsCarPark.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_CAR_PARK_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestCarPark.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsCarPark.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsCarPark.COLUMN_CAR_PARK_IDENTITY
                    + ", MAX("
                    + HertsCarPark.COLUMN_CREATION_TIME + ") AS "
                    + HertsCarPark.COLUMN_CREATION_TIME + " FROM "
                    + HertsCarPark.TABLE_NAME + " GROUP BY "
                    + HertsCarPark.COLUMN_CAR_PARK_IDENTITY + ") AS b ON a."
                    + HertsCarPark.COLUMN_CAR_PARK_IDENTITY + "=b."
                    + HertsCarPark.COLUMN_CAR_PARK_IDENTITY + " AND a."
                    + HertsCarPark.COLUMN_CREATION_TIME + "=b."
                    + HertsCarPark.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_EVENT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsEvent.TABLE_NAME + " ("
                    + HertsEvent._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HertsEvent.COLUMN_ID + " TEXT NOT NULL,"
                    + HertsEvent.COLUMN_START_OF_PERIOD + " TEXT,"
                    + HertsEvent.COLUMN_END_OF_PERIOD + " TEXT,"
                    + HertsEvent.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + HertsEvent.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + HertsEvent.COLUMN_LATITUDE + " REAL,"
                    + HertsEvent.COLUMN_LONGITUDE + " REAL,"
                    + HertsEvent.COLUMN_DESCRIPTION + " TEXT,"
                    + HertsEvent.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + HertsEvent.COLUMN_VALIDITY_STATUS + " TEXT,"
                    + HertsEvent.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsEvent.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsEvent.COLUMN_ID + ","
                    + HertsEvent.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_EVENT_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestEvent.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsEvent.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsEvent.COLUMN_ID
                    + ", MAX("
                    + HertsEvent.COLUMN_CREATION_TIME + ") AS "
                    + HertsEvent.COLUMN_CREATION_TIME + " FROM "
                    + HertsEvent.TABLE_NAME + " GROUP BY "
                    + HertsEvent.COLUMN_ID + ") AS b ON a."
                    + HertsEvent.COLUMN_ID + "=b."
                    + HertsEvent.COLUMN_ID + " AND a."
                    + HertsEvent.COLUMN_CREATION_TIME + "=b."
                    + HertsEvent.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_ROADWORKS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsRoadworks.TABLE_NAME + " ("
                    + HertsRoadworks.COLUMN_ID + " TEXT NOT NULL,"
                    + HertsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT + " TEXT,"
                    + HertsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE + " TEXT,"
                    + HertsRoadworks.COLUMN_COMMENT + " TEXT,"
                    + HertsRoadworks.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + HertsRoadworks.COLUMN_LATITUDE + " REAL,"
                    + HertsRoadworks.COLUMN_LONGITUDE + " REAL,"
                    + HertsRoadworks.COLUMN_VALIDITY_STATUS + " TEXT,"
                    + HertsRoadworks.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + HertsRoadworks.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + HertsRoadworks.COLUMN_START_OF_PERIOD + " TEXT,"
                    + HertsRoadworks.COLUMN_END_OF_PERIOD + " TEXT,"
                    + HertsRoadworks.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsRoadworks.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsRoadworks.COLUMN_ID + ","
                    + HertsRoadworks.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_ROADWORKS_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestRoadworks.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsRoadworks.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsRoadworks.COLUMN_ID
                    + ", MAX("
                    + HertsRoadworks.COLUMN_CREATION_TIME + ") AS "
                    + HertsRoadworks.COLUMN_CREATION_TIME + " FROM "
                    + HertsRoadworks.TABLE_NAME + " GROUP BY "
                    + HertsRoadworks.COLUMN_ID + ") AS b ON a."
                    + HertsRoadworks.COLUMN_ID + "=b."
                    + HertsRoadworks.COLUMN_ID + " AND a."
                    + HertsRoadworks.COLUMN_CREATION_TIME + "=b."
                    + HertsRoadworks.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_FLOW_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsTrafficFlow.TABLE_NAME + " ("
                    + HertsTrafficFlow._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HertsTrafficFlow.COLUMN_ID + " TEXT NOT NULL,"
                    + HertsTrafficFlow.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + HertsTrafficFlow.COLUMN_FROM_TYPE + " TEXT,"
                    + HertsTrafficFlow.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + HertsTrafficFlow.COLUMN_FROM_LATITUDE + " REAL,"
                    + HertsTrafficFlow.COLUMN_FROM_LONGITUDE + " REAL,"
                    + HertsTrafficFlow.COLUMN_TO_TYPE + " TEXT,"
                    + HertsTrafficFlow.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + HertsTrafficFlow.COLUMN_TO_LATITUDE + " REAL,"
                    + HertsTrafficFlow.COLUMN_TO_LONGITUDE + " REAL,"
                    + HertsTrafficFlow.COLUMN_TIME + " TEXT,"
                    + HertsTrafficFlow.COLUMN_VEHICLE_FLOW + " REAL,"
                    + HertsTrafficFlow.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsTrafficFlow.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsTrafficFlow.COLUMN_ID + ","
                    + HertsTrafficFlow.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_FLOW_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestTrafficFlow.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsTrafficFlow.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsTrafficFlow.COLUMN_ID
                    + ", MAX("
                    + HertsTrafficFlow.COLUMN_CREATION_TIME + ") AS "
                    + HertsTrafficFlow.COLUMN_CREATION_TIME + " FROM "
                    + HertsTrafficFlow.TABLE_NAME + " GROUP BY "
                    + HertsTrafficFlow.COLUMN_ID + ") AS b ON a."
                    + HertsTrafficFlow.COLUMN_ID + "=b."
                    + HertsTrafficFlow.COLUMN_ID + " AND a."
                    + HertsTrafficFlow.COLUMN_CREATION_TIME + "=b."
                    + HertsTrafficFlow.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SCOOT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsTrafficScoot.TABLE_NAME + " ("
                    + HertsTrafficScoot._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HertsTrafficScoot.COLUMN_ID + " TEXT NOT NULL,"
                    + HertsTrafficScoot.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + HertsTrafficScoot.COLUMN_FROM_TYPE + " TEXT,"
                    + HertsTrafficScoot.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + HertsTrafficScoot.COLUMN_FROM_LATITUDE + " REAL,"
                    + HertsTrafficScoot.COLUMN_FROM_LONGITUDE + " REAL,"
                    + HertsTrafficScoot.COLUMN_TO_TYPE + " TEXT,"
                    + HertsTrafficScoot.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + HertsTrafficScoot.COLUMN_TO_LATITUDE + " REAL,"
                    + HertsTrafficScoot.COLUMN_TO_LONGITUDE + " REAL,"
                    + HertsTrafficScoot.COLUMN_TIME + " TEXT,"
                    + HertsTrafficScoot.COLUMN_CURRENT_FLOW + " REAL,"
                    + HertsTrafficScoot.COLUMN_AVERAGE_SPEED + " REAL,"
                    + HertsTrafficScoot.COLUMN_LINK_STATUS_TYPE + " REAL,"
                    + HertsTrafficScoot.COLUMN_LINK_STATUS + " REAL,"
                    + HertsTrafficScoot.COLUMN_LINK_TRAVEL_TIME + " REAL,"
                    + HertsTrafficScoot.COLUMN_CONGESTION_PERCENT + " REAL,"
                    + HertsTrafficScoot.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsTrafficScoot.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsTrafficScoot.COLUMN_ID + ","
                    + HertsTrafficScoot.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SCOOT_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestTrafficScoot.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsTrafficScoot.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsTrafficScoot.COLUMN_ID
                    + ", MAX("
                    + HertsTrafficScoot.COLUMN_CREATION_TIME + ") AS "
                    + HertsTrafficScoot.COLUMN_CREATION_TIME + " FROM "
                    + HertsTrafficScoot.TABLE_NAME + " GROUP BY "
                    + HertsTrafficScoot.COLUMN_ID + ") AS b ON a."
                    + HertsTrafficScoot.COLUMN_ID + "=b."
                    + HertsTrafficScoot.COLUMN_ID + " AND a."
                    + HertsTrafficScoot.COLUMN_CREATION_TIME + "=b."
                    + HertsTrafficScoot.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SPEED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsTrafficSpeed.TABLE_NAME + " ("
                    + HertsTrafficSpeed._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HertsTrafficSpeed.COLUMN_ID + " TEXT NOT NULL,"
                    + HertsTrafficSpeed.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + HertsTrafficSpeed.COLUMN_FROM_TYPE + " TEXT,"
                    + HertsTrafficSpeed.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + HertsTrafficSpeed.COLUMN_FROM_LATITUDE + " REAL,"
                    + HertsTrafficSpeed.COLUMN_FROM_LONGITUDE + " REAL,"
                    + HertsTrafficSpeed.COLUMN_TO_TYPE + " TEXT,"
                    + HertsTrafficSpeed.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + HertsTrafficSpeed.COLUMN_TO_LATITUDE + " REAL,"
                    + HertsTrafficSpeed.COLUMN_TO_LONGITUDE + " REAL,"
                    + HertsTrafficSpeed.COLUMN_TIME + " TEXT,"
                    + HertsTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED + " REAL,"
                    + HertsTrafficSpeed.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsTrafficSpeed.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsTrafficSpeed.COLUMN_ID + ","
                    + HertsTrafficSpeed.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SPEED_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestTrafficSpeed.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsTrafficSpeed.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsTrafficSpeed.COLUMN_ID
                    + ", MAX("
                    + HertsTrafficSpeed.COLUMN_CREATION_TIME + ") AS "
                    + HertsTrafficSpeed.COLUMN_CREATION_TIME + " FROM "
                    + HertsTrafficSpeed.TABLE_NAME + " GROUP BY "
                    + HertsTrafficSpeed.COLUMN_ID + ") AS b ON a."
                    + HertsTrafficSpeed.COLUMN_ID + "=b."
                    + HertsTrafficSpeed.COLUMN_ID + " AND a."
                    + HertsTrafficSpeed.COLUMN_CREATION_TIME + "=b."
                    + HertsTrafficSpeed.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsTrafficTravelTime.TABLE_NAME + " ("
                    + HertsTrafficTravelTime._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HertsTrafficTravelTime.COLUMN_ID + " TEXT NOT NULL,"
                    + HertsTrafficTravelTime.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + HertsTrafficTravelTime.COLUMN_FROM_TYPE + " TEXT,"
                    + HertsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + HertsTrafficTravelTime.COLUMN_FROM_LATITUDE + " REAL,"
                    + HertsTrafficTravelTime.COLUMN_FROM_LONGITUDE + " REAL,"
                    + HertsTrafficTravelTime.COLUMN_TO_TYPE + " TEXT,"
                    + HertsTrafficTravelTime.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + HertsTrafficTravelTime.COLUMN_TO_LATITUDE + " REAL,"
                    + HertsTrafficTravelTime.COLUMN_TO_LONGITUDE + " REAL,"
                    + HertsTrafficTravelTime.COLUMN_TIME + " TEXT,"
                    + HertsTrafficTravelTime.COLUMN_TRAVEL_TIME + " REAL,"
                    + HertsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + " REAL,"
                    + HertsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + " REAL,"
                    + HertsTrafficTravelTime.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsTrafficTravelTime.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsTrafficTravelTime.COLUMN_ID + ","
                    + HertsTrafficTravelTime.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestTrafficTravelTime.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsTrafficTravelTime.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsTrafficTravelTime.COLUMN_ID
                    + ", MAX("
                    + HertsTrafficTravelTime.COLUMN_CREATION_TIME + ") AS "
                    + HertsTrafficTravelTime.COLUMN_CREATION_TIME + " FROM "
                    + HertsTrafficTravelTime.TABLE_NAME + " GROUP BY "
                    + HertsTrafficTravelTime.COLUMN_ID + ") AS b ON a."
                    + HertsTrafficTravelTime.COLUMN_ID + "=b."
                    + HertsTrafficTravelTime.COLUMN_ID + " AND a."
                    + HertsTrafficTravelTime.COLUMN_CREATION_TIME + "=b."
                    + HertsTrafficTravelTime.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE TABLE IF NOT EXISTS " + HertsVariableMessageSign.TABLE_NAME + " ("
                    + HertsVariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + HertsVariableMessageSign.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + HertsVariableMessageSign.COLUMN_DESCRIPTION + " TEXT,"
                    + HertsVariableMessageSign.COLUMN_VMS_TYPE + " TEXT,"
                    + HertsVariableMessageSign.COLUMN_LATITUDE + " REAL,"
                    + HertsVariableMessageSign.COLUMN_LONGITUDE + " REAL,"
                    + HertsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER,"
                    + HertsVariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER,"
                    + HertsVariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT,"
                    + HertsVariableMessageSign.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + HertsVariableMessageSign.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + HertsVariableMessageSign.COLUMN_LOCATION_ID + ","
                    + HertsVariableMessageSign.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE VIEW IF NOT EXISTS " + HertsLatestVariableMessageSign.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + HertsVariableMessageSign.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + HertsVariableMessageSign.COLUMN_LOCATION_ID
                    + ", MAX("
                    + HertsVariableMessageSign.COLUMN_CREATION_TIME + ") AS "
                    + HertsVariableMessageSign.COLUMN_CREATION_TIME + " FROM "
                    + HertsVariableMessageSign.TABLE_NAME + " GROUP BY "
                    + HertsVariableMessageSign.COLUMN_LOCATION_ID + ") AS b ON a."
                    + HertsVariableMessageSign.COLUMN_LOCATION_ID + "=b."
                    + HertsVariableMessageSign.COLUMN_LOCATION_ID + " AND a."
                    + HertsVariableMessageSign.COLUMN_CREATION_TIME + "=b."
                    + HertsVariableMessageSign.COLUMN_CREATION_TIME + ";";

    private HertsContract() {
    }

    public static final class HertsCarPark implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_car_park";
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
    public static final class HertsLatestCarPark {
        public static final String TABLE_NAME = "herts_latest_car_park";
    }

    public static final class HertsEvent implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_event";
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

    public static final class HertsLatestEvent {
        public static final String TABLE_NAME = "herts_latest_event";
    }

    public static final class HertsRoadworks implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_roadworks";
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

    public static final class HertsLatestRoadworks {
        public static final String TABLE_NAME = "herts_latest_roadworks";
    }

    public static final class HertsTrafficFlow implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_traffic_flow";
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

    public static final class HertsLatestTrafficFlow {
        public static final String TABLE_NAME = "herts_latest_traffic_flow";
    }

    public static final class HertsTrafficScoot implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_traffic_scoot";
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

    public static final class HertsLatestTrafficScoot {
        public static final String TABLE_NAME = "herts_latest_traffic_scoot";
    }

    public static final class HertsTrafficSpeed implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_traffic_speed";
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

    public static final class HertsLatestTrafficSpeed {
        public static final String TABLE_NAME = "herts_latest_traffic_speed";
    }

    public static final class HertsTrafficTravelTime implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_traffic_travel_time";
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

    public static final class HertsLatestTrafficTravelTime {
        public static final String TABLE_NAME = "herts_latest_traffic_travel_time";
    }

    public static final class HertsVariableMessageSign implements CommonBaseColumns {
        public static final String TABLE_NAME = "herts_variable_message_sign";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_VMS_TYPE = "vms_type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
        public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
        public static final String COLUMN_VMS_LEGENDS = "vms_legends";
    }

    public static final class HertsLatestVariableMessageSign {
        public static final String TABLE_NAME = "herts_latest_variable_message_sign";
    }
}
