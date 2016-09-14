package net.uk.onetransport.android.county.bucks.provider;

import android.provider.BaseColumns;

public class BucksContract {

    // Table initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksCarPark.TABLE_NAME + " ("
                    + BucksCarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + " TEXT,"
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
                    + BucksCarPark.COLUMN_CIN_ID + " TEXT,"
                    + BucksCarPark.COLUMN_CREATION_TIME + " INTEGER,"
                    + "UNIQUE ("
                    + BucksCarPark.COLUMN_CAR_PARK_IDENTITY + ","
                    + BucksCarPark.COLUMN_CIN_ID
                    + ") ON CONFLICT IGNORE);";
    public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksVariableMessageSign.TABLE_NAME + " ("
                    + BucksVariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksVariableMessageSign.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_NAME + " TEXT NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_VMS_TYPE + " TEXT NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_LATITUDE + " REAL NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_LONGITUDE + " REAL NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_DESCRIPTOR + " TEXT NOT NULL,"
                    + BucksVariableMessageSign.COLUMN_TPEG_DIRECTION + " TEXT NOT NULL"
                    + ");";
    public static final String CREATE_TRAFFIC_FLOW_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksTrafficFlow.TABLE_NAME + " ("
                    + BucksTrafficFlow._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BucksTrafficFlow.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + BucksTrafficFlow.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + BucksTrafficFlow.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + BucksTrafficFlow.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + BucksTrafficFlow.COLUMN_FROM_LATITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_FROM_LONGITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_TO_LATITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_TO_LONGITUDE + " REAL,"
                    + BucksTrafficFlow.COLUMN_VEHICLE_FLOW + " INTEGER,"
                    + BucksTrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED + " REAL,"
                    + BucksTrafficFlow.COLUMN_TRAVEL_TIME + " INTEGER,"
                    + BucksTrafficFlow.COLUMN_FREE_FLOW_SPEED + " INTEGER,"
                    + BucksTrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME + " INTEGER,"
                    + BucksTrafficFlow.COLUMN_CONGESTION_PERCENT + " REAL,"
                    + BucksTrafficFlow.COLUMN_CURRENT_FLOW + " REAL,"
                    + BucksTrafficFlow.COLUMN_AVERAGE_SPEED + " REAL,"
                    + BucksTrafficFlow.COLUMN_LINK_STATUS + " TEXT,"
                    + BucksTrafficFlow.COLUMN_LINK_STATUS_TYPE + " TEXT,"
                    + BucksTrafficFlow.COLUMN_LINK_TRAVEL_TIME + " TEXT,"
                    + BucksTrafficFlow.COLUMN_OCCUPANCY + " TEXT," // TODO    Check types.
                    + BucksTrafficFlow.COLUMN_QUEUE_PRESENT + " TEXT,"
                    + BucksTrafficFlow.COLUMN_QUEUE_SEVERITY + " TEXT"
                    + ");";
    public static final String CREATE_ROAD_WORKS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BucksRoadWorks.TABLE_NAME + " ("
                    + BucksRoadWorks.COLUMN_ID + " TEXT NOT NULL,"
                    + BucksRoadWorks.COLUMN_COMMENT + " TEXT,"
                    + BucksRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT + " TEXT,"
                    + BucksRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE + " TEXT,"
                    + BucksRoadWorks.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + BucksRoadWorks.COLUMN_TYPE + " TEXT,"
                    + BucksRoadWorks.COLUMN_STATUS + " TEXT,"
                    + BucksRoadWorks.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + BucksRoadWorks.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + BucksRoadWorks.COLUMN_PERIODS + " TEXT,"
                    + BucksRoadWorks.COLUMN_LOCATION_DESCRIPTION + " TEXT,"
                    + BucksRoadWorks.COLUMN_LATITUDE + " REAL,"
                    + BucksRoadWorks.COLUMN_LONGITUDE + " REAL"
                    + ");";

    private BucksContract() {
    }

    public static final class BucksCarPark implements BucksBaseColumns{
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

    public static final class BucksVariableMessageSign implements BaseColumns {
        public static final String TABLE_NAME = "bucks_variable_message_sign";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
        public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
        public static final String COLUMN_VMS_LEGENDS = "vms_legends";
        public static final String COLUMN_VMS_TYPE = "vms_type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_DESCRIPTOR = "descriptor";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
    }

    public static final class BucksTrafficFlow implements BaseColumns {
        public static final String TABLE_NAME = "bucks_traffic_flow";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
        public static final String COLUMN_VEHICLE_FLOW = "vehicle_flow";
        public static final String COLUMN_AVERAGE_VEHICLE_SPEED = "average_vehicle_speed";
        public static final String COLUMN_TRAVEL_TIME = "travel_time";
        public static final String COLUMN_FREE_FLOW_SPEED = "free_flow_speed";
        public static final String COLUMN_FREE_FLOW_TRAVEL_TIME = "free_flow_travel_time";
        public static final String COLUMN_CONGESTION_PERCENT = "congestion_percent";
        public static final String COLUMN_CURRENT_FLOW = "current_flow";
        public static final String COLUMN_AVERAGE_SPEED = "average_speed";
        public static final String COLUMN_LINK_STATUS = "link_status";
        public static final String COLUMN_LINK_STATUS_TYPE = "link_status_type";
        public static final String COLUMN_LINK_TRAVEL_TIME = "link_travel_time";
        public static final String COLUMN_OCCUPANCY = "occupancy";
        public static final String COLUMN_QUEUE_PRESENT = "queue_present";
        public static final String COLUMN_QUEUE_SEVERITY = "queue_severity";
    }

    public static final class BucksRoadWorks implements BaseColumns {
        public static final String TABLE_NAME = "bucks_road_works";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_EFFECT_ON_ROAD_LAYOUT = "effect_on_road_layout";
        public static final String COLUMN_ROAD_MAINTENANCE_TYPE = "road_maintenance_type";
        public static final String COLUMN_IMPACT_ON_TRAFFIC = "impact_on_traffic";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_OVERALL_START_TIME = "overall_start_time";
        public static final String COLUMN_OVERALL_END_TIME = "overall_end_time";
        public static final String COLUMN_PERIODS = "periods"; // TODO    Merge with pipes?
        public static final String COLUMN_LOCATION_DESCRIPTION = "location_description";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
    }
}
