package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.northants.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsTrafficScootInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficScoot(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS traffic scoot insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficScoot(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS traffic scoot insert");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context,
                NorthantsContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        if (trafficScoots == null || trafficScoots.length == 0) {
            runnerTask.report("NORTHANTS traffic scoot insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        NorthantsContentHelper.insertIntoProvider(context, trafficScoots);
        NorthantsContentHelper.insertIntoProvider(context, trafficScoots);
        TrafficScoot[] trafficScoots1 = NorthantsContentHelper.getTrafficScoots(context);
        if (trafficScoots.length == trafficScoots1.length) {
            runnerTask.report("NORTHANTS traffic scoot insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS traffic scoot insert ... FAILED.", COLOUR_FAILED);
    }
}
