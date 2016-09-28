package net.uk.onetransport.android.county.northants.trafficflow;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.Retriever;

public class TrafficFlowRetriever extends Retriever<TrafficFlow> implements TrafficFlowParams {

    public TrafficFlowRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected TrafficFlow[] fromJson(String content, String cinId, Long creationTime) {
        TrafficFlow[] trafficFlows = GSON.fromJson(content, TrafficFlow[].class);
        for (TrafficFlow trafficFlow : trafficFlows) {
            trafficFlow.setCinId(cinId);
            trafficFlow.setCreationTime(creationTime);
        }
        return trafficFlows;
    }
}
