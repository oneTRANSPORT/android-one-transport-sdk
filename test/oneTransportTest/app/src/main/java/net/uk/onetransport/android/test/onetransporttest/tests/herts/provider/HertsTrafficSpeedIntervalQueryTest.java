package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficSpeedIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficSpeedQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic speed interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficSpeedQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic speed interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficSpeed[] trafficSpeeds = HertsContentHelper.getTrafficSpeeds(context, oldest, newest);
        TrafficSpeed[] trafficSpeeds1 = HertsContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length != trafficSpeeds1.length) {
            runnerTask.report("HERTS traffic speed interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficSpeeds = HertsContentHelper.getTrafficSpeeds(context, oldest, newest);
        if (trafficSpeeds.length > 0) {
            runnerTask.report("HERTS traffic speed interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("HERTS traffic speed interval query ... PASSED.", COLOUR_PASSED);
    }
}
