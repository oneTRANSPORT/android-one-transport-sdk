package net.uk.onetransport.android.county.bucks.events;

import com.google.gson.annotations.Expose;

public class Event {

    @Expose
    private String id;
    @Expose
    private String startOfPeriod;
    @Expose
    private String endOfPeriod;
    @Expose
    private String overallStartTime;
    @Expose
    private String overallEndTime;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;
    @Expose
    private String description;
    @Expose
    private String impactOnTraffic;
    @Expose
    private String validityStatus;
    private String cinId;
    private Long creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartOfPeriod() {
        return startOfPeriod;
    }

    public void setStartOfPeriod(String startOfPeriod) {
        this.startOfPeriod = startOfPeriod;
    }

    public String getEndOfPeriod() {
        return endOfPeriod;
    }

    public void setEndOfPeriod(String endOfPeriod) {
        this.endOfPeriod = endOfPeriod;
    }

    public String getOverallStartTime() {
        return overallStartTime;
    }

    public void setOverallStartTime(String overallStartTime) {
        this.overallStartTime = overallStartTime;
    }

    public String getOverallEndTime() {
        return overallEndTime;
    }

    public void setOverallEndTime(String overallEndTime) {
        this.overallEndTime = overallEndTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImpactOnTraffic() {
        return impactOnTraffic;
    }

    public void setImpactOnTraffic(String impactOnTraffic) {
        this.impactOnTraffic = impactOnTraffic;
    }

    public String getValidityStatus() {
        return validityStatus;
    }

    public void setValidityStatus(String validityStatus) {
        this.validityStatus = validityStatus;
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
