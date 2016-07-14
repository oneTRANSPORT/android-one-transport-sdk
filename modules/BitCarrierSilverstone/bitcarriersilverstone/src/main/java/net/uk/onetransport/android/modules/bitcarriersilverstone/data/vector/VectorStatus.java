package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VectorStatus {

    @Expose
    @SerializedName("vid")
    private Integer vId;
    @Expose
    @SerializedName("levelofservice")
    private String levelOfService;

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public String getLevelOfService() {
        return levelOfService;
    }

    public void setLevelOfService(String levelOfService) {
        this.levelOfService = levelOfService;
    }
}
