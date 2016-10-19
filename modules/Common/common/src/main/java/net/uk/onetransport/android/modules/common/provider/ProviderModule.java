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

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.SyncResult;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;

public interface ProviderModule { // TODO    Add @NonNull throughout?

    void createDatabase(SQLiteDatabase sqLiteDatabase);

    // Add ids and this to hash.
    void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules, String authority);

    String getType(int match, String mimeDirPrefix, String mimeItemPrefix);

    int bulkInsert(int match, ContentValues[] values, SQLiteDatabase sqLiteDatabase);

    Uri insert(int match, ContentValues values, SQLiteDatabase sqLiteDatabase);

    Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                 String sortOrder, SQLiteDatabase sqLiteDatabase);

    int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
               SQLiteDatabase sqLiteDatabase);

    int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase);

    void onPerformSync(Account account, Bundle extras, String authority,
                       ContentProviderClient providerClient, SyncResult syncResult);
}
