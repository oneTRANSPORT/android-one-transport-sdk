package net.uk.onetransport.android.modules.bitcarriersilverstone.config.route;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {

    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("cityid")
    private Integer cityId;
    @Expose
    @SerializedName("metavector")
    private Integer metavector;
    @Expose
    @SerializedName("vector1")
    private String vector1;
    @Expose
    @SerializedName("vector1_contrib")
    private Double vector1Contrib;
    @Expose
    @SerializedName("vector2")
    private String vector2;
    @Expose
    @SerializedName("vector2_contrib")
    private Double vector2Contrib;
    @Expose
    @SerializedName("offset")
    private Integer offset;
    @Expose
    @SerializedName("distance")
    private Integer distance;
    @Expose
    @SerializedName("ffs")
    private String ffs;
    @Expose
    @SerializedName("ffs_min")
    private String ffsMin;
    @Expose
    @SerializedName("route_detections_min")
    private Integer routeDetectionsMin;
    @Expose
    @SerializedName("zone")
    private Integer zone;
    @Expose
    @SerializedName("name")
    private Integer name;
    @Expose
    @SerializedName("customer_name")
    private String customerName;
    @Expose
    @SerializedName("configuration")
    private Integer configuration;
    @Expose
    @SerializedName("rid")
    private String rId;
    @Expose
    @SerializedName("levels")
    private String levels;
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
    private String averageYellow;
    @Expose
    @SerializedName("ff_green")
    private String ffGreen;
    @Expose
    @SerializedName("ff_yellow")
    private String ffYellow;
    @Expose
    @SerializedName("detections_min")
    private String detectionsMin;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getMetavector() {
        return metavector;
    }

    public void setMetavector(Integer metavector) {
        this.metavector = metavector;
    }

    public String getVector1() {
        return vector1;
    }

    public void setVector1(String vector1) {
        this.vector1 = vector1;
    }

    public Double getVector1Contrib() {
        return vector1Contrib;
    }

    public void setVector1Contrib(Double vector1Contrib) {
        this.vector1Contrib = vector1Contrib;
    }

    public String getVector2() {
        return vector2;
    }

    public void setVector2(String vector2) {
        this.vector2 = vector2;
    }

    public Double getVector2Contrib() {
        return vector2Contrib;
    }

    public void setVector2Contrib(Double vector2Contrib) {
        this.vector2Contrib = vector2Contrib;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getFfs() {
        return ffs;
    }

    public void setFfs(String ffs) {
        this.ffs = ffs;
    }

    public String getFfsMin() {
        return ffsMin;
    }

    public void setFfsMin(String ffsMin) {
        this.ffsMin = ffsMin;
    }

    public Integer getRouteDetectionsMin() {
        return routeDetectionsMin;
    }

    public void setRouteDetectionsMin(Integer routeDetectionsMin) {
        this.routeDetectionsMin = routeDetectionsMin;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
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

    public Integer getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Integer configuration) {
        this.configuration = configuration;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
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

    public String getAverageYellow() {
        return averageYellow;
    }

    public void setAverageYellow(String averageYellow) {
        this.averageYellow = averageYellow;
    }

    public String getFfGreen() {
        return ffGreen;
    }

    public void setFfGreen(String ffGreen) {
        this.ffGreen = ffGreen;
    }

    public String getFfYellow() {
        return ffYellow;
    }

    public void setFfYellow(String ffYellow) {
        this.ffYellow = ffYellow;
    }

    public String getDetectionsMin() {
        return detectionsMin;
    }

    public void setDetectionsMin(String detectionsMin) {
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
