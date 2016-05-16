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

public class PredefinedTrLocationArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportPredefinedTrLocation/All";

    private PredefinedTrLocation[] predefinedTrLocations;
    private PredefinedTrLocationArrayCallback predefinedTrLocationArrayCallback;
    private int id;

    private PredefinedTrLocationArray() {
    }

    public PredefinedTrLocationArray(PredefinedTrLocation[] predefinedTrLocations) {
        this.predefinedTrLocations = predefinedTrLocations;
    }

    public static PredefinedTrLocationArray getPredefinedTrLocationArray(String aeId, String baseUrl,
                                                                         String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new PredefinedTrLocationArray(GSON.fromJson(content, PredefinedTrLocation[].class));
    }

    public static void getPredefinedTrLocationArrayAsync(String aeId, String baseUrl, String userName,
                                                         String password,
                                                         PredefinedTrLocationArrayCallback predefinedTrLocationArrayCallback,
                                                         int id) {
        PredefinedTrLocationArray predefinedTrLocationArray = new PredefinedTrLocationArray();
        predefinedTrLocationArray.predefinedTrLocationArrayCallback = predefinedTrLocationArrayCallback;
        predefinedTrLocationArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                predefinedTrLocationArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            predefinedTrLocationArrayCallback.onPredefinedTrLocationArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                predefinedTrLocations = GSON.fromJson(content, PredefinedTrLocation[].class);
                predefinedTrLocationArrayCallback.onPredefinedTrLocationArrayReady(id, this);
            } catch (Exception e) {
                predefinedTrLocationArrayCallback.onPredefinedTrLocationArrayError(id, e);
            }
        }
    }

    public void insertIntoProvider(Context context) {
        if (predefinedTrLocations != null && predefinedTrLocations.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (PredefinedTrLocation predefinedTrLocation : predefinedTrLocations) {
                values.clear();
                values.put(BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                        predefinedTrLocation.getLocationId());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                        predefinedTrLocation.getToLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                        predefinedTrLocation.getToLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                        predefinedTrLocation.getFromLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                        predefinedTrLocation.getFromLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                        predefinedTrLocation.getToDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                        predefinedTrLocation.getFromDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION,
                        predefinedTrLocation.getTpegDirection());
                contentResolver.insert(BucksProvider.SEGMENT_LOCATION_URI, values);
            }
        }
    }

    public PredefinedTrLocation[] getPredefinedTrLocations() {
        return predefinedTrLocations;
    }
}
