package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class NodeRetriever extends Retriever<Node> implements NodeParams {

    public ArrayList<Node> retrieve(Context context) throws Exception {
        ArrayList<Node> nodes = new ArrayList<>();
        retrieve(context, nodes);
        // We need to extract the customer id from the customer name field.
        setCustomerIds(nodes);
        return nodes;
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
        return GSON.fromJson(content, Node.class);
    }

    private void setCustomerIds(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            node.setCustomerIdFromName();
        }
    }
}
