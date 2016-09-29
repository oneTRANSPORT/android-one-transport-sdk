package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.northants.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsTrafficSpeedInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficSpeed(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS traffic speed insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficSpeed(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS traffic speed insert");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context,
                NorthantsContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        if (trafficSpeeds == null || trafficSpeeds.length == 0) {
            runnerTask.report("NORTHANTS traffic speed insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        NorthantsContentHelper.insertIntoProvider(context, trafficSpeeds);
        NorthantsContentHelper.insertIntoProvider(context, trafficSpeeds);
        TrafficSpeed[] trafficSpeeds1 = NorthantsContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length == trafficSpeeds1.length) {
            runnerTask.report("NORTHANTS traffic speed insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS traffic speed insert ... FAILED.", COLOUR_FAILED);
    }
}
