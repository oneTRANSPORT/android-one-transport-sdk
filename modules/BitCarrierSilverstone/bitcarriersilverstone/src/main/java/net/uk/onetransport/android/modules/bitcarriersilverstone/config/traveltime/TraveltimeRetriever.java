package net.uk.onetransport.android.modules.bitcarriersilverstone.config.traveltime;

import android.content.Context;
import android.database.Cursor;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;

import java.util.ArrayList;

public class TraveltimeRetriever extends Retriever<TravelTime> implements TraveltimeParams {

    public TraveltimeRetriever(Context context) {
        super(context);
    }

    public ArrayList<TravelTime> retrieve() throws Exception {
        ArrayList<TravelTime> travelTimes = new ArrayList<>();
        retrieve(travelTimes);
        return travelTimes;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected TravelTime fromJson(String content, String cinId, Long creationTime) {
        TravelTime travelTime = GSON.fromJson(content, TravelTime.class);
        travelTime.setCinId(cinId);
        travelTime.setCreationTime(creationTime);
        return travelTime;
    }

    @Override
    protected Cursor getResourceNames(Context context, int traveltimeId) {
        return BcsContentHelper.getConfigTraveltimeNames(context, traveltimeId);
    }
}
