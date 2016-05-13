package net.uk.onetransport.android.county.bucks.provider;

import android.provider.BaseColumns;

public class BucksContract {

    // Initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CarPark.TABLE_NAME + " ("
                    + CarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CarPark.COLUMN_CAR_PARK_IDENTITY + " TEXT NOT NULL,"
                    + CarPark.COLUMN_TOTAL_PARKING_CAPACITY + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_ALMOST_FULL_INCREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_ALMOST_FULL_DECREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_FULL_DECREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_FULL_INCREASING + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_ENTRANCE_FULL + " INTEGER NOT NULL,"
                    + CarPark.COLUMN_RADIUS + " REAL NOT NULL,"
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

    private BucksContract() {
    }

    public static final class CarPark implements BaseColumns {
        public static final String TABLE_NAME = "car_park";
        public static final String COLUMN_CAR_PARK_IDENTITY = "car_park_identity";
        public static final String COLUMN_TOTAL_PARKING_CAPACITY = "total_parking_capacity";
        public static final String COLUMN_ALMOST_FULL_INCREASING = "almost_full_increasing";
        public static final String COLUMN_ALMOST_FULL_DECREASING = "almost_full_decreasing";
        public static final String COLUMN_FULL_DECREASING = "full_decreasing";
        public static final String COLUMN_FULL_INCREASING = "full_increasing";
        public static final String COLUMN_ENTRANCE_FULL = "entrance_full";
        public static final String COLUMN_RADIUS = "radius";
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
}
