package net.uk.onetransport.android.modules.bitcarriersilverstone.data.traveltime;

public interface TravelSummaryArrayCallback {

    void onTravelSummaryArrayReady(int id, TravelSummaryArray travelSummaryArray);

    void onTravelSummaryArrayError(int id, Throwable throwable);

}
