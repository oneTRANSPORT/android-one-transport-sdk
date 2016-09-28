package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficQueueInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficQueue(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic queue insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficQueue(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic queue insert");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        if (trafficQueues == null || trafficQueues.length == 0) {
            runnerTask.report("HERTS traffic queue insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        HertsContentHelper.insertIntoProvider(context, trafficQueues);
        HertsContentHelper.insertIntoProvider(context, trafficQueues);
        TrafficQueue[] trafficQueues1 = HertsContentHelper.getTrafficQueues(context);
        if (trafficQueues.length == trafficQueues1.length) {
            runnerTask.report("HERTS traffic queue insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic queue insert ... FAILED.", COLOUR_FAILED);
    }
}
