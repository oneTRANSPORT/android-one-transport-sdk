package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.carparks.CarPark;
import net.uk.onetransport.android.county.herts.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsCarParkInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertCarParks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS car park insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertCarParks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS car park insert");
        Context context = runnerTask.getContext();
        // This is only needed if new CIs are created during the running of the tests.
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_CAR_PARK);
        CarPark[] carParks = new CarParkRetriever(context).retrieve();
        if (carParks == null || carParks.length == 0) {
            runnerTask.report("HERTS car park insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        // Insert the same thing twice to check for a working UNIQUE ON CONFLICT IGNORE index.
        HertsContentHelper.insertIntoProvider(context, carParks);
        HertsContentHelper.insertIntoProvider(context, carParks);
        CarPark[] carParks1 = HertsContentHelper.getCarParks(context);
        if (carParks.length == carParks1.length) {
            runnerTask.report("HERTS car park insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS car park insert ... FAILED.", COLOUR_FAILED);
    }
}
