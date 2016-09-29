package net.uk.onetransport.android.county.oxon.trafficflow;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.RetrieverLoader;

public class TrafficFlowRetrieverLoader extends RetrieverLoader<TrafficFlow> {

    public TrafficFlowRetrieverLoader(Context context) {
        super(context, new TrafficFlowRetriever(context));
    }
}
