package net.uk.onetransport.android.county.bucks.trafficflow;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.locations.TrafficFlow;

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
            String content = ((ContentInstance) resource).getContent();
            trafficFlows = GSON.fromJson(content, TrafficFlow[].class);
            trafficFlowArrayCallback.onTrafficFlowArrayReady(id, this);
        }
    }

    public TrafficFlow[] getTrafficFlows() {
        return trafficFlows;
    }
}
