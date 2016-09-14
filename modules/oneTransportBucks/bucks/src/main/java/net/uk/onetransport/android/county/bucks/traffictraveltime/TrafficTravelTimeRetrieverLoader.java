package net.uk.onetransport.android.county.bucks.traffictraveltime;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.RetrieverLoader;

public class TrafficTravelTimeRetrieverLoader extends RetrieverLoader<TrafficTravelTime> {

    public TrafficTravelTimeRetrieverLoader(Context context) {
        super(context, new TrafficTravelTimeRetriever(context));
    }
}
