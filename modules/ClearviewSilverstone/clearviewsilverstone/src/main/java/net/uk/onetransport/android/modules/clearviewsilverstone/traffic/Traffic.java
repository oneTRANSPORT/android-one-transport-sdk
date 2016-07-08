package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Traffic {

    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("direction")
    private Boolean direction;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }
}
