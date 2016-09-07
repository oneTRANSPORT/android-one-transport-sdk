package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

public class TrafficGroup {

    private Integer sensorId;
    private Traffic[] traffic;
    private String cinId;
    private Long creationTime;

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

    public String getCinId() {
        return cinId;
    }

    public void setCinId(String cinId) {
        this.cinId = cinId;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }
}
