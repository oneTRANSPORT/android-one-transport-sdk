package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsSketchIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        sketchQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS sketch interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    // TODO    Re-do all interval tests when Silverstone data is present.
    private void sketchQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS sketch interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Cursor cursor = BcsContentHelper.getSketchCursor(runnerTask.getContext(), oldest, newest);
        Sketch[] sketches = BcsContentHelper.getSketches(runnerTask.getContext(), oldest, newest);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == sketches.length) {
                runnerTask.report("BCS sketch interval query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS sketch interval query ... FAILED.", COLOUR_FAILED);
    }
}
