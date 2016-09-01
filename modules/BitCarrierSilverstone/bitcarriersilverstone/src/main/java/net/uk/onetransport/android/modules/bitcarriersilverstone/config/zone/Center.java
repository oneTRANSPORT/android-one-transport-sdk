package net.uk.onetransport.android.modules.bitcarriersilverstone.config.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Center {

    @Expose
    @SerializedName("lat")
    private Double latitude;
    @Expose
    @SerializedName("lon")
    private Double longitude;
    @Expose
    @SerializedName("zoom")
    private Integer zoom;

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

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }
}
