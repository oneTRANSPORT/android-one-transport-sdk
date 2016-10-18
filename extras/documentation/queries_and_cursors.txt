SQL queries and cursors
=======================

The getter methods provided by the content helper classes described in the
previous section have equivalents that return cursors instead of objects.

If you need a cursor to inject into an adapter view, then these are for you.
Remember to close the cursor when you have finished with it.  For example,
retrieving the latest roadworks in Hertfordshire:

        Cursor roadworksCursor = HertsContentHelper.getLatestRoadworksCursor(context);

The methods to inflate objects from cursors are also publicly available.
Here is the implementation of `getLatestRoadworks`:

        public static Roadworks[] getLatestRoadworks(@NonNull Context context) {
            return roadworksesFromCursor(getLatestRoadworksCursor(context));
        }

The full list of cursor object extraction methods for local authorities is:

        public static CarPark[] carParksFromCursor(Cursor cursor)
        public static Event[] eventsFromCursor(Cursor cursor)
        public static Roadworks[] roadworksesFromCursor(Cursor cursor)
        public static TrafficFlow[] trafficFlowsFromCursor(Cursor cursor)
        public static TrafficScoot[] trafficScootsFromCursor(Cursor cursor)
        public static TrafficSpeed[] trafficSpeedsFromCursor(Cursor cursor)
        public static TrafficTravelTime[] trafficTravelTimesFromCursor(Cursor cursor)
        public static VariableMessageSign[] variableMessageSignsFromCursor(Cursor cursor)

BitCarrier and Clearview helper classes have similar methods to suit their own
resource types.

# SQL contract classes

If you are going to work with cursors, you need to know the SQL table
structure so you can extract the columns you need.  Each module in the
oneTransport SDK has a contract class that defines the SQL tables that module
uses and also contains SQL commands to create the tables from scratch.

The classes are:

        net.uk.onetransport.android.county.bucks.provider.BucksContract
        net.uk.onetransport.android.county.herts.provider.HertsContract
        net.uk.onetransport.android.county.northants.provider.NorthantsContract
        net.uk.onetransport.android.county.oxon.provider.OxonContract
        net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract
        net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract

Taking Hertfordshire as an example, here is the definition of the columns in
the variable message sign table:

        public static final class HertsVariableMessageSign implements CommonBaseColumns {
            public static final String TABLE_NAME = "herts_variable_message_sign";
            public static final String COLUMN_LOCATION_ID = "location_id";
            public static final String COLUMN_DESCRIPTION = "description";
            public static final String COLUMN_VMS_TYPE = "vms_type";
            public static final String COLUMN_LATITUDE = "latitude";
            public static final String COLUMN_LONGITUDE = "longitude";
            public static final String COLUMN_NUMBER_OF_CHARACTERS = "number_of_characters";
            public static final String COLUMN_NUMBER_OF_ROWS = "number_of_rows";
            public static final String COLUMN_VMS_LEGENDS = "vms_legends";
        }

Usually Android supplies a `BaseColumns` class so that every table has a
field named `_id`.  We have extended that to include details of the content
instance that was the source for the row so that we can avoid adding
duplicate rows.

        public interface CommonBaseColumns extends BaseColumns {
            String COLUMN_CIN_ID = "cin_id";
            String COLUMN_CREATION_TIME = "creation_time";
        }

The SQL create statement for this table adds a unique index covering the
main identifier and the content instance identifier:

        public static final String CREATE_VARIABLE_MESSAGE_SIGN_TABLE =
              "CREATE TABLE IF NOT EXISTS " + HertsVariableMessageSign.TABLE_NAME + " ("
             + HertsVariableMessageSign._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
             + HertsVariableMessageSign.COLUMN_LOCATION_ID + " TEXT NOT NULL,"
             + HertsVariableMessageSign.COLUMN_DESCRIPTION + " TEXT,"
             + HertsVariableMessageSign.COLUMN_VMS_TYPE + " TEXT,"
             + HertsVariableMessageSign.COLUMN_LATITUDE + " REAL,"
             + HertsVariableMessageSign.COLUMN_LONGITUDE + " REAL,"
             + HertsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS + " INTEGER,"
             + HertsVariableMessageSign.COLUMN_NUMBER_OF_ROWS + " INTEGER,"
             + HertsVariableMessageSign.COLUMN_VMS_LEGENDS + " TEXT,"
             + HertsVariableMessageSign.COLUMN_CIN_ID + " TEXT NOT NULL,"
             + HertsVariableMessageSign.COLUMN_CREATION_TIME + " INTEGER,"
             + "UNIQUE ("
             + HertsVariableMessageSign.COLUMN_LOCATION_ID + ","
             + HertsVariableMessageSign.COLUMN_CIN_ID
             + ") ON CONFLICT IGNORE);";

In order to produce a list of rows containing the latest data (from the
latest content instance) we need to do a join.  However, since the content
provider interface doesn't allow for join queries like this, we create a SQL
view that the content provider can query like a table.

        public static final class HertsLatestVariableMessageSign {
            public static final String TABLE_NAME = "herts_latest_variable_message_sign";
        }

        public static final String CREATE_LATEST_VARIABLE_MESSAGE_SIGN_TABLE =
              "CREATE VIEW IF NOT EXISTS " + HertsLatestVariableMessageSign.TABLE_NAME + " AS "
            + "SELECT a.* FROM " + HertsVariableMessageSign.TABLE_NAME + " AS a "
            + "INNER JOIN (SELECT " + HertsVariableMessageSign.COLUMN_LOCATION_ID
            + ", MAX(" + HertsVariableMessageSign.COLUMN_CREATION_TIME + ") AS "
            + HertsVariableMessageSign.COLUMN_CREATION_TIME + " FROM "
            + HertsVariableMessageSign.TABLE_NAME + " GROUP BY "
            + HertsVariableMessageSign.COLUMN_LOCATION_ID + ") AS b ON a."
            + HertsVariableMessageSign.COLUMN_LOCATION_ID + "=b."
            + HertsVariableMessageSign.COLUMN_LOCATION_ID + " AND a."
            + HertsVariableMessageSign.COLUMN_CREATION_TIME + "=b."
            + HertsVariableMessageSign.COLUMN_CREATION_TIME + ";";

## SQL queries

Custom queries using the content resolver interface are now possible.  Here
is the definition for `getCarParkCursor`:

        public static Cursor getCarParkCursor(@NonNull Context context) {
            return context.getContentResolver().query(BucksProviderModule.CAR_PARK_URI,
                   new String[]{"*"}, null, null, BucksCarPark.COLUMN_CAR_PARK_IDENTITY);
        }

The full list of module provider URIs is available in each of the provider
module classes, such as `BucksProviderModule`:

        public static Uri CAR_PARK_URI;
        public static Uri LATEST_CAR_PARK_URI;
        public static Uri EVENT_URI;
        public static Uri LATEST_EVENT_URI;
        public static Uri ROADWORKS_URI;
        public static Uri LATEST_ROADWORKS_URI;
        public static Uri TRAFFIC_FLOW_URI;
        public static Uri LATEST_TRAFFIC_FLOW_URI;
        public static Uri TRAFFIC_QUEUE_URI;
        public static Uri LATEST_TRAFFIC_QUEUE_URI;
        public static Uri TRAFFIC_SCOOT_URI;
        public static Uri LATEST_TRAFFIC_SCOOT_URI;
        public static Uri TRAFFIC_SPEED_URI;
        public static Uri LATEST_TRAFFIC_SPEED_URI;
        public static Uri TRAFFIC_TRAVEL_TIME_URI;
        public static Uri LATEST_TRAFFIC_TRAVEL_TIME_URI;
        public static Uri VARIABLE_MESSAGE_SIGN_URI;
        public static Uri LATEST_VARIABLE_MESSAGE_SIGN_URI;

Each URI maps to a table in the usual way, and in conjunction with the
table classes in `BucksContract` can be used to construct SQL queries.
For example, getting all the car park names in reverse alphabetical order:

        Cursor cursor = context.getContentResolver().query(BucksProviderModule.CAR_PARK_URI,
                           new String[]{BucksCarPark.COLUMN_PARKING_AREA_NAME}, null, null,
                           BucksCarPark.COLUMN_CAR_PARK_IDENTITY + " DESC");
