package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import com.google.gson.annotations.Expose;

public class VariableMessageSign {

    @Expose
    private String locationId;
    @Expose
    private String description;
    @Expose
    private String vmsType;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;
    @Expose
    private Double numberOfCharacters;
    @Expose
    private Double numberOfRows;
    @Expose
    private String[] legends;
    private String cinId;
    private Long creationTime;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVmsType() {
        return vmsType;
    }

    public void setVmsType(String vmsType) {
        this.vmsType = vmsType;
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

    public Double getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(Double numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }

    public Double getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Double numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public String[] getLegends() {
        return legends;
    }

    public void setLegends(String[] legends) {
        this.legends = legends;
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
