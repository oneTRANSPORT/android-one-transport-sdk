package net.uk.onetransport.android.county.bucks.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.common.provider.ProviderModule;

import java.util.ArrayList;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.CarPark;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.RoadWorks;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.TrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.VariableMessageSign;

public class BucksProviderModule implements ProviderModule {

    private static Uri AUTHORITY_URI;
    private static Uri CAR_PARK_URI;
    private static Uri VARIABLE_MESSAGE_SIGN_URI;
    private static Uri TRAFFIC_FLOW_URI;
    private static Uri ROAD_WORKS_URI;
    // Uri matching
    private static int CAR_PARKS;
    private static int CAR_PARK_ID;
    private static int VARIABLE_MESSAGE_SIGNS;
    private static int VARIABLE_MESSAGE_SIGN_ID;
    private static int TRAFFIC_FLOWS;
    private static int TRAFFIC_FLOW_ID;
    private static int ROAD_WORKS;
    private static int ROAD_WORKS_ID;

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BucksContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_ROAD_WORKS_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        CAR_PARK_URI = Uri.withAppendedPath(AUTHORITY_URI, CarPark.TABLE_NAME);
        VARIABLE_MESSAGE_SIGN_URI = Uri.withAppendedPath(AUTHORITY_URI,
                VariableMessageSign.TABLE_NAME);
        TRAFFIC_FLOW_URI = Uri.withAppendedPath(AUTHORITY_URI, TrafficFlow.TABLE_NAME);
        ROAD_WORKS_URI = Uri.withAppendedPath(AUTHORITY_URI, RoadWorks.TABLE_NAME);

        CAR_PARKS = providerModules.size();
        uriMatcher.addURI(authority, CarPark.TABLE_NAME, CAR_PARKS);
        providerModules.add(this);
        CAR_PARK_ID = providerModules.size();
        uriMatcher.addURI(authority, CarPark.TABLE_NAME + "/#", CAR_PARK_ID);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGNS = providerModules.size();
        uriMatcher.addURI(authority, VariableMessageSign.TABLE_NAME,
                VARIABLE_MESSAGE_SIGNS);
        providerModules.add(this);
        VARIABLE_MESSAGE_SIGN_ID = providerModules.size();
        uriMatcher.addURI(authority, VariableMessageSign.TABLE_NAME + "/#",
                VARIABLE_MESSAGE_SIGN_ID);
        providerModules.add(this);
        TRAFFIC_FLOWS = providerModules.size();
        uriMatcher.addURI(authority, TrafficFlow.TABLE_NAME, TRAFFIC_FLOWS);
        providerModules.add(this);
        TRAFFIC_FLOW_ID = providerModules.size();
        uriMatcher.addURI(authority, TrafficFlow.TABLE_NAME + "/#", TRAFFIC_FLOW_ID);
        providerModules.add(this);
        ROAD_WORKS = providerModules.size();
        uriMatcher.addURI(authority, RoadWorks.TABLE_NAME, ROAD_WORKS);
        providerModules.add(this);
        ROAD_WORKS_ID = providerModules.size();
        uriMatcher.addURI(authority, RoadWorks.TABLE_NAME + "/#", ROAD_WORKS_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == CAR_PARKS) {
            return mimeDirPrefix + CarPark.TABLE_NAME;
        }
        if (match == CAR_PARK_ID) {
            return mimeItemPrefix + CarPark.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGNS) {
            return mimeDirPrefix + VariableMessageSign.TABLE_NAME;
        }
        if (match == VARIABLE_MESSAGE_SIGN_ID) {
            return mimeItemPrefix + VariableMessageSign.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOWS) {
            return mimeDirPrefix + TrafficFlow.TABLE_NAME;
        }
        if (match == TRAFFIC_FLOW_ID) {
            return mimeItemPrefix + TrafficFlow.TABLE_NAME;
        }
        if (match == ROAD_WORKS) {
            return mimeDirPrefix + RoadWorks.TABLE_NAME;
        }
        if (match == ROAD_WORKS_ID) {
            return mimeItemPrefix + RoadWorks.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues) {
        long id;
        SQLiteDatabase db = bucksDbHelper.getWritableDatabase();
        ContentResolver contentResolver = getContext().getContentResolver();
        switch (uriMatcher.match(uri)) {
            case CAR_PARKS:
                id = db.insert(CarPark.TABLE_NAME, null, contentValues);
                contentResolver.notifyChange(CAR_PARK_URI, null);
                return ContentUris.withAppendedId(CAR_PARK_URI, id);
            case VARIABLE_MESSAGE_SIGNS:
                id = db.insert(VariableMessageSign.TABLE_NAME, null, contentValues);
                contentResolver.notifyChange(VARIABLE_MESSAGE_SIGN_URI, null);
                return ContentUris.withAppendedId(VARIABLE_MESSAGE_SIGN_URI, id);
            case TRAFFIC_FLOWS:
                id = db.insert(TrafficFlow.TABLE_NAME, null, contentValues);
                contentResolver.notifyChange(TRAFFIC_FLOW_URI, null);
                return ContentUris.withAppendedId(TRAFFIC_FLOW_URI, id);
            case ROAD_WORKS:
                id = db.insert(RoadWorks.TABLE_NAME, null, contentValues);
                contentResolver.notifyChange(ROAD_WORKS_URI, null);
                return ContentUris.withAppendedId(ROAD_WORKS_URI, id);
        }
        return null;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        return 0;
    }
}
