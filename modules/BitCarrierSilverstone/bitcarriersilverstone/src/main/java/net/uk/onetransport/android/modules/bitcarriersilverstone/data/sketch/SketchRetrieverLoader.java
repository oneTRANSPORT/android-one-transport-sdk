package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

import java.util.ArrayList;

public class SketchRetrieverLoader extends RetrieverLoader<Sketch> implements SketchParams {

    public SketchRetrieverLoader(Context context) {
        super(context);
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
        return Retriever.GSON.fromJson(content, Sketch.class);
    }
}