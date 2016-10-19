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
package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class BcsNodeInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertNodes(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS node insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertNodes(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS node insert");
        Context context = runnerTask.getContext();
        ArrayList<Node> nodes = new NodeRetriever(context).retrieve();
        if (nodes == null || nodes.size() == 0) {
            runnerTask.report("BCS node insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.insertNodesIntoProvider(context, nodes);
        Cursor cursor = BcsContentHelper.getNodeCursor(context);
        Node[] nodes1 = BcsContentHelper.getNodes(context);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == nodes1.length) {
                runnerTask.report("BCS node insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS node insert ... FAILED.", COLOUR_FAILED);
    }
}
