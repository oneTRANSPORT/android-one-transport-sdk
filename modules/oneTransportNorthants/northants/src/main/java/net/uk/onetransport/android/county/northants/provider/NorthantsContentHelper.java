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
package net.uk.onetransport.android.county.northants.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.county.northants.carparks.CarPark;
import net.uk.onetransport.android.county.northants.roadworks.Roadworks;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.northants.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;
import net.uk.onetransport.android.modules.common.provider.CommonContentHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsCarPark;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsRoadworks;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsTrafficFlow;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsTrafficTravelTime;
import static net.uk.onetransport.android.county.northants.provider.NorthantsContract.NorthantsVariableMessageSign;

public class NorthantsContentHelper extends CommonContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_CAR_PARK,
            DATA_TYPE_ROADWORKS,
            DATA_TYPE_TRAFFIC_FLOW,
            DATA_TYPE_TRAFFIC_TRAVEL_TIME,
            DATA_TYPE_VMS})
    public @interface DataType {
    }

    public static final int DATA_TYPE_CAR_PARK = 1;
    public static final int DATA_TYPE_ROADWORKS = 2;
    public static final int DATA_TYPE_TRAFFIC_FLOW = 3;
    public static final int DATA_TYPE_TRAFFIC_TRAVEL_TIME = 4;
    public static final int DATA_TYPE_VMS = 5;

    public static void insertIntoProvider(@NonNull Context context, @NonNull CarPark[] carParks)
            throws RemoteException, OperationApplicationException {
        if (carParks.length > 0) {
            ContentValues[] cvs = new ContentValues[carParks.length];
            for (int i = 0; i < carParks.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY, carParks[i].getCarParkIdentity());
                cvs[i].put(NorthantsCarPark.COLUMN_LATITUDE, carParks[i].getLatitude());
                cvs[i].put(NorthantsCarPark.COLUMN_LONGITUDE, carParks[i].getLongitude());
                cvs[i].put(NorthantsCarPark.COLUMN_OCCUPANCY, carParks[i].getOccupancy());
                cvs[i].put(NorthantsCarPark.COLUMN_OCCUPANCY_TREND, carParks[i].getOccupancyTrend());
                cvs[i].put(NorthantsCarPark.COLUMN_TOTAL_PARKING_CAPACITY, carParks[i].getTotalParkingCapacity());
                cvs[i].put(NorthantsCarPark.COLUMN_FILL_RATE, carParks[i].getFillRate());
                cvs[i].put(NorthantsCarPark.COLUMN_EXIT_RATE, carParks[i].getExitRate());
                cvs[i].put(NorthantsCarPark.COLUMN_ALMOST_FULL_INCREASING, carParks[i].getAlmostFullIncreasing());
                cvs[i].put(NorthantsCarPark.COLUMN_ALMOST_FULL_DECREASING, carParks[i].getAlmostFullDecreasing());
                cvs[i].put(NorthantsCarPark.COLUMN_FULL_INCREASING, carParks[i].getFullIncreasing());
                cvs[i].put(NorthantsCarPark.COLUMN_FULL_DECREASING, carParks[i].getFullDecreasing());
                cvs[i].put(NorthantsCarPark.COLUMN_STATUS, carParks[i].getStatus());
                cvs[i].put(NorthantsCarPark.COLUMN_STATUS_TIME, carParks[i].getStatusTime());
                cvs[i].put(NorthantsCarPark.COLUMN_QUEUING_TIME, carParks[i].getQueuingTime());
                cvs[i].put(NorthantsCarPark.COLUMN_PARKING_AREA_NAME, carParks[i].getParkingAreaName());
                cvs[i].put(NorthantsCarPark.COLUMN_ENTRANCE_FULL, carParks[i].getEntranceFull());
                cvs[i].put(NorthantsCarPark.COLUMN_CIN_ID, carParks[i].getCinId());
                cvs[i].put(NorthantsCarPark.COLUMN_CREATION_TIME, carParks[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(NorthantsProviderModule.CAR_PARK_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull Roadworks[] roadworkses)
            throws RemoteException, OperationApplicationException {
        if (roadworkses.length > 0) {
            ContentValues[] cvs = new ContentValues[roadworkses.length];
            for (int i = 0; i < roadworkses.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(NorthantsRoadworks.COLUMN_ID, roadworkses[i].getId());
                cvs[i].put(NorthantsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT, roadworkses[i].getEffectOnRoadLayout());
                cvs[i].put(NorthantsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE, roadworkses[i].getRoadMaintenanceType());
                cvs[i].put(NorthantsRoadworks.COLUMN_COMMENT, roadworkses[i].getComment());
                cvs[i].put(NorthantsRoadworks.COLUMN_IMPACT_ON_TRAFFIC, roadworkses[i].getImpactOnTraffic());
                cvs[i].put(NorthantsRoadworks.COLUMN_LATITUDE, roadworkses[i].getLatitude());
                cvs[i].put(NorthantsRoadworks.COLUMN_LONGITUDE, roadworkses[i].getLongitude());
                cvs[i].put(NorthantsRoadworks.COLUMN_VALIDITY_STATUS, roadworkses[i].getValidityStatus());
                cvs[i].put(NorthantsRoadworks.COLUMN_OVERALL_START_TIME, roadworkses[i].getOverallStartTime());
                cvs[i].put(NorthantsRoadworks.COLUMN_OVERALL_END_TIME, roadworkses[i].getOverallEndTime());
                cvs[i].put(NorthantsRoadworks.COLUMN_START_OF_PERIOD, roadworkses[i].getStartOfPeriod());
                cvs[i].put(NorthantsRoadworks.COLUMN_END_OF_PERIOD, roadworkses[i].getEndOfPeriod());
                cvs[i].put(NorthantsRoadworks.COLUMN_CIN_ID, roadworkses[i].getCinId());
                cvs[i].put(NorthantsRoadworks.COLUMN_CREATION_TIME, roadworkses[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(NorthantsProviderModule.ROADWORKS_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context, @NonNull TrafficFlow[] trafficFlows)
            throws RemoteException, OperationApplicationException {
        if (trafficFlows.length > 0) {
            ContentValues[] cvs = new ContentValues[trafficFlows.length];
            for (int i = 0; i < trafficFlows.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(NorthantsTrafficFlow.COLUMN_ID, trafficFlows[i].getId());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_TPEG_DIRECTION, trafficFlows[i].getTpegDirection());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_FROM_TYPE, trafficFlows[i].getFromType());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_FROM_DESCRIPTOR, trafficFlows[i].getFromDescriptor());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_FROM_LATITUDE, trafficFlows[i].getFromLatitude());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_FROM_LONGITUDE, trafficFlows[i].getFromLongitude());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_TO_TYPE, trafficFlows[i].getToType());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_TO_DESCRIPTOR, trafficFlows[i].getToDescriptor());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_TO_LATITUDE, trafficFlows[i].getToLatitude());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_TO_LONGITUDE, trafficFlows[i].getToLongitude());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_TIME, trafficFlows[i].getTime());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_VEHICLE_FLOW, trafficFlows[i].getVehicleFlow());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_CIN_ID, trafficFlows[i].getCinId());
                cvs[i].put(NorthantsTrafficFlow.COLUMN_CREATION_TIME, trafficFlows[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(NorthantsProviderModule.TRAFFIC_FLOW_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull TrafficTravelTime[] trafficTravelTimes)
            throws RemoteException, OperationApplicationException {
        if (trafficTravelTimes.length > 0) {
            ContentValues[] cvs = new ContentValues[trafficTravelTimes.length];
            for (int i = 0; i < trafficTravelTimes.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_ID, trafficTravelTimes[i].getId());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_ID, trafficTravelTimes[i].getId());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_TPEG_DIRECTION, trafficTravelTimes[i].getTpegDirection());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_FROM_TYPE, trafficTravelTimes[i].getFromType());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR, trafficTravelTimes[i].getFromDescriptor());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_FROM_LATITUDE, trafficTravelTimes[i].getFromLatitude());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_FROM_LONGITUDE, trafficTravelTimes[i].getFromLongitude());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_TO_TYPE, trafficTravelTimes[i].getToType());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_TO_DESCRIPTOR, trafficTravelTimes[i].getToDescriptor());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_TO_LATITUDE, trafficTravelTimes[i].getToLatitude());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_TO_LONGITUDE, trafficTravelTimes[i].getToLongitude());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_TIME, trafficTravelTimes[i].getTime());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_TRAVEL_TIME, trafficTravelTimes[i].getTravelTime());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME, trafficTravelTimes[i].getFreeFlowTravelTime());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED, trafficTravelTimes[i].getFreeFlowSpeed());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_CIN_ID, trafficTravelTimes[i].getCinId());
                cvs[i].put(NorthantsTrafficTravelTime.COLUMN_CREATION_TIME, trafficTravelTimes[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(NorthantsProviderModule.TRAFFIC_TRAVEL_TIME_URI, cvs);
        }
    }

    public static void insertIntoProvider(@NonNull Context context,
                                          @NonNull VariableMessageSign[] variableMessageSigns)
            throws RemoteException, OperationApplicationException {
        if (variableMessageSigns.length > 0) {
            ContentValues[] cvs = new ContentValues[variableMessageSigns.length];
            for (int i = 0; i < variableMessageSigns.length; i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_LOCATION_ID, variableMessageSigns[i].getLocationId());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_LOCATION_ID, variableMessageSigns[i].getLocationId());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_DESCRIPTION, variableMessageSigns[i].getDescription());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_VMS_TYPE, variableMessageSigns[i].getVmsType());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_LATITUDE, variableMessageSigns[i].getLatitude());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_LONGITUDE, variableMessageSigns[i].getLongitude());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS, variableMessageSigns[i].getNumberOfCharacters());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_NUMBER_OF_ROWS, variableMessageSigns[i].getNumberOfRows());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_VMS_LEGENDS, variableMessageSigns[i].getLegendAsString());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_CIN_ID, variableMessageSigns[i].getCinId());
                cvs[i].put(NorthantsVariableMessageSign.COLUMN_CREATION_TIME, variableMessageSigns[i].getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(NorthantsProviderModule.VARIABLE_MESSAGE_SIGN_URI, cvs);
        }
    }

    public static Cursor getCarParkCursor(@NonNull Context context) {
        return context.getContentResolver().query(NorthantsProviderModule.CAR_PARK_URI,
                new String[]{"*"}, null, null, NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY);
    }

    public static Cursor getCarParkCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(NorthantsProviderModule.CAR_PARK_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestCarParkCursor(@NonNull Context context) {
        return context.getContentResolver().query(NorthantsProviderModule.LATEST_CAR_PARK_URI,
                new String[]{"*"}, null, null, NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY);
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

    public static Cursor getRoadworksCursor(@NonNull Context context) {
        return context.getContentResolver().query(NorthantsProviderModule.ROADWORKS_URI,
                new String[]{"*"}, null, null, NorthantsRoadworks.COLUMN_ID);
    }

    public static Cursor getRoadworksCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(NorthantsProviderModule.ROADWORKS_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestRoadworksCursor(@NonNull Context context) {
        return context.getContentResolver().query(NorthantsProviderModule.LATEST_ROADWORKS_URI,
                new String[]{"*"}, null, null, NorthantsRoadworks.COLUMN_ID);
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
        return context.getContentResolver().query(NorthantsProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, null, null, NorthantsTrafficFlow.COLUMN_ID);
    }

    public static Cursor getTrafficFlowCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(NorthantsProviderModule.TRAFFIC_FLOW_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficFlowCursor(@NonNull Context context) {
        return context.getContentResolver().query(NorthantsProviderModule.LATEST_TRAFFIC_FLOW_URI,
                new String[]{"*"}, null, null, NorthantsTrafficFlow.COLUMN_ID);
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

    public static Cursor getTrafficTravelTimeCursor(@NonNull Context context) {
        return context.getContentResolver().query(NorthantsProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                new String[]{"*"}, null, null, NorthantsTrafficTravelTime.COLUMN_ID);
    }

    public static Cursor getTrafficTravelTimeCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(NorthantsProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficTravelTimeCursor(@NonNull Context context) {
        return context.getContentResolver().query(
                NorthantsProviderModule.LATEST_TRAFFIC_TRAVEL_TIME_URI, new String[]{"*"},
                null, null, NorthantsTrafficTravelTime.COLUMN_ID);
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
        return context.getContentResolver().query(NorthantsProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, null, null, NorthantsVariableMessageSign.COLUMN_LOCATION_ID);
    }

    public static Cursor getVariableMessageSignCursor(@NonNull Context context,
                                                      long oldest, long newest) {
        return context.getContentResolver().query(NorthantsProviderModule.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, CREATION_INTERVAL_SELECTION, interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestVariableMessageSignCursor(@NonNull Context context) {
        return context.getContentResolver().query(
                NorthantsProviderModule.LATEST_VARIABLE_MESSAGE_SIGN_URI,
                new String[]{"*"}, null, null, NorthantsVariableMessageSign.COLUMN_LOCATION_ID);
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
                contentResolver.delete(NorthantsProviderModule.CAR_PARK_URI, null, null);
                break;
            case DATA_TYPE_ROADWORKS:
                contentResolver.delete(NorthantsProviderModule.ROADWORKS_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(NorthantsProviderModule.TRAFFIC_FLOW_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC_TRAVEL_TIME:
                contentResolver.delete(NorthantsProviderModule.TRAFFIC_TRAVEL_TIME_URI, null, null);
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(NorthantsProviderModule.VARIABLE_MESSAGE_SIGN_URI, null, null);
                break;
        }
    }

    public static void deleteFromProviderBeforeTime(@NonNull Context context, @DataType int dataType,
                                                    long creationTime) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_CAR_PARK:
                contentResolver.delete(NorthantsProviderModule.CAR_PARK_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_ROADWORKS:
                contentResolver.delete(NorthantsProviderModule.ROADWORKS_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_FLOW:
                contentResolver.delete(NorthantsProviderModule.TRAFFIC_FLOW_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC_TRAVEL_TIME:
                contentResolver.delete(NorthantsProviderModule.TRAFFIC_TRAVEL_TIME_URI,
                        CREATED_BEFORE, new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_VMS:
                contentResolver.delete(NorthantsProviderModule.VARIABLE_MESSAGE_SIGN_URI,
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
                            NorthantsCarPark.COLUMN_CAR_PARK_IDENTITY)));
                    carParks[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_LATITUDE)));
                    carParks[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_LONGITUDE)));
                    carParks[i].setOccupancy(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_OCCUPANCY)));
                    carParks[i].setOccupancyTrend(cursor.getString(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_OCCUPANCY_TREND)));
                    carParks[i].setTotalParkingCapacity(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_TOTAL_PARKING_CAPACITY)));
                    carParks[i].setFillRate(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_FILL_RATE)));
                    carParks[i].setExitRate(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_EXIT_RATE)));
                    carParks[i].setAlmostFullIncreasing(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_ALMOST_FULL_INCREASING)));
                    carParks[i].setAlmostFullDecreasing(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_ALMOST_FULL_DECREASING)));
                    carParks[i].setStatus(cursor.getString(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_STATUS)));
                    carParks[i].setStatusTime(cursor.getString(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_STATUS_TIME)));
                    carParks[i].setQueuingTime(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_QUEUING_TIME)));
                    carParks[i].setParkingAreaName(cursor.getString(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_PARKING_AREA_NAME)));
                    carParks[i].setEntranceFull(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_ENTRANCE_FULL)));
                    carParks[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_CIN_ID)));
                    carParks[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            NorthantsCarPark.COLUMN_CREATION_TIME)));
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

    public static Roadworks[] roadworksesFromCursor(Cursor cursor) {
        Roadworks[] roadworkses = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                roadworkses = new Roadworks[cursor.getCount()];
                for (int i = 0; i < roadworkses.length; i++) {
                    roadworkses[i] = new Roadworks();
                    roadworkses[i].setId(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_ID)));
                    roadworkses[i].setEffectOnRoadLayout(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_EFFECT_ON_ROAD_LAYOUT)));
                    roadworkses[i].setRoadMaintenanceType(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_ROAD_MAINTENANCE_TYPE)));
                    roadworkses[i].setComment(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_COMMENT)));
                    roadworkses[i].setImpactOnTraffic(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_IMPACT_ON_TRAFFIC)));
                    roadworkses[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_LATITUDE)));
                    roadworkses[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_LONGITUDE)));
                    roadworkses[i].setValidityStatus(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_VALIDITY_STATUS)));
                    roadworkses[i].setOverallStartTime(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_OVERALL_START_TIME)));
                    roadworkses[i].setOverallEndTime(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_OVERALL_END_TIME)));
                    roadworkses[i].setStartOfPeriod(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_START_OF_PERIOD)));
                    roadworkses[i].setEndOfPeriod(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_END_OF_PERIOD)));
                    roadworkses[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_CIN_ID)));
                    roadworkses[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            NorthantsRoadworks.COLUMN_CREATION_TIME)));
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
                            NorthantsTrafficFlow.COLUMN_ID)));
                    trafficFlows[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_TPEG_DIRECTION)));
                    trafficFlows[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_FROM_TYPE)));
                    trafficFlows[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_FROM_DESCRIPTOR)));
                    trafficFlows[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_FROM_LATITUDE)));
                    trafficFlows[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_FROM_LONGITUDE)));
                    trafficFlows[i].setToType(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_TO_TYPE)));
                    trafficFlows[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_TO_DESCRIPTOR)));
                    trafficFlows[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_TO_LATITUDE)));
                    trafficFlows[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_TO_LONGITUDE)));
                    trafficFlows[i].setTime(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_TIME)));
                    trafficFlows[i].setVehicleFlow(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_VEHICLE_FLOW)));
                    trafficFlows[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_CIN_ID)));
                    trafficFlows[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            NorthantsTrafficFlow.COLUMN_CREATION_TIME)));
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

    public static TrafficTravelTime[] trafficTravelTimesFromCursor(Cursor cursor) {
        TrafficTravelTime[] trafficTravelTimes = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficTravelTimes = new TrafficTravelTime[cursor.getCount()];
                for (int i = 0; i < trafficTravelTimes.length; i++) {
                    trafficTravelTimes[i] = new TrafficTravelTime();
                    trafficTravelTimes[i].setId(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_ID)));
                    trafficTravelTimes[i].setTpegDirection(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_TPEG_DIRECTION)));
                    trafficTravelTimes[i].setFromType(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_FROM_TYPE)));
                    trafficTravelTimes[i].setFromDescriptor(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_FROM_DESCRIPTOR)));
                    trafficTravelTimes[i].setFromLatitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_FROM_LATITUDE)));
                    trafficTravelTimes[i].setFromLongitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_FROM_LONGITUDE)));
                    trafficTravelTimes[i].setToType(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_TO_TYPE)));
                    trafficTravelTimes[i].setToDescriptor(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_TO_DESCRIPTOR)));
                    trafficTravelTimes[i].setToLatitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_TO_LATITUDE)));
                    trafficTravelTimes[i].setToLongitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_TO_LONGITUDE)));
                    trafficTravelTimes[i].setTime(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_TIME)));
                    trafficTravelTimes[i].setTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_TRAVEL_TIME)));
                    trafficTravelTimes[i].setFreeFlowTravelTime(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_TRAVEL_TIME)));
                    trafficTravelTimes[i].setFreeFlowSpeed(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_FREE_FLOW_SPEED)));
                    trafficTravelTimes[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_CIN_ID)));
                    trafficTravelTimes[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            NorthantsTrafficTravelTime.COLUMN_CREATION_TIME)));
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
                            NorthantsVariableMessageSign.COLUMN_LOCATION_ID)));
                    variableMessageSigns[i].setDescription(cursor.getString(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_DESCRIPTION)));
                    variableMessageSigns[i].setVmsType(cursor.getString(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_VMS_TYPE)));
                    variableMessageSigns[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_LATITUDE)));
                    variableMessageSigns[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_LONGITUDE)));
                    variableMessageSigns[i].setNumberOfCharacters(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS)));
                    variableMessageSigns[i].setNumberOfRows(cursor.getDouble(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_NUMBER_OF_ROWS)));
                    variableMessageSigns[i].setLegendFromString(cursor.getString(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_VMS_LEGENDS)));
                    variableMessageSigns[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_CIN_ID)));
                    variableMessageSigns[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            NorthantsVariableMessageSign.COLUMN_CREATION_TIME)));
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
