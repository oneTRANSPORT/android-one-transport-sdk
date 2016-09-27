package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksCarParkIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        carParkQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("Bucks car park interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void carParkQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("Bucks car park interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        CarPark[] carParks = BucksContentHelper.getCarParks(context, oldest, newest);
        CarPark[] carParks1 = BucksContentHelper.getCarParks(context);
        if (carParks.length != carParks1.length) {
            runnerTask.report("Bucks car park interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        carParks = BucksContentHelper.getCarParks(context, oldest, newest);
        if (carParks.length > 0) {
            runnerTask.report("Bucks car park interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("Bucks car park interval query ... PASSED.", COLOUR_PASSED);
    }
}
