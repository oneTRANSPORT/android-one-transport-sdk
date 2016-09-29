package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficTravelTimeInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficTravelTime(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS traffic travel time insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficTravelTime(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic travel time insert");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
        if (trafficTravelTimes == null || trafficTravelTimes.length == 0) {
            runnerTask.report("BUCKS traffic travel time insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, trafficTravelTimes);
        BucksContentHelper.insertIntoProvider(context, trafficTravelTimes);
        TrafficTravelTime[] trafficTravelTimes1= BucksContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length == trafficTravelTimes1.length) {
            runnerTask.report("BUCKS traffic travel time insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS traffic travel time insert ... FAILED.", COLOUR_FAILED);
    }
}