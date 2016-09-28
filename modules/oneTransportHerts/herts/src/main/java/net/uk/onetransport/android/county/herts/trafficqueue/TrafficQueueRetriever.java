package net.uk.onetransport.android.county.herts.trafficqueue;

import android.content.Context;

import net.uk.onetransport.android.county.herts.generic.Retriever;

public class TrafficQueueRetriever extends Retriever<TrafficQueue> implements TrafficQueueParams {

    public TrafficQueueRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override // TODO    Needs Unique index title, cinId?
    protected TrafficQueue[] fromJson(String content, String cinId, Long creationTime) {
        TrafficQueue[] trafficQueues = GSON.fromJson(content, TrafficQueue[].class);
        for (TrafficQueue trafficQueue : trafficQueues) {
            trafficQueue.setCinId(cinId);
            trafficQueue.setCreationTime(creationTime);
        }
        return trafficQueues;
    }
}
