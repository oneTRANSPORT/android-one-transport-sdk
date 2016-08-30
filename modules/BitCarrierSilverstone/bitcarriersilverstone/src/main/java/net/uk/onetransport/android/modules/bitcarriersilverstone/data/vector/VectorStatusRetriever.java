//package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;
//
//import android.content.Context;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//
//import java.util.ArrayList;
//
//public class VectorStatusRetriever extends Retriever<VectorStatus> implements VectorStatusParams {
//
//    public ArrayList<VectorStatus> retrieve(Context context) throws Exception {
//        ArrayList<VectorStatus> vectorStatuses = new ArrayList<>();
//        retrieve(context, vectorStatuses);
//        return vectorStatuses;
//    }
//
//    @Override
//    protected int[] getIds() {
//        return VECTOR_IDS;
//    }
//
//    @Override
//    protected String getRetrievePrefix() {
//        return RETRIEVE_PREFIX;
//    }
//
//    @Override
//    protected VectorStatus fromJson(String content) {
//        return GSON.fromJson(content, VectorStatus.class);
//    }
//}
