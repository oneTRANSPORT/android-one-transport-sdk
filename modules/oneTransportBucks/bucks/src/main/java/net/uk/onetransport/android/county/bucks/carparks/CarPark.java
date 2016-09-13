package net.uk.onetransport.android.county.bucks.carparks;

import com.google.gson.annotations.Expose;

public class CarPark {

    @Expose
    private String carParkIdentity;
    @Expose
    private Double almostFullIncreasing;
    @Expose
    private Double occupancy;
    @Expose
    private Double exitRate;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;
    @Expose
    private String occupancyTrend;
    @Expose
    private Double fullIncreasing;
    @Expose
    private Double fillRate;
    @Expose
    private Double totalParkingCapacity;
    @Expose
    private String statusTime;
    @Expose
    private Double queuingTime;
    @Expose
    private Double almostFullDecreasing;
    @Expose
    private Double fullDecreasing;
    @Expose
    private String parkingAreaName;
    @Expose
    private Double entranceFull;
    @Expose
    private String status;
    private String cinId;
    private Long creationTime;

    public String getCarParkIdentity() {
        return carParkIdentity;
    }

    public void setCarParkIdentity(String carParkIdentity) {
        this.carParkIdentity = carParkIdentity;
    }

    public Double getAlmostFullIncreasing() {
        return almostFullIncreasing;
    }

    public void setAlmostFullIncreasing(Double almostFullIncreasing) {
        this.almostFullIncreasing = almostFullIncreasing;
    }

    public Double getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Double occupancy) {
        this.occupancy = occupancy;
    }

    public Double getExitRate() {
        return exitRate;
    }

    public void setExitRate(Double exitRate) {
        this.exitRate = exitRate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getOccupancyTrend() {
        return occupancyTrend;
    }

    public void setOccupancyTrend(String occupancyTrend) {
        this.occupancyTrend = occupancyTrend;
    }

    public Double getFullIncreasing() {
        return fullIncreasing;
    }

    public void setFullIncreasing(Double fullIncreasing) {
        this.fullIncreasing = fullIncreasing;
    }

    public Double getFillRate() {
        return fillRate;
    }

    public void setFillRate(Double fillRate) {
        this.fillRate = fillRate;
    }

    public Double getTotalParkingCapacity() {
        return totalParkingCapacity;
    }

    public void setTotalParkingCapacity(Double totalParkingCapacity) {
        this.totalParkingCapacity = totalParkingCapacity;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    public Double getQueuingTime() {
        return queuingTime;
    }

    public void setQueuingTime(Double queuingTime) {
        this.queuingTime = queuingTime;
    }

    public Double getAlmostFullDecreasing() {
        return almostFullDecreasing;
    }

    public void setAlmostFullDecreasing(Double almostFullDecreasing) {
        this.almostFullDecreasing = almostFullDecreasing;
    }

    public Double getFullDecreasing() {
        return fullDecreasing;
    }

    public void setFullDecreasing(Double fullDecreasing) {
        this.fullDecreasing = fullDecreasing;
    }

    public String getParkingAreaName() {
        return parkingAreaName;
    }

    public void setParkingAreaName(String parkingAreaName) {
        this.parkingAreaName = parkingAreaName;
    }

    public Double getEntranceFull() {
        return entranceFull;
    }

    public void setEntranceFull(Double entranceFull) {
        this.entranceFull = entranceFull;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
