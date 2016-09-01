package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @Expose
    @SerializedName("score")
    private Integer score;
    @Expose
    @SerializedName("publish")
    private Stat publish;
    @Expose
    @SerializedName("calculated")
    private Stat calculated;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Stat getPublish() {
        return publish;
    }

    public void setPublish(Stat publish) {
        this.publish = publish;
    }

    public Stat getCalculated() {
        return calculated;
    }

    public void setCalculated(Stat calculated) {
        this.calculated = calculated;
    }
}
