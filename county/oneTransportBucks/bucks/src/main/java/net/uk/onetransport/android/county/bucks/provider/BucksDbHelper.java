package net.uk.onetransport.android.county.bucks.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BucksDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bucks-db";
    private static final String INIT_LAST_UPDATED = "insert into "
            + BucksContract.LastUpdated.TABLE_NAME + " ("
            + BucksContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS + ") values (0);";

    public BucksDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BucksContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_SEGMENT_LOCATION_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_ROAD_WORKS_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_LAST_UPDATED_TABLE);
        sqLiteDatabase.execSQL(INIT_LAST_UPDATED);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_FLOW_LOCATION_VIEW);
        Log.i("BucksDbHelper", "DB File = " + sqLiteDatabase.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO    Fill in when we update production.
        // TODO    Since the database is a cache, we can just drop it and create the new one.
    }
}
