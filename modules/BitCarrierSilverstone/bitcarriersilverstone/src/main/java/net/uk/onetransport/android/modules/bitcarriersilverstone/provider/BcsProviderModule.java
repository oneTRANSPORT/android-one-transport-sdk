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
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchRetriever;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;
import net.uk.onetransport.android.modules.common.sync.CommonSyncAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneTravelTimes;

public class BcsProviderModule implements ProviderModule {

    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    public static Uri SKETCH_URI;
    public static Uri TRAVEL_SUMMARY_URI;
    public static Uri VECTOR_STATUS_URI;
    public static Uri NODE_URI;
    // Sync adapter extras.
    private static final String EXTRAS_SKETCHES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.SKETCHES";
    private static final String EXTRAS_TRAVEL_SUMMARIES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.TRAVEL_SUMMARIES";
    private static final String EXTRAS_VECTOR_STATUSES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.VECTOR_STATUSES";
    private static final String EXTRAS_NODES =
            "net.uk.onetransport.android.modules.bitcarriersilverstone.sync.NODES";

    private static int SKETCHES;
    private static int SKETCH_ID;
    private static int TRAVEL_SUMMARIES;
    private static int TRAVEL_SUMMARY_ID;
    private static int VECTOR_STATUSES;
    private static int VECTOR_STATUS_ID;
    private static int NODES;
    private static int NODE_ID;

    private Context context;

    public BcsProviderModule(Context context) {
        this.context = context;
    }

    @Override
    public void createDatabase(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_SKETCH_TABLE);
//        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_TRAVEL_SUMMARY_TABLE);
//        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_VECTOR_STATUS_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_NODE_TABLE);
        sqLiteDatabase.execSQL(BcsContract.CREATE_BIT_CARRIER_TRAVEL_TIMES_TABLE);
        importTravelTimes(sqLiteDatabase);
    }

    @Override
    public void addUris(UriMatcher uriMatcher, ArrayList<ProviderModule> providerModules,
                        String authority) {
        AUTHORITY = authority;
        AUTHORITY_URI = Uri.parse("content://" + authority + "/");
        SKETCH_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneSketch.TABLE_NAME);
//        TRAVEL_SUMMARY_URI = Uri.withAppendedPath(AUTHORITY_URI,
//                BitCarrierSilverstoneTravelSummary.TABLE_NAME);
//        VECTOR_STATUS_URI = Uri.withAppendedPath(AUTHORITY_URI,
//                BitCarrierSilverstoneVectorStatus.TABLE_NAME);
        NODE_URI = Uri.withAppendedPath(AUTHORITY_URI,
                BitCarrierSilverstoneNode.TABLE_NAME);

        SKETCHES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneSketch.TABLE_NAME, SKETCHES);
        providerModules.add(this);
        SKETCH_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneSketch.TABLE_NAME + "/#", SKETCH_ID);
        providerModules.add(this);
//        TRAVEL_SUMMARIES = providerModules.size();
//        uriMatcher.addURI(authority, BitCarrierSilverstoneTravelSummary.TABLE_NAME,
//                TRAVEL_SUMMARIES);
//        providerModules.add(this);
//        TRAVEL_SUMMARY_ID = providerModules.size();
//        uriMatcher.addURI(authority, BitCarrierSilverstoneTravelSummary.TABLE_NAME + "/#",
//                TRAVEL_SUMMARY_ID);
//        providerModules.add(this);
//        VECTOR_STATUSES = providerModules.size();
//        uriMatcher.addURI(authority, BitCarrierSilverstoneVectorStatus.TABLE_NAME,
//                VECTOR_STATUSES);
//        providerModules.add(this);
//        VECTOR_STATUS_ID = providerModules.size();
//        uriMatcher.addURI(authority, BitCarrierSilverstoneVectorStatus.TABLE_NAME + "/#",
//                VECTOR_STATUS_ID);
//        providerModules.add(this);
        NODES = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneNode.TABLE_NAME, NODES);
        providerModules.add(this);
        NODE_ID = providerModules.size();
        uriMatcher.addURI(authority, BitCarrierSilverstoneNode.TABLE_NAME + "/#", NODE_ID);
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
//        if (match == TRAVEL_SUMMARIES) {
//            return mimeDirPrefix + BitCarrierSilverstoneTravelSummary.TABLE_NAME;
//        }
//        if (match == TRAVEL_SUMMARY_ID) {
//            return mimeItemPrefix + BitCarrierSilverstoneTravelSummary.TABLE_NAME;
//        }
//        if (match == VECTOR_STATUSES) {
//            return mimeDirPrefix + BitCarrierSilverstoneVectorStatus.TABLE_NAME;
//        }
//        if (match == VECTOR_STATUS_ID) {
//            return mimeItemPrefix + BitCarrierSilverstoneVectorStatus.TABLE_NAME;
//        }
        if (match == NODES) {
            return mimeDirPrefix + BitCarrierSilverstoneNode.TABLE_NAME;
        }
        if (match == NODE_ID) {
            return mimeItemPrefix + BitCarrierSilverstoneNode.TABLE_NAME;
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
//        if (match == TRAVEL_SUMMARIES) {
//            id = sqLiteDatabase.insert(BitCarrierSilverstoneTravelSummary.TABLE_NAME, null, contentValues);
//            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
//            return ContentUris.withAppendedId(TRAVEL_SUMMARY_URI, id);
//        }
//        if (match == VECTOR_STATUSES) {
//            id = sqLiteDatabase.insert(BitCarrierSilverstoneVectorStatus.TABLE_NAME, null, contentValues);
//            contentResolver.notifyChange(VECTOR_STATUS_URI, null);
//            return ContentUris.withAppendedId(VECTOR_STATUS_URI, id);
//        }
        if (match == NODES) {
            id = sqLiteDatabase.insert(BitCarrierSilverstoneNode.TABLE_NAME, null, contentValues);
            contentResolver.notifyChange(NODE_URI, null);
            return ContentUris.withAppendedId(NODE_URI, id);
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
                    BitCarrierSilverstoneSketch._ID + "=?", new String[]{uri.getLastPathSegment()}, null, null,
                    sortOrder);
            cursor.setNotificationUri(contentResolver, SKETCH_URI);
            return cursor;
        }
//        if (match == TRAVEL_SUMMARIES) {
//            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneTravelSummary.TABLE_NAME,
//                    projection, selection, selectionArgs, null, null, sortOrder);
//            cursor.setNotificationUri(contentResolver, TRAVEL_SUMMARY_URI);
//            return cursor;
//        }
//        if (match == TRAVEL_SUMMARY_ID) {
//            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneTravelSummary.TABLE_NAME,
//                    projection, BitCarrierSilverstoneTravelSummary._ID + "=?",
//                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
//            cursor.setNotificationUri(contentResolver, TRAVEL_SUMMARY_URI);
//            return cursor;
//        }
//        if (match == VECTOR_STATUSES) {
//            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneVectorStatus.TABLE_NAME,
//                    projection, selection, selectionArgs, null, null, sortOrder);
//            cursor.setNotificationUri(contentResolver, VECTOR_STATUS_URI);
//            return cursor;
//        }
//        if (match == VECTOR_STATUS_ID) {
//            Cursor cursor = sqLiteDatabase.query(BitCarrierSilverstoneVectorStatus.TABLE_NAME,
//                    projection, BitCarrierSilverstoneVectorStatus._ID + "=?",
//                    new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
//            cursor.setNotificationUri(contentResolver, VECTOR_STATUS_URI);
//            return cursor;
//        }
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
        return null;
    }

    @Override
    public int update(Uri uri, int match, ContentValues values, String selection, String[] selectionArgs,
                      SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        if (match == SKETCHES) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneSketch.TABLE_NAME, values, selection,
                    selectionArgs);
            contentResolver.notifyChange(SKETCH_URI, null);
            return rows;
        }
        if (match == SKETCH_ID) {
            int rows = sqLiteDatabase.update(BitCarrierSilverstoneSketch.TABLE_NAME, values,
                    BitCarrierSilverstoneSketch._ID + "=?", new String[]{uri.getLastPathSegment()});
            contentResolver.notifyChange(SKETCH_URI, null);
            return rows;
        }
//        if (match == TRAVEL_SUMMARIES) {
//            int rows = sqLiteDatabase.update(BitCarrierSilverstoneTravelSummary.TABLE_NAME, values,
//                    selection, selectionArgs);
//            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
//            return rows;
//        }
//        if (match == TRAVEL_SUMMARY_ID) {
//            int rows = sqLiteDatabase.update(BitCarrierSilverstoneTravelSummary.TABLE_NAME, values,
//                    BitCarrierSilverstoneTravelSummary._ID + "=?", new String[]{uri.getLastPathSegment()});
//            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
//            return rows;
//        }
//        if (match == VECTOR_STATUSES) {
//            int rows = sqLiteDatabase.update(BitCarrierSilverstoneVectorStatus.TABLE_NAME, values,
//                    selection, selectionArgs);
//            contentResolver.notifyChange(VECTOR_STATUS_URI, null);
//            return rows;
//        }
//        if (match == VECTOR_STATUS_ID) {
//            int rows = sqLiteDatabase.update(BitCarrierSilverstoneVectorStatus.TABLE_NAME, values,
//                    BitCarrierSilverstoneVectorStatus._ID + "=?", new String[]{uri.getLastPathSegment()});
//            contentResolver.notifyChange(VECTOR_STATUS_URI, null);
//            return rows;
//        }
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
        return 0;
    }

    @Override
    public int delete(int match, String selection, String[] selectionArgs, SQLiteDatabase sqLiteDatabase) {
        ContentResolver contentResolver = context.getContentResolver();
        int rows = 0;
        if (match == SKETCHES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneSketch.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(SKETCH_URI, null);
        }
//        if (match == TRAVEL_SUMMARIES) {
//            rows = sqLiteDatabase.delete(BitCarrierSilverstoneTravelSummary.TABLE_NAME, selection,
//                    selectionArgs);
//            contentResolver.notifyChange(TRAVEL_SUMMARY_URI, null);
//        }
//        if (match == VECTOR_STATUSES) {
//            rows = sqLiteDatabase.delete(BitCarrierSilverstoneVectorStatus.TABLE_NAME, selection,
//                    selectionArgs);
//            contentResolver.notifyChange(VECTOR_STATUS_URI, null);
//        }
        if (match == NODES) {
            rows = sqLiteDatabase.delete(BitCarrierSilverstoneNode.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(NODE_URI, null);
        }
        return rows;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient providerClient, SyncResult syncResult) {
        if (authority.equals(OneTransportProvider.AUTHORITY)) {
            if (extras.getBoolean(EXTRAS_SKETCHES, false)) {
                try {
                    ArrayList<Sketch> sketches = new SketchRetriever().retrieve(context);
                    BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_SKETCH);
                    BcsContentHelper.insertSketchesIntoProvider(context, sketches);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            if (extras.getBoolean(EXTRAS_TRAVEL_SUMMARIES, false)) {
//                try {
//                    ArrayList<TravelSummary> travelSummaries = new TravelSummaryRetriever()
//                            .retrieve(context);
//                    BcsContentHelper.deleteFromProvider(context,
//                            BcsContentHelper.DATA_TYPE_TRAVEL_SUMMARY);
//                    BcsContentHelper.insertTravelSummariesIntoProvider(context, travelSummaries);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            if (extras.getBoolean(EXTRAS_VECTOR_STATUSES, false)) {
//                try {
//                    ArrayList<VectorStatus> vectorStatuses = new VectorStatusRetriever()
//                            .retrieve(context);
//                    BcsContentHelper.deleteFromProvider(context,
//                            BcsContentHelper.DATA_TYPE_VECTOR_STATUS);
//                    BcsContentHelper.insertVectorStatusesIntoProvider(context, vectorStatuses);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            if (extras.getBoolean(EXTRAS_NODES, false)) {
                try {
                    ArrayList<Node> nodes = new NodeRetriever().retrieve(context);
                    BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_NODE);
                    BcsContentHelper.insertNodesIntoProvider(context, nodes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void refresh(Context context, boolean sketches, boolean travelSummaries,
                               boolean vectorStatuses, boolean nodes) {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(EXTRAS_SKETCHES, sketches);
        settingsBundle.putBoolean(EXTRAS_TRAVEL_SUMMARIES, travelSummaries);
        settingsBundle.putBoolean(EXTRAS_VECTOR_STATUSES, vectorStatuses);
        settingsBundle.putBoolean(EXTRAS_NODES, nodes);
        CommonSyncAdapter.refresh(context, settingsBundle);
    }

    private void importTravelTimes(SQLiteDatabase sqLiteDatabase) {
        // Import historical data summary that would take too long to download.
        BufferedReader br = new BufferedReader(new InputStreamReader(
                context.getResources().openRawResource(R.raw.bit_carrier_summary_import)));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                String insert = "INSERT INTO " + BitCarrierSilverstoneTravelTimes.TABLE_NAME
                        + " VALUES (NULL," + line + ");";
                sqLiteDatabase.execSQL(insert);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
