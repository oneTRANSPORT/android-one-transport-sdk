package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

public interface TrafficArrayCallback {

    void onTrafficArrayReady(int id, TrafficArray trafficArray);

    void onTrafficArrayError(int id, Throwable throwable);
}
