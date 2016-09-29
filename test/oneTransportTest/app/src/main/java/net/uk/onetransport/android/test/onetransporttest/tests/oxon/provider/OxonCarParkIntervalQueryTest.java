package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonCarParkIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        carParkQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON car park interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void carParkQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON car park interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        CarPark[] carParks = OxonContentHelper.getCarParks(context, oldest, newest);
        CarPark[] carParks1 = OxonContentHelper.getCarParks(context);
        if (carParks.length != carParks1.length) {
            runnerTask.report("OXON car park interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        carParks = OxonContentHelper.getCarParks(context, oldest, newest);
        if (carParks.length > 0) {
            runnerTask.report("OXON car park interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("OXON car park interval query ... PASSED.", COLOUR_PASSED);
    }
}
