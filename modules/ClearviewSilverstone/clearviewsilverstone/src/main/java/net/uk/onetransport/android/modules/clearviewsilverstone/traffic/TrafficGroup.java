package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

public class TrafficGroup {

    private Integer sensorId;
    private Traffic[] traffic;

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Traffic[] getTraffic() {
        return traffic;
    }

    public void setTraffic(Traffic[] traffic) {
        this.traffic = traffic;
    }
}
