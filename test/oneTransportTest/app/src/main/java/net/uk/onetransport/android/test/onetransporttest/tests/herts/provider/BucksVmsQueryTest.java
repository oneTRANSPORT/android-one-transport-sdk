package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVmsQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        vmsQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS vms query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void vmsQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS vms query");
        Cursor cursor = BucksContentHelper.getVariableMessageSignCursor(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS vms query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS vms query ... FAILED.", COLOUR_FAILED);
    }
}
