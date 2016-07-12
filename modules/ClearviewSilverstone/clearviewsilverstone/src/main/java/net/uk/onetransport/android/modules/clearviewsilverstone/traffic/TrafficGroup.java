package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

public class TrafficGroup {

    private int sensorId;
    private Traffic[] traffic;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public Traffic[] getTraffic() {
        return traffic;
    }

    public void setTraffic(Traffic[] traffic) {
        this.traffic = traffic;
    }
}
