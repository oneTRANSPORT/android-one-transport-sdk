//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.route;
//
//import android.content.Context;
//
//import com.interdigital.android.dougal.resource.Container;
//import com.interdigital.android.dougal.resource.ContentInstance;
//import com.interdigital.android.dougal.resource.Resource;
//import com.interdigital.android.dougal.resource.callback.DougalCallback;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.BaseArray;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;
//
//public class RouteArray extends BaseArray implements DougalCallback {
//
//    public static final int START_ROUTE_ID = 133;
//    public static final int END_ROUTE_ID = 215;
//    public static final int NUM_ROUTES = END_ROUTE_ID - START_ROUTE_ID + 1;
//    public static final String AE_NAME = "Worldsensing";
//    private static final String RETRIEVE_PREFIX = AE_NAME
//            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/routes/r";
//
//    private static int completed = 0;
//
//    private Route[] routes;
//    private RouteArrayCallback routeArrayCallback;
//    private int id; // TODO    Move to superclass?
//
//    private RouteArray() {
//    }
//
//    public RouteArray(Route[] routes) {
//        this.routes = routes;
//    }
//
//    public static RouteArray getRouteArray(Context context) throws Exception {
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        Route[] newRoutes = new Route[NUM_ROUTES];
//        for (int i = START_ROUTE_ID; i <= END_ROUTE_ID; i++) {
//            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(i), userName, password);
//            String content = contentInstance.getContent();
//            newRoutes[i] = GSON.fromJson(content, Route.class);
//        }
//        return new RouteArray(newRoutes);
//    }
//
//    public static void getRouteArrayAsync(Context context,
//                                           RouteArrayCallback routeArrayCallback, int id) {
//        RouteArray routeArray = new RouteArray();
//        routeArray.routes = new Route[NUM_ROUTES];
//        routeArray.routeArrayCallback = routeArrayCallback;
//        routeArray.id = id;
//        String aeId = "C-" + CredentialHelper.getAeId(context);
//        String userName = CredentialHelper.getAeId(context);
//        String password = CredentialHelper.getSessionToken(context);
//        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
//        for (int i = START_ROUTE_ID; i <= END_ROUTE_ID; i++) {
//            Container.retrieveLatestAsync(aeId, cseBaseUrl,
//                    RETRIEVE_PREFIX + String.valueOf(i), userName, password, routeArray);
//        }
//    }
//
//    @Override
//    public void getResponse(Resource resource, Throwable throwable) {
//        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
//        if (throwable != null) {
//            routeArrayCallback.onRouteArrayError(id, throwable);
//        } else {
//            try {
//                String content = ((ContentInstance) resource).getContent();
//                // TODO    There will be holes if there is a download error.
//                routes[completed] = GSON.fromJson(content, Route.class);
//            } catch (Exception e) {
//                routeArrayCallback.onRouteArrayError(id, e);
//            }
//            completed++;
//            if (completed == NUM_ROUTES) {
//                routeArrayCallback.onRouteArrayReady(id, this);
//            }
//        }
//    }
//
//    public Route[] getRoutes() {
//        return routes;
//    }
//}
