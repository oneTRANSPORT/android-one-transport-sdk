package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlow;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficFlowIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficFlowQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic flow interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficFlowQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic flow interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficFlow[] trafficFlows = HertsContentHelper.getTrafficFlows(context, oldest, newest);
        TrafficFlow[] trafficFlows1 = HertsContentHelper.getTrafficFlows(context);
        if (trafficFlows.length != trafficFlows1.length) {
            runnerTask.report("HERTS traffic flow interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficFlows = HertsContentHelper.getTrafficFlows(context, oldest, newest);
        if (trafficFlows.length > 0) {
            runnerTask.report("HERTS traffic flow interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("HERTS traffic flow interval query ... PASSED.", COLOUR_PASSED);
    }
}
