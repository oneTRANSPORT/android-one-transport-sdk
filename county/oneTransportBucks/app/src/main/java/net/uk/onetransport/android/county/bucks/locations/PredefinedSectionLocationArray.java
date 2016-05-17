package net.uk.onetransport.android.county.bucks.locations;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;

public class PredefinedSectionLocationArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportPredefinedSectionLocation/All";

    private PredefinedSectionLocation[] predefinedSectionLocations;
    private PredefinedSectionLocationArrayCallback predefinedSectionLocationArrayCallback;
    private int id;

    private PredefinedSectionLocationArray() {
    }

    public PredefinedSectionLocationArray(PredefinedSectionLocation[] predefinedSectionLocations) {
        this.predefinedSectionLocations = predefinedSectionLocations;
    }

    public static PredefinedSectionLocationArray getPredefinedSectionLocationArray(String aeId, String baseUrl,
                                                                                   String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new PredefinedSectionLocationArray(GSON.fromJson(content, PredefinedSectionLocation[].class));
    }

    public static void getPredefinedSectionLocationArrayAsync(String aeId, String baseUrl, String userName,
                                                              String password,
                                                              PredefinedSectionLocationArrayCallback predefinedSectionLocationArrayCallback,
                                                              int id) {
        PredefinedSectionLocationArray predefinedSectionLocationArray = new PredefinedSectionLocationArray();
        predefinedSectionLocationArray.predefinedSectionLocationArrayCallback = predefinedSectionLocationArrayCallback;
        predefinedSectionLocationArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                predefinedSectionLocationArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            predefinedSectionLocationArrayCallback.onPredefinedSectionLocationArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                predefinedSectionLocations = GSON.fromJson(content, PredefinedSectionLocation[].class);
                predefinedSectionLocationArrayCallback.onPredefinedSectionLocationArrayReady(id, this);
            } catch (Exception e) {
                predefinedSectionLocationArrayCallback.onPredefinedSectionLocationArrayError(id, e);
            }
        }
    }

    public PredefinedSectionLocation[] getPredefinedSectionLocations() {
        return predefinedSectionLocations;
    }
}
