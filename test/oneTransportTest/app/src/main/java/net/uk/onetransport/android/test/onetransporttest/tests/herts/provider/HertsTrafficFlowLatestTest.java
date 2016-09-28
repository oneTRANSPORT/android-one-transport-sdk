package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlow;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficFlowLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficFlowQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic flow latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficFlowQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic flow latest query");
        Context context = runnerTask.getContext();
        TrafficFlow[] trafficFlows = HertsContentHelper.getLatestTrafficFlows(context);
        if (trafficFlows.length > 0) {
            runnerTask.report("HERTS traffic flow latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic flow latest query ... FAILED.", COLOUR_FAILED);
    }
}
