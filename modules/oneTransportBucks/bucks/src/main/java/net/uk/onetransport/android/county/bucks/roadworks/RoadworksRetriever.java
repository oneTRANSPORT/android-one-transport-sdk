package net.uk.onetransport.android.county.bucks.roadworks;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.Retriever;

public class RoadworksRetriever extends Retriever<Roadworks> implements RoadworksParams {

    public RoadworksRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override // TODO    Needs Unique index title, cinId?
    protected Roadworks[] fromJson(String content, String cinId, Long creationTime) {
        Roadworks[] roadworkses = GSON.fromJson(content, Roadworks[].class);
        for (Roadworks roadworks : roadworkses) {
            roadworks.setCinId(cinId);
            roadworks.setCreationTime(creationTime);
        }
        return roadworkses;
    }
}
