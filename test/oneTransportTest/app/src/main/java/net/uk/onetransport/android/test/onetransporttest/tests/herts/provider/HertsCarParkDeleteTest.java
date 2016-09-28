package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.carparks.CarPark;
import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsCarParkDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteCarParks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS car park delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteCarParks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS car park delete");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_CAR_PARK);
        CarPark[] carParks = HertsContentHelper.getCarParks(context);
        if (carParks.length == 0) {
            runnerTask.report("HERTS car park delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS car park delete ... FAILED.", COLOUR_FAILED);
    }
}
