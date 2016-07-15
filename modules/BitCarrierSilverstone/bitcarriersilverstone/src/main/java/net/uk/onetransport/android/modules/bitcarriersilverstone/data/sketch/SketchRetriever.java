package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class SketchRetriever extends Retriever<Sketch> implements SketchParams {

    public ArrayList<Sketch> retrieve(Context context) throws Exception {
        ArrayList<Sketch> sketches = new ArrayList<>();
        retrieve(context, sketches);
        return sketches;
    }

    @Override
    protected int[] getIds() {
        return SKETCH_IDS;
    }

    @Override
    protected String getRetrivePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected Sketch fromJson(String content) {
        return GSON.fromJson(content, Sketch.class);
    }
}
