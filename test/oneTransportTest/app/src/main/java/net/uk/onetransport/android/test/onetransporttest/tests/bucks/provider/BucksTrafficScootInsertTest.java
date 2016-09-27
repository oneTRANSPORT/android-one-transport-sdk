package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficScootInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficScoot(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS traffic scoot insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficScoot(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic scoot insert");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        if (trafficScoots == null || trafficScoots.length == 0) {
            runnerTask.report("BUCKS traffic scoot insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, trafficScoots);
        BucksContentHelper.insertIntoProvider(context, trafficScoots);
        TrafficScoot[] trafficScoots1 = BucksContentHelper.getTrafficScoots(context);
        if (trafficScoots.length == trafficScoots1.length) {
            runnerTask.report("BUCKS traffic scoot insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS traffic scoot insert ... FAILED.", COLOUR_FAILED);
    }
}
