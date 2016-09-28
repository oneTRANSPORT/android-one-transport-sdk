package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.carparks.CarPark;
import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsCarParkLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        carParkQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS car park latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void carParkQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS car park latest query");
        Context context = runnerTask.getContext();
        CarPark[] carParks = HertsContentHelper.getLatestCarParks(context);
        if (carParks.length > 0) {
            runnerTask.report("HERTS car park latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS car park latest query ... FAILED.", COLOUR_FAILED);
    }
}
