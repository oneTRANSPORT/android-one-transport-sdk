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
    public static final String CREATE_VMS_LOCATION_TABLE =
            "CREATE TABLE IF NOT EXISTS " + VmsLocation.TABLE_NAME + " ("
                    + VmsLocation._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + VmsLocation.COLUMN_NAME + " TEXT NOT NULL,"
                    + VmsLocation.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + VmsLocation.COLUMN_LATITUDE + " REAL NOT NULL,"
                    + VmsLocation.COLUMN_LONGITUDE + " REAL NOT NULL,"
                    + VmsLocation.COLUMN_DESCRIPTOR + " TEXT NOT NULL,"
                    + VmsLocation.COLUMN_TPEG_DIRECTION + " TEXT NOT NULL"
                    + ");";
    public static final String CREATE_SEGMENT_LOCATION_TABLE =
            "CREATE TABLE IF NOT EXISTS " + SegmentLocation.TABLE_NAME + " ("
                    + SegmentLocation._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + SegmentLocation.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
                    + SegmentLocation.COLUMN_FROM_LATITUDE + " REAL NOT NULL,"
                    + SegmentLocation.COLUMN_FROM_LONGITUDE + " REAL NOT NULL,"
                    + SegmentLocation.COLUMN_TO_LATITUDE + " REAL NOT NULL,"
                    + SegmentLocation.COLUMN_TO_LONGITUDE + " REAL NOT NULL,"
                    + SegmentLocation.COLUMN_FROM_DESCRIPTOR + " TEXT NOT NULL,"
                    + SegmentLocation.COLUMN_TO_DESCRIPTOR + " TEXT NOT NULL,"
                    + SegmentLocation.COLUMN_TPEG_DIRECTION + " TEXT NOT NULL"
                    + ");";
    public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
            "CREATE TABLE IF NOT EXISTS " + VariableMessageSign.TABLE_NAME + " ("
                    + VariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + VariableMessageSign.COLUMN_LOCATION_REFERENCE + " TEXT NOT NULL,"
                    + VariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER NOT NULL,"
                    + VariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER NOT NULL,"
                    + VariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT NOT NULL,"
                    + VariableMessageSign.COLUMN_VMS_TYPE + " TEXT NOT NULL"
                    + ");";
    public static final String CREATE_TRAFFIC_FLOW_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TrafficFlow.TABLE_NAME + " ("
                    + TrafficFlow._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TrafficFlow.COLUMN_LOCATION_REFERENCE + " TEXT NOT NULL,"
                    + TrafficFlow.COLUMN_VEHICLE_FLOW + " INTEGER NOT NULL,"
                    + TrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED + " REAL NOT NULL,"
                    + TrafficFlow.COLUMN_TRAVEL_TIME + " INTEGER NOT NULL,"
                    + TrafficFlow.COLUMN_FREE_FLOW_SPEED + " INTEGER NOT NULL,"
                    + TrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME + " INTEGER NOT NULL"
                    + ");";
    // Views using location as a key.
    public static final String CREATE_VMS_LOCATION_VIEW =
            "CREATE VIEW IF NOT EXISTS " + VmsJoinLocation.TABLE_NAME + " AS SELECT "
                    + VariableMessageSign.TABLE_NAME + "." + VariableMessageSign._ID
                    + " as " + VariableMessageSign._ID + ","
                    + VmsJoinLocation.COLUMN_NUMBER_OF_CHARACTERS + ","
                    + VmsJoinLocation.COLUMN_NUMBER_OF_ROWS + ","
                    + VmsJoinLocation.COLUMN_VMS_LEGENDS + ","
                    + VmsJoinLocation.COLUMN_VMS_TYPE + ","
                    + VmsJoinLocation.COLUMN_LATITUDE + ","
                    + VmsJoinLocation.COLUMN_LONGITUDE + ","
                    + VmsJoinLocation.COLUMN_DESCRIPTOR + ","
                    + VmsJoinLocation.COLUMN_TPEG_DIRECTION
                    + " FROM " + VariableMessageSign.TABLE_NAME + "," + VmsLocation.TABLE_NAME
                    + " WHERE " + VariableMessageSign.COLUMN_LOCATION_REFERENCE
                    + "=" + VmsLocation.COLUMN_LOCATION_ID + ";";
    public static final String CREATE_TRAFFIC_FLOW_LOCATION_VIEW =
            "CREATE VIEW IF NOT EXISTS " + TrafficFlowJoinLocation.TABLE_NAME + " AS SELECT "
                    + TrafficFlow.TABLE_NAME + "." + TrafficFlow._ID
                    + " as " + TrafficFlow._ID + ","
                    + TrafficFlowJoinLocation.COLUMN_VEHICLE_FLOW + ","
                    + TrafficFlowJoinLocation.COLUMN_AVERAGE_VEHICLE_SPEED + ","
                    + TrafficFlowJoinLocation.COLUMN_TRAVEL_TIME + ","
                    + TrafficFlowJoinLocation.COLUMN_FREE_FLOW_SPEED + ","
                    + TrafficFlowJoinLocation.COLUMN_FREE_FLOW_TRAVEL_TIME + ","
                    + TrafficFlowJoinLocation.COLUMN_FROM_LATITUDE + ","
                    + TrafficFlowJoinLocation.COLUMN_FROM_LONGITUDE + ","
                    + TrafficFlowJoinLocation.COLUMN_TO_LATITUDE + ","
                    + TrafficFlowJoinLocation.COLUMN_TO_LONGITUDE + ","
                    + TrafficFlowJoinLocation.COLUMN_FROM_DESCRIPTOR + ","
                    + TrafficFlowJoinLocation.COLUMN_TO_DESCRIPTOR + ","
                    + TrafficFlowJoinLocation.COLUMN_TPEG_DIRECTION
                    + " FROM " + TrafficFlow.TABLE_NAME + "," + SegmentLocation.TABLE_NAME
                    + " WHERE " + TrafficFlow.COLUMN_LOCATION_REFERENCE
                    + "=" + SegmentLocation.COLUMN_LOCATION_ID + ";";

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

    public static final class VmsLocation implements BaseColumns {
        public static final String TABLE_NAME = "vms_location";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_DESCRIPTOR = "descriptor";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
    }

    public static final class SegmentLocation implements BaseColumns {
        public static final String TABLE_NAME = "segment_location";
        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
    }

    public static final class VariableMessageSign implements BaseColumns {
        public static final String TABLE_NAME = "variable_message_sign";
        public static final String COLUMN_LOCATION_REFERENCE = "location_reference";
        public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
        public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
        // Store the rows of data as pipe-separated text in one row.
        public static final String COLUMN_VMS_LEGENDS = "vms_legends";
        public static final String COLUMN_VMS_TYPE = "vms_type";
    }

    public static final class TrafficFlow implements BaseColumns {
        public static final String TABLE_NAME = "traffic_flow";
        public static final String COLUMN_LOCATION_REFERENCE = "location_reference";
        public static final String COLUMN_VEHICLE_FLOW = "vehicle_flow";
        public static final String COLUMN_AVERAGE_VEHICLE_SPEED = "average_vehicle_speed";
        public static final String COLUMN_TRAVEL_TIME = "travel_time";
        public static final String COLUMN_FREE_FLOW_SPEED = "free_flow_speed";
        public static final String COLUMN_FREE_FLOW_TRAVEL_TIME = "free_flow_travel_time";
    }

    public static final class VmsJoinLocation implements BaseColumns {
        public static final String TABLE_NAME = "vms_join_location";
        public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
        public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
        public static final String COLUMN_VMS_LEGENDS = "vms_legends";
        public static final String COLUMN_VMS_TYPE = "vms_type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_DESCRIPTOR = "descriptor";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
    }

    public static final class TrafficFlowJoinLocation implements BaseColumns {
        public static final String TABLE_NAME = "traffic_flow_join_location";
        public static final String COLUMN_VEHICLE_FLOW = "vehicle_flow";
        public static final String COLUMN_AVERAGE_VEHICLE_SPEED = "average_vehicle_speed";
        public static final String COLUMN_TRAVEL_TIME = "travel_time";
        public static final String COLUMN_FREE_FLOW_SPEED = "free_flow_speed";
        public static final String COLUMN_FREE_FLOW_TRAVEL_TIME = "free_flow_travel_time";
        public static final String COLUMN_FROM_LATITUDE = "from_latitude";
        public static final String COLUMN_FROM_LONGITUDE = "from_longitude";
        public static final String COLUMN_TO_LATITUDE = "to_latitude";
        public static final String COLUMN_TO_LONGITUDE = "to_longitude";
        public static final String COLUMN_FROM_DESCRIPTOR = "from_descriptor";
        public static final String COLUMN_TO_DESCRIPTOR = "to_descriptor";
        public static final String COLUMN_TPEG_DIRECTION = "tpeg_direction";
    }
}
