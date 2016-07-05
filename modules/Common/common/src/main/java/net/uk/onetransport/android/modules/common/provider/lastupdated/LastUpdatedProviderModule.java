package net.uk.onetransport.android.modules.common.provider.lastupdated;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import net.uk.onetransport.android.modules.common.R;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;

import java.util.ArrayList;

import static net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedContract.LastUpdated;

public class LastUpdatedProviderModule implements ProviderModule {

    private static Uri LAST_UPDATED_URI;
    // Uri matching
    private static int LAST_UPDATED;
    private static int LAST_UPDATED_ID;

    private Context context;

    public LastUpdatedProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LastUpdatedContract.CREATE_LAST_UPDATED_TABLE);
        sqLiteDatabase.execSQL(LastUpdatedContract.INIT_LAST_UPDATED);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        Uri authorityUri = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
                .authority(authority).build();
        LAST_UPDATED_URI = Uri.withAppendedPath(authorityUri, LastUpdated.TABLE_NAME);

        LAST_UPDATED = providerModules.size();
        uriMatcher.addURI(authority, LastUpdated.TABLE_NAME, LAST_UPDATED);
        providerModules.add(this);
        // TODO    Do we actually need this?
        LAST_UPDATED_ID = providerModules.size();
        uriMatcher.addURI(authority, LastUpdated.TABLE_NAME + "/#", LAST_UPDATED_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == LAST_UPDATED) {
            return mimeDirPrefix + LastUpdated.TABLE_NAME;
        }
        if (match == LAST_UPDATED_ID) {
            return mimeItemPrefix + LastUpdated.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues values, SQLiteDatabase sqLiteDatabase) {
        if (match == LAST_UPDATED) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + LastUpdated.TABLE_NAME);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == LAST_UPDATED) {
            Cursor cursor = sqLiteDatabase.query(LastUpdated.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LAST_UPDATED_URI);
            return cursor;
        }
        if (match == LAST_UPDATED_ID) {
            Cursor cursor = sqLiteDatabase.query(LastUpdated.TABLE_NAME, projection,
                    LastUpdated._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, LAST_UPDATED_URI);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
                      SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == LAST_UPDATED) {
            int rows = sqLiteDatabase.update(LastUpdated.TABLE_NAME, values, selection, selectionArgs);
            contentResolver.notifyChange(LAST_UPDATED_URI, null);
            return rows;
        }
        if (match == LAST_UPDATED_ID) {
            int rows = sqLiteDatabase.update(LastUpdated.TABLE_NAME, values,
                    LastUpdated._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(LAST_UPDATED_URI, null);
            return rows;
        }
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase) {
        if (match == LAST_UPDATED) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + LastUpdated.TABLE_NAME);
        }
        return 0;
    }
}
