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
package net.uk.onetransport.android.modules.clearviewsilverstone.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.Traffic;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroup;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficItem;
import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;
import net.uk.onetransport.android.modules.common.provider.CommonContentHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneDevice;
import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneTraffic;

public class CvsContentHelper extends CommonContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATA_TYPE_DEVICE, DATA_TYPE_TRAFFIC})
    public @interface DataType {
    }

    public static final int DATA_TYPE_DEVICE = 1;
    public static final int DATA_TYPE_TRAFFIC = 2;

    public static void insertDevicesIntoProvider(@NonNull Context context,
                                                 @NonNull ArrayList<Device> devices)
            throws RemoteException, OperationApplicationException {
        if (devices.size() > 0) {
            ContentValues[] cvs = new ContentValues[devices.size()];
            for (int i = 0; i < devices.size(); i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_SENSOR_ID, devices.get(i).getSensorId());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_TITLE, devices.get(i).getTitle());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_DESCRIPTION, devices.get(i).getDescription());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_TYPE, devices.get(i).getType());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_LATITUDE, devices.get(i).getLatitude());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_LONGITUDE, devices.get(i).getLongitude());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_CHANGED, devices.get(i).getChanged());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_CIN_ID, devices.get(i).getCinId());
                cvs[i].put(ClearviewSilverstoneDevice.COLUMN_CREATION_TIME, devices.get(i).getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(CvsProviderModule.DEVICE_URI, cvs);
        }
    }

    public static void insertTrafficGroupsIntoProvider(@NonNull Context context,
                                                       @NonNull ArrayList<TrafficGroup> trafficGroups)
            throws RemoteException, OperationApplicationException {
        if (trafficGroups.size() > 0) {
            ArrayList<ContentValues> cvs = new ArrayList<>();
            for (int i = 0; i < trafficGroups.size(); i++) {
                int sensorId = trafficGroups.get(i).getSensorId();
                Traffic[] traffic = trafficGroups.get(i).getTraffic();
                for (int j = 0; j < traffic.length; j++) {
                    ContentValues cv = new ContentValues();
                    cvs.add(cv);
                    cv.put(ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID, sensorId);
                    cv.put(ClearviewSilverstoneTraffic.COLUMN_DIRECTION, traffic[j].getDirection());
                    cv.put(ClearviewSilverstoneTraffic.COLUMN_LANE, traffic[j].getTime());
                    cv.put(ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP, traffic[j].getTime());
                    cv.put(ClearviewSilverstoneTraffic.COLUMN_CIN_ID, trafficGroups.get(i).getCinId());
                    cv.put(ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME, trafficGroups.get(i).getCreationTime());
                }
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(CvsProviderModule.TRAFFIC_URI,
                    cvs.toArray(new ContentValues[cvs.size()]));
        }
    }

    public static Cursor getDeviceCursor(@NonNull Context context) {
        return context.getContentResolver().query(CvsProviderModule.DEVICE_URI,
                new String[]{"*"}, null, null, ClearviewSilverstoneDevice.COLUMN_SENSOR_ID);
    }

    public static Cursor getDeviceCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(CvsProviderModule.DEVICE_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestDeviceCursor(@NonNull Context context) {
        return context.getContentResolver().query(CvsProviderModule.LATEST_DEVICE_URI,
                new String[]{"*"}, null, null,
                ClearviewSilverstoneDevice.COLUMN_SENSOR_ID);
    }

    public static Device[] getDevices(@NonNull Context context) {
        return devicesFromCursor(getDeviceCursor(context));
    }

    public static Device[] getDevices(@NonNull Context context, long oldest, long newest) {
        return devicesFromCursor(getDeviceCursor(context, oldest, newest));
    }

    public static Device[] getLatestDevices(@NonNull Context context) {
        return devicesFromCursor(getLatestDeviceCursor(context));
    }

    public static Cursor getTrafficItemCursor(@NonNull Context context) {
        return context.getContentResolver().query(CvsProviderModule.TRAFFIC_URI,
                new String[]{"*"}, null, null,
                ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + ","
                        + ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP + " DESC");
    }

    public static Cursor getTrafficItemCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(CvsProviderModule.TRAFFIC_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTrafficItemCursor(@NonNull Context context) {
        return context.getContentResolver().query(CvsProviderModule.LATEST_TRAFFIC_URI,
                new String[]{"*"}, null, null,
                ClearviewSilverstoneDevice.COLUMN_SENSOR_ID);
    }

    public static TrafficItem[] getTrafficItems(@NonNull Context context) {
        return trafficItemsFromCursor(getTrafficItemCursor(context));
    }

    public static TrafficItem[] getTrafficItems(@NonNull Context context, long oldest, long newest) {
        return trafficItemsFromCursor(getTrafficItemCursor(context, oldest, newest));
    }

    public static TrafficItem[] getLatestTrafficItems(@NonNull Context context) {
        return trafficItemsFromCursor(getLatestTrafficItemCursor(context));
    }

    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_DEVICE:
                contentResolver.delete(CvsProviderModule.DEVICE_URI, null, null);
                break;
            case DATA_TYPE_TRAFFIC:
                contentResolver.delete(CvsProviderModule.TRAFFIC_URI, null, null);
                break;
        }
    }

    public static void deleteFromProviderBeforeTime(@NonNull Context context, @DataType int dataType,
                                                    long creationTime) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_DEVICE:
                contentResolver.delete(CvsProviderModule.DEVICE_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAFFIC:
                contentResolver.delete(CvsProviderModule.TRAFFIC_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
        }
    }

    public static Device[] devicesFromCursor(Cursor cursor) {
        Device[] devices = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                devices = new Device[cursor.getCount()];
                for (int i = 0; i < devices.length; i++) {
                    devices[i] = new Device();
                    devices[i].setSensorId(cursor.getInt(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_SENSOR_ID)));
                    devices[i].setTitle(cursor.getString(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_TITLE)));
                    devices[i].setDescription(cursor.getString(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_DESCRIPTION)));
                    devices[i].setType(cursor.getString(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_TYPE)));
                    devices[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_LATITUDE)));
                    devices[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_LONGITUDE)));
                    devices[i].setChanged(cursor.getString(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_CHANGED)));
                    devices[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_CIN_ID)));
                    devices[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            ClearviewSilverstoneDevice.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (devices == null) {
            return new Device[0];
        }
        return devices;
    }

    public static TrafficItem[] trafficItemsFromCursor(Cursor cursor) {
        TrafficItem[] trafficItems = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                trafficItems = new TrafficItem[cursor.getCount()];
                for (int i = 0; i < trafficItems.length; i++) {
                    trafficItems[i] = new TrafficItem();
                    trafficItems[i].setSensorId(cursor.getInt(cursor.getColumnIndex(
                            ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID)));
                    trafficItems[i].setDirection(cursor.getInt(cursor.getColumnIndex(
                            ClearviewSilverstoneTraffic.COLUMN_DIRECTION)) == 1);
                    trafficItems[i].setLane(cursor.getInt(cursor.getColumnIndex(
                            ClearviewSilverstoneTraffic.COLUMN_LANE)));
                    trafficItems[i].setTime(cursor.getString(cursor.getColumnIndex(
                            ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP)));
                    trafficItems[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            ClearviewSilverstoneTraffic.COLUMN_CIN_ID)));
                    trafficItems[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (trafficItems == null) {
            return new TrafficItem[0];
        }
        return trafficItems;
    }
}
