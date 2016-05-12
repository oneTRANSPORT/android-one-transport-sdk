package net.uk.onetransport.android.county.bucks.trafficflow;

public interface TrafficFlowArrayCallback {

    void onTrafficFlowArrayReady(int id,
                                 TrafficFlowArray TrafficFlowArray);

    void onTrafficFlowArrayError(int id, Throwable throwable);

}
