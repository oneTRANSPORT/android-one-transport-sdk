package net.uk.onetransport.android.county.bucks.locations;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;

public class SegmentLocationArray extends BaseArray implements DougalCallback {

    private static final String LINK_PATH = "BCCFeedImportPredefinedLinkLocation/All";
    private static final String SECTION_PATH = "BCCFeedImportPredefinedSectionLocation/All";
    private static final String TR_PATH = "BCCFeedImportPredefinedTrLocation/All";

    private SegmentLocation[] segmentLocations;
    private SegmentLocationArrayCallback segmentLocationArrayCallback;
    private int id;
    private int completed;
    private Resource[] resources = new Resource[3];
    private Throwable[] throwables = new Throwable[3];

    public SegmentLocationArray() {
    }

    public SegmentLocationArray(SegmentLocation[] segmentLocations) {
        this.segmentLocations = segmentLocations;
    }

    public static SegmentLocationArray getSegmentLocationArray(String aeId, String baseUrl,
                                                               String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, LINK_PATH,
                userName, password);
        SegmentLocationArray linkArray = new SegmentLocationArray(
                GSON.fromJson(contentInstance.getContent(), SegmentLocation[].class));
        contentInstance = Container.retrieveLatest(aeId, baseUrl, SECTION_PATH,
                userName, password);
        SegmentLocationArray sectionArray = new SegmentLocationArray(
                GSON.fromJson(contentInstance.getContent(), SegmentLocation[].class));
        contentInstance = Container.retrieveLatest(aeId, baseUrl, TR_PATH,
                userName, password);
        SegmentLocationArray trArray = new SegmentLocationArray(
                GSON.fromJson(contentInstance.getContent(), SegmentLocation[].class));
        int linkLen = linkArray.getSegmentLocations().length;
        int sectionLen = sectionArray.getSegmentLocations().length;
        int trLen = trArray.getSegmentLocations().length;
        SegmentLocation[] segmentLocations = new SegmentLocation[linkLen + sectionLen + trLen];

        System.arraycopy(linkArray.getSegmentLocations(), 0, segmentLocations, 0, linkLen);
        System.arraycopy(sectionArray.getSegmentLocations(), 0, segmentLocations, linkLen, sectionLen);
        System.arraycopy(trArray.getSegmentLocations(), 0, segmentLocations, linkLen + sectionLen, trLen);

        SegmentLocationArray resultArray = new SegmentLocationArray();
        resultArray.setSegmentLocations(segmentLocations);
        return resultArray;
    }

    public static void getSegmentLocationArrayAsync(String aeId, String baseUrl,
                                                    String userName, String password,
                                                    SegmentLocationArrayCallback segmentLocationArrayCallback, int id) {
        SegmentLocationArray segmentLocationArray = new SegmentLocationArray();
        segmentLocationArray.segmentLocationArrayCallback = segmentLocationArrayCallback;
        segmentLocationArray.id = id;
        segmentLocationArray.completed = 0;
        Container.retrieveLatestAsync(aeId, baseUrl, LINK_PATH, userName, password,
                segmentLocationArray);
        Container.retrieveLatestAsync(aeId, baseUrl, SECTION_PATH, userName, password,
                segmentLocationArray);
        Container.retrieveLatestAsync(aeId, baseUrl, TR_PATH, userName, password,
                segmentLocationArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        resources[completed] = resource;
        throwables[completed] = throwable;
        completed++;
        if (completed == 3) {
            if (throwables[0] != null || throwables[1] != null || throwables[2] != null) {
                for (Throwable th : throwables) {
                    if (th != null) {
                        // Only return the first error, one callback.
                        segmentLocationArrayCallback.onSegmentLocationArrayError(id, throwable);
                        return;
                    }
                }
            } else {
                try {
                    SegmentLocation[][] segmentLocationss = new SegmentLocation[3][];
                    for (int i = 0; i < resources.length; i++) {
                        String content = ((ContentInstance) resources[i]).getContent();
                        segmentLocationss[i] = GSON.fromJson(content, SegmentLocation[].class);
                    }
                    int totalLength = 0;
                    for (int i = 0; i < segmentLocationss.length; i++) {
                        totalLength += segmentLocationss[i].length;
                    }
                    segmentLocations = new SegmentLocation[totalLength];
                    System.arraycopy(segmentLocationss[0], 0, segmentLocations, 0, segmentLocationss[0].length);
                    System.arraycopy(segmentLocationss[1], 0, segmentLocations, segmentLocationss[0].length, segmentLocationss[1].length);
                    System.arraycopy(segmentLocationss[2], 0, segmentLocations, segmentLocationss[0].length + segmentLocationss[1].length, segmentLocationss[2].length);
                    segmentLocationArrayCallback.onSegmentLocationArrayReady(id, this);
                } catch (Exception e) {
                    segmentLocationArrayCallback.onSegmentLocationArrayError(id, e);
                }
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
