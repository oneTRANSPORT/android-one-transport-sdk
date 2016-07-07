package net.uk.onetransport.android.modules.common.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;

public class CommonDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "one-transport-db";

    private HashSet<ProviderModule> providerModuleSet = new HashSet<>();

    public CommonDbHelper(Context context, ArrayList<ProviderModule> providerModules) {
        super(context, DB_NAME, null, 1);
        providerModuleSet.addAll(providerModules); // Remove duplicates.
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (ProviderModule module : providerModuleSet) {
            module.createDatabase(sqLiteDatabase);
        }
        Log.i("CommonDbHelper", "DB File = " + sqLiteDatabase.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO    Fill in when we update production.
        // TODO    Since the database is a cache, we can just drop it and create the new one.
    }
}
