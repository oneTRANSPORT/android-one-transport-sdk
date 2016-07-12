package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

public interface TrafficGroupArrayCallback {

    void onTrafficGroupArrayReady(int id, TrafficGroupArray trafficGroupArray);

    void onTrafficGroupArrayError(int id, Throwable throwable);
}
