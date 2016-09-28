package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficTravelTimeIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficTravelTimeQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic travel time interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficTravelTimeQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic travel time interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficTravelTime[] trafficTravelTimes = HertsContentHelper.getTrafficTravelTimes(context,
                oldest, newest);
        TrafficTravelTime[] trafficTravelTimes1 = HertsContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length != trafficTravelTimes1.length) {
            runnerTask.report("HERTS traffic travel time interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficTravelTimes = HertsContentHelper.getTrafficTravelTimes(context, oldest, newest);
        if (trafficTravelTimes.length > 0) {
            runnerTask.report("HERTS traffic travel time interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("HERTS traffic travel time interval query ... PASSED.", COLOUR_PASSED);
    }
}
