package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class TravelSummaryRetriever extends Retriever<TravelSummary>
        implements TravelSummaryParams {

    public ArrayList<TravelSummary> retrieve(Context context) throws Exception {
        ArrayList<TravelSummary> travelSummaries = new ArrayList<>();
        retrieve(context, travelSummaries);
        return travelSummaries;
    }

    @Override
    protected int[] getIds() {
        return TRAVEL_IDS;
    }

    @Override
    protected String getRetrivePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected TravelSummary fromJson(String content) {
        return GSON.fromJson(content, TravelSummary.class);
    }
}
