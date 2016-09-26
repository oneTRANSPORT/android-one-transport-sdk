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

    public String getPeriodsAsString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < periods.length; i++) {
            builder.append(periods[i].getStart());
            builder.append("|");
            builder.append(periods[i].getEnd());
            if (i < periods.length - 1) {
                builder.append("|");
            }
        }
        return builder.toString();
    }

    public void setPeriodsFromString(String periodString) {
        String[] bits = periodString.split("\\|");
        Period[] periods = new Period[bits.length / 2];
        for (int i = 0; i < bits.length; i += 2) {
            Period period = new Period();
            period.setStart(bits[i]);
            period.setEnd(bits[i + 1]);
            periods[i / 2] = period;
        }
    }
}
