package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

import android.provider.BaseColumns;

public class BcsContract {

    public static final String CREATE_BIT_CARRIER_CONFIG_SKETCH_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneConfigSketch.TABLE_NAME + " ("
                    + BitCarrierSilverstoneConfigSketch._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneConfigSketch.COLUMN_SKETCH_ID + " INTEGER NOT NULL,"
                    + BitCarrierSilverstoneConfigSketch.COLUMN_VECTOR_ID + " INTEGER,"
                    + BitCarrierSilverstoneConfigSketch.COLUMN_COORDINATES + " TEXT,"
                    + BitCarrierSilverstoneConfigSketch.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneConfigSketch.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
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
    public static final String CREATE_BIT_CARRIER_ROUTE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneRoute.TABLE_NAME + " ("
                    + BitCarrierSilverstoneRoute._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneRoute.COLUMN_ROUTE_ID + " INTEGER,"
                    + BitCarrierSilverstoneRoute.COLUMN_CITY_ID + " INTEGER,"
                    + BitCarrierSilverstoneRoute.COLUMN_METAVECTOR_ID + " INTEGER,"
                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR1 + " TEXT,"
                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR1_CONTRIB + " REAL,"
                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR2 + " TEXT,"
                    + BitCarrierSilverstoneRoute.COLUMN_VECTOR2_CONTRIB + " REAL,"
                    + BitCarrierSilverstoneRoute.COLUMN_OFFSET + " REAL,"
                    + BitCarrierSilverstoneRoute.COLUMN_DISTANCE + " REAL,"
                    + BitCarrierSilverstoneRoute.COLUMN_ROUTE_DETECTIONS_MIN + " INTEGER,"
                    + BitCarrierSilverstoneRoute.COLUMN_ZONE_ID + " INTEGER,"
                    + BitCarrierSilverstoneRoute.COLUMN_NAME + " TEXT,"
                    + BitCarrierSilverstoneRoute.COLUMN_CUSTOMER_NAME + " TEXT,"
                    + BitCarrierSilverstoneRoute.COLUMN_CONFIGURATION + " INTEGER,"
                    + BitCarrierSilverstoneRoute.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneRoute.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_BIT_CARRIER_METAVECTOR_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneMetavector.TABLE_NAME + " ("
                    + BitCarrierSilverstoneMetavector._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneMetavector.COLUMN_METAVECTOR_ID + " INTEGER,"
                    + BitCarrierSilverstoneMetavector.COLUMN_NAME + " TEXT,"
                    + BitCarrierSilverstoneMetavector.COLUMN_CUSTOMER_NAME + " TEXT,"
                    + BitCarrierSilverstoneMetavector.COLUMN_SEQUENCE + " TEXT,"
                    + BitCarrierSilverstoneMetavector.COLUMN_ZONE + " INTEGER,"
                    + BitCarrierSilverstoneMetavector.COLUMN_CITY_ID + " INTEGER,"
                    + BitCarrierSilverstoneMetavector.COLUMN_CIN_ID
                    + " TEXT UNIQUE ON CONFLICT REPLACE,"
                    + BitCarrierSilverstoneMetavector.COLUMN_CREATION_TIME + " INTEGER"
                    + ");";
    public static final String CREATE_BIT_CARRIER_TRAVEL_TIME_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneTravelTime.TABLE_NAME + " ("
                    + BitCarrierSilverstoneTravelTime._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_TRAVEL_TIME_ID + " INTEGER,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_DATE + " TEXT,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_HOUR + " INTEGER,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_FROM_LOCATION + " TEXT,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_TO_LOCATION + " TEXT,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_SCORE + " REAL,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_SPEED + " REAL,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_ELAPSED + " REAL,"
                    + BitCarrierSilverstoneTravelTime.COLUMN_TREND + " REAL"
                    + ");";
    public static final String CREATE_BIT_CARRIER_VECTOR_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BitCarrierSilverstoneVector.TABLE_NAME + " ("
                    + BitCarrierSilverstoneVector._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + BitCarrierSilverstoneVector.COLUMN_VECTOR_ID + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_NAME + " TEXT,"
                    + BitCarrierSilverstoneVector.COLUMN_CUSTOMER_NAME + " TEXT,"
                    + BitCarrierSilverstoneVector.COLUMN_FROM + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_TO + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_DISTANCE + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_ZONE + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_CITY_ID + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_BLOCK_TIME + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_SEGREGATION + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_CONFIGURATION + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_PRIORITY + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_CHECK_FORCED + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_SKETCH_ID + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_ROUTE_ID + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_LEVELS + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_GREEN + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_YELLOW + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_AVERAGE_GREEN + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_AVERAGE_YELLOW + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_DETECTIONS_MIN + " INTEGER,"
                    + BitCarrierSilverstoneVector.COLUMN_HAS_COLOUR + " BOOLEAN"
                    + ");";
    public static final String CREATE_BIT_CARRIER_LATEST_TRAVEL_TIME_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME
                    + " AS SELECT "
                    + BitCarrierSilverstoneLatestTravelTime._ID + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TRAVEL_TIME_ID + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_DATE + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_HOUR + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_FROM_LOCATION + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TO_LOCATION + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_SCORE + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_SPEED + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_ELAPSED + ","
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TREND
                    + " FROM " + BitCarrierSilverstoneTravelTime.TABLE_NAME
                    + " GROUP BY " + BitCarrierSilverstoneTravelTime.COLUMN_TRAVEL_TIME_ID + ";";
    public static final String CREATE_BIT_CARRIER_LATEST_VECTOR_TRAVEL_TIME_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME
                    + " AS SELECT "
                    + "node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_FROM_CUSTOMER_ID + ","
                    + "node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TO_CUSTOMER_ID + ","
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_SKETCH_ID + " as "
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_SKETCH_ID + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TRAVEL_TIME_ID + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_DATE + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_HOUR + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_FROM_LOCATION + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TO_LOCATION + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_SCORE + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_SPEED + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_ELAPSED + ","
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TREND
                    + " FROM "
                    + BitCarrierSilverstoneVector.TABLE_NAME + ","
                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node1,"
                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node2,"
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME
                    + " WHERE node1." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_FROM
                    + " AND node2." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_TO
                    + " AND "
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_FROM_LOCATION
                    + " = node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
                    + " AND "
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneLatestTravelTime.COLUMN_TO_LOCATION
                    + " = node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
                    + " AND "
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_SKETCH_ID + " IS NOT NULL;";
    public static final String CREATE_BIT_CARRIER_VECTOR_TRAVEL_TIME_TABLE =
            "CREATE VIEW IF NOT EXISTS " + BitCarrierSilverstoneVectorTravelTime.TABLE_NAME
                    + " AS SELECT "
                    + "node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_FROM_CUSTOMER_ID + ","
                    + "node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID + " as "
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TO_CUSTOMER_ID + ","
                    + "node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME + " as "
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_FROM_CUSTOMER_NAME + ","
                    + "node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME + " as "
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TO_CUSTOMER_NAME + ","
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_SKETCH_ID + " as "
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_SKETCH_ID + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TRAVEL_TIME_ID + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_DATE + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_HOUR + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_FROM_LOCATION + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TO_LOCATION + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_SCORE + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_SPEED + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_ELAPSED + ","
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneVectorTravelTime.COLUMN_TREND
                    + " FROM "
                    + BitCarrierSilverstoneVector.TABLE_NAME + ","
                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node1,"
                    + BitCarrierSilverstoneNode.TABLE_NAME + " as node2,"
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME
                    + " WHERE node1." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_FROM
                    + " AND node2." + BitCarrierSilverstoneNode.COLUMN_NODE_ID + " = "
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_TO
                    + " AND "
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneTravelTime.COLUMN_FROM_LOCATION
                    + " = node1." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
                    + " AND "
                    + BitCarrierSilverstoneTravelTime.TABLE_NAME + "."
                    + BitCarrierSilverstoneTravelTime.COLUMN_TO_LOCATION
                    + " = node2." + BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID
                    + " AND "
                    + BitCarrierSilverstoneVector.TABLE_NAME + "."
                    + BitCarrierSilverstoneVector.COLUMN_SKETCH_ID + " IS NOT NULL;";

    private BcsContract() {
    }

    public static final class BitCarrierSilverstoneConfigSketch implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_config_sketch";
        public static final String COLUMN_SKETCH_ID = "sketch_id";
        public static final String COLUMN_VECTOR_ID = "vector_id";
        public static final String COLUMN_COORDINATES = "coordinates";
    }

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

    public static final class BitCarrierSilverstoneRoute implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_route";
        public static final String COLUMN_ROUTE_ID = "route_id";
        public static final String COLUMN_CITY_ID = "city_id";
        public static final String COLUMN_METAVECTOR_ID = "metavector";
        public static final String COLUMN_VECTOR1 = "vector1";
        public static final String COLUMN_VECTOR1_CONTRIB = "vector1_contrib";
        public static final String COLUMN_VECTOR2 = "vector2";
        public static final String COLUMN_VECTOR2_CONTRIB = "vector2_contrib";
        public static final String COLUMN_OFFSET = "offset";
        public static final String COLUMN_DISTANCE = "distance";
        public static final String COLUMN_ROUTE_DETECTIONS_MIN = "route_detections_min";
        public static final String COLUMN_ZONE_ID = "zone";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
        public static final String COLUMN_CONFIGURATION = "configuration";
    }

    public static final class BitCarrierSilverstoneMetavector implements BcsBaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_metavector";
        public static final String COLUMN_METAVECTOR_ID = "metavector_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CUSTOMER_NAME = "customer_name";
        public static final String COLUMN_SEQUENCE = "sequence";
        public static final String COLUMN_ZONE = "zone";
        public static final String COLUMN_CITY_ID = "city_id";
    }

    public static final class BitCarrierSilverstoneTravelTime implements BaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_travel_time";
        public static final String COLUMN_TRAVEL_TIME_ID = "travel_time_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_HOUR = "hour";
        public static final String COLUMN_FROM_LOCATION = "from_location";
        public static final String COLUMN_TO_LOCATION = "to_location";
        public static final String COLUMN_SCORE = "score";
        public static final String COLUMN_SPEED = "speed";
        public static final String COLUMN_ELAPSED = "elapsed";
        public static final String COLUMN_TREND = "trend";
    }

    public static final class BitCarrierSilverstoneVector implements BaseColumns {
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

    public static final class BitCarrierSilverstoneLatestTravelTime implements BaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_latest_travel_time";
        public static final String COLUMN_TRAVEL_TIME_ID = "travel_time_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_HOUR = "hour";
        public static final String COLUMN_FROM_LOCATION = "from_location";
        public static final String COLUMN_TO_LOCATION = "to_location";
        public static final String COLUMN_SCORE = "score";
        public static final String COLUMN_SPEED = "speed";
        public static final String COLUMN_ELAPSED = "elapsed";
        public static final String COLUMN_TREND = "trend";
    }

    public static final class BitCarrierSilverstoneLatestVectorTravelTime implements BaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_latest_vector_travel_time";
        public static final String COLUMN_FROM_CUSTOMER_ID = "from_customer_id";
        public static final String COLUMN_TO_CUSTOMER_ID = "to_customer_id";
        public static final String COLUMN_SKETCH_ID = "sketch_id";
        public static final String COLUMN_TRAVEL_TIME_ID =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_TRAVEL_TIME_ID;
        public static final String COLUMN_DATE =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_DATE;
        public static final String COLUMN_HOUR =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_HOUR;
        public static final String COLUMN_FROM_LOCATION =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_FROM_LOCATION;
        public static final String COLUMN_TO_LOCATION =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_TO_LOCATION;
        public static final String COLUMN_SCORE =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_SCORE;
        public static final String COLUMN_SPEED =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_SPEED;
        public static final String COLUMN_ELAPSED =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_ELAPSED;
        public static final String COLUMN_TREND =
                BitCarrierSilverstoneLatestTravelTime.COLUMN_TREND;
    }

    public static final class BitCarrierSilverstoneVectorTravelTime implements BaseColumns {
        public static final String TABLE_NAME = "bit_carrier_silverstone_vector_travel_time";
        public static final String COLUMN_FROM_CUSTOMER_ID = "from_customer_id";
        public static final String COLUMN_TO_CUSTOMER_ID = "to_customer_id";
        public static final String COLUMN_FROM_CUSTOMER_NAME = "from_customer_name";
        public static final String COLUMN_TO_CUSTOMER_NAME = "to_customer_name";
        public static final String COLUMN_SKETCH_ID = "sketch_id";
        public static final String COLUMN_TRAVEL_TIME_ID =
                BitCarrierSilverstoneTravelTime.COLUMN_TRAVEL_TIME_ID;
        public static final String COLUMN_DATE =
                BitCarrierSilverstoneTravelTime.COLUMN_DATE;
        public static final String COLUMN_HOUR =
                BitCarrierSilverstoneTravelTime.COLUMN_HOUR;
        public static final String COLUMN_FROM_LOCATION =
                BitCarrierSilverstoneTravelTime.COLUMN_FROM_LOCATION;
        public static final String COLUMN_TO_LOCATION =
                BitCarrierSilverstoneTravelTime.COLUMN_TO_LOCATION;
        public static final String COLUMN_SCORE =
                BitCarrierSilverstoneTravelTime.COLUMN_SCORE;
        public static final String COLUMN_SPEED =
                BitCarrierSilverstoneTravelTime.COLUMN_SPEED;
        public static final String COLUMN_ELAPSED =
                BitCarrierSilverstoneTravelTime.COLUMN_ELAPSED;
        public static final String COLUMN_TREND =
                BitCarrierSilverstoneTravelTime.COLUMN_TREND;
    }

}
