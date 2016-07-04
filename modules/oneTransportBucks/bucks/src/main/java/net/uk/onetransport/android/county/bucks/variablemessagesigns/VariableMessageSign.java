package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import com.google.gson.annotations.Expose;

public class VariableMessageSign {

    // TODO    Add meta data after discussion?
    @Expose
    private String locationId;
    @Expose
    private String name;
    @Expose
    private Integer numberOfCharacters;
    @Expose
    private Integer numberOfRows;
    @Expose
    private String[] vmsLegends;
    @Expose
    private String vmsType;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;
    @Expose
    private String descriptor;
    @Expose
    private String tpegDirection;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(Integer numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public String[] getVmsLegends() {
        return vmsLegends;
    }

    public void setVmsLegends(String[] vmsLegends) {
        this.vmsLegends = vmsLegends;
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

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getTpegDirection() {
        return tpegDirection;
    }

    public void setTpegDirection(String tpegDirection) {
        this.tpegDirection = tpegDirection;
    }
}
