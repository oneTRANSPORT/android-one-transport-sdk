package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;

import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatus;

public class BaseArray<T extends BaseArray.BaseData> {

    public interface BaseData{

        String getIds();

        String getRetrivePrefix();
    }

    protected static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private T[] ts;
    private RetrieverCallback<BaseArray<T>> retrieverCallback;
    private int id;

    private BaseArray() {
    }

    public BaseArray(T[] ts) {
        this.ts = ts;
    }

    public static BaseArray getBaseArray(Context context) throws Exception {
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
}
