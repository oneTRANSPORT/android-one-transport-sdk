package net.uk.onetransport.android.county.herts.trafficspeed;

import android.content.Context;

import net.uk.onetransport.android.county.herts.generic.RetrieverLoader;

public class TrafficSpeedRetrieverLoader extends RetrieverLoader<TrafficSpeed> {

    public TrafficSpeedRetrieverLoader(Context context) {
        super(context, new TrafficSpeedRetriever(context));
    }
}
