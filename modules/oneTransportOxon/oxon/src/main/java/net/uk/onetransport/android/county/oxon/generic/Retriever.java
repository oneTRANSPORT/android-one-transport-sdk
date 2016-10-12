package net.uk.onetransport.android.county.oxon.generic;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;

import net.uk.onetransport.android.county.oxon.R;
import net.uk.onetransport.android.county.oxon.authentication.CredentialHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Context context;

    public Retriever(Context context) {
        this.context = context;
    }

    public T[] retrieve() throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.oxon_cse_base_url);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                getRetrievePrefix(), userName, password);
        String cinId = contentInstance.getResourceId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Long creationTime = null;
        if (contentInstance.getCreationTime() != null) {
            Date date = sdf.parse(contentInstance.getCreationTime());
            creationTime = date.getTime() / 1000L; // Millis to seconds.  Save a bit of db space.
        }
        String content = contentInstance.getContent();
        return fromJson(content, cinId, creationTime);
    }

    protected abstract String getRetrievePrefix();

    protected abstract T[] fromJson(String content, String cinId, Long creationTime);

}
