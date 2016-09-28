package net.uk.onetransport.android.county.northants.provider;

import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;

public class NorthantsContract {

    // Table initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsCarPark.TABLE_NAME + " ("
                    + NorthantsCarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY + " TEXT NOT NULL,"
                    + NorthantsCarPark.COLUMN_LATITUDE + " REAL,"
                    + NorthantsCarPark.COLUMN_LONGITUDE + " REAL,"
                    + NorthantsCarPark.COLUMN_OCCUPANCY + " REAL,"
                    + NorthantsCarPark.COLUMN_OCCUPANCY_TREND + " TEXT,"
                    + NorthantsCarPark.COLUMN_TOTAL_PARKING_CAPACITY + " REAL,"
                    + NorthantsCarPark.COLUMN_FILL_RATE + " REAL,"
                    + NorthantsCarPark.COLUMN_EXIT_RATE + " REAL,"
                    + NorthantsCarPark.COLUMN_ALMOST_FULL_INCREASING + " REAL,"
                    + NorthantsCarPark.COLUMN_ALMOST_FULL_DECREASING + " REAL,"
                    + NorthantsCarPark.COLUMN_FULL_DECREASING + " REAL,"
                    + NorthantsCarPark.COLUMN_FULL_INCREASING + " REAL,"
                    + NorthantsCarPark.COLUMN_STATUS + " TEXT,"
                    + NorthantsCarPark.COLUMN_STATUS_TIME + " TEXT,"
                    + NorthantsCarPark.COLUMN_QUEUING_TIME + " REAL,"
                    + NorthantsCarPark.COLUMN_PARKING_AREA_NAME + " TEXT,"
                    + NorthantsCarPark.COLUMN_ENTRANCE_FULL + " REAL,"
                    + NorthantsCarPark.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsCarPark.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                    + NorthantsCarPark.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_CAR_PARK_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestCarPark.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsCarPark.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY
                    + ", MAX("
                    + NorthantsCarPark.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsCarPark.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsCarPark.TABLE_NAME + " GROUP BY "
                    + NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY + ") AS b ON a."
                    + NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY + "=b."
                    + NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY + " AND a."
                    + NorthantsCarPark.COLUMN_CREATION_TIME + "=b."
                    + NorthantsCarPark.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_EVENT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsEvent.TABLE_NAME + " ("
                    + NorthantsEvent._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsEvent.COLUMN_ID + " TEXT,"
                    + NorthantsEvent.COLUMN_START_OF_PERIOD + " TEXT,"
                    + NorthantsEvent.COLUMN_END_OF_PERIOD + " TEXT,"
                    + NorthantsEvent.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + NorthantsEvent.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + NorthantsEvent.COLUMN_LATITUDE + " REAL,"
                    + NorthantsEvent.COLUMN_LONGITUDE + " REAL,"
                    + NorthantsEvent.COLUMN_DESCRIPTION + " TEXT,"
                    + NorthantsEvent.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + NorthantsEvent.COLUMN_VALIDITY_STATUS + " TEXT,"
                    + NorthantsEvent.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsEvent.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsEvent.COLUMN_ID + ","
                    + NorthantsEvent.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_EVENT_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestEvent.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsEvent.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsEvent.COLUMN_ID
                    + ", MAX("
                    + NorthantsEvent.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsEvent.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsEvent.TABLE_NAME + " GROUP BY "
                    + NorthantsEvent.COLUMN_ID + ") AS b ON a."
                    + NorthantsEvent.COLUMN_ID + "=b."
                    + NorthantsEvent.COLUMN_ID + " AND a."
                    + NorthantsEvent.COLUMN_CREATION_TIME + "=b."
                    + NorthantsEvent.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_ROAD_WORKS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsRoadWorks.TABLE_NAME + " ("
                    + NorthantsRoadWorks.COLUMN_ID + " TEXT NOT NULL,"
                    + NorthantsRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_COMMENT + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_LATITUDE + " REAL,"
                    + NorthantsRoadWorks.COLUMN_LONGITUDE + " REAL,"
                    + NorthantsRoadWorks.COLUMN_VALIDITY_STATUS + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_START_OF_PERIOD + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_END_OF_PERIOD + " TEXT,"
                    + NorthantsRoadWorks.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsRoadWorks.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsRoadWorks.COLUMN_ID + ","
                    + NorthantsRoadWorks.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_ROAD_WORKS_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestRoadWorks.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsRoadWorks.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsRoadWorks.COLUMN_ID
                    + ", MAX("
                    + NorthantsRoadWorks.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsRoadWorks.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsRoadWorks.TABLE_NAME + " GROUP BY "
                    + NorthantsRoadWorks.COLUMN_ID + ") AS b ON a."
                    + NorthantsRoadWorks.COLUMN_ID + "=b."
                    + NorthantsRoadWorks.COLUMN_ID + " AND a."
                    + NorthantsRoadWorks.COLUMN_CREATION_TIME + "=b."
                    + NorthantsRoadWorks.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_FLOW_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsTrafficFlow.TABLE_NAME + " ("
                    + NorthantsTrafficFlow._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsTrafficFlow.COLUMN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficFlow.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + NorthantsTrafficFlow.COLUMN_FROM_TYPE + " TEXT,"
                    + NorthantsTrafficFlow.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficFlow.COLUMN_FROM_LATITUDE + " REAL,"
                    + NorthantsTrafficFlow.COLUMN_FROM_LONGITUDE + " REAL,"
                    + NorthantsTrafficFlow.COLUMN_TO_TYPE + " TEXT,"
                    + NorthantsTrafficFlow.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficFlow.COLUMN_TO_LATITUDE + " REAL,"
                    + NorthantsTrafficFlow.COLUMN_TO_LONGITUDE + " REAL,"
                    + NorthantsTrafficFlow.COLUMN_TIME + " TEXT,"
                    + NorthantsTrafficFlow.COLUMN_VEHICLE_FLOW + " REAL,"
                    + NorthantsTrafficFlow.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficFlow.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsTrafficFlow.COLUMN_ID + ","
                    + NorthantsTrafficFlow.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_FLOW_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestTrafficFlow.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsTrafficFlow.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsTrafficFlow.COLUMN_ID
                    + ", MAX("
                    + NorthantsTrafficFlow.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsTrafficFlow.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsTrafficFlow.TABLE_NAME + " GROUP BY "
                    + NorthantsTrafficFlow.COLUMN_ID + ") AS b ON a."
                    + NorthantsTrafficFlow.COLUMN_ID + "=b."
                    + NorthantsTrafficFlow.COLUMN_ID + " AND a."
                    + NorthantsTrafficFlow.COLUMN_CREATION_TIME + "=b."
                    + NorthantsTrafficFlow.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_QUEUE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsTrafficQueue.TABLE_NAME + " ("
                    + NorthantsTrafficQueue._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsTrafficQueue.COLUMN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficQueue.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + NorthantsTrafficQueue.COLUMN_FROM_TYPE + " TEXT,"
                    + NorthantsTrafficQueue.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficQueue.COLUMN_FROM_LATITUDE + " REAL,"
                    + NorthantsTrafficQueue.COLUMN_FROM_LONGITUDE + " REAL,"
                    + NorthantsTrafficQueue.COLUMN_TO_TYPE + " TEXT,"
                    + NorthantsTrafficQueue.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficQueue.COLUMN_TO_LATITUDE + " REAL,"
                    + NorthantsTrafficQueue.COLUMN_TO_LONGITUDE + " REAL,"
                    + NorthantsTrafficQueue.COLUMN_TIME + " TEXT,"
                    + NorthantsTrafficQueue.COLUMN_SEVERITY + " REAL,"
                    + NorthantsTrafficQueue.COLUMN_PRESENT + " TEXT,"
                    + NorthantsTrafficQueue.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficQueue.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsTrafficQueue.COLUMN_ID + ","
                    + NorthantsTrafficQueue.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_QUEUE_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestTrafficQueue.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsTrafficQueue.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsTrafficQueue.COLUMN_ID
                    + ", MAX("
                    + NorthantsTrafficQueue.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsTrafficQueue.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsTrafficQueue.TABLE_NAME + " GROUP BY "
                    + NorthantsTrafficQueue.COLUMN_ID + ") AS b ON a."
                    + NorthantsTrafficQueue.COLUMN_ID + "=b."
                    + NorthantsTrafficQueue.COLUMN_ID + " AND a."
                    + NorthantsTrafficQueue.COLUMN_CREATION_TIME + "=b."
                    + NorthantsTrafficQueue.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SCOOT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsTrafficScoot.TABLE_NAME + " ("
                    + NorthantsTrafficScoot._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsTrafficScoot.COLUMN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficScoot.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + NorthantsTrafficScoot.COLUMN_FROM_TYPE + " TEXT,"
                    + NorthantsTrafficScoot.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficScoot.COLUMN_FROM_LATITUDE + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_FROM_LONGITUDE + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_TO_TYPE + " TEXT,"
                    + NorthantsTrafficScoot.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficScoot.COLUMN_TO_LATITUDE + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_TO_LONGITUDE + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_TIME + " TEXT,"
                    + NorthantsTrafficScoot.COLUMN_CURRENT_FLOW + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_AVERAGE_SPEED + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_LINK_STATUS_TYPE + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_LINK_STATUS + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_LINK_TRAVEL_TIME + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_CONGESTION_PERCENT + " REAL,"
                    + NorthantsTrafficScoot.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficScoot.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsTrafficScoot.COLUMN_ID + ","
                    + NorthantsTrafficScoot.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SCOOT_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestTrafficScoot.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsTrafficScoot.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsTrafficScoot.COLUMN_ID
                    + ", MAX("
                    + NorthantsTrafficScoot.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsTrafficScoot.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsTrafficScoot.TABLE_NAME + " GROUP BY "
                    + NorthantsTrafficScoot.COLUMN_ID + ") AS b ON a."
                    + NorthantsTrafficScoot.COLUMN_ID + "=b."
                    + NorthantsTrafficScoot.COLUMN_ID + " AND a."
                    + NorthantsTrafficScoot.COLUMN_CREATION_TIME + "=b."
                    + NorthantsTrafficScoot.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SPEED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsTrafficSpeed.TABLE_NAME + " ("
                    + NorthantsTrafficSpeed._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsTrafficSpeed.COLUMN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficSpeed.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + NorthantsTrafficSpeed.COLUMN_FROM_TYPE + " TEXT,"
                    + NorthantsTrafficSpeed.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficSpeed.COLUMN_FROM_LATITUDE + " REAL,"
                    + NorthantsTrafficSpeed.COLUMN_FROM_LONGITUDE + " REAL,"
                    + NorthantsTrafficSpeed.COLUMN_TO_TYPE + " TEXT,"
                    + NorthantsTrafficSpeed.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficSpeed.COLUMN_TO_LATITUDE + " REAL,"
                    + NorthantsTrafficSpeed.COLUMN_TO_LONGITUDE + " REAL,"
                    + NorthantsTrafficSpeed.COLUMN_TIME + " TEXT,"
                    + NorthantsTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED + " REAL,"
                    + NorthantsTrafficSpeed.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficSpeed.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsTrafficSpeed.COLUMN_ID + ","
                    + NorthantsTrafficSpeed.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SPEED_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestTrafficSpeed.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsTrafficSpeed.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsTrafficSpeed.COLUMN_ID
                    + ", MAX("
                    + NorthantsTrafficSpeed.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsTrafficSpeed.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsTrafficSpeed.TABLE_NAME + " GROUP BY "
                    + NorthantsTrafficSpeed.COLUMN_ID + ") AS b ON a."
                    + NorthantsTrafficSpeed.COLUMN_ID + "=b."
                    + NorthantsTrafficSpeed.COLUMN_ID + " AND a."
                    + NorthantsTrafficSpeed.COLUMN_CREATION_TIME + "=b."
                    + NorthantsTrafficSpeed.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsTrafficTravelTime.TABLE_NAME + " ("
                    + NorthantsTrafficTravelTime._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsTrafficTravelTime.COLUMN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficTravelTime.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + NorthantsTrafficTravelTime.COLUMN_FROM_TYPE + " TEXT,"
                    + NorthantsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficTravelTime.COLUMN_FROM_LATITUDE + " REAL,"
                    + NorthantsTrafficTravelTime.COLUMN_FROM_LONGITUDE + " REAL,"
                    + NorthantsTrafficTravelTime.COLUMN_TO_TYPE + " TEXT,"
                    + NorthantsTrafficTravelTime.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + NorthantsTrafficTravelTime.COLUMN_TO_LATITUDE + " REAL,"
                    + NorthantsTrafficTravelTime.COLUMN_TO_LONGITUDE + " REAL,"
                    + NorthantsTrafficTravelTime.COLUMN_TIME + " TEXT,"
                    + NorthantsTrafficTravelTime.COLUMN_TRAVEL_TIME + " REAL,"
                    + NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + " REAL,"
                    + NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + " REAL,"
                    + NorthantsTrafficTravelTime.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsTrafficTravelTime.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsTrafficTravelTime.COLUMN_ID + ","
                    + NorthantsTrafficTravelTime.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestTrafficTravelTime.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsTrafficTravelTime.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsTrafficTravelTime.COLUMN_ID
                    + ", MAX("
                    + NorthantsTrafficTravelTime.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsTrafficTravelTime.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsTrafficTravelTime.TABLE_NAME + " GROUP BY "
                    + NorthantsTrafficTravelTime.COLUMN_ID + ") AS b ON a."
                    + NorthantsTrafficTravelTime.COLUMN_ID + "=b."
                    + NorthantsTrafficTravelTime.COLUMN_ID + " AND a."
                    + NorthantsTrafficTravelTime.COLUMN_CREATION_TIME + "=b."
                    + NorthantsTrafficTravelTime.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsVariableMessageSign.TABLE_NAME + " ("
                    + NorthantsVariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + NorthantsVariableMessageSign.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + NorthantsVariableMessageSign.COLUMN_DESCRIPTION + " TEXT,"
                    + NorthantsVariableMessageSign.COLUMN_VMS_TYPE + " TEXT,"
                    + NorthantsVariableMessageSign.COLUMN_LATITUDE + " REAL,"
                    + NorthantsVariableMessageSign.COLUMN_LONGITUDE + " REAL,"
                    + NorthantsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER,"
                    + NorthantsVariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER,"
                    + NorthantsVariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT,"
                    + NorthantsVariableMessageSign.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsVariableMessageSign.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsVariableMessageSign.COLUMN_LOCATION_ID + ","
                    + NorthantsVariableMessageSign.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestVariableMessageSign.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsVariableMessageSign.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsVariableMessageSign.COLUMN_LOCATION_ID
                    + ", MAX("
                    + NorthantsVariableMessageSign.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsVariableMessageSign.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsVariableMessageSign.TABLE_NAME + " GROUP BY "
                    + NorthantsVariableMessageSign.COLUMN_LOCATION_ID + ") AS b ON a."
                    + NorthantsVariableMessageSign.COLUMN_LOCATION_ID + "=b."
                    + NorthantsVariableMessageSign.COLUMN_LOCATION_ID + " AND a."
                    + NorthantsVariableMessageSign.COLUMN_CREATION_TIME + "=b."
                    + NorthantsVariableMessageSign.COLUMN_CREATION_TIME + ";";

    private NorthantsContract() {
    }

    public static final class NorthantsCarPark implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_car_park";
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
    public static final class NorthantsLatestCarPark {
        public static final String TABLE_NAME = "northants_latest_car_park";
    }

    public static final class NorthantsEvent implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_event";
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

    public static final class NorthantsLatestEvent {
        public static final String TABLE_NAME = "northants_latest_event";
    }

    public static final class NorthantsRoadWorks implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_road_works";
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

    public static final class NorthantsLatestRoadWorks {
        public static final String TABLE_NAME = "northants_latest_road_works";
    }

    public static final class NorthantsTrafficFlow implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_traffic_flow";
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

    public static final class NorthantsLatestTrafficFlow {
        public static final String TABLE_NAME = "northants_latest_traffic_flow";
    }

    public static final class NorthantsTrafficQueue implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_traffic_queue";
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

    public static final class NorthantsLatestTrafficQueue {
        public static final String TABLE_NAME = "northants_latest_traffic_queue";
    }

    public static final class NorthantsTrafficScoot implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_traffic_scoot";
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

    public static final class NorthantsLatestTrafficScoot {
        public static final String TABLE_NAME = "northants_latest_traffic_scoot";
    }

    public static final class NorthantsTrafficSpeed implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_traffic_speed";
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

    public static final class NorthantsLatestTrafficSpeed {
        public static final String TABLE_NAME = "northants_latest_traffic_speed";
    }

    public static final class NorthantsTrafficTravelTime implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_traffic_travel_time";
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

    public static final class NorthantsLatestTrafficTravelTime {
        public static final String TABLE_NAME = "northants_latest_traffic_travel_time";
    }

    public static final class NorthantsVariableMessageSign implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_variable_message_sign";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_VMS_TYPE = "vms_type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
        public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
        public static final String COLUMN_VMS_LEGENDS = "vms_legends";
    }

    public static final class NorthantsLatestVariableMessageSign {
        public static final String TABLE_NAME = "northants_latest_variable_message_sign";
    }
}
