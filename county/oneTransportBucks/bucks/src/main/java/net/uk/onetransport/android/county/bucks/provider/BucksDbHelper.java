package net.uk.onetransport.android.county.bucks.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BucksDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bucks-db";

    public BucksDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BucksContract.CREATE_CAR_PARK_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_VMS_LOCATION_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_SEGMENT_LOCATION_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_VARIABLE_MESSAGE_SIGN_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_FLOW_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_ROAD_WORKS_TABLE);
        sqLiteDatabase.execSQL(BucksContract.CREATE_VMS_LOCATION_VIEW);
        sqLiteDatabase.execSQL(BucksContract.CREATE_TRAFFIC_FLOW_LOCATION_VIEW);
        Log.i("BucksDbHelper", "DB File = " + sqLiteDatabase.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO Fill in when we update production.
    }
}
