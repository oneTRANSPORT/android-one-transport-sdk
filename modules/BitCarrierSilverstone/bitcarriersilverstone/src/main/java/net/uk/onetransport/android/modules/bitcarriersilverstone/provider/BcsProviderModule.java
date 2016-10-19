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
package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.SketchRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummaryRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneConfigVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneDataVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneLatestDataVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneLatestTravelSummary;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneTravelSummary;

public class BcsProviderModule implements ProviderModule {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri SKETCH_URI;
    public static Uri NODE_URI;
    public static Uri TRAVEL_SUMMARY_URI;
    public static Uri LATEST_TRAVEL_SUMMARY_URI;
    public static Uri CONFIG_VECTOR_URI;
    public static Uri DATA_VECTOR_URI;
    public static Uri LATEST_DATA_VECTOR_URI;

    // Sync adapter extras.
    private static final String EXTRAS_SKETCHES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.SKETCHES";
    private static final String EXTRAS_NODES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.NODES";
    private static final String EXTRAS_VECTORS =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.VECTORS";
    private static final String EXTRAS_TRAVEL_SUMMARIES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.TRAVEL_SUMMARIES";

    private static int SKETCHES;
    private static int SKETCH_ID;
    private static int NODES;
    private static int NODE_ID;
    private static int TRAVEL_SUMMARIES;
    private static int TRAVEL_SUMMARY_ID;
    private static int LATEST_TRAVEL_SUMMARIES;
    private static int LATEST_TRAVEL_SUMMARY_ID;
    private static int DATA_VECTORS;
    private static int DATA_VECTOR_ID;
    private static int LATEST_DATA_VECTORS;
    private static int LATEST_DATA_VECTOR_ID;
    private static int CONFIG_VECTORS;
    private static int CONFIG_VECTOR_ID;

    private Context context;

    public BcsProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_DATA_SKETCH_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_NODE_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_TRAVEL_SUMMARY_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_LATEST_TRAVEL_SUMMARY_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_CONFIG_VECTOR_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_DATA_VECTOR_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_LATEST_DATA_VECTOR_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        SKETCH_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BcsContract.BitCarrierSilverstoneSketch.TABLE_NAME);
        NODE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneNode.TABLE_NAME);
        TRAVEL_SUMMARY_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneTravelSummary.TABLE_NAME);
        LATEST_TRAVEL_SUMMARY_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME);
        CONFIG_VECTOR_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneConfigVector.TABLE_NAME);
        DATA_VECTOR_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneDataVector.TABLE_NAME);
        LATEST_DATA_VECTOR_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneLatestDataVector.TABLE_NAME);

        SKETCHES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneSketch.TABLE_NAME, SKETCHES);
        providerModules.add(this);
        SKETCH_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneSketch.TABLE_NAME + "/#", SKETCH_ID);
        providerModules.add(this);
        NODES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneNode.TABLE_NAME, NODES);
        providerModules.add(this);
        NODE_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneNode.TABLE_NAME + "/#", NODE_ID);
        providerModules.add(this);
        TRAVEL_SUMMARIES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneTravelSummary.TABLE_NAME, TRAVEL_SUMMARIES);
        providerModules.add(this);
        TRAVEL_SUMMARY_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneTravelSummary.TABLE_NAME + "/#",
                TRAVEL_SUMMARY_ID);
        providerModules.add(this);
        LATEST_TRAVEL_SUMMARIES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME,
                LATEST_TRAVEL_SUMMARIES);
        providerModules.add(this);
        LATEST_TRAVEL_SUMMARY_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME + "/#",
                LATEST_TRAVEL_SUMMARY_ID);
        providerModules.add(this);
        CONFIG_VECTORS = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneConfigVector.TABLE_NAME,
                CONFIG_VECTORS);
        providerModules.add(this);
        CONFIG_VECTOR_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneConfigVector.TABLE_NAME + "/#",
                CONFIG_VECTOR_ID);
        providerModules.add(this);
        DATA_VECTORS = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneDataVector.TABLE_NAME, DATA_VECTORS);
        providerModules.add(this);
        DATA_VECTOR_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneDataVector.TABLE_NAME + "/#",
                DATA_VECTOR_ID);
        providerModules.add(this);
        LATEST_DATA_VECTORS = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestDataVector.TABLE_NAME,
                LATEST_DATA_VECTORS);
        providerModules.add(this);
        LATEST_DATA_VECTOR_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestDataVector.TABLE_NAME + "/#",
                LATEST_DATA_VECTOR_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == SKETCHES) {
            return mimeDirPrefix + BitCarrierSilverstoneSketch.TABLE_NAME;
        }
        if (match == SKETCH_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneSketch.TABLE_NAME;
        }
        if (match == NODES) {
            return mimeDirPrefix + BitCarrierSilverstoneNode.TABLE_NAME;
        }
        if (match == NODE_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneNode.TABLE_NAME;
        }
        if (match == TRAVEL_SUMMARIES) {
            return mimeDirPrefix + BitCarrierSilverstoneTravelSummary.TABLE_NAME;
        }
        if (match == TRAVEL_SUMMARY_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneTravelSummary.TABLE_NAME;
        }
        if (match == LATEST_TRAVEL_SUMMARIES) {
            return mimeDirPrefix + BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME;
        }
        if (match == LATEST_TRAVEL_SUMMARY_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME;
        }
        if (match == CONFIG_VECTORS) {
            return mimeDirPrefix + BitCarrierSilverstoneConfigVector.TABLE_NAME;
        }
        if (match == CONFIG_VECTOR_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneConfigVector.TABLE_NAME;
        }
        if (match == DATA_VECTORS) {
            return mimeDirPrefix + BitCarrierSilverstoneDataVector.TABLE_NAME;
        }
        if (match == DATA_VECTOR_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneDataVector.TABLE_NAME;
        }
        if (match == LATEST_DATA_VECTORS) {
            return mimeDirPrefix + BitCarrierSilverstoneLatestDataVector.TABLE_NAME;
        }
        if (match == LATEST_DATA_VECTOR_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneLatestDataVector.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == SKETCHES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneSketch.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(SKETCH_URI, null);
            return ContentUris.withAppendedId(SKETCH_URI, id);
        }
        if (match == NODES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneNode.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(NODE_URI, null);
            return ContentUris.withAppendedId(NODE_URI, id);
        }
        if (match == TRAVEL_SUMMARIES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneTravelSummary.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
            return ContentUris.withAppendedId(TRAVEL_SUMMARY_URI, id);
        }
        if (match == LATEST_TRAVEL_SUMMARIES) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME);
        }
        if (match == CONFIG_VECTORS) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneConfigVector.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(CONFIG_VECTOR_URI, null);
            return ContentUris.withAppendedId(CONFIG_VECTOR_URI, id);
        }
        if (match == DATA_VECTORS) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneDataVector.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(DATA_VECTOR_URI, null);
            return ContentUris.withAppendedId(DATA_VECTOR_URI, id);
        }
        if (match == LATEST_DATA_VECTORS) {
            throw new IllegalArgumentException(context.getString(R.string.error_insert_not_allowed)
                    + BitCarrierSilverstoneLatestDataVector.TABLE_NAME);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == SKETCHES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneSketch.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, SKETCH_URI);
            return cursor;
        }
        if (match == SKETCH_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneSketch.TABLE_NAME, projection,
                    BcsContract.BitCarrierSilverstoneSketch._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, SKETCH_URI);
            return cursor;
        }
        if (match == NODES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneNode.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, NODE_URI);
            return cursor;
        }
        if (match == NODE_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneNode.TABLE_NAME, projection,
                    BitCarrierSilverstoneNode._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, NODE_URI);
            return cursor;
        }
        if (match == TRAVEL_SUMMARIES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneTravelSummary.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAVEL_SUMMARY_URI);
            return cursor;
        }
        if (match == TRAVEL_SUMMARY_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneTravelSummary.TABLE_NAME, projection,
                    BitCarrierSilverstoneTravelSummary._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAVEL_SUMMARY_URI);
            return cursor;
        }
        if (match == LATEST_TRAVEL_SUMMARIES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAVEL_SUMMARY_URI);
            return cursor;
        }
        if (match == LATEST_TRAVEL_SUMMARY_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME,
                    projection, BitCarrierSilverstoneTravelSummary._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAVEL_SUMMARY_URI);
            return cursor;
        }
        if (match == CONFIG_VECTORS) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneConfigVector.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CONFIG_VECTOR_URI);
            return cursor;
        }
        if (match == CONFIG_VECTOR_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneConfigVector.TABLE_NAME, projection,
                    BitCarrierSilverstoneConfigVector._ID + "=?", new String[]{uri.getLastPathSegment()},
                    null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CONFIG_VECTOR_URI);
            return cursor;
        }
        if (match == DATA_VECTORS) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneDataVector.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, DATA_VECTOR_URI);
            return cursor;
        }
        if (match == DATA_VECTOR_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneDataVector.TABLE_NAME, projection,
                    BitCarrierSilverstoneDataVector._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, DATA_VECTOR_URI);
            return cursor;
        }
        if (match == LATEST_DATA_VECTORS) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestDataVector.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_DATA_VECTOR_URI);
            return cursor;
        }
        if (match == LATEST_DATA_VECTOR_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestDataVector.TABLE_NAME,
                    projection, BitCarrierSilverstoneDataVector._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_DATA_VECTOR_URI);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
                      SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == SKETCHES) {
            int rows = sqLiteDatabase.update(BcsContract.BitCarrierSilverstoneSketch.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(SKETCH_URI, null);
            return rows;
        }
        if (match == SKETCH_ID) {
            int rows = sqLiteDatabase.update(BcsContract.BitCarrierSilverstoneSketch.TABLE_NAME, values,
                    BcsContract.BitCarrierSilverstoneSketch._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(SKETCH_URI, null);
            return rows;
        }
        if (match == NODES) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneNode.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(NODE_URI, null);
            return rows;
        }
        if (match == NODE_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneNode.TABLE_NAME, values,
                    BitCarrierSilverstoneNode._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(NODE_URI, null);
            return rows;
        }
        if (match == TRAVEL_SUMMARIES) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneTravelSummary.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
            return rows;
        }
        if (match == TRAVEL_SUMMARY_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneTravelSummary.TABLE_NAME, values,
                    BitCarrierSilverstoneTravelSummary._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
            return rows;
        }
        if (match == LATEST_TRAVEL_SUMMARIES) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME);
        }
        if (match == LATEST_TRAVEL_SUMMARY_ID) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME);
        }
        if (match == CONFIG_VECTORS) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneConfigVector.TABLE_NAME, values,
                    selection, selectionArgs);
            contentResolver.notifyChange(CONFIG_VECTOR_URI, null);
            return rows;
        }
        if (match == CONFIG_VECTOR_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneConfigVector.TABLE_NAME, values,
                    BitCarrierSilverstoneConfigVector._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(CONFIG_VECTOR_URI, null);
            return rows;
        }
        if (match == DATA_VECTORS) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneDataVector.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(DATA_VECTOR_URI, null);
            return rows;
        }
        if (match == DATA_VECTOR_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneDataVector.TABLE_NAME, values,
                    BitCarrierSilverstoneDataVector._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(DATA_VECTOR_URI, null);
            return rows;
        }
        if (match == LATEST_DATA_VECTORS) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BitCarrierSilverstoneLatestDataVector.TABLE_NAME);
        }
        if (match == LATEST_DATA_VECTOR_ID) {
            throw new IllegalArgumentException(context.getString(R.string.error_update_not_allowed)
                    + BitCarrierSilverstoneLatestDataVector.TABLE_NAME);
        }
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int rows = 0;
        if (match == SKETCHES) {
            rows = sqLiteDatabase.delete(BcsContract.BitCarrierSilverstoneSketch.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(SKETCH_URI, null);
        }
        if (match == NODES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneNode.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(NODE_URI, null);
        }
        if (match == TRAVEL_SUMMARIES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneTravelSummary.TABLE_NAME, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
        }
        if (match == LATEST_TRAVEL_SUMMARIES) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BitCarrierSilverstoneLatestTravelSummary.TABLE_NAME);
        }
        if (match == CONFIG_VECTORS) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneConfigVector.TABLE_NAME, selection,
                    selectionArgs);
            contentResolver.notifyChange(CONFIG_VECTOR_URI, null);
        }
        if (match == DATA_VECTORS) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneDataVector.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(DATA_VECTOR_URI, null);
        }
        if (match == LATEST_DATA_VECTORS) {
            throw new IllegalArgumentException(context.getString(R.string.error_delete_not_allowed)
                    + BitCarrierSilverstoneLatestDataVector.TABLE_NAME);
        }
        return rows;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient providerClient, SyncResult syncResult) {
        if (authority.equals(OneTransportProvider.AUTHORITY)) {
            if (extras.getBoolean(EXTRAS_SKETCHES, false)) {
                try {
                    ArrayList<Sketch> sketches = new SketchRetriever(context).retrieve();
                    BcsContentHelper.insertSketchesIntoProvider(context, sketches);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (extras.getBoolean(EXTRAS_NODES, false)) {
                try {
                    ArrayList<Node> nodes = new NodeRetriever(context).retrieve();
                    BcsContentHelper.insertNodesIntoProvider(context, nodes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (extras.getBoolean(EXTRAS_VECTORS, false)) {
                try {
                    ArrayList<net.uk.onetransport.android.modules.bitcarriersilverstone
                            .config.vector.Vector> configVectors = new net.uk.onetransport.android.modules
                            .bitcarriersilverstone.config.vector.VectorRetriever(context).retrieve();
                    BcsContentHelper.insertConfigVectorsIntoProvider(context, configVectors);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ArrayList<Vector> dataVectors = new VectorRetriever(context).retrieve();
                    BcsContentHelper.insertDataVectorsIntoProvider(context, dataVectors);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (extras.getBoolean(EXTRAS_TRAVEL_SUMMARIES, false)) {
                try {
                    ArrayList<TravelSummary> travelSummaries = new TravelSummaryRetriever(context)
                            .retrieve();
                    BcsContentHelper.insertTravelSummariesIntoProvider(context, travelSummaries);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refresh(Context context,
                               boolean sketches,
                               boolean nodes,
                               boolean vectors,
                               boolean travelSummaries) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_SKETCHES, sketches);
        settingsBundle.putBoolean(EXTRAS_NODES, nodes);
        settingsBundle.putBoolean(EXTRAS_VECTORS, vectors);
        settingsBundle.putBoolean(EXTRAS_TRAVEL_SUMMARIES, travelSummaries);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }

}
