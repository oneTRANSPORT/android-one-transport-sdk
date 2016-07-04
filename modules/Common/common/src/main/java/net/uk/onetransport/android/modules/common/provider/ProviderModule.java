package net.uk.onetransport.android.modules.common.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;

public interface ProviderModule {

    void createDatabase(SQLiteDatabase sqLiteDatabase);

    // Add ids and this to hash.
    void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules, String authority);

    String getType(int match, String mimeDirPrefix, String mimeItemPrefix);

    Uri insert(@NonNull Uri uri, ContentValues values);

    Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                 String sortOrder);

    int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs);

    int delete(@NonNull Uri uri, String selection, String[] selectionArgs);
}
