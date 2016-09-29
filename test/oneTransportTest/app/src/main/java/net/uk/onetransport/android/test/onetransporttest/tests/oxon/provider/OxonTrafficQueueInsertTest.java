package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficQueueInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficQueue(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic queue insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficQueue(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic queue insert");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        if (trafficQueues == null || trafficQueues.length == 0) {
            runnerTask.report("OXON traffic queue insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        OxonContentHelper.insertIntoProvider(context, trafficQueues);
        OxonContentHelper.insertIntoProvider(context, trafficQueues);
        TrafficQueue[] trafficQueues1 = OxonContentHelper.getTrafficQueues(context);
        if (trafficQueues.length == trafficQueues1.length) {
            runnerTask.report("OXON traffic queue insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic queue insert ... FAILED.", COLOUR_FAILED);
    }
}
