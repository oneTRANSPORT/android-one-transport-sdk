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

// TODO    Might be an idea for BaseArray to implement an AsyncTaskLoader.
// TODO    Either way, sort out asynchronicity.
public class TrafficGroupArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PREFIX = DeviceArray.AE_NAME + "/DEVICE_";

    private static int completed;

    private TrafficGroup[] trafficGroups;
    private TrafficGroupArrayCallback trafficGroupArrayCallback;
    private int id;

    private TrafficGroupArray() {
    }

    public TrafficGroupArray(TrafficGroup[] trafficGroups) {
        this.trafficGroups = trafficGroups;
    }

    public static TrafficGroupArray getTrafficGroupArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        TrafficGroup[] newTrafficGroups = new TrafficGroup[DeviceArray.DEVICE_IDS.length];
        for (int i = 0; i < DeviceArray.DEVICE_IDS.length; i++) {
            int deviceId = DeviceArray.DEVICE_IDS[i];
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(deviceId), userName, password);
            String content = contentInstance.getContent();
            Traffic[] newTraffic = GSON.fromJson(content, Traffic[].class);
            newTrafficGroups[i] = new TrafficGroup();
            newTrafficGroups[i].setSensorId(deviceId);
            newTrafficGroups[i].setTraffic(newTraffic);
        }
        return new TrafficGroupArray(newTrafficGroups);
    }

    public static void getTrafficGroupArrayAsync(Context context,
                                                 TrafficGroupArrayCallback trafficGroupArrayCallback, int id) {
        TrafficGroupArray trafficGroupArray = new TrafficGroupArray();
        trafficGroupArray.trafficGroups = new TrafficGroup[DeviceArray.DEVICE_IDS.length];
        trafficGroupArray.trafficGroupArrayCallback = trafficGroupArrayCallback;
        trafficGroupArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        completed = 0;
        for (int i = 0; i < DeviceArray.DEVICE_IDS.length; i++) {
            int deviceId = DeviceArray.DEVICE_IDS[i];
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(deviceId), userName, password, trafficGroupArray);
        }
    }


    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            trafficGroupArrayCallback.onTrafficGroupArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                Traffic[] traffic = GSON.fromJson(content, Traffic[].class);
                trafficGroups[completed] = new TrafficGroup();
                trafficGroups[completed].setSensorId(Integer.parseInt(
                        resource.getRetrievePath().replaceFirst("^.*DEVICE_", "")
                                .replaceFirst("/la$", "")));
                trafficGroups[completed].setTraffic(traffic);
            } catch (Exception e) {
                trafficGroupArrayCallback.onTrafficGroupArrayError(id, e);
            }
            completed++;
            if (completed == DeviceArray.DEVICE_IDS.length) {
                trafficGroupArrayCallback.onTrafficGroupArrayReady(id, this);
            }
        }
    }

    public TrafficGroup[] getTrafficGroups() {
        return trafficGroups;
    }
}
