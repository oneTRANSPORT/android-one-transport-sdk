package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;

import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

import java.util.ArrayList;

public abstract class RetrieverLoader<T> extends AsyncTaskLoader<RetrieverResult<T>> {

    private RetrieverResult<T> retrieverResult;

    public RetrieverLoader(Context context) {
        super(context);
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
        Context context = getContext();
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        int[] ids = getIds();
        for (int i = 0; i < ids.length && !isLoadInBackgroundCanceled(); i++) {
            try {
                ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                        getRetrivePrefix() + String.valueOf(ids[i]), userName, password);
                String content = contentInstance.getContent();
                retrieverResult.getTs().add(fromJson(content));
            } catch (Exception exception) {
                retrieverResult.getExceptions().add(exception);
            }
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

    protected abstract int[] getIds();

    protected abstract String getRetrivePrefix();

    protected abstract T fromJson(String content);
}
