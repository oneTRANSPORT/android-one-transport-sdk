//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.metavector;
//
//import android.content.Context;
//import android.database.Cursor;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
//
//import java.util.ArrayList;
//
//public class MetavectorRetriever extends Retriever<Metavector> implements MetaVectorParams {
//
//    public MetavectorRetriever(Context context) {
//        super(context);
//    }
//
//    public ArrayList<Metavector> retrieve() throws Exception {
//        ArrayList<Metavector> metavectors = new ArrayList<>();
//        retrieve(metavectors);
//        return metavectors;
//    }
//
//    @Override
//    protected String getRetrievePrefix() {
//        return RETRIEVE_PREFIX;
//    }
//
//    @Override
//    protected Metavector fromJson(String content, String cinId, Long creationTime) {
//        Metavector metavector = GSON.fromJson(content, Metavector.class);
//        metavector.setCinId(cinId);
//        metavector.setCreationTime(creationTime);
//        return metavector;
//    }
//
//    @Override
//    protected Cursor getResourceNames(Context context, int metaVectorId) {
//        return BcsContentHelper.getMetaVectorNames(context, metaVectorId);
//    }
//}
