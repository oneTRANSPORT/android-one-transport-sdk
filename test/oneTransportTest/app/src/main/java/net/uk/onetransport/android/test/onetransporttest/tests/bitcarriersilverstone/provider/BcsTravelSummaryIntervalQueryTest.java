package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsTravelSummaryIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        travelSummaryQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS travel summary interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void travelSummaryQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS travel summary interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Cursor cursor = BcsContentHelper.getTravelSummaries(runnerTask.getContext(), oldest, newest);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BCS travel summary interval query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS travel summary interval query ... FAILED.", COLOUR_FAILED);
    }
}
