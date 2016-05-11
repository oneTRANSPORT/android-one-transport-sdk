package net.uk.onetransport.android.test.onetransporttest.tests.bucks.locations;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedLocationArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetPredefinedLocationsArrayTest
        implements PredefinedLocationArrayCallback, OneTransportTest {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getPredefinedLocationsArray();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS get predefined locations array");
        this.dougalCallback = dougalCallback;
        PredefinedLocationArray.getPredefinedLocationArrayAsync(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onPredefinedLocationArrayReady(int i, PredefinedLocationArray predefinedLocationArray) {
        if (i != 1 || predefinedLocationArray == null
                || predefinedLocationArray.getPredefinedLocations() == null
                || predefinedLocationArray.getPredefinedLocations().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Predefined locations array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onPredefinedLocationArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Predefined location array error"));
    }

    private void getPredefinedLocationsArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get predefined location array");
        PredefinedLocationArray predefinedLocationArray = PredefinedLocationArray
                .getPredefinedLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedLocationArray.getPredefinedLocations() == null
                || predefinedLocationArray.getPredefinedLocations().length == 0) {
            runnerTask.report("BUCKS get predefined location array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get predefined location array ... PASSED.", COLOUR_PASSED);
        }
    }
}
