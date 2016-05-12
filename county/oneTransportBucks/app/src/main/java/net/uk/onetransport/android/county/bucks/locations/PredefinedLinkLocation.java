package net.uk.onetransport.android.county.bucks.locations;

import com.google.gson.annotations.Expose;

public class PredefinedLinkLocation {

    @Expose
    private String locationId;
    @Expose
    private Double fromLatitude;
    @Expose
    private Double fromLongitude;
    @Expose
    private Double toLatitude;
    @Expose
    private Double toLongitude;
    @Expose
    private String fromDescriptor;
    @Expose
    private String toDescriptor;
    @Expose
    private String tpegDirection;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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

    public String getFromDescriptor() {
        return fromDescriptor;
    }

    public void setFromDescriptor(String fromDescriptor) {
        this.fromDescriptor = fromDescriptor;
    }

    public String getToDescriptor() {
        return toDescriptor;
    }

    public void setToDescriptor(String toDescriptor) {
        this.toDescriptor = toDescriptor;
    }

    public String getTpegDirection() {
        return tpegDirection;
    }

    public void setTpegDirection(String tpegDirection) {
        this.tpegDirection = tpegDirection;
    }
}
