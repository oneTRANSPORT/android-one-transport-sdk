package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import java.util.List;

public class RetrieverResult<T> {

    private List<T> ts;
    private List<Exception> exceptions;

    public RetrieverResult(List<T> ts, List<Exception> exceptions) {
        this.ts = ts;
        this.exceptions = exceptions;
    }

    public List<T> getTs() {
        return ts;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }
}
