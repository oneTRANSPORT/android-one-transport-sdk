package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.ResourceChild;

import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

import java.util.ArrayList;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public Retriever() {
    }

    public void retrieve(Context context, ArrayList<T> list) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        // Get the names of child resources.
        ResourceChild[] children = Container.retrieveChildren(aeId, cseBaseUrl, getRetrivePrefix(),
                userName, password).getResourceChildren();
        for (int i = 0; i < children.length; i++) {
            String name = children[i].getName();
            // TODO    Not just the latest, go back as far as needed.
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    getRetrivePrefix() + "/" + name, userName, password);
            String content = contentInstance.getContent();
            list.add(fromJson(content));
        }
    }

    protected abstract String getRetrivePrefix();

    protected abstract T fromJson(String content);

}
