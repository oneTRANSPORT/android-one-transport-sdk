package net.uk.onetransport.android.county.northants.trafficflow;

import com.google.gson.annotations.Expose;

public class TrafficFlow {

    @Expose
    private String id;
    @Expose
    private String tpegDirection;
    @Expose
    private String fromType;
    @Expose
    private String fromDescriptor;
    @Expose
    private Double fromLatitude;
    @Expose
    private Double fromLongitude;
    @Expose
    private String toType;
    @Expose
    private String toDescriptor;
    @Expose
    private Double toLatitude;
    @Expose
    private Double toLongitude;
    @Expose
    private String time;
    @Expose
    private Double vehicleFlow;
    private String cinId;
    private Long creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpegDirection() {
        return tpegDirection;
    }

    public void setTpegDirection(String tpegDirection) {
        this.tpegDirection = tpegDirection;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getFromDescriptor() {
        return fromDescriptor;
    }

    public void setFromDescriptor(String fromDescriptor) {
        this.fromDescriptor = fromDescriptor;
    }

    public Double getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(Double fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public Double getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(Double fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getToDescriptor() {
        return toDescriptor;
    }

    public void setToDescriptor(String toDescriptor) {
        this.toDescriptor = toDescriptor;
    }

    public Double getToLatitude() {
        return toLatitude;
    }

    public void setToLatitude(Double toLatitude) {
        this.toLatitude = toLatitude;
    }

    public Double getToLongitude() {
        return toLongitude;
    }

    public void setToLongitude(Double toLongitude) {
        this.toLongitude = toLongitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getVehicleFlow() {
        return vehicleFlow;
    }

    public void setVehicleFlow(Double vehicleFlow) {
        this.vehicleFlow = vehicleFlow;
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
