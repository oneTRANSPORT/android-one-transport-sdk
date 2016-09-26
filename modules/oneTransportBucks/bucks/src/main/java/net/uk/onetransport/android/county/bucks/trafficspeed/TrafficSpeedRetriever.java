package net.uk.onetransport.android.county.bucks.trafficspeed;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.Retriever;

public class TrafficSpeedRetriever extends Retriever<TrafficSpeed> implements TrafficSpeedParams {

    public TrafficSpeedRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override // TODO    Needs Unique index title, cinId?
    protected TrafficSpeed[] fromJson(String content, String cinId, Long creationTime) {
        TrafficSpeed[] trafficSpeeds = GSON.fromJson(content, TrafficSpeed[].class);
        for (TrafficSpeed trafficSpeed : trafficSpeeds) {
            trafficSpeed.setCinId(cinId);
            trafficSpeed.setCreationTime(creationTime);
        }
        return trafficSpeeds;
    }
}