package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneSketch;

public class BcsContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_SKETCH, DATA_TYPE_TRAVEL_SUMMARY,
            DATA_TYPE_VECTOR_STATUS, DATA_TYPE_NODE})
    public @interface DataType {
    }

    public static final int DATA_TYPE_SKETCH = 1;
    public static final int DATA_TYPE_TRAVEL_SUMMARY = 2;
    public static final int DATA_TYPE_VECTOR_STATUS = 3;
    public static final int DATA_TYPE_NODE = 4;

    public static void insertSketchesIntoProvider(@NonNull Context context,
                                                  @NonNull ArrayList<Sketch> sketches)
            throws RemoteException, OperationApplicationException {
        if (sketches.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Sketch sketch : sketches) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.SKETCH_URI)
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_SENSOR_ID, sketch.getsId())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_VID, sketch.getvId())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_LICENSE, sketch.getLicense())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_LEVEL_OF_SERVICE,
                                sketch.getLevelOfService())
                        .withValue(BitCarrierSilverstoneSketch.COLUMN_COORDINATES, sketch.getCoordinates())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

//    public static void insertTravelSummariesIntoProvider(@NonNull Context context,
//                                                         @NonNull ArrayList<TravelSummary> travelSummaries)
//            throws RemoteException, OperationApplicationException {
//        if (travelSummaries.size() > 0) {
//            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
//            for (TravelSummary travelSummary : travelSummaries) {
//                ContentProviderOperation.Builder builder = ContentProviderOperation
//                        .newInsert(BcsProviderModule.TRAVEL_SUMMARY_URI)
//                        .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_RID, travelSummary.getrId())
//                        .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_TIME, travelSummary.getTime())
//                        .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LEVEL_OF_SERVICE,
//                                travelSummary.getLevelOfService());
//                if (travelSummary.getAverage() != null) {
//                    builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_SCORE,
//                            travelSummary.getAverage().getScore());
//                    if (travelSummary.getAverage().getPublish() != null) {
//                        builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_SPEED,
//                                travelSummary.getAverage().getPublish().getSpeed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_ELAPSED,
//                                        travelSummary.getAverage().getPublish().getElapsed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_TREND,
//                                        travelSummary.getAverage().getPublish().getTrend())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_PUBLISH_READINGS,
//                                        travelSummary.getAverage().getPublish().getReadings());
//                    }
//                    if (travelSummary.getAverage().getCalculated() != null) {
//                        builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_SPEED,
//                                travelSummary.getAverage().getCalculated().getSpeed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_ELAPSED,
//                                        travelSummary.getAverage().getCalculated().getElapsed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_TREND,
//                                        travelSummary.getAverage().getCalculated().getTrend())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_AVERAGE_CALCULATED_READINGS,
//                                        travelSummary.getAverage().getCalculated().getReadings());
//                    }
//                }
//                if (travelSummary.getLast() != null) {
//                    builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_SCORE,
//                            travelSummary.getLast().getScore());
//                    if (travelSummary.getLast().getPublish() != null) {
//                        builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_SPEED,
//                                travelSummary.getLast().getPublish().getSpeed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_ELAPSED,
//                                        travelSummary.getLast().getPublish().getElapsed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_TREND,
//                                        travelSummary.getLast().getPublish().getTrend())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_PUBLISH_READINGS,
//                                        travelSummary.getLast().getPublish().getReadings());
//                    }
//                    if (travelSummary.getLast().getCalculated() != null) {
//                        builder.withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_SPEED,
//                                travelSummary.getLast().getCalculated().getSpeed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_ELAPSED,
//                                        travelSummary.getLast().getCalculated().getElapsed())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_TREND,
//                                        travelSummary.getLast().getCalculated().getTrend())
//                                .withValue(BitCarrierSilverstoneTravelSummary.COLUMN_LAST_CALCULATED_READINGS,
//                                        travelSummary.getLast().getCalculated().getReadings());
//                    }
//                }
//                operationList.add(builder.withYieldAllowed(true).build());
//            }
//            ContentResolver contentResolver = context.getContentResolver();
//            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
//        }
//    }

//    public static void insertVectorStatusesIntoProvider(@NonNull Context context,
//                                                        @NonNull ArrayList<VectorStatus> vectorStatuses)
//            throws RemoteException, OperationApplicationException {
//        if (vectorStatuses.size() > 0) {
//            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
//            for (VectorStatus vectorStatus : vectorStatuses) {
//                ContentProviderOperation.Builder builder = ContentProviderOperation
//                        .newInsert(BcsProviderModule.VECTOR_STATUS_URI)
//                        .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_VID, vectorStatus.getvId())
//                        .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_TIME, vectorStatus.getTime())
//                        .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LEVEL_OF_SERVICE,
//                                vectorStatus.getLevelOfService());
//                if (vectorStatus.getAverage() != null) {
//                    builder.withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_SCORE,
//                            vectorStatus.getAverage().getScore());
//                    if (vectorStatus.getAverage().getPublish() != null) {
//                        builder.withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_SPEED,
//                                vectorStatus.getAverage().getPublish().getSpeed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_ELAPSED,
//                                        vectorStatus.getAverage().getPublish().getElapsed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_TREND,
//                                        vectorStatus.getAverage().getPublish().getTrend())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_PUBLISH_READINGS,
//                                        vectorStatus.getAverage().getPublish().getReadings());
//                    }
//                    if (vectorStatus.getAverage().getCalculated() != null) {
//                        builder.withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_SPEED,
//                                vectorStatus.getAverage().getCalculated().getSpeed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_ELAPSED,
//                                        vectorStatus.getAverage().getCalculated().getElapsed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_TREND,
//                                        vectorStatus.getAverage().getCalculated().getTrend())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_AVERAGE_CALCULATED_READINGS,
//                                        vectorStatus.getAverage().getCalculated().getReadings());
//                    }
//                }
//                if (vectorStatus.getLast() != null) {
//                    builder.withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_SCORE,
//                            vectorStatus.getLast().getScore());
//                    if (vectorStatus.getLast().getPublish() != null) {
//                        builder.withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_SPEED,
//                                vectorStatus.getLast().getPublish().getSpeed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_ELAPSED,
//                                        vectorStatus.getLast().getPublish().getElapsed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_TREND,
//                                        vectorStatus.getLast().getPublish().getTrend())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_PUBLISH_READINGS,
//                                        vectorStatus.getLast().getPublish().getReadings());
//                    }
//                    if (vectorStatus.getLast().getCalculated() != null) {
//                        builder.withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_SPEED,
//                                vectorStatus.getLast().getCalculated().getSpeed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_ELAPSED,
//                                        vectorStatus.getLast().getCalculated().getElapsed())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_TREND,
//                                        vectorStatus.getLast().getCalculated().getTrend())
//                                .withValue(BitCarrierSilverstoneVectorStatus.COLUMN_LAST_CALCULATED_READINGS,
//                                        vectorStatus.getLast().getCalculated().getReadings());
//                    }
//                }
//                operationList.add(builder.withYieldAllowed(true).build());
//            }
//            ContentResolver contentResolver = context.getContentResolver();
//            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
//        }
//    }

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
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static Cursor getSketches(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.SKETCH_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneSketch.COLUMN_SENSOR_ID);
    }

//    public static Cursor getTravelSummaries(@NonNull Context context) {
//        return context.getContentResolver().query(BcsProviderModule.TRAVEL_SUMMARY_URI,
//                new String[]{"*"}, null, null, BitCarrierSilverstoneTravelSummary.COLUMN_RID);
//    }
//
//    public static Cursor getVectorStatuses(@NonNull Context context) {
//        return context.getContentResolver().query(BcsProviderModule.VECTOR_STATUS_URI,
//                new String[]{"*"}, null, null, BitCarrierSilverstoneVectorStatus.COLUMN_VID);
//    }

    public static Cursor getNodes(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.NODE_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID);
    }

    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_SKETCH:
                contentResolver.delete(BcsProviderModule.SKETCH_URI, null, null);
                break;
            case DATA_TYPE_TRAVEL_SUMMARY:
                contentResolver.delete(BcsProviderModule.TRAVEL_SUMMARY_URI, null, null);
                break;
            case DATA_TYPE_VECTOR_STATUS:
                contentResolver.delete(BcsProviderModule.VECTOR_STATUS_URI, null, null);
                break;
            case DATA_TYPE_NODE:
                contentResolver.delete(BcsProviderModule.NODE_URI, null, null);
                break;
        }
    }
}
