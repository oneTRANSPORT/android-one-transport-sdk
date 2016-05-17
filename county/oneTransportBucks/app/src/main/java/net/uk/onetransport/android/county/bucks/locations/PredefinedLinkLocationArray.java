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
            try {
                String content = ((ContentInstance) resource).getContent();
                predefinedLinkLocations = GSON.fromJson(content, PredefinedLinkLocation[].class);
                predefinedLinkLocationArrayCallback.onPredefinedLinkLocationArrayReady(id, this);
            } catch (Exception e) {
                predefinedLinkLocationArrayCallback.onPredefinedLinkLocationArrayError(id, e);
            }
        }
    }

    public void insertIntoProvider(Context context) {
        if (predefinedLinkLocations != null && predefinedLinkLocations.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (PredefinedLinkLocation predefinedLinkLocation : predefinedLinkLocations) {
                values.clear();
                values.put(BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                        predefinedLinkLocation.getLocationId());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                        predefinedLinkLocation.getToLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                        predefinedLinkLocation.getToLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                        predefinedLinkLocation.getFromLatitude());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                        predefinedLinkLocation.getFromLongitude());
                values.put(BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                        predefinedLinkLocation.getToDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                        predefinedLinkLocation.getFromDescriptor());
                values.put(BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION,
                        predefinedLinkLocation.getTpegDirection());
                contentResolver.insert(BucksProvider.SEGMENT_LOCATION_URI, values);
            }
        }
    }

    public static void deleteFromProvider(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.delete(BucksProvider.SEGMENT_LOCATION_URI,
                BucksContract.SegmentLocation.COLUMN_LOCATION_ID + " like 'LINKBUCK-%'", null);
    }

    public PredefinedLinkLocation[] getPredefinedLinkLocations() {
        return predefinedLinkLocations;
    }
}
