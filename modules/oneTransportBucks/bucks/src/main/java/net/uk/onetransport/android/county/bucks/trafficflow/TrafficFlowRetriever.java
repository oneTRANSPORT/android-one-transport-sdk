package net.uk.onetransport.android.county.bucks.trafficflow;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.events.Event;
import net.uk.onetransport.android.county.bucks.events.EventParams;
import net.uk.onetransport.android.county.bucks.generic.Retriever;

public class TrafficFlowRetriever extends Retriever<TrafficFlow> implements TrafficFlowParams {

    public TrafficFlowRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override // TODO    Needs Unique index title, cinId?
    protected TrafficFlow[] fromJson(String content, String cinId, Long creationTime) {
        TrafficFlow[] trafficFlows = GSON.fromJson(content, TrafficFlow[].class);
        for (TrafficFlow trafficFlow : trafficFlows) {
            trafficFlow.setCinId(cinId);
            trafficFlow.setCreationTime(creationTime);
        }
        return trafficFlows;
    }
}
