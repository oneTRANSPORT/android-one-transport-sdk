package net.uk.onetransport.android.modules.clearviewsilverstone.device;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.BaseArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.R;
import net.uk.onetransport.android.modules.clearviewsilverstone.authentication.CredentialHelper;

public class DeviceArray extends BaseArray implements DougalCallback {

    private static final int[] DEVICE_IDS = {1745, 1746, 1747, 1748, 1749, 1750, 1751, 1752, 1753, 1754};
    private static final String AE_NAME = "ClearviewIntelligence_VBV";
    private static final String RETRIEVE_PREFIX = AE_NAME + "/DEVICES/DEVICE_";

    private static int completed = 0;

    private Device[] devices;
    private DeviceArrayCallback deviceArrayCallback;
    private int id;

    private DeviceArray() {
    }

    public DeviceArray(Device[] devices) {
        this.devices = devices;
    }

    public static DeviceArray getDeviceArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        Device[] newDevices = new Device[DEVICE_IDS.length];
        for (int i = 0; i < DEVICE_IDS.length; i++) {
            int deviceId = DEVICE_IDS[i];
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(deviceId), userName, password);
            String content = contentInstance.getContent();
            newDevices[i] = GSON.fromJson(content, Device.class);
        }
        return new DeviceArray(newDevices);
    }

    public static void getDeviceArrayAsync(Context context,
                                           DeviceArrayCallback deviceArrayCallback, int id) {
        DeviceArray deviceArray = new DeviceArray();
        deviceArray.devices = new Device[DEVICE_IDS.length];
        deviceArray.deviceArrayCallback = deviceArrayCallback;
        deviceArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        for (int i = 0; i < DEVICE_IDS.length; i++) {
            int deviceId = DEVICE_IDS[i];
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(deviceId), userName, password, deviceArray);
        }
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            deviceArrayCallback.onDeviceArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                devices[completed] = GSON.fromJson(content, Device.class);
            } catch (Exception e) {
                deviceArrayCallback.onDeviceArrayError(id, e);
            }
            completed++;
            if (completed == DEVICE_IDS.length) {
                deviceArrayCallback.onDeviceArrayReady(id, this);
            }
        }
    }

    public Device[] getDevices() {
        return devices;
    }
}
