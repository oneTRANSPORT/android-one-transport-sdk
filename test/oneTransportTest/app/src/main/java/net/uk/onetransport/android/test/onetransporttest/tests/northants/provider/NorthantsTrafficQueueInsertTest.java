package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.northants.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsTrafficQueueInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficQueue(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS traffic queue insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficQueue(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS traffic queue insert");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context,
                NorthantsContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        if (trafficQueues == null || trafficQueues.length == 0) {
            runnerTask.report("NORTHANTS traffic queue insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        NorthantsContentHelper.insertIntoProvider(context, trafficQueues);
        NorthantsContentHelper.insertIntoProvider(context, trafficQueues);
        TrafficQueue[] trafficQueues1 = NorthantsContentHelper.getTrafficQueues(context);
        if (trafficQueues.length == trafficQueues1.length) {
            runnerTask.report("NORTHANTS traffic queue insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS traffic queue insert ... FAILED.", COLOUR_FAILED);
    }
}
