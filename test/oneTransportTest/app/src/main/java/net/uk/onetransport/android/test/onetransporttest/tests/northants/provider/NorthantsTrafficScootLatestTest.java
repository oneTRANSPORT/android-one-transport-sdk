package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsTrafficScootLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficScootQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS traffic scoot latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficScootQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS traffic scoot latest query");
        Context context = runnerTask.getContext();
        TrafficScoot[] trafficScoots = NorthantsContentHelper.getLatestTrafficScoots(context);
        if (trafficScoots.length > 0) {
            runnerTask.report("NORTHANTS traffic scoot latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS traffic scoot latest query ... FAILED.", COLOUR_FAILED);
    }
}