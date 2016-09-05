package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Position {

    @Expose
    @SerializedName("lat")
    private Double latitude;
    @Expose
    @SerializedName("lon")
    private Double longitude;

    public Position() {
    }

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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