package net.uk.onetransport.android.county.bucks.provider;

import android.provider.BaseColumns;

public class BucksContract {

    // Table initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CarPark.TABLE_NAME + " ("
                    + CarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CarPark.COLUMN_CAR_PARK_IDENTITY + " TEXT NOT NULL,"
                    + CarPark.COLUMN_FILL_RATE + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_EXIT_RATE + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_TOTAL_PARKING_CAPACITY + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_ALMOST_FULL_INCREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_ALMOST_FULL_DECREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_FULL_DECREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_FULL_INCREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_ENTRANCE_FULL + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_LATITUDE + " REAL NOT NULL,"
                    + CarPark.COLUMN_LONGITUDE + " REAL NOT NULL"
                    + ");";
    public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE TABLE IF NOT EXISTS " + VariableMessageSign.TABLE_NAME + " ("
                    + VariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + VariableMessageSign.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + VariableMessageSign.COLUMN_NAME + " TEXT NOT NULL,"
                    + VariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER NOT NULL,"
                    + VariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER NOT NULL,"
                    + VariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT NOT NULL,"
                    + VariableMessageSign.COLUMN_VMS_TYPE + " TEXT NOT NULL,"
                    + VariableMessageSign.COLUMN_LATITUDE + " REAL NOT NULL,"
                    + VariableMessageSign.COLUMN_LONGITUDE + " REAL NOT NULL,"
                    + VariableMessageSign.COLUMN_DESCRIPTOR + " TEXT NOT NULL,"
                    + VariableMessageSign.COLUMN_TPEG_DIRECTION + " TEXT NOT NULL"
                    + ");";
    public static final String CREATE_TRAFFIC_FLOW_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TrafficFlow.TABLE_NAME + " ("
                    + TrafficFlow._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TrafficFlow.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + TrafficFlow.COLUMN_FROM_DESCRIPTOR + " TEXT,"
                    + TrafficFlow.COLUMN_TO_DESCRIPTOR + " TEXT,"
                    + TrafficFlow.COLUMN_TPEG_DIRECTION + " TEXT,"
                    + TrafficFlow.COLUMN_FROM_LATITUDE + " REAL,"
                    + TrafficFlow.COLUMN_FROM_LONGITUDE + " REAL,"
                    + TrafficFlow.COLUMN_TO_LATITUDE + " REAL,"
                    + TrafficFlow.COLUMN_TO_LONGITUDE + " REAL,"
                    + TrafficFlow.COLUMN_VEHICLE_FLOW + " INTEGER,"
                    + TrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED + " REAL,"
                    + TrafficFlow.COLUMN_TRAVEL_TIME + " INTEGER,"
                    + TrafficFlow.COLUMN_FREE_FLOW_SPEED + " INTEGER,"
                    + TrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME + " INTEGER,"
                    + TrafficFlow.COLUMN_CONGESTION_PERCENT + " REAL,"
                    + TrafficFlow.COLUMN_CURRENT_FLOW + " REAL,"
                    + TrafficFlow.COLUMN_AVERAGE_SPEED + " REAL,"
                    + TrafficFlow.COLUMN_LINK_STATUS + " TEXT,"
                    + TrafficFlow.COLUMN_LINK_STATUS_TYPE + " TEXT,"
                    + TrafficFlow.COLUMN_LINK_TRAVEL_TIME + " TEXT,"
                    + TrafficFlow.COLUMN_OCCUPANCY + " TEXT," // TODO    Check types.
                    + TrafficFlow.COLUMN_QUEUE_PRESENT + " TEXT,"
                    + TrafficFlow.COLUMN_QUEUE_SEVERITY + " TEXT"
                    + ");";
    public static final String CREATE_ROAD_WORKS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + RoadWorks.TABLE_NAME + " ("
                    + RoadWorks.COLUMN_ID + " TEXT NOT NULL,"
                    + RoadWorks.COLUMN_COMMENT + " TEXT,"
                    + RoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT + " TEXT,"
                    + RoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE + " TEXT,"
                    + RoadWorks.COLUMN_IMPACT_ON_TRAFFIC + " TEXT,"
                    + RoadWorks.COLUMN_TYPE + " TEXT,"
                    + RoadWorks.COLUMN_STATUS + " TEXT,"
                    + RoadWorks.COLUMN_OVERALL_START_TIME + " TEXT,"
                    + RoadWorks.COLUMN_OVERALL_END_TIME + " TEXT,"
                    + RoadWorks.COLUMN_PERIODS + " TEXT,"
                    + RoadWorks.COLUMN_LOCATION_DESCRIPTION + " TEXT,"
                    + RoadWorks.COLUMN_LATITUDE + " REAL,"
                    + RoadWorks.COLUMN_LONGITUDE + " REAL"
                    + ");";

    private BucksContract() {
    }

    public static final class CarPark implements BaseColumns {
        public static final String TABLE_NAME = "car_park";
        public static final String COLUMN_CAR_PARK_IDENTITY = "car_park_identity";
        public static final String COLUMN_EXIT_RATE = "exit_rate";
        public static final String COLUMN_FILL_RATE = "fill_rate";
        public static final String COLUMN_TOTAL_PARKING_CAPACITY = "total_parking_capacity";
        public static final String COLUMN_ALMOST_FULL_INCREASING = "almost_full_increasing";
        public static final String COLUMN_ALMOST_FULL_DECREASING = "almost_full_decreasing";
        public static final String COLUMN_FULL_DECREASING = "full_decreasing";
        public static final String COLUMN_FULL_INCREASING = "full_increasing";
        public static final String COLUMN_ENTRANCE_FULL = "entrance_full";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
    }

    public static final class VariableMessageSign implements BaseColumns {
        public static final String TABLE_NAME = "variable_message_sign";
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

    public static final class TrafficFlow implements BaseColumns {
        public static final String TABLE_NAME = "traffic_flow";
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

    public static final class RoadWorks implements BaseColumns {
        public static final String TABLE_NAME = "road_works";
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
