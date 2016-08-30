package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

import android.content.Context;
import android.database.Cursor;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class NodeRetriever extends Retriever<Node> implements NodeParams {

    public NodeRetriever(Context context) {
        super(context);
    }

    public ArrayList<Node> retrieve() throws Exception {
        ArrayList<Node> nodes = new ArrayList<>();
        retrieve( nodes);
        // We need to extract the customer id from the customer name field.
        setCustomerIds(nodes);
        return nodes;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected Node fromJson(String content, String cinId, Long creationTime) {
        return GSON.fromJson(content, Node.class);
    }

    @Override
    protected Cursor getResourceNames(Context context, int id) {
        return null;
    }

    private void setCustomerIds(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            node.setCustomerIdFromName();
        }
    }
}
