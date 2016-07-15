package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;

import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

import java.util.ArrayList;

public abstract class RetrieverLoader<T> extends AsyncTaskLoader<RetrieverResult<T>> {

    private ArrayList<T> ts;

    public RetrieverLoader(Context context, ArrayList<T> ts) {
        super(context);
        this.ts = ts;
    }

    @Override
    public RetrieverResult<T> loadInBackground() {
        Context context = getContext();
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        int[] ids = getIds();
        ArrayList<Exception> exceptions = new ArrayList<>();
        for (int i = 0; i < ids.length && !isLoadInBackgroundCanceled(); i++) {
            try {
                ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                        getRetrivePrefix() + String.valueOf(ids[i]), userName, password);
                String content = contentInstance.getContent();
                ts.add(fromJson(content));
            } catch (Exception exception) {
                exceptions.add(exception);
            }
        }
        return new RetrieverResult<>(ts, exceptions);
    }

    protected abstract int[] getIds();

    protected abstract String getRetrivePrefix();

    protected abstract T fromJson(String content);
}
