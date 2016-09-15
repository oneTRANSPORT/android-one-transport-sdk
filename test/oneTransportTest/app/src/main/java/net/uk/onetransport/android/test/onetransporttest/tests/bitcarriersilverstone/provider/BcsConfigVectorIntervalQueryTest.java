package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsConfigVectorIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        configVectorQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS config vector interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void configVectorQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS config vector interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Cursor cursor = BcsContentHelper.getConfigVectorCursor(runnerTask.getContext(), oldest, newest);
        Vector[] vectors = BcsContentHelper.getConfigVectors(runnerTask.getContext(), oldest, newest);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == vectors.length) {
                runnerTask.report("BCS config vector interval query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS config vector interval query ... FAILED.", COLOUR_FAILED);
    }
}
