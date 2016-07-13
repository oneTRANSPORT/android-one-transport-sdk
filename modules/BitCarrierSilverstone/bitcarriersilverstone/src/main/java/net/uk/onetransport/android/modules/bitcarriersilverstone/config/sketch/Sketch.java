package net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sketch {

    @Expose
    @SerializedName("sid")
    private Integer sId;
    @Expose
    @SerializedName("vid")
    private Integer vId;
    @Expose
    @SerializedName("visible")
    private Boolean visible;
    @Expose
    @SerializedName("from_road")
    private Integer fromRoad;
    @Expose
    @SerializedName("from_pk")
    private Double fromPk;
    @Expose
    @SerializedName("from_way")
    private String fromWay;
    @Expose
    @SerializedName("to_road")
    private Integer toRoad;
    @Expose
    @SerializedName("to_pk")
    private Double toPk;
    @Expose
    @SerializedName("to_way")
    private String toWay;
    @Expose
    @SerializedName("zone")
    private Integer zone;
    @Expose
    @SerializedName("json")
    private String locationJsonArray;
    @Expose
    @SerializedName("copyrights")
    private String copyrights;
    // This array has to be extracted separately from the loction JSON string.
    private Position[] positions;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getFromRoad() {
        return fromRoad;
    }

    public void setFromRoad(Integer fromRoad) {
        this.fromRoad = fromRoad;
    }

    public Double getFromPk() {
        return fromPk;
    }

    public void setFromPk(Double fromPk) {
        this.fromPk = fromPk;
    }

    public String getFromWay() {
        return fromWay;
    }

    public void setFromWay(String fromWay) {
        this.fromWay = fromWay;
    }

    public Integer getToRoad() {
        return toRoad;
    }

    public void setToRoad(Integer toRoad) {
        this.toRoad = toRoad;
    }

    public Double getToPk() {
        return toPk;
    }

    public void setToPk(Double toPk) {
        this.toPk = toPk;
    }

    public String getToWay() {
        return toWay;
    }

    public void setToWay(String toWay) {
        this.toWay = toWay;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public String getLocationJsonArray() {
        return locationJsonArray;
    }

    public void setLocationJsonArray(String locationJsonArray) {
        this.locationJsonArray = locationJsonArray;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
    }
}
