package net.uk.onetransport.android.county.bucks.roadworks;

import com.google.gson.annotations.Expose;

public class RoadWorks {

    @Expose
    private String id;
    @Expose
    private String comment;
    @Expose
    private String effectOnRoadLayout;
    @Expose
    private String roadMaintenanceType;
    @Expose
    private String impactOnTraffic;
    @Expose
    private String type;
    @Expose
    private Validity validity;
    @Expose
    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEffectOnRoadLayout() {
        return effectOnRoadLayout;
    }

    public void setEffectOnRoadLayout(String effectOnRoadLayout) {
        this.effectOnRoadLayout = effectOnRoadLayout;
    }

    public String getRoadMaintenanceType() {
        return roadMaintenanceType;
    }

    public void setRoadMaintenanceType(String roadMaintenanceType) {
        this.roadMaintenanceType = roadMaintenanceType;
    }

    public String getImpactOnTraffic() {
        return impactOnTraffic;
    }

    public void setImpactOnTraffic(String impactOnTraffic) {
        this.impactOnTraffic = impactOnTraffic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Validity getValidity() {
        return validity;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
