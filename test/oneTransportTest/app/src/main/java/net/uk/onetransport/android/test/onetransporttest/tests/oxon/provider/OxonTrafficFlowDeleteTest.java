package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlow;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficFlowDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficFlow(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic flow delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficFlow(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic flow delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        TrafficFlow[] trafficFlows = OxonContentHelper.getTrafficFlows(context);
        if (trafficFlows.length == 0) {
            runnerTask.report("OXON traffic flow delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic flow delete ... FAILED.", COLOUR_FAILED);
    }
}
