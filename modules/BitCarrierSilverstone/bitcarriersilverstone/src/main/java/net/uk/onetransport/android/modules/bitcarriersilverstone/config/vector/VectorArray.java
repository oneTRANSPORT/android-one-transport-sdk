//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector;
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
//public class VectorArray extends BaseArray implements DougalCallback {
//
//    public static final int START_VECTOR_ID = 276;
//    public static final int END_VECTOR_ID = 365;
//    public static final int NUM_VECTORS = END_VECTOR_ID - START_VECTOR_ID + 1;
//    public static final String AE_NAME = "Worldsensing";
//    private static final String RETRIEVE_PREFIX = AE_NAME
//            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/vectors/v";
//
//    private static int completed = 0;
//
//    private Vector[] vectors;
//    private VectorArrayCallback vectorArrayCallback;
//    private int id; // TODO    Move to superclass?
//
//    private VectorArray() {
//    }
//
//    public VectorArray(Vector[] vectors) {
//        this.vectors = vectors;
//    }
//
//    public static VectorArray getVectorArray(Context context) throws Exception {
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        Vector[] newVectors = new Vector[NUM_VECTORS];
//        for (int i = START_VECTOR_ID; i <= END_VECTOR_ID; i++) {
//            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(i), userName, password);
//            String content = contentInstance.getContent();
//            newVectors[i] = GSON.fromJson(content, Vector.class);
//        }
//        return new VectorArray(newVectors);
//    }
//
//    public static void getVectorArrayAsync(Context context,
//                                           VectorArrayCallback vectorArrayCallback, int id) {
//        VectorArray vectorArray = new VectorArray();
//        vectorArray.vectors = new Vector[NUM_VECTORS];
//        vectorArray.vectorArrayCallback = vectorArrayCallback;
//        vectorArray.id = id;
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        for (int i = START_VECTOR_ID; i <= END_VECTOR_ID; i++) {
//            Container.retrieveLatestAsync(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(i), userName, password, vectorArray);
//        }
//    }
//
//    @Override
//    public void getResponse(Resource resource, Throwable throwable) {
//        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
//        if (throwable != null) {
//            vectorArrayCallback.onVectorArrayError(id, throwable);
//        } else {
//            try {
//                String content = ((ContentInstance) resource).getContent();
//                // TODO    There will be holes if there is a download error.
//                vectors[completed] = GSON.fromJson(content, Vector.class);
//            } catch (Exception e) {
//                vectorArrayCallback.onVectorArrayError(id, e);
//            }
//            completed++;
//            if (completed == NUM_VECTORS) {
//                vectorArrayCallback.onVectorArrayReady(id, this);
//            }
//        }
//    }
//
//    public Vector[] getVectors() {
//        return vectors;
//    }
//}
