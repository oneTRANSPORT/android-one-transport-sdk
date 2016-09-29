package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficQueueIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficQueueQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic queue interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficQueueQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic queue interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficQueue[] trafficQueues = OxonContentHelper.getTrafficQueues(context, oldest, newest);
        TrafficQueue[] trafficQueues1 = OxonContentHelper.getTrafficQueues(context);
        if (trafficQueues.length != trafficQueues1.length) {
            runnerTask.report("OXON traffic queue interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficQueues = OxonContentHelper.getTrafficQueues(context, oldest, newest);
        if (trafficQueues.length > 0) {
            runnerTask.report("OXON traffic queue interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("OXON traffic queue interval query ... PASSED.", COLOUR_PASSED);
    }
}
