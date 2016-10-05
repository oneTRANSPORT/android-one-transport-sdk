package net.uk.onetransport.android.county.oxon.provider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorks;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;
import net.uk.onetransport.android.modules.common.provider.CommonContentHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonCarPark;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonEvent;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonRoadWorks;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficFlow;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficQueue;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficScoot;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficSpeed;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonTrafficTravelTime;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonVariableMessageSign;

public class OxonContentHelper extends CommonContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_CAR_PARK,
            DATA_TYPE_EVENT,
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
    public static final int DATA_TYPE_EVENT = 2;
    public static final int DATA_TYPE_ROAD_WORKS = 3;
    public static final int DATA_TYPE_TRAFFIC_FLOW = 4;
    public static final int DATA_TYPE_TRAFFIC_QUEUE = 5;
    public static final int DATA_TYPE_TRAFFIC_SCOOT = 6;
    public static final int DATA_TYPE_TRAFFIC_SPEED = 7;
    public static final int DATA_TYPE_TRAFFIC_TRAVEL_TIME = 8;
    public static final int DATA_TYPE_VMS = 9;

    public static void insertIntoProvider(@NonNull Context context, @NonNull CarPark[] carParks)
            throws RemoteException, OperationApplicationException {
        if (carParks.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (CarPark carPark : carParks) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.CAR_PARK_URI)
                        .withValue(OxonCarPark.COLUMN_CAR_PARK_IDENTITY, carPark.getCarParkIdentity())
                        .withValue(OxonCarPark.COLUMN_LATITUDE, carPark.getLatitude())
                        .withValue(OxonCarPark.COLUMN_LONGITUDE, carPark.getLongitude())
                        .withValue(OxonCarPark.COLUMN_OCCUPANCY, carPark.getOccupancy())
                        .withValue(OxonCarPark.COLUMN_OCCUPANCY_TREND, carPark.getOccupancyTrend())
                        .withValue(OxonCarPark.COLUMN_TOTAL_PARKING_CAPACITY,
                                carPark.getTotalParkingCapacity())
                        .withValue(OxonCarPark.COLUMN_FILL_RATE, carPark.getFillRate())
                        .withValue(OxonCarPark.COLUMN_EXIT_RATE, carPark.getExitRate())
                        .withValue(OxonCarPark.COLUMN_ALMOST_FULL_INCREASING,
                                carPark.getAlmostFullIncreasing())
                        .withValue(OxonCarPark.COLUMN_ALMOST_FULL_DECREASING,
                                carPark.getAlmostFullDecreasing())
                        .withValue(OxonCarPark.COLUMN_FULL_INCREASING, carPark.getFullIncreasing())
                        .withValue(OxonCarPark.COLUMN_FULL_DECREASING, carPark.getFullDecreasing())
                        .withValue(OxonCarPark.COLUMN_STATUS, carPark.getStatus())
                        .withValue(OxonCarPark.COLUMN_STATUS_TIME, carPark.getStatusTime())
                        .withValue(OxonCarPark.COLUMN_QUEUING_TIME, carPark.getQueuingTime())
                        .withValue(OxonCarPark.COLUMN_PARKING_AREA_NAME,
                                carPark.getParkingAreaName())
                        .withValue(OxonCarPark.COLUMN_ENTRANCE_FULL, carPark.getEntranceFull())
                        .withValue(OxonCarPark.COLUMN_CIN_ID, carPark.getCinId())
                        .withValue(OxonCarPark.COLUMN_CREATION_TIME, carPark.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull Event[] events)
            throws RemoteException, OperationApplicationException {
        if (events.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Event event : events) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.EVENT_URI)
                        .withValue(OxonEvent.COLUMN_ID, event.getId())
                        .withValue(OxonEvent.COLUMN_START_OF_PERIOD, event.getStartOfPeriod())
                        .withValue(OxonEvent.COLUMN_END_OF_PERIOD, event.getEndOfPeriod())
                        .withValue(OxonEvent.COLUMN_OVERALL_START_TIME, event.getOverallStartTime())
                        .withValue(OxonEvent.COLUMN_OVERALL_END_TIME, event.getOverallEndTime())
                        .withValue(OxonEvent.COLUMN_LATITUDE, event.getLatitude())
                        .withValue(OxonEvent.COLUMN_LONGITUDE, event.getLongitude())
                        .withValue(OxonEvent.COLUMN_DESCRIPTION, event.getDescription())
                        .withValue(OxonEvent.COLUMN_IMPACT_ON_TRAFFIC, event.getImpactOnTraffic())
                        .withValue(OxonEvent.COLUMN_VALIDITY_STATUS, event.getValidityStatus())
                        .withValue(OxonEvent.COLUMN_CIN_ID, event.getCinId())
                        .withValue(OxonEvent.COLUMN_CREATION_TIME, event.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull RoadWorks[] roadWorkses)
            throws RemoteException, OperationApplicationException {
        if (roadWorkses.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (RoadWorks roadWorks : roadWorkses) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.ROAD_WORKS_URI)
                        .withValue(OxonRoadWorks.COLUMN_ID, roadWorks.getId())
                        .withValue(OxonRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT,
                                roadWorks.getEffectOnRoadLayout())
                        .withValue(OxonRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE,
                                roadWorks.getRoadMaintenanceType())
                        .withValue(OxonRoadWorks.COLUMN_COMMENT, roadWorks.getComment())
                        .withValue(OxonRoadWorks.COLUMN_IMPACT_ON_TRAFFIC,
                                roadWorks.getImpactOnTraffic())
                        .withValue(OxonRoadWorks.COLUMN_LATITUDE,
                                roadWorks.getLatitude())
                        .withValue(OxonRoadWorks.COLUMN_LONGITUDE,
                                roadWorks.getLongitude())
                        .withValue(OxonRoadWorks.COLUMN_VALIDITY_STATUS,
                                roadWorks.getValidityStatus())
                        .withValue(OxonRoadWorks.COLUMN_OVERALL_START_TIME,
                                roadWorks.getOverallStartTime())
                        .withValue(OxonRoadWorks.COLUMN_OVERALL_END_TIME,
                                roadWorks.getOverallEndTime())
                        .withValue(OxonRoadWorks.COLUMN_START_OF_PERIOD,
                                roadWorks.getStartOfPeriod())
                        .withValue(OxonRoadWorks.COLUMN_END_OF_PERIOD,
                                roadWorks.getEndOfPeriod())
                        .withValue(OxonRoadWorks.COLUMN_CIN_ID, roadWorks.getCinId())
                        .withValue(OxonRoadWorks.COLUMN_CREATION_TIME, roadWorks.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficFlow[] trafficFlows)
            throws RemoteException, OperationApplicationException {
        if (trafficFlows.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficFlow trafficFlow : trafficFlows) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.TRAFFIC_FLOW_URI)
                        .withValue(OxonTrafficFlow.COLUMN_ID, trafficFlow.getId())
                        .withValue(OxonTrafficFlow.COLUMN_TPEG_DIRECTION, trafficFlow.getTpegDirection())
                        .withValue(OxonTrafficFlow.COLUMN_FROM_TYPE, trafficFlow.getFromType())
                        .withValue(OxonTrafficFlow.COLUMN_FROM_DESCRIPTOR,
                                trafficFlow.getFromDescriptor())
                        .withValue(OxonTrafficFlow.COLUMN_FROM_LATITUDE, trafficFlow.getFromLatitude())
                        .withValue(OxonTrafficFlow.COLUMN_FROM_LONGITUDE,
                                trafficFlow.getFromLongitude())
                        .withValue(OxonTrafficFlow.COLUMN_TO_TYPE, trafficFlow.getToType())
                        .withValue(OxonTrafficFlow.COLUMN_TO_DESCRIPTOR, trafficFlow.getToDescriptor())
                        .withValue(OxonTrafficFlow.COLUMN_TO_LATITUDE, trafficFlow.getToLatitude())
                        .withValue(OxonTrafficFlow.COLUMN_TO_LONGITUDE, trafficFlow.getToLongitude())
                        .withValue(OxonTrafficFlow.COLUMN_TIME, trafficFlow.getTime())
                        .withValue(OxonTrafficFlow.COLUMN_VEHICLE_FLOW, trafficFlow.getVehicleFlow())
                        .withValue(OxonTrafficFlow.COLUMN_CIN_ID, trafficFlow.getCinId())
                        .withValue(OxonTrafficFlow.COLUMN_CREATION_TIME, trafficFlow.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficQueue[] trafficQueues)
            throws RemoteException, OperationApplicationException {
        if (trafficQueues.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficQueue trafficQueue : trafficQueues) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.TRAFFIC_QUEUE_URI)
                        .withValue(OxonTrafficQueue.COLUMN_ID, trafficQueue.getId())
                        .withValue(OxonTrafficQueue.COLUMN_TPEG_DIRECTION, trafficQueue.getTpegDirection())
                        .withValue(OxonTrafficQueue.COLUMN_FROM_TYPE, trafficQueue.getFromType())
                        .withValue(OxonTrafficQueue.COLUMN_FROM_DESCRIPTOR,
                                trafficQueue.getFromDescriptor())
                        .withValue(OxonTrafficQueue.COLUMN_FROM_LATITUDE, trafficQueue.getFromLatitude())
                        .withValue(OxonTrafficQueue.COLUMN_FROM_LONGITUDE,
                                trafficQueue.getFromLongitude())
                        .withValue(OxonTrafficQueue.COLUMN_TO_TYPE, trafficQueue.getToType())
                        .withValue(OxonTrafficQueue.COLUMN_TO_DESCRIPTOR, trafficQueue.getToDescriptor())
                        .withValue(OxonTrafficQueue.COLUMN_TO_LATITUDE, trafficQueue.getToLatitude())
                        .withValue(OxonTrafficQueue.COLUMN_TO_LONGITUDE, trafficQueue.getToLongitude())
                        .withValue(OxonTrafficQueue.COLUMN_TIME, trafficQueue.getTime())
                        .withValue(OxonTrafficQueue.COLUMN_SEVERITY, trafficQueue.getSeverity())
                        .withValue(OxonTrafficQueue.COLUMN_PRESENT, trafficQueue.getPresent())
                        .withValue(OxonTrafficQueue.COLUMN_CIN_ID, trafficQueue.getCinId())
                        .withValue(OxonTrafficQueue.COLUMN_CREATION_TIME, trafficQueue.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficScoot[] trafficScoots)
            throws RemoteException, OperationApplicationException {
        if (trafficScoots.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficScoot trafficScoot : trafficScoots) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.TRAFFIC_SCOOT_URI)
                        .withValue(OxonTrafficScoot.COLUMN_ID, trafficScoot.getId())
                        .withValue(OxonTrafficScoot.COLUMN_TPEG_DIRECTION, trafficScoot.getTpegDirection())
                        .withValue(OxonTrafficScoot.COLUMN_FROM_TYPE, trafficScoot.getFromType())
                        .withValue(OxonTrafficScoot.COLUMN_FROM_DESCRIPTOR,
                                trafficScoot.getFromDescriptor())
                        .withValue(OxonTrafficScoot.COLUMN_FROM_LATITUDE, trafficScoot.getFromLatitude())
                        .withValue(OxonTrafficScoot.COLUMN_FROM_LONGITUDE,
                                trafficScoot.getFromLongitude())
                        .withValue(OxonTrafficScoot.COLUMN_TO_TYPE, trafficScoot.getToType())
                        .withValue(OxonTrafficScoot.COLUMN_TO_DESCRIPTOR, trafficScoot.getToDescriptor())
                        .withValue(OxonTrafficScoot.COLUMN_TO_LATITUDE, trafficScoot.getToLatitude())
                        .withValue(OxonTrafficScoot.COLUMN_TO_LONGITUDE, trafficScoot.getToLongitude())
                        .withValue(OxonTrafficScoot.COLUMN_TIME, trafficScoot.getTime())
                        .withValue(OxonTrafficScoot.COLUMN_CURRENT_FLOW, trafficScoot.getCurrentFlow())
                        .withValue(OxonTrafficScoot.COLUMN_AVERAGE_SPEED, trafficScoot.getAverageSpeed())
                        .withValue(OxonTrafficScoot.COLUMN_LINK_STATUS_TYPE,
                                trafficScoot.getLinkStatusType())
                        .withValue(OxonTrafficScoot.COLUMN_LINK_STATUS, trafficScoot.getLinkStatus())
                        .withValue(OxonTrafficScoot.COLUMN_LINK_TRAVEL_TIME,
                                trafficScoot.getLinkTravelTime())
                        .withValue(OxonTrafficScoot.COLUMN_CONGESTION_PERCENT,
                                trafficScoot.getCongestionPercent())
                        .withValue(OxonTrafficScoot.COLUMN_CIN_ID, trafficScoot.getCinId())
                        .withValue(OxonTrafficScoot.COLUMN_CREATION_TIME, trafficScoot.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficSpeed[] trafficSpeeds)
            throws RemoteException, OperationApplicationException {
        if (trafficSpeeds.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficSpeed trafficSpeed : trafficSpeeds) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.TRAFFIC_SPEED_URI)
                        .withValue(OxonTrafficSpeed.COLUMN_ID, trafficSpeed.getId())
                        .withValue(OxonTrafficSpeed.COLUMN_TPEG_DIRECTION, trafficSpeed.getTpegDirection())
                        .withValue(OxonTrafficSpeed.COLUMN_FROM_TYPE, trafficSpeed.getFromType())
                        .withValue(OxonTrafficSpeed.COLUMN_FROM_DESCRIPTOR,
                                trafficSpeed.getFromDescriptor())
                        .withValue(OxonTrafficSpeed.COLUMN_FROM_LATITUDE, trafficSpeed.getFromLatitude())
                        .withValue(OxonTrafficSpeed.COLUMN_FROM_LONGITUDE,
                                trafficSpeed.getFromLongitude())
                        .withValue(OxonTrafficSpeed.COLUMN_TO_TYPE, trafficSpeed.getToType())
                        .withValue(OxonTrafficSpeed.COLUMN_TO_DESCRIPTOR, trafficSpeed.getToDescriptor())
                        .withValue(OxonTrafficSpeed.COLUMN_TO_LATITUDE, trafficSpeed.getToLatitude())
                        .withValue(OxonTrafficSpeed.COLUMN_TO_LONGITUDE, trafficSpeed.getToLongitude())
                        .withValue(OxonTrafficSpeed.COLUMN_TIME, trafficSpeed.getTime())
                        .withValue(OxonTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED,
                                trafficSpeed.getAverageVehicleSpeed())
                        .withValue(OxonTrafficSpeed.COLUMN_CIN_ID, trafficSpeed.getCinId())
                        .withValue(OxonTrafficSpeed.COLUMN_CREATION_TIME, trafficSpeed.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull TrafficTravelTime[] trafficTravelTimes)
            throws RemoteException, OperationApplicationException {
        if (trafficTravelTimes.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficTravelTime trafficTravelTime : trafficTravelTimes) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.TRAFFIC_TRAVEL_TIME_URI)
                        .withValue(OxonTrafficTravelTime.COLUMN_ID, trafficTravelTime.getId())
                        .withValue(OxonTrafficTravelTime.COLUMN_TPEG_DIRECTION,
                                trafficTravelTime.getTpegDirection())
                        .withValue(OxonTrafficTravelTime.COLUMN_FROM_TYPE,
                                trafficTravelTime.getFromType())
                        .withValue(OxonTrafficTravelTime.COLUMN_FROM_DESCRIPTOR,
                                trafficTravelTime.getFromDescriptor())
                        .withValue(OxonTrafficTravelTime.COLUMN_FROM_LATITUDE,
                                trafficTravelTime.getFromLatitude())
                        .withValue(OxonTrafficTravelTime.COLUMN_FROM_LONGITUDE,
                                trafficTravelTime.getFromLongitude())
                        .withValue(OxonTrafficTravelTime.COLUMN_TO_TYPE, trafficTravelTime.getToType())
                        .withValue(OxonTrafficTravelTime.COLUMN_TO_DESCRIPTOR,
                                trafficTravelTime.getToDescriptor())
                        .withValue(OxonTrafficTravelTime.COLUMN_TO_LATITUDE,
                                trafficTravelTime.getToLatitude())
                        .withValue(OxonTrafficTravelTime.COLUMN_TO_LONGITUDE,
                                trafficTravelTime.getToLongitude())
                        .withValue(OxonTrafficTravelTime.COLUMN_TIME, trafficTravelTime.getTime())
                        .withValue(OxonTrafficTravelTime.COLUMN_TRAVEL_TIME,
                                trafficTravelTime.getTravelTime())
                        .withValue(OxonTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME,
                                trafficTravelTime.getFreeFlowTravelTime())
                        .withValue(OxonTrafficTravelTime.COLUMN_FREE_FLOW_SPEED,
                                trafficTravelTime.getFreeFlowSpeed())
                        .withValue(OxonTrafficTravelTime.COLUMN_CIN_ID, trafficTravelTime.getCinId())
                        .withValue(OxonTrafficTravelTime.COLUMN_CREATION_TIME,
                                trafficTravelTime.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull VariableMessageSign[] variableMessageSigns)
            throws RemoteException, OperationApplicationException {
        if (variableMessageSigns.length > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (VariableMessageSign variableMessageSign : variableMessageSigns) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(OxonProviderModule.VARIABLE_MESSAGE_SIGN_URI)
                        .withValue(OxonVariableMessageSign.COLUMN_LOCATION_ID,
                                variableMessageSign.getLocationId())
                        .withValue(OxonVariableMessageSign.COLUMN_DESCRIPTION,
                                variableMessageSign.getDescription())
                        .withValue(OxonVariableMessageSign.COLUMN_VMS_TYPE,
                                variableMessageSign.getVmsType())
                        .withValue(OxonVariableMessageSign.COLUMN_LATITUDE,
                                variableMessageSign.getLatitude())
                        .withValue(OxonVariableMessageSign.COLUMN_LONGITUDE,
                                variableMessageSign.getLongitude())
                        .withValue(OxonVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS,
                                variableMessageSign.getNumberOfCharacters())
                        .withValue(OxonVariableMessageSign.COLUMN_NUMBER_OF_ROWS,
                                variableMessageSign.getNumberOfRows())
                        .withValue(OxonVariableMessageSign.COLUMN_VMS_LEGENDS,
                                variableMessageSign.getLegendAsString())
                        .withValue(OxonVariableMessageSign.COLUMN_CIN_ID, variableMessageSign.getCinId())
                        .withValue(OxonVariableMessageSign.COLUMN_CREATION_TIME,
                                variableMessageSign.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(OxonProviderModule.AUTHORITY, operationList);
        }
    }

    public static Cursor getCarParkCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.CAR_PARK_URI,
                new String[]{"*"}, null, null, OxonCarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getCarParkCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.CAR_PARK_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestCarParkCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_CAR_PARK_URI,
                new String[]{"*"}, null, null, OxonCarPark.COLUMN_CAR_PARK_IDENTITY);
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

    public static Cursor getEventCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.EVENT_URI,
                new String[]{"*"}, null, null, OxonEvent.COLUMN_ID);
    }

    public static Cursor getEventCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.EVENT_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestEventCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_EVENT_URI,
                new String[]{"*"}, null, null, OxonEvent.COLUMN_ID);
    }

    public static Event[] getEvents(@NonNull Context context) {
        return eventsFromCursor(getEventCursor(context));
    }

    public static Event[] getEvents(@NonNull Context context, long oldest, long newest) {
        return eventsFromCursor(getEventCursor(context, oldest, newest));
    }

    public static Event[] getLatestEvents(@NonNull Context context) {
        return eventsFromCursor(getLatestEventCursor(context));
    }

    public static Cursor getRoadWorksCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.ROAD_WORKS_URI,
                new String[]{"*"}, null, null, OxonRoadWorks.COLUMN_ID);
    }

    public static Cursor getRoadWorksCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.ROAD_WORKS_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestRoadWorksCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_ROAD_WORKS_URI,
                new String[]{"*"}, null, null, OxonRoadWorks.COLUMN_ID);
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
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, null, null, OxonTrafficFlow.COLUMN_ID);
    }

    public static Cursor getTrafficFlowCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficFlowCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_TRAFFIC_FLOW_URI,
                new String[]{"*"}, null, null, OxonTrafficFlow.COLUMN_ID);
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
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_QUEUE_URI,
                new String[]{"*"}, null, null, OxonTrafficQueue.COLUMN_ID);
    }

    public static Cursor getTrafficQueueCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_QUEUE_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficQueueCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_TRAFFIC_QUEUE_URI,
                new String[]{"*"}, null, null, OxonTrafficQueue.COLUMN_ID);
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
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_SCOOT_URI,
                new String[]{"*"}, null, null, OxonTrafficScoot.COLUMN_ID);
    }

    public static Cursor getTrafficScootCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_SCOOT_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficScootCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_TRAFFIC_SCOOT_URI,
                new String[]{"*"}, null, null, OxonTrafficScoot.COLUMN_ID);
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
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_SPEED_URI,
                new String[]{"*"}, null, null, OxonTrafficSpeed.COLUMN_ID);
    }

    public static Cursor getTrafficSpeedCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_SPEED_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficSpeedCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_TRAFFIC_SPEED_URI,
                new String[]{"*"}, null, null, OxonTrafficSpeed.COLUMN_ID);
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
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                new String[]{"*"}, null, null, OxonTrafficTravelTime.COLUMN_ID);
    }

    public static Cursor getTrafficTravelTimeCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficTravelTimeCursor(@NonNull Context context) {
        return context.getContentResolver().query(
                OxonProviderModule.LATEST_TRAFFIC_TRAVEL_TIME_URI, new String[]{"*"},
                null, null, OxonTrafficTravelTime.COLUMN_ID);
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
        return context.getContentResolver().query(OxonProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, null, null, OxonVariableMessageSign.COLUMN_LOCATION_ID);
    }

    public static Cursor getVariableMessageSignCursor(@NonNull Context context,
                                                      long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestVariableMessageSignCursor(@NonNull Context context) {
        return context.getContentResolver().query(
                OxonProviderModule.LATEST_VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, null, null, OxonVariableMessageSign.COLUMN_LOCATION_ID);
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
                contentResolver.delete(OxonProviderModule.CAR_PARK_URI, null, null);
                break;
            case DATA_TYPE_EVENT:
                contentResolver.delete(OxonProviderModule.EVENT_URI, null, null);
                break;
            case DATA_TYPE_ROAD_WORKS:
                contentResolver.delete(OxonProviderModule.ROAD_WORKS_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(OxonProviderModule.TRAFFIC_FLOW_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_QUEUE:
                contentResolver.delete(OxonProviderModule.TRAFFIC_QUEUE_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_SCOOT:
                contentResolver.delete(OxonProviderModule.TRAFFIC_SCOOT_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_SPEED:
                contentResolver.delete(OxonProviderModule.TRAFFIC_SPEED_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_TRAVEL_TIME:
                contentResolver.delete(OxonProviderModule.TRAFFIC_TRAVEL_TIME_URI, null, null);
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(OxonProviderModule.VARIABLE_MESSAGE_SIGN_URI, null, null);
                break;
        }
    }

    public static void deleteFromProviderBeforeTime(@NonNull Context context, @DataType int dataType,
                                                    long creationTime) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_CAR_PARK:
                contentResolver.delete(OxonProviderModule.CAR_PARK_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_EVENT:
                contentResolver.delete(OxonProviderModule.EVENT_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_ROAD_WORKS:
                contentResolver.delete(OxonProviderModule.ROAD_WORKS_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(OxonProviderModule.TRAFFIC_FLOW_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_QUEUE:
                contentResolver.delete(OxonProviderModule.TRAFFIC_QUEUE_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_SCOOT:
                contentResolver.delete(OxonProviderModule.TRAFFIC_SCOOT_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_SPEED:
                contentResolver.delete(OxonProviderModule.TRAFFIC_SPEED_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_TRAVEL_TIME:
                contentResolver.delete(OxonProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                        CREATED_BEFORE, new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(OxonProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                        CREATED_BEFORE, new String[]{String.valueOf(creationTime)});
                break;
        }
    }

    public static CarPark[] carParksFromCursor(Cursor cursor) {
        CarPark[] carParks = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                carParks = new CarPark[cursor.getCount()];
                for (int i = 0; i < carParks.length; i++) {
                    carParks[i] = new CarPark();
                    carParks[i].setCarParkIdentity(cursor.getString(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_CAR_PARK_IDENTITY)));
                    carParks[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_LATITUDE)));
                    carParks[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_LONGITUDE)));
                    carParks[i].setOccupancy(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_OCCUPANCY)));
                    carParks[i].setOccupancyTrend(cursor.getString(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_OCCUPANCY_TREND)));
                    carParks[i].setTotalParkingCapacity(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_TOTAL_PARKING_CAPACITY)));
                    carParks[i].setFillRate(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_FILL_RATE)));
                    carParks[i].setExitRate(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_EXIT_RATE)));
                    carParks[i].setAlmostFullIncreasing(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_ALMOST_FULL_INCREASING)));
                    carParks[i].setAlmostFullDecreasing(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_ALMOST_FULL_DECREASING)));
                    carParks[i].setStatus(cursor.getString(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_STATUS)));
                    carParks[i].setStatusTime(cursor.getString(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_STATUS_TIME)));
                    carParks[i].setQueuingTime(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_QUEUING_TIME)));
                    carParks[i].setParkingAreaName(cursor.getString(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_PARKING_AREA_NAME)));
                    carParks[i].setEntranceFull(cursor.getDouble(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_ENTRANCE_FULL)));
                    carParks[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_CIN_ID)));
                    carParks[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonCarPark.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (carParks == null) {
            return new CarPark[0];
        }
        return carParks;
    }

    public static Event[] eventsFromCursor(Cursor cursor) {
        Event[] events = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                events = new Event[cursor.getCount()];
                for (int i = 0; i < events.length; i++) {
                    events[i] = new Event();
                    events[i].setId(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_ID)));
                    events[i].setStartOfPeriod(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_START_OF_PERIOD)));
                    events[i].setEndOfPeriod(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_END_OF_PERIOD)));
                    events[i].setOverallStartTime(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_OVERALL_START_TIME)));
                    events[i].setOverallEndTime(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_OVERALL_END_TIME)));
                    events[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonEvent.COLUMN_LATITUDE)));
                    events[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonEvent.COLUMN_LONGITUDE)));
                    events[i].setImpactOnTraffic(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_IMPACT_ON_TRAFFIC)));
                    events[i].setValidityStatus(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_VALIDITY_STATUS)));
                    events[i].setDescription(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_DESCRIPTION)));
                    events[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonEvent.COLUMN_CIN_ID)));
                    events[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonEvent.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (events == null) {
            return new Event[0];
        }
        return events;
    }

    public static RoadWorks[] roadWorksesFromCursor(Cursor cursor) {
        RoadWorks[] roadWorkses = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                roadWorkses = new RoadWorks[cursor.getCount()];
                for (int i = 0; i < roadWorkses.length; i++) {
                    roadWorkses[i] = new RoadWorks();
                    roadWorkses[i].setId(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_ID)));
                    roadWorkses[i].setEffectOnRoadLayout(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_EFFECT_ON_ROAD_LAYOUT)));
                    roadWorkses[i].setRoadMaintenanceType(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_ROAD_MAINTENANCE_TYPE)));
                    roadWorkses[i].setComment(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_COMMENT)));
                    roadWorkses[i].setImpactOnTraffic(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_IMPACT_ON_TRAFFIC)));
                    roadWorkses[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_LATITUDE)));
                    roadWorkses[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_LONGITUDE)));
                    roadWorkses[i].setValidityStatus(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_VALIDITY_STATUS)));
                    roadWorkses[i].setOverallStartTime(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_OVERALL_START_TIME)));
                    roadWorkses[i].setOverallEndTime(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_OVERALL_END_TIME)));
                    roadWorkses[i].setStartOfPeriod(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_START_OF_PERIOD)));
                    roadWorkses[i].setEndOfPeriod(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_END_OF_PERIOD)));
                    roadWorkses[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_CIN_ID)));
                    roadWorkses[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonRoadWorks.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (roadWorkses == null) {
            return new RoadWorks[0];
        }
        return roadWorkses;
    }

    public static TrafficFlow[] trafficFlowsFromCursor(Cursor cursor) {
        TrafficFlow[] trafficFlows = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficFlows = new TrafficFlow[cursor.getCount()];
                for (int i = 0; i < trafficFlows.length; i++) {
                    trafficFlows[i] = new TrafficFlow();
                    trafficFlows[i].setId(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_ID)));
                    trafficFlows[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_TPEG_DIRECTION)));
                    trafficFlows[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_FROM_TYPE)));
                    trafficFlows[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_FROM_DESCRIPTOR)));
                    trafficFlows[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_FROM_LATITUDE)));
                    trafficFlows[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_FROM_LONGITUDE)));
                    trafficFlows[i].setToType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_TO_TYPE)));
                    trafficFlows[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_TO_DESCRIPTOR)));
                    trafficFlows[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_TO_LATITUDE)));
                    trafficFlows[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_TO_LONGITUDE)));
                    trafficFlows[i].setTime(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_TIME)));
                    trafficFlows[i].setVehicleFlow(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_VEHICLE_FLOW)));
                    trafficFlows[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_CIN_ID)));
                    trafficFlows[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonTrafficFlow.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
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
                            OxonTrafficQueue.COLUMN_ID)));
                    trafficQueues[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_TPEG_DIRECTION)));
                    trafficQueues[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_FROM_TYPE)));
                    trafficQueues[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_FROM_DESCRIPTOR)));
                    trafficQueues[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_FROM_LATITUDE)));
                    trafficQueues[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_FROM_LONGITUDE)));
                    trafficQueues[i].setToType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_TO_TYPE)));
                    trafficQueues[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_TO_DESCRIPTOR)));
                    trafficQueues[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_TO_LATITUDE)));
                    trafficQueues[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_TO_LONGITUDE)));
                    trafficQueues[i].setTime(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_TIME)));
                    trafficQueues[i].setSeverity(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_SEVERITY)));
                    trafficQueues[i].setPresent(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_PRESENT)));
                    trafficQueues[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_CIN_ID)));
                    trafficQueues[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonTrafficQueue.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
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
                            OxonTrafficScoot.COLUMN_ID)));
                    trafficScoots[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_TPEG_DIRECTION)));
                    trafficScoots[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_FROM_TYPE)));
                    trafficScoots[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_FROM_DESCRIPTOR)));
                    trafficScoots[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_FROM_LATITUDE)));
                    trafficScoots[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_FROM_LONGITUDE)));
                    trafficScoots[i].setToType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_TO_TYPE)));
                    trafficScoots[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_TO_DESCRIPTOR)));
                    trafficScoots[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_TO_LATITUDE)));
                    trafficScoots[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_TO_LONGITUDE)));
                    trafficScoots[i].setTime(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_TIME)));
                    trafficScoots[i].setCurrentFlow(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_CURRENT_FLOW)));
                    trafficScoots[i].setAverageSpeed(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_AVERAGE_SPEED)));
                    trafficScoots[i].setLinkStatusType(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_LINK_STATUS_TYPE)));
                    trafficScoots[i].setLinkStatus(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_LINK_STATUS)));
                    trafficScoots[i].setLinkTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_LINK_TRAVEL_TIME)));
                    trafficScoots[i].setCongestionPercent(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_CONGESTION_PERCENT)));
                    trafficScoots[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_CIN_ID)));
                    trafficScoots[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonTrafficScoot.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
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
                            OxonTrafficSpeed.COLUMN_ID)));
                    trafficSpeeds[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_TPEG_DIRECTION)));
                    trafficSpeeds[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_FROM_TYPE)));
                    trafficSpeeds[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_FROM_DESCRIPTOR)));
                    trafficSpeeds[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_FROM_LATITUDE)));
                    trafficSpeeds[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_FROM_LONGITUDE)));
                    trafficSpeeds[i].setToType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_TO_TYPE)));
                    trafficSpeeds[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_TO_DESCRIPTOR)));
                    trafficSpeeds[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_TO_LATITUDE)));
                    trafficSpeeds[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_TO_LONGITUDE)));
                    trafficSpeeds[i].setTime(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_TIME)));
                    trafficSpeeds[i].setAverageVehicleSpeed(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED)));
                    trafficSpeeds[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_CIN_ID)));
                    trafficSpeeds[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonTrafficSpeed.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
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
                            OxonTrafficTravelTime.COLUMN_ID)));
                    trafficTravelTimes[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_TPEG_DIRECTION)));
                    trafficTravelTimes[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_FROM_TYPE)));
                    trafficTravelTimes[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_FROM_DESCRIPTOR)));
                    trafficTravelTimes[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_FROM_LATITUDE)));
                    trafficTravelTimes[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_FROM_LONGITUDE)));
                    trafficTravelTimes[i].setToType(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_TO_TYPE)));
                    trafficTravelTimes[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_TO_DESCRIPTOR)));
                    trafficTravelTimes[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_TO_LATITUDE)));
                    trafficTravelTimes[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_TO_LONGITUDE)));
                    trafficTravelTimes[i].setTime(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_TIME)));
                    trafficTravelTimes[i].setTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_TRAVEL_TIME)));
                    trafficTravelTimes[i].setFreeFlowTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME)));
                    trafficTravelTimes[i].setFreeFlowSpeed(cursor.getDouble(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_FREE_FLOW_SPEED)));
                    trafficTravelTimes[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_CIN_ID)));
                    trafficTravelTimes[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonTrafficTravelTime.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (trafficTravelTimes == null) {
            return new TrafficTravelTime[0];
        }
        return trafficTravelTimes;
    }


    public static VariableMessageSign[] variableMessageSignsFromCursor(Cursor cursor) {
        VariableMessageSign[] variableMessageSigns = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                variableMessageSigns = new VariableMessageSign[cursor.getCount()];
                for (int i = 0; i < variableMessageSigns.length; i++) {
                    variableMessageSigns[i] = new VariableMessageSign();
                    variableMessageSigns[i].setLocationId(cursor.getString(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_LOCATION_ID)));
                    variableMessageSigns[i].setDescription(cursor.getString(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_DESCRIPTION)));
                    variableMessageSigns[i].setVmsType(cursor.getString(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_VMS_TYPE)));
                    variableMessageSigns[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_LATITUDE)));
                    variableMessageSigns[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_LONGITUDE)));
                    variableMessageSigns[i].setNumberOfCharacters(cursor.getDouble(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS)));
                    variableMessageSigns[i].setNumberOfRows(cursor.getDouble(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_NUMBER_OF_ROWS)));
                    variableMessageSigns[i].setLegendFromString(cursor.getString(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_VMS_LEGENDS)));
                    variableMessageSigns[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_CIN_ID)));
                    variableMessageSigns[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonVariableMessageSign.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
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
