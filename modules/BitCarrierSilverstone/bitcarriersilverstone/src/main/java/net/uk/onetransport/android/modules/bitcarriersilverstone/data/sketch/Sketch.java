package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Position;

import java.util.ArrayList;

public class Sketch {

    @Expose
    @SerializedName("sid")
    private Integer sId;
    @Expose
    @SerializedName("vid")
    private Integer vId;
    @Expose
    @SerializedName("levelofservice")
    private String levelOfService;
    @Expose
    @SerializedName("license")
    private String license;
    @Expose
    @SerializedName("coordinates")
    private String coordinates;
    private ArrayList<Position> positions = new ArrayList<>();

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

    public String getLevelOfService() {
        return levelOfService;
    }

    public void setLevelOfService(String levelOfService) {
        this.levelOfService = levelOfService;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }

    public void setPositionsFromCoordinates() {
        positions.clear();
        for (String lonLat : coordinates.split(" ")) {
            try {
                double longitude = Double.parseDouble(lonLat.replaceFirst(",.*$", "").trim());
                double latitude = Double.parseDouble(lonLat.replaceFirst("^.*,", "").trim());
                positions.add(new Position(latitude, longitude));
            } catch (NumberFormatException e) {
                // Don't add to array list.
            }
        }
    }
}
