package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowArray;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficFlowInsertTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertTrafficFlow();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS traffic flow insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficFlow() throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic flow insert");
        TrafficFlowArray trafficFlowArray = TrafficFlowArray.getTrafficFlowArray(runnerTask.getContext());
        if (trafficFlowArray.getTrafficFlows() == null || trafficFlowArray.getTrafficFlows().length == 0) {
            runnerTask.report("BUCKS traffic flow insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        BucksContentHelper.insertIntoProvider(context, trafficFlowArray.getTrafficFlows());
        Cursor cursor = BucksContentHelper.getTrafficFlows(context);
        if (cursor != null) {
            if (cursor.getCount() == trafficFlowArray.getTrafficFlows().length) {
                runnerTask.report("BUCKS traffic flow insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS traffic flow insert ... FAILED.", COLOUR_FAILED);
    }
}
