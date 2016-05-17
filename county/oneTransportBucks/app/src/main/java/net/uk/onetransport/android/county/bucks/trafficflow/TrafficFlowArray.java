package net.uk.onetransport.android.county.bucks.trafficflow;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;

public class TrafficFlowArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCTrafficFlowFeedImport/All";

    private TrafficFlow[] trafficFlows;
    private TrafficFlowArrayCallback trafficFlowArrayCallback;
    private int id;

    public TrafficFlowArray() {
    }

    public TrafficFlowArray(TrafficFlow[] trafficFlows) {
        this.trafficFlows = trafficFlows;
    }

    public static TrafficFlowArray getTrafficFlowArray(String aeId, String baseUrl,
                                                       String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new TrafficFlowArray(GSON.fromJson(content, TrafficFlow[].class));
    }

    public static void getTrafficFlowArrayAsync(String aeId, String baseUrl, String userName,
                                                String password,
                                                TrafficFlowArrayCallback trafficFlowArrayCallback,
                                                int id) {
        TrafficFlowArray trafficFlowArray = new TrafficFlowArray();
        trafficFlowArray.trafficFlowArrayCallback = trafficFlowArrayCallback;
        trafficFlowArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                trafficFlowArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            trafficFlowArrayCallback.onTrafficFlowArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                trafficFlows = GSON.fromJson(content, TrafficFlow[].class);
                trafficFlowArrayCallback.onTrafficFlowArrayReady(id, this);
            } catch (Exception e) {
                trafficFlowArrayCallback.onTrafficFlowArrayError(id, e);
            }
        }
    }

    // TODO Merge different JSON objects.
    public void insertIntoProvider(Context context) {
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

    public static void deleteFromProvider(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.delete(BucksProvider.TRAFFIC_FLOW_URI, null, null);
    }

    public TrafficFlow[] getTrafficFlows() {
        return trafficFlows;
    }
}
