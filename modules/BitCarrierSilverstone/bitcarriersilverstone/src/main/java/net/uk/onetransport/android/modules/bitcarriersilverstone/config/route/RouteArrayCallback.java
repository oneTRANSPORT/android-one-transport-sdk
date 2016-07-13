package net.uk.onetransport.android.modules.bitcarriersilverstone.config.route;

public interface RouteArrayCallback {

    void onRouteArrayReady(int id, RouteArray routeArray);

    void onRouteArrayError(int id, Throwable throwable);

}
