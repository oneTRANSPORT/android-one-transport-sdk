package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class NodeRetrieverLoader extends RetrieverLoader<Node> {

    public NodeRetrieverLoader(Context context) {
        super(context, new NodeRetriever());
    }
}
