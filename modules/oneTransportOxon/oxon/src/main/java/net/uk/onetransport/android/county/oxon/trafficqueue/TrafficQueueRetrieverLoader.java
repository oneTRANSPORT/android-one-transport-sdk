package net.uk.onetransport.android.county.oxon.trafficqueue;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.RetrieverLoader;

public class TrafficQueueRetrieverLoader extends RetrieverLoader<TrafficQueue> {

    public TrafficQueueRetrieverLoader(Context context) {
        super(context, new TrafficQueueRetriever(context));
    }
}
