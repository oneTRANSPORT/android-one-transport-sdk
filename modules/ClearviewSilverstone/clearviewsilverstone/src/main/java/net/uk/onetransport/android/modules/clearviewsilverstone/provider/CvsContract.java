package net.uk.onetransport.android.modules.clearviewsilverstone.provider;

import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;

public class CvsContract {
// TODO    Check all indexes and accumulation of data.
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
                    + " TEXT NOT NULL UNIQUE ON CONFLICT REPLACE,"
                    + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_CLEARVIEW_SILVERSTONE_LATEST_DEVICE_TABLE =
            "CREATE VIEW IF NOT EXISTS " + ClearviewSilverstoneLatestDevice.TABLE_NAME
                    + " AS SELECT a.* FROM " + ClearviewSilverstoneDevice.TABLE_NAME
                    + " AS a INNER JOIN (SELECT "
                    + ClearviewSilverstoneDevice.COLUMN_SENSOR_ID
                    + ", MAX("
                    + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME + ") AS "
                    + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME + " FROM "
                    + ClearviewSilverstoneDevice.TABLE_NAME + " GROUP BY "
                    + ClearviewSilverstoneDevice.COLUMN_SENSOR_ID + ") AS b ON a."
                    + ClearviewSilverstoneDevice.COLUMN_SENSOR_ID + "=b."
                    + ClearviewSilverstoneDevice.COLUMN_SENSOR_ID + " AND a."
                    + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME + "=b."
                    + ClearviewSilverstoneDevice.COLUMN_CREATION_TIME + ";";
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
    public static final String CREATE_CLEARVIEW_SILVERSTONE_LATEST_TRAFFIC_TABLE =
            "CREATE VIEW IF NOT EXISTS " + ClearviewSilverstoneLatestTraffic.TABLE_NAME
                    + " AS SELECT a.* FROM " + ClearviewSilverstoneTraffic.TABLE_NAME
                    + " AS a INNER JOIN (SELECT "
                    + ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID
                    + ", MAX("
                    + ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME + ") AS "
                    + ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME + " FROM "
                    + ClearviewSilverstoneTraffic.TABLE_NAME + " GROUP BY "
                    + ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + ") AS b ON a."
                    + ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + "=b."
                    + ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + " AND a."
                    + ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME + "=b."
                    + ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME + ";";

    private CvsContract() {
    }

    public static final class ClearviewSilverstoneDevice implements CommonBaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_device";
        public static final String COLUMN_SENSOR_ID = "sensor_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_CHANGED = "changed";
    }

    public static final class ClearviewSilverstoneLatestDevice implements CommonBaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_latest_device";
    }

    public static final class ClearviewSilverstoneTraffic implements CommonBaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_traffic";
        public static final String COLUMN_SENSOR_ID = "sensor_id";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_LANE = "lane";
        public static final String COLUMN_DIRECTION = "direction";
    }

    public static final class ClearviewSilverstoneLatestTraffic implements CommonBaseColumns {
        public static final String TABLE_NAME = "clearview_silverstone_latest_traffic";
    }
}
