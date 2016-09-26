package net.uk.onetransport.android.county.bucks.provider;

import com.google.gson.annotations.Expose;

import net.uk.onetransport.android.county.bucks.events.Description;
import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;

public class BucksContract {

    // Table initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksCarPark.TABLE_NAME + " ("
                    + BucksCarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + " TEXT NOT NULL,"
                    + BucksCarPark.COLUMN_LATITUDE + " REAL,"
                    + BucksCarPark.COLUMN_LONGITUDE + " REAL,"
                    + BucksCarPark.COLUMN_OCCUPANCY + " REAL,"
                    + BucksCarPark.COLUMN_OCCUPANCY_TREND + " TEXT,"
                    + BucksCarPark.COLUMN_TOTAL_PARKING_CAPACITY + " REAL,"
                    + BucksCarPark.COLUMN_FILL_RATE + " REAL,"
                    + BucksCarPark.COLUMN_EXIT_RATE + " REAL,"
                    + BucksCarPark.COLUMN_ALMOST_FULL_INCREASING + " REAL,"
                    + BucksCarPark.COLUMN_ALMOST_FULL_DECREASING + " REAL,"
                    + BucksCarPark.COLUMN_FULL_DECREASING + " REAL,"
                    + BucksCarPark.COLUMN_FULL_INCREASING + " REAL,"
                    + BucksCarPark.COLUMN_STATUS + " TEXT,"
                    + BucksCarPark.COLUMN_STATUS_TIME + " TEXT,"
                    + BucksCarPark.COLUMN_QUEUING_TIME + " REAL,"
                    + BucksCarPark.COLUMN_PARKING_AREA_NAME + " TEXT,"
                    + BucksCarPark.COLUMN_ENTRANCE_FULL + " REAL,"
                    + BucksCarPark.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksCarPark.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                    + BucksCarPark.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_CAR_PARK_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestCarPark.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksCarPark.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksCarPark.COLUMN_CAR_PARK_IDENTITY
                    + ", MAX("
                    + BucksCarPark.COLUMN_CREATION_TIME + ") AS "
                    + BucksCarPark.COLUMN_CREATION_TIME + " FROM "
                    + BucksCarPark.TABLE_NAME + " GROUP BY "
                    + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + ") AS b ON a."
                    + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + "=b."
                    + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + " AND a."
                    + BucksCarPark.COLUMN_CREATION_TIME + "=b."
                    + BucksCarPark.COLUMN_CREATION_TIME + ";";
    // TODO    On hold for now while we check if this is correct.
//    public static final String CREATE_EVENT_TABLE =
//            "CREATE TABLE IF NOT EXISTS " + BucksEvent.TABLE_NAME + " ("
//                    + BucksEvent._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//    private String startOfPeriod;
//    private String endOfPeriod;
//    private String overallStartTime;
//    private String overallEndTime;
//    private Double latitude;
//    private Double longitude;
//    private Description description;
//    private String impactOnTraffic;
//    private String validityStatus;
//    private String cinId;
//    private Long creationTime;
//;
    public static final String CREATE_ROAD_WORKS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksRoadWorks.TABLE_NAME + " ("
                    + BucksRoadWorks.COLUMN_ID + " TEXT NOT NULL,"
                    + BucksRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT + " TEXT,"
                    + BucksRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE + " TEXT,"
                    + BucksRoadWorks.COLUMN_COMMENT + " TEXT,"
                    + BucksRoadWorks.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + BucksRoadWorks.COLUMN_LATITUDE + " REAL,"
                    + BucksRoadWorks.COLUMN_LONGITUDE + " REAL,"
                    + BucksRoadWorks.COLUMN_STATUS + " TEXT,"
                    + BucksRoadWorks.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + BucksRoadWorks.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + BucksRoadWorks.COLUMN_PERIODS + " TEXT,"
                    + BucksRoadWorks.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksRoadWorks.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksRoadWorks.COLUMN_ID + ","
                    + BucksRoadWorks.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_ROAD_WORKS_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestRoadWorks.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksRoadWorks.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksRoadWorks.COLUMN_ID
                    + ", MAX("
                    + BucksRoadWorks.COLUMN_CREATION_TIME + ") AS "
                    + BucksRoadWorks.COLUMN_CREATION_TIME + " FROM "
                    + BucksRoadWorks.TABLE_NAME + " GROUP BY "
                    + BucksRoadWorks.COLUMN_ID + ") AS b ON a."
                    + BucksRoadWorks.COLUMN_ID + "=b."
                    + BucksRoadWorks.COLUMN_ID + " AND a."
                    + BucksRoadWorks.COLUMN_CREATION_TIME + "=b."
                    + BucksRoadWorks.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_FLOW_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksTrafficFlow.TABLE_NAME + " ("
                    + BucksTrafficFlow._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksTrafficFlow.COLUMN_ID + " TEXT NOT NULL,"
                    + BucksTrafficFlow.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + BucksTrafficFlow.COLUMN_FROM_TYPE + " TEXT,"
                    + BucksTrafficFlow.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + BucksTrafficFlow.COLUMN_FROM_LATITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_FROM_LONGITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_TO_TYPE + " TEXT,"
                    + BucksTrafficFlow.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + BucksTrafficFlow.COLUMN_TO_LATITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_TO_LONGITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_TIME + " TEXT,"
                    + BucksTrafficFlow.COLUMN_VEHICLE_FLOW + " REAL,"
                    + BucksTrafficFlow.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksTrafficFlow.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksTrafficFlow.COLUMN_ID + ","
                    + BucksTrafficFlow.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_FLOW_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestTrafficFlow.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksTrafficFlow.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksTrafficFlow.COLUMN_ID
                    + ", MAX("
                    + BucksTrafficFlow.COLUMN_CREATION_TIME + ") AS "
                    + BucksTrafficFlow.COLUMN_CREATION_TIME + " FROM "
                    + BucksTrafficFlow.TABLE_NAME + " GROUP BY "
                    + BucksTrafficFlow.COLUMN_ID + ") AS b ON a."
                    + BucksTrafficFlow.COLUMN_ID + "=b."
                    + BucksTrafficFlow.COLUMN_ID + " AND a."
                    + BucksTrafficFlow.COLUMN_CREATION_TIME + "=b."
                    + BucksTrafficFlow.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_QUEUE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksTrafficQueue.TABLE_NAME + " ("
                    + BucksTrafficQueue._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksTrafficQueue.COLUMN_ID + " TEXT NOT NULL,"
                    + BucksTrafficQueue.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + BucksTrafficQueue.COLUMN_FROM_TYPE + " TEXT,"
                    + BucksTrafficQueue.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + BucksTrafficQueue.COLUMN_FROM_LATITUDE + " REAL,"
                    + BucksTrafficQueue.COLUMN_FROM_LONGITUDE + " REAL,"
                    + BucksTrafficQueue.COLUMN_TO_TYPE + " TEXT,"
                    + BucksTrafficQueue.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + BucksTrafficQueue.COLUMN_TO_LATITUDE + " REAL,"
                    + BucksTrafficQueue.COLUMN_TO_LONGITUDE + " REAL,"
                    + BucksTrafficQueue.COLUMN_TIME + " TEXT,"
                    + BucksTrafficQueue.COLUMN_SEVERITY + " REAL,"
                    + BucksTrafficQueue.COLUMN_PRESENT + " TEXT,"
                    + BucksTrafficQueue.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksTrafficQueue.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksTrafficQueue.COLUMN_ID + ","
                    + BucksTrafficQueue.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_QUEUE_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestTrafficQueue.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksTrafficQueue.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksTrafficQueue.COLUMN_ID
                    + ", MAX("
                    + BucksTrafficQueue.COLUMN_CREATION_TIME + ") AS "
                    + BucksTrafficQueue.COLUMN_CREATION_TIME + " FROM "
                    + BucksTrafficQueue.TABLE_NAME + " GROUP BY "
                    + BucksTrafficQueue.COLUMN_ID + ") AS b ON a."
                    + BucksTrafficQueue.COLUMN_ID + "=b."
                    + BucksTrafficQueue.COLUMN_ID + " AND a."
                    + BucksTrafficQueue.COLUMN_CREATION_TIME + "=b."
                    + BucksTrafficQueue.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SCOOT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksTrafficScoot.TABLE_NAME + " ("
                    + BucksTrafficScoot._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksTrafficScoot.COLUMN_ID + " TEXT NOT NULL,"
                    + BucksTrafficScoot.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + BucksTrafficScoot.COLUMN_FROM_TYPE + " TEXT,"
                    + BucksTrafficScoot.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + BucksTrafficScoot.COLUMN_FROM_LATITUDE + " REAL,"
                    + BucksTrafficScoot.COLUMN_FROM_LONGITUDE + " REAL,"
                    + BucksTrafficScoot.COLUMN_TO_TYPE + " TEXT,"
                    + BucksTrafficScoot.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + BucksTrafficScoot.COLUMN_TO_LATITUDE + " REAL,"
                    + BucksTrafficScoot.COLUMN_TO_LONGITUDE + " REAL,"
                    + BucksTrafficScoot.COLUMN_TIME + " TEXT,"
                    + BucksTrafficScoot.COLUMN_CURRENT_FLOW + " REAL,"
                    + BucksTrafficScoot.COLUMN_AVERAGE_SPEED + " REAL,"
                    + BucksTrafficScoot.COLUMN_LINK_STATUS_TYPE + " REAL,"
                    + BucksTrafficScoot.COLUMN_LINK_STATUS + " REAL,"
                    + BucksTrafficScoot.COLUMN_LINK_TRAVEL_TIME + " REAL,"
                    + BucksTrafficScoot.COLUMN_CONGESTION_PERCENT + " REAL,"
                    + BucksTrafficScoot.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksTrafficScoot.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksTrafficScoot.COLUMN_ID + ","
                    + BucksTrafficScoot.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SCOOT_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestTrafficScoot.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksTrafficScoot.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksTrafficScoot.COLUMN_ID
                    + ", MAX("
                    + BucksTrafficScoot.COLUMN_CREATION_TIME + ") AS "
                    + BucksTrafficScoot.COLUMN_CREATION_TIME + " FROM "
                    + BucksTrafficScoot.TABLE_NAME + " GROUP BY "
                    + BucksTrafficScoot.COLUMN_ID + ") AS b ON a."
                    + BucksTrafficScoot.COLUMN_ID + "=b."
                    + BucksTrafficScoot.COLUMN_ID + " AND a."
                    + BucksTrafficScoot.COLUMN_CREATION_TIME + "=b."
                    + BucksTrafficScoot.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_SPEED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksTrafficSpeed.TABLE_NAME + " ("
                    + BucksTrafficSpeed._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksTrafficSpeed.COLUMN_ID + " TEXT NOT NULL,"
                    + BucksTrafficSpeed.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + BucksTrafficSpeed.COLUMN_FROM_TYPE + " TEXT,"
                    + BucksTrafficSpeed.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + BucksTrafficSpeed.COLUMN_FROM_LATITUDE + " REAL,"
                    + BucksTrafficSpeed.COLUMN_FROM_LONGITUDE + " REAL,"
                    + BucksTrafficSpeed.COLUMN_TO_TYPE + " TEXT,"
                    + BucksTrafficSpeed.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + BucksTrafficSpeed.COLUMN_TO_LATITUDE + " REAL,"
                    + BucksTrafficSpeed.COLUMN_TO_LONGITUDE + " REAL,"
                    + BucksTrafficSpeed.COLUMN_TIME + " TEXT,"
                    + BucksTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED + " REAL,"
                    + BucksTrafficSpeed.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksTrafficSpeed.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksTrafficSpeed.COLUMN_ID + ","
                    + BucksTrafficSpeed.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_SPEED_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestTrafficSpeed.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksTrafficSpeed.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksTrafficSpeed.COLUMN_ID
                    + ", MAX("
                    + BucksTrafficSpeed.COLUMN_CREATION_TIME + ") AS "
                    + BucksTrafficSpeed.COLUMN_CREATION_TIME + " FROM "
                    + BucksTrafficSpeed.TABLE_NAME + " GROUP BY "
                    + BucksTrafficSpeed.COLUMN_ID + ") AS b ON a."
                    + BucksTrafficSpeed.COLUMN_ID + "=b."
                    + BucksTrafficSpeed.COLUMN_ID + " AND a."
                    + BucksTrafficSpeed.COLUMN_CREATION_TIME + "=b."
                    + BucksTrafficSpeed.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksTrafficTravelTime.TABLE_NAME + " ("
                    + BucksTrafficTravelTime._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksTrafficTravelTime.COLUMN_ID + " TEXT NOT NULL,"
                    + BucksTrafficTravelTime.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + BucksTrafficTravelTime.COLUMN_FROM_TYPE + " TEXT,"
                    + BucksTrafficTravelTime.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + BucksTrafficTravelTime.COLUMN_FROM_LATITUDE + " REAL,"
                    + BucksTrafficTravelTime.COLUMN_FROM_LONGITUDE + " REAL,"
                    + BucksTrafficTravelTime.COLUMN_TO_TYPE + " TEXT,"
                    + BucksTrafficTravelTime.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + BucksTrafficTravelTime.COLUMN_TO_LATITUDE + " REAL,"
                    + BucksTrafficTravelTime.COLUMN_TO_LONGITUDE + " REAL,"
                    + BucksTrafficTravelTime.COLUMN_TIME + " TEXT,"
                    + BucksTrafficTravelTime.COLUMN_TRAVEL_TIME + " REAL,"
                    + BucksTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME + " REAL,"
                    + BucksTrafficTravelTime.COLUMN_FREE_FLOW_SPEED + " REAL,"
                    + BucksTrafficTravelTime.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksTrafficTravelTime.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksTrafficTravelTime.COLUMN_ID + ","
                    + BucksTrafficTravelTime.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_TRAFFIC_TRAVEL_TIME_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestTrafficTravelTime.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksTrafficTravelTime.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksTrafficTravelTime.COLUMN_ID
                    + ", MAX("
                    + BucksTrafficTravelTime.COLUMN_CREATION_TIME + ") AS "
                    + BucksTrafficTravelTime.COLUMN_CREATION_TIME + " FROM "
                    + BucksTrafficTravelTime.TABLE_NAME + " GROUP BY "
                    + BucksTrafficTravelTime.COLUMN_ID + ") AS b ON a."
                    + BucksTrafficTravelTime.COLUMN_ID + "=b."
                    + BucksTrafficTravelTime.COLUMN_ID + " AND a."
                    + BucksTrafficTravelTime.COLUMN_CREATION_TIME + "=b."
                    + BucksTrafficTravelTime.COLUMN_CREATION_TIME + ";";
    public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksVariableMessageSign.TABLE_NAME + " ("
                    + BucksVariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksVariableMessageSign.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_DESCRIPTION + " TEXT,"
                    + BucksVariableMessageSign.COLUMN_VMS_TYPE + " TEXT,"
                    + BucksVariableMessageSign.COLUMN_LATITUDE + " REAL,"
                    + BucksVariableMessageSign.COLUMN_LONGITUDE + " REAL,"
                    + BucksVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER,"
                    + BucksVariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER,"
                    + BucksVariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT,"
                    + BucksVariableMessageSign.COLUMN_CIN_ID + " TEXT NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksVariableMessageSign.COLUMN_LOCATION_ID + ","
                    + BucksVariableMessageSign.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BucksLatestVariableMessageSign.TABLE_NAME + " AS "
                    + "SELECT a.* FROM " + BucksVariableMessageSign.TABLE_NAME + " AS a "
                    + "INNER JOIN (SELECT " + BucksVariableMessageSign.COLUMN_LOCATION_ID
                    + ", MAX("
                    + BucksVariableMessageSign.COLUMN_CREATION_TIME + ") AS "
                    + BucksVariableMessageSign.COLUMN_CREATION_TIME + " FROM "
                    + BucksVariableMessageSign.TABLE_NAME + " GROUP BY "
                    + BucksVariableMessageSign.COLUMN_LOCATION_ID + ") AS b ON a."
                    + BucksVariableMessageSign.COLUMN_LOCATION_ID + "=b."
                    + BucksVariableMessageSign.COLUMN_LOCATION_ID + " AND a."
                    + BucksVariableMessageSign.COLUMN_CREATION_TIME + "=b."
                    + BucksVariableMessageSign.COLUMN_CREATION_TIME + ";";

    private BucksContract() {
    }

    public static final class BucksCarPark implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_car_park";
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
    public static final class BucksLatestCarPark {
        public static final String TABLE_NAME = "bucks_latest_car_park";
    }

    public static final class BucksEvent implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_event";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_START_OF_PERIOD = "start_of_period";
        public static final String COLUMN_END_OF_PERIOD = "end_of_period";
        public static final String COLUMN_OVERALL_START_TIME = "overall_start_time";
        public static final String COLUMN_OVERALL_END_TIME = "overall_end_time";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_LANG = "lang";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_IMPACT_ON_TRAFFIC = "impact_on_traffic";
        public static final String COLUMN_VALIDITY_STATUS = "validity_status";
    }

    public static final class BucksLatestEvent {
        public static final String TABLE_NAME = "bucks_latest_event";
    }

    public static final class BucksRoadWorks implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_road_works";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_EFFECT_ON_ROAD_LAYOUT = "effect_on_road_layout";
        public static final String COLUMN_ROAD_MAINTENANCE_TYPE = "road_maintenance_type";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_IMPACT_ON_TRAFFIC = "impact_on_traffic";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_OVERALL_START_TIME = "overall_start_time";
        public static final String COLUMN_OVERALL_END_TIME = "overall_end_time";
        public static final String COLUMN_PERIODS = "periods";
    }

    public static final class BucksLatestRoadWorks {
        public static final String TABLE_NAME = "bucks_latest_road_works";
    }

    public static final class BucksTrafficFlow implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_traffic_flow";
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

    public static final class BucksLatestTrafficFlow {
        public static final String TABLE_NAME = "bucks_latest_traffic_flow";
    }

    public static final class BucksTrafficQueue implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_traffic_queue";
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

    public static final class BucksLatestTrafficQueue {
        public static final String TABLE_NAME = "bucks_latest_traffic_queue";
    }

    public static final class BucksTrafficScoot implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_traffic_scoot";
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

    public static final class BucksLatestTrafficScoot {
        public static final String TABLE_NAME = "bucks_latest_traffic_scoot";
    }

    public static final class BucksTrafficSpeed implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_traffic_speed";
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

    public static final class BucksLatestTrafficSpeed {
        public static final String TABLE_NAME = "bucks_latest_traffic_speed";
    }

    public static final class BucksTrafficTravelTime implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_traffic_travel_time";
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

    public static final class BucksLatestTrafficTravelTime {
        public static final String TABLE_NAME = "bucks_latest_traffic_travel_time";
    }

    public static final class BucksVariableMessageSign implements CommonBaseColumns {
        public static final String TABLE_NAME = "bucks_variable_message_sign";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_VMS_TYPE = "vms_type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
        public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
        public static final String COLUMN_VMS_LEGENDS = "vms_legends";
    }

    public static final class BucksLatestVariableMessageSign {
        public static final String TABLE_NAME = "bucks_latest_variable_message_sign";
    }
}
