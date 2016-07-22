package net.uk.onetransport.android.modules.clearviewsilverstone.provider;

import android.provider.BaseColumns;

public class CvsContract {

    public static final String CREATE_CLEARVIEW_SILVERSTONE_DEVICE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ClearviewSilverstoneDevice.TABLE_NAME + " ("
                    + ClearviewSilverstoneDevice._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ClearviewSilverstoneDevice.COLUMN_SENSOR_ID + " INTEGER NOT NULL,"
                    + ClearviewSilverstoneDevice.COLUMN_TITLE + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_DESCRIPTION + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_TYPE + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_LATITUDE + " REAL,"
                    + ClearviewSilverstoneDevice.COLUMN_LONGITUDE + " REAL,"
                    + ClearviewSilverstoneDevice.COLUMN_CHANGED + " TEXT"
                    + ");";
    public static final String CREATE_CLEARVIEW_SILVERSTONE_TRAFFIC_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ClearviewSilverstoneTraffic.TABLE_NAME + " ("
                    + ClearviewSilverstoneTraffic._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + " INTEGER NOT NULL,"
                    + ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP + " TEXT,"
                    + ClearviewSilverstoneTraffic.COLUMN_DIRECTION + " BOOLEAN"
                    + ");";
    public static final String CREATE_CLEARVIEW_SILVERSTONE_HISTORY_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ClearviewSilverstoneHistory.TABLE_NAME + " ("
                    + ClearviewSilverstoneHistory._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ClearviewSilverstoneHistory.COLUMN_SENSOR_ID + " INTEGER NOT NULL,"
                    + ClearviewSilverstoneHistory.COLUMN_TIMESTAMP + " TEXT,"
                    + ClearviewSilverstoneHistory.COLUMN_VEHICLES + " INTEGER,"
                    + ClearviewSilverstoneTraffic.COLUMN_DIRECTION + " BOOLEAN"
                    + ");";

    private CvsContract() {
    }

    public static final class ClearviewSilverstoneDevice implements BaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_device";
        public static final String COLUMN_SENSOR_ID = "sensor_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_CHANGED = "changed";
    }

    public static final class ClearviewSilverstoneTraffic implements BaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_traffic";
        public static final String COLUMN_SENSOR_ID = "sensor_id";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_DIRECTION = "direction";
    }

    public static final class ClearviewSilverstoneHistory implements BaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_history";
        public static final String COLUMN_SENSOR_ID = "sensor_id";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_VEHICLES = "vehicles";
        public static final String COLUMN_DIRECTION = "direction";
    }
}
