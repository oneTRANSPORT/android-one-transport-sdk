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
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.SketchRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummaryRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsProviderModule;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.AdapterObserver;

import java.util.ArrayList;

public class BcsSyncAdapterTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        startSync(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS sync adapter");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void startSync(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS sync adapter");
        Context context = runnerTask.getContext();
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_NODE);
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_CONFIG_VECTOR);
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_DATA_VECTOR);
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_SKETCH);
        BcsContentHelper.deleteFromProvider(context,
                BcsContentHelper.DATA_TYPE_TRAVEL_SUMMARY);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        BcsProviderModule.refresh(context, true, true, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        ArrayList<Node> nodes = new NodeRetriever(context).retrieve();
        ArrayList<net.uk.onetransport.android.modules.bitcarriersilverstone
                .config.vector.Vector> configVectors = new net.uk.onetransport.android.modules
                .bitcarriersilverstone.config.vector.VectorRetriever(context).retrieve();
        ArrayList<Vector> dataVectors = new VectorRetriever(context).retrieve();
        ArrayList<Sketch> sketches = new SketchRetriever(context).retrieve();
        ArrayList<TravelSummary> travelSummaries = new TravelSummaryRetriever(context).retrieve();
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        Cursor cursor = BcsContentHelper.getNodeCursor(context);
        Node[] nodes1 = BcsContentHelper.getNodes(context);
        if (cursor != null) {
            if (cursor.getCount() != nodes.size() || nodes.size() != nodes1.length) {
                runnerTask.report("BCS sync adapter nodes ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        cursor = BcsContentHelper.getConfigVectorCursor(context);
        net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector
                .Vector[] configVectors1 = BcsContentHelper.getConfigVectors(context);
        if (cursor != null) {
            if (cursor.getCount() != configVectors.size() || configVectors.size() != configVectors1.length) {
                runnerTask.report("BCS sync adapter config vectors ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        cursor = BcsContentHelper.getDataVectorCursor(context);
        Vector[] dataVectors1 = BcsContentHelper.getDataVectors(context);
        if (cursor != null) {
            if (cursor.getCount() != dataVectors.size() || dataVectors.size() != dataVectors1.length) {
                runnerTask.report("BCS sync adapter data vectors ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        cursor = BcsContentHelper.getSketchCursor(context);
        Sketch[] sketches1 = BcsContentHelper.getSketches(context);
        if (cursor != null) {
            if (cursor.getCount() != sketches.size() || sketches.size() != sketches1.length) {
                runnerTask.report("BCS sync adapter sketches ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        cursor = BcsContentHelper.getTravelSummaryCursor(context);
        TravelSummary[] travelSummaries1 = BcsContentHelper.getTravelSummaries(context);
        if (cursor != null) {
            if (cursor.getCount() != travelSummaries.size()
                    || travelSummaries.size() != travelSummaries1.length) {
                runnerTask.report("BCS sync adapter travel summaries ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS sync adapter ... PASSED.", COLOUR_PASSED);
    }
}
