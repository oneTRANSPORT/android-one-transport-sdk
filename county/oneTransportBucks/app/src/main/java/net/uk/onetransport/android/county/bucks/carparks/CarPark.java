package net.uk.onetransport.android.county.bucks.carparks;

import com.google.gson.annotations.Expose;

public class CarPark {

    @Expose
    private String carParkIdentity;
    @Expose
    private Integer totalParkingCapacity;
    @Expose
    private Integer almostFullDecreasing;
    @Expose
    private Integer almostFullIncreasing;
    @Expose
    private Integer fullDecreasing;
    @Expose
    private Integer fullIncreasing;
    @Expose
    private Integer entranceFull;
    @Expose
    private Double radius;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;

    public String getCarParkIdentity() {
        return carParkIdentity;
    }

    public void setCarParkIdentity(String carParkIdentity) {
        this.carParkIdentity = carParkIdentity;
    }

    public Integer getTotalParkingCapacity() {
        return totalParkingCapacity;
    }

    public void setTotalParkingCapacity(Integer totalParkingCapacity) {
        this.totalParkingCapacity = totalParkingCapacity;
    }

    public Integer getAlmostFullDecreasing() {
        return almostFullDecreasing;
    }

    public void setAlmostFullDecreasing(Integer almostFullDecreasing) {
        this.almostFullDecreasing = almostFullDecreasing;
    }

    public Integer getAlmostFullIncreasing() {
        return almostFullIncreasing;
    }

    public void setAlmostFullIncreasing(Integer almostFullIncreasing) {
        this.almostFullIncreasing = almostFullIncreasing;
    }

    public Integer getFullDecreasing() {
        return fullDecreasing;
    }

    public void setFullDecreasing(Integer fullDecreasing) {
        this.fullDecreasing = fullDecreasing;
    }

    public Integer getFullIncreasing() {
        return fullIncreasing;
    }

    public void setFullIncreasing(Integer fullIncreasing) {
        this.fullIncreasing = fullIncreasing;
    }

    public Integer getEntranceFull() {
        return entranceFull;
    }

    public void setEntranceFull(Integer entranceFull) {
        this.entranceFull = entranceFull;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
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

}
