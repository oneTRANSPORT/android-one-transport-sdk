//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch;
//
//import android.content.Context;
//
//import com.interdigital.android.dougal.resource.Container;
//import com.interdigital.android.dougal.resource.ContentInstance;
//import com.interdigital.android.dougal.resource.Resource;
//import com.interdigital.android.dougal.resource.callback.DougalCallback;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.BaseArray;
//
//public class SketchArray extends BaseArray implements DougalCallback {
//
//    public static final int[] SKETCH_IDS = {1, 2, 3, 4, 5, 7, 8, 9, 10, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
//            24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46};
//    public static final String AE_NAME = "Worldsensing";
//    private static final String RETRIEVE_PREFIX = AE_NAME
//            + "/BitCarrier/v1.0/InterdigitalDemo/silverstone/config/sketches/s";
//    private static int completed = 0;
//
//    private Sketch[] sketches;
//    private SketchArrayCallback sketchArrayCallback;
//    private int id; // TODO    Move to superclass?
//
//    private SketchArray() {
//    }
//
//    public SketchArray(Sketch[] sketches) {
//        this.sketches = sketches;
//    }
//
//    public static SketchArray getSketchArray(Context context) throws Exception {
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        Sketch[] newSketches = new Sketch[SKETCH_IDS.length];
//        for (int i = 0; i < SKETCH_IDS.length; i++) {
//            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(SKETCH_IDS[i]), userName, password);
//            String content = contentInstance.getContent();
//            newSketches[i] = GSON.fromJson(content, Sketch.class);
//            newSketches[i].setPositions(
//                    GSON.fromJson(newSketches[i].getLocationJsonArray(), Position[].class));
//        }
//        return new SketchArray(newSketches);
//    }
//
//    public static void getSketchArrayAsync(Context context,
//                                           SketchArrayCallback sketchArrayCallback, int id) {
//        SketchArray sketchArray = new SketchArray();
//        sketchArray.sketches = new Sketch[SKETCH_IDS.length];
//        sketchArray.sketchArrayCallback = sketchArrayCallback;
//        sketchArray.id = id;
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        for (int i = 0; i < SKETCH_IDS.length; i++) {
//            Container.retrieveLatestAsync(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(SKETCH_IDS[i]), userName, password, sketchArray);
//        }
//    }
//
//    @Override
//    public void getResponse(Resource resource, Throwable throwable) {
//        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
//        if (throwable != null) {
//            sketchArrayCallback.onSketchArrayError(id, throwable);
//        } else {
//            try {
//                String content = ((ContentInstance) resource).getContent();
//                // TODO    There will be holes if there is a download error.
//                sketches[completed] = GSON.fromJson(content, Sketch.class);
//                sketches[completed].setPositions(
//                        GSON.fromJson(sketches[completed].getLocationJsonArray(), Position[].class));
//            } catch (Exception e) {
//                sketchArrayCallback.onSketchArrayError(id, e);
//            }
//            completed++;
//            if (completed == SKETCH_IDS.length) {
//                sketchArrayCallback.onSketchArrayReady(id, this);
//            }
//        }
//    }
//
//    public Sketch[] getSketches() {
//        return sketches;
//    }
//}
