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
import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocation;
import net.uk.onetransport.android.county.bucks.locations.SegmentLocation;
import net.uk.onetransport.android.county.bucks.roadworks.Period;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorks;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class BucksContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_SEGMENT_LOCATION, DATA_TYPE_VMS_LOCATION,
            DATA_TYPE_CAR_PARK, DATA_TYPE_VMS, DATA_TYPE_TRAFFIC_FLOW,
            DATA_TYPE_ROAD_WORKS})
    public @interface DataType {
    }

    public static final int DATA_TYPE_SEGMENT_LOCATION = 1;
    public static final int DATA_TYPE_VMS_LOCATION = 2;
    public static final int DATA_TYPE_CAR_PARK = 3;
    public static final int DATA_TYPE_TRAFFIC_FLOW = 4;
    public static final int DATA_TYPE_VMS = 5;
    public static final int DATA_TYPE_ROAD_WORKS = 6;

    // Could really use the proper column identifiers here.
    private static final String LAT_LON_BOX = "latitude >= ? and longitude >= ? "
            + "and latitude <= ? and longitude <= ?";
    private static final String LAT_LON_TO_FROM_BOX = "to_latitude >= ? and to_longitude >= ? "
            + "and to_latitude <= ? and to_longitude <= ? "
            + "and from_latitude >= ? and from_longitude >= ? "
            + "and from_latitude <= ? and from_longitude <= ?";

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull SegmentLocation[] segmentLocations)
            throws RemoteException, OperationApplicationException {
        if (segmentLocations.length > 0) {
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

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull PredefinedVmsLocation[] predefinedVmsLocations)
            throws RemoteException, OperationApplicationException {
        if (predefinedVmsLocations.length > 0) {
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

    public static void insertIntoProvider(@NonNull Context context, @NonNull CarPark[] carParks)
            throws RemoteException, OperationApplicationException {
        if (carParks.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (CarPark carPark : carParks) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProvider.CAR_PARK_URI)
                        .withValue(BucksContract.CarPark.COLUMN_FILL_RATE, carPark.getFillRate())
                        .withValue(BucksContract.CarPark.COLUMN_EXIT_RATE, carPark.getExitRate())
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
    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficFlow[] trafficFlows)
            throws RemoteException, OperationApplicationException {
        if (trafficFlows.length > 0) {
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
                        .withValue(BucksContract.TrafficFlow.COLUMN_CONGESTION_PERCENT,
                                trafficFlow.getCongestionPercent())
                        .withValue(BucksContract.TrafficFlow.COLUMN_CURRENT_FLOW,
                                trafficFlow.getCurrentFlow())
                        .withValue(BucksContract.TrafficFlow.COLUMN_AVERAGE_SPEED,
                                trafficFlow.getAverageSpeed())
                        .withValue(BucksContract.TrafficFlow.COLUMN_LINK_STATUS,
                                trafficFlow.getLinkStatus())
                        .withValue(BucksContract.TrafficFlow.COLUMN_LINK_STATUS_TYPE,
                                trafficFlow.getLinkStatusType())
                        .withValue(BucksContract.TrafficFlow.COLUMN_LINK_TRAVEL_TIME,
                                trafficFlow.getLinkTravelTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProvider.AUTHORITY, operationList);
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
                        .newInsert(BucksProvider.ROAD_WORKS_URI)
                        .withValue(BucksContract.RoadWorks.COLUMN_ID, roadWork.getId())
                        .withValue(BucksContract.RoadWorks.COLUMN_COMMENT, roadWork.getComment())
                        .withValue(BucksContract.RoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT,
                                roadWork.getEffectOnRoadLayout())
                        .withValue(BucksContract.RoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE,
                                roadWork.getRoadMaintenanceType())
                        .withValue(BucksContract.RoadWorks.COLUMN_IMPACT_ON_TRAFFIC,
                                roadWork.getImpactOnTraffic())
                        .withValue(BucksContract.RoadWorks.COLUMN_TYPE, roadWork.getType())
                        .withValue(BucksContract.RoadWorks.COLUMN_STATUS, roadWork.getValidity().getStatus())
                        .withValue(BucksContract.RoadWorks.COLUMN_OVERALL_START_TIME,
                                roadWork.getValidity().getOverallStartTime())
                        .withValue(BucksContract.RoadWorks.COLUMN_OVERALL_END_TIME,
                                roadWork.getValidity().getOverallEndTime())
                        .withValue(BucksContract.RoadWorks.COLUMN_PERIODS, builder.toString())
                        .withValue(BucksContract.RoadWorks.COLUMN_LOCATION_DESCRIPTION,
                                roadWork.getLocation().getDescription())
                        .withValue(BucksContract.RoadWorks.COLUMN_LATITUDE,
                                roadWork.getLocation().getLatitude())
                        .withValue(BucksContract.RoadWorks.COLUMN_LONGITUDE,
                                roadWork.getLocation().getLongitude())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProvider.AUTHORITY, operationList);
        }
    }

    public static Cursor getSegmentLocations(@NonNull Context context) {
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

    public static Cursor getVmsLocations(@NonNull Context context) {
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

    public static Cursor getCarParks(@NonNull Context context) {
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
                        BucksContract.CarPark.COLUMN_LATITUDE,
                        BucksContract.CarPark.COLUMN_LONGITUDE
                }, null, null, BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getCarParks(@NonNull Context context, double minLatitude, double minLongitude,
                                     double maxLatitude, double maxLongitude) {
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
                        BucksContract.CarPark.COLUMN_LATITUDE,
                        BucksContract.CarPark.COLUMN_LONGITUDE
                }, LAT_LON_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getTrafficFlows(@NonNull Context context) {
        return context.getContentResolver().query(BucksProvider.TRAFFIC_FLOW_URI,
                new String[]{
                        BucksContract.TrafficFlow._ID,
                        BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE,
                        BucksContract.TrafficFlow.COLUMN_VEHICLE_FLOW,
                        BucksContract.TrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED,
                        BucksContract.TrafficFlow.COLUMN_TRAVEL_TIME,
                        BucksContract.TrafficFlow.COLUMN_FREE_FLOW_SPEED,
                        BucksContract.TrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME,
                        BucksContract.TrafficFlow.COLUMN_CONGESTION_PERCENT,
                        BucksContract.TrafficFlow.COLUMN_CURRENT_FLOW,
                        BucksContract.TrafficFlow.COLUMN_AVERAGE_SPEED,
                        BucksContract.TrafficFlow.COLUMN_LINK_STATUS,
                        BucksContract.TrafficFlow.COLUMN_LINK_STATUS_TYPE,
                        BucksContract.TrafficFlow.COLUMN_LINK_TRAVEL_TIME
                }, null, null, BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE);
    }

    public static Cursor getVariableMessageSigns(@NonNull Context context) {
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

    public static Cursor getRoadWorks(@NonNull Context context) {
        return context.getContentResolver().query(BucksProvider.ROAD_WORKS_URI,
                new String[]{
                        BucksContract.RoadWorks.COLUMN_ID,
                        BucksContract.RoadWorks.COLUMN_COMMENT,
                        BucksContract.RoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT,
                        BucksContract.RoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE,
                        BucksContract.RoadWorks.COLUMN_IMPACT_ON_TRAFFIC,
                        BucksContract.RoadWorks.COLUMN_TYPE,
                        BucksContract.RoadWorks.COLUMN_STATUS,
                        BucksContract.RoadWorks.COLUMN_OVERALL_START_TIME,
                        BucksContract.RoadWorks.COLUMN_OVERALL_END_TIME,
                        BucksContract.RoadWorks.COLUMN_PERIODS,
                        BucksContract.RoadWorks.COLUMN_LOCATION_DESCRIPTION,
                        BucksContract.RoadWorks.COLUMN_LATITUDE,
                        BucksContract.RoadWorks.COLUMN_LONGITUDE
                }, null, null, BucksContract.RoadWorks.COLUMN_ID);
    }

    public static Cursor getRoadWorks(@NonNull Context context, double minLatitude,
                                      double minLongitude, double maxLatitude, double maxLongitude) {
        return context.getContentResolver().query(BucksProvider.ROAD_WORKS_URI,
                new String[]{
                        BucksContract.RoadWorks.COLUMN_ID,
                        BucksContract.RoadWorks.COLUMN_COMMENT,
                        BucksContract.RoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT,
                        BucksContract.RoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE,
                        BucksContract.RoadWorks.COLUMN_IMPACT_ON_TRAFFIC,
                        BucksContract.RoadWorks.COLUMN_TYPE,
                        BucksContract.RoadWorks.COLUMN_STATUS,
                        BucksContract.RoadWorks.COLUMN_OVERALL_START_TIME,
                        BucksContract.RoadWorks.COLUMN_OVERALL_END_TIME,
                        BucksContract.RoadWorks.COLUMN_PERIODS,
                        BucksContract.RoadWorks.COLUMN_LOCATION_DESCRIPTION,
                        BucksContract.RoadWorks.COLUMN_LATITUDE,
                        BucksContract.RoadWorks.COLUMN_LONGITUDE
                }, LAT_LON_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksContract.RoadWorks.COLUMN_ID);
    }

    public static Cursor getVmsJoinLocations(@NonNull Context context) {
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

    public static Cursor getVmsJoinLocations(@NonNull Context context, double minLatitude,
                                             double minLongitude, double maxLatitude, double maxLongitude) {
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
                }, LAT_LON_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksContract.VmsJoinLocation.COLUMN_DESCRIPTOR);
    }

    public static Cursor getTrafficFlowJoinLocations(@NonNull Context context) {
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
                        BucksContract.TrafficFlowJoinLocation.COLUMN_TPEG_DIRECTION,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_CONGESTION_PERCENT,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_CURRENT_FLOW,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_AVERAGE_SPEED,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_LINK_STATUS,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_LINK_STATUS_TYPE,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_LINK_TRAVEL_TIME
                }, null, null, BucksContract.TrafficFlowJoinLocation.COLUMN_TO_DESCRIPTOR);
    }

    public static Cursor getTrafficFlowJoinLocations(@NonNull Context context, double minLatitude,
                                                     double minLongitude, double maxLatitude, double maxLongitude) {
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
                        BucksContract.TrafficFlowJoinLocation.COLUMN_TPEG_DIRECTION,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_CONGESTION_PERCENT,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_CURRENT_FLOW,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_AVERAGE_SPEED,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_LINK_STATUS,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_LINK_STATUS_TYPE,
                        BucksContract.TrafficFlowJoinLocation.COLUMN_LINK_TRAVEL_TIME
                }, LAT_LON_TO_FROM_BOX, new String[]{
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude),
                        String.valueOf(minLatitude),
                        String.valueOf(minLongitude),
                        String.valueOf(maxLatitude),
                        String.valueOf(maxLongitude)
                }, BucksContract.TrafficFlowJoinLocation.COLUMN_TO_DESCRIPTOR);
    }

    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_CAR_PARK:
                contentResolver.delete(BucksProvider.CAR_PARK_URI, null, null);
                break;
            case DATA_TYPE_SEGMENT_LOCATION:
                contentResolver.delete(BucksProvider.SEGMENT_LOCATION_URI, null, null);
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
            case DATA_TYPE_ROAD_WORKS:
                contentResolver.delete(BucksProvider.ROAD_WORKS_URI, null, null);
                break;
        }
    }
}