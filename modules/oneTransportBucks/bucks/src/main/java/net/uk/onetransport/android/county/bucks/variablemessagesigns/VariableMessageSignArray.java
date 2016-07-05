package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.authentication.CredentialHelper;
import net.uk.onetransport.android.county.bucks.storage.Prefs;

public class VariableMessageSignArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCFeedImportMerger/Vms/All";

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
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        String aeId =  "C-"+CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
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
        String aeId =  "C-"+CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
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