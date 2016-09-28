package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficFlowInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficFlow(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic flow insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficFlow(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic flow insert");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        if (trafficFlows == null || trafficFlows.length == 0) {
            runnerTask.report("HERTS traffic flow insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        HertsContentHelper.insertIntoProvider(context, trafficFlows);
        HertsContentHelper.insertIntoProvider(context, trafficFlows);
        TrafficFlow[] trafficFlows1 = HertsContentHelper.getTrafficFlows(context);
        if (trafficFlows.length == trafficFlows1.length) {
            runnerTask.report("HERTS traffic flow insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic flow insert ... FAILED.", COLOUR_FAILED);
    }
}
