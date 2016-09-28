package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficQueueDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficQueue(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic queue delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficQueue(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic queue delete");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        TrafficQueue[] trafficQueues = HertsContentHelper.getTrafficQueues(context);
        if (trafficQueues.length == 0) {
            runnerTask.report("HERTS traffic queue delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic queue delete ... FAILED.", COLOUR_FAILED);
    }
}
