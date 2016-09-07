package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

import android.content.Context;

import net.uk.onetransport.android.modules.clearviewsilverstone.generic.RetrieverLoader;

public class TrafficGroupRetrieverLoader extends RetrieverLoader<TrafficGroup>
        implements TrafficGroupParams {

    public TrafficGroupRetrieverLoader(Context context) {
        super(context, new TrafficGroupRetriever(context), "DEVICE_"); // TODO    Extract.
    }
}
