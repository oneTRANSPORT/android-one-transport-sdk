package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficTravelTimeInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficTravelTime(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic travel time insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficTravelTime(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic travel time insert");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
        if (trafficTravelTimes == null || trafficTravelTimes.length == 0) {
            runnerTask.report("OXON traffic travel time insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        OxonContentHelper.insertIntoProvider(context, trafficTravelTimes);
        OxonContentHelper.insertIntoProvider(context, trafficTravelTimes);
        TrafficTravelTime[] trafficTravelTimes1 = OxonContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length == trafficTravelTimes1.length) {
            runnerTask.report("OXON traffic travel time insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic travel time insert ... FAILED.", COLOUR_FAILED);
    }
}
