package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficSpeedLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficSpeedQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic speed latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficSpeedQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic speed latest query");
        Context context = runnerTask.getContext();
        TrafficSpeed[] trafficSpeeds = HertsContentHelper.getLatestTrafficSpeeds(context);
        if (trafficSpeeds.length > 0) {
            runnerTask.report("HERTS traffic speed latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic speed latest query ... FAILED.", COLOUR_FAILED);
    }
}
