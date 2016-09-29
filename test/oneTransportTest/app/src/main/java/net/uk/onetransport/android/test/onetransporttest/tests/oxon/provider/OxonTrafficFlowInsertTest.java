package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficFlowInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficFlow(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic flow insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficFlow(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic flow insert");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        if (trafficFlows == null || trafficFlows.length == 0) {
            runnerTask.report("OXON traffic flow insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        OxonContentHelper.insertIntoProvider(context, trafficFlows);
        OxonContentHelper.insertIntoProvider(context, trafficFlows);
        TrafficFlow[] trafficFlows1 = OxonContentHelper.getTrafficFlows(context);
        if (trafficFlows.length == trafficFlows1.length) {
            runnerTask.report("OXON traffic flow insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic flow insert ... FAILED.", COLOUR_FAILED);
    }
}
