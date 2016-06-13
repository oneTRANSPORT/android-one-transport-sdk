//package net.uk.onetransport.android.county.bucks.locations;
//
//import android.content.Context;
//
//import com.interdigital.android.dougal.resource.Container;
//import com.interdigital.android.dougal.resource.ContentInstance;
//import com.interdigital.android.dougal.resource.Resource;
//import com.interdigital.android.dougal.resource.callback.DougalCallback;
//
//import net.uk.onetransport.android.county.bucks.BaseArray;
//import net.uk.onetransport.android.county.bucks.R;
//import net.uk.onetransport.android.county.bucks.authentication.CredentialHelper;
//import net.uk.onetransport.android.county.bucks.storage.Prefs;
//
//public class SegmentLocationArray extends BaseArray implements DougalCallback {
//
//    private static final String LINK_PATH = "BCCPredefinedLocationLinkFeedImport/All";
//    private static final String SECTION_PATH = "BCCPredefinedLocationSectionFeedImport/All";
//    private static final String TR_PATH = "BCCPredefinedLocationTransportRouteFeedImport/All";
//
//    private SegmentLocation[] segmentLocations;
//    private SegmentLocationArrayCallback segmentLocationArrayCallback;
//    private int id;
//    private int completed;
//    private Resource[] resources = new Resource[3];
//    private Throwable[] throwables = new Throwable[3];
//
//    public SegmentLocationArray() {
//    }
//
//    public SegmentLocationArray(SegmentLocation[] segmentLocations) {
//        this.segmentLocations = segmentLocations;
//    }
//
//    public static SegmentLocationArray getSegmentLocationArray(Context context) throws Exception {
//        String aeId =  "C-"+CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
//        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, LINK_PATH,
//                userName, password);
//        SegmentLocationArray linkArray = new SegmentLocationArray(
//                GSON.fromJson(contentInstance.getContent(), SegmentLocation[].class));
//        contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, SECTION_PATH,
//                userName, password);
//        SegmentLocationArray sectionArray = new SegmentLocationArray(
//                GSON.fromJson(contentInstance.getContent(), SegmentLocation[].class));
//        contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, TR_PATH,
//                userName, password);
//        SegmentLocationArray trArray = new SegmentLocationArray(
//                GSON.fromJson(contentInstance.getContent(), SegmentLocation[].class));
//        int linkLen = linkArray.getSegmentLocations().length;
//        int sectionLen = sectionArray.getSegmentLocations().length;
//        int trLen = trArray.getSegmentLocations().length;
//        SegmentLocation[] segmentLocations = new SegmentLocation[linkLen + sectionLen + trLen];
//
//        System.arraycopy(linkArray.getSegmentLocations(), 0, segmentLocations, 0, linkLen);
//        System.arraycopy(sectionArray.getSegmentLocations(), 0, segmentLocations, linkLen, sectionLen);
//        System.arraycopy(trArray.getSegmentLocations(), 0, segmentLocations, linkLen + sectionLen, trLen);
//
//        SegmentLocationArray resultArray = new SegmentLocationArray();
//        resultArray.setSegmentLocations(segmentLocations);
//        return resultArray;
//    }
//
//    public static void getSegmentLocationArrayAsync(Context context,
//                                                    SegmentLocationArrayCallback segmentLocationArrayCallback, int id) {
//        SegmentLocationArray segmentLocationArray = new SegmentLocationArray();
//        segmentLocationArray.segmentLocationArrayCallback = segmentLocationArrayCallback;
//        segmentLocationArray.id = id;
//        segmentLocationArray.completed = 0;
//        String aeId =  "C-"+CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
//        Container.retrieveLatestAsync(aeId, cseBaseUrl, LINK_PATH, userName, password,
//                segmentLocationArray);
//        Container.retrieveLatestAsync(aeId, cseBaseUrl, SECTION_PATH, userName, password,
//                segmentLocationArray);
//        Container.retrieveLatestAsync(aeId, cseBaseUrl, TR_PATH, userName, password,
//                segmentLocationArray);
//    }
//
//    @Override
//    public void getResponse(Resource resource, Throwable throwable) {
//        resources[completed] = resource;
//        throwables[completed] = throwable;
//        completed++;
//        if (completed == 3) {
//            if (throwables[0] != null || throwables[1] != null || throwables[2] != null) {
//                for (Throwable th : throwables) {
//                    if (th != null) {
//                        // Only return the first error, one callback.
//                        segmentLocationArrayCallback.onSegmentLocationArrayError(id, throwable);
//                        return;
//                    }
//                }
//            } else {
//                try {
//                    SegmentLocation[][] segmentLocationss = new SegmentLocation[3][];
//                    for (int i = 0; i < resources.length; i++) {
//                        String content = ((ContentInstance) resources[i]).getContent();
//                        segmentLocationss[i] = GSON.fromJson(content, SegmentLocation[].class);
//                    }
//                    int totalLength = 0;
//                    for (int i = 0; i < segmentLocationss.length; i++) {
//                        totalLength += segmentLocationss[i].length;
//                    }
//                    segmentLocations = new SegmentLocation[totalLength];
//                    System.arraycopy(segmentLocationss[0], 0, segmentLocations, 0, segmentLocationss[0].length);
//                    System.arraycopy(segmentLocationss[1], 0, segmentLocations, segmentLocationss[0].length, segmentLocationss[1].length);
//                    System.arraycopy(segmentLocationss[2], 0, segmentLocations, segmentLocationss[0].length + segmentLocationss[1].length, segmentLocationss[2].length);
//                    segmentLocationArrayCallback.onSegmentLocationArrayReady(id, this);
//                } catch (Exception e) {
//                    segmentLocationArrayCallback.onSegmentLocationArrayError(id, e);
//                }
//            }
//        }
//    }
//
//    public SegmentLocation[] getSegmentLocations() {
//        return segmentLocations;
//    }
//
//    public void setSegmentLocations(SegmentLocation[] segmentLocations) {
//        this.segmentLocations = segmentLocations;
//    }
//}
