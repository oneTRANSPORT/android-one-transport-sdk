package net.uk.onetransport.android.county.northants.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NorthantsDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "northants-db";
    private static final String INIT_LAST_UPDATED = "insert into "
            + NorthantsContract.LastUpdated.TABLE_NAME + " ("
            + NorthantsContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS + ") values (0);";

    public NorthantsDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_CLEARVIEW_DEVICE_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_CLEARVIEW_DATA_TABLE);
        sqLiteDatabase.execSQL(NorthantsContract.CREATE_LAST_UPDATED_TABLE);
        sqLiteDatabase.execSQL(INIT_LAST_UPDATED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO    Fill in when we update production.
        // TODO    Since the database is a cache, we can just drop it and create the new one.
    }

}
