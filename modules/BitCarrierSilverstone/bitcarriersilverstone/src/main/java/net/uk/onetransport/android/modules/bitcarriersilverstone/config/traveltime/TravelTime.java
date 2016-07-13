package net.uk.onetransport.android.modules.bitcarriersilverstone.config.traveltime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelTime {

    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("routeid")
    private Integer routeId;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("customer_name")
    private String customerName;
    @Expose
    @SerializedName("publish")
    private Boolean publish;
    @Expose
    @SerializedName("extra_offset")
    private Integer extraOffset;
    @Expose
    @SerializedName("zone")
    private Integer zone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public Integer getExtraOffset() {
        return extraOffset;
    }

    public void setExtraOffset(Integer extraOffset) {
        this.extraOffset = extraOffset;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }
}
