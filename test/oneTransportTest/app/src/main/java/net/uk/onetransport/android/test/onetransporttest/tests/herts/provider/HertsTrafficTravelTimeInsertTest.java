package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficTravelTimeInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficTravelTime(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic travel time insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficTravelTime(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic travel time insert");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
        if (trafficTravelTimes == null || trafficTravelTimes.length == 0) {
            runnerTask.report("HERTS traffic travel time insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        HertsContentHelper.insertIntoProvider(context, trafficTravelTimes);
        HertsContentHelper.insertIntoProvider(context, trafficTravelTimes);
        TrafficTravelTime[] trafficTravelTimes1 = HertsContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length == trafficTravelTimes1.length) {
            runnerTask.report("HERTS traffic travel time insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic travel time insert ... FAILED.", COLOUR_FAILED);
    }
}
