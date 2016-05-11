package net.uk.onetransport.android.county.bucks.locations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

public class PredefinedLocationArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportPredefinedLocation/All";

    private PredefinedLocation[] predefinedLocations;
    private PredefinedLocationArrayCallback predefinedLocationArrayCallback;
    private int id;

    public PredefinedLocationArray() {
    }

    public PredefinedLocationArray(PredefinedLocation[] predefinedLocations) {
        this.predefinedLocations = predefinedLocations;
    }

    public static PredefinedLocationArray getPredefinedLocationArray(String aeId, String baseUrl,
                                                                     String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        // TODO Use one instance of Gson everywhere.
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return new PredefinedLocationArray(gson.fromJson(content, PredefinedLocation[].class));
    }

    public static void getPredefinedLocationArrayAsync(String aeId, String baseUrl, String userName,
                                                       String password,
                                                       PredefinedLocationArrayCallback predefinedLocationArrayCallback, int id) {
        PredefinedLocationArray predefinedLocationArray = new PredefinedLocationArray();
        predefinedLocationArray.predefinedLocationArrayCallback = predefinedLocationArrayCallback;
        predefinedLocationArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                predefinedLocationArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            predefinedLocationArrayCallback.onPredefinedLocationArrayError(id, throwable);
        } else {
            String content = ((ContentInstance) resource).getContent();
            // TODO Use one instance of Gson everywhere.
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            predefinedLocations = gson.fromJson(content, PredefinedLocation[].class);
            predefinedLocationArrayCallback.onPredefinedLocationArrayReady(id, this);
        }
    }

    public PredefinedLocation[] getPredefinedLocations() {
        return predefinedLocations;
    }
}
