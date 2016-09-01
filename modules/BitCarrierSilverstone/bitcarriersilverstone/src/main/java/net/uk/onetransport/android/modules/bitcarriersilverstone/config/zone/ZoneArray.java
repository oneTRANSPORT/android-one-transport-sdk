//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.zone;
//
//import android.content.Context;
//
//import com.interdigital.android.dougal.resource.Container;
//import com.interdigital.android.dougal.resource.ContentInstance;
//import com.interdigital.android.dougal.resource.Resource;
//import com.interdigital.android.dougal.resource.callback.DougalCallback;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.BaseArray;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;
//
//public class ZoneArray extends BaseArray implements DougalCallback {
//
//    public static final String AE_NAME = "Worldsensing";
//    private static final String RETRIEVE_PREFIX = AE_NAME
//            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/zones/z1";
//
//    private Zone zone;
//    private ZoneArrayCallback zoneArrayCallback;
//    private int id; // TODO    Move to superclass?
//
//    private ZoneArray() {
//    }
//
//    public ZoneArray(Zone zone) {
//        this.zone = zone;
//    }
//
//    public static ZoneArray getZoneArray(Context context) throws Exception {
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        Zone newZone;
//        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
//                RETRIEVE_PREFIX, userName, password);
//        String content = contentInstance.getContent();
//        newZone = GSON.fromJson(content, Zone.class);
//        return new ZoneArray(newZone);
//    }
//
//    public static void getZoneArrayAsync(Context context,
//                                         ZoneArrayCallback zoneArrayCallback, int id) {
//        ZoneArray zoneArray = new ZoneArray();
//        zoneArray.zoneArrayCallback = zoneArrayCallback;
//        zoneArray.id = id;
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        Container.retrieveLatestAsync(aeId, cseBaseUrl,
//                RETRIEVE_PREFIX, userName, password, zoneArray);
//    }
//
//    @Override
//    public void getResponse(Resource resource, Throwable throwable) {
//        if (throwable != null) {
//            zoneArrayCallback.onZoneArrayError(id, throwable);
//        } else {
//            try {
//                String content = ((ContentInstance) resource).getContent();
//                zone = GSON.fromJson(content, Zone.class);
//                zoneArrayCallback.onZoneArrayReady(id, this);
//            } catch (Exception e) {
//                zoneArrayCallback.onZoneArrayError(id, e);
//            }
//        }
//    }
//
//    public Zone getZone() {
//        return zone;
//    }
//}
