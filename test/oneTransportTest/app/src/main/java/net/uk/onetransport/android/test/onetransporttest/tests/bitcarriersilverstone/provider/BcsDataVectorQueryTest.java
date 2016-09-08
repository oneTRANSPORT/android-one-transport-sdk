package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsDataVectorQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        dataVectorQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS data vector query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void dataVectorQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS data vector query");
        Cursor cursor = BcsContentHelper.getDataVectors(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BCS data vector query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS data vector query ... FAILED.", COLOUR_FAILED);
    }
}
