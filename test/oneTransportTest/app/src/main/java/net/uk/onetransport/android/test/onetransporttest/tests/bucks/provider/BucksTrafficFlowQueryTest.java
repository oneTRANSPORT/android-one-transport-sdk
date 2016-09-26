package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficFlowQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficFlowQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS traffic flow query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficFlowQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic flow query");
        Cursor cursor = BucksContentHelper.getTrafficFlowCursor(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS traffic flow query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS traffic flow query ... FAILED.", COLOUR_FAILED);
    }
}
