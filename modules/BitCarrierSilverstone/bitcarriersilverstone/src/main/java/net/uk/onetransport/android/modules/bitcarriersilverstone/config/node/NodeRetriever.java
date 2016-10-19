/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

import android.content.Context;
import android.database.Cursor;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;

import java.util.ArrayList;

public class NodeRetriever extends Retriever<Node> implements NodeParams {

    public NodeRetriever(Context context) {
        super(context);
    }

    public ArrayList<Node> retrieve() throws Exception {
        ArrayList<Node> nodes = new ArrayList<>();
        retrieve(nodes);
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
        Node node = GSON.fromJson(content, Node.class);
        node.setCinId(cinId);
        node.setCreationTime(creationTime);
        return node;
    }

    private void setCustomerIds(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            node.setCustomerIdFromName();
        }
    }
}
