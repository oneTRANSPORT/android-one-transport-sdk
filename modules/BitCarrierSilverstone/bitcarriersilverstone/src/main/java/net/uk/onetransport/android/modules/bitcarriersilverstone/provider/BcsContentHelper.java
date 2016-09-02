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
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Stat;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelTime;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneTravelSummary;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneVector;

public class BcsContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            DATA_TYPE_SKETCH,
            DATA_TYPE_NODE,
            DATA_TYPE_TRAVEL_SUMMARY,
            DATA_TYPE_VECTOR
    })
    public @interface DataType {
    }

    public static final int DATA_TYPE_SKETCH = 1;
    public static final int DATA_TYPE_NODE = 2;
    public static final int DATA_TYPE_TRAVEL_SUMMARY = 6;
    public static final int DATA_TYPE_VECTOR = 3;

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
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_SKETCH_ID, sketch.getsId())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_VECTOR_ID, sketch.getvId())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_LICENSE, sketch.getLicense())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_LEVEL_OF_SERVICE,
                                sketch.getLevelOfService())
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

    public static void insertVectorsIntoProvider(@NonNull Context context,
                                                 @NonNull ArrayList<Vector> vectors)
            throws RemoteException, OperationApplicationException {
        if (vectors.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Vector vector : vectors) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.VECTOR_URI)
                        .withValue(BitCarrierSilverstoneVector.COLUMN_VECTOR_ID, vector.getId())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_NAME, vector.getName())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_CUSTOMER_NAME,
                                vector.getCustomerName())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_FROM, vector.getFrom())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_TO, vector.getTo())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_DISTANCE, vector.getDistance())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_ZONE, vector.getZone())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_CITY_ID, vector.getCityId())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_BLOCK_TIME,
                                vector.getBlockTime())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_SEGREGATION,
                                vector.getSegregation())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_CONFIGURATION,
                                vector.getConfiguration())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_PRIORITY, vector.getPriority())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_CHECK_FORCED,
                                vector.getCheckForced())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_SKETCH_ID, vector.getsId())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_ROUTE_ID, vector.getrId())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_LEVELS, vector.getLevels())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_GREEN, vector.getGreen())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_YELLOW, vector.getYellow())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_AVERAGE_GREEN,
                                vector.getAverageGreen())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_AVERAGE_YELLOW,
                                vector.getAverageYellow())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_DETECTIONS_MIN,
                                vector.getDetectionsMin())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_HAS_COLOUR,
                                vector.getHasColour())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_CIN_ID, vector.getCinId())
                        .withValue(BcsContract.BitCarrierSilverstoneVector.COLUMN_CREATION_TIME,
                                vector.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
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

    public static Cursor getVectors(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.VECTOR_URI,
                new String[]{"*"}, null, null, BcsContract.BitCarrierSilverstoneVector.COLUMN_VECTOR_ID);
    }

    public static Cursor getVectors(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.VECTOR_URI,
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
            case DATA_TYPE_VECTOR:
                contentResolver.delete(BcsProviderModule.VECTOR_URI, null, null);
                break;
            case DATA_TYPE_TRAVEL_SUMMARY:
                contentResolver.delete(BcsProviderModule.TRAVEL_SUMMARY_URI, null, null);
                break;
        }
    }
}
