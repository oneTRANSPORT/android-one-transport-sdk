package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficFlowDeleteTest extends OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteTrafficFlow();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS traffic flow delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficFlow() throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic flow delete");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        Cursor cursor = BucksContentHelper.getTrafficFlows(context);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("BUCKS traffic flow delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS traffic flow delete ... FAILED.", COLOUR_FAILED);
    }
}
