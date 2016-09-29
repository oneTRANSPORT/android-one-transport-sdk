package net.uk.onetransport.android.county.oxon.trafficscoot;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.Retriever;

public class TrafficScootRetriever extends Retriever<TrafficScoot> implements TrafficScootParams {

    public TrafficScootRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected TrafficScoot[] fromJson(String content, String cinId, Long creationTime) {
        TrafficScoot[] trafficScoots = GSON.fromJson(content, TrafficScoot[].class);
        for (TrafficScoot trafficScoot : trafficScoots) {
            trafficScoot.setCinId(cinId);
            trafficScoot.setCreationTime(creationTime);
        }
        return trafficScoots;
    }
}
