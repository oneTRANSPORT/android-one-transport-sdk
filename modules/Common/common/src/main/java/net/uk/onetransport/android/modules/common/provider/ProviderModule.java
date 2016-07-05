package net.uk.onetransport.android.modules.common.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;

public interface ProviderModule {

    void createDatabase(SQLiteDatabase sqLiteDatabase);

    // Add ids and this to hash.
    void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules, String authority);

    String getType(int match, String mimeDirPrefix, String mimeItemPrefix);

    Uri insert(int match, ContentValues values, SQLiteDatabase sqLiteDatabase);

    Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                 String sortOrder, SQLiteDatabase sqLiteDatabase);

    int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
               SQLiteDatabase sqLiteDatabase);

    int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase);
}
