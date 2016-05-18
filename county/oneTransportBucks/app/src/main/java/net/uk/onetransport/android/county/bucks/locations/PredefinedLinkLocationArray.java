package net.uk.onetransport.android.county.bucks.locations;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

public class PredefinedLinkLocationArray extends SegmentLocationArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportPredefinedLinkLocation/All";

    private PredefinedLinkLocationArray() {
    }

    public static SegmentLocationArray getSegmentLocationArray(String aeId, String baseUrl,
                                                                      String userName, String password) throws Exception {
        return getSegmentLocationArray(aeId, baseUrl, RETRIEVE_PATH, userName, password);
    }

    public static void getSegmentLocationArrayAsync(String aeId, String baseUrl, String userName,
                                                           String password,
                                                           SegmentLocationArrayCallback segmentLocationArrayCallback,
                                                           int id) {
        getSegmentLocationArrayAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                segmentLocationArrayCallback, id);
    }
}
