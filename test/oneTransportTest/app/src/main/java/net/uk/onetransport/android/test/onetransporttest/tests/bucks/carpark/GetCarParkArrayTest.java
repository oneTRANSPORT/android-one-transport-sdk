package net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.county.bucks.carparks.CarParkArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;

public class GetCarParkArrayTest implements CarParkArrayCallback, CarParkTest {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getCarParkArray();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS get car park array");
        this.dougalCallback = dougalCallback;
        CarParkArray.getCarParkArrayAsync(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onCarParkListReady(int i, CarParkArray carParkArray) {
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
    public void onCarParkListError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Car park array error"));
    }

    private void getCarParkArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get car park array");
        CarParkArray carParkArray = CarParkArray.getCarParkArray(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD);
        if (carParkArray.getCarParks() == null || carParkArray.getCarParks().length == 0) {
            runnerTask.report("BUCKS get car park array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get car park array ... PASSED.", COLOUR_PASSED);
        }
    }

}
