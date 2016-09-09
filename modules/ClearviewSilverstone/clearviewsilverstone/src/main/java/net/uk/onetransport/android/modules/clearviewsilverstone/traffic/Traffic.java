package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Traffic {

    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("lane")
    private Integer lane;
    @Expose
    @SerializedName("direction")
    private Boolean direction;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLane() {
        return lane;
    }

    public void setLane(Integer lane) {
        this.lane = lane;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }
}
