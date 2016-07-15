package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;

import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

import java.util.ArrayList;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public Retriever() {
    }

    protected void retrieve(Context context, ArrayList<T> list) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        int[] ids = getIds();
        for (int i = 0; i < ids.length; i++) {
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    getRetrivePrefix() + String.valueOf(ids[i]), userName, password);
            String content = contentInstance.getContent();
            list.add(fromJson(content));
        }
    }

    protected abstract int[] getIds();

    protected abstract String getRetrivePrefix();

    protected abstract T fromJson(String content);

}
