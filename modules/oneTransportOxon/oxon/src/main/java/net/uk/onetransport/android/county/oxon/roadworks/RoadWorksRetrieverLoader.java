package net.uk.onetransport.android.county.oxon.roadworks;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.RetrieverLoader;

public class RoadworksRetrieverLoader extends RetrieverLoader<Roadworks> {

    public RoadworksRetrieverLoader(Context context) {
        super(context, new RoadworksRetriever(context));
    }
}
