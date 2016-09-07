package net.uk.onetransport.android.modules.clearviewsilverstone.generic;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.ResourceChild;

import net.uk.onetransport.android.modules.clearviewsilverstone.R;
import net.uk.onetransport.android.modules.clearviewsilverstone.authentication.CredentialHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Context context;

    public Retriever(Context context) {
        this.context = context;
    }

    public void retrieve(ArrayList<T> list, String container) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        // Get the names of child resources.
        // This is very ugly, but we have to follow the spec.
        ResourceChild[] children = retrieveChildren(aeId, cseBaseUrl, userName, password);
        for (int i = 0; i < children.length; i++) {
            String name = children[i].getName();
            if (TextUtils.isEmpty(container) || name.contains(container)) {
                ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                        getRetrievePrefix() + "/" + name, userName, password);
                String cinId = contentInstance.getResourceId();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                Long creationTime = null;
                if (contentInstance.getCreationTime() != null) {
                    Date date = sdf.parse(contentInstance.getCreationTime());
                    creationTime = date.getTime() / 1000L; // Millis to seconds.  Save a bit of db space.
                }
                String content = contentInstance.getContent();
                list.add(fromJson(content, name, cinId, creationTime));
            }
        }
    }

    protected abstract String getRetrievePrefix();

    protected abstract ResourceChild[] retrieveChildren(String aeId, String cseBaseUrl,
                                                        String userName, String password) throws Exception;

    protected abstract T fromJson(String content, String container, String cinId, Long creationTime);

}
