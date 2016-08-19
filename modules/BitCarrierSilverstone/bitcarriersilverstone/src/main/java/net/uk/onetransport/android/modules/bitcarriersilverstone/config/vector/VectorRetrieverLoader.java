package net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeParams;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class VectorRetrieverLoader extends RetrieverLoader<Vector> implements VectorParams {

    public VectorRetrieverLoader(Context context) {
        super(context);
    }

    @Override
    protected int[] getIds() {
        return VECTOR_IDS;
    }

    @Override
    protected String getRetrivePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected Vector fromJson(String content) {
        return Retriever.GSON.fromJson(content, Vector.class);
    }
}
