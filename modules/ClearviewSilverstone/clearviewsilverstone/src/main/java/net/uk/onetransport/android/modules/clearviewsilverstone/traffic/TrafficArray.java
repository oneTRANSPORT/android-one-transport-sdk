package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.BaseArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.R;
import net.uk.onetransport.android.modules.clearviewsilverstone.authentication.CredentialHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceArray;

public class TrafficArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PREFIX = DeviceArray.AE_NAME + "/DEVICE_";

    private static int completed = 0;

    private Traffic[][] traffics;
    private TrafficArrayCallback trafficArrayCallback;
    private int id;

    private TrafficArray() {
    }

    public TrafficArray(Traffic[][] traffics) {
        this.traffics = traffics;
    }

    public static TrafficArray getTrafficArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        Traffic[][] newTraffics = new Traffic[DeviceArray.DEVICE_IDS.length][];
        for (int i = 0; i < DeviceArray.DEVICE_IDS.length; i++) {
            int deviceId = DeviceArray.DEVICE_IDS[i];
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(deviceId), userName, password);
            String content = contentInstance.getContent();
            newTraffics[i] = GSON.fromJson(content, Traffic[].class);
        }
        return new TrafficArray(newTraffics);
    }

    public static void getTrafficArrayAsync(Context context, TrafficArrayCallback trafficArrayCallback,
                                            int id) {
        TrafficArray trafficArray = new TrafficArray();
        trafficArray.traffics = new Traffic[DeviceArray.DEVICE_IDS.length][];
        trafficArray.trafficArrayCallback = trafficArrayCallback;
        trafficArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        for (int i = 0; i < DeviceArray.DEVICE_IDS.length; i++) {
            int deviceId = DeviceArray.DEVICE_IDS[i];
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(deviceId), userName, password, trafficArray);
        }
    }


    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            trafficArrayCallback.onTrafficArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                traffics[completed] = GSON.fromJson(content, Traffic[].class);
            } catch (Exception e) {
                trafficArrayCallback.onTrafficArrayError(id, e);
            }
            completed++;
            if (completed == DeviceArray.DEVICE_IDS.length) {
                trafficArrayCallback.onTrafficArrayReady(id, this);
            }
        }
    }

    public Traffic[][] getTraffics() {
        return traffics;
    }
}
