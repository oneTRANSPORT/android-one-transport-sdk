package net.uk.onetransport.android.county.bucks.locations;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;

public class PredefinedVmsLocationArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportPredefinedVmsLocation/All";

    private PredefinedVmsLocation[] predefinedVmsLocations;
    private PredefinedVmsLocationArrayCallback predefinedVmsLocationArrayCallback;
    private int id;

    private PredefinedVmsLocationArray() {
    }

    public PredefinedVmsLocationArray(PredefinedVmsLocation[] predefinedVmsLocations) {
        this.predefinedVmsLocations = predefinedVmsLocations;
    }

    public static PredefinedVmsLocationArray getPredefinedVmsLocationArray(String aeId, String baseUrl,
                                                                           String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new PredefinedVmsLocationArray(GSON.fromJson(content, PredefinedVmsLocation[].class));
    }

    public static void getPredefinedVmsLocationArrayAsync(String aeId, String baseUrl, String userName,
                                                          String password,
                                                          PredefinedVmsLocationArrayCallback predefinedVmsLocationArrayCallback,
                                                          int id) {
        PredefinedVmsLocationArray predefinedVmsLocationArray = new PredefinedVmsLocationArray();
        predefinedVmsLocationArray.predefinedVmsLocationArrayCallback = predefinedVmsLocationArrayCallback;
        predefinedVmsLocationArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                predefinedVmsLocationArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            predefinedVmsLocationArrayCallback.onPredefinedVmsLocationArrayError(id, throwable);
        } else {
            String content = ((ContentInstance) resource).getContent();
            predefinedVmsLocations = GSON.fromJson(content, PredefinedVmsLocation[].class);
            predefinedVmsLocationArrayCallback.onPredefinedVmsLocationArrayReady(id, this);
        }
    }

    public PredefinedVmsLocation[] getPredefinedVmsLocations() {
        return predefinedVmsLocations;
    }
}
