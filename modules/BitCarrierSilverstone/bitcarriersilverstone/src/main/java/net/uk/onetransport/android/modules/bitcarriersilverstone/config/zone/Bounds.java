package net.uk.onetransport.android.modules.bitcarriersilverstone.config.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bounds {

    @Expose
    @SerializedName("latn")
    private Double northernLatitude;
    @Expose
    @SerializedName("lonw")
    private Double westernLongitude;
    @Expose
    @SerializedName("lats")
    private Double southernLatitude;
    @Expose
    @SerializedName("lone")
    private Double easternLongitude;

    public Double getNorthernLatitude() {
        return northernLatitude;
    }

    public void setNorthernLatitude(Double northernLatitude) {
        this.northernLatitude = northernLatitude;
    }

    public Double getWesternLongitude() {
        return westernLongitude;
    }

    public void setWesternLongitude(Double westernLongitude) {
        this.westernLongitude = westernLongitude;
    }

    public Double getSouthernLatitude() {
        return southernLatitude;
    }

    public void setSouthernLatitude(Double southernLatitude) {
        this.southernLatitude = southernLatitude;
    }

    public Double getEasternLongitude() {
        return easternLongitude;
    }

    public void setEasternLongitude(Double easternLongitude) {
        this.easternLongitude = easternLongitude;
    }
}
