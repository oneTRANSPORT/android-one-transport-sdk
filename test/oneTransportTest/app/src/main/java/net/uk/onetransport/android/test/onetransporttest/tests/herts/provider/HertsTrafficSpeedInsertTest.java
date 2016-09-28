package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficSpeedInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficSpeed(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic speed insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficSpeed(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic speed insert");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        if (trafficSpeeds == null || trafficSpeeds.length == 0) {
            runnerTask.report("HERTS traffic speed insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        HertsContentHelper.insertIntoProvider(context, trafficSpeeds);
        HertsContentHelper.insertIntoProvider(context, trafficSpeeds);
        TrafficSpeed[] trafficSpeeds1 = HertsContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length == trafficSpeeds1.length) {
            runnerTask.report("HERTS traffic speed insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic speed insert ... FAILED.", COLOUR_FAILED);
    }
}
