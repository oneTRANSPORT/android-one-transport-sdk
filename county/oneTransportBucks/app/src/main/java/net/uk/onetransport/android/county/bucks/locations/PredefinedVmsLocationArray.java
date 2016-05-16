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
            try {
                String content = ((ContentInstance) resource).getContent();
                predefinedVmsLocations = GSON.fromJson(content, PredefinedVmsLocation[].class);
                predefinedVmsLocationArrayCallback.onPredefinedVmsLocationArrayReady(id, this);
            } catch (Exception e) {
                predefinedVmsLocationArrayCallback.onPredefinedVmsLocationArrayError(id, e);
            }
        }
    }

    public void insertIntoProvider(Context context) {
        if (predefinedVmsLocations != null && predefinedVmsLocations.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (PredefinedVmsLocation predefinedVmsLocation : predefinedVmsLocations) {
                values.clear();
                values.put(BucksContract.VmsLocation.COLUMN_NAME,
                        predefinedVmsLocation.getName());
                values.put(BucksContract.VmsLocation.COLUMN_LOCATION_ID,
                        predefinedVmsLocation.getLocationId());
                values.put(BucksContract.VmsLocation.COLUMN_LATITUDE,
                        predefinedVmsLocation.getLatitude());
                values.put(BucksContract.VmsLocation.COLUMN_LONGITUDE,
                        predefinedVmsLocation.getLongitude());
                values.put(BucksContract.VmsLocation.COLUMN_DESCRIPTOR,
                        predefinedVmsLocation.getDescriptor());
                values.put(BucksContract.VmsLocation.COLUMN_TPEG_DIRECTION,
                        predefinedVmsLocation.getTpegDirection());
                contentResolver.insert(BucksProvider.VMS_LOCATION_URI, values);
            }
        }
    }

    public PredefinedVmsLocation[] getPredefinedVmsLocations() {
        return predefinedVmsLocations;
    }
}
