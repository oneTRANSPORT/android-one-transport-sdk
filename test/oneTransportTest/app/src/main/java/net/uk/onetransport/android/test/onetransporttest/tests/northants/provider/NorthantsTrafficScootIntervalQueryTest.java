package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsTrafficScootIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficScootQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS traffic scoot interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficScootQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS traffic scoot interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficScoot[] trafficScoots = NorthantsContentHelper.getTrafficScoots(context, oldest, newest);
        TrafficScoot[] trafficScoots1 = NorthantsContentHelper.getTrafficScoots(context);
        if (trafficScoots.length != trafficScoots1.length) {
            runnerTask.report("NORTHANTS traffic scoot interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficScoots = NorthantsContentHelper.getTrafficScoots(context, oldest, newest);
        if (trafficScoots.length > 0) {
            runnerTask.report("NORTHANTS traffic scoot interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("NORTHANTS traffic scoot interval query ... PASSED.", COLOUR_PASSED);
    }
}
