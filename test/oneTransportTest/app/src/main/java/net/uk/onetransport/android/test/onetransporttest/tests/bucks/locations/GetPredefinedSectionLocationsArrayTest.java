package net.uk.onetransport.android.test.onetransporttest.tests.bucks.locations;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedSectionLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedSectionLocationArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetPredefinedSectionLocationsArrayTest
        implements PredefinedSectionLocationArrayCallback, OneTransportTest {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getPredefinedLocationsArray();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS get predefined section locations array");
        this.dougalCallback = dougalCallback;
        PredefinedSectionLocationArray.getPredefinedSectionLocationArrayAsync(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onPredefinedSectionLocationArrayReady(int i,
                                                      PredefinedSectionLocationArray predefinedSectionLocationArray) {
        if (i != 1 || predefinedSectionLocationArray == null
                || predefinedSectionLocationArray.getPredefinedSectionLocations() == null
                || predefinedSectionLocationArray.getPredefinedSectionLocations().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Predefined section locations array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onPredefinedSectionLocationArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Predefined section location array error"));
    }

    private void getPredefinedLocationsArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get predefined section location array");
        PredefinedSectionLocationArray predefinedSectionLocationArray = PredefinedSectionLocationArray
                .getPredefinedSectionLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedSectionLocationArray.getPredefinedSectionLocations() == null
                || predefinedSectionLocationArray.getPredefinedSectionLocations().length == 0) {
            runnerTask.report("BUCKS get predefined section location array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get predefined section location array ... PASSED.", COLOUR_PASSED);
        }
    }
}
