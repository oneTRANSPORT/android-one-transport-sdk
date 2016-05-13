package net.uk.onetransport.android.county.bucks.locations;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;

public class PredefinedLinkLocationArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportPredefinedLinkLocation/All";

    private PredefinedLinkLocation[] predefinedLinkLocations;
    private PredefinedLinkLocationArrayCallback predefinedLinkLocationArrayCallback;
    private int id;

    private PredefinedLinkLocationArray() {
    }

    public PredefinedLinkLocationArray(PredefinedLinkLocation[] predefinedLinkLocations) {
        this.predefinedLinkLocations = predefinedLinkLocations;
    }

    public static PredefinedLinkLocationArray getPredefinedLinkLocationArray(String aeId, String baseUrl,
                                                                         String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new PredefinedLinkLocationArray(GSON.fromJson(content, PredefinedLinkLocation[].class));
    }

    public static void getPredefinedLinkLocationArrayAsync(String aeId, String baseUrl, String userName,
                                                       String password,
                                                       PredefinedLinkLocationArrayCallback predefinedLinkLocationArrayCallback,
                                                           int id) {
        PredefinedLinkLocationArray predefinedLinkLocationArray = new PredefinedLinkLocationArray();
        predefinedLinkLocationArray.predefinedLinkLocationArrayCallback = predefinedLinkLocationArrayCallback;
        predefinedLinkLocationArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                predefinedLinkLocationArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            predefinedLinkLocationArrayCallback.onPredefinedLinkLocationArrayError(id, throwable);
        } else {
            String content = ((ContentInstance) resource).getContent();
            predefinedLinkLocations = GSON.fromJson(content, PredefinedLinkLocation[].class);
            predefinedLinkLocationArrayCallback.onPredefinedLinkLocationArrayReady(id, this);
        }
    }

    public PredefinedLinkLocation[] getPredefinedLinkLocations() {
        return predefinedLinkLocations;
    }
}
