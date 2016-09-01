package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class TravelSummaryRetrieverLoader extends RetrieverLoader<TravelSummary>
        implements TravelSummaryParams {

    public TravelSummaryRetrieverLoader(Context context) {
        super(context, new TravelSummaryRetriever(context));
    }
}
