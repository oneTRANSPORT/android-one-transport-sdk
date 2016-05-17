package net.uk.onetransport.android.county.bucks.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.IntDef;

import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.locations.PredefinedLinkLocation;
import net.uk.onetransport.android.county.bucks.locations.PredefinedSectionLocation;
import net.uk.onetransport.android.county.bucks.locations.PredefinedTrLocation;
import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocation;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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

    public static void insertIntoProvider(Context context, PredefinedLinkLocation[] predefinedLinkLocations) {
        if (predefinedLinkLocations != null && predefinedLinkLocations.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (PredefinedLinkLocation predefinedLinkLocation : predefinedLinkLocations) {
                values.clear();
                values.put(BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                        predefinedLinkLocation.getLocationId());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                        predefinedLinkLocation.getToLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                        predefinedLinkLocation.getToLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                        predefinedLinkLocation.getFromLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                        predefinedLinkLocation.getFromLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                        predefinedLinkLocation.getToDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                        predefinedLinkLocation.getFromDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION,
                        predefinedLinkLocation.getTpegDirection());
                contentResolver.insert(BucksProvider.SEGMENT_LOCATION_URI, values);
            }
        }
    }

    public static void insertIntoProvider(Context context,
                                          PredefinedSectionLocation[] predefinedSectionLocations) {
        if (predefinedSectionLocations != null && predefinedSectionLocations.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (PredefinedSectionLocation predefinedSectionLocation : predefinedSectionLocations) {
                values.clear();
                values.put(BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                        predefinedSectionLocation.getLocationId());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                        predefinedSectionLocation.getToLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                        predefinedSectionLocation.getToLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                        predefinedSectionLocation.getFromLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                        predefinedSectionLocation.getFromLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                        predefinedSectionLocation.getToDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                        predefinedSectionLocation.getFromDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION,
                        predefinedSectionLocation.getTpegDirection());
                contentResolver.insert(BucksProvider.SEGMENT_LOCATION_URI, values);
            }
        }
    }

    public static void insertIntoProvider(Context context, PredefinedTrLocation[] predefinedTrLocations) {
        if (predefinedTrLocations != null && predefinedTrLocations.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (PredefinedTrLocation predefinedTrLocation : predefinedTrLocations) {
                values.clear();
                values.put(BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                        predefinedTrLocation.getLocationId());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                        predefinedTrLocation.getToLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                        predefinedTrLocation.getToLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                        predefinedTrLocation.getFromLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                        predefinedTrLocation.getFromLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                        predefinedTrLocation.getToDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                        predefinedTrLocation.getFromDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION,
                        predefinedTrLocation.getTpegDirection());
                contentResolver.insert(BucksProvider.SEGMENT_LOCATION_URI, values);
            }
        }
    }

    public static void insertIntoProvider(Context context, PredefinedVmsLocation[] predefinedVmsLocations) {
        if (predefinedVmsLocations != null && predefinedVmsLocations.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (PredefinedVmsLocation predefinedVmsLocation : predefinedVmsLocations) {
                values.clear();
                values.put(BucksContract.VmsLocation.COLUMN_NAME,
                        predefinedVmsLocation.getName());
                values.put(BucksContract.VmsLocation.COLUMN_LOCATION_ID,
                        predefinedVmsLocation.getLocationId());
                values.put(BucksContract.VmsLocation.COLUMN_LATITUDE,
                        predefinedVmsLocation.getLatitude());
                values.put(BucksContract.VmsLocation.COLUMN_LONGITUDE,
                        predefinedVmsLocation.getLongitude());
                values.put(BucksContract.VmsLocation.COLUMN_DESCRIPTOR,
                        predefinedVmsLocation.getDescriptor());
                values.put(BucksContract.VmsLocation.COLUMN_TPEG_DIRECTION,
                        predefinedVmsLocation.getTpegDirection());
                contentResolver.insert(BucksProvider.VMS_LOCATION_URI, values);
            }
        }
    }

    public static void insertIntoProvider(Context context, CarPark[] carParks) {
        if (carParks != null && carParks.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (CarPark carPark : carParks) {
                values.clear();
                values.put(BucksContract.CarPark.COLUMN_RADIUS, carPark.getRadius());
                values.put(BucksContract.CarPark.COLUMN_LATITUDE, carPark.getLatitude());
                values.put(BucksContract.CarPark.COLUMN_LONGITUDE, carPark.getLongitude());
                values.put(BucksContract.CarPark.COLUMN_ENTRANCE_FULL, carPark.getEntranceFull());
                values.put(BucksContract.CarPark.COLUMN_FULL_INCREASING, carPark.getFullIncreasing());
                values.put(BucksContract.CarPark.COLUMN_FULL_DECREASING, carPark.getFullDecreasing());
                values.put(BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY,
                        carPark.getCarParkIdentity());
                values.put(BucksContract.CarPark.COLUMN_TOTAL_PARKING_CAPACITY,
                        carPark.getTotalParkingCapacity());
                values.put(BucksContract.CarPark.COLUMN_ALMOST_FULL_INCREASING,
                        carPark.getAlmostFullIncreasing());
                values.put(BucksContract.CarPark.COLUMN_ALMOST_FULL_DECREASING,
                        carPark.getAlmostFullDecreasing());
                contentResolver.insert(BucksProvider.CAR_PARK_URI, values);
            }
        }
    }

    // TODO Merge different JSON objects.
    public static void insertIntoProvider(Context context, TrafficFlow[] trafficFlows) {
        if (trafficFlows != null && trafficFlows.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (TrafficFlow trafficFlow : trafficFlows) {
                values.clear();
                values.put(BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE,
                        trafficFlow.getLocationReference());
                values.put(BucksContract.TrafficFlow.COLUMN_VEHICLE_FLOW,
                        trafficFlow.getVehicleFlow());
                values.put(BucksContract.TrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED,
                        trafficFlow.getAverageVehicleSpeed());
                values.put(BucksContract.TrafficFlow.COLUMN_TRAVEL_TIME, trafficFlow.getTravelTime());
                values.put(BucksContract.TrafficFlow.COLUMN_FREE_FLOW_SPEED,
                        trafficFlow.getFreeFlowSpeed());
                values.put(BucksContract.TrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME,
                        trafficFlow.getFreeFlowTravelTime());
                contentResolver.insert(BucksProvider.TRAFFIC_FLOW_URI, values);
            }
        }
    }

    public static void insertIntoProvider(Context context, VariableMessageSign[] variableMessageSigns) {
        if (variableMessageSigns != null && variableMessageSigns.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            StringBuffer buffer = new StringBuffer();
            for (VariableMessageSign variableMessageSign : variableMessageSigns) {
                values.clear();
                values.put(BucksContract.VariableMessageSign.COLUMN_LOCATION_REFERENCE,
                        variableMessageSign.getLocationReference());
                values.put(BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS,
                        variableMessageSign.getNumberOfCharacters());
                values.put(BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_ROWS,
                        variableMessageSign.getNumberOfRows());
                buffer.delete(0, buffer.length());
                String[] vmsLegends = variableMessageSign.getVmsLegends();
                for (int i = 0; i < vmsLegends.length; i++) {
                    buffer.append(vmsLegends[i]);
                    if (i < vmsLegends.length - 1) {
                        buffer.append("|");
                    }
                }
                values.put(BucksContract.VariableMessageSign.COLUMN_VMS_LEGENDS, buffer.toString());
                values.put(BucksContract.VariableMessageSign.COLUMN_VMS_TYPE,
                        variableMessageSign.getVmsType());
                contentResolver.insert(BucksProvider.VARIABLE_MESSAGE_SIGN_URI, values);
            }
        }
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
