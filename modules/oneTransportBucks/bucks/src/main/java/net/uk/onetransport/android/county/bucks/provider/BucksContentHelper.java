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
import net.uk.onetransport.android.county.bucks.roadworks.Location;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorks;
import net.uk.onetransport.android.county.bucks.roadworks.Validity;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.bucks.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;
import net.uk.onetransport.android.modules.common.provider.CommonContentHelper;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedContract;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksCarPark;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksRoadWorks;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficFlow;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficQueue;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficScoot;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficSpeed;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksTrafficTravelTime;
import static net.uk.onetransport.android.county.bucks.provider.BucksContract.BucksVariableMessageSign;

public class BucksContentHelper extends CommonContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_CAR_PARK,
            DATA_TYPE_ROAD_WORKS,
            DATA_TYPE_TRAFFIC_FLOW,
            DATA_TYPE_TRAFFIC_QUEUE,
            DATA_TYPE_TRAFFIC_SCOOT,
            DATA_TYPE_TRAFFIC_SPEED,
            DATA_TYPE_TRAFFIC_TRAVEL_TIME,
            DATA_TYPE_VMS})
    public @interface DataType {
    }

    public static final int DATA_TYPE_CAR_PARK = 1;
    public static final int DATA_TYPE_ROAD_WORKS = 2;
    public static final int DATA_TYPE_TRAFFIC_FLOW = 3;
    public static final int DATA_TYPE_TRAFFIC_QUEUE = 4;
    public static final int DATA_TYPE_TRAFFIC_SCOOT = 5;
    public static final int DATA_TYPE_TRAFFIC_SPEED = 6;
    public static final int DATA_TYPE_TRAFFIC_TRAVEL_TIME = 7;
    public static final int DATA_TYPE_VMS = 8;

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

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull RoadWorks[] roadWorkses)
            throws RemoteException, OperationApplicationException {
        if (roadWorkses.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (RoadWorks roadWorks : roadWorkses) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.ROAD_WORKS_URI)
                        .withValue(BucksRoadWorks.COLUMN_ID, roadWorks.getId())
                        .withValue(BucksRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT,
                                roadWorks.getEffectOnRoadLayout())
                        .withValue(BucksRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE,
                                roadWorks.getRoadMaintenanceType())
                        .withValue(BucksRoadWorks.COLUMN_COMMENT, roadWorks.getComment())
                        .withValue(BucksRoadWorks.COLUMN_IMPACT_ON_TRAFFIC,
                                roadWorks.getImpactOnTraffic())
                        .withValue(BucksRoadWorks.COLUMN_LATITUDE,
                                roadWorks.getLocation().getLatitude())
                        .withValue(BucksRoadWorks.COLUMN_LONGITUDE,
                                roadWorks.getLocation().getLongitude())
                        .withValue(BucksRoadWorks.COLUMN_STATUS, roadWorks.getValidity().getStatus())
                        .withValue(BucksRoadWorks.COLUMN_OVERALL_START_TIME,
                                roadWorks.getValidity().getOverallStartTime())
                        .withValue(BucksRoadWorks.COLUMN_OVERALL_END_TIME,
                                roadWorks.getValidity().getOverallEndTime())
                        .withValue(BucksRoadWorks.COLUMN_PERIODS,
                                roadWorks.getValidity().getPeriodsAsString())
                        .withValue(BucksRoadWorks.COLUMN_CIN_ID, roadWorks.getCinId())
                        .withValue(BucksRoadWorks.COLUMN_CREATION_TIME, roadWorks.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficFlow[] trafficFlows)
            throws RemoteException, OperationApplicationException {
        if (trafficFlows.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficFlow trafficFlow : trafficFlows) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.TRAFFIC_FLOW_URI)
                        .withValue(BucksTrafficFlow.COLUMN_ID, trafficFlow.getId())
                        .withValue(BucksTrafficFlow.COLUMN_TPEG_DIRECTION, trafficFlow.getTpegDirection())
                        .withValue(BucksTrafficFlow.COLUMN_FROM_TYPE, trafficFlow.getFromType())
                        .withValue(BucksTrafficFlow.COLUMN_FROM_DESCRIPTOR,
                                trafficFlow.getFromDescriptor())
                        .withValue(BucksTrafficFlow.COLUMN_FROM_LATITUDE, trafficFlow.getFromLatitude())
                        .withValue(BucksTrafficFlow.COLUMN_FROM_LONGITUDE,
                                trafficFlow.getFromLongitude())
                        .withValue(BucksTrafficFlow.COLUMN_TO_TYPE, trafficFlow.getToType())
                        .withValue(BucksTrafficFlow.COLUMN_TO_DESCRIPTOR, trafficFlow.getToDescriptor())
                        .withValue(BucksTrafficFlow.COLUMN_TO_LATITUDE, trafficFlow.getToLatitude())
                        .withValue(BucksTrafficFlow.COLUMN_TO_LONGITUDE, trafficFlow.getToLongitude())
                        .withValue(BucksTrafficFlow.COLUMN_TIME, trafficFlow.getTime())
                        .withValue(BucksTrafficFlow.COLUMN_VEHICLE_FLOW, trafficFlow.getVehicleFlow())
                        .withValue(BucksTrafficFlow.COLUMN_CIN_ID, trafficFlow.getCinId())
                        .withValue(BucksTrafficFlow.COLUMN_CREATION_TIME, trafficFlow.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficQueue[] trafficQueues)
            throws RemoteException, OperationApplicationException {
        if (trafficQueues.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficQueue trafficQueue : trafficQueues) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.TRAFFIC_QUEUE_URI)
                        .withValue(BucksTrafficQueue.COLUMN_ID, trafficQueue.getId())
                        .withValue(BucksTrafficQueue.COLUMN_TPEG_DIRECTION, trafficQueue.getTpegDirection())
                        .withValue(BucksTrafficQueue.COLUMN_FROM_TYPE, trafficQueue.getFromType())
                        .withValue(BucksTrafficQueue.COLUMN_FROM_DESCRIPTOR,
                                trafficQueue.getFromDescriptor())
                        .withValue(BucksTrafficQueue.COLUMN_FROM_LATITUDE, trafficQueue.getFromLatitude())
                        .withValue(BucksTrafficQueue.COLUMN_FROM_LONGITUDE,
                                trafficQueue.getFromLongitude())
                        .withValue(BucksTrafficQueue.COLUMN_TO_TYPE, trafficQueue.getToType())
                        .withValue(BucksTrafficQueue.COLUMN_TO_DESCRIPTOR, trafficQueue.getToDescriptor())
                        .withValue(BucksTrafficQueue.COLUMN_TO_LATITUDE, trafficQueue.getToLatitude())
                        .withValue(BucksTrafficQueue.COLUMN_TO_LONGITUDE, trafficQueue.getToLongitude())
                        .withValue(BucksTrafficQueue.COLUMN_TIME, trafficQueue.getTime())
                        .withValue(BucksTrafficQueue.COLUMN_SEVERITY, trafficQueue.getSeverity())
                        .withValue(BucksTrafficQueue.COLUMN_PRESENT, trafficQueue.getPresent())
                        .withValue(BucksTrafficQueue.COLUMN_CIN_ID, trafficQueue.getCinId())
                        .withValue(BucksTrafficQueue.COLUMN_CREATION_TIME, trafficQueue.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficScoot[] trafficScoots)
            throws RemoteException, OperationApplicationException {
        if (trafficScoots.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficScoot trafficScoot : trafficScoots) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.TRAFFIC_SCOOT_URI)
                        .withValue(BucksTrafficScoot.COLUMN_ID, trafficScoot.getId())
                        .withValue(BucksTrafficScoot.COLUMN_TPEG_DIRECTION, trafficScoot.getTpegDirection())
                        .withValue(BucksTrafficScoot.COLUMN_FROM_TYPE, trafficScoot.getFromType())
                        .withValue(BucksTrafficScoot.COLUMN_FROM_DESCRIPTOR,
                                trafficScoot.getFromDescriptor())
                        .withValue(BucksTrafficScoot.COLUMN_FROM_LATITUDE, trafficScoot.getFromLatitude())
                        .withValue(BucksTrafficScoot.COLUMN_FROM_LONGITUDE,
                                trafficScoot.getFromLongitude())
                        .withValue(BucksTrafficScoot.COLUMN_TO_TYPE, trafficScoot.getToType())
                        .withValue(BucksTrafficScoot.COLUMN_TO_DESCRIPTOR, trafficScoot.getToDescriptor())
                        .withValue(BucksTrafficScoot.COLUMN_TO_LATITUDE, trafficScoot.getToLatitude())
                        .withValue(BucksTrafficScoot.COLUMN_TO_LONGITUDE, trafficScoot.getToLongitude())
                        .withValue(BucksTrafficScoot.COLUMN_TIME, trafficScoot.getTime())
                        .withValue(BucksTrafficScoot.COLUMN_CURRENT_FLOW, trafficScoot.getCurrentFlow())
                        .withValue(BucksTrafficScoot.COLUMN_AVERAGE_SPEED, trafficScoot.getAverageSpeed())
                        .withValue(BucksTrafficScoot.COLUMN_LINK_STATUS_TYPE,
                                trafficScoot.getLinkStatusType())
                        .withValue(BucksTrafficScoot.COLUMN_LINK_STATUS, trafficScoot.getLinkStatus())
                        .withValue(BucksTrafficScoot.COLUMN_LINK_TRAVEL_TIME,
                                trafficScoot.getLinkTravelTime())
                        .withValue(BucksTrafficScoot.COLUMN_CONGESTION_PERCENT,
                                trafficScoot.getCongestionPercent())
                        .withValue(BucksTrafficScoot.COLUMN_CIN_ID, trafficScoot.getCinId())
                        .withValue(BucksTrafficScoot.COLUMN_CREATION_TIME, trafficScoot.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficSpeed[] trafficSpeeds)
            throws RemoteException, OperationApplicationException {
        if (trafficSpeeds.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficSpeed trafficSpeed : trafficSpeeds) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.TRAFFIC_SPEED_URI)
                        .withValue(BucksTrafficSpeed.COLUMN_ID, trafficSpeed.getId())
                        .withValue(BucksTrafficSpeed.COLUMN_TPEG_DIRECTION, trafficSpeed.getTpegDirection())
                        .withValue(BucksTrafficSpeed.COLUMN_FROM_TYPE, trafficSpeed.getFromType())
                        .withValue(BucksTrafficSpeed.COLUMN_FROM_DESCRIPTOR,
                                trafficSpeed.getFromDescriptor())
                        .withValue(BucksTrafficSpeed.COLUMN_FROM_LATITUDE, trafficSpeed.getFromLatitude())
                        .withValue(BucksTrafficSpeed.COLUMN_FROM_LONGITUDE,
                                trafficSpeed.getFromLongitude())
                        .withValue(BucksTrafficSpeed.COLUMN_TO_TYPE, trafficSpeed.getToType())
                        .withValue(BucksTrafficSpeed.COLUMN_TO_DESCRIPTOR, trafficSpeed.getToDescriptor())
                        .withValue(BucksTrafficSpeed.COLUMN_TO_LATITUDE, trafficSpeed.getToLatitude())
                        .withValue(BucksTrafficSpeed.COLUMN_TO_LONGITUDE, trafficSpeed.getToLongitude())
                        .withValue(BucksTrafficSpeed.COLUMN_TIME, trafficSpeed.getTime())
                        .withValue(BucksTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED,
                                trafficSpeed.getAverageVehicleSpeed())
                        .withValue(BucksTrafficSpeed.COLUMN_CIN_ID, trafficSpeed.getCinId())
                        .withValue(BucksTrafficSpeed.COLUMN_CREATION_TIME, trafficSpeed.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull TrafficTravelTime[] trafficTravelTimes)
            throws RemoteException, OperationApplicationException {
        if (trafficTravelTimes.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficTravelTime trafficTravelTime : trafficTravelTimes) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.TRAFFIC_TRAVEL_TIME_URI)
                        .withValue(BucksTrafficTravelTime.COLUMN_ID, trafficTravelTime.getId())
                        .withValue(BucksTrafficTravelTime.COLUMN_TPEG_DIRECTION,
                                trafficTravelTime.getTpegDirection())
                        .withValue(BucksTrafficTravelTime.COLUMN_FROM_TYPE,
                                trafficTravelTime.getFromType())
                        .withValue(BucksTrafficTravelTime.COLUMN_FROM_DESCRIPTOR,
                                trafficTravelTime.getFromDescriptor())
                        .withValue(BucksTrafficTravelTime.COLUMN_FROM_LATITUDE,
                                trafficTravelTime.getFromLatitude())
                        .withValue(BucksTrafficTravelTime.COLUMN_FROM_LONGITUDE,
                                trafficTravelTime.getFromLongitude())
                        .withValue(BucksTrafficTravelTime.COLUMN_TO_TYPE, trafficTravelTime.getToType())
                        .withValue(BucksTrafficTravelTime.COLUMN_TO_DESCRIPTOR,
                                trafficTravelTime.getToDescriptor())
                        .withValue(BucksTrafficTravelTime.COLUMN_TO_LATITUDE,
                                trafficTravelTime.getToLatitude())
                        .withValue(BucksTrafficTravelTime.COLUMN_TO_LONGITUDE,
                                trafficTravelTime.getToLongitude())
                        .withValue(BucksTrafficTravelTime.COLUMN_TIME, trafficTravelTime.getTime())
                        .withValue(BucksTrafficTravelTime.COLUMN_TRAVEL_TIME,
                                trafficTravelTime.getTravelTime())
                        .withValue(BucksTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME,
                                trafficTravelTime.getFreeFlowTravelTime())
                        .withValue(BucksTrafficTravelTime.COLUMN_FREE_FLOW_SPEED,
                                trafficTravelTime.getFreeFlowSpeed())
                        .withValue(BucksTrafficTravelTime.COLUMN_CIN_ID, trafficTravelTime.getCinId())
                        .withValue(BucksTrafficTravelTime.COLUMN_CREATION_TIME,
                                trafficTravelTime.getCreationTime())
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
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (VariableMessageSign variableMessageSign : variableMessageSigns) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI)
                        .withValue(BucksVariableMessageSign.COLUMN_LOCATION_ID,
                                variableMessageSign.getLocationId())
                        .withValue(BucksVariableMessageSign.COLUMN_DESCRIPTION,
                                variableMessageSign.getDescription())
                        .withValue(BucksVariableMessageSign.COLUMN_VMS_TYPE,
                                variableMessageSign.getVmsType())
                        .withValue(BucksVariableMessageSign.COLUMN_LATITUDE,
                                variableMessageSign.getLatitude())
                        .withValue(BucksVariableMessageSign.COLUMN_LONGITUDE,
                                variableMessageSign.getLongitude())
                        .withValue(BucksVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS,
                                variableMessageSign.getNumberOfCharacters())
                        .withValue(BucksVariableMessageSign.COLUMN_NUMBER_OF_ROWS,
                                variableMessageSign.getNumberOfRows())
                        .withValue(BucksVariableMessageSign.COLUMN_VMS_LEGENDS,
                                variableMessageSign.getLegendsAsString())
                        .withValue(BucksVariableMessageSign.COLUMN_CIN_ID, variableMessageSign.getCinId())
                        .withValue(BucksVariableMessageSign.COLUMN_CREATION_TIME,
                                variableMessageSign.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(BucksProviderModule.AUTHORITY, operationList);
        }
    }

    public static Cursor getCarParkCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.CAR_PARK_URI,
                new String[]{"*"}, null, null, BucksCarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getCarParkCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.CAR_PARK_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestCarParkCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.LATEST_CAR_PARK_URI,
                new String[]{"*"}, null, null, BucksCarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static CarPark[] getCarParks(@NonNull Context context) {
        return carParksFromCursor(getCarParkCursor(context));
    }

    public static CarPark[] getCarParks(@NonNull Context context, long oldest, long newest) {
        return carParksFromCursor(getCarParkCursor(context, oldest, newest));
    }

    public static CarPark[] getLatestCarParks(@NonNull Context context) {
        return carParksFromCursor(getLatestCarParkCursor(context));
    }

    public static Cursor getRoadWorksCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.ROAD_WORKS_URI,
                new String[]{"*"}, null, null, BucksRoadWorks.COLUMN_ID);
    }

    public static Cursor getRoadWorksCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.ROAD_WORKS_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestRoadWorksCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.LATEST_ROAD_WORKS_URI,
                new String[]{"*"}, null, null, BucksRoadWorks.COLUMN_ID);
    }

    public static RoadWorks[] getRoadWorks(@NonNull Context context) {
        return roadWorksesFromCursor(getRoadWorksCursor(context));
    }

    public static RoadWorks[] getRoadWorks(@NonNull Context context, long oldest, long newest) {
        return roadWorksesFromCursor(getRoadWorksCursor(context, oldest, newest));
    }

    public static RoadWorks[] getLatestRoadWorks(@NonNull Context context) {
        return roadWorksesFromCursor(getLatestRoadWorksCursor(context));
    }

    public static Cursor getTrafficFlowCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, null, null, BucksTrafficFlow.COLUMN_ID);
    }

    public static Cursor getTrafficFlowCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficFlowCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.LATEST_TRAFFIC_FLOW_URI,
                new String[]{"*"}, null, null, BucksTrafficFlow.COLUMN_ID);
    }

    public static TrafficFlow[] getTrafficFlows(@NonNull Context context) {
        return trafficFlowsFromCursor(getTrafficFlowCursor(context));
    }

    public static TrafficFlow[] getTrafficFlows(@NonNull Context context, long oldest, long newest) {
        return trafficFlowsFromCursor(getTrafficFlowCursor(context, oldest, newest));
    }

    public static TrafficFlow[] getLatestTrafficFlows(@NonNull Context context) {
        return trafficFlowsFromCursor(getLatestTrafficFlowCursor(context));
    }

    public static Cursor getTrafficQueueCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_QUEUE_URI,
                new String[]{"*"}, null, null, BucksTrafficQueue.COLUMN_ID);
    }

    public static Cursor getTrafficQueueCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_QUEUE_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficQueueCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.LATEST_TRAFFIC_QUEUE_URI,
                new String[]{"*"}, null, null, BucksTrafficQueue.COLUMN_ID);
    }

    public static TrafficQueue[] getTrafficQueues(@NonNull Context context) {
        return trafficQueuesFromCursor(getTrafficQueueCursor(context));
    }

    public static TrafficQueue[] getTrafficQueues(@NonNull Context context, long oldest, long newest) {
        return trafficQueuesFromCursor(getTrafficQueueCursor(context, oldest, newest));
    }

    public static TrafficQueue[] getLatestTrafficQueues(@NonNull Context context) {
        return trafficQueuesFromCursor(getLatestTrafficQueueCursor(context));
    }

    public static Cursor getTrafficScootCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_SCOOT_URI,
                new String[]{"*"}, null, null, BucksTrafficScoot.COLUMN_ID);
    }

    public static Cursor getTrafficScootCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_SCOOT_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficScootCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.LATEST_TRAFFIC_SCOOT_URI,
                new String[]{"*"}, null, null, BucksTrafficScoot.COLUMN_ID);
    }

    public static TrafficScoot[] getTrafficScoots(@NonNull Context context) {
        return trafficScootsFromCursor(getTrafficScootCursor(context));
    }

    public static TrafficScoot[] getTrafficScoots(@NonNull Context context, long oldest, long newest) {
        return trafficScootsFromCursor(getTrafficScootCursor(context, oldest, newest));
    }

    public static TrafficScoot[] getLatestTrafficScoots(@NonNull Context context) {
        return trafficScootsFromCursor(getLatestTrafficScootCursor(context));
    }

    public static Cursor getTrafficSpeedCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_SPEED_URI,
                new String[]{"*"}, null, null, BucksTrafficSpeed.COLUMN_ID);
    }

    public static Cursor getTrafficSpeedCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_SPEED_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficSpeedCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.LATEST_TRAFFIC_SPEED_URI,
                new String[]{"*"}, null, null, BucksTrafficSpeed.COLUMN_ID);
    }

    public static TrafficSpeed[] getTrafficSpeeds(@NonNull Context context) {
        return trafficSpeedsFromCursor(getTrafficSpeedCursor(context));
    }

    public static TrafficSpeed[] getTrafficSpeeds(@NonNull Context context, long oldest, long newest) {
        return trafficSpeedsFromCursor(getTrafficSpeedCursor(context, oldest, newest));
    }

    public static TrafficSpeed[] getLatestTrafficSpeeds(@NonNull Context context) {
        return trafficSpeedsFromCursor(getLatestTrafficSpeedCursor(context));
    }

    public static Cursor getTrafficTravelTimeCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                new String[]{"*"}, null, null, BucksTrafficTravelTime.COLUMN_ID);
    }

    public static Cursor getTrafficTravelTimeCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficTravelTimeCursor(@NonNull Context context) {
        return context.getContentResolver().query(
                BucksProviderModule.LATEST_TRAFFIC_TRAVEL_TIME_URI, new String[]{"*"},
                null, null, BucksTrafficTravelTime.COLUMN_ID);
    }

    public static TrafficTravelTime[] getTrafficTravelTimes(@NonNull Context context) {
        return trafficTravelTimesFromCursor(getTrafficTravelTimeCursor(context));
    }

    public static TrafficTravelTime[] getTrafficTravelTimes(@NonNull Context context, long oldest,
                                                            long newest) {
        return trafficTravelTimesFromCursor(getTrafficTravelTimeCursor(context, oldest, newest));
    }

    public static TrafficTravelTime[] getLatestTrafficTravelTimes(@NonNull Context context) {
        return trafficTravelTimesFromCursor(getLatestTrafficTravelTimeCursor(context));
    }

    public static Cursor getVariableMessageSignCursor(@NonNull Context context) {
        return context.getContentResolver().query(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, null, null, BucksVariableMessageSign.COLUMN_LOCATION_ID);
    }

    public static Cursor getVariableMessageSignCursor(@NonNull Context context,
                                                      long oldest, long newest) {
        return context.getContentResolver().query(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestVariableMessageSignCursor(@NonNull Context context) {
        return context.getContentResolver().query(
                BucksProviderModule.LATEST_VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, null, null, BucksVariableMessageSign.COLUMN_LOCATION_ID);
    }

    public static VariableMessageSign[] getVariableMessageSigns(@NonNull Context context) {
        return variableMessageSignsFromCursor(getVariableMessageSignCursor(context));
    }

    public static VariableMessageSign[] getVariableMessageSigns(@NonNull Context context,
                                                                long oldest, long newest) {
        return variableMessageSignsFromCursor(getVariableMessageSignCursor(context, oldest, newest));
    }

    public static VariableMessageSign[] getLatestVariableMessageSigns(@NonNull Context context) {
        return variableMessageSignsFromCursor(getLatestVariableMessageSignCursor(context));
    }

    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_CAR_PARK:
                contentResolver.delete(BucksProviderModule.CAR_PARK_URI, null, null);
                break;
            case DATA_TYPE_ROAD_WORKS:
                contentResolver.delete(BucksProviderModule.ROAD_WORKS_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(BucksProviderModule.TRAFFIC_FLOW_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_QUEUE:
                contentResolver.delete(BucksProviderModule.TRAFFIC_QUEUE_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_SCOOT:
                contentResolver.delete(BucksProviderModule.TRAFFIC_SCOOT_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_SPEED:
                contentResolver.delete(BucksProviderModule.TRAFFIC_SPEED_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_TRAVEL_TIME:
                contentResolver.delete(BucksProviderModule.TRAFFIC_TRAVEL_TIME_URI, null, null);
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI, null, null);
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
            case DATA_TYPE_ROAD_WORKS:
                contentResolver.delete(BucksProviderModule.ROAD_WORKS_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(BucksProviderModule.TRAFFIC_FLOW_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_QUEUE:
                contentResolver.delete(BucksProviderModule.TRAFFIC_QUEUE_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_SCOOT:
                contentResolver.delete(BucksProviderModule.TRAFFIC_SCOOT_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_SPEED:
                contentResolver.delete(BucksProviderModule.TRAFFIC_SPEED_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_TRAVEL_TIME:
                contentResolver.delete(BucksProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                        CREATED_BEFORE, new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(BucksProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                        CREATED_BEFORE, new String[]{String.valueOf(creationTime)});
                break;
        }
    }

    // TODO    Should be in Common?
    public static Cursor getLastUpdated(@NonNull Context context) {
        return context.getContentResolver().query(LastUpdatedProviderModule.LAST_UPDATED_URI,
                new String[]{
                        LastUpdatedContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS
                }, null, null, null);
    }

    public static CarPark[] carParksFromCursor(Cursor cursor) {
        CarPark[] carParks = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                carParks = new CarPark[cursor.getCount()];
                for (int i = 0; i < carParks.length; i++) {
                    carParks[i] = new CarPark();
                    carParks[i].setCarParkIdentity(cursor.getString(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_CAR_PARK_IDENTITY)));
                    carParks[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_LATITUDE)));
                    carParks[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_LONGITUDE)));
                    carParks[i].setOccupancy(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_OCCUPANCY)));
                    carParks[i].setOccupancyTrend(cursor.getString(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_OCCUPANCY_TREND)));
                    carParks[i].setTotalParkingCapacity(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_TOTAL_PARKING_CAPACITY)));
                    carParks[i].setFillRate(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_FILL_RATE)));
                    carParks[i].setExitRate(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_EXIT_RATE)));
                    carParks[i].setAlmostFullIncreasing(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_ALMOST_FULL_INCREASING)));
                    carParks[i].setAlmostFullDecreasing(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_ALMOST_FULL_DECREASING)));
                    carParks[i].setStatus(cursor.getString(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_STATUS)));
                    carParks[i].setStatusTime(cursor.getString(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_STATUS_TIME)));
                    carParks[i].setQueuingTime(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_QUEUING_TIME)));
                    carParks[i].setParkingAreaName(cursor.getString(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_PARKING_AREA_NAME)));
                    carParks[i].setEntranceFull(cursor.getDouble(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_ENTRANCE_FULL)));
                    carParks[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_CIN_ID)));
                    carParks[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksCarPark.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (carParks == null) {
            return new CarPark[0];
        }
        return carParks;
    }

    public static TrafficFlow[] trafficFlowsFromCursor(Cursor cursor) {
        TrafficFlow[] trafficFlows = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficFlows = new TrafficFlow[cursor.getCount()];
                for (int i = 0; i < trafficFlows.length; i++) {
                    trafficFlows[i] = new TrafficFlow();
                    trafficFlows[i].setId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_ID)));
                    trafficFlows[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_TPEG_DIRECTION)));
                    trafficFlows[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_FROM_TYPE)));
                    trafficFlows[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_FROM_DESCRIPTOR)));
                    trafficFlows[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_FROM_LATITUDE)));
                    trafficFlows[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_FROM_LONGITUDE)));
                    trafficFlows[i].setToType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_TO_TYPE)));
                    trafficFlows[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_TO_DESCRIPTOR)));
                    trafficFlows[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_TO_LATITUDE)));
                    trafficFlows[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_TO_LONGITUDE)));
                    trafficFlows[i].setTime(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_TIME)));
                    trafficFlows[i].setVehicleFlow(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_VEHICLE_FLOW)));
                    trafficFlows[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_CIN_ID)));
                    trafficFlows[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksTrafficFlow.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (trafficFlows == null) {
            return new TrafficFlow[0];
        }
        return trafficFlows;
    }

    public static TrafficQueue[] trafficQueuesFromCursor(Cursor cursor) {
        TrafficQueue[] trafficQueues = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficQueues = new TrafficQueue[cursor.getCount()];
                for (int i = 0; i < trafficQueues.length; i++) {
                    trafficQueues[i] = new TrafficQueue();
                    trafficQueues[i].setId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_ID)));
                    trafficQueues[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_TPEG_DIRECTION)));
                    trafficQueues[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_FROM_TYPE)));
                    trafficQueues[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_FROM_DESCRIPTOR)));
                    trafficQueues[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_FROM_LATITUDE)));
                    trafficQueues[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_FROM_LONGITUDE)));
                    trafficQueues[i].setToType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_TO_TYPE)));
                    trafficQueues[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_TO_DESCRIPTOR)));
                    trafficQueues[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_TO_LATITUDE)));
                    trafficQueues[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_TO_LONGITUDE)));
                    trafficQueues[i].setTime(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_TIME)));
                    trafficQueues[i].setSeverity(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_SEVERITY)));
                    trafficQueues[i].setPresent(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_PRESENT)));
                    trafficQueues[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_CIN_ID)));
                    trafficQueues[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksTrafficQueue.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (trafficQueues == null) {
            return new TrafficQueue[0];
        }
        return trafficQueues;
    }

    public static TrafficScoot[] trafficScootsFromCursor(Cursor cursor) {
        TrafficScoot[] trafficScoots = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficScoots = new TrafficScoot[cursor.getCount()];
                for (int i = 0; i < trafficScoots.length; i++) {
                    trafficScoots[i] = new TrafficScoot();
                    trafficScoots[i].setId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_ID)));
                    trafficScoots[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_TPEG_DIRECTION)));
                    trafficScoots[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_FROM_TYPE)));
                    trafficScoots[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_FROM_DESCRIPTOR)));
                    trafficScoots[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_FROM_LATITUDE)));
                    trafficScoots[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_FROM_LONGITUDE)));
                    trafficScoots[i].setToType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_TO_TYPE)));
                    trafficScoots[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_TO_DESCRIPTOR)));
                    trafficScoots[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_TO_LATITUDE)));
                    trafficScoots[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_TO_LONGITUDE)));
                    trafficScoots[i].setTime(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_TIME)));
                    trafficScoots[i].setCurrentFlow(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_CURRENT_FLOW)));
                    trafficScoots[i].setAverageSpeed(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_AVERAGE_SPEED)));
                    trafficScoots[i].setLinkStatusType(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_LINK_STATUS_TYPE)));
                    trafficScoots[i].setLinkStatus(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_LINK_STATUS)));
                    trafficScoots[i].setLinkTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_LINK_TRAVEL_TIME)));
                    trafficScoots[i].setCongestionPercent(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_CONGESTION_PERCENT)));
                    trafficScoots[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_CIN_ID)));
                    trafficScoots[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksTrafficScoot.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (trafficScoots == null) {
            return new TrafficScoot[0];
        }
        return trafficScoots;
    }

    public static TrafficSpeed[] trafficSpeedsFromCursor(Cursor cursor) {
        TrafficSpeed[] trafficSpeeds = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficSpeeds = new TrafficSpeed[cursor.getCount()];
                for (int i = 0; i < trafficSpeeds.length; i++) {
                    trafficSpeeds[i] = new TrafficSpeed();
                    trafficSpeeds[i].setId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_ID)));
                    trafficSpeeds[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_TPEG_DIRECTION)));
                    trafficSpeeds[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_FROM_TYPE)));
                    trafficSpeeds[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_FROM_DESCRIPTOR)));
                    trafficSpeeds[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_FROM_LATITUDE)));
                    trafficSpeeds[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_FROM_LONGITUDE)));
                    trafficSpeeds[i].setToType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_TO_TYPE)));
                    trafficSpeeds[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_TO_DESCRIPTOR)));
                    trafficSpeeds[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_TO_LATITUDE)));
                    trafficSpeeds[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_TO_LONGITUDE)));
                    trafficSpeeds[i].setTime(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_TIME)));
                    trafficSpeeds[i].setAverageVehicleSpeed(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED)));
                    trafficSpeeds[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_CIN_ID)));
                    trafficSpeeds[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksTrafficSpeed.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (trafficSpeeds == null) {
            return new TrafficSpeed[0];
        }
        return trafficSpeeds;
    }

    public static TrafficTravelTime[] trafficTravelTimesFromCursor(Cursor cursor) {
        TrafficTravelTime[] trafficTravelTimes = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficTravelTimes = new TrafficTravelTime[cursor.getCount()];
                for (int i = 0; i < trafficTravelTimes.length; i++) {
                    trafficTravelTimes[i] = new TrafficTravelTime();
                    trafficTravelTimes[i].setId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_ID)));
                    trafficTravelTimes[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_TPEG_DIRECTION)));
                    trafficTravelTimes[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_FROM_TYPE)));
                    trafficTravelTimes[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_FROM_DESCRIPTOR)));
                    trafficTravelTimes[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_FROM_LATITUDE)));
                    trafficTravelTimes[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_FROM_LONGITUDE)));
                    trafficTravelTimes[i].setToType(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_TO_TYPE)));
                    trafficTravelTimes[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_TO_DESCRIPTOR)));
                    trafficTravelTimes[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_TO_LATITUDE)));
                    trafficTravelTimes[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_TO_LONGITUDE)));
                    trafficTravelTimes[i].setTime(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_TIME)));
                    trafficTravelTimes[i].setTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_TRAVEL_TIME)));
                    trafficTravelTimes[i].setFreeFlowTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME)));
                    trafficTravelTimes[i].setFreeFlowSpeed(cursor.getDouble(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_FREE_FLOW_SPEED)));
                    trafficTravelTimes[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_CIN_ID)));
                    trafficTravelTimes[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksTrafficTravelTime.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (trafficTravelTimes == null) {
            return new TrafficTravelTime[0];
        }
        return trafficTravelTimes;
    }

    public static RoadWorks[] roadWorksesFromCursor(Cursor cursor) {
        RoadWorks[] roadWorkses = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                roadWorkses = new RoadWorks[cursor.getCount()];
                for (int i = 0; i < roadWorkses.length; i++) {
                    roadWorkses[i] = new RoadWorks();
                    roadWorkses[i].setId(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_ID)));
                    roadWorkses[i].setEffectOnRoadLayout(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT)));
                    roadWorkses[i].setRoadMaintenanceType(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE)));
                    roadWorkses[i].setComment(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_COMMENT)));
                    roadWorkses[i].setImpactOnTraffic(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_IMPACT_ON_TRAFFIC)));
                    Location location = new Location();
                    location.setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_LATITUDE)));
                    location.setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_LONGITUDE)));
                    roadWorkses[i].setLocation(location);
                    Validity validity = new Validity();
                    validity.setStatus(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_STATUS)));
                    validity.setOverallStartTime(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_OVERALL_START_TIME)));
                    validity.setOverallEndTime(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_OVERALL_END_TIME)));
                    validity.setPeriodsFromString(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_PERIODS)));
                    roadWorkses[i].setValidity(validity);
                    roadWorkses[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_CIN_ID)));
                    roadWorkses[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksRoadWorks.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (roadWorkses == null) {
            return new RoadWorks[0];
        }
        return roadWorkses;
    }

    public static VariableMessageSign[] variableMessageSignsFromCursor(Cursor cursor) {
        VariableMessageSign[] variableMessageSigns = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                variableMessageSigns = new VariableMessageSign[cursor.getCount()];
                for (int i = 0; i < variableMessageSigns.length; i++) {
                    variableMessageSigns[i] = new VariableMessageSign();
                    variableMessageSigns[i].setLocationId(cursor.getString(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_LOCATION_ID)));
                    variableMessageSigns[i].setDescription(cursor.getString(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_DESCRIPTION)));
                    variableMessageSigns[i].setVmsType(cursor.getString(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_VMS_TYPE)));
                    variableMessageSigns[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_LATITUDE)));
                    variableMessageSigns[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_LONGITUDE)));
                    variableMessageSigns[i].setNumberOfCharacters(cursor.getDouble(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS)));
                    variableMessageSigns[i].setNumberOfRows(cursor.getDouble(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_NUMBER_OF_ROWS)));
                    variableMessageSigns[i].setLegendsFromString(cursor.getString(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_VMS_LEGENDS)));
                    variableMessageSigns[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_CIN_ID)));
                    variableMessageSigns[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BucksVariableMessageSign.COLUMN_CREATION_TIME)));
                }
            }
            cursor.close();
        }
        if (variableMessageSigns == null) {
            return new VariableMessageSign[0];
        }
        return variableMessageSigns;
    }
}
