package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficSpeedDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficSpeed(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic speed delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficSpeed(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic speed delete");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        TrafficSpeed[] trafficSpeeds = HertsContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length == 0) {
            runnerTask.report("HERTS traffic speed delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic speed delete ... FAILED.", COLOUR_FAILED);
    }
}
