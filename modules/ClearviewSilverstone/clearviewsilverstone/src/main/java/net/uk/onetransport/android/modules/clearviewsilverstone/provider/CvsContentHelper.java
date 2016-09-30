package net.uk.onetransport.android.modules.clearviewsilverstone.provider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
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
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (Device device : devices) {
                ContentProviderOperation operation = ContentProviderOperation
                        .newInsert(CvsProviderModule.DEVICE_URI)
                        .withValue(ClearviewSilverstoneDevice.COLUMN_SENSOR_ID, device.getSensorId())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_TITLE, device.getTitle())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_DESCRIPTION, device.getDescription())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_TYPE, device.getType())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_LATITUDE, device.getLatitude())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_LONGITUDE, device.getLongitude())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_CHANGED, device.getChanged())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_CIN_ID, device.getCinId())
                        .withValue(ClearviewSilverstoneDevice.COLUMN_CREATION_TIME,
                                device.getCreationTime())
                        .withYieldAllowed(true)
                        .build();
                operationList.add(operation);
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(CvsProviderModule.AUTHORITY, operationList);
        }
    }

    public static void insertTrafficGroupsIntoProvider(@NonNull Context context,
                                                       @NonNull ArrayList<TrafficGroup> trafficGroups)
            throws RemoteException, OperationApplicationException {
        if (trafficGroups.size() > 0) {
            ArrayList<ContentProviderOperation> operationList = new ArrayList<>();
            for (TrafficGroup trafficGroup : trafficGroups) {
                int sensorId = trafficGroup.getSensorId();
                Traffic[] traffic = trafficGroup.getTraffic();
                for (Traffic trafficItem : traffic) {
                    ContentProviderOperation operation = ContentProviderOperation
                            .newInsert(CvsProviderModule.TRAFFIC_URI)
                            .withValue(ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID, sensorId)
                            .withValue(ClearviewSilverstoneTraffic.COLUMN_DIRECTION, trafficItem.getDirection())
                            .withValue(ClearviewSilverstoneTraffic.COLUMN_LANE, trafficItem.getTime())
                            .withValue(ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP, trafficItem.getTime())
                            .withValue(ClearviewSilverstoneTraffic.COLUMN_CIN_ID, trafficGroup.getCinId())
                            .withValue(ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME,
                                    trafficGroup.getCreationTime())
                            .withYieldAllowed(true)
                            .build();
                    operationList.add(operation);
                }
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.applyBatch(CvsProviderModule.AUTHORITY, operationList);
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
