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

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.metavector.Metavector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.route.Route;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneConfigSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneDataSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneLatestVectorTravelTime;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneMetavector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneRoute;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneTravelTime;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneVector;

public class BcsContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_DATA_SKETCH, DATA_TYPE_NODE, DATA_TYPE_ROUTE,
            DATA_TYPE_VECTOR, DATA_TYPE_CONFIG_SKETCH, DATA_TYPE_METAVECTOR})
    public @interface DataType {
    }

    public static final int DATA_TYPE_DATA_SKETCH = 1;
    public static final int DATA_TYPE_NODE = 2;
    public static final int DATA_TYPE_ROUTE = 6;
    public static final int DATA_TYPE_VECTOR = 3;
    public static final int DATA_TYPE_CONFIG_SKETCH = 4;
    public static final int DATA_TYPE_METAVECTOR = 5;

    public static void insertDataSketchesIntoProvider(@NonNull Context context,
                                                      @NonNull ArrayList<Sketch> sketches)
            throws RemoteException, OperationApplicationException {
        if (sketches.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Sketch sketch : sketches) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.DATA_SKETCH_URI)
                        .withValue(BitCarrierSilverstoneDataSketch.COLUMN_SKETCH_ID, sketch.getsId())
                        .withValue(BitCarrierSilverstoneDataSketch.COLUMN_VECTOR_ID, sketch.getvId())
                        .withValue(BitCarrierSilverstoneDataSketch.COLUMN_LICENSE, sketch.getLicense())
                        .withValue(BitCarrierSilverstoneDataSketch.COLUMN_LEVEL_OF_SERVICE,
                                sketch.getLevelOfService())
                        .withValue(BitCarrierSilverstoneDataSketch.COLUMN_COORDINATES,
                                sketch.getCoordinates())
                        .withValue(BitCarrierSilverstoneDataSketch.COLUMN_CIN_ID, sketch.getCinId())
                        .withValue(BitCarrierSilverstoneDataSketch.COLUMN_CREATION_TIME,
                                sketch.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertConfigSketchesIntoProvider(@NonNull Context context,
                                                        @NonNull ArrayList<net.uk.onetransport.android.modules
                                                                .bitcarriersilverstone.config.sketch.Sketch> sketches)
            throws RemoteException, OperationApplicationException {
        if (sketches.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch
                    sketch : sketches) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.CONFIG_SKETCH_URI)
                        .withValue(BitCarrierSilverstoneConfigSketch.COLUMN_SKETCH_ID,
                                sketch.getsId())
                        .withValue(BitCarrierSilverstoneConfigSketch.COLUMN_VECTOR_ID,
                                sketch.getvId())
                        .withValue(BitCarrierSilverstoneConfigSketch.COLUMN_COORDINATES,
                                sketch.getLocationJsonArray())
                        .withValue(BitCarrierSilverstoneConfigSketch.COLUMN_CIN_ID, sketch.getCinId())
                        .withValue(BitCarrierSilverstoneConfigSketch.COLUMN_CREATION_TIME,
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

    public static void insertRoutesIntoProvider(@NonNull Context context,
                                                @NonNull ArrayList<Route> routes)
            throws RemoteException, OperationApplicationException {
        if (routes.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Route route : routes) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.ROUTE_URI)
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_ROUTE_ID, route.getId())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_CITY_ID, route.getCityId())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_METAVECTOR_ID,
                                route.getMetavector())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_VECTOR1, route.getVector1())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_VECTOR1_CONTRIB,
                                route.getVector1Contrib())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_VECTOR2, route.getVector2())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_VECTOR2_CONTRIB,
                                route.getVector2Contrib())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_OFFSET, route.getOffset())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_DISTANCE, route.getDistance())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_ROUTE_DETECTIONS_MIN,
                                route.getRouteDetectionsMin())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_ZONE_ID, route.getZone())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_NAME, route.getName())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_CUSTOMER_NAME,
                                route.getCustomerName())
                        .withValue(BitCarrierSilverstoneRoute.COLUMN_CONFIGURATION,
                                route.getConfiguration())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertMetavectorsIntoProvider(@NonNull Context context,
                                                     @NonNull ArrayList<Metavector> metavectors)
            throws RemoteException, OperationApplicationException {
        if (metavectors.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Metavector metavector : metavectors) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BcsProviderModule.METAVECTOR_URI)
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_METAVECTOR_ID,
                                metavector.getId())
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_NAME,
                                metavector.getName())
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_CUSTOMER_NAME,
                                metavector.getCustomerName())
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_CITY_ID,
                                metavector.getCityId())
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_ZONE,
                                metavector.getZone())
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_SEQUENCE,
                                metavector.getSequence())
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_CREATION_TIME,
                                metavector.getCreationTime())
                        .withValue(BitCarrierSilverstoneMetavector.COLUMN_CIN_ID,
                                metavector.getCinId())
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
                        .withValue(BitCarrierSilverstoneVector.COLUMN_CUSTOMER_NAME,
                                vector.getCustomerName())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_FROM, vector.getFrom())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_TO, vector.getTo())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_DISTANCE, vector.getDistance())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_ZONE, vector.getZone())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_CITY_ID, vector.getCityId())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_BLOCK_TIME, vector.getBlockTime())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_SEGREGATION, vector.getSegregation())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_CONFIGURATION,
                                vector.getConfiguration())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_PRIORITY, vector.getPriority())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_CHECK_FORCED,
                                vector.getCheckForced())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_SKETCH_ID, vector.getsId())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_ROUTE_ID, vector.getrId())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_LEVELS, vector.getLevels())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_GREEN, vector.getGreen())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_YELLOW, vector.getYellow())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_AVERAGE_GREEN,
                                vector.getAverageGreen())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_AVERAGE_YELLOW,
                                vector.getAverageYellow())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_DETECTIONS_MIN,
                                vector.getDetectionsMin())
                        .withValue(BitCarrierSilverstoneVector.COLUMN_HAS_COLOUR, vector.getHasColour())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BcsProviderModule.AUTHORITY, operationList);
        }
    }

    public static Cursor getConfigSketches(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.CONFIG_SKETCH_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneConfigSketch.COLUMN_SKETCH_ID);
    }

    public static Cursor getConfigSketchNames(@NonNull Context context, int sketchId) {
        return context.getContentResolver().query(BcsProviderModule.CONFIG_SKETCH_URI,
                new String[]{BitCarrierSilverstoneConfigSketch.COLUMN_CIN_ID},
                BitCarrierSilverstoneConfigSketch.COLUMN_SKETCH_ID + "=?",
                new String[]{String.valueOf(sketchId)},
                BitCarrierSilverstoneConfigSketch.COLUMN_CIN_ID);
    }

    public static Cursor getDataSketches(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.DATA_SKETCH_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneDataSketch.COLUMN_SKETCH_ID);
    }

    public static Cursor getDataSketchNames(@NonNull Context context, int sketchId) {
        return context.getContentResolver().query(BcsProviderModule.DATA_SKETCH_URI,
                new String[]{BitCarrierSilverstoneDataSketch.COLUMN_CIN_ID},
                BitCarrierSilverstoneDataSketch.COLUMN_SKETCH_ID + "=?",
                new String[]{String.valueOf(sketchId)},
                BitCarrierSilverstoneDataSketch.COLUMN_CIN_ID);
    }

    public static Cursor getNodes(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.NODE_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID);
    }

    public static Cursor getNodeNames(@NonNull Context context, int nodeId) {
        return context.getContentResolver().query(BcsProviderModule.NODE_URI,
                new String[]{BitCarrierSilverstoneNode.COLUMN_CIN_ID},
                BitCarrierSilverstoneNode.COLUMN_NODE_ID + "=?",
                new String[]{String.valueOf(nodeId)},
                BitCarrierSilverstoneNode.COLUMN_CIN_ID);
    }

    public static Cursor getRoutes(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.ROUTE_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneRoute.COLUMN_ROUTE_ID);
    }

    public static Cursor getRouteNames(@NonNull Context context, int routeId) {
        return context.getContentResolver().query(BcsProviderModule.ROUTE_URI,
                new String[]{BitCarrierSilverstoneRoute.COLUMN_CIN_ID},
                BitCarrierSilverstoneRoute.COLUMN_ROUTE_ID + "=?",
                new String[]{String.valueOf(routeId)},
                BitCarrierSilverstoneRoute.COLUMN_CIN_ID);
    }

    public static Cursor getMetaVectors(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.METAVECTOR_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneMetavector.COLUMN_METAVECTOR_ID);
    }

    public static Cursor getMetaVectorNames(@NonNull Context context, int metaVectorId) {
        return context.getContentResolver().query(BcsProviderModule.METAVECTOR_URI,
                new String[]{BitCarrierSilverstoneMetavector.COLUMN_CIN_ID},
                BitCarrierSilverstoneMetavector.COLUMN_METAVECTOR_ID + "=?",
                new String[]{String.valueOf(metaVectorId)},
                BitCarrierSilverstoneMetavector.COLUMN_CIN_ID);
    }

    public static Cursor getTravelTimes(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.TRAVEL_TIME_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneTravelTime.COLUMN_TRAVEL_TIME_ID);
    }

    public static Cursor getLatestTravelTimes(@NonNull Context context) {
        return context.getContentResolver().query(
                Uri.withAppendedPath(BcsProviderModule.TRAVEL_TIME_URI, "la"),
                new String[]{"*"}, null, null, BitCarrierSilverstoneTravelTime.COLUMN_TRAVEL_TIME_ID);
    }

    public static Cursor getVectors(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.VECTOR_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneVector.COLUMN_VECTOR_ID);
    }

    public static Cursor getLatestVectorTravelTimes(@NonNull Context context) {
        return context.getContentResolver()
                .query(BcsProviderModule.LATEST_VECTOR_TRAVEL_TIME_URI, new String[]{"*"}, null, null,
                        BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TRAVEL_TIME_ID);
    }

    public static Cursor getVectorTravelTimes(@NonNull Context context) {
        return context.getContentResolver()
                .query(BcsProviderModule.VECTOR_TRAVEL_TIME_URI, new String[]{"*"}, null, null,
                        BitCarrierSilverstoneLatestVectorTravelTime.COLUMN_TRAVEL_TIME_ID);
    }

    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_DATA_SKETCH:
                contentResolver.delete(BcsProviderModule.DATA_SKETCH_URI, null, null);
                break;
            case DATA_TYPE_NODE:
                contentResolver.delete(BcsProviderModule.NODE_URI, null, null);
                break;
            case DATA_TYPE_VECTOR:
                contentResolver.delete(BcsProviderModule.VECTOR_URI, null, null);
                break;
            case DATA_TYPE_CONFIG_SKETCH:
                contentResolver.delete(BcsProviderModule.CONFIG_SKETCH_URI, null, null);
                break;
        }
    }
}
