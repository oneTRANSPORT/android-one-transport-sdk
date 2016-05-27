package net.uk.onetransport.android.county.bucks.roadworks;

import com.google.gson.annotations.Expose;

public class Validity {

    @Expose
    private String status;
    @Expose
    private String overallStartTime;
    @Expose
    private String overallEndTime;
    @Expose
    private Period[] periods;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOverallStartTime() {
        return overallStartTime;
    }

    public void setOverallStartTime(String overallStartTime) {
        this.overallStartTime = overallStartTime;
    }

    public String getOverallEndTime() {
        return overallEndTime;
    }

    public void setOverallEndTime(String overallEndTime) {
        this.overallEndTime = overallEndTime;
    }

    public Period[] getPeriods() {
        return periods;
    }

    public void setPeriods(Period[] periods) {
        this.periods = periods;
    }
}
