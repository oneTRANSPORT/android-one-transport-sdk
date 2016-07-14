package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import java.util.ArrayList;
import java.util.List;

public class RetrieverResult<T> {

    private ArrayList<T> ts;
    private ArrayList<Exception> exceptions;

    public RetrieverResult(ArrayList<T> ts, ArrayList<Exception> exceptions) {
        this.ts = ts;
        this.exceptions = exceptions;
    }

    public List<T> getTs() {
        return ts;
    }

    public ArrayList<Exception> getExceptions() {
        return exceptions;
    }
}
