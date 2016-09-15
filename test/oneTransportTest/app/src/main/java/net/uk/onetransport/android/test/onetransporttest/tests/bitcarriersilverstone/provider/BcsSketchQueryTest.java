package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsSketchQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        sketchQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS sketch query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void sketchQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS sketch query");
        Cursor cursor = BcsContentHelper.getSketchCursor(runnerTask.getContext());
        Sketch[] sketches = BcsContentHelper.getSketches(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == sketches.length) {
                runnerTask.report("BCS sketch query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS sketch query ... FAILED.", COLOUR_FAILED);
    }
}
