package net.uk.onetransport.android.county.northants.roadworks;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.RetrieverLoader;

public class RoadWorksRetrieverLoader extends RetrieverLoader<RoadWorks> {

    public RoadWorksRetrieverLoader(Context context) {
        super(context, new RoadWorksRetriever(context));
    }
}
