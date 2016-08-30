package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;
import android.database.Cursor;

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
import java.util.HashSet;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Context context;

    public Retriever(Context context) {
        this.context = context;
    }

    public void retrieve(ArrayList<T> list) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        // Get the names of child resources.
        ResourceChild[] containerChildren = Container.retrieveChildren(aeId, cseBaseUrl, getRetrievePrefix(),
                userName, password).getResourceChildren();
        for (int i = 0; i < containerChildren.length; i++) {
            String name = containerChildren[i].getName();
            HashSet<String> existingNames = getExistingNames(Integer.parseInt(
                    name.replaceFirst("[^0-9]", "")));

            // TODO    Not just the latest, go back as far as needed.
            // TODO    Make every download use a retriever.
            // TODO    Update database to keep proper timestamps.
            // TODO    Query by time interval?

            ResourceChild[] containerCis = Container.retrieveChildren(aeId, cseBaseUrl,
                    getRetrievePrefix() + "/" + name, userName, password).getResourceChildren();
            // Prevent overload by restricting to 100 new CIs per type.
            for (int j = 0; j < containerCis.length && list.size() < 100; j++) {
                String ciName = containerCis[j].getName();
                if (!existingNames.contains(ciName)) {
                    ContentInstance contentInstance = ContentInstance.retrieve(aeId, cseBaseUrl,
                            getRetrievePrefix() + "/" + name + "/" + ciName, userName, password);
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
        }
    }

    protected abstract String getRetrievePrefix();

    protected abstract T fromJson(String content, String cinId, Long creationTime);

    protected abstract Cursor getResourceNames(Context context, int id);

    private HashSet<String> getExistingNames(int id) {
        HashSet<String> existingNames = new HashSet<>();
        Cursor cursor = getResourceNames(context, id);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    existingNames.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return existingNames;
    }
}
