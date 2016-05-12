package net.uk.onetransport.android.county.bucks.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class BucksProvider extends ContentProvider {

    public static final String AUTHORITY = "net.uk.oneTransport.android.county.bucks.provider";
    public static final String AUTHORITY_URI = "content://" + AUTHORITY + "/";

    private BucksDbHelper bucksDbHelper;

    @Override
    public boolean onCreate() {
        bucksDbHelper = new BucksDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
