package net.uk.onetransport.android.county.bucks.locations;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;

public class PredefinedSectionLocationArray extends SegmentLocationArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportPredefinedSectionLocation/All";

    private PredefinedSectionLocationArray() {
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
