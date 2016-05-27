package net.uk.onetransport.android.county.bucks.roadworks;

public interface RoadWorksArrayCallback {

    void onRoadWorksArrayReady(int id, RoadWorksArray roadWorksArray);

    void onRoadWorksArrayError(int id, Throwable throwable);
}
