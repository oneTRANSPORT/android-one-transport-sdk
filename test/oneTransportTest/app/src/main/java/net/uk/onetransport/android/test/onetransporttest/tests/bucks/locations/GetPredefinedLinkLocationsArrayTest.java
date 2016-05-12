package net.uk.onetransport.android.test.onetransporttest.tests.bucks.locations;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedLinkLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedLinkLocationArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetPredefinedLinkLocationsArrayTest
        implements PredefinedLinkLocationArrayCallback, OneTransportTest {

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
        PredefinedLinkLocationArray.getPredefinedLinkLocationArrayAsync(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onPredefinedLinkLocationArrayReady(int i,
                                                 PredefinedLinkLocationArray predefinedLinkLocationArray) {
        if (i != 1 || predefinedLinkLocationArray == null
                || predefinedLinkLocationArray.getPredefinedLinkLocations() == null
                || predefinedLinkLocationArray.getPredefinedLinkLocations().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Predefined link locations array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onPredefinedLinkLocationArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Predefined link location array error"));
    }

    private void getPredefinedLocationsArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get predefined link location array");
        PredefinedLinkLocationArray predefinedLinkLocationArray = PredefinedLinkLocationArray
                .getPredefinedLinkLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedLinkLocationArray.getPredefinedLinkLocations() == null
                || predefinedLinkLocationArray.getPredefinedLinkLocations().length == 0) {
            runnerTask.report("BUCKS get predefined link location array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get predefined link location array ... PASSED.", COLOUR_PASSED);
        }
    }
}
