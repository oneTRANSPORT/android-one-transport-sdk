package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelSummary {

    @Expose
    @SerializedName("rid")
    private Integer rId;
    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("traveltimes")
    private TravelTime[] travelTimes;
    @Expose
    @SerializedName("average")
    private Details details;
    @Expose
    @SerializedName("last")
    private Details last;
    @Expose
    @SerializedName("levelofservice")
    private String levelOfService;
    private String cinId;
    private Long creationTime;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TravelTime[] getTravelTimes() {
        return travelTimes;
    }

    public void setTravelTimes(TravelTime[] travelTimes) {
        this.travelTimes = travelTimes;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Details getLast() {
        return last;
    }

    public void setLast(Details last) {
        this.last = last;
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
