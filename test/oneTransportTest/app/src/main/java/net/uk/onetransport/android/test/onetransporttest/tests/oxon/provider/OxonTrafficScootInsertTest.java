package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficScootInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficScoot(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic scoot insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficScoot(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic scoot insert");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        if (trafficScoots == null || trafficScoots.length == 0) {
            runnerTask.report("OXON traffic scoot insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        OxonContentHelper.insertIntoProvider(context, trafficScoots);
        OxonContentHelper.insertIntoProvider(context, trafficScoots);
        TrafficScoot[] trafficScoots1 = OxonContentHelper.getTrafficScoots(context);
        if (trafficScoots.length == trafficScoots1.length) {
            runnerTask.report("OXON traffic scoot insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic scoot insert ... FAILED.", COLOUR_FAILED);
    }
}
