package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonCarParkDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteCarParks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON car park delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteCarParks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON car park delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_CAR_PARK);
        CarPark[] carParks = OxonContentHelper.getCarParks(context);
        if (carParks.length == 0) {
            runnerTask.report("OXON car park delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON car park delete ... FAILED.", COLOUR_FAILED);
    }
}
