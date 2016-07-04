package net.uk.onetransport.android.modules.common.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.common.R;

import java.util.ArrayList;

import static net.uk.onetransport.android.modules.common.provider.CommonContract.LastUpdated;

public class CommonProvider extends ContentProvider {

    // Not final as the authority must be injected by the app.
    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri LAST_UPDATED_URI;

    // Content MIME types.
    private static String MIME_DIR_PREFIX;
    private static String MIME_ITEM_PREFIX;

    // Uri matching
    private static final int LAST_UPDATED = -2;
    private static final int LAST_UPDATED_ID = -1;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static ArrayList<ProviderModule> providerModules = new ArrayList<>();

    private CommonDbHelper commonDbHelper;

    public CommonProvider() {
    }

    public static void initialise(@NonNull Context context, ProviderModule[] providerModuleArray) {
        AUTHORITY = context.getString(R.string.provider_authority);
        AUTHORITY_URI = Uri.parse("content://" + AUTHORITY + "/");
        LAST_UPDATED_URI = Uri.withAppendedPath(AUTHORITY_URI, LastUpdated.TABLE_NAME);

        MIME_DIR_PREFIX = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".";
        MIME_ITEM_PREFIX = "vnd.android.cursor.item/vnd." + AUTHORITY + ".";

        if (uriMatcher.match(LAST_UPDATED_URI) == -1) {
            uriMatcher.addURI(AUTHORITY, LastUpdated.TABLE_NAME, LAST_UPDATED);
            uriMatcher.addURI(AUTHORITY, LastUpdated.TABLE_NAME + "/#", LAST_UPDATED_ID);
            for (ProviderModule module : providerModuleArray) {
                module.addUris(uriMatcher, providerModules, AUTHORITY);
            }
        }
    }

    @Override
    public boolean onCreate() {
        commonDbHelper = new CommonDbHelper(getContext(), providerModules);
        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case LAST_UPDATED:
                return MIME_DIR_PREFIX + LastUpdated.TABLE_NAME;
            case LAST_UPDATED_ID:
                return MIME_ITEM_PREFIX + LastUpdated.TABLE_NAME;
        }
        return providerModules.get(match).getType(match, MIME_DIR_PREFIX, MIME_ITEM_PREFIX);
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) { // TODO    Pass db?
        int match = uriMatcher.match(uri);
        switch (match) {
            case LAST_UPDATED:
                throw new IllegalArgumentException("Insert not allowed into " + LastUpdated.TABLE_NAME);
        }
        SQLiteDatabase db = commonDbHelper.getWritableDatabase();
        return providerModules.get(match).insert(uri, values);
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = commonDbHelper.getReadableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case LAST_UPDATED:
                Cursor cursor = db.query(LastUpdated.TABLE_NAME, projection,
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
        return providerModules.get(uriMatcher.match(uri)).query(uri, projection, selection, selectionArgs,
                sortOrder);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = commonDbHelper.getReadableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case LAST_UPDATED:
                int rows = db.update(LastUpdated.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(LAST_UPDATED_URI, null);
                return rows;
            case LAST_UPDATED_ID:
                rows = db.update(LastUpdated.TABLE_NAME, values,
                        LastUpdated._ID + "=?", new String[]{uri.getLastPathSegment()});
                contentResolver.notifyChange(LAST_UPDATED_URI, null);
                return rows;
        }
        return providerModules.get(uriMatcher.match(uri)).update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = commonDbHelper.getWritableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        int rows = 0;
        switch (uriMatcher.match(uri)) {
//            case CAR_PARKS:
//                rows = db.delete(CarPark.TABLE_NAME, selection, selectionArgs);
//                contentResolver.notifyChange(CAR_PARK_URI, null);
//                break;
            case LAST_UPDATED:
                throw new IllegalArgumentException("Delete not allowed from " + LastUpdated.TABLE_NAME);
        }
        return providerModules.get(uriMatcher.match(uri)).delete(uri, selection, selectionArgs);
    }

}
