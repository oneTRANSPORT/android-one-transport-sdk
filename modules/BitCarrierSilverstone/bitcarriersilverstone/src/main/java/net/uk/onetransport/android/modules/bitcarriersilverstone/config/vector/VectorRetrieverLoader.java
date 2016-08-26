package net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class VectorRetrieverLoader extends RetrieverLoader<Vector> implements VectorParams {

    public VectorRetrieverLoader(Context context) {
        super(context, new VectorRetriever());
    }

}
