package net.uk.onetransport.android.county.bucks.provider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.roadworks.Period;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorks;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedContract;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksCarPark;

public class BucksContentHelper { // TODO    Extends Common helper?

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_CAR_PARK, DATA_TYPE_VMS, DATA_TYPE_TRAFFIC_FLOW,
            DATA_TYPE_ROAD_WORKS})
    public @interface DataType {
    }

    public static final int DATA_TYPE_CAR_PARK = 3;
    public static final int DATA_TYPE_TRAFFIC_FLOW = 4;
    public static final int DATA_TYPE_VMS = 5;
    public static final int DATA_TYPE_ROAD_WORKS = 6;

    private static final String CREATION_INTERVAL_SELECTION = // TODO    From Common?
            BucksBaseColumns.COLUMN_CREATION_TIME + ">=? AND "
                    + BucksBaseColumns.COLUMN_CREATION_TIME + "<=?";
    private static final String CREATED_BEFORE = "creation_time < ?";

    // Could really use the proper column identifiers here.
    private static final String LAT_LON_BOX = "latitude >= ? and longitude >= ? "
            + "and latitude <= ? and longitude <= ?";
    private static final String LAT_LON_TO_FROM_BOX = "to_latitude >= ? and to_longitude >= ? "
            + "and to_latitude <= ? and to_longitude <= ? "
            + "and from_latitude >= ? and from_longitude >= ? "
            + "and from_latitude <= ? and from_longitude <= ?";

    public static void insertIntoProvider(@NonNull Context context, @NonNull CarPark[] carParks)
            throws RemoteException, OperationApplicationException {
        if (carParks.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (CarPark carPark : carParks) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.CAR_PARK_URI)
                        .withValue(BucksCarPark.COLUMN_CAR_PARK_IDENTITY, carPark.getCarParkIdentity())
                        .withValue(BucksCarPark.COLUMN_LATITUDE, carPark.getLatitude())
                        .withValue(BucksCarPark.COLUMN_LONGITUDE, carPark.getLongitude())
                        .withValue(BucksCarPark.COLUMN_OCCUPANCY, carPark.getOccupancy())
                        .withValue(BucksCarPark.COLUMN_OCCUPANCY_TREND, carPark.getOccupancyTrend())
                        .withValue(BucksCarPark.COLUMN_TOTAL_PARKING_CAPACITY,
                                carPark.getTotalParkingCapacity())
                        .withValue(BucksCarPark.COLUMN_FILL_RATE, carPark.getFillRate())
                        .withValue(BucksCarPark.COLUMN_EXIT_RATE, carPark.getExitRate())
                        .withValue(BucksCarPark.COLUMN_ALMOST_FULL_INCREASING,
                                carPark.getAlmostFullIncreasing())
                        .withValue(BucksCarPark.COLUMN_ALMOST_FULL_DECREASING,
                                carPark.getAlmostFullDecreasing())
                        .withValue(BucksCarPark.COLUMN_FULL_INCREASING, carPark.getFullIncreasing())
                        .withValue(BucksCarPark.COLUMN_FULL_DECREASING, carPark.getFullDecreasing())
                        .withValue(BucksCarPark.COLUMN_STATUS, carPark.getStatus())
                        .withValue(BucksCarPark.COLUMN_STATUS_TIME, carPark.getStatusTime())
                        .withValue(BucksCarPark.COLUMN_QUEUING_TIME, carPark.getQueuingTime())
                        .withValue(BucksCarPark.COLUMN_PARKING_AREA_NAME,
                                carPark.getParkingAreaName())
                        .withValue(BucksCarPark.COLUMN_ENTRANCE_FULL, carPark.getEntranceFull())
                        .withValue(BucksCarPark.COLUMN_CIN_ID, carPark.getCinId())
                        .withValue(BucksCarPark.COLUMN_CREATION_TIME, carPark.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    // TODO    All these inserts need to be checked.
    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficFlow[] trafficFlows)
            throws RemoteException, OperationApplicationException {
        if (trafficFlows.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficFlow trafficFlow : trafficFlows) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.TRAFFIC_FLOW_URI)
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_LOCATION_ID,
                                trafficFlow.getId())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_FROM_DESCRIPTOR,
                                trafficFlow.getFromDescriptor())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_FROM_LATITUDE,
                                trafficFlow.getFromLatitude())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_FROM_LONGITUDE,
                                trafficFlow.getFromLongitude())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_TO_DESCRIPTOR,
                                trafficFlow.getToDescriptor())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_TO_LATITUDE,
                                trafficFlow.getToLatitude())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_TO_LONGITUDE,
                                trafficFlow.getToLongitude())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_TPEG_DIRECTION,
                                trafficFlow.getTpegDirection())
                        .withValue(BucksContract.BucksTrafficFlow.COLUMN_VEHICLE_FLOW,
                                trafficFlow.getVehicleFlow())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull VariableMessageSign[] variableMessageSigns)
            throws RemoteException, OperationApplicationException {
        if (variableMessageSigns.length > 0) {
            StringBuilder builder = new StringBuilder();
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (VariableMessageSign variableMessageSign : variableMessageSigns) {
                builder.delete(0, builder.length());
                String[] vmsLegends = variableMessageSign.getVmsLegends();
                for (int i = 0; i < vmsLegends.length; i++) {
                    builder.append(vmsLegends[i]);
                    if (i < vmsLegends.length - 1) {
                        builder.append("|");
                    }
                }
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI)
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_LOCATION_ID,
                                variableMessageSign.getLocationId())
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_NAME,
                                variableMessageSign.getName())
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS,
                                variableMessageSign.getNumberOfCharacters())
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_NUMBER_OF_ROWS,
                                variableMessageSign.getNumberOfRows())
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_VMS_LEGENDS,
                                builder.toString())
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_VMS_TYPE,
                                variableMessageSign.getVmsType())
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_LATITUDE,
                                variableMessageSign.getLatitude())
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_LONGITUDE,
                                variableMessageSign.getLongitude())
// TODO    // FIXME: 14/09/2016
                        .withValue(BucksContract.BucksVariableMessageSign.COLUMN_DESCRIPTOR,
                                variableMessageSign.getDescription())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull RoadWorks[] roadWorks)
            throws RemoteException, OperationApplicationException {
        if (roadWorks.length > 0) {
            StringBuilder builder = new StringBuilder();
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (RoadWorks roadWork : roadWorks) {
                builder.delete(0, builder.length());
                Period[] periods = roadWork.getValidity().getPeriods();
                for (int i = 0; i < periods.length; i++) {
                    builder.append(periods[i].getStart());
                    builder.append("|");
                    builder.append(periods[i].getEnd());
                    if (i < periods.length - 1) {
                        builder.append("|");
                    }
                }
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.ROAD_WORKS_URI)
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_ID, roadWork.getId())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_COMMENT, roadWork.getComment())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT,
                                roadWork.getEffectOnRoadLayout())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE,
                                roadWork.getRoadMaintenanceType())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_IMPACT_ON_TRAFFIC,
                                roadWork.getImpactOnTraffic())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_STATUS, roadWork.getValidity().getStatus())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_OVERALL_START_TIME,
                                roadWork.getValidity().getOverallStartTime())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_OVERALL_END_TIME,
                                roadWork.getValidity().getOverallEndTime())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_PERIODS, builder.toString())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_LATITUDE,
                                roadWork.getLocation().getLatitude())
                        .withValue(BucksContract.BucksRoadWorks.COLUMN_LONGITUDE,
                                roadWork.getLocation().getLongitude())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    // TODO    Get everything.
    // TODO    Get latest set.
    // TODO    Get all sets in time interval.
    // TODO    Get latest set in lat/lon rectangle.

    public static Cursor getCarParks(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.CAR_PARK_URI,
                new String[]{"*"}, null, null, BucksCarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getLatestCarParks(@NonNull Context context){
        return context.getContentResolver().query(BucksProviderModule.LATEST_CAR_PARK_URI,
                new String[]{"*"}, null, null,
                BucksLatestCarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getCarParks(@NonNull Context context, double minLatitude, double minLongitude,
                                     double maxLatitude, double maxLongitude) {
        return context.getContentResolver().query(BucksProviderModule.CAR_PARK_URI,
                new String[]{"*"}, LAT_LON_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksCarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getCarParks(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.CAR_PARK_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION,
                new String[]{String.valueOf(oldest), String.valueOf(newest)},
                BucksBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getTrafficFlows(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, null, null, BucksContract.BucksTrafficFlow.COLUMN_LOCATION_ID);
    }

    public static Cursor getTrafficFlows(@NonNull Context context, double minLatitude,
                                         double minLongitude, double maxLatitude, double maxLongitude) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, LAT_LON_TO_FROM_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude),
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksContract.BucksTrafficFlow.COLUMN_LOCATION_ID);
    }

    public static Cursor getVariableMessageSigns(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, null, null, BucksContract.BucksVariableMessageSign.COLUMN_NAME);
    }

    public static Cursor getVariableMessageSigns(@NonNull Context context, double minLatitude,
                                                 double minLongitude, double maxLatitude, double maxLongitude) {
        return context.getContentResolver().query(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, LAT_LON_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksContract.BucksVariableMessageSign.COLUMN_NAME);
    }

    public static Cursor getRoadWorks(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.ROAD_WORKS_URI,
                new String[]{"*"}, null, null, BucksContract.BucksRoadWorks.COLUMN_ID);
    }

    public static Cursor getRoadWorks(@NonNull Context context, double minLatitude,
                                      double minLongitude, double maxLatitude, double maxLongitude) {
        return context.getContentResolver().query(BucksProviderModule.ROAD_WORKS_URI,
                new String[]{"*"}, LAT_LON_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksContract.BucksRoadWorks.COLUMN_ID);
    }

    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_CAR_PARK:
                contentResolver.delete(BucksProviderModule.CAR_PARK_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(BucksProviderModule.TRAFFIC_FLOW_URI, null, null);
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI, null, null);
                break;
            case DATA_TYPE_ROAD_WORKS:
                contentResolver.delete(BucksProviderModule.ROAD_WORKS_URI, null, null);
                break;
        }
    }

    public static void deleteFromProviderBeforeTime(@NonNull Context context, @DataType int dataType,
                                                    long creationTime) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_CAR_PARK:
                contentResolver.delete(BucksProviderModule.CAR_PARK_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(BucksProviderModule.TRAFFIC_FLOW_URI,  CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                        CREATED_BEFORE, new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_ROAD_WORKS:
                contentResolver.delete(BucksProviderModule.ROAD_WORKS_URI,  CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
        }
    }

            //    public static void refreshLastUpdated(@NonNull Context context) {
//        ContentResolver contentResolver = context.getContentResolver();
//        ContentValues values = new ContentValues();
//        values.put(LastUpdatedContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS,
//                System.currentTimeMillis());
//        contentResolver.update(LastUpdatedProviderModule.LAST_UPDATED_URI, values, null, null);
//    }
    // TODO    Should be in Common?
    public static Cursor getLastUpdated(@NonNull Context context) {
        return context.getContentResolver().query(LastUpdatedProviderModule.LAST_UPDATED_URI,
                new String[]{
                        LastUpdatedContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS
                }, null, null, null);
    }
}
