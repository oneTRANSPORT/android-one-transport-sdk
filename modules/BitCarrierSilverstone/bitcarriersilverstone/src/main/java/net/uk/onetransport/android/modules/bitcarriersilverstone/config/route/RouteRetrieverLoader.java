package net.uk.onetransport.android.modules.bitcarriersilverstone.config.route;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class RouteRetrieverLoader extends RetrieverLoader<Route> {

    public RouteRetrieverLoader(Context context) {
        super(context, new RouteRetriever(context));
    }
}
