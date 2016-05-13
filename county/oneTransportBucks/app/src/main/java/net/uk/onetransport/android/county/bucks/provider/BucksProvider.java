package net.uk.onetransport.android.county.bucks.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.CarPark;

public class BucksProvider extends ContentProvider {

    public static final String AUTHORITY = "net.uk.oneTransport.android.county.bucks.provider";
    public static final String AUTHORITY_URI = "content://" + AUTHORITY + "/";
    public static final Uri CAR_PARK_URI = Uri.parse(AUTHORITY_URI + CarPark.TABLE_NAME);

    // Content MIME types.
    private static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item/vnd." + AUTHORITY + ".";
    private static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".";
    private static final String CAR_PARKS_MIME_TYPE = MIME_DIR_PREFIX
            + CarPark.TABLE_NAME;
    private static final String CAR_PARK_ID_MIME_TYPE = MIME_ITEM_PREFIX
            + CarPark.TABLE_NAME;

    // Uri matching
    private static final int CAR_PARKS = 1;
    private static final int CAR_PARK_ID = 2;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private BucksDbHelper bucksDbHelper;

    public BucksProvider() {
        uriMatcher.addURI(AUTHORITY, CarPark.TABLE_NAME, CAR_PARKS);
        uriMatcher.addURI(AUTHORITY, CarPark.TABLE_NAME + "/#", CAR_PARK_ID);
    }

    @Override
    public boolean onCreate() {
        bucksDbHelper = new BucksDbHelper(getContext());
        return true;
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
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CAR_PARKS:
                return CAR_PARKS_MIME_TYPE;
            case CAR_PARK_ID:
                return CAR_PARK_ID_MIME_TYPE;
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
        }
        return null;
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
        }
        return rows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
