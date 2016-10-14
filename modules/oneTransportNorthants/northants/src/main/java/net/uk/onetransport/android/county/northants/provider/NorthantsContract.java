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
    public static final String CREATE_ROADWORKS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NorthantsRoadworks.TABLE_NAME + " ("
                    + NorthantsRoadworks.COLUMN_ID + " TEXT NOT NULL,"
                    + NorthantsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT + " TEXT,"
                    + NorthantsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE + " TEXT,"
                    + NorthantsRoadworks.COLUMN_COMMENT + " TEXT,"
                    + NorthantsRoadworks.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + NorthantsRoadworks.COLUMN_LATITUDE + " REAL,"
                    + NorthantsRoadworks.COLUMN_LONGITUDE + " REAL,"
                    + NorthantsRoadworks.COLUMN_VALIDITY_STATUS + " TEXT,"
                    + NorthantsRoadworks.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + NorthantsRoadworks.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + NorthantsRoadworks.COLUMN_START_OF_PERIOD + " TEXT,"
                    + NorthantsRoadworks.COLUMN_END_OF_PERIOD + " TEXT,"
                    + NorthantsRoadworks.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + NorthantsRoadworks.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + NorthantsRoadworks.COLUMN_ID + ","
                    + NorthantsRoadworks.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_ROADWORKS_TABLE =
            "CREATE VIEW IF NOT EXISTS " + NorthantsLatestRoadworks.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + NorthantsRoadworks.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + NorthantsRoadworks.COLUMN_ID
                    + ", MAX("
                    + NorthantsRoadworks.COLUMN_CREATION_TIME + ") AS "
                    + NorthantsRoadworks.COLUMN_CREATION_TIME + " FROM "
                    + NorthantsRoadworks.TABLE_NAME + " GROUP BY "
                    + NorthantsRoadworks.COLUMN_ID + ") AS b ON a."
                    + NorthantsRoadworks.COLUMN_ID + "=b."
                    + NorthantsRoadworks.COLUMN_ID + " AND a."
                    + NorthantsRoadworks.COLUMN_CREATION_TIME + "=b."
                    + NorthantsRoadworks.COLUMN_CREATION_TIME + ";";
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

    public static final class NorthantsRoadworks implements CommonBaseColumns {
        public static final String TABLE_NAME = "northants_roadworks";
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

    public static final class NorthantsLatestRoadworks {
        public static final String TABLE_NAME = "northants_latest_roadworks";
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
