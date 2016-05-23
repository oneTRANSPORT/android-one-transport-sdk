package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import com.google.gson.annotations.Expose;

public class VariableMessageSign {

    @Expose
    private String locationReference;
    @Expose
    private Integer numberOfCharacters;
    @Expose
    private Integer numberOfRows;
    @Expose
    private String[] vmsLegends;
    @Expose
    private String vmsType;

    public String getLocationReference() {
        return locationReference;
    }

    public void setLocationReference(String locationReference) {
        this.locationReference = locationReference;
    }

    public Integer getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(Integer numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public String[] getVmsLegends() {
        return vmsLegends;
    }

    public void setVmsLegends(String[] vmsLegends) {
        this.vmsLegends = vmsLegends;
    }

    public String getVmsType() {
        return vmsType;
    }

    public void setVmsType(String vmsType) {
        this.vmsType = vmsType;
    }
}
