package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficQueueLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficQueueQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic queue latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficQueueQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic queue latest query");
        Context context = runnerTask.getContext();
        TrafficQueue[] trafficQueues = HertsContentHelper.getLatestTrafficQueues(context);
        if (trafficQueues.length > 0) {
            runnerTask.report("HERTS traffic queue latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic queue latest query ... FAILED.", COLOUR_FAILED);
    }
}
