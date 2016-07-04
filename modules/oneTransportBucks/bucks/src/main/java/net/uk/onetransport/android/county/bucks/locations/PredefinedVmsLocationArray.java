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
//
//public class PredefinedVmsLocationArray extends BaseArray implements DougalCallback {
//
//    private static final String RETRIEVE_PATH = "BCCPredefinedLocationVmsFeedImport/All";
//
//    private PredefinedVmsLocation[] predefinedVmsLocations;
//    private PredefinedVmsLocationArrayCallback predefinedVmsLocationArrayCallback;
//    private int id;
//
//    private PredefinedVmsLocationArray() {
//    }
//
//    public PredefinedVmsLocationArray(PredefinedVmsLocation[] predefinedVmsLocations) {
//        this.predefinedVmsLocations = predefinedVmsLocations;
//    }
//
//    public static PredefinedVmsLocationArray getPredefinedVmsLocationArray(Context context)
//            throws Exception {
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
//        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH,
//                userName, password);
//        String content = contentInstance.getContent();
//        return new PredefinedVmsLocationArray(GSON.fromJson(content, PredefinedVmsLocation[].class));
//    }
//
//    public static void getPredefinedVmsLocationArrayAsync(Context context,
//                                                          PredefinedVmsLocationArrayCallback predefinedVmsLocationArrayCallback,
//                                                          int id) {
//        PredefinedVmsLocationArray predefinedVmsLocationArray = new PredefinedVmsLocationArray();
//        predefinedVmsLocationArray.predefinedVmsLocationArrayCallback = predefinedVmsLocationArrayCallback;
//        predefinedVmsLocationArray.id = id;
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
//        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH, userName, password,
//                predefinedVmsLocationArray);
//    }
//
//    @Override
//    public void getResponse(Resource resource, Throwable throwable) {
//        if (throwable != null) {
//            predefinedVmsLocationArrayCallback.onPredefinedVmsLocationArrayError(id, throwable);
//        } else {
//            try {
//                String content = ((ContentInstance) resource).getContent();
//                predefinedVmsLocations = GSON.fromJson(content, PredefinedVmsLocation[].class);
//                predefinedVmsLocationArrayCallback.onPredefinedVmsLocationArrayReady(id, this);
//            } catch (Exception e) {
//                predefinedVmsLocationArrayCallback.onPredefinedVmsLocationArrayError(id, e);
//            }
//        }
//    }
//
//    public PredefinedVmsLocation[] getPredefinedVmsLocations() {
//        return predefinedVmsLocations;
//    }
//}
