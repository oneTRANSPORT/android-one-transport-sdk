package net.uk.onetransport.android.county.northants.roadworks;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.RetrieverLoader;

public class RoadworksRetrieverLoader extends RetrieverLoader<Roadworks> {

    public RoadworksRetrieverLoader(Context context) {
        super(context, new RoadworksRetriever(context));
    }
}
