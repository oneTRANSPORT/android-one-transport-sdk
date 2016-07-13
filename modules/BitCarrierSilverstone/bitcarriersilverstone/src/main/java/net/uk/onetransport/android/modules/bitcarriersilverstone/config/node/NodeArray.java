package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.BaseArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

public class NodeArray extends BaseArray implements DougalCallback {

    public static final String[] NODE_IDS = {"n1159", "n1160", "n1161", "n1162", "n1163", "n1164", "n1165",
            "n1166", "n1167", "n1168", "n1169", "n1170", "n1171", "n1172"};
    public static final String AE_NAME = "Worldsensing";
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/nodes/";

    private static int completed = 0;

    private Node[] nodes;
    private NodeArrayCallback nodeArrayCallback;
    private int id;

    private NodeArray() {
    }

    public NodeArray(Node[] nodes) {
        this.nodes = nodes;
    }

    public static NodeArray getNodeArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        Node[] newNodes = new Node[NODE_IDS.length];
        for (int i = 0; i < NODE_IDS.length; i++) {
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + NODE_IDS[i], userName, password);
            String content = contentInstance.getContent();
            newNodes[i] = GSON.fromJson(content, Node.class);
        }
        return new NodeArray(newNodes);
    }

    public static void getNodeArrayAsync(Context context,
                                         NodeArrayCallback nodeArrayCallback, int id) {
        NodeArray nodeArray = new NodeArray();
        nodeArray.nodes = new Node[NODE_IDS.length];
        nodeArray.nodeArrayCallback = nodeArrayCallback;
        nodeArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        for (int i = 0; i < NODE_IDS.length; i++) {
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + NODE_IDS[i], userName, password, nodeArray);
        }
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            nodeArrayCallback.onNodeArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                nodes[completed] = GSON.fromJson(content, Node.class);
            } catch (Exception e) {
                nodeArrayCallback.onNodeArrayError(id, e);
            }
            completed++;
            if (completed == NODE_IDS.length) {
                nodeArrayCallback.onNodeArrayReady(id, this);
            }
        }
    }

    public Node[] getNodes() {
        return nodes;
    }
}
