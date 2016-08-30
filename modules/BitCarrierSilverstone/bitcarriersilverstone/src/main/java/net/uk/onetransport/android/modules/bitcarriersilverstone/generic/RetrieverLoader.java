package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class RetrieverLoader<T> extends AsyncTaskLoader<RetrieverResult<T>> {

    private Retriever<T> retriever;
    private RetrieverResult<T> retrieverResult;

    public RetrieverLoader(Context context, Retriever<T> retriever) {
        super(context);
        this.retriever = retriever;
        retrieverResult = new RetrieverResult<>(new ArrayList<T>(), new ArrayList<Exception>());
    }

    @Override
    public void deliverResult(RetrieverResult<T> retrieverResult) {
        if (isReset()) {
            return;
        }
        this.retrieverResult = retrieverResult;
        super.deliverResult(retrieverResult);
    }

    @Override
    protected void onStartLoading() {
        if (retrieverResult.getTs().size() != 0) {
            deliverResult(retrieverResult);
        }
        if (takeContentChanged() || retrieverResult.getTs().size() == 0) {
            forceLoad();
        }
    }

    @Override
    public RetrieverResult<T> loadInBackground() {
        try {
            retriever.retrieve(retrieverResult.getTs());
        } catch (Exception exception) {
            retrieverResult.getExceptions().add(exception);
        }
        return retrieverResult;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        retrieverResult = new RetrieverResult<>(new ArrayList<T>(), new ArrayList<Exception>());
    }
}
