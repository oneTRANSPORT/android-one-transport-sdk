package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsLatestDataVectorQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        latestDataVectorQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS latest data vector query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void latestDataVectorQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS latest data vector query");
        Cursor cursor = BcsContentHelper.getLatestDataVectorCursor(runnerTask.getContext());
        Vector[] vectors = BcsContentHelper.getLatestDataVectors(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == vectors.length) {
                runnerTask.report("BCS latest data vector query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS latest data vector query ... FAILED.", COLOUR_FAILED);
    }
}
