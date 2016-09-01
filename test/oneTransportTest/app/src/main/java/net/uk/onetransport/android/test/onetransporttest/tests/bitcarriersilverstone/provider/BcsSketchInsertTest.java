package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class BcsSketchInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertSketches(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS sketch insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertSketches(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS sketch insert");
        Context context = runnerTask.getContext();
        ArrayList<Sketch> sketches = new SketchRetriever(context).retrieve();
        if (sketches == null || sketches.size() == 0) {
            runnerTask.report("BCS sketch insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.insertDataSketchesIntoProvider(context, sketches);
        Cursor cursor = BcsContentHelper.getDataSketches(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BCS sketch insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS sketch insert ... FAILED.", COLOUR_FAILED);
    }
}
