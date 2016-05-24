package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficFlowJoinQueryTest extends OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        trafficFlowJoinLocationQuery();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS traffic flow join location query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficFlowJoinLocationQuery() throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic flow join location query");
        Cursor cursor = BucksContentHelper.getTrafficFlowJoinLocations(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS traffic flow join location query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS traffic flow join location query ... FAILED.", COLOUR_FAILED);
    }
}
