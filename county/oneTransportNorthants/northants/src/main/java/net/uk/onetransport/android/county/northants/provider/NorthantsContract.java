package net.uk.onetransport.android.county.northants.provider;

import android.provider.BaseColumns;

public class NorthantsContract {

    public static final String CREATE_CLEARVIEW_DEVICE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ClearviewDevice.TABLE_NAME + " ("
                    + ClearviewDevice._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ClearviewDevice.COLUMN_TITLE + " TEXT NOT NULL,"
                    + ClearviewDevice.COLUMN_DESCRIPTION + " TEXT NOT NULL,"
                    + ClearviewDevice.COLUMN_TYPE + " TEXT NOT NULL,"
                    + ClearviewDevice.COLUMN_LATITUDE + " REAL NOT NULL"
                    + ClearviewDevice.COLUMN_LONGITUDE + " REAL NOT NULL"
                    + ClearviewDevice.COLUMN_CHANGED + " TEXT NOT NULL"
                    + ");";
    public static final String CREATE_CLEARVIEW_DATA_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ClearviewData.TABLE_NAME
                    + ClearviewData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ClearviewData.COLUMN_VEHICLE_NUMBER + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_TIME + " TEXT NOT NULL,"
                    + ClearviewData.COLUMN_LANE + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_SUB_SITE + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_INDEX_MARK + " TEXT,"
                    + ClearviewData.COLUMN_SPEED + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_LENGTH + " REAL NOT NULL,"
                    + ClearviewData.COLUMN_HEADWAY + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_GROSS_WEIGHT + " TEXT,"
                    + ClearviewData.COLUMN_GAP + " REAL NOT NULL,"
                    + ClearviewData.COLUMN_DIRECTION + " BOOLEAN NOT NULL,"
                    + ClearviewData.COLUMN_VEHICLE_CLASS + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_OVERHANG + " TEXT,"
                    + ClearviewData.COLUMN_CLASS_SCHEME + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_CHASSIS_HEIGHT_CODE + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_WHEELBASE + " TEXT,"
                    + ClearviewData.COLUMN_AXLE_DATA + " TEXT,"
                    + ClearviewData.COLUMN_OCCUPANCY_TIME + " INTEGER NOT NULL,"
                    + ClearviewData.COLUMN_RESULT_CODE + " TEXT,"
                    + ClearviewData.COLUMN_DELTA_TIME + " INTEGER NOT NULL"
                    + ");";
    public static final String CREATE_LAST_UPDATED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + LastUpdated.TABLE_NAME + " ("
                    + LastUpdated.COLUMN_LAST_UPDATE_MILLIS + " INTEGER NOT NULL"
                    + ");";

    private NorthantsContract() {
    }

    public static final class ClearviewDevice implements BaseColumns {
        public static final String TABLE_NAME = "clearview_device";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_CHANGED = "changed";
    }

    public static final class ClearviewData implements BaseColumns {
        public static final String TABLE_NAME = "clearview_data";
        public static final String COLUMN_VEHICLE_NUMBER = "vehicle_number";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_LANE = "lane";
        public static final String COLUMN_SUB_SITE = "sub_site";
        public static final String COLUMN_INDEX_MARK = "index_mark";
        public static final String COLUMN_SPEED = "speed";
        public static final String COLUMN_LENGTH = "length";
        public static final String COLUMN_HEADWAY = "headway";
        public static final String COLUMN_GROSS_WEIGHT = "gross_weight";
        public static final String COLUMN_GAP = "gap";
        public static final String COLUMN_DIRECTION = "direction";
        public static final String COLUMN_VEHICLE_CLASS = "vehicle_class";
        public static final String COLUMN_OVERHANG = "overhang";
        public static final String COLUMN_CLASS_SCHEME = "class_scheme";
        public static final String COLUMN_CHASSIS_HEIGHT_CODE = "chassis_height_code";
        public static final String COLUMN_WHEELBASE = "wheelbase";
        public static final String COLUMN_AXLE_DATA = "axle_data";
        public static final String COLUMN_OCCUPANCY_TIME = "occupancy_time";
        public static final String COLUMN_RESULT_CODE = "result_code";
        public static final String COLUMN_DELTA_TIME = "delta_time";
    }

    public static final class LastUpdated implements BaseColumns {
        public static final String TABLE_NAME = "last_updated";
        public static final String COLUMN_LAST_UPDATE_MILLIS = "last_update_millis";
    }
}
