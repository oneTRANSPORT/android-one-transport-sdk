package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;

public class VariableMessageSignArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCSignSettingFeedImport/All";

    private VariableMessageSign[] variableMessageSigns;
    private VariableMessageSignArrayCallback variableMessageSignArrayCallback;
    private int id;

    public VariableMessageSignArray() {
    }

    public VariableMessageSignArray(VariableMessageSign[] variableMessageSigns) {
        this.variableMessageSigns = variableMessageSigns;
    }

    public static VariableMessageSignArray getVariableMessageSignArray(Context context)
            throws Exception {
        String aeId = maybeCreateAe(context);
        if (aeId == null) { // TODO Error reporting?
            return null;
        }
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new VariableMessageSignArray(GSON.fromJson(content, VariableMessageSign[].class));
    }

    public static void getVariableMessageSignArrayAsync(Context context,
                                                        VariableMessageSignArrayCallback variableMessageSignArrayCallback,
                                                        int id) {
        VariableMessageSignArray variableMessageSignArray = new VariableMessageSignArray();
        variableMessageSignArray.variableMessageSignArrayCallback = variableMessageSignArrayCallback;
        variableMessageSignArray.id = id;
        String aeId = maybeCreateAe(context);
        if (aeId == null) { // TODO Error reporting?
            return;
        }
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH, userName, password,
                variableMessageSignArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            variableMessageSignArrayCallback.onVariableMessageSignArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                variableMessageSigns = GSON.fromJson(content, VariableMessageSign[].class);
                variableMessageSignArrayCallback.onVariableMessageSignArrayReady(id, this);
            } catch (Exception e) {
                variableMessageSignArrayCallback.onVariableMessageSignArrayError(id, e);
            }
        }
    }

    public VariableMessageSign[] getVariableMessageSigns() {
        return variableMessageSigns;
    }
}
