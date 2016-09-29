package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficSpeedInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficSpeed(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic speed insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficSpeed(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic speed insert");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        if (trafficSpeeds == null || trafficSpeeds.length == 0) {
            runnerTask.report("OXON traffic speed insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        OxonContentHelper.insertIntoProvider(context, trafficSpeeds);
        OxonContentHelper.insertIntoProvider(context, trafficSpeeds);
        TrafficSpeed[] trafficSpeeds1 = OxonContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length == trafficSpeeds1.length) {
            runnerTask.report("OXON traffic speed insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic speed insert ... FAILED.", COLOUR_FAILED);
    }
}
