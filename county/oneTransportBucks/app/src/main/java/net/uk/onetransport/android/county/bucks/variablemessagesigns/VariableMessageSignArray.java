package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

public class VariableMessageSignArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCSignSettingFeedImport/All";

    private VariableMessageSign[] variableMessageSigns;
    private VariableMessageSignArrayCallback variableMessageSignArrayCallback;
    private int id;

    public VariableMessageSignArray() {
    }

    public VariableMessageSignArray(VariableMessageSign[] variableMessageSigns) {
        this.variableMessageSigns = variableMessageSigns;
    }

    public static VariableMessageSignArray getVariableMessageSignArray(String aeId, String baseUrl,
                                                                       String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        // TODO Use one instance of Gson everywhere.
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return new VariableMessageSignArray(gson.fromJson(content, VariableMessageSign[].class));
    }

    public static void getVariableMessageSignArrayAsync(String aeId, String baseUrl, String userName,
                                                        String password,
                                                        VariableMessageSignArrayCallback variableMessageSignArrayCallback, int id) {
        VariableMessageSignArray variableMessageSignArray = new VariableMessageSignArray();
        variableMessageSignArray.variableMessageSignArrayCallback = variableMessageSignArrayCallback;
        variableMessageSignArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                variableMessageSignArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            variableMessageSignArrayCallback.onVariableMessageSignArrayError(id, throwable);
        } else {
            String content = ((ContentInstance) resource).getContent();
            // TODO Use one instance of Gson everywhere.
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            variableMessageSigns = gson.fromJson(content, VariableMessageSign[].class);
            variableMessageSignArrayCallback.onVariableMessageSignArrayReady(id, this);
        }
    }

    public VariableMessageSign[] getVariableMessageSigns() {
        return variableMessageSigns;
    }
}
