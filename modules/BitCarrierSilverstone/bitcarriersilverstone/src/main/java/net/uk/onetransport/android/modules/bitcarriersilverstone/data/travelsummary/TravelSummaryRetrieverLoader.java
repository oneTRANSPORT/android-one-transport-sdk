//package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;
//
//import android.content.Context;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;
//
//import java.util.ArrayList;
//
//public class TravelSummaryRetrieverLoader extends RetrieverLoader<TravelSummary>
//        implements TravelSummaryParams {
//
//    public TravelSummaryRetrieverLoader(Context context) {
//        super(context);
//    }
//
//    @Override
//    protected int[] getIds() {
//        return TRAVEL_IDS;
//    }
//
//    @Override
//    protected String getRetrievePrefix() {
//        return RETRIEVE_PREFIX;
//    }
//
//    @Override
//    protected TravelSummary fromJson(String content) {
//        return Retriever.GSON.fromJson(content, TravelSummary.class);
//    }
//}
