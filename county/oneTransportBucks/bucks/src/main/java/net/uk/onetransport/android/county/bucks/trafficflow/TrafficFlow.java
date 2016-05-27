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
    @Expose
    private Integer occupancy;
    @Expose
    private Double congestionPercent;
    @Expose
    private Double currentFlow;
    @Expose
    private Double averageSpeed;
    @Expose
    private String linkStatus;
    @Expose
    private String linkStatusType;
    @Expose
    private String linkTravelTime;

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

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

    public Double getCongestionPercent() {
        return congestionPercent;
    }

    public void setCongestionPercent(Double congestionPercent) {
        this.congestionPercent = congestionPercent;
    }

    public Double getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(Double currentFlow) {
        this.currentFlow = currentFlow;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public String getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(String linkStatus) {
        this.linkStatus = linkStatus;
    }

    public String getLinkStatusType() {
        return linkStatusType;
    }

    public void setLinkStatusType(String linkStatusType) {
        this.linkStatusType = linkStatusType;
    }

    public String getLinkTravelTime() {
        return linkTravelTime;
    }

    public void setLinkTravelTime(String linkTravelTime) {
        this.linkTravelTime = linkTravelTime;
    }
}
