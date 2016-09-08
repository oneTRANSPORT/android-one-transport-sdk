package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Details;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Stat;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelTime;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneConfigVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneDataVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneTravelSummary;

public class BcsContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            DATA_TYPE_SKETCH,
            DATA_TYPE_NODE,
            DATA_TYPE_TRAVEL_SUMMARY,
            DATA_TYPE_CONFIG_VECTOR,
            DATA_TYPE_DATA_VECTOR
    })
    public @interface DataType {
    }

    public static final int DATA_TYPE_SKETCH = 1;
    public static final int DATA_TYPE_NODE = 2;
    public static final int DATA_TYPE_TRAVEL_SUMMARY = 6;
    public static final int DATA_TYPE_CONFIG_VECTOR = 3;
    public static final int DATA_TYPE_DATA_VECTOR = 4;

    private static final String CREATION_INTERVAL_SELECTION =
            BcsBaseColumns.COLUMN_CREATION_TIME + ">=? AND "
                    + BcsBaseColumns.COLUMN_CREATION_TIME + "<=?";

    public static void insertSketchesIntoProvider(@NonNull Context context,
                                                  @NonNull ArrayList<Sketch> sketches)
            throws RemoteException, OperationApplicationException {
        if (sketches.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Sketch sketch : sketches) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.SKETCH_URI)
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_SKETCH_ID, sketch.getSketchId())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_VECTOR_ID, sketch.getVectorId())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_VISIBLE, sketch.getVisible())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_COPYRIGHTS, sketch.getCopyrights())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_COORDINATES,
                                sketch.getCoordinates())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_CIN_ID, sketch.getCinId())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_CREATION_TIME,
                                sketch.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertNodesIntoProvider(@NonNull Context context,
                                               @NonNull ArrayList<Node> nodes)
            throws RemoteException, OperationApplicationException {
        if (nodes.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Node node : nodes) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.NODE_URI)
                        .withValue(BitCarrierSilverstoneNode.COLUMN_NODE_ID, node.getId())
                        .withValue(BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID, node.getCustomerId())
                        .withValue(BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME,
                                node.getCustomerName())
                        .withValue(BitCarrierSilverstoneNode.COLUMN_LATITUDE, node.getLatitude())
                        .withValue(BitCarrierSilverstoneNode.COLUMN_LONGITUDE, node.getLongitude())
                        .withValue(BitCarrierSilverstoneNode.COLUMN_CIN_ID, node.getCinId())
                        .withValue(BitCarrierSilverstoneNode.COLUMN_CREATION_TIME,
                                node.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertConfigVectorsIntoProvider(@NonNull Context context,
                                                       @NonNull ArrayList<net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector> vectors)
            throws RemoteException, OperationApplicationException {
        if (vectors.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector vector : vectors) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.CONFIG_VECTOR_URI)
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_VECTOR_ID, vector.getId())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_NAME, vector.getName())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_CUSTOMER_NAME,
                                vector.getCustomerName())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_FROM, vector.getFrom())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_TO, vector.getTo())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_DISTANCE, vector.getDistance())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID, vector.getsId())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_CIN_ID, vector.getCinId())
                        .withValue(BitCarrierSilverstoneConfigVector.COLUMN_CREATION_TIME,
                                vector.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertDataVectorsIntoProvider(@NonNull Context context,
                                                     @NonNull ArrayList<Vector> vectors)
            throws RemoteException, OperationApplicationException {
        if (vectors.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Vector vector : vectors) {
                ContentProviderOperation.Builder builder = ContentProviderOperation
                        .newInsert(BcsProviderModule.DATA_VECTOR_URI)
                        .withValue(BitCarrierSilverstoneDataVector.COLUMN_VECTOR_ID, vector.getVectorId());
                Details details = vector.getAverageDetails();
                if (details != null) {
                    Stat stat = details.getPublish();
                    if (stat != null) {
                        builder.withValue(BitCarrierSilverstoneDataVector.COLUMN_ELAPSED, stat.getElapsed())
                                .withValue(BitCarrierSilverstoneDataVector.COLUMN_SPEED, stat.getSpeed());
                    }
                }
                builder.withValue(BitCarrierSilverstoneDataVector.COLUMN_LEVEL_OF_SERVICE,
                        vector.getLevelOfService())
                        .withValue(BitCarrierSilverstoneDataVector.COLUMN_TIMESTAMP, vector.getTimestamp())
                        .withValue(BitCarrierSilverstoneDataVector.COLUMN_CIN_ID, vector.getCinId())
                        .withValue(BitCarrierSilverstoneDataVector.COLUMN_CREATION_TIME,
                                vector.getCreationTime())
                        .withYieldAllowed(true);
                operationList.add(builder.build());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertTravelSummariesIntoProvider(@NonNull Context context,
                                                         @NonNull ArrayList<TravelSummary> travelSummaries)
            throws RemoteException, OperationApplicationException {
        if (travelSummaries.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TravelSummary travelSummary : travelSummaries) {
                ContentProviderOperation.Builder builder = ContentProviderOperation
                        .newInsert(BcsProviderModule.TRAVEL_SUMMARY_URI)
                        .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_CLOCK_TIME,
                                travelSummary.getTime());
                if (travelSummary.getTravelTimes() != null) {
                    // TODO   We assume the array only has one entry.  Otherwise problem.
                    TravelTime travelTime = travelSummary.getTravelTimes()[0];
                    builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_TIME_ID,
                            travelTime.gettId())
                            .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_FROM_LOCATION,
                                    travelTime.getFrom())
                            .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_TO_LOCATION,
                                    travelTime.getTo());
                }
                if (travelSummary.getDetails() != null) {
                    builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_SCORE,
                            travelSummary.getDetails().getScore());
                    if (travelSummary.getDetails().getPublish() != null) {
                        Stat stat = travelSummary.getDetails().getPublish();
                        builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_SPEED,
                                stat.getSpeed())
                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_ELAPSED,
                                        stat.getElapsed())
                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_TREND,
                                        stat.getTrend());
                    }
                }
                operationList.add(builder
                        .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_CIN_ID,
                                travelSummary.getCinId())
                        .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_CREATION_TIME,
                                travelSummary.getCreationTime())
                        .withYieldAllowed(true)
                        .build());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static Cursor getNodes(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.NODE_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID);
    }

    // Need the Silverstone data to test.
    public static Cursor getNodes(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.NODE_URI, new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                new String[]{String.valueOf(oldest), String.valueOf(newest)},
                BcsBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getSketches(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.SKETCH_URI,
                new String[]{"*"}, null, null, BcsContract.BitCarrierSilverstoneSketch.COLUMN_SKETCH_ID);
    }

    public static Cursor getSketches(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.SKETCH_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                new String[]{String.valueOf(oldest), String.valueOf(newest)},
                BcsBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getTravelSummaries(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.TRAVEL_SUMMARY_URI,
                new String[]{"*"}, null, null,
                BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_TIME_ID);
    }

    public static Cursor getTravelSummaries(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.TRAVEL_SUMMARY_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                new String[]{String.valueOf(oldest), String.valueOf(newest)},
                BcsBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getConfigVectors(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.CONFIG_VECTOR_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneConfigVector.COLUMN_VECTOR_ID);
    }

    public static Cursor getConfigVectors(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.CONFIG_VECTOR_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                new String[]{String.valueOf(oldest), String.valueOf(newest)},
                BcsBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getDataVectors(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.DATA_VECTOR_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneDataVector.COLUMN_VECTOR_ID);
    }

    public static Cursor getDataVectors(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.DATA_VECTOR_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                new String[]{String.valueOf(oldest), String.valueOf(newest)},
                BcsBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTravelTimes(@NonNull Context context) {
        return context.getContentResolver().query(
                Uri.withAppendedPath(BcsProviderModule.TRAVEL_SUMMARY_URI, "la"),
                new String[]{"*"}, null, null, BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_TIME_ID);
    }

    // TODO    Option to delete before a particular time.
    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_SKETCH:
                contentResolver.delete(BcsProviderModule.SKETCH_URI, null, null);
                break;
            case DATA_TYPE_NODE:
                contentResolver.delete(BcsProviderModule.NODE_URI, null, null);
                break;
            case DATA_TYPE_CONFIG_VECTOR:
                contentResolver.delete(BcsProviderModule.CONFIG_VECTOR_URI, null, null);
                break;
            case DATA_TYPE_DATA_VECTOR:
                contentResolver.delete(BcsProviderModule.DATA_VECTOR_URI, null, null);
                break;
            case DATA_TYPE_TRAVEL_SUMMARY:
                contentResolver.delete(BcsProviderModule.TRAVEL_SUMMARY_URI, null, null);
                break;
        }
    }
}
