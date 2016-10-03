package net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

public class Sketch {

    @Expose
    @SerializedName("sid")
    private Integer sketchId;
    @Expose
    @SerializedName("vid")
    private Integer vectorId;
    @Expose
    @SerializedName("visible")
    private Boolean visible;
    @Expose
    @SerializedName("copyrights")
    private String copyrights;
    @Expose
    @SerializedName("json")
    private String coordinates;
    private Position[] positions;
    private String cinId;
    private Long creationTime;

    public void setPositionsFromCoordinates() {
        if (coordinates != null) {
            positions = Retriever.GSON.fromJson(coordinates, Position[].class);
        }
    }

    public Integer getSketchId() {
        return sketchId;
    }

    public void setSketchId(Integer sketchId) {
        this.sketchId = sketchId;
    }

    public Integer getVectorId() {
        return vectorId;
    }

    public void setVectorId(Integer vectorId) {
        this.vectorId = vectorId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Position[] getPositions() {
        if (positions == null) {
            setPositionsFromCoordinates();
        }
        return positions;
    }

    public void setPositions(Position[] positions) {
        this.positions = positions;
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
