package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficTravelTimeLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficTravelTimeQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic travel time latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficTravelTimeQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic travel time latest query");
        Context context = runnerTask.getContext();
        TrafficTravelTime[] trafficTravelTimes = OxonContentHelper.getLatestTrafficTravelTimes(context);
        if (trafficTravelTimes.length > 0) {
            runnerTask.report("OXON traffic travel time latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic travel time latest query ... FAILED.", COLOUR_FAILED);
    }
}
