package net.uk.onetransport.android.test.onetransporttest.tests.bucks.locations;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocationArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetPredefinedVmsLocationsArrayTest
        implements PredefinedVmsLocationArrayCallback, OneTransportTest {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getPredefinedLocationsArray();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS get predefined link locations array");
        this.dougalCallback = dougalCallback;
        PredefinedVmsLocationArray.getPredefinedVmsLocationArrayAsync(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onPredefinedVmsLocationArrayReady(int i,
                                                 PredefinedVmsLocationArray predefinedVmsLocationArray) {
        if (i != 1 || predefinedVmsLocationArray == null
                || predefinedVmsLocationArray.getPredefinedVmsLocations() == null
                || predefinedVmsLocationArray.getPredefinedVmsLocations().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Predefined link locations array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onPredefinedVmsLocationArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Predefined link location array error"));
    }

    private void getPredefinedLocationsArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get predefined link location array");
        PredefinedVmsLocationArray predefinedVmsLocationArray = PredefinedVmsLocationArray
                .getPredefinedVmsLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedVmsLocationArray.getPredefinedVmsLocations() == null
                || predefinedVmsLocationArray.getPredefinedVmsLocations().length == 0) {
            runnerTask.report("BUCKS get predefined link location array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get predefined link location array ... PASSED.", COLOUR_PASSED);
        }
    }
}
