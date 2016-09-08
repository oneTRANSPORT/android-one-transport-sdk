package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

public class BcsContract {

    public static final String CREATE_BIT_CARRIER_DATA_SKETCH_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneSketch.TABLE_NAME + " ("
                    + BitCarrierSilverstoneSketch._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneSketch.COLUMN_SKETCH_ID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneSketch.COLUMN_VECTOR_ID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneSketch.COLUMN_VISIBLE + " INTEGER,"
                    + BitCarrierSilverstoneSketch.COLUMN_COPYRIGHTS + " TEXT,"
                    + BitCarrierSilverstoneSketch.COLUMN_COORDINATES + " TEXT,"
                    + BitCarrierSilverstoneSketch.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneSketch.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_BIT_CARRIER_NODE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneNode.TABLE_NAME + " ("
                    + BitCarrierSilverstoneNode._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " INTEGER,"
                    + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " INTEGER,"
                    + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME + " TEXT,"
                    + BitCarrierSilverstoneNode.COLUMN_LATITUDE + " REAL,"
                    + BitCarrierSilverstoneNode.COLUMN_LONGITUDE + " REAL,"
                    + BitCarrierSilverstoneNode.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneNode.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_BIT_CARRIER_TRAVEL_SUMMARY_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneTravelSummary.TABLE_NAME + " ("
                    + BitCarrierSilverstoneTravelSummary._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_TIME_ID + " INTEGER,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_CLOCK_TIME + " TEXT,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_FROM_LOCATION + " TEXT,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_TO_LOCATION + " TEXT,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_SCORE + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_SPEED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_TREND + " REAL,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneTravelSummary.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_BIT_CARRIER_CONFIG_VECTOR_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneConfigVector.TABLE_NAME + " ("
                    + BitCarrierSilverstoneConfigVector._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_VECTOR_ID + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_NAME + " TEXT,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CUSTOMER_NAME + " TEXT,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_FROM + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_TO + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_DISTANCE + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_BIT_CARRIER_DATA_VECTOR_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneDataVector.TABLE_NAME + " ("
                    + BitCarrierSilverstoneDataVector._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneDataVector.COLUMN_VECTOR_ID + " INTEGER,"
                    + BitCarrierSilverstoneDataVector.COLUMN_TIMESTAMP + " TEXT,"
                    + BitCarrierSilverstoneDataVector.COLUMN_SPEED + " REAL,"
                    + BitCarrierSilverstoneDataVector.COLUMN_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneDataVector.COLUMN_LEVEL_OF_SERVICE + " TEXT,"
                    + BitCarrierSilverstoneDataVector.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneDataVector.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";

    private BcsContract() {
    }

    public static final class BitCarrierSilverstoneSketch implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_sketch";
        public static final String COLUMN_SKETCH_ID = "sketch_id";
        public static final String COLUMN_VECTOR_ID = "vector_id";
        public static final String COLUMN_VISIBLE = "visible";
        public static final String COLUMN_COPYRIGHTS = "copyrights";
        public static final String COLUMN_COORDINATES = "coordinates";
    }

    public static final class BitCarrierSilverstoneNode implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_node";
        public static final String COLUMN_NODE_ID = "node_id";
        // The customer id is a prefix of the customer name.  Need to extract manually.
        // Eg. "25-A4421 / A421, Buckingham"
        // The id is the bit before the hyphen.
        public static final String COLUMN_CUSTOMER_ID = "customer_id";
        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
        public static final String COLUMN_LATITUDE = "lat";
        public static final String COLUMN_LONGITUDE = "lon";
    }

    public static final class BitCarrierSilverstoneTravelSummary implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_travel_summary";
        public static final String COLUMN_TRAVEL_TIME_ID = "travel_summary_id";
        public static final String COLUMN_CLOCK_TIME = "clock_time";
        public static final String COLUMN_FROM_LOCATION = "from_location";
        public static final String COLUMN_TO_LOCATION = "to_location";
        public static final String COLUMN_SCORE = "score";
        public static final String COLUMN_SPEED = "speed";
        public static final String COLUMN_ELAPSED = "elapsed";
        public static final String COLUMN_TREND = "trend";
    }

    public static final class BitCarrierSilverstoneConfigVector implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_config_vector";
        public static final String COLUMN_VECTOR_ID = "vector_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
        public static final String COLUMN_FROM = "from_location";
        public static final String COLUMN_TO = "to_location";
        public static final String COLUMN_DISTANCE = "distance";
        public static final String COLUMN_SKETCH_ID = "sketch_id";
    }

    public static final class BitCarrierSilverstoneDataVector implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_data_vector";
        public static final String COLUMN_VECTOR_ID = "vector_id";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_SPEED = "SPEED";
        public static final String COLUMN_ELAPSED = "elapsed";
        public static final String COLUMN_LEVEL_OF_SERVICE = "level_of_service";
    }
}
