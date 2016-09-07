package net.uk.onetransport.android.modules.clearviewsilverstone.generic;

import java.util.ArrayList;

public class RetrieverResult<T> {

    private ArrayList<T> ts;
    private ArrayList<Exception> exceptions;

    public RetrieverResult(ArrayList<T> ts, ArrayList<Exception> exceptions) {
        this.ts = ts;
        this.exceptions = exceptions;
    }

    public ArrayList<T> getTs() {
        return ts;
    }

    public ArrayList<Exception> getExceptions() {
        return exceptions;
    }
}
