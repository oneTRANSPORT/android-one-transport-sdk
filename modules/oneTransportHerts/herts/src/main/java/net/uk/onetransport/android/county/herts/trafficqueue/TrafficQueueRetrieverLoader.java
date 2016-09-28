package net.uk.onetransport.android.county.herts.trafficqueue;

import android.content.Context;

import net.uk.onetransport.android.county.herts.generic.RetrieverLoader;

public class TrafficQueueRetrieverLoader extends RetrieverLoader<TrafficQueue> {

    public TrafficQueueRetrieverLoader(Context context) {
        super(context, new TrafficQueueRetriever(context));
    }
}
