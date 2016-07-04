package net.uk.onetransport.android.county.bucks.roadworks;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.authentication.CredentialHelper;

public class RoadWorksArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCRoadWorksFeedImport/All";

    private RoadWorks[] roadWorks;
    private RoadWorksArrayCallback roadWorksArrayCallback;
    private int id;

    private RoadWorksArray() {
    }

    public RoadWorksArray(RoadWorks[] roadWorks) {
        this.roadWorks = roadWorks;
    }

    public static RoadWorksArray getRoadWorksArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new RoadWorksArray(GSON.fromJson(content, RoadWorks[].class));
    }

    public static void getRoadWorksArrayAsync(Context context,
                                              RoadWorksArrayCallback roadWorksArrayCallback, int id) {
        RoadWorksArray roadWorksArray = new RoadWorksArray();
        roadWorksArray.roadWorksArrayCallback = roadWorksArrayCallback;
        roadWorksArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH, userName, password,
                roadWorksArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            roadWorksArrayCallback.onRoadWorksArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                roadWorks = GSON.fromJson(content, RoadWorks[].class);
                roadWorksArrayCallback.onRoadWorksArrayReady(id, this);
            } catch (Exception e) {
                roadWorksArrayCallback.onRoadWorksArrayError(id, e);
            }
        }
    }

    public RoadWorks[] getRoadWorks() {
        return roadWorks;
    }
}
