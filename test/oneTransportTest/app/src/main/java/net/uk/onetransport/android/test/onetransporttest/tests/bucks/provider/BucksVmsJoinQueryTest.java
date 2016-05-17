package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVmsJoinQueryTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        vmsJoinLocationQuery();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS vms join location query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void vmsJoinLocationQuery() throws Exception {
        runnerTask.setCurrentTest("BUCKS vms join location query");
        Cursor cursor = BucksContentHelper.getVmsJoinLocations(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS vms join location query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS vms join location query ... FAILED.", COLOUR_FAILED);
    }
}
