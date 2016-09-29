package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficQueueDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficQueue(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic queue delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficQueue(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic queue delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        TrafficQueue[] trafficQueues = OxonContentHelper.getTrafficQueues(context);
        if (trafficQueues.length == 0) {
            runnerTask.report("OXON traffic queue delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic queue delete ... FAILED.", COLOUR_FAILED);
    }
}
