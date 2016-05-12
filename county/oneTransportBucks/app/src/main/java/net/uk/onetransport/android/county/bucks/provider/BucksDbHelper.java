package net.uk.onetransport.android.county.bucks.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BucksDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bucks-db";

    public BucksDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BucksContract.CREATE_CAR_PARK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO Fill in when we update production.
    }
}
