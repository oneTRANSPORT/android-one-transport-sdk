//package net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch;
//
//import android.content.Context;
//import android.database.Cursor;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
//import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
//
//import java.util.ArrayList;
//
//public class SketchRetriever extends Retriever<Sketch> implements SketchParams {
//
//    public SketchRetriever(Context context) {
//        super(context);
//    }
//
//    public ArrayList<Sketch> retrieve() throws Exception {
//        ArrayList<Sketch> sketches = new ArrayList<>();
//        retrieve(sketches);
//        return sketches;
//    }
//
//    @Override
//    protected String getRetrievePrefix() {
//        return RETRIEVE_PREFIX;
//    }
//
//    @Override
//    protected Sketch fromJson(String content, String cinId, Long creationTime) {
//        Sketch sketch = GSON.fromJson(content, Sketch.class);
//        sketch.setCinId(cinId);
//        sketch.setCreationTime(creationTime);
//        return sketch;
//    }
//
//    @Override
//    protected Cursor getResourceNames(Context context, int sketchId) {
//        return BcsContentHelper.getConfigSketchNames(context, sketchId);
//    }
//}
