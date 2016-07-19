package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchRetriever;
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
        // The sync adapter should do this anyway, but just setting the pre-condition for the test.
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_SKETCH);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        BcsProviderModule.refresh(context, true, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        ArrayList<Sketch> sketches = new SketchRetriever().retrieve(context);
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        Cursor cursor = BcsContentHelper.getSketches(context);
        if (cursor != null) {
            if (cursor.getCount() == sketches.size()) {
                runnerTask.report("BCS sync adapter ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS sync adapter ... FAILED.", COLOUR_FAILED);
    }
}
