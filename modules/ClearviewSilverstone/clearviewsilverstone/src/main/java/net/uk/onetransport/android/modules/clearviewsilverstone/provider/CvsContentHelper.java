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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneDevice;
import static net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract.ClearviewSilverstoneTraffic;

public class CvsContentHelper {

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

    public static Cursor getDevices(@NonNull Context context) {
        return context.getContentResolver().query(CvsProviderModule.DEVICE_URI,
                new String[]{"*"}, null, null, ClearviewSilverstoneDevice.COLUMN_SENSOR_ID);
    }

    public static Cursor getTraffic(@NonNull Context context) {
        return context.getContentResolver().query(CvsProviderModule.TRAFFIC_URI,
                new String[]{"*"}, null, null,
                ClearviewSilverstoneTraffic.COLUMN_SENSOR_ID + ","
                        + ClearviewSilverstoneTraffic.COLUMN_TIMESTAMP + " DESC");
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
}
