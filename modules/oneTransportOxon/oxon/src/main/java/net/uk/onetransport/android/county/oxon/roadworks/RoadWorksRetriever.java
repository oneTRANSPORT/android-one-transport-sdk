package net.uk.onetransport.android.county.oxon.roadworks;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.Retriever;

public class RoadWorksRetriever extends Retriever<RoadWorks> implements RoadWorksParams {

    public RoadWorksRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected RoadWorks[] fromJson(String content, String cinId, Long creationTime) {
        RoadWorks[] roadWorkses = GSON.fromJson(content, RoadWorks[].class);
        for (RoadWorks roadWorks : roadWorkses) {
            roadWorks.setCinId(cinId);
            roadWorks.setCreationTime(creationTime);
        }
        return roadWorkses;
    }
}
