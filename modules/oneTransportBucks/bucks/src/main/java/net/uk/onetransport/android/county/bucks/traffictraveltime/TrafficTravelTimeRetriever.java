package net.uk.onetransport.android.county.bucks.traffictraveltime;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.Retriever;

public class TrafficTravelTimeRetriever extends Retriever<TrafficTravelTime>
        implements TrafficTravelTimeParams {

    public TrafficTravelTimeRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override // TODO    Needs Unique index title, cinId?
    protected TrafficTravelTime[] fromJson(String content, String cinId, Long creationTime) {
        TrafficTravelTime[] trafficTravelTimes = GSON.fromJson(content, TrafficTravelTime[].class);
        for (TrafficTravelTime trafficTravelTime : trafficTravelTimes) {
            trafficTravelTime.setCinId(cinId);
            trafficTravelTime.setCreationTime(creationTime);
        }
        return trafficTravelTimes;
    }
}
