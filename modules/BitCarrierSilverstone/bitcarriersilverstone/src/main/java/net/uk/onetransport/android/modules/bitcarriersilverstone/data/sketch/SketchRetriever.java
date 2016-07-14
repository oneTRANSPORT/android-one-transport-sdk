package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

public class SketchRetriever extends Retriever<Sketch> {

    private static final int[] SKETCH_IDS = {122, 128, 133, 134, 145, 146, 147, 150, 151, 152, 153, 154, 156,
            157, 161, 165, 175, 176, 183, 193, 195, 196};
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/data/sketches/s";

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
