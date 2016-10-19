/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.uk.onetransport.android.modules.common.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class CommonDbHelper extends SQLiteOpenHelper {

    public static final String DB_DIRECTORY = "oneTransport";
    public static final String DB_NAME = "one-transport-db";

    private HashSet<ProviderModule> providerModuleSet = new HashSet<>();

    public CommonDbHelper(Context context, ArrayList<ProviderModule> providerModules) {
        super(context, Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + DB_DIRECTORY
                + File.separator + OneTransportProvider.AUTHORITY
                + File.separator + DB_NAME, null, 1);

        // We assume the database file is not yet created, so can make its directory tree here first.
        File dbDirectory = new File(Environment.getExternalStorageDirectory(),
                File.separator + CommonDbHelper.DB_DIRECTORY
                        + File.separator + OneTransportProvider.AUTHORITY);
        dbDirectory.mkdirs();
        if (!dbDirectory.isDirectory()) {
            Log.i("CommonDbHelper", "Database directory could not be created: " + dbDirectory.getAbsolutePath());
        }
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
