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
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_VECTOR);
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
        ArrayList<Vector> vectors = new VectorRetriever(context).retrieve();
        ArrayList<Sketch> sketches = new SketchRetriever(context).retrieve();
        ArrayList<TravelSummary> travelSummaries = new TravelSummaryRetriever(context).retrieve();
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        Cursor cursor = BcsContentHelper.getNodes(context);
        if (cursor != null) {
            if (cursor.getCount() != nodes.size()) {
                runnerTask.report("BCS sync adapter nodes ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        cursor = BcsContentHelper.getVectors(context);
        if (cursor != null) {
            if (cursor.getCount() != vectors.size()) {
                runnerTask.report("BCS sync adapter vectors ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        cursor = BcsContentHelper.getSketches(context);
        if (cursor != null) {
            if (cursor.getCount() != sketches.size()) {
                runnerTask.report("BCS sync adapter sketches ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        cursor = BcsContentHelper.getTravelSummaries(context);
        if (cursor != null) {
            if (cursor.getCount() != travelSummaries.size()) {
                runnerTask.report("BCS sync adapter travel summaries ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS sync adapter ... PASSED.", COLOUR_PASSED);
    }
}
