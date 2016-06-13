package net.uk.onetransport.android.county.bucks.trafficflow;

import android.content.Context;
import android.support.annotation.NonNull;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.authentication.CredentialHelper;

public class TrafficFlowArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH_LINK = "FeedImportMerger/Link/All";
    private static final String RETRIEVE_PATH_TR = "FeedImportMerger/Tr/All";
    private static final String RETRIEVE_PATH_SECTION = "FeedImportMerger/Section/All";

    private TrafficFlow[] trafficFlows;
    private TrafficFlowArrayCallback trafficFlowArrayCallback;
    private int id;
    private int completed = 0;
    private ContentInstance[] contentInstances = new ContentInstance[3];

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
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                RETRIEVE_PATH_LINK, userName, password);
        String content = contentInstance.getContent();
        TrafficFlow[] trafficFlowsLink = GSON.fromJson(content, TrafficFlow[].class);
        contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH_TR,
                userName, password);
        content = contentInstance.getContent();
        TrafficFlow[] trafficFlowsTr = GSON.fromJson(content, TrafficFlow[].class);
        contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH_SECTION,
                userName, password);
        content = contentInstance.getContent();
        TrafficFlow[] trafficFlowsSection = GSON.fromJson(content, TrafficFlow[].class);
        TrafficFlow[] trafficFlowsAll = mergeTrafficFlows(trafficFlowsLink, trafficFlowsTr, trafficFlowsSection);
        return new TrafficFlowArray(trafficFlowsAll);
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
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH_LINK, userName, password,
                trafficFlowArray);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH_TR, userName, password,
                trafficFlowArray);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH_SECTION, userName, password,
                trafficFlowArray);
    }

    @Override
    public synchronized void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            trafficFlowArrayCallback.onTrafficFlowArrayError(id, throwable);
        } else {
            contentInstances[completed++] = (ContentInstance) resource;
            if (completed == 3) {
                try {
                    TrafficFlow[][] trafficFlowsAll = new TrafficFlow[3][];
                    for (int i = 0; i < 3; i++) {
                        String content = contentInstances[i].getContent();
                        trafficFlowsAll[i] = GSON.fromJson(content, TrafficFlow[].class);
                    }
                    trafficFlows = mergeTrafficFlows(trafficFlowsAll[0], trafficFlowsAll[1], trafficFlowsAll[2]);
                    trafficFlowArrayCallback.onTrafficFlowArrayReady(id, this);
                } catch (Exception e) {
                    trafficFlowArrayCallback.onTrafficFlowArrayError(id, e);
                }
            }
        }
    }

    public TrafficFlow[] getTrafficFlows() {
        return trafficFlows;
    }

    public void setTrafficFlows(TrafficFlow[] trafficFlows) {
        this.trafficFlows = trafficFlows;
    }

    @NonNull
    private static TrafficFlow[] mergeTrafficFlows(TrafficFlow[] trafficFlowsLink,
                                                   TrafficFlow[] trafficFlowsTr, TrafficFlow[] trafficFlowsSection) {
        TrafficFlow[] trafficFlowsAll = new TrafficFlow[trafficFlowsLink.length + trafficFlowsTr.length
                + trafficFlowsSection.length];
        System.arraycopy(trafficFlowsLink, 0, trafficFlowsAll, 0, trafficFlowsLink.length);
        System.arraycopy(trafficFlowsTr, 0, trafficFlowsAll, trafficFlowsLink.length, trafficFlowsTr.length);
        System.arraycopy(trafficFlowsSection, 0, trafficFlowsAll,
                trafficFlowsLink.length + trafficFlowsTr.length, trafficFlowsSection.length);
        return trafficFlowsAll;
    }
}