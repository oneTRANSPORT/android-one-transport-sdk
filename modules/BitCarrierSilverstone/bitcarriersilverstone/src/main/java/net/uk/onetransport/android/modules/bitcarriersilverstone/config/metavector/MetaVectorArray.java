package net.uk.onetransport.android.modules.bitcarriersilverstone.config.metavector;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.BaseArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

public class MetaVectorArray extends BaseArray implements DougalCallback {

    public static final int START_META_VECTOR_ID = 124;
    public static final int END_META_VECTOR_ID = 206;
    public static final int NUM_META_VECTORS = END_META_VECTOR_ID
            - START_META_VECTOR_ID + 1;
    public static final String AE_NAME = "Worldsensing";
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/metavectors/m";

    private static int completed = 0;

    private MetaVector[] metaVectors;
    private MetaVectorArrayCallback metaVectorArrayCallback;
    private int id; // TODO    Move to superclass?

    private MetaVectorArray() {
    }

    public MetaVectorArray(MetaVector[] metaVectors) {
        this.metaVectors = metaVectors;
    }

    public static MetaVectorArray getMetaVectorArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        MetaVector[] newMetaVectors = new MetaVector[NUM_META_VECTORS];
        for (int i = START_META_VECTOR_ID; i <= END_META_VECTOR_ID; i++) {
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(i), userName, password);
            String content = contentInstance.getContent();
            newMetaVectors[i] = GSON.fromJson(content, MetaVector.class);
        }
        return new MetaVectorArray(newMetaVectors);
    }

    public static void getMetaVectorArrayAsync(Context context,
                                               MetaVectorArrayCallback metaVectorArrayCallback, int id) {
        MetaVectorArray metaVectorArray = new MetaVectorArray();
        metaVectorArray.metaVectors = new MetaVector[NUM_META_VECTORS];
        metaVectorArray.metaVectorArrayCallback = metaVectorArrayCallback;
        metaVectorArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        for (int i = START_META_VECTOR_ID; i <= END_META_VECTOR_ID; i++) {
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(i), userName, password, metaVectorArray);
        }
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            metaVectorArrayCallback.onMetaVectorArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                metaVectors[completed] = GSON.fromJson(content, MetaVector.class);
            } catch (Exception e) {
                metaVectorArrayCallback.onMetaVectorArrayError(id, e);
            }
            completed++;
            if (completed == NUM_META_VECTORS) {
                metaVectorArrayCallback.onMetaVectorArrayReady(id, this);
            }
        }
    }

    public MetaVector[] getMetaVectors() {
        return metaVectors;
    }
}
