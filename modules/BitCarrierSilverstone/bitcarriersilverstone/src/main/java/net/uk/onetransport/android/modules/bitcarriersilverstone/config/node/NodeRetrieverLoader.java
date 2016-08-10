package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class NodeRetrieverLoader extends RetrieverLoader<Node> implements NodeParams {

    public NodeRetrieverLoader(Context context) {
        super(context);
    }

    @Override
    protected int[] getIds() {
        return NODE_IDS;
    }

    @Override
    protected String getRetrivePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected Node fromJson(String content) {
        return Retriever.GSON.fromJson(content, Node.class);
    }
}
