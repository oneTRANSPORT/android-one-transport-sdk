package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class SketchRetrieverLoader extends RetrieverLoader<Sketch> implements SketchParams {

    public SketchRetrieverLoader(Context context) {
        super(context, new SketchRetriever());
    }

}
