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
import android.support.annotation.NonNull;

import net.uk.onetransport.android.county.bucks.R;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.CarPark;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.SegmentLocation;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.TrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.TrafficFlowJoinLocation;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VariableMessageSign;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VmsJoinLocation;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VmsLocation;

public class BucksProvider extends ContentProvider {

    // Not final as the authority must be injected by the app.
    public static String AUTHORITY;
    public static String AUTHORITY_URI;

    public static Uri CAR_PARK_URI;
    public static Uri VMS_LOCATION_URI;
    public static Uri SEGMENT_LOCATION_URI;
    public static Uri VARIABLE_MESSAGE_SIGN_URI;
    public static Uri TRAFFIC_FLOW_URI;
    public static Uri VMS_JOIN_LOCATION_URI;
    public static Uri TRAFFIC_FLOW_JOIN_LOCATION_URI;

    // Content MIME types.
    private static String MIME_DIR_PREFIX;
    private static String MIME_ITEM_PREFIX;

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
    private static final int VMS_JOIN_LOCATIONS = 11;
    private static final int VMS_JOIN_LOCATION_ID = 12;
    private static final int TRAFFIC_FLOWS_JOIN_LOCATIONS = 13;
    private static final int TRAFFIC_FLOW_JOIN_LOCATION_ID = 14;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private BucksDbHelper bucksDbHelper;

    public BucksProvider() {
    }

    public static void initialise(@NonNull Context context) {
        // TODO Use string resources?  Override by app resources.
        AUTHORITY = context.getString(R.string.provider_authority);
        AUTHORITY_URI = "content://" + AUTHORITY + "/";

        CAR_PARK_URI = Uri.parse(AUTHORITY_URI + CarPark.TABLE_NAME);
        VMS_LOCATION_URI = Uri.parse(AUTHORITY_URI + VmsLocation.TABLE_NAME);
        SEGMENT_LOCATION_URI = Uri.parse(AUTHORITY_URI + SegmentLocation.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.parse(AUTHORITY_URI
                + VariableMessageSign.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.parse(AUTHORITY_URI + TrafficFlow.TABLE_NAME);
        VMS_JOIN_LOCATION_URI = Uri.parse(AUTHORITY_URI + VmsJoinLocation.TABLE_NAME);
        TRAFFIC_FLOW_JOIN_LOCATION_URI = Uri.parse(AUTHORITY_URI
                + TrafficFlowJoinLocation.TABLE_NAME);

        MIME_DIR_PREFIX = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".";
        MIME_ITEM_PREFIX = "vnd.android.cursor.item/vnd." + AUTHORITY + ".";

        if (uriMatcher.match(CAR_PARK_URI) == -1) {
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
            uriMatcher.addURI(AUTHORITY, TrafficFlow.TABLE_NAME + "/#",
                    TRAFFIC_FLOW_ID);
            uriMatcher.addURI(AUTHORITY, VmsJoinLocation.TABLE_NAME, VMS_JOIN_LOCATIONS);
            uriMatcher.addURI(AUTHORITY, VariableMessageSign.TABLE_NAME + "/#",
                    VMS_JOIN_LOCATION_ID);
            uriMatcher.addURI(AUTHORITY, TrafficFlowJoinLocation.TABLE_NAME,
                    TRAFFIC_FLOWS_JOIN_LOCATIONS);
            uriMatcher.addURI(AUTHORITY, TrafficFlowJoinLocation.TABLE_NAME + "/#",
                    TRAFFIC_FLOW_JOIN_LOCATION_ID);
        }
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
                return MIME_DIR_PREFIX + CarPark.TABLE_NAME;
            case CAR_PARK_ID:
                return MIME_ITEM_PREFIX + CarPark.TABLE_NAME;
            case VMS_LOCATIONS:
                return MIME_DIR_PREFIX + VmsLocation.TABLE_NAME;
            case VMS_LOCATION_ID:
                return MIME_ITEM_PREFIX + VmsLocation.TABLE_NAME;
            case SEGMENT_LOCATIONS:
                return MIME_DIR_PREFIX + SegmentLocation.TABLE_NAME;
            case SEGMENT_LOCATION_ID:
                return MIME_ITEM_PREFIX + SegmentLocation.TABLE_NAME;
            case VARIABLE_MESSAGE_SIGNS:
                return MIME_DIR_PREFIX + VariableMessageSign.TABLE_NAME;
            case VARIABLE_MESSAGE_SIGN_ID:
                return MIME_ITEM_PREFIX + VariableMessageSign.TABLE_NAME;
            case TRAFFIC_FLOWS:
                return MIME_DIR_PREFIX + TrafficFlow.TABLE_NAME;
            case TRAFFIC_FLOW_ID:
                return MIME_ITEM_PREFIX + TrafficFlow.TABLE_NAME;
            case VMS_JOIN_LOCATIONS:
                return MIME_DIR_PREFIX + VmsJoinLocation.TABLE_NAME;
            case VMS_JOIN_LOCATION_ID:
                return MIME_ITEM_PREFIX + VmsJoinLocation.TABLE_NAME;
            case TRAFFIC_FLOWS_JOIN_LOCATIONS:
                return MIME_DIR_PREFIX + TrafficFlowJoinLocation.TABLE_NAME;
            case TRAFFIC_FLOW_JOIN_LOCATION_ID:
                return MIME_ITEM_PREFIX + TrafficFlowJoinLocation.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @NonNull ContentValues values) {
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
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
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
            case VMS_JOIN_LOCATIONS:
                cursor = db.query(VmsJoinLocation.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, VMS_JOIN_LOCATION_URI);
                return cursor;
            case VMS_JOIN_LOCATION_ID:
                cursor = db.query(VmsJoinLocation.TABLE_NAME, projection,
                        VmsJoinLocation._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                        sortOrder);
                cursor.setNotificationUri(contentResolver, VMS_JOIN_LOCATION_URI);
                return cursor;
            case TRAFFIC_FLOWS_JOIN_LOCATIONS:
                cursor = db.query(TrafficFlowJoinLocation.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_JOIN_LOCATION_URI);
                return cursor;
            case TRAFFIC_FLOW_JOIN_LOCATION_ID:
                cursor = db.query(TrafficFlowJoinLocation.TABLE_NAME, projection,
                        TrafficFlowJoinLocation._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                        sortOrder);
                cursor.setNotificationUri(contentResolver, TRAFFIC_FLOW_JOIN_LOCATION_URI);
                return cursor;
        }
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
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
                rows = db.update(TrafficFlow.TABLE_NAME, values,
                        TrafficFlow._ID + "=?", new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                return rows;
        }
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
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

}
