package net.uk.onetransport.android.modules.bitcarriersilverstone.vector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vector {

    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("customer_name")
    private String customerName;
    @Expose
    @SerializedName("from")
    private Integer from;
    @Expose
    @SerializedName("distance")
    private Integer distance;
    @Expose
    @SerializedName("zone")
    private Integer zone;
    @Expose
    @SerializedName("cityid")
    private Integer cityId;
    @Expose
    @SerializedName("block_time")
    private Integer blockTime;
    @Expose
    @SerializedName("segregation")
    private Integer segregation;
    @Expose
    @SerializedName("configuration")
    private Integer configuration;
    @Expose
    @SerializedName("priority")
    private Integer priority;
    @Expose
    @SerializedName("check_forced")
    private Integer checkForced;
    @Expose
    @SerializedName("sid")
    private Integer sId;
    @Expose
    @SerializedName("rid")
    private Integer rId;
    @Expose
    @SerializedName("levels")
    private Integer levels;
    @Expose
    @SerializedName("green")
    private String green;
    @Expose
    @SerializedName("yellow")
    private String yellow;
    @Expose
    @SerializedName("avg_green")
    private String averageGreen;
    @Expose
    @SerializedName("avg_yellow")
    private String avgerageYellow;
    @Expose
    @SerializedName("detections_min")
    private Integer detectionsMin;
    @Expose
    @SerializedName("has_color")
    private Boolean hasColour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Integer blockTime) {
        this.blockTime = blockTime;
    }

    public Integer getSegregation() {
        return segregation;
    }

    public void setSegregation(Integer segregation) {
        this.segregation = segregation;
    }

    public Integer getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Integer configuration) {
        this.configuration = configuration;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getCheckForced() {
        return checkForced;
    }

    public void setCheckForced(Integer checkForced) {
        this.checkForced = checkForced;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green;
    }

    public String getYellow() {
        return yellow;
    }

    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    public String getAverageGreen() {
        return averageGreen;
    }

    public void setAverageGreen(String averageGreen) {
        this.averageGreen = averageGreen;
    }

    public String getAvgerageYellow() {
        return avgerageYellow;
    }

    public void setAvgerageYellow(String avgerageYellow) {
        this.avgerageYellow = avgerageYellow;
    }

    public Integer getDetectionsMin() {
        return detectionsMin;
    }

    public void setDetectionsMin(Integer detectionsMin) {
        this.detectionsMin = detectionsMin;
    }

    public Boolean getHasColour() {
        return hasColour;
    }

    public void setHasColour(Boolean hasColour) {
        this.hasColour = hasColour;
    }
}
