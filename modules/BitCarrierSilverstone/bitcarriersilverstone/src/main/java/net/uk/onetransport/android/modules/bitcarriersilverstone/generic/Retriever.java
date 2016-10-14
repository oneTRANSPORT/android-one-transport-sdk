package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.ResourceChild;

import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Context context;

    public Retriever(Context context) {
        this.context = context;
    }

    public void retrieve(ArrayList<T> list) throws Exception {
        String aeId = CredentialHelper.getAeId(context);
        String token = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        // Get the names of child resources.
        ResourceChild[] containerChildren = Container.retrieveChildren(aeId, cseBaseUrl, getRetrievePrefix(),
                token).getResourceChildren();
        for (int i = 0; i < containerChildren.length; i++) {
            String name = containerChildren[i].getName();
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    getRetrievePrefix() + "/" + name, token);
            String cinId = contentInstance.getResourceId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
            Long creationTime = null;
            if (contentInstance.getCreationTime() != null) {
                Date date = sdf.parse(contentInstance.getCreationTime());
                creationTime = date.getTime() / 1000L; // Millis to seconds.  Save a bit of db space.
            }
            String content = contentInstance.getContent();
            list.add(fromJson(content, cinId, creationTime));
        }
    }

    protected abstract String getRetrievePrefix();

    protected abstract T fromJson(String content, String cinId, Long creationTime);

}
