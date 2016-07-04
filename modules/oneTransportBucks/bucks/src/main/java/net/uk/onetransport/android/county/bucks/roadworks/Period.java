package net.uk.onetransport.android.county.bucks.roadworks;

import com.google.gson.annotations.Expose;

public class Period {

    @Expose
    private String start;
    @Expose
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
