package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficTravelTimeDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficTravelTime(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic travel time delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficTravelTime(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic travel time delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        TrafficTravelTime[] trafficTravelTimes = OxonContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length == 0) {
            runnerTask.report("OXON traffic travel time delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic travel time delete ... FAILED.", COLOUR_FAILED);
    }
}
