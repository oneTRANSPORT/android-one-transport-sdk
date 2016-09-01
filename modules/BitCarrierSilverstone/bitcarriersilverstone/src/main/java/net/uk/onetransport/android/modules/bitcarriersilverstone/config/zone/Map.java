package net.uk.onetransport.android.modules.bitcarriersilverstone.config.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map {

    @Expose
    @SerializedName("center")
    private Center center;
    @Expose
    @SerializedName("bounds")
    private Bounds bounds;

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
}
