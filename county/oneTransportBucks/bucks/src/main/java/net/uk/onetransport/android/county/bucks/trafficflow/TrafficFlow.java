package net.uk.onetransport.android.county.bucks.trafficflow;

import com.google.gson.annotations.Expose;

public class TrafficFlow {

    @Expose
    private String locationReference;
    @Expose
    private Integer vehicleFlow;
    @Expose
    private Double averageVehicleSpeed;
    @Expose
    private Integer travelTime;
    @Expose
    private Integer freeFlowSpeed;
    @Expose
    private Integer freeFlowTravelTime;
    @Expose
    private String queuePresent;
    @Expose
    private Integer queueSeverity;

    public String getLocationReference() {
        return locationReference;
    }

    public void setLocationReference(String locationReference) {
        this.locationReference = locationReference;
    }

    public Integer getVehicleFlow() {
        return vehicleFlow;
    }

    public void setVehicleFlow(Integer vehicleFlow) {
        this.vehicleFlow = vehicleFlow;
    }

    public Double getAverageVehicleSpeed() {
        return averageVehicleSpeed;
    }

    public void setAverageVehicleSpeed(Double averageVehicleSpeed) {
        this.averageVehicleSpeed = averageVehicleSpeed;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    public Integer getFreeFlowSpeed() {
        return freeFlowSpeed;
    }

    public void setFreeFlowSpeed(Integer freeFlowSpeed) {
        this.freeFlowSpeed = freeFlowSpeed;
    }

    public Integer getFreeFlowTravelTime() {
        return freeFlowTravelTime;
    }

    public void setFreeFlowTravelTime(Integer freeFlowTravelTime) {
        this.freeFlowTravelTime = freeFlowTravelTime;
    }

    public String getQueuePresent() {
        return queuePresent;
    }

    public void setQueuePresent(String queuePresent) {
        this.queuePresent = queuePresent;
    }

    public Integer getQueueSeverity() {
        return queueSeverity;
    }

    public void setQueueSeverity(Integer queueSeverity) {
        this.queueSeverity = queueSeverity;
    }
}
