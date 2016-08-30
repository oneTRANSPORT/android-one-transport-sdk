//package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;
//
//import android.content.Context;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;
//
//public class VectorStatusRetrieverLoader extends RetrieverLoader<VectorStatus>
//        implements VectorStatusParams {
//
//    public VectorStatusRetrieverLoader(Context context) {
//        super(context);
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
//        return Retriever.GSON.fromJson(content, VectorStatus.class);
//    }
//}
