package net.uk.onetransport.android.modules.bitcarriersilverstone.data.traveltime;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.BaseArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

public class TravelSummaryArray extends BaseArray implements DougalCallback {

    public static final int[] TRAVEL_IDS = {191, 192, 194, 196, 197, 199, 200, 203, 205, 206, 213, 216, 218,
            219, 222, 229, 232, 234, 236, 243, 244, 254, 255, 256, 259, 265, 270, 272, 273, 276, 280, 284};

    public static final String AE_NAME = "Worldsensing";
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/data/traveltimes/t";

    private static int completed = 0;

    private TravelSummary[] travelSummaries;
    private TravelSummaryArrayCallback travelSummaryArrayCallback;
    private int id; // TODO    Move to superclass?

    private TravelSummaryArray() {
    }

    public TravelSummaryArray(TravelSummary[] travelSummaries) {
        this.travelSummaries = travelSummaries;
    }

    public static TravelSummaryArray getTravelSummaryArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        TravelSummary[] newTravelSummaries = new TravelSummary[TRAVEL_IDS.length];
        for (int i = 0; i < TRAVEL_IDS.length; i++) {
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(TRAVEL_IDS[i]), userName, password);
            String content = contentInstance.getContent();
            newTravelSummaries[i] = GSON.fromJson(content, TravelSummary.class);
        }
        return new TravelSummaryArray(newTravelSummaries);
    }

    public static void getTravelSummaryArrayAsync(Context context,
                                                  TravelSummaryArrayCallback travelSummaryArrayCallback, int id) {
        TravelSummaryArray travelSummaryArray = new TravelSummaryArray();
        travelSummaryArray.travelSummaries = new TravelSummary[TRAVEL_IDS.length];
        travelSummaryArray.travelSummaryArrayCallback = travelSummaryArrayCallback;
        travelSummaryArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        for (int i = 0; i < TRAVEL_IDS.length; i++) {
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(TRAVEL_IDS[i]), userName, password,
                    travelSummaryArray);
        }
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            travelSummaryArrayCallback.onTravelSummaryArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                travelSummaries[completed] = GSON.fromJson(content, TravelSummary.class);
            } catch (Exception e) {
                travelSummaryArrayCallback.onTravelSummaryArrayError(id, e);
            }
            completed++;
            if (completed == TRAVEL_IDS.length) {
                travelSummaryArrayCallback.onTravelSummaryArrayReady(id, this);
            }
        }
    }

    public TravelSummary[] getTravelSummaries() {
        return travelSummaries;
    }
}
