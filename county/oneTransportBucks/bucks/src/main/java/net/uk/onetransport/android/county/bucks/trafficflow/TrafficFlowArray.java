package net.uk.onetransport.android.county.bucks.trafficflow;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.authentication.CredentialHelper;

public class TrafficFlowArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCTrafficFlowFeedImport/All";
    private static final String RETRIEVE_PATH_EXT = "BCCTrafficFlowExtensionFeedImport/All";

    private TrafficFlow[] trafficFlows;
    private TrafficFlowArrayCallback trafficFlowArrayCallback;
    private int id;

    public TrafficFlowArray() {
    }

    public TrafficFlowArray(TrafficFlow[] trafficFlows) {
        this.trafficFlows = trafficFlows;
    }

    public static TrafficFlowArray getTrafficFlowArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH_EXT,
                userName, password);
        String contentExt = contentInstance.getContent();
        TrafficFlowArray trafficFlowArray = new TrafficFlowArray(
                GSON.fromJson(content, TrafficFlow[].class));
        TrafficFlowArray trafficFlowArrayExt = new TrafficFlowArray(
                GSON.fromJson(contentExt, TrafficFlow[].class));
        merge(trafficFlowArray, trafficFlowArrayExt);
        return trafficFlowArray;
    }

    public static void getTrafficFlowArrayAsync(Context context,
                                                TrafficFlowArrayCallback trafficFlowArrayCallback, int id) {
        TrafficFlowArray trafficFlowArray = new TrafficFlowArray();
        trafficFlowArray.trafficFlowArrayCallback = trafficFlowArrayCallback;
        trafficFlowArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH, userName, password,
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

    public TrafficFlow[] getTrafficFlows() {
        return trafficFlows;
    }

    private static void merge(TrafficFlowArray tfa1, TrafficFlowArray tfa2) {
        for (TrafficFlow tf1 : tfa1.getTrafficFlows()) {
            for (TrafficFlow tf2 : tfa2.getTrafficFlows()) {
                if (tf1.getLocationReference().equals(tf2.getLocationReference())) {
                    if (tf2.getQueuePresent() != null) {
                        tf1.setQueuePresent(tf2.getQueuePresent());
                    }
                    if (tf2.getQueueSeverity() != null) {
                        tf1.setQueueSeverity(tf2.getQueueSeverity());
                    }
                }
            }
        }
    }
}
