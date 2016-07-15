package net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.county.bucks.carparks.CarParkArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetCarParkArrayTest extends OneTransportTest implements CarParkArrayCallback {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getCarParkArray(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS get car park array");
        this.dougalCallback = dougalCallback;
        CarParkArray.getCarParkArrayAsync(((RunnerFragment) dougalCallback).getContext(), this, 1);
    }

    @Override
    public void onCarParkArrayReady(int i, CarParkArray carParkArray) {
        if (i != 1 || carParkArray == null || carParkArray.getCarParks() == null
                || carParkArray.getCarParks().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Car park array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onCarParkArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Car park array error"));
    }

    private void getCarParkArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS get car park array");
        CarParkArray carParkArray = CarParkArray.getCarParkArray(runnerTask.getContext());
        if (carParkArray.getCarParks() == null || carParkArray.getCarParks().length == 0) {
            runnerTask.report("BUCKS get car park array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get car park array ... PASSED.", COLOUR_PASSED);
        }
    }

}
