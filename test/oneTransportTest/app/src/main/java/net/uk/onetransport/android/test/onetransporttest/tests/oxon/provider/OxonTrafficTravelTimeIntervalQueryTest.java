package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficTravelTimeIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficTravelTimeQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic travel time interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficTravelTimeQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic travel time interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficTravelTime[] trafficTravelTimes = OxonContentHelper.getTrafficTravelTimes(context,
                oldest, newest);
        TrafficTravelTime[] trafficTravelTimes1 = OxonContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length != trafficTravelTimes1.length) {
            runnerTask.report("OXON traffic travel time interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficTravelTimes = OxonContentHelper.getTrafficTravelTimes(context, oldest, newest);
        if (trafficTravelTimes.length > 0) {
            runnerTask.report("OXON traffic travel time interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("OXON traffic travel time interval query ... PASSED.", COLOUR_PASSED);
    }
}
