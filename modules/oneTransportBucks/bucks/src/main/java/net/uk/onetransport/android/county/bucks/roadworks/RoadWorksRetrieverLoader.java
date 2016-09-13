package net.uk.onetransport.android.county.bucks.roadworks;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.RetrieverLoader;

public class RoadWorksRetrieverLoader extends RetrieverLoader<RoadWorks> {

    public RoadWorksRetrieverLoader(Context context) {
        super(context, new RoadWorksRetriever(context));
    }
}
