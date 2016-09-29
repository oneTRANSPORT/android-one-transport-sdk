package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsTrafficFlowInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficFlow(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS traffic flow insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficFlow(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS traffic flow insert");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        if (trafficFlows == null || trafficFlows.length == 0) {
            runnerTask.report("NORTHANTS traffic flow insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        NorthantsContentHelper.insertIntoProvider(context, trafficFlows);
        NorthantsContentHelper.insertIntoProvider(context, trafficFlows);
        TrafficFlow[] trafficFlows1 = NorthantsContentHelper.getTrafficFlows(context);
        if (trafficFlows.length == trafficFlows1.length) {
            runnerTask.report("NORTHANTS traffic flow insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS traffic flow insert ... FAILED.", COLOUR_FAILED);
    }
}