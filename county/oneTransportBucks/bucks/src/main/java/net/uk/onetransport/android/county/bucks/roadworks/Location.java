package net.uk.onetransport.android.county.bucks.roadworks;

import com.google.gson.annotations.Expose;

public class Location {

    @Expose
    private String description;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
