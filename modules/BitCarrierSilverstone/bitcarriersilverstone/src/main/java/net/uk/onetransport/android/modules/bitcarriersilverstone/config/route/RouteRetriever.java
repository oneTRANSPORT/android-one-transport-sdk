//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.route;
//
//import android.content.Context;
//import android.database.Cursor;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
//
//import java.util.ArrayList;
//
//public class RouteRetriever extends Retriever<Route> implements RouteParams {
//
//    public RouteRetriever(Context context) {
//        super(context);
//    }
//
//    public ArrayList<Route> retrieve() throws Exception {
//        ArrayList<Route> routes = new ArrayList<>();
//        retrieve(routes);
//        return routes;
//    }
//
//    @Override
//    protected String getRetrievePrefix() {
//        return RETRIEVE_PREFIX;
//    }
//
//    @Override
//    protected Route fromJson(String content, String cinId, Long creationTime) {
//        Route route = GSON.fromJson(content, Route.class);
//        route.setCinId(cinId);
//        route.setCreationTime(creationTime);
//        return route;
//    }
//
//    @Override
//    protected Cursor getResourceNames(Context context, int nodeId) {
//        return BcsContentHelper.getRouteNames(context, nodeId);
//    }
//}
