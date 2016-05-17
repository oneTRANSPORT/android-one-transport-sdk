package net.uk.onetransport.android.county.bucks.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.CarPark;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.SegmentLocation;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.TrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VariableMessageSign;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VmsLocation;

public class BucksProvider extends ContentProvider {

    public static final String AUTHORITY = "net.uk.oneTransport.android.county.bucks.provider";
    public static final String AUTHORITY_URI = "content://" + AUTHORITY + "/";

    public static final Uri CAR_PARK_URI = Uri.parse(AUTHORITY_URI + CarPark.TABLE_NAME);
    public static final Uri VMS_LOCATION_URI = Uri.parse(AUTHORITY_URI
            + VmsLocation.TABLE_NAME);
    public static final Uri SEGMENT_LOCATION_URI = Uri.parse(AUTHORITY_URI
            + SegmentLocation.TABLE_NAME);
    public static final Uri VARIABLE_MESSAGE_SIGN_URI = Uri.parse(AUTHORITY_URI
            + VariableMessageSign.TABLE_NAME);
    public static final Uri TRAFFIC_FLOW_URI = Uri.parse(AUTHORITY_URI
            + TrafficFlow.TABLE_NAME);

    // Content MIME types.
    private static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".";
    private static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item/vnd." + AUTHORITY + ".";

    private static final String CAR_PARKS_MIME_TYPE = MIME_DIR_PREFIX
            + CarPark.TABLE_NAME;
    private static final String CAR_PARK_ID_MIME_TYPE = MIME_ITEM_PREFIX
            + CarPark.TABLE_NAME;
    private static final String VMS_LOCATIONS_MIME_TYPE = MIME_DIR_PREFIX
            + VmsLocation.TABLE_NAME;
    private static final String VMS_LOCATION_ID_MIME_TYPE = MIME_ITEM_PREFIX
            + VmsLocation.TABLE_NAME;
    private static final String SEGMENT_LOCATIONS_MIME_TYPE = MIME_DIR_PREFIX
            + SegmentLocation.TABLE_NAME;
    private static final String SEGMENT_LOCATION_ID_MIME_TYPE = MIME_ITEM_PREFIX
            + SegmentLocation.TABLE_NAME;
    private static final String VARIABLE_MESSAGE_SIGNS_MIME_TYPE = MIME_DIR_PREFIX
            + VariableMessageSign.TABLE_NAME;
    private static final String VARIABLE_MESSAGE_SIGN_ID_MIME_TYPE = MIME_ITEM_PREFIX
            + VariableMessageSign.TABLE_NAME;
    private static final String TRAFFIC_FLOWS_MIME_TYPE = MIME_DIR_PREFIX
            + TrafficFlow.TABLE_NAME;
    private static final String TRAFFIC_FLOW_ID_MIME_TYPE = MIME_ITEM_PREFIX
            + TrafficFlow.TABLE_NAME;

    // Uri matching
    private static final int CAR_PARKS = 1;
    private static final int CAR_PARK_ID = 2;
    private static final int VMS_LOCATIONS = 3;
    private static final int VMS_LOCATION_ID = 4;
    private static final int SEGMENT_LOCATIONS = 5;
    private static final int SEGMENT_LOCATION_ID = 6;
    private static final int VARIABLE_MESSAGE_SIGNS = 7;
    private static final int VARIABLE_MESSAGE_SIGN_ID = 8;
    private static final int TRAFFIC_FLOWS = 9;
    private static final int TRAFFIC_FLOW_ID = 10;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private BucksDbHelper bucksDbHelper;

    public BucksProvider() {
        uriMatcher.addURI(AUTHORITY, CarPark.TABLE_NAME, CAR_PARKS);
        uriMatcher.addURI(AUTHORITY, CarPark.TABLE_NAME + "/#", CAR_PARK_ID);
        uriMatcher.addURI(AUTHORITY, VmsLocation.TABLE_NAME, VMS_LOCATIONS);
        uriMatcher.addURI(AUTHORITY, VmsLocation.TABLE_NAME + "/#", VMS_LOCATION_ID);
        uriMatcher.addURI(AUTHORITY, SegmentLocation.TABLE_NAME, SEGMENT_LOCATIONS);
        uriMatcher.addURI(AUTHORITY, SegmentLocation.TABLE_NAME + "/#",
                SEGMENT_LOCATION_ID);
        uriMatcher.addURI(AUTHORITY, VariableMessageSign.TABLE_NAME,
                VARIABLE_MESSAGE_SIGNS);
        uriMatcher.addURI(AUTHORITY, VariableMessageSign.TABLE_NAME + "/#",
                VARIABLE_MESSAGE_SIGN_ID);
        uriMatcher.addURI(AUTHORITY, TrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
        uriMatcher.addURI(AUTHORITY, TrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
    }

    @Override
    public boolean onCreate() {
        bucksDbHelper = new BucksDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CAR_PARKS:
                return CAR_PARKS_MIME_TYPE;
            case CAR_PARK_ID:
                return CAR_PARK_ID_MIME_TYPE;
            case VMS_LOCATIONS:
                return VMS_LOCATIONS_MIME_TYPE;
            case VMS_LOCATION_ID:
                return VMS_LOCATION_ID_MIME_TYPE;
            case SEGMENT_LOCATIONS:
                return SEGMENT_LOCATIONS_MIME_TYPE;
            case SEGMENT_LOCATION_ID:
                return SEGMENT_LOCATION_ID_MIME_TYPE;
            case VARIABLE_MESSAGE_SIGNS:
                return VARIABLE_MESSAGE_SIGNS_MIME_TYPE;
            case VARIABLE_MESSAGE_SIGN_ID:
                return VARIABLE_MESSAGE_SIGN_ID_MIME_TYPE;
            case TRAFFIC_FLOWS:
                return TRAFFIC_FLOWS_MIME_TYPE;
            case TRAFFIC_FLOW_ID:
                return TRAFFIC_FLOW_ID_MIME_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id;
        SQLiteDatabase db = bucksDbHelper.getWritableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case CAR_PARKS:
                id = db.insert(CarPark.TABLE_NAME, null, values);
                contentResolver.notifyChange(CAR_PARK_URI, null);
                return ContentUris.withAppendedId(CAR_PARK_URI, id);
            case VMS_LOCATIONS:
                id = db.insert(VmsLocation.TABLE_NAME, null, values);
                contentResolver.notifyChange(VMS_LOCATION_URI, null);
                return ContentUris.withAppendedId(VMS_LOCATION_URI, id);
            case SEGMENT_LOCATIONS:
                id = db.insert(SegmentLocation.TABLE_NAME, null, values);
                contentResolver.notifyChange(SEGMENT_LOCATION_URI, null);
                return ContentUris.withAppendedId(SEGMENT_LOCATION_URI, id);
            case VARIABLE_MESSAGE_SIGNS:
                id = db.insert(VariableMessageSign.TABLE_NAME, null, values);
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
                return ContentUris.withAppendedId(VARIABLE_MESSAGE_SIGN_URI, id);
            case TRAFFIC_FLOWS:
                id = db.insert(TrafficFlow.TABLE_NAME, null, values);
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = bucksDbHelper.getReadableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case CAR_PARKS:
                Cursor cursor = db.query(CarPark.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
                return cursor;
            case CAR_PARK_ID:
                cursor = db.query(CarPark.TABLE_NAME, projection,
                        CarPark._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, CAR_PARK_URI);
                return cursor;
            case VMS_LOCATIONS:
                cursor = db.query(VmsLocation.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, VMS_LOCATION_URI);
                return cursor;
            case VMS_LOCATION_ID:
                cursor = db.query(VmsLocation.TABLE_NAME, projection,
                        VmsLocation._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, VMS_LOCATION_URI);
                return cursor;
            case SEGMENT_LOCATIONS:
                cursor = db.query(SegmentLocation.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, SEGMENT_LOCATION_URI);
                return cursor;
            case SEGMENT_LOCATION_ID:
                cursor = db.query(SegmentLocation.TABLE_NAME, projection,
                        SegmentLocation._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, SEGMENT_LOCATION_URI);
                return cursor;
            case VARIABLE_MESSAGE_SIGNS:
                cursor = db.query(VariableMessageSign.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
                return cursor;
            case VARIABLE_MESSAGE_SIGN_ID:
                cursor = db.query(VariableMessageSign.TABLE_NAME, projection,
                        VariableMessageSign._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                        sortOrder);
                cursor.setNotificationUri(contentResolver, VARIABLE_MESSAGE_SIGN_URI);
                return cursor;
            case TRAFFIC_FLOWS:
                cursor = db.query(TrafficFlow.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
                return cursor;
            case TRAFFIC_FLOW_ID:
                cursor = db.query(TrafficFlow.TABLE_NAME, projection,
                        TrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                        sortOrder);
                cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_URI);
                return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = bucksDbHelper.getReadableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case CAR_PARKS:
                int rows = db.update(CarPark.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(CAR_PARK_URI, null);
                return rows;
            case CAR_PARK_ID:
                rows = db.update(CarPark.TABLE_NAME, values, CarPark._ID + "=?",
                        new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(CAR_PARK_URI, null);
                return rows;
            case VMS_LOCATIONS:
                rows = db.update(VmsLocation.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(VMS_LOCATION_URI, null);
                return rows;
            case VMS_LOCATION_ID:
                rows = db.update(VmsLocation.TABLE_NAME, values, VmsLocation._ID + "=?",
                        new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(VMS_LOCATION_URI, null);
                return rows;
            case SEGMENT_LOCATIONS:
                rows = db.update(SegmentLocation.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(SEGMENT_LOCATION_URI, null);
                return rows;
            case SEGMENT_LOCATION_ID:
                rows = db.update(SegmentLocation.TABLE_NAME, values, SegmentLocation._ID + "=?",
                        new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(SEGMENT_LOCATION_URI, null);
                return rows;
            case VARIABLE_MESSAGE_SIGNS:
                rows = db.update(VariableMessageSign.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
                return rows;
            case VARIABLE_MESSAGE_SIGN_ID:
                rows = db.update(VariableMessageSign.TABLE_NAME, values, VariableMessageSign._ID + "=?",
                        new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
                return rows;
            case TRAFFIC_FLOWS:
                rows = db.update(TrafficFlow.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                return rows;
            case TRAFFIC_FLOW_ID:
                rows = db.update(TrafficFlow.TABLE_NAME, values, TrafficFlow._ID + "=?",
                        new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                return rows;
        }
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = bucksDbHelper.getWritableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        int rows = 0;
        switch (uriMatcher.match(uri)) {
            case CAR_PARKS:
                rows = db.delete(CarPark.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(CAR_PARK_URI, null);
                break;
            case VMS_LOCATIONS:
                rows = db.delete(VmsLocation.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(VMS_LOCATION_URI, null);
                break;
            case SEGMENT_LOCATIONS:
                rows = db.delete(SegmentLocation.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(SEGMENT_LOCATION_URI, null);
                break;
            case VARIABLE_MESSAGE_SIGNS:
                rows = db.delete(VariableMessageSign.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
                break;
            case TRAFFIC_FLOWS:
                rows = db.delete(TrafficFlow.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                break;
        }
        return rows;
    }

    // Convenience methods for app developers.

    public static Cursor getCarParksByName(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.query(BucksProvider.CAR_PARK_URI, new String[]{
                BucksContract.CarPark._ID,
                BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY,
                BucksContract.CarPark.COLUMN_TOTAL_PARKING_CAPACITY,
                BucksContract.CarPark.COLUMN_ALMOST_FULL_DECREASING,
                BucksContract.CarPark.COLUMN_ALMOST_FULL_INCREASING,
                BucksContract.CarPark.COLUMN_FULL_DECREASING,
                BucksContract.CarPark.COLUMN_FULL_INCREASING,
                BucksContract.CarPark.COLUMN_ENTRANCE_FULL,
                BucksContract.CarPark.COLUMN_RADIUS,
                BucksContract.CarPark.COLUMN_LATITUDE,
                BucksContract.CarPark.COLUMN_LONGITUDE
        }, null, null, BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY);
    }

}
