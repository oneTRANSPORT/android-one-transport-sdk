package net.uk.onetransport.android.county.oxon.generic;

import java.util.ArrayList;

public class RetrieverResult<T> {

    private T[] content = null;
    private ArrayList<Exception> exceptions = new ArrayList<>();

    public T[] getContent() {
        return content;
    }

    public ArrayList<Exception> getExceptions() {
        return exceptions;
    }

    public void setContent(T[] content) {
        this.content = content;
    }
}