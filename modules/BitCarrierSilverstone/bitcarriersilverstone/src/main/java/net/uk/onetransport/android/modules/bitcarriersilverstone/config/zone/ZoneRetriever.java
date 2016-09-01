//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.zone;
//
//import android.content.Context;
//import android.database.Cursor;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
//
//import java.util.ArrayList;
//
//public class ZoneRetriever extends Retriever<Zone> implements ZoneParams {
//
//    public ZoneRetriever(Context context) {
//        super(context);
//    }
//
//    public ArrayList<Zone> retrieve() throws Exception {
//        ArrayList<Zone> zones = new ArrayList<>();
//        retrieve(zones);
//        return zones;
//    }
//
//    @Override
//    protected String getRetrievePrefix() {
//        return RETRIEVE_PREFIX;
//    }
//
//    @Override
//    protected Zone fromJson(String content, String cinId, Long creationTime) {
//        Zone zone = GSON.fromJson(content, Zone.class);
//        zone.setCinId(cinId);
//        zone.setCreationTime(creationTime);
//        return zone;
//    }
//
//    @Override
//    protected Cursor getResourceNames(Context context, int zoneId) {
//        return BcsContentHelper.getZoneNames(context, zoneId);
//    }
//}
