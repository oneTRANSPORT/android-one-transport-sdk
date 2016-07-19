package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

import android.provider.BaseColumns;

public class BcsContract {

    public static final String CREATE_BIT_CARRIER_SKETCH_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneSketch.TABLE_NAME + " ("
                    + BitCarrierSilverstoneSketch._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneSketch.COLUMN_SENSOR_ID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneSketch.COLUMN_VID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneSketch.COLUMN_LEVEL_OF_SERVICE + " TEXT,"
                    + BitCarrierSilverstoneSketch.COLUMN_LICENSE + " TEXT,"
                    + BitCarrierSilverstoneSketch.COLUMN_COORDINATES + " TEXT"
                    + ");";
    public static final String CREATE_BIT_CARRIER_TRAVEL_SUMMARY_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneTravelSummary.TABLE_NAME + " ("
                    + BitCarrierSilverstoneTravelSummary._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_RID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_TIME + " TEXT,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_SCORE + " INTEGER,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_SPEED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_TREND + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_READINGS + " INTEGER,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_SPEED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_TREND + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_READINGS + " INTEGER,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_SCORE + " INTEGER,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_SPEED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_TREND + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_READINGS + " INTEGER,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_SPEED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_TREND + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_READINGS + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_LEVEL_OF_SERVICE + " TEXT"
                    + ");";
    public static final String CREATE_BIT_CARRIER_VECTOR_STATUS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneVectorStatus.TABLE_NAME + " ("
                    + BitCarrierSilverstoneVectorStatus._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_VID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_TIME + " TEXT,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_SCORE + " INTEGER,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_SPEED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_TREND + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_READINGS + " INTEGER,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_SPEED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_TREND + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_READINGS + " INTEGER,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_SCORE + " INTEGER,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_SPEED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_TREND + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_READINGS + " INTEGER,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_SPEED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_TREND + " REAL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_READINGS + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneVectorStatus.COLUMN_LEVEL_OF_SERVICE + " TEXT"
                    + ");";

    private BcsContract() {
    }

    public static final class BitCarrierSilverstoneSketch implements BaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_sketch";
        public static final String COLUMN_SENSOR_ID = "sid";
        public static final String COLUMN_VID = "vid"; // TODO    VECTOR_ID?
        public static final String COLUMN_LEVEL_OF_SERVICE = "level_of_service";
        public static final String COLUMN_LICENSE = "license";
        public static final String COLUMN_COORDINATES = "coordinates";
    }

    public static final class BitCarrierSilverstoneTravelSummary implements BaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_travel_summary";
        public static final String COLUMN_RID = "rid";
        public static final String COLUMN_TIME = "time";
        // TODO    Will leave this out for now.  Requires a separate table for one-to-many.
        // TODO    Or could take first array value only.
//        public static final String COLUMN_TRAVEL_TIMES = "";
        public static final String COLUMN_AVERAGE_SCORE = "average_score";
        public static final String COLUMN_AVERAGE_PUBLISH_SPEED = "average_publish_speed";
        public static final String COLUMN_AVERAGE_PUBLISH_ELAPSED = "average_publish_elapsed";
        public static final String COLUMN_AVERAGE_PUBLISH_TREND = "average_publish_trend";
        public static final String COLUMN_AVERAGE_PUBLISH_READINGS = "average_publish_readings";
        public static final String COLUMN_AVERAGE_CALCULATED_SPEED = "average_calculated_speed";
        public static final String COLUMN_AVERAGE_CALCULATED_ELAPSED = "average_calculated_elapsed";
        public static final String COLUMN_AVERAGE_CALCULATED_TREND = "average_calculated_trend";
        public static final String COLUMN_AVERAGE_CALCULATED_READINGS = "average_calculated_readings";
        public static final String COLUMN_LAST_SCORE = "last_score";
        public static final String COLUMN_LAST_PUBLISH_SPEED = "last_publish_speed";
        public static final String COLUMN_LAST_PUBLISH_ELAPSED = "last_publish_elapsed";
        public static final String COLUMN_LAST_PUBLISH_TREND = "last_publish_trend";
        public static final String COLUMN_LAST_PUBLISH_READINGS = "last_publish_readings";
        public static final String COLUMN_LAST_CALCULATED_SPEED = "last_calculated_speed";
        public static final String COLUMN_LAST_CALCULATED_ELAPSED = "last_calculated_elapsed";
        public static final String COLUMN_LAST_CALCULATED_TREND = "last_calculated_trend";
        public static final String COLUMN_LAST_CALCULATED_READINGS = "last_calculated_readings";
        public static final String COLUMN_LEVEL_OF_SERVICE = "level_of_service";
    }

    public static final class BitCarrierSilverstoneVectorStatus implements BaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_vector_status";
        public static final String COLUMN_VID = "vid";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_AVERAGE_SCORE = "average_score";
        public static final String COLUMN_AVERAGE_PUBLISH_SPEED = "average_publish_speed";
        public static final String COLUMN_AVERAGE_PUBLISH_ELAPSED = "average_publish_elapsed";
        public static final String COLUMN_AVERAGE_PUBLISH_TREND = "average_publish_trend";
        public static final String COLUMN_AVERAGE_PUBLISH_READINGS = "average_publish_readings";
        public static final String COLUMN_AVERAGE_CALCULATED_SPEED = "average_calculated_speed";
        public static final String COLUMN_AVERAGE_CALCULATED_ELAPSED = "average_calculated_elapsed";
        public static final String COLUMN_AVERAGE_CALCULATED_TREND = "average_calculated_trend";
        public static final String COLUMN_AVERAGE_CALCULATED_READINGS = "average_calculated_readings";
        public static final String COLUMN_LAST_SCORE = "last_score";
        public static final String COLUMN_LAST_PUBLISH_SPEED = "last_publish_speed";
        public static final String COLUMN_LAST_PUBLISH_ELAPSED = "last_publish_elapsed";
        public static final String COLUMN_LAST_PUBLISH_TREND = "last_publish_trend";
        public static final String COLUMN_LAST_PUBLISH_READINGS = "last_publish_readings";
        public static final String COLUMN_LAST_CALCULATED_SPEED = "last_calculated_speed";
        public static final String COLUMN_LAST_CALCULATED_ELAPSED = "last_calculated_elapsed";
        public static final String COLUMN_LAST_CALCULATED_TREND = "last_calculated_trend";
        public static final String COLUMN_LAST_CALCULATED_READINGS = "last_calculated_readings";
        public static final String COLUMN_LEVEL_OF_SERVICE = "level_of_service";
    }
}
