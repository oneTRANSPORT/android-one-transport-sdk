package net.uk.onetransport.android.county.bucks.trafficflow;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.authentication.CredentialHelper;

import java.util.ArrayList;

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
        internalMerge(trafficFlowArray);
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

    public void setTrafficFlows(TrafficFlow[] trafficFlows) {
        this.trafficFlows = trafficFlows;
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
                    if (tf2.getCongestionPercent() != null) {
                        tf1.setCongestionPercent(tf2.getCongestionPercent());
                    }
                    if (tf2.getCurrentFlow() != null) {
                        tf1.setCurrentFlow(tf2.getCurrentFlow());
                    }
                    if (tf2.getAverageSpeed() != null) {
                        tf1.setAverageSpeed(tf2.getAverageSpeed());
                    }
                    if (tf2.getLinkStatus() != null) {
                        tf1.setLinkStatus(tf2.getLinkStatus());
                    }
                    if (tf2.getLinkStatusType() != null) {
                        tf1.setLinkStatusType(tf2.getLinkStatusType());
                    }
                    if (tf2.getLinkTravelTime() != null) {
                        tf1.setLinkTravelTime(tf2.getLinkTravelTime());
                    }
                }
            }
        }
    }

    private static void internalMerge(TrafficFlowArray tfa) {
        ArrayList<TrafficFlow> tfList = new ArrayList<>();
        for (TrafficFlow tf1 : tfa.getTrafficFlows()) {
            String locationReference = tf1.getLocationReference();
            int found = -1;
            for (int i = 0; i < tfList.size() && found < 0; i++) {
                if (tfList.get(i).getLocationReference().equals(locationReference)) {
                    found = i;
                }
            }
            if (found == -1) {
                tfList.add(tf1);
            } else {
                TrafficFlow tf2 = tfList.get(found);
                if (tf1.getQueueSeverity() != null) {
                    tf2.setQueueSeverity(tf1.getQueueSeverity());
                }
                if (tf1.getQueuePresent() != null) {
                    tf2.setQueuePresent(tf1.getQueuePresent());
                }
                if (tf1.getAverageSpeed() != null) {
                    tf2.setAverageSpeed(tf1.getAverageSpeed());
                }
                if (tf1.getAverageVehicleSpeed() != null) {
                    tf2.setAverageVehicleSpeed(tf1.getAverageVehicleSpeed());
                }
                if (tf1.getCongestionPercent() != null) {
                    tf2.setCongestionPercent(tf1.getCongestionPercent());
                }
                if (tf1.getCurrentFlow() != null) {
                    tf2.setCurrentFlow(tf1.getCurrentFlow());
                }
                if (tf1.getFreeFlowSpeed() != null) {
                    tf2.setFreeFlowSpeed(tf1.getFreeFlowSpeed());
                }
                if (tf1.getFreeFlowTravelTime() != null) {
                    tf2.setFreeFlowTravelTime(tf1.getFreeFlowTravelTime());
                }
                if (tf1.getLinkStatus() != null) {
                    tf2.setLinkStatus(tf1.getLinkStatus());
                }
                if (tf1.getLinkStatusType() != null) {
                    tf2.setLinkStatusType(tf1.getLinkStatusType());
                }
                if (tf1.getOccupancy() != null) {
                    tf2.setOccupancy(tf1.getOccupancy());
                }
                if (tf1.getLinkTravelTime() != null) {
                    tf2.setLinkTravelTime(tf1.getLinkTravelTime());
                }
                if (tf1.getVehicleFlow() != null) {
                    tf2.setVehicleFlow(tf1.getVehicleFlow());
                }
            }
        }
        tfa.setTrafficFlows(tfList.toArray(new TrafficFlow[tfList.size()]));
    }
}