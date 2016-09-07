package net.uk.onetransport.android.modules.clearviewsilverstone.device;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

    // This field comes from the URI of the device and is not given in the JSON payload.
    private int sensorId;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("latitude")
    private Double latitude;
    @Expose
    @SerializedName("longitude")
    private Double longitude;
    @Expose
    @SerializedName("changed")
    private String changed;
    private String cinId;
    private Long creationTime;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
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
