package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficTravelTimeDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficTravelTime(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic travel time delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficTravelTime(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic travel time delete");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        TrafficTravelTime[] trafficTravelTimes = HertsContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length == 0) {
            runnerTask.report("HERTS traffic travel time delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic travel time delete ... FAILED.", COLOUR_FAILED);
    }
}
