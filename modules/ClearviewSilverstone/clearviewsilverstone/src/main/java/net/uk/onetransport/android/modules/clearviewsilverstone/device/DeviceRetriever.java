package net.uk.onetransport.android.modules.clearviewsilverstone.device;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ResourceChild;

import net.uk.onetransport.android.modules.clearviewsilverstone.generic.Retriever;

import java.util.ArrayList;

public class DeviceRetriever extends Retriever<Device> implements DeviceParams {

    public DeviceRetriever(Context context) {
        super(context);
    }

    public ArrayList<Device> retrieve() throws Exception {
        ArrayList<Device> devices = new ArrayList<>();
        retrieve(devices, null);
        return devices;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected ResourceChild[] retrieveChildren(String aeId, String cseBaseUrl, String userName,
                                               String password) throws Exception {
        return Container.retrieveChildren(aeId, cseBaseUrl, getRetrievePrefix(),
                userName, password).getResourceChildren();

    }

    @Override
    protected Device fromJson(String content, String container, String cinId, Long creationTime) {
        Device device = GSON.fromJson(content, Device.class);
        device.setCinId(cinId);
        device.setCreationTime(creationTime);
        return device;
    }
}
