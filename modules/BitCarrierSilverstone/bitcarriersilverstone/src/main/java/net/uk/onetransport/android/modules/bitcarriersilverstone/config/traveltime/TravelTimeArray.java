//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.traveltime;
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
//// TODO    Could this be generic or move more code to the superclass?
//public class TravelTimeArray extends BaseArray implements DougalCallback {
//
//    public static final int START_TIME_ID = 1;
//    public static final int END_TIME_ID = 83;
//    public static final int NUM_TIMES = END_TIME_ID - START_TIME_ID + 1;
//    public static final String AE_NAME = "Worldsensing";
//    private static final String RETRIEVE_PREFIX = AE_NAME
//            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/traveltimes/t";
//
//    private static int completed = 0;
//
//    private TravelTime[] travelTimes;
//    private TravelTimeArrayCallback travelTimeArrayCallback;
//    private int id; // TODO    Move to superclass?
//
//    private TravelTimeArray() {
//    }
//
//    public TravelTimeArray(TravelTime[] travelTimes) {
//        this.travelTimes = travelTimes;
//    }
//
//    public static TravelTimeArray getTravelTimeArray(Context context) throws Exception {
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        TravelTime[] newTravelTimes = new TravelTime[NUM_TIMES];
//        for (int i = START_TIME_ID; i <= END_TIME_ID; i++) {
//            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(i), userName, password);
//            String content = contentInstance.getContent();
//            newTravelTimes[i] = GSON.fromJson(content, TravelTime.class);
//        }
//        return new TravelTimeArray(newTravelTimes);
//    }
//
//    public static void getTravelTimeArrayAsync(Context context,
//                                               TravelTimeArrayCallback travelTimeArrayCallback, int id) {
//        TravelTimeArray travelTimeArray = new TravelTimeArray();
//        travelTimeArray.travelTimes = new TravelTime[NUM_TIMES];
//        travelTimeArray.travelTimeArrayCallback = travelTimeArrayCallback;
//        travelTimeArray.id = id;
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        for (int i = START_TIME_ID; i <= END_TIME_ID; i++) {
//            Container.retrieveLatestAsync(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(i), userName, password, travelTimeArray);
//        }
//    }
//
//    @Override
//    public void getResponse(Resource resource, Throwable throwable) {
//        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
//        if (throwable != null) {
//            travelTimeArrayCallback.onTravelTimeArrayError(id, throwable);
//        } else {
//            try {
//                String content = ((ContentInstance) resource).getContent();
//                // TODO    There will be holes if there is a download error.
//                travelTimes[completed] = GSON.fromJson(content, TravelTime.class);
//            } catch (Exception e) {
//                travelTimeArrayCallback.onTravelTimeArrayError(id, e);
//            }
//            completed++;
//            if (completed == NUM_TIMES) {
//                travelTimeArrayCallback.onTravelTimeArrayReady(id, this);
//            }
//        }
//    }
//
//    public TravelTime[] getTravelTimes() {
//        return travelTimes;
//    }
//}
