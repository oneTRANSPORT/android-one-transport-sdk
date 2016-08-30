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
import android.util.Log;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.VectorRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneConfigSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneDataSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneLatestTravelTime;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneLatestVectorTravelTime;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneTravelTime;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneVectorTravelTime;

public class BcsProviderModule implements ProviderModule {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri DATA_SKETCH_URI;
    public static Uri NODE_URI;
    public static Uri TRAVEL_TIME_URI;
    public static Uri VECTOR_URI;
    public static Uri LATEST_TRAVEL_TIME_URI;
    public static Uri LATEST_VECTOR_TRAVEL_TIME_URI;
    public static Uri VECTOR_TRAVEL_TIME_URI;
    public static Uri CONFIG_SKETCH_URI;

    // Sync adapter extras.
    private static final String EXTRAS_SKETCHES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.DATA_SKETCHES";
    private static final String EXTRAS_NODES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.NODES";
    private static final String EXTRAS_VECTORS =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.VECTORS";

    private static int DATA_SKETCHES;
    private static int DATA_SKETCH_ID;
    private static int NODES;
    private static int NODE_ID;
    private static int TRAVEL_TIMES;
    private static int TRAVEL_TIME_ID;
    private static int VECTORS;
    private static int VECTOR_ID;
    private static int LATEST_TRAVEL_TIMES;
    private static int LATEST_TRAVEL_TIME_ID;
    private static int LATEST_VECTOR_TRAVEL_TIMES;
    private static int LATEST_VECTOR_TRAVEL_TIME_ID;
    private static int VECTOR_TRAVEL_TIMES;
    private static int VECTOR_TRAVEL_TIME_ID;
    private static int CONFIG_SKETCHES;
    private static int CONFIG_SKETCH_ID;

    private Context context;

    public BcsProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_DATA_SKETCH_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_NODE_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_VECTOR_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_LATEST_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(
                BcsContract.CREATE_BIT_CARRIER_LATEST_VECTOR_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_VECTOR_TRAVEL_TIME_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_CONFIG_SKETCH_TABLE);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        DATA_SKETCH_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneDataSketch.TABLE_NAME);
        NODE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneNode.TABLE_NAME);
        TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneTravelTime.TABLE_NAME);
        VECTOR_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneVector.TABLE_NAME);
        LATEST_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneLatestTravelTime.TABLE_NAME);
        LATEST_VECTOR_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME);
        VECTOR_TRAVEL_TIME_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneVectorTravelTime.TABLE_NAME);
        CONFIG_SKETCH_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneConfigSketch.TABLE_NAME);

        DATA_SKETCHES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneDataSketch.TABLE_NAME, DATA_SKETCHES);
        providerModules.add(this);
        DATA_SKETCH_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneDataSketch.TABLE_NAME + "/#", DATA_SKETCH_ID);
        providerModules.add(this);
        NODES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneNode.TABLE_NAME, NODES);
        providerModules.add(this);
        NODE_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneNode.TABLE_NAME + "/#", NODE_ID);
        providerModules.add(this);
        TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneTravelTime.TABLE_NAME, TRAVEL_TIMES);
        providerModules.add(this);
        TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneTravelTime.TABLE_NAME + "/#",
                TRAVEL_TIME_ID);
        providerModules.add(this);
        VECTORS = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneVector.TABLE_NAME, VECTORS);
        providerModules.add(this);
        VECTOR_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneVector.TABLE_NAME + "/#", VECTOR_ID);
        providerModules.add(this);
        LATEST_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestTravelTime.TABLE_NAME,
                LATEST_TRAVEL_TIMES);
        providerModules.add(this);
        LATEST_TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestTravelTime.TABLE_NAME + "/#",
                LATEST_TRAVEL_TIME_ID);
        providerModules.add(this);
        LATEST_VECTOR_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME,
                LATEST_VECTOR_TRAVEL_TIMES);
        providerModules.add(this);
        LATEST_VECTOR_TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME + "/#",
                LATEST_VECTOR_TRAVEL_TIME_ID);
        providerModules.add(this);
        VECTOR_TRAVEL_TIMES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneVectorTravelTime.TABLE_NAME,
                VECTOR_TRAVEL_TIMES);
        providerModules.add(this);
        VECTOR_TRAVEL_TIME_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneVectorTravelTime.TABLE_NAME + "/#",
                VECTOR_TRAVEL_TIME_ID);
        providerModules.add(this);
        CONFIG_SKETCHES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneConfigSketch.TABLE_NAME,
                CONFIG_SKETCHES);
        providerModules.add(this);
        CONFIG_SKETCH_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneConfigSketch.TABLE_NAME + "/#",
                CONFIG_SKETCH_ID);
        providerModules.add(this);
    }

    @Override
    public String getType(int match, String mimeDirPrefix, String mimeItemPrefix) {
        if (match == DATA_SKETCHES) {
            return mimeDirPrefix + BitCarrierSilverstoneDataSketch.TABLE_NAME;
        }
        if (match == DATA_SKETCH_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneDataSketch.TABLE_NAME;
        }
        if (match == NODES) {
            return mimeDirPrefix + BitCarrierSilverstoneNode.TABLE_NAME;
        }
        if (match == NODE_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneNode.TABLE_NAME;
        }
        if (match == TRAVEL_TIME_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneTravelTime.TABLE_NAME;
        }
        if (match == VECTORS) {
            return mimeDirPrefix + BitCarrierSilverstoneVector.TABLE_NAME;
        }
        if (match == VECTOR_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneVector.TABLE_NAME;
        }
        if (match == LATEST_TRAVEL_TIMES) {
            return mimeDirPrefix + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME;
        }
        if (match == LATEST_TRAVEL_TIME_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME;
        }
        if (match == LATEST_VECTOR_TRAVEL_TIMES) {
            return mimeDirPrefix + BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME;
        }
        if (match == LATEST_VECTOR_TRAVEL_TIME_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME;
        }
        if (match == VECTOR_TRAVEL_TIMES) {
            return mimeDirPrefix + BitCarrierSilverstoneVectorTravelTime.TABLE_NAME;
        }
        if (match == VECTOR_TRAVEL_TIME_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneVectorTravelTime.TABLE_NAME;
        }
        if (match == CONFIG_SKETCHES) {
            return mimeDirPrefix + BitCarrierSilverstoneConfigSketch.TABLE_NAME;
        }
        if (match == CONFIG_SKETCH_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneConfigSketch.TABLE_NAME;
        }
        return null;
    }

    @Override
    public Uri insert(int match, ContentValues contentValues, SQLiteDatabase sqLiteDatabase) {
        long id;
        ContentResolver contentResolver = context.getContentResolver();
        if (match == DATA_SKETCHES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneDataSketch.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(DATA_SKETCH_URI, null);
            return ContentUris.withAppendedId(DATA_SKETCH_URI, id);
        }
        if (match == NODES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneNode.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(NODE_URI, null);
            return ContentUris.withAppendedId(NODE_URI, id);
        }
        if (match == TRAVEL_TIMES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneTravelTime.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(TRAVEL_TIME_URI, null);
            return ContentUris.withAppendedId(TRAVEL_TIME_URI, id);
        }
        if (match == VECTORS) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneVector.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(VECTOR_URI, null);
            return ContentUris.withAppendedId(VECTOR_URI, id);
        }
        if (match == LATEST_TRAVEL_TIMES) {
            Log.e("BcsProviderModule", "Insert not permitted into "
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME);
        }
        if (match == LATEST_VECTOR_TRAVEL_TIMES) {
            Log.e("BcsProviderModule", "Insert not permitted into "
                    + BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME);
        }
        if (match == VECTOR_TRAVEL_TIMES) {
            Log.e("BcsProviderModule", "Insert not permitted into "
                    + BitCarrierSilverstoneVectorTravelTime.TABLE_NAME);
        }
        if (match == CONFIG_SKETCHES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneConfigSketch.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(CONFIG_SKETCH_URI, null);
            return ContentUris.withAppendedId(CONFIG_SKETCH_URI, id);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, int match, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == DATA_SKETCHES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneDataSketch.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, DATA_SKETCH_URI);
            return cursor;
        }
        if (match == DATA_SKETCH_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneDataSketch.TABLE_NAME, projection,
                    BitCarrierSilverstoneDataSketch._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, DATA_SKETCH_URI);
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
        if (match == TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneTravelTime.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneTravelTime.TABLE_NAME, projection,
                    BitCarrierSilverstoneTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == VECTORS) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneVector.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VECTOR_URI);
            return cursor;
        }
        if (match == VECTOR_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneVector.TABLE_NAME, projection,
                    BitCarrierSilverstoneVector._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, VECTOR_URI);
            return cursor;
        }
        if (match == LATEST_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestTravelTime.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == LATEST_TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestTravelTime.TABLE_NAME,
                    projection, BitCarrierSilverstoneLatestTravelTime._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == LATEST_VECTOR_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_VECTOR_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == LATEST_VECTOR_TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME,
                    projection, BitCarrierSilverstoneLatestVectorTravelTime._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, LATEST_VECTOR_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == VECTOR_TRAVEL_TIMES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneVectorTravelTime.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VECTOR_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == VECTOR_TRAVEL_TIME_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneVectorTravelTime.TABLE_NAME,
                    projection, BitCarrierSilverstoneVectorTravelTime._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, VECTOR_TRAVEL_TIME_URI);
            return cursor;
        }
        if (match == CONFIG_SKETCHES) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneConfigSketch.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CONFIG_SKETCH_URI);
            return cursor;
        }
        if (match == CONFIG_SKETCH_ID) {
            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneConfigSketch.TABLE_NAME,
                    projection, BitCarrierSilverstoneConfigSketch._ID + "=?",
                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, CONFIG_SKETCH_URI);
            return cursor;
        }
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
                      SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == DATA_SKETCHES) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneDataSketch.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(DATA_SKETCH_URI, null);
            return rows;
        }
        if (match == DATA_SKETCH_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneDataSketch.TABLE_NAME, values,
                    BitCarrierSilverstoneDataSketch._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(DATA_SKETCH_URI, null);
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
        if (match == TRAVEL_TIMES) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneTravelTime.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == TRAVEL_TIME_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneTravelTime.TABLE_NAME, values,
                    BitCarrierSilverstoneTravelTime._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(TRAVEL_TIME_URI, null);
            return rows;
        }
        if (match == VECTORS) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneVector.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(VECTOR_URI, null);
            return rows;
        }
        if (match == VECTOR_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneVector.TABLE_NAME, values,
                    BitCarrierSilverstoneVector._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(VECTOR_URI, null);
            return rows;
        }
        if (match == LATEST_TRAVEL_TIMES || match == LATEST_TRAVEL_TIME_ID) {
            Log.e("BcsProviderModule", "Update not permitted for "
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME);
        }
        if (match == LATEST_VECTOR_TRAVEL_TIMES
                || match == LATEST_VECTOR_TRAVEL_TIME_ID) {
            Log.e("BcsProviderModule", "Update not permitted for "
                    + BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME);
        }
        if (match == VECTOR_TRAVEL_TIMES || match == VECTOR_TRAVEL_TIME_ID) {
            Log.e("BcsProviderModule", "Update not permitted for "
                    + BitCarrierSilverstoneVectorTravelTime.TABLE_NAME);
        }
        if (match == CONFIG_SKETCHES) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneConfigSketch.TABLE_NAME, values,
                    selection, selectionArgs);
            contentResolver.notifyChange(CONFIG_SKETCH_URI, null);
            return rows;
        }
        if (match == CONFIG_SKETCH_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneConfigSketch.TABLE_NAME, values,
                    BitCarrierSilverstoneVector._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(CONFIG_SKETCH_URI, null);
            return rows;
        }
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int rows = 0;
        if (match == DATA_SKETCHES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneDataSketch.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(DATA_SKETCH_URI, null);
        }
        if (match == NODES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneNode.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(NODE_URI, null);
        }
        if (match == TRAVEL_TIMES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneTravelTime.TABLE_NAME, selection,
                    selectionArgs);
            contentResolver.notifyChange(TRAVEL_TIME_URI, null);
        }
        if (match == VECTORS) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneVector.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(VECTOR_URI, null);
        }
        if (match == LATEST_TRAVEL_TIMES) {
            Log.e("BcsProviderModule", "Delete not permitted for "
                    + BitCarrierSilverstoneLatestTravelTime.TABLE_NAME);
        }
        if (match == LATEST_VECTOR_TRAVEL_TIMES) {
            Log.e("BcsProviderModule", "Delete not permitted for "
                    + BitCarrierSilverstoneLatestVectorTravelTime.TABLE_NAME);
        }
        if (match == VECTOR_TRAVEL_TIMES) {
            Log.e("BcsProviderModule", "Delete not permitted for "
                    + BitCarrierSilverstoneVectorTravelTime.TABLE_NAME);
        }
        if (match == CONFIG_SKETCHES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneConfigSketch.TABLE_NAME, selection,
                    selectionArgs);
            contentResolver.notifyChange(CONFIG_SKETCH_URI, null);
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
                    BcsContentHelper.insertDataSketchesIntoProvider(context, sketches);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ArrayList<net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch>
                            sketches = new net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch
                            .SketchRetriever(context).retrieve();
                    BcsContentHelper.insertConfigSketchesIntoProvider(context, sketches);
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
                    ArrayList<Vector> vectors = new VectorRetriever(context).retrieve();
                    BcsContentHelper.insertVectorsIntoProvider(context, vectors);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refresh(Context context, boolean sketches, boolean nodes, boolean vectors) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_SKETCHES, sketches);
        settingsBundle.putBoolean(EXTRAS_NODES, nodes);
        settingsBundle.putBoolean(EXTRAS_VECTORS, vectors);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }

}
