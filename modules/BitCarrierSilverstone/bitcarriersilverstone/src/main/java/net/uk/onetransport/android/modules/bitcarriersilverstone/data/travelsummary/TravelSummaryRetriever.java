package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class TravelSummaryRetriever extends Retriever<TravelSummary>
        implements TravelSummaryParams {

    public TravelSummaryRetriever(Context context) {
        super(context);
    }

    public ArrayList<TravelSummary> retrieve() throws Exception {
        ArrayList<TravelSummary> travelSummaries = new ArrayList<>();
        retrieve(travelSummaries);
        return travelSummaries;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected TravelSummary fromJson(String content, String cinId, Long creationTime) {
        return GSON.fromJson(content, TravelSummary.class);
    }

}
