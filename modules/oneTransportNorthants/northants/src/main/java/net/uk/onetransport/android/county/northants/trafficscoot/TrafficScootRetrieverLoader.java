package net.uk.onetransport.android.county.northants.trafficscoot;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.RetrieverLoader;

public class TrafficScootRetrieverLoader extends RetrieverLoader<TrafficScoot> {

    public TrafficScootRetrieverLoader(Context context) {
        super(context, new TrafficScootRetriever(context));
    }
}