package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficTravelTimeLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficTravelTimeQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic travel time latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficTravelTimeQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic travel time latest query");
        Context context = runnerTask.getContext();
        TrafficTravelTime[] trafficTravelTimes = HertsContentHelper.getLatestTrafficTravelTimes(context);
        if (trafficTravelTimes.length > 0) {
            runnerTask.report("HERTS traffic travel time latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic travel time latest query ... FAILED.", COLOUR_FAILED);
    }
}
