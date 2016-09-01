//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.city;
//
//import android.content.Context;
//import android.database.Cursor;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//
//import java.util.ArrayList;
//
//public class CityRetriever extends Retriever<City> implements CityParams {
//
//    public CityRetriever(Context context) {
//        super(context);
//    }
//
//    public ArrayList<City> retrieve() throws Exception {
//        ArrayList<City> cities = new ArrayList<>();
//        retrieve(cities);
//        return cities;
//    }
//
//    @Override
//    protected String getRetrievePrefix() {
//        return RETRIEVE_PREFIX;
//    }
//
//    @Override
//    protected City fromJson(String content, String cinId, Long creationTime) {
//        City city = GSON.fromJson(content, City.class);
//        city.setCinId(cinId);
//        city.setCreationTime(creationTime);
//        return city;
//    }
//
//}
