package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Details;

public class Vector {

    @Expose
    @SerializedName("vid")
    private Integer vectorId;
    @Expose
    @SerializedName("time")
    private String timestamp;
    @Expose
    @SerializedName("average")
    private Details averageDetails;
    @Expose
    @SerializedName("last")
    private Details lastDetails;
    @Expose
    @SerializedName("levelofservice")
    private String levelOfService;
    private String cinId;
    private Long creationTime;

    public Integer getVectorId() {
        return vectorId;
    }

    public void setVectorId(Integer vectorId) {
        this.vectorId = vectorId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Details getAverageDetails() {
        return averageDetails;
    }

    public void setAverageDetails(Details averageDetails) {
        this.averageDetails = averageDetails;
    }

    public Details getLastDetails() {
        return lastDetails;
    }

    public void setLastDetails(Details lastDetails) {
        this.lastDetails = lastDetails;
    }

    public String getLevelOfService() {
        return levelOfService;
    }

    public void setLevelOfService(String levelOfService) {
        this.levelOfService = levelOfService;
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
