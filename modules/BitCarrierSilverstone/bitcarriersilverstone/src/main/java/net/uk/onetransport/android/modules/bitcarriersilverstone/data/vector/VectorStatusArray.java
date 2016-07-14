package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.BaseArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

public class VectorStatusArray extends BaseArray implements DougalCallback {

    private static final int[] VECTOR_IDS = {811, 812, 824, 828, 829, 830, 831, 835, 836, 838, 839, 841,
            861, 862, 864, 865, 867, 868, 869, 870, 872, 879, 880, 882, 883, 884, 885, 887, 888, 890, 891,
            893, 894, 895, 898, 899, 901, 904, 908, 910, 911};
    private static final String AE_NAME = "Worldsensing";
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/data/vectors/v";

    private static int completed = 0;

    private VectorStatus[] vectorStatuses;
    private VectorStatusArrayCallback vectorStatusArrayCallback;
    private int id; // TODO    Move to superclass?

    private VectorStatusArray() {
    }

    public VectorStatusArray(VectorStatus[] vectorStatuses) {
        this.vectorStatuses = vectorStatuses;
    }

    public static VectorStatusArray getVectorStatusArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        VectorStatus[] newVectorStatuses = new VectorStatus[VECTOR_IDS.length];
        for (int i = 0; i < VECTOR_IDS.length; i++) {
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(VECTOR_IDS[i]), userName, password);
            String content = contentInstance.getContent();
            newVectorStatuses[i] = GSON.fromJson(content, VectorStatus.class);
        }
        return new VectorStatusArray(newVectorStatuses);
    }

    public static void getVectorStatusArrayAsync(Context context,
                                                 VectorStatusArrayCallback vectorStatusArrayCallback, int id) {
        VectorStatusArray vectorStatusArray = new VectorStatusArray();
        vectorStatusArray.vectorStatuses = new VectorStatus[VECTOR_IDS.length];
        vectorStatusArray.vectorStatusArrayCallback = vectorStatusArrayCallback;
        vectorStatusArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        for (int i = 0; i < VECTOR_IDS.length; i++) {
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(VECTOR_IDS[i]), userName, password,
                    vectorStatusArray);
        }
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            vectorStatusArrayCallback.onVectorStatusArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                vectorStatuses[completed] = GSON.fromJson(content, VectorStatus.class);
            } catch (Exception e) {
                vectorStatusArrayCallback.onVectorStatusArrayError(id, e);
            }
            completed++;
            if (completed == VECTOR_IDS.length) {
                vectorStatusArrayCallback.onVectorStatusArrayReady(id, this);
            }
        }
    }

    public VectorStatus[] getVectorStatuses() {
        return vectorStatuses;
    }
}
