package net.uk.onetransport.android.modules.clearviewsilverstone.device;

import android.content.Context;

import net.uk.onetransport.android.modules.clearviewsilverstone.generic.RetrieverLoader;

public class DeviceRetrieverLoader extends RetrieverLoader<Device> implements DeviceParams {

    public DeviceRetrieverLoader(Context context) {
        super(context, new DeviceRetriever(context), null);
    }
}
