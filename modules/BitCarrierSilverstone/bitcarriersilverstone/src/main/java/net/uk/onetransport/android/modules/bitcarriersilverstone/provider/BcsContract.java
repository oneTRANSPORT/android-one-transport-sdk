package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

public class BcsContract {

//    public static final String CREATE_BIT_CARRIER_CONFIG_SKETCH_TABLE =
//            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneConfigSketch.TABLE_NAME + " ("
//                    + BitCarrierSilverstoneConfigSketch._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + BitCarrierSilverstoneConfigSketch.COLUMN_SKETCH_ID + " INTEGER NOT NULL,"
//                    + BitCarrierSilverstoneConfigSketch.COLUMN_VECTOR_ID + " INTEGER,"
//                    + BitCarrierSilverstoneConfigSketch.COLUMN_COORDINATES + " TEXT,"
//                    + BitCarrierSilverstoneConfigSketch.COLUMN_CIN_ID
//                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
//                    + BitCarrierSilverstoneConfigSketch.COLUMN_CREATION_TIME + " INTEGER"
//                    + ");";
    public static final String CREATE_BIT_CARRIER_DATA_SKETCH_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneDataSketch.TABLE_NAME + " ("
                    + BitCarrierSilverstoneDataSketch._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneDataSketch.COLUMN_SKETCH_ID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneDataSketch.COLUMN_VECTOR_ID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneDataSketch.COLUMN_LEVEL_OF_SERVICE + " TEXT,"
                    + BitCarrierSilverstoneDataSketch.COLUMN_LICENSE + " TEXT,"
                    + BitCarrierSilverstoneDataSketch.COLUMN_COORDINATES + " TEXT,"
                    + BitCarrierSilverstoneDataSketch.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneDataSketch.COLUMN_CREATION_TIME + " INTEGER"
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
//    public static final String CREATE_BIT_CARRIER_ROUTE_TABLE =
//            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneRoute.TABLE_NAME + " ("
//                    + BitCarrierSilverstoneRoute._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + BitCarrierSilverstoneRoute.COLUMN_ROUTE_ID + " INTEGER,"
//                    + BitCarrierSilverstoneRoute.COLUMN_CITY_ID + " INTEGER,"
//                    + BitCarrierSilverstoneRoute.COLUMN_METAVECTOR_ID + " INTEGER,"
//                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR1 + " TEXT,"
//                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR1_CONTRIB + " REAL,"
//                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR2 + " TEXT,"
//                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR2_CONTRIB + " REAL,"
//                    + BitCarrierSilverstoneRoute.COLUMN_OFFSET + " REAL,"
//                    + BitCarrierSilverstoneRoute.COLUMN_DISTANCE + " REAL,"
//                    + BitCarrierSilverstoneRoute.COLUMN_ROUTE_DETECTIONS_MIN + " INTEGER,"
//                    + BitCarrierSilverstoneRoute.COLUMN_ZONE + " INTEGER,"
//                    + BitCarrierSilverstoneRoute.COLUMN_NAME + " TEXT,"
//                    + BitCarrierSilverstoneRoute.COLUMN_CUSTOMER_NAME + " TEXT,"
//                    + BitCarrierSilverstoneRoute.COLUMN_CONFIGURATION + " INTEGER,"
//                    + BitCarrierSilverstoneRoute.COLUMN_CIN_ID
//                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
//                    + BitCarrierSilverstoneRoute.COLUMN_CREATION_TIME + " INTEGER"
//                    + ");";
//    public static final String CREATE_BIT_CARRIER_METAVECTOR_TABLE =
//            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneMetavector.TABLE_NAME + " ("
//                    + BitCarrierSilverstoneMetavector._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_METAVECTOR_ID + " INTEGER,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_NAME + " TEXT,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_CUSTOMER_NAME + " TEXT,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_SEQUENCE + " TEXT,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_ZONE + " INTEGER,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_CITY_ID + " INTEGER,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_CIN_ID
//                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
//                    + BitCarrierSilverstoneMetavector.COLUMN_CREATION_TIME + " INTEGER"
//                    + ");";
//    public static final String CREATE_BIT_CARRIER_CONFIG_TRAVELTIME_TABLE =
//            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneConfigTravelTime.TABLE_NAME + " ("
//                    + BitCarrierSilverstoneConfigTravelTime._ID
//                    + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_TRAVEL_TIME_ID + " INTEGER,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_ROUTE_ID + " INTEGER,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_NAME + " TEXT,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_CUSTOMER_NAME + " TEXT,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_ZONE + " INTEGER,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_PUBLISH + " BOOLEAN,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_EXTRA_OFFSET + " REAL,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_CIN_ID
//                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
//                    + BitCarrierSilverstoneConfigTravelTime.COLUMN_CREATION_TIME + " INTEGER"
//                    + ");";
    public static final String CREATE_BIT_CARRIER_TRAVEL_SUMMARY_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneDataTravelSummary.TABLE_NAME + " ("
                    + BitCarrierSilverstoneDataTravelSummary._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_TRAVEL_TIME_ID + " INTEGER,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_CLOCK_TIME + " TEXT,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_FROM_LOCATION + " TEXT,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_TO_LOCATION + " TEXT,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_SCORE + " REAL,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_SPEED + " REAL,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_TREND + " REAL,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneDataTravelSummary.COLUMN_CREATION_TIME + " INTEGER"
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
                    + BitCarrierSilverstoneConfigVector.COLUMN_ZONE + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CITY_ID + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_BLOCK_TIME + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_SEGREGATION + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CONFIGURATION + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_PRIORITY + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CHECK_FORCED + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_ROUTE_ID + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_LEVELS + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_GREEN + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_YELLOW + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_AVERAGE_GREEN + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_AVERAGE_YELLOW + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_DETECTIONS_MIN + " INTEGER,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_HAS_COLOUR + " BOOLEAN,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneConfigVector.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
//    public static final String CREATE_BIT_CARRIER_ZONE_TABLE =
//            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneZone.TABLE_NAME + " ("
//                    + BitCarrierSilverstoneZone._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + BitCarrierSilverstoneZone.COLUMN_ZONE_ID + " INTEGER,"
//                    + BitCarrierSilverstoneZone.COLUMN_PARENT_ID + " INTEGER,"
//                    + BitCarrierSilverstoneZone.COLUMN_NAME + " TEXT,"
//                    + BitCarrierSilverstoneZone.COLUMN_CENTER_LATITUDE + " REAL,"
//                    + BitCarrierSilverstoneZone.COLUMN_CENTER_LONGITUDE + " REAL,"
//                    + BitCarrierSilverstoneZone.COLUMN_ZOOM + " INTEGER,"
//                    + BitCarrierSilverstoneZone.COLUMN_NORTHERN_LATITUDE + " REAL,"
//                    + BitCarrierSilverstoneZone.COLUMN_WESTERN_LONGITUDE + " REAL,"
//                    + BitCarrierSilverstoneZone.COLUMN_SOUTHERN_LATITUDE + " REAL,"
//                    + BitCarrierSilverstoneZone.COLUMN_EASTERN_LONGITUDE + " REAL,"
//                    + BitCarrierSilverstoneZone.COLUMN_CIN_ID
//                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
//                    + BitCarrierSilverstoneZone.COLUMN_CREATION_TIME + " INTEGER"
//                    + ");";
//    public static final String CREATE_BIT_CARRIER_LATEST_TRAVEL_TIME_TABLE =
//            "CREATE VIEW IF NOT EXISTS " + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME
//                    + " AS SELECT "
//                    + BitCarrierSilverstoneLatestTravelTime._ID + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TRAVEL_TIME_ID + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_DATE + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_HOUR + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_FROM_LOCATION + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TO_LOCATION + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_SCORE + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_SPEED + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_ELAPSED + ","
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TREND
//                    + " FROM " + BitCarrierSilverstoneTravelTime.TABLE_NAME
//                    + " GROUP BY " + BitCarrierSilverstoneTravelTime.COLUMN_TRAVEL_TIME_ID + ";";
//    public static final String CREATE_BIT_CARRIER_LATEST_VECTOR_TRAVEL_TIME_TABLE =
//            "CREATE VIEW IF NOT EXISTS " + BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME
//                    + " AS SELECT "
//                    + "node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_FROM_CUSTOMER_ID + ","
//                    + "node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TO_CUSTOMER_ID + ","
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID + " as "
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_SKETCH_ID + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TRAVEL_TIME_ID + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_DATE + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_HOUR + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_FROM_LOCATION + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TO_LOCATION + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_SCORE + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_SPEED + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_ELAPSED + ","
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TREND
//                    + " FROM "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + ","
//                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node1,"
//                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node2,"
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME
//                    + " WHERE node1." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_FROM
//                    + " AND node2." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_TO
//                    + " AND "
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_FROM_LOCATION
//                    + " = node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
//                    + " AND "
//                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TO_LOCATION
//                    + " = node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
//                    + " AND "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID + " IS NOT NULL;";
//    public static final String CREATE_BIT_CARRIER_VECTOR_TRAVEL_TIME_TABLE =
//            "CREATE VIEW IF NOT EXISTS " + BitCarrierSilverstoneVectorTravelTime.TABLE_NAME
//                    + " AS SELECT "
//                    + "node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_FROM_CUSTOMER_ID + ","
//                    + "node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TO_CUSTOMER_ID + ","
//                    + "node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME + " as "
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_FROM_CUSTOMER_NAME + ","
//                    + "node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME + " as "
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TO_CUSTOMER_NAME + ","
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID + " as "
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_SKETCH_ID + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TRAVEL_TIME_ID + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_DATE + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_HOUR + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_FROM_LOCATION + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TO_LOCATION + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_SCORE + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_SPEED + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_ELAPSED + ","
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TREND
//                    + " FROM "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + ","
//                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node1,"
//                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node2,"
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME
//                    + " WHERE node1." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_FROM
//                    + " AND node2." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_TO
//                    + " AND "
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneTravelTime.COLUMN_FROM_LOCATION
//                    + " = node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
//                    + " AND "
//                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
//                    + BitCarrierSilverstoneTravelTime.COLUMN_TO_LOCATION
//                    + " = node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
//                    + " AND "
//                    + BitCarrierSilverstoneConfigVector.TABLE_NAME + "."
//                    + BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID + " IS NOT NULL;";

    private BcsContract() {
    }

//    public static final class BitCarrierSilverstoneConfigSketch implements BcsBaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_config_sketch";
//        public static final String COLUMN_SKETCH_ID = "sketch_id";
//        public static final String COLUMN_VECTOR_ID = "vector_id";
//        public static final String COLUMN_COORDINATES = "coordinates";
//    }

    public static final class BitCarrierSilverstoneDataSketch implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_data_sketch";
        public static final String COLUMN_SKETCH_ID = "sketch_id";
        public static final String COLUMN_VECTOR_ID = "vector_id";
        public static final String COLUMN_LEVEL_OF_SERVICE = "level_of_service";
        public static final String COLUMN_LICENSE = "license";
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

//    public static final class BitCarrierSilverstoneRoute implements BcsBaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_route";
//        public static final String COLUMN_ROUTE_ID = "route_id";
//        public static final String COLUMN_CITY_ID = "city_id";
//        public static final String COLUMN_METAVECTOR_ID = "metavector";
//        public static final String COLUMN_VECTOR1 = "vector1";
//        public static final String COLUMN_VECTOR1_CONTRIB = "vector1_contrib";
//        public static final String COLUMN_VECTOR2 = "vector2";
//        public static final String COLUMN_VECTOR2_CONTRIB = "vector2_contrib";
//        public static final String COLUMN_OFFSET = "offset";
//        public static final String COLUMN_DISTANCE = "distance";
//        public static final String COLUMN_ROUTE_DETECTIONS_MIN = "route_detections_min";
//        public static final String COLUMN_ZONE = "zone";
//        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
//        public static final String COLUMN_CONFIGURATION = "configuration";
//    }

//    public static final class BitCarrierSilverstoneMetavector implements BcsBaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_metavector";
//        public static final String COLUMN_METAVECTOR_ID = "metavector_id";
//        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
//        public static final String COLUMN_SEQUENCE = "sequence";
//        public static final String COLUMN_ZONE = "zone";
//        public static final String COLUMN_CITY_ID = "city_id";
//    }

//    public static final class BitCarrierSilverstoneConfigTravelTime implements BcsBaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_config_travel_time";
//        public static final String COLUMN_TRAVEL_TIME_ID = "travel_time_id";
//        public static final String COLUMN_ROUTE_ID = "route_id";
//        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
//        public static final String COLUMN_PUBLISH = "publish";
//        public static final String COLUMN_EXTRA_OFFSET = "extra_offset";
//        public static final String COLUMN_ZONE = "zone";
//    }

    public static final class BitCarrierSilverstoneDataTravelSummary implements BcsBaseColumns {
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
        public static final String TABLE_NAME = "bit_carrier_silverstone_vector";
        public static final String COLUMN_VECTOR_ID = "vector_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
        public static final String COLUMN_FROM = "from_location";
        public static final String COLUMN_TO = "to_location";
        public static final String COLUMN_DISTANCE = "distance";
        public static final String COLUMN_ZONE = "zone";
        public static final String COLUMN_CITY_ID = "city_id";
        public static final String COLUMN_BLOCK_TIME = "block_time";
        public static final String COLUMN_SEGREGATION = "segregation";
        public static final String COLUMN_CONFIGURATION = "configuration";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_CHECK_FORCED = "check_forced";
        public static final String COLUMN_SKETCH_ID = "sketch_id";
        public static final String COLUMN_ROUTE_ID = "route_id";
        public static final String COLUMN_LEVELS = "levels";
        public static final String COLUMN_GREEN = "green";
        public static final String COLUMN_YELLOW = "yellow";
        public static final String COLUMN_AVERAGE_GREEN = "average_green";
        public static final String COLUMN_AVERAGE_YELLOW = "average_yellow";
        public static final String COLUMN_DETECTIONS_MIN = "detections_min";
        public static final String COLUMN_HAS_COLOUR = "has_colour";
    }

//    public static final class BitCarrierSilverstoneZone implements BcsBaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_zone";
//        public static final String COLUMN_ZONE_ID = "zone_id";
//        public static final String COLUMN_PARENT_ID = "parent_id";
//        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_CENTER_LATITUDE = "center_latitude";
//        public static final String COLUMN_CENTER_LONGITUDE = "center_longitude";
//        public static final String COLUMN_ZOOM = "zoom";
//        public static final String COLUMN_NORTHERN_LATITUDE = "northern_latitude";
//        public static final String COLUMN_WESTERN_LONGITUDE = "western_longitude";
//        public static final String COLUMN_SOUTHERN_LATITUDE = "southern_latitude";
//        public static final String COLUMN_EASTERN_LONGITUDE = "eastern_longitude";
//    }

//    public static final class BitCarrierSilverstoneLatestTravelTime implements BaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_latest_travel_time";
//        public static final String COLUMN_TRAVEL_TIME_ID = "travel_time_id";
//        public static final String COLUMN_DATE = "date";
//        public static final String COLUMN_HOUR = "hour";
//        public static final String COLUMN_FROM_LOCATION = "from_location";
//        public static final String COLUMN_TO_LOCATION = "to_location";
//        public static final String COLUMN_SCORE = "score";
//        public static final String COLUMN_SPEED = "speed";
//        public static final String COLUMN_ELAPSED = "elapsed";
//        public static final String COLUMN_TREND = "trend";
//    }

//    public static final class BitCarrierSilverstoneLatestVectorTravelTime implements BaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_latest_vector_travel_time";
//        public static final String COLUMN_FROM_CUSTOMER_ID = "from_customer_id";
//        public static final String COLUMN_TO_CUSTOMER_ID = "to_customer_id";
//        public static final String COLUMN_SKETCH_ID = "sketch_id";
//        public static final String COLUMN_TRAVEL_TIME_ID =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_TRAVEL_TIME_ID;
//        public static final String COLUMN_DATE =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_DATE;
//        public static final String COLUMN_HOUR =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_HOUR;
//        public static final String COLUMN_FROM_LOCATION =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_FROM_LOCATION;
//        public static final String COLUMN_TO_LOCATION =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_TO_LOCATION;
//        public static final String COLUMN_SCORE =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_SCORE;
//        public static final String COLUMN_SPEED =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_SPEED;
//        public static final String COLUMN_ELAPSED =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_ELAPSED;
//        public static final String COLUMN_TREND =
//                BitCarrierSilverstoneLatestTravelTime.COLUMN_TREND;
//    }

//    public static final class BitCarrierSilverstoneVectorTravelTime implements BaseColumns {
//        public static final String TABLE_NAME = "bit_carrier_silverstone_vector_travel_time";
//        public static final String COLUMN_FROM_CUSTOMER_ID = "from_customer_id";
//        public static final String COLUMN_TO_CUSTOMER_ID = "to_customer_id";
//        public static final String COLUMN_FROM_CUSTOMER_NAME = "from_customer_name";
//        public static final String COLUMN_TO_CUSTOMER_NAME = "to_customer_name";
//        public static final String COLUMN_SKETCH_ID = "sketch_id";
//        public static final String COLUMN_TRAVEL_TIME_ID =
//                BitCarrierSilverstoneTravelTime.COLUMN_TRAVEL_TIME_ID;
//        public static final String COLUMN_DATE =
//                BitCarrierSilverstoneTravelTime.COLUMN_DATE;
//        public static final String COLUMN_HOUR =
//                BitCarrierSilverstoneTravelTime.COLUMN_HOUR;
//        public static final String COLUMN_FROM_LOCATION =
//                BitCarrierSilverstoneTravelTime.COLUMN_FROM_LOCATION;
//        public static final String COLUMN_TO_LOCATION =
//                BitCarrierSilverstoneTravelTime.COLUMN_TO_LOCATION;
//        public static final String COLUMN_SCORE =
//                BitCarrierSilverstoneTravelTime.COLUMN_SCORE;
//        public static final String COLUMN_SPEED =
//                BitCarrierSilverstoneTravelTime.COLUMN_SPEED;
//        public static final String COLUMN_ELAPSED =
//                BitCarrierSilverstoneTravelTime.COLUMN_ELAPSED;
//        public static final String COLUMN_TREND =
//                BitCarrierSilverstoneTravelTime.COLUMN_TREND;
//    }

}
