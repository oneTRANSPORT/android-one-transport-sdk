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
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.LastUpdated;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.RoadWorks;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.TrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VariableMessageSign;

public class BucksProvider extends ContentProvider {

    // Not final as the authority must be injected by the app.
    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri CAR_PARK_URI;
    public static Uri VARIABLE_MESSAGE_SIGN_URI;
    public static Uri TRAFFIC_FLOW_URI;
    public static Uri ROAD_WORKS_URI;
    public static Uri LAST_UPDATED_URI;

    // Content MIME types.
    private static String MIME_DIR_PREFIX;
    private static String MIME_ITEM_PREFIX;

    // Uri matching
    private static final int CAR_PARKS = 1;
    private static final int CAR_PARK_ID = 2;
    private static final int VARIABLE_MESSAGE_SIGNS = 7;
    private static final int VARIABLE_MESSAGE_SIGN_ID = 8;
    private static final int TRAFFIC_FLOWS = 9;
    private static final int TRAFFIC_FLOW_ID = 10;
    private static final int ROAD_WORKS = 11;
    private static final int ROAD_WORKS_ID = 12;
    private static final int LAST_UPDATED = 13;
    private static final int LAST_UPDATED_ID = 14;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private BucksDbHelper bucksDbHelper;

    public BucksProvider() {
    }

    public static void initialise(@NonNull Context context) {
        AUTHORITY = context.getString(R.string.provider_authority);
        AUTHORITY_URI = Uri.parse("content://" + AUTHORITY + "/");

        CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI, CarPark.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                VariableMessageSign.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI, TrafficFlow.TABLE_NAME);
        ROAD_WORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, RoadWorks.TABLE_NAME);
        LAST_UPDATED_URI = Uri.withAppendedPath(AUTHORITY_URI, LastUpdated.TABLE_NAME);

        MIME_DIR_PREFIX = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".";
        MIME_ITEM_PREFIX = "vnd.android.cursor.item/vnd." + AUTHORITY + ".";

        if (uriMatcher.match(CAR_PARK_URI) == -1) {
            uriMatcher.addURI(AUTHORITY, CarPark.TABLE_NAME, CAR_PARKS);
            uriMatcher.addURI(AUTHORITY, CarPark.TABLE_NAME + "/#", CAR_PARK_ID);
            uriMatcher.addURI(AUTHORITY, VariableMessageSign.TABLE_NAME,
                    VARIABLE_MESSAGE_SIGNS);
            uriMatcher.addURI(AUTHORITY, VariableMessageSign.TABLE_NAME + "/#",
                    VARIABLE_MESSAGE_SIGN_ID);
            uriMatcher.addURI(AUTHORITY, TrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
            uriMatcher.addURI(AUTHORITY, TrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
            uriMatcher.addURI(AUTHORITY, RoadWorks.TABLE_NAME, ROAD_WORKS);
            uriMatcher.addURI(AUTHORITY, RoadWorks.TABLE_NAME + "/#", ROAD_WORKS_ID);
            uriMatcher.addURI(AUTHORITY, LastUpdated.TABLE_NAME, LAST_UPDATED);
            uriMatcher.addURI(AUTHORITY, LastUpdated.TABLE_NAME + "/#", LAST_UPDATED_ID);
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
            case VARIABLE_MESSAGE_SIGNS:
                return MIME_DIR_PREFIX + VariableMessageSign.TABLE_NAME;
            case VARIABLE_MESSAGE_SIGN_ID:
                return MIME_ITEM_PREFIX + VariableMessageSign.TABLE_NAME;
            case TRAFFIC_FLOWS:
                return MIME_DIR_PREFIX + TrafficFlow.TABLE_NAME;
            case TRAFFIC_FLOW_ID:
                return MIME_ITEM_PREFIX + TrafficFlow.TABLE_NAME;
            case ROAD_WORKS:
                return MIME_DIR_PREFIX + RoadWorks.TABLE_NAME;
            case ROAD_WORKS_ID:
                return MIME_ITEM_PREFIX + RoadWorks.TABLE_NAME;
            case LAST_UPDATED:
                return MIME_DIR_PREFIX + LastUpdated.TABLE_NAME;
            case LAST_UPDATED_ID:
                return MIME_ITEM_PREFIX + LastUpdated.TABLE_NAME;
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
            case VARIABLE_MESSAGE_SIGNS:
                id = db.insert(VariableMessageSign.TABLE_NAME, null, values);
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
                return ContentUris.withAppendedId(VARIABLE_MESSAGE_SIGN_URI, id);
            case TRAFFIC_FLOWS:
                id = db.insert(TrafficFlow.TABLE_NAME, null, values);
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
            case ROAD_WORKS:
                id = db.insert(RoadWorks.TABLE_NAME, null, values);
                contentResolver.notifyChange(ROAD_WORKS_URI, null);
                return ContentUris.withAppendedId(ROAD_WORKS_URI, id);
            case LAST_UPDATED:
                throw new IllegalArgumentException("Insert not allowed into " + LastUpdated.TABLE_NAME);
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
            case ROAD_WORKS:
                cursor = db.query(RoadWorks.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, ROAD_WORKS_URI);
                return cursor;
            case ROAD_WORKS_ID:
                cursor = db.query(RoadWorks.TABLE_NAME, projection,
                        RoadWorks._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                        sortOrder);
                cursor.setNotificationUri(contentResolver, ROAD_WORKS_URI);
                return cursor;
            case LAST_UPDATED:
                cursor = db.query(LastUpdated.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, LAST_UPDATED_URI);
                return cursor;
            case LAST_UPDATED_ID:
                cursor = db.query(LastUpdated.TABLE_NAME, projection,
                        LastUpdated._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                        sortOrder);
                cursor.setNotificationUri(contentResolver, LAST_UPDATED_URI);
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
            case ROAD_WORKS:
                rows = db.update(RoadWorks.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(ROAD_WORKS_URI, null);
                return rows;
            case ROAD_WORKS_ID:
                rows = db.update(RoadWorks.TABLE_NAME, values,
                        RoadWorks._ID + "=?", new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(ROAD_WORKS_URI, null);
                return rows;
            case LAST_UPDATED:
                rows = db.update(LastUpdated.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(LAST_UPDATED_URI, null);
                return rows;
            case LAST_UPDATED_ID:
                rows = db.update(LastUpdated.TABLE_NAME, values,
                        LastUpdated._ID + "=?", new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(LAST_UPDATED_URI, null);
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
            case VARIABLE_MESSAGE_SIGNS:
                rows = db.delete(VariableMessageSign.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
                break;
            case TRAFFIC_FLOWS:
                rows = db.delete(TrafficFlow.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                break;
            case ROAD_WORKS:
                rows = db.delete(RoadWorks.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(ROAD_WORKS_URI, null);
                break;
            case LAST_UPDATED:
                throw new IllegalArgumentException("Delete not allowed from " + LastUpdated.TABLE_NAME);
        }
        return rows;
    }

}
