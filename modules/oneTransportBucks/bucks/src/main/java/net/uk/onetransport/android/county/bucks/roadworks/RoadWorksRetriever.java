package net.uk.onetransport.android.county.bucks.roadworks;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.Retriever;

public class RoadWorksRetriever extends Retriever<RoadWorks> implements RoadWorksParams {

    public RoadWorksRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override // TODO    Needs Unique index title, cinId?
    protected RoadWorks[] fromJson(String content, String cinId, Long creationTime) {
        RoadWorks[] roadWorkses = GSON.fromJson(content, RoadWorks[].class);
        for (RoadWorks roadWorks : roadWorkses) {
            roadWorks.setCinId(cinId);
            roadWorks.setCreationTime(creationTime);
        }
        return roadWorkses;
    }
}
