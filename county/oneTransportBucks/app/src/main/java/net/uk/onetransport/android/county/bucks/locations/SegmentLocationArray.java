package net.uk.onetransport.android.county.bucks.locations;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;

public class SegmentLocationArray extends BaseArray implements DougalCallback {

    private String retrievePath = "BCCFeedImportPredefinedLinkLocation/All";

    private SegmentLocation[] segmentLocations;
    private SegmentLocationArrayCallback segmentLocationArrayCallback;
    private int id;

    public SegmentLocationArray(){
    }

    public SegmentLocationArray(SegmentLocation[] segmentLocations) {
        this.segmentLocations = segmentLocations;
    }

    public static SegmentLocationArray getSegmentLocationArray(String aeId, String baseUrl,
                                                               String path, String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, path,
                userName, password);
        String content = contentInstance.getContent();
        return new SegmentLocationArray(GSON.fromJson(content, SegmentLocation[].class));
    }

    public static void getSegmentLocationArrayAsync(String aeId, String baseUrl, String path,
                                                    String userName, String password,
                                                    SegmentLocationArrayCallback segmentLocationArrayCallback,
                                                    int id) {
        SegmentLocationArray segmentLocationArray = new SegmentLocationArray();
        segmentLocationArray.segmentLocationArrayCallback = segmentLocationArrayCallback;
        segmentLocationArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, path, userName, password,
                segmentLocationArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            segmentLocationArrayCallback.onSegmentLocationArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                segmentLocations = GSON.fromJson(content, SegmentLocation[].class);
                segmentLocationArrayCallback.onSegmentLocationArrayReady(id, this);
            } catch (Exception e) {
                segmentLocationArrayCallback.onSegmentLocationArrayError(id, e);
            }
        }
    }

    public SegmentLocation[] getSegmentLocations() {
        return segmentLocations;
    }

    public void setSegmentLocations(SegmentLocation[] segmentLocations) {
        this.segmentLocations = segmentLocations;
    }
}
