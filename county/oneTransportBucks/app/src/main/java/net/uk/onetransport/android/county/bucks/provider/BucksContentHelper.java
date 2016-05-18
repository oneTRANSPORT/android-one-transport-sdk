package net.uk.onetransport.android.county.bucks.provider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;

import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocation;
import net.uk.onetransport.android.county.bucks.locations.SegmentLocation;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class BucksContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_LINK_LOCATION, DATA_TYPE_SECTION_LOCATION,
            DATA_TYPE_TR_LOCATION, DATA_TYPE_VMS_LOCATION, DATA_TYPE_CAR_PARK,
            DATA_TYPE_VMS, DATA_TYPE_TRAFFIC_FLOW})
    public @interface DataType {
    }

    public static final int DATA_TYPE_LINK_LOCATION = 1;
    public static final int DATA_TYPE_SECTION_LOCATION = 2;
    public static final int DATA_TYPE_TR_LOCATION = 3;
    public static final int DATA_TYPE_VMS_LOCATION = 4;
    public static final int DATA_TYPE_CAR_PARK = 5;
    public static final int DATA_TYPE_TRAFFIC_FLOW = 6;
    public static final int DATA_TYPE_VMS = 7;

    public static void insertIntoProvider(Context context, SegmentLocation[] segmentLocations)
            throws RemoteException, OperationApplicationException {
        if (segmentLocations != null && segmentLocations.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (SegmentLocation segmentLocation : segmentLocations) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProvider.SEGMENT_LOCATION_URI)
                        .withValue(BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                                segmentLocation.getLocationId())
                        .withValue(BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                                segmentLocation.getToLatitude())
                        .withValue(BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                                segmentLocation.getToLongitude())
                        .withValue(BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                                segmentLocation.getFromLatitude())
                        .withValue(BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                                segmentLocation.getFromLongitude())
                        .withValue(BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                                segmentLocation.getToDescriptor())
                        .withValue(BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                                segmentLocation.getFromDescriptor())
                        .withValue(BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION,
                                segmentLocation.getTpegDirection())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProvider.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(Context context,
                                          PredefinedVmsLocation[] predefinedVmsLocations)
            throws RemoteException, OperationApplicationException {
        if (predefinedVmsLocations != null && predefinedVmsLocations.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (PredefinedVmsLocation predefinedVmsLocation : predefinedVmsLocations) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProvider.VMS_LOCATION_URI)
                        .withValue(BucksContract.VmsLocation.COLUMN_NAME,
                                predefinedVmsLocation.getName())
                        .withValue(BucksContract.VmsLocation.COLUMN_LOCATION_ID,
                                predefinedVmsLocation.getLocationId())
                        .withValue(BucksContract.VmsLocation.COLUMN_LATITUDE,
                                predefinedVmsLocation.getLatitude())
                        .withValue(BucksContract.VmsLocation.COLUMN_LONGITUDE,
                                predefinedVmsLocation.getLongitude())
                        .withValue(BucksContract.VmsLocation.COLUMN_DESCRIPTOR,
                                predefinedVmsLocation.getDescriptor())
                        .withValue(BucksContract.VmsLocation.COLUMN_TPEG_DIRECTION,
                                predefinedVmsLocation.getTpegDirection())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProvider.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(Context context, CarPark[] carParks)
            throws RemoteException, OperationApplicationException {
        if (carParks != null && carParks.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (CarPark carPark : carParks) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProvider.CAR_PARK_URI)
                        .withValue(BucksContract.CarPark.COLUMN_RADIUS, carPark.getRadius())
                        .withValue(BucksContract.CarPark.COLUMN_LATITUDE, carPark.getLatitude())
                        .withValue(BucksContract.CarPark.COLUMN_LONGITUDE, carPark.getLongitude())
                        .withValue(BucksContract.CarPark.COLUMN_ENTRANCE_FULL, carPark.getEntranceFull())
                        .withValue(BucksContract.CarPark.COLUMN_FULL_INCREASING,
                                carPark.getFullIncreasing())
                        .withValue(BucksContract.CarPark.COLUMN_FULL_DECREASING,
                                carPark.getFullDecreasing())
                        .withValue(BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY,
                                carPark.getCarParkIdentity())
                        .withValue(BucksContract.CarPark.COLUMN_TOTAL_PARKING_CAPACITY,
                                carPark.getTotalParkingCapacity())
                        .withValue(BucksContract.CarPark.COLUMN_ALMOST_FULL_INCREASING,
                                carPark.getAlmostFullIncreasing())
                        .withValue(BucksContract.CarPark.COLUMN_ALMOST_FULL_DECREASING,
                                carPark.getAlmostFullDecreasing())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProvider.AUTHORITY, operationList);
        }
    }

    // TODO Merge different JSON objects.
    public static void insertIntoProvider(Context context, TrafficFlow[] trafficFlows)
            throws RemoteException, OperationApplicationException {
        if (trafficFlows != null && trafficFlows.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficFlow trafficFlow : trafficFlows) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProvider.TRAFFIC_FLOW_URI)
                        .withValue(BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE,
                                trafficFlow.getLocationReference())
                        .withValue(BucksContract.TrafficFlow.COLUMN_VEHICLE_FLOW,
                                trafficFlow.getVehicleFlow())
                        .withValue(BucksContract.TrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED,
                                trafficFlow.getAverageVehicleSpeed())
                        .withValue(BucksContract.TrafficFlow.COLUMN_TRAVEL_TIME, trafficFlow.getTravelTime())
                        .withValue(BucksContract.TrafficFlow.COLUMN_FREE_FLOW_SPEED,
                                trafficFlow.getFreeFlowSpeed())
                        .withValue(BucksContract.TrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME,
                                trafficFlow.getFreeFlowTravelTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProvider.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(Context context, VariableMessageSign[] variableMessageSigns)
            throws RemoteException, OperationApplicationException {
        if (variableMessageSigns != null && variableMessageSigns.length > 0) {
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
                        .newInsert(BucksProvider.VARIABLE_MESSAGE_SIGN_URI)
                        .withValue(BucksContract.VariableMessageSign.COLUMN_LOCATION_REFERENCE,
                                variableMessageSign.getLocationReference())
                        .withValue(BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS,
                                variableMessageSign.getNumberOfCharacters())
                        .withValue(BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_ROWS,
                                variableMessageSign.getNumberOfRows())
                        .withValue(BucksContract.VariableMessageSign.COLUMN_VMS_LEGENDS,
                                builder.toString())
                        .withValue(BucksContract.VariableMessageSign.COLUMN_VMS_TYPE,
                                variableMessageSign.getVmsType())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProvider.AUTHORITY, operationList);
        }
    }

    public static Cursor getSegmentLocations(Context context) {
        return context.getContentResolver().query(BucksProvider.SEGMENT_LOCATION_URI,
                new String[]{
                        BucksContract.SegmentLocation._ID,
                        BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                        BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                        BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                        BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                        BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                        BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                        BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                        BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION
                }, null, null, BucksContract.SegmentLocation.COLUMN_LOCATION_ID);
    }

    public static Cursor getVmsLocations(Context context) {
        return context.getContentResolver().query(BucksProvider.VMS_LOCATION_URI,
                new String[]{
                        BucksContract.VmsLocation._ID,
                        BucksContract.VmsLocation.COLUMN_NAME,
                        BucksContract.VmsLocation.COLUMN_LOCATION_ID,
                        BucksContract.VmsLocation.COLUMN_LATITUDE,
                        BucksContract.VmsLocation.COLUMN_LONGITUDE,
                        BucksContract.VmsLocation.COLUMN_DESCRIPTOR,
                        BucksContract.VmsLocation.COLUMN_TPEG_DIRECTION
                }, null, null, BucksContract.VmsLocation.COLUMN_NAME);
    }

    public static Cursor getCarParks(Context context) {
        return context.getContentResolver().query(BucksProvider.CAR_PARK_URI,
                new String[]{
                        BucksContract.CarPark._ID,
                        BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY,
                        BucksContract.CarPark.COLUMN_TOTAL_PARKING_CAPACITY,
                        BucksContract.CarPark.COLUMN_ALMOST_FULL_DECREASING,
                        BucksContract.CarPark.COLUMN_ALMOST_FULL_INCREASING,
                        BucksContract.CarPark.COLUMN_FULL_DECREASING,
                        BucksContract.CarPark.COLUMN_FULL_INCREASING,
                        BucksContract.CarPark.COLUMN_ENTRANCE_FULL,
                        BucksContract.CarPark.COLUMN_RADIUS,
                        BucksContract.CarPark.COLUMN_LATITUDE,
                        BucksContract.CarPark.COLUMN_LONGITUDE
                }, null, null, BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getTrafficFlows(Context context) {
        return context.getContentResolver().query(BucksProvider.TRAFFIC_FLOW_URI,
                new String[]{
                        BucksContract.TrafficFlow._ID,
                        BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE,
                        BucksContract.TrafficFlow.COLUMN_VEHICLE_FLOW,
                        BucksContract.TrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED,
                        BucksContract.TrafficFlow.COLUMN_TRAVEL_TIME,
                        BucksContract.TrafficFlow.COLUMN_FREE_FLOW_SPEED,
                        BucksContract.TrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME
                }, null, null, BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE);
    }

    public static Cursor getVariableMessageSigns(Context context) {
        return context.getContentResolver().query(BucksProvider.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{
                        BucksContract.VariableMessageSign._ID,
                        BucksContract.VariableMessageSign.COLUMN_LOCATION_REFERENCE,
                        BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS,
                        BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_ROWS,
                        BucksContract.VariableMessageSign.COLUMN_VMS_LEGENDS,
                        BucksContract.VariableMessageSign.COLUMN_VMS_TYPE
                }, null, null, BucksContract.VariableMessageSign.COLUMN_LOCATION_REFERENCE);
    }

    public static Cursor getVmsJoinLocations(Context context) {
        return context.getContentResolver().query(BucksProvider.VMS_JOIN_LOCATION_URI,
                new String[]{
                        BucksContract.VmsJoinLocation.COLUMN_NUMBER_OF_CHARACTERS,
                        BucksContract.VmsJoinLocation.COLUMN_NUMBER_OF_ROWS,
                        BucksContract.VmsJoinLocation.COLUMN_VMS_LEGENDS,
                        BucksContract.VmsJoinLocation.COLUMN_VMS_TYPE,
                        BucksContract.VmsJoinLocation.COLUMN_LATITUDE,
                        BucksContract.VmsJoinLocation.COLUMN_LONGITUDE,
                        BucksContract.VmsJoinLocation.COLUMN_DESCRIPTOR,
                        BucksContract.VmsJoinLocation.COLUMN_TPEG_DIRECTION
                }, null, null, BucksContract.VmsJoinLocation.COLUMN_DESCRIPTOR);
    }

    public static Cursor getTrafficFlowJoinLocations(Context context) {
        return context.getContentResolver().query(BucksProvider.TRAFFIC_FLOW_JOIN_LOCATION_URI,
                new String[]{
                        BucksContract.TrafficFlowJoinLocation.COLUMN_VEHICLE_FLOW,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_AVERAGE_VEHICLE_SPEED,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_TRAVEL_TIME,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_FREE_FLOW_SPEED,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_FREE_FLOW_TRAVEL_TIME,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_FROM_LATITUDE,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_FROM_LONGITUDE,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_TO_LATITUDE,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_TO_LONGITUDE,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_FROM_DESCRIPTOR,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_TO_DESCRIPTOR,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_TPEG_DIRECTION
                }, null, null, BucksContract.TrafficFlowJoinLocation.COLUMN_TO_DESCRIPTOR);
    }

    public static void deleteFromProvider(Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_CAR_PARK:
                contentResolver.delete(BucksProvider.CAR_PARK_URI, null, null);
                break;
            case DATA_TYPE_LINK_LOCATION:
                contentResolver.delete(BucksProvider.SEGMENT_LOCATION_URI,
                        BucksContract.SegmentLocation.COLUMN_LOCATION_ID + " like 'LINKBUCK-%'", null);
                break;
            case DATA_TYPE_SECTION_LOCATION:
                contentResolver.delete(BucksProvider.SEGMENT_LOCATION_URI,
                        BucksContract.SegmentLocation.COLUMN_LOCATION_ID + " like 'SECTION%'", null);
                break;
            case DATA_TYPE_TR_LOCATION:
                contentResolver.delete(BucksProvider.SEGMENT_LOCATION_URI,
                        BucksContract.SegmentLocation.COLUMN_LOCATION_ID + " like 'TRBUCK-%'", null);
                break;
            case DATA_TYPE_VMS_LOCATION:
                contentResolver.delete(BucksProvider.VMS_LOCATION_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(BucksProvider.TRAFFIC_FLOW_URI, null, null);
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(BucksProvider.VARIABLE_MESSAGE_SIGN_URI, null, null);
                break;
        }
    }

}
