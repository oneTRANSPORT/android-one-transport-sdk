package net.uk.onetransport.android.county.northants.provider;

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
import android.support.annotation.Nullable;

import net.uk.onetransport.android.county.northants.R;

import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.ClearviewData;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.ClearviewDevice;

public class NorthantsProvider extends ContentProvider {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri CLEARVIEW_DEVICE_URI;
    public static Uri CLEARVIEW_DATA_URI;

    private static String MIME_DIR_PREFIX;
    private static String MIME_ITEM_PREFIX;

    private static final int CLEARVIEW_DEVICES = 1;
    private static final int CLEARVIEW_DEVICE_ID = 2;
    private static final int CLEARVIEW_DATA = 3;
    private static final int CLEARVIEW_DATA_ID = 4;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private NorthantsDbHelper northantsDbHelper;

    public NorthantsProvider() {
    }

    public static void initialise(@NonNull Context context) {
        AUTHORITY = context.getString(R.string.provider_authority);
        AUTHORITY_URI = Uri.parse("content://" + AUTHORITY + "/");

        CLEARVIEW_DEVICE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                ClearviewDevice.TABLE_NAME);
        CLEARVIEW_DATA_URI = Uri.withAppendedPath(AUTHORITY_URI,
                ClearviewData.TABLE_NAME);

        MIME_DIR_PREFIX = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".";
        MIME_ITEM_PREFIX = "vnd.android.cursor.item/vnd." + AUTHORITY + ".";

        if (uriMatcher.match(CLEARVIEW_DEVICE_URI) == -1) {
            uriMatcher.addURI(AUTHORITY, ClearviewDevice.TABLE_NAME, CLEARVIEW_DEVICES);
            uriMatcher.addURI(AUTHORITY, ClearviewDevice.TABLE_NAME + "/#",
                    CLEARVIEW_DEVICE_ID);
            uriMatcher.addURI(AUTHORITY, ClearviewData.TABLE_NAME, CLEARVIEW_DATA);
            uriMatcher.addURI(AUTHORITY, ClearviewData.TABLE_NAME + "/#", CLEARVIEW_DATA_ID);
        }
    }

    @Override
    public boolean onCreate() {
        northantsDbHelper = new NorthantsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CLEARVIEW_DEVICES:
                return MIME_DIR_PREFIX + ClearviewDevice.TABLE_NAME;
            case CLEARVIEW_DEVICE_ID:
                return MIME_ITEM_PREFIX + ClearviewDevice.TABLE_NAME;
            case CLEARVIEW_DATA:
                return MIME_DIR_PREFIX + ClearviewData.TABLE_NAME;
            case CLEARVIEW_DATA_ID:
                return MIME_ITEM_PREFIX + ClearviewData.TABLE_NAME;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long id;
        SQLiteDatabase db = northantsDbHelper.getWritableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case CLEARVIEW_DEVICES:
                id = db.insert(ClearviewDevice.TABLE_NAME, null, values);
                contentResolver.notifyChange(CLEARVIEW_DEVICE_URI, null);
                return ContentUris.withAppendedId(CLEARVIEW_DEVICE_URI, id);
            case CLEARVIEW_DATA:
                id = db.insert(ClearviewData.TABLE_NAME, null, values);
                contentResolver.notifyChange(CLEARVIEW_DATA_URI, null);
                return ContentUris.withAppendedId(CLEARVIEW_DATA_URI, id);
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = northantsDbHelper.getReadableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case CLEARVIEW_DEVICES:
                Cursor cursor = db.query(ClearviewDevice.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, CLEARVIEW_DEVICE_URI);
                return cursor;
            case CLEARVIEW_DEVICE_ID:
                cursor = db.query(ClearviewDevice.TABLE_NAME, projection,
                        ClearviewDevice._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, CLEARVIEW_DEVICE_URI);
                return cursor;
            case CLEARVIEW_DATA:
                cursor = db.query(ClearviewData.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(contentResolver, CLEARVIEW_DATA_URI);
                return cursor;
            case CLEARVIEW_DATA_ID:
                cursor = db.query(ClearviewData.TABLE_NAME, projection,
                        ClearviewData._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                        sortOrder);
                cursor.setNotificationUri(contentResolver, CLEARVIEW_DATA_URI);
                return cursor;
        }
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = northantsDbHelper.getReadableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case CLEARVIEW_DEVICES:
                int rows = db.update(ClearviewDevice.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(CLEARVIEW_DEVICE_URI, null);
                return rows;
            case CLEARVIEW_DEVICE_ID:
                rows = db.update(ClearviewDevice.TABLE_NAME, values, ClearviewDevice._ID + "=?",
                        new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(CLEARVIEW_DEVICE_URI, null);
                return rows;
            case CLEARVIEW_DATA:
                rows = db.update(ClearviewData.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(CLEARVIEW_DATA_URI, null);
                return rows;
            case CLEARVIEW_DATA_ID:
                rows = db.update(ClearviewData.TABLE_NAME, values, ClearviewData._ID + "=?",
                        new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(CLEARVIEW_DATA_URI, null);
                return rows;
        }
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = northantsDbHelper.getWritableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        int rows = 0;
        switch (uriMatcher.match(uri)) {
            case CLEARVIEW_DEVICES:
                rows = db.delete(ClearviewDevice.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(CLEARVIEW_DEVICE_URI, null);
                break;
            case CLEARVIEW_DATA:
                rows = db.delete(ClearviewData.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(CLEARVIEW_DATA_URI, null);
                break;
        }
        return rows;
    }
}
