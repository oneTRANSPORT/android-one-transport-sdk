package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Average;

public class VectorStatus {

    @Expose
    @SerializedName("vid")
    private Integer vId;
    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("average")
    private Average average;
    @Expose
    @SerializedName("last")
    private Average last;
    @Expose
    @SerializedName("levelofservice")
    private String levelOfService;

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Average getAverage() {
        return average;
    }

    public void setAverage(Average average) {
        this.average = average;
    }

    public Average getLast() {
        return last;
    }

    public void setLast(Average last) {
        this.last = last;
    }

    public String getLevelOfService() {
        return levelOfService;
    }

    public void setLevelOfService(String levelOfService) {
        this.levelOfService = levelOfService;
    }
}
