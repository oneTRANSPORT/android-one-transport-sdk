package net.uk.onetransport.android.modules.bitcarriersilverstone.config.metavector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metavector {

    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("dbname")
    private Integer dbName;
    @Expose
    @SerializedName("sequence")
    private String sequence;
    @Expose
    @SerializedName("failsafe")
    private String failsafe;
    @Expose
    @SerializedName("name")
    private Integer name;
    @Expose
    @SerializedName("customer_name")
    private String customerName;
    @Expose
    @SerializedName("zone")
    private Integer zone;
    @Expose
    @SerializedName("cityid")
    private Integer cityId;
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
    @SerializedName("score_min")
    private String scoreMin;
    @Expose
    @SerializedName("has_color")
    private Boolean hasColour;
    private String cinId;
    private Long creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDbName() {
        return dbName;
    }

    public void setDbName(Integer dbName) {
        this.dbName = dbName;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getFailsafe() {
        return failsafe;
    }

    public void setFailsafe(String failsafe) {
        this.failsafe = failsafe;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getScoreMin() {
        return scoreMin;
    }

    public void setScoreMin(String scoreMin) {
        this.scoreMin = scoreMin;
    }

    public Boolean getHasColour() {
        return hasColour;
    }

    public void setHasColour(Boolean hasColour) {
        this.hasColour = hasColour;
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
