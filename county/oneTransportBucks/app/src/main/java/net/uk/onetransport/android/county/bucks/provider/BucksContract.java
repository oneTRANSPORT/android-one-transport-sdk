package net.uk.onetransport.android.county.bucks.provider;

import android.provider.BaseColumns;

public class BucksContract {

    // Initialisation.
    public static final String CREATE_CAR_PARK_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CarPark.TABLE_NAME + " ("
                    + CarPark._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CarPark.CAR_PARK_IDENTITY + " TEXT NOT NULL,"
                    + CarPark.TOTAL_PARKING_CAPACITY + " INTEGER NOT NULL,"
                    + CarPark.ALMOST_FULL_INCREASING + " INTEGER NOT NULL,"
                    + CarPark.ALMOST_FULL_DECREASING + " INTEGER NOT NULL,"
                    + CarPark.FULL_DECREASING + " INTEGER NOT NULL,"
                    + CarPark.FULL_INCREASING + " INTEGER NOT NULL,"
                    + CarPark.ENTRANCE_FULL + " INTEGER NOT NULL,"
                    + CarPark.RADIUS + " REAL NOT NULL,"
                    + CarPark.LATITUDE + " REAL NOT NULL,"
                    + CarPark.LONGITUDE + " REAL NOT NULL"
                    + ");";

    private BucksContract() {
    }

    public static final class CarPark implements BaseColumns {
        public static final String TABLE_NAME = "car_park";
        public static final String CAR_PARK_IDENTITY = "car_park_identity";
        public static final String TOTAL_PARKING_CAPACITY = "total_parking_capacity";
        public static final String ALMOST_FULL_INCREASING = "almost_full_increasing";
        public static final String ALMOST_FULL_DECREASING = "almost_full_decreasing";
        public static final String FULL_DECREASING = "full_decreasing";
        public static final String FULL_INCREASING = "full_increasing";
        public static final String ENTRANCE_FULL = "entrance_full";
        public static final String RADIUS = "radius";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
    }
}
