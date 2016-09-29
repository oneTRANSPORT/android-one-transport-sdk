package net.uk.onetransport.android.county.oxon.generic;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class RetrieverLoader<T> extends AsyncTaskLoader<RetrieverResult<T>> {

    private Retriever<T> retriever;
    private RetrieverResult<T> retrieverResult;

    public RetrieverLoader(Context context, Retriever<T> retriever) {
        super(context);
        this.retriever = retriever;
        retrieverResult = new RetrieverResult<>();
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
        if (retrieverResult.getContent() != null) {
            deliverResult(retrieverResult);
        }
        if (takeContentChanged() || retrieverResult.getContent() == null) {
            forceLoad();
        }
    }

    @Override
    public RetrieverResult<T> loadInBackground() {
        try {
            retrieverResult.setContent(retriever.retrieve());
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
        retrieverResult = new RetrieverResult<>();
    }
}
