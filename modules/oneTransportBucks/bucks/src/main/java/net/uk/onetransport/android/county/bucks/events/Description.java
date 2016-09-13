package net.uk.onetransport.android.county.bucks.events;

import com.google.gson.annotations.Expose;

public class Description {

    @Expose
    private String lang;
    @Expose
    private String content;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
