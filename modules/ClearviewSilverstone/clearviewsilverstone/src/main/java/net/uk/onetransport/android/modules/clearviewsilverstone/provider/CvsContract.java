package net.uk.onetransport.android.modules.clearviewsilverstone.provider;

public class CvsContract {

    public static final String CREATE_CLEARVIEW_SILVERSTONE_DEVICE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ClearviewSilverstoneDevice.TABLE_NAME + " ("
                    + ClearviewSilverstoneDevice._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ClearviewSilverstoneDevice.COLUMN_SENSOR_ID + " INTEGER,"
                    + ClearviewSilverstoneDevice.COLUMN_TITLE + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_DESCRIPTION + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_TYPE + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_LATITUDE + " REAL,"
                    + ClearviewSilverstoneDevice.COLUMN_LONGITUDE + " REAL,"
                    + ClearviewSilverstoneDevice.COLUMN_CHANGED + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_CLEARVIEW_SILVERSTONE_TRAFFIC_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ClearviewSilverstoneTraffic.TABLE_NAME + " ("
                    + ClearviewSilverstoneTraffic._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + " INTEGER NOT NULL,"
                    + ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP + " TEXT,"
                    + ClearviewSilverstoneTraffic.COLUMN_LANE + " INTEGER,"
                    + ClearviewSilverstoneTraffic.COLUMN_DIRECTION + " BOOLEAN,"
                    + ClearviewSilverstoneDevice.COLUMN_CIN_ID + " TEXT,"
                    + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";

    private CvsContract() {
    }

    public static final class ClearviewSilverstoneDevice implements CvsBaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_device";
        public static final String COLUMN_SENSOR_ID = "sensor_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_CHANGED = "changed";
    }

    public static final class ClearviewSilverstoneTraffic implements CvsBaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_traffic";
        public static final String COLUMN_SENSOR_ID = "sensor_id";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_LANE = "lane";
        public static final String COLUMN_DIRECTION = "direction";
    }

}
