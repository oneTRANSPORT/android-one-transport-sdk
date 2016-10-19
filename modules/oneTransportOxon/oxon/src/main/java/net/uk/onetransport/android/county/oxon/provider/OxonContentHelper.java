/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.uk.onetransport.android.county.oxon.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.roadworks.Roadworks;
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

import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonCarPark;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonEvent;
import static net.uk.onetransport.android.county.oxon.provider.OxonContract.OxonRoadworks;
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
            DATA_TYPE_ROADWORKS,
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
    public static final int DATA_TYPE_ROADWORKS = 3;
    public static final int DATA_TYPE_TRAFFIC_FLOW = 4;
    public static final int DATA_TYPE_TRAFFIC_QUEUE = 5;
    public static final int DATA_TYPE_TRAFFIC_SCOOT = 6;
    public static final int DATA_TYPE_TRAFFIC_SPEED = 7;
    public static final int DATA_TYPE_TRAFFIC_TRAVEL_TIME = 8;
    public static final int DATA_TYPE_VMS = 9;

    public static void insertIntoProvider(@NonNull Context context, @NonNull CarPark[] carParks)
            throws RemoteException, OperationApplicationException {
        if (carParks.length > 0) {
            ContentValues[] cvs = new ContentValues[carParks.length];
            for (int i = 0; i < carParks.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonCarPark.COLUMN_CAR_PARK_IDENTITY, carParks[i].getCarParkIdentity());
                cvs[i].put(OxonCarPark.COLUMN_LATITUDE, carParks[i].getLatitude());
                cvs[i].put(OxonCarPark.COLUMN_LONGITUDE, carParks[i].getLongitude());
                cvs[i].put(OxonCarPark.COLUMN_OCCUPANCY, carParks[i].getOccupancy());
                cvs[i].put(OxonCarPark.COLUMN_OCCUPANCY_TREND, carParks[i].getOccupancyTrend());
                cvs[i].put(OxonCarPark.COLUMN_TOTAL_PARKING_CAPACITY, carParks[i].getTotalParkingCapacity());
                cvs[i].put(OxonCarPark.COLUMN_FILL_RATE, carParks[i].getFillRate());
                cvs[i].put(OxonCarPark.COLUMN_EXIT_RATE, carParks[i].getExitRate());
                cvs[i].put(OxonCarPark.COLUMN_ALMOST_FULL_INCREASING, carParks[i].getAlmostFullIncreasing());
                cvs[i].put(OxonCarPark.COLUMN_ALMOST_FULL_DECREASING, carParks[i].getAlmostFullDecreasing());
                cvs[i].put(OxonCarPark.COLUMN_FULL_INCREASING, carParks[i].getFullIncreasing());
                cvs[i].put(OxonCarPark.COLUMN_FULL_DECREASING, carParks[i].getFullDecreasing());
                cvs[i].put(OxonCarPark.COLUMN_STATUS, carParks[i].getStatus());
                cvs[i].put(OxonCarPark.COLUMN_STATUS_TIME, carParks[i].getStatusTime());
                cvs[i].put(OxonCarPark.COLUMN_QUEUING_TIME, carParks[i].getQueuingTime());
                cvs[i].put(OxonCarPark.COLUMN_PARKING_AREA_NAME, carParks[i].getParkingAreaName());
                cvs[i].put(OxonCarPark.COLUMN_ENTRANCE_FULL, carParks[i].getEntranceFull());
                cvs[i].put(OxonCarPark.COLUMN_CIN_ID, carParks[i].getCinId());
                cvs[i].put(OxonCarPark.COLUMN_CREATION_TIME, carParks[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.CAR_PARK_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull Event[] events)
            throws RemoteException, OperationApplicationException {
        if (events.length > 0) {
            ContentValues[] cvs = new ContentValues[events.length];
            for (int i = 0; i < events.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonEvent.COLUMN_ID, events[i].getId());
                cvs[i].put(OxonEvent.COLUMN_START_OF_PERIOD, events[i].getStartOfPeriod());
                cvs[i].put(OxonEvent.COLUMN_END_OF_PERIOD, events[i].getEndOfPeriod());
                cvs[i].put(OxonEvent.COLUMN_OVERALL_START_TIME, events[i].getOverallStartTime());
                cvs[i].put(OxonEvent.COLUMN_OVERALL_END_TIME, events[i].getOverallEndTime());
                cvs[i].put(OxonEvent.COLUMN_LATITUDE, events[i].getLatitude());
                cvs[i].put(OxonEvent.COLUMN_LONGITUDE, events[i].getLongitude());
                cvs[i].put(OxonEvent.COLUMN_DESCRIPTION, events[i].getDescription());
                cvs[i].put(OxonEvent.COLUMN_IMPACT_ON_TRAFFIC, events[i].getImpactOnTraffic());
                cvs[i].put(OxonEvent.COLUMN_VALIDITY_STATUS, events[i].getValidityStatus());
                cvs[i].put(OxonEvent.COLUMN_CIN_ID, events[i].getCinId());
                cvs[i].put(OxonEvent.COLUMN_CREATION_TIME, events[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.EVENT_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull Roadworks[] roadworkses)
            throws RemoteException, OperationApplicationException {
        if (roadworkses.length > 0) {
            ContentValues[] cvs = new ContentValues[roadworkses.length];
            for (int i = 0; i < roadworkses.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonRoadworks.COLUMN_ID, roadworkses[i].getId());
                cvs[i].put(OxonRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT, roadworkses[i].getEffectOnRoadLayout());
                cvs[i].put(OxonRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE, roadworkses[i].getRoadMaintenanceType());
                cvs[i].put(OxonRoadworks.COLUMN_COMMENT, roadworkses[i].getComment());
                cvs[i].put(OxonRoadworks.COLUMN_IMPACT_ON_TRAFFIC, roadworkses[i].getImpactOnTraffic());
                cvs[i].put(OxonRoadworks.COLUMN_LATITUDE, roadworkses[i].getLatitude());
                cvs[i].put(OxonRoadworks.COLUMN_LONGITUDE, roadworkses[i].getLongitude());
                cvs[i].put(OxonRoadworks.COLUMN_VALIDITY_STATUS, roadworkses[i].getValidityStatus());
                cvs[i].put(OxonRoadworks.COLUMN_OVERALL_START_TIME, roadworkses[i].getOverallStartTime());
                cvs[i].put(OxonRoadworks.COLUMN_OVERALL_END_TIME, roadworkses[i].getOverallEndTime());
                cvs[i].put(OxonRoadworks.COLUMN_START_OF_PERIOD, roadworkses[i].getStartOfPeriod());
                cvs[i].put(OxonRoadworks.COLUMN_END_OF_PERIOD, roadworkses[i].getEndOfPeriod());
                cvs[i].put(OxonRoadworks.COLUMN_CIN_ID, roadworkses[i].getCinId());
                cvs[i].put(OxonRoadworks.COLUMN_CREATION_TIME, roadworkses[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.ROADWORKS_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficFlow[] trafficFlows)
            throws RemoteException, OperationApplicationException {
        if (trafficFlows.length > 0) {
            ContentValues[] cvs = new ContentValues[trafficFlows.length];
            for (int i = 0; i < trafficFlows.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonTrafficFlow.COLUMN_ID, trafficFlows[i].getId());
                cvs[i].put(OxonTrafficFlow.COLUMN_TPEG_DIRECTION, trafficFlows[i].getTpegDirection());
                cvs[i].put(OxonTrafficFlow.COLUMN_FROM_TYPE, trafficFlows[i].getFromType());
                cvs[i].put(OxonTrafficFlow.COLUMN_FROM_DESCRIPTOR, trafficFlows[i].getFromDescriptor());
                cvs[i].put(OxonTrafficFlow.COLUMN_FROM_LATITUDE, trafficFlows[i].getFromLatitude());
                cvs[i].put(OxonTrafficFlow.COLUMN_FROM_LONGITUDE, trafficFlows[i].getFromLongitude());
                cvs[i].put(OxonTrafficFlow.COLUMN_TO_TYPE, trafficFlows[i].getToType());
                cvs[i].put(OxonTrafficFlow.COLUMN_TO_DESCRIPTOR, trafficFlows[i].getToDescriptor());
                cvs[i].put(OxonTrafficFlow.COLUMN_TO_LATITUDE, trafficFlows[i].getToLatitude());
                cvs[i].put(OxonTrafficFlow.COLUMN_TO_LONGITUDE, trafficFlows[i].getToLongitude());
                cvs[i].put(OxonTrafficFlow.COLUMN_TIME, trafficFlows[i].getTime());
                cvs[i].put(OxonTrafficFlow.COLUMN_VEHICLE_FLOW, trafficFlows[i].getVehicleFlow());
                cvs[i].put(OxonTrafficFlow.COLUMN_CIN_ID, trafficFlows[i].getCinId());
                cvs[i].put(OxonTrafficFlow.COLUMN_CREATION_TIME, trafficFlows[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.TRAFFIC_FLOW_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficQueue[] trafficQueues)
            throws RemoteException, OperationApplicationException {
        if (trafficQueues.length > 0) {
            ContentValues[] cvs = new ContentValues[trafficQueues.length];
            for (int i = 0; i < trafficQueues.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonTrafficQueue.COLUMN_ID, trafficQueues[i].getId());
                cvs[i].put(OxonTrafficQueue.COLUMN_ID, trafficQueues[i].getId());
                cvs[i].put(OxonTrafficQueue.COLUMN_TPEG_DIRECTION, trafficQueues[i].getTpegDirection());
                cvs[i].put(OxonTrafficQueue.COLUMN_FROM_TYPE, trafficQueues[i].getFromType());
                cvs[i].put(OxonTrafficQueue.COLUMN_FROM_DESCRIPTOR, trafficQueues[i].getFromDescriptor());
                cvs[i].put(OxonTrafficQueue.COLUMN_FROM_LATITUDE, trafficQueues[i].getFromLatitude());
                cvs[i].put(OxonTrafficQueue.COLUMN_FROM_LONGITUDE, trafficQueues[i].getFromLongitude());
                cvs[i].put(OxonTrafficQueue.COLUMN_TO_TYPE, trafficQueues[i].getToType());
                cvs[i].put(OxonTrafficQueue.COLUMN_TO_DESCRIPTOR, trafficQueues[i].getToDescriptor());
                cvs[i].put(OxonTrafficQueue.COLUMN_TO_LATITUDE, trafficQueues[i].getToLatitude());
                cvs[i].put(OxonTrafficQueue.COLUMN_TO_LONGITUDE, trafficQueues[i].getToLongitude());
                cvs[i].put(OxonTrafficQueue.COLUMN_TIME, trafficQueues[i].getTime());
                cvs[i].put(OxonTrafficQueue.COLUMN_SEVERITY, trafficQueues[i].getSeverity());
                cvs[i].put(OxonTrafficQueue.COLUMN_PRESENT, trafficQueues[i].getPresent());
                cvs[i].put(OxonTrafficQueue.COLUMN_CIN_ID, trafficQueues[i].getCinId());
                cvs[i].put(OxonTrafficQueue.COLUMN_CREATION_TIME, trafficQueues[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.TRAFFIC_QUEUE_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficScoot[] trafficScoots)
            throws RemoteException, OperationApplicationException {
        if (trafficScoots.length > 0) {
            ContentValues[] cvs = new ContentValues[trafficScoots.length];
            for (int i = 0; i < trafficScoots.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonTrafficScoot.COLUMN_ID, trafficScoots[i].getId());
                cvs[i].put(OxonTrafficScoot.COLUMN_ID, trafficScoots[i].getId());
                cvs[i].put(OxonTrafficScoot.COLUMN_TPEG_DIRECTION, trafficScoots[i].getTpegDirection());
                cvs[i].put(OxonTrafficScoot.COLUMN_FROM_TYPE, trafficScoots[i].getFromType());
                cvs[i].put(OxonTrafficScoot.COLUMN_FROM_DESCRIPTOR, trafficScoots[i].getFromDescriptor());
                cvs[i].put(OxonTrafficScoot.COLUMN_FROM_LATITUDE, trafficScoots[i].getFromLatitude());
                cvs[i].put(OxonTrafficScoot.COLUMN_FROM_LONGITUDE, trafficScoots[i].getFromLongitude());
                cvs[i].put(OxonTrafficScoot.COLUMN_TO_TYPE, trafficScoots[i].getToType());
                cvs[i].put(OxonTrafficScoot.COLUMN_TO_DESCRIPTOR, trafficScoots[i].getToDescriptor());
                cvs[i].put(OxonTrafficScoot.COLUMN_TO_LATITUDE, trafficScoots[i].getToLatitude());
                cvs[i].put(OxonTrafficScoot.COLUMN_TO_LONGITUDE, trafficScoots[i].getToLongitude());
                cvs[i].put(OxonTrafficScoot.COLUMN_TIME, trafficScoots[i].getTime());
                cvs[i].put(OxonTrafficScoot.COLUMN_CURRENT_FLOW, trafficScoots[i].getCurrentFlow());
                cvs[i].put(OxonTrafficScoot.COLUMN_AVERAGE_SPEED, trafficScoots[i].getAverageSpeed());
                cvs[i].put(OxonTrafficScoot.COLUMN_LINK_STATUS_TYPE, trafficScoots[i].getLinkStatusType());
                cvs[i].put(OxonTrafficScoot.COLUMN_LINK_STATUS, trafficScoots[i].getLinkStatus());
                cvs[i].put(OxonTrafficScoot.COLUMN_LINK_TRAVEL_TIME, trafficScoots[i].getLinkTravelTime());
                cvs[i].put(OxonTrafficScoot.COLUMN_CONGESTION_PERCENT, trafficScoots[i].getCongestionPercent());
                cvs[i].put(OxonTrafficScoot.COLUMN_CIN_ID, trafficScoots[i].getCinId());
                cvs[i].put(OxonTrafficScoot.COLUMN_CREATION_TIME, trafficScoots[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.TRAFFIC_SCOOT_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficSpeed[] trafficSpeeds)
            throws RemoteException, OperationApplicationException {
        if (trafficSpeeds.length > 0) {
            ContentValues[] cvs = new ContentValues[trafficSpeeds.length];
            for (int i = 0; i < trafficSpeeds.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonTrafficSpeed.COLUMN_ID, trafficSpeeds[i].getId());
                cvs[i].put(OxonTrafficSpeed.COLUMN_ID, trafficSpeeds[i].getId());
                cvs[i].put(OxonTrafficSpeed.COLUMN_TPEG_DIRECTION, trafficSpeeds[i].getTpegDirection());
                cvs[i].put(OxonTrafficSpeed.COLUMN_FROM_TYPE, trafficSpeeds[i].getFromType());
                cvs[i].put(OxonTrafficSpeed.COLUMN_FROM_DESCRIPTOR, trafficSpeeds[i].getFromDescriptor());
                cvs[i].put(OxonTrafficSpeed.COLUMN_FROM_LATITUDE, trafficSpeeds[i].getFromLatitude());
                cvs[i].put(OxonTrafficSpeed.COLUMN_FROM_LONGITUDE, trafficSpeeds[i].getFromLongitude());
                cvs[i].put(OxonTrafficSpeed.COLUMN_TO_TYPE, trafficSpeeds[i].getToType());
                cvs[i].put(OxonTrafficSpeed.COLUMN_TO_DESCRIPTOR, trafficSpeeds[i].getToDescriptor());
                cvs[i].put(OxonTrafficSpeed.COLUMN_TO_LATITUDE, trafficSpeeds[i].getToLatitude());
                cvs[i].put(OxonTrafficSpeed.COLUMN_TO_LONGITUDE, trafficSpeeds[i].getToLongitude());
                cvs[i].put(OxonTrafficSpeed.COLUMN_TIME, trafficSpeeds[i].getTime());
                cvs[i].put(OxonTrafficSpeed.COLUMN_AVERAGE_VEHICLE_SPEED, trafficSpeeds[i].getAverageVehicleSpeed());
                cvs[i].put(OxonTrafficSpeed.COLUMN_CIN_ID, trafficSpeeds[i].getCinId());
                cvs[i].put(OxonTrafficSpeed.COLUMN_CREATION_TIME, trafficSpeeds[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.TRAFFIC_SPEED_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull TrafficTravelTime[] trafficTravelTimes)
            throws RemoteException, OperationApplicationException {
        if (trafficTravelTimes.length > 0) {
            ContentValues[] cvs = new ContentValues[trafficTravelTimes.length];
            for (int i = 0; i < trafficTravelTimes.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonTrafficTravelTime.COLUMN_ID, trafficTravelTimes[i].getId());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_ID, trafficTravelTimes[i].getId());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_TPEG_DIRECTION, trafficTravelTimes[i].getTpegDirection());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_FROM_TYPE, trafficTravelTimes[i].getFromType());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_FROM_DESCRIPTOR, trafficTravelTimes[i].getFromDescriptor());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_FROM_LATITUDE, trafficTravelTimes[i].getFromLatitude());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_FROM_LONGITUDE, trafficTravelTimes[i].getFromLongitude());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_TO_TYPE, trafficTravelTimes[i].getToType());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_TO_DESCRIPTOR, trafficTravelTimes[i].getToDescriptor());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_TO_LATITUDE, trafficTravelTimes[i].getToLatitude());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_TO_LONGITUDE, trafficTravelTimes[i].getToLongitude());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_TIME, trafficTravelTimes[i].getTime());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_TRAVEL_TIME, trafficTravelTimes[i].getTravelTime());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME, trafficTravelTimes[i].getFreeFlowTravelTime());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_FREE_FLOW_SPEED, trafficTravelTimes[i].getFreeFlowSpeed());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_CIN_ID, trafficTravelTimes[i].getCinId());
                cvs[i].put(OxonTrafficTravelTime.COLUMN_CREATION_TIME, trafficTravelTimes[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.TRAFFIC_TRAVEL_TIME_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull VariableMessageSign[] variableMessageSigns)
            throws RemoteException, OperationApplicationException {
        if (variableMessageSigns.length > 0) {
            ContentValues[] cvs = new ContentValues[variableMessageSigns.length];
            for (int i = 0; i < variableMessageSigns.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(OxonVariableMessageSign.COLUMN_LOCATION_ID, variableMessageSigns[i].getLocationId());
                cvs[i].put(OxonVariableMessageSign.COLUMN_LOCATION_ID, variableMessageSigns[i].getLocationId());
                cvs[i].put(OxonVariableMessageSign.COLUMN_DESCRIPTION, variableMessageSigns[i].getDescription());
                cvs[i].put(OxonVariableMessageSign.COLUMN_VMS_TYPE, variableMessageSigns[i].getVmsType());
                cvs[i].put(OxonVariableMessageSign.COLUMN_LATITUDE, variableMessageSigns[i].getLatitude());
                cvs[i].put(OxonVariableMessageSign.COLUMN_LONGITUDE, variableMessageSigns[i].getLongitude());
                cvs[i].put(OxonVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS, variableMessageSigns[i].getNumberOfCharacters());
                cvs[i].put(OxonVariableMessageSign.COLUMN_NUMBER_OF_ROWS, variableMessageSigns[i].getNumberOfRows());
                cvs[i].put(OxonVariableMessageSign.COLUMN_VMS_LEGENDS, variableMessageSigns[i].getLegendAsString());
                cvs[i].put(OxonVariableMessageSign.COLUMN_CIN_ID, variableMessageSigns[i].getCinId());
                cvs[i].put(OxonVariableMessageSign.COLUMN_CREATION_TIME, variableMessageSigns[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(OxonProviderModule.VARIABLE_MESSAGE_SIGN_URI, cvs);
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

    public static Cursor getRoadworksCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.ROADWORKS_URI,
                new String[]{"*"}, null, null, OxonRoadworks.COLUMN_ID);
    }

    public static Cursor getRoadworksCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(OxonProviderModule.ROADWORKS_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestRoadworksCursor(@NonNull Context context) {
        return context.getContentResolver().query(OxonProviderModule.LATEST_ROADWORKS_URI,
                new String[]{"*"}, null, null, OxonRoadworks.COLUMN_ID);
    }

    public static Roadworks[] getRoadworks(@NonNull Context context) {
        return roadworksesFromCursor(getRoadworksCursor(context));
    }

    public static Roadworks[] getRoadworks(@NonNull Context context, long oldest, long newest) {
        return roadworksesFromCursor(getRoadworksCursor(context, oldest, newest));
    }

    public static Roadworks[] getLatestRoadworks(@NonNull Context context) {
        return roadworksesFromCursor(getLatestRoadworksCursor(context));
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
            case DATA_TYPE_ROADWORKS:
                contentResolver.delete(OxonProviderModule.ROADWORKS_URI, null, null);
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
            case DATA_TYPE_ROADWORKS:
                contentResolver.delete(OxonProviderModule.ROADWORKS_URI, CREATED_BEFORE,
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

    public static Roadworks[] roadworksesFromCursor(Cursor cursor) {
        Roadworks[] roadworkses = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                roadworkses = new Roadworks[cursor.getCount()];
                for (int i = 0; i < roadworkses.length; i++) {
                    roadworkses[i] = new Roadworks();
                    roadworkses[i].setId(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_ID)));
                    roadworkses[i].setEffectOnRoadLayout(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT)));
                    roadworkses[i].setRoadMaintenanceType(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE)));
                    roadworkses[i].setComment(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_COMMENT)));
                    roadworkses[i].setImpactOnTraffic(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_IMPACT_ON_TRAFFIC)));
                    roadworkses[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_LATITUDE)));
                    roadworkses[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_LONGITUDE)));
                    roadworkses[i].setValidityStatus(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_VALIDITY_STATUS)));
                    roadworkses[i].setOverallStartTime(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_OVERALL_START_TIME)));
                    roadworkses[i].setOverallEndTime(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_OVERALL_END_TIME)));
                    roadworkses[i].setStartOfPeriod(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_START_OF_PERIOD)));
                    roadworkses[i].setEndOfPeriod(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_END_OF_PERIOD)));
                    roadworkses[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_CIN_ID)));
                    roadworkses[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            OxonRoadworks.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (roadworkses == null) {
            return new Roadworks[0];
        }
        return roadworkses;
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
