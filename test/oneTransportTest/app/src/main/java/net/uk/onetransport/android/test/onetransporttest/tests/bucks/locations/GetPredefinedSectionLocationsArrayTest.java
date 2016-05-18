package net.uk.onetransport.android.test.onetransporttest.tests.bucks.locations;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedSectionLocationArray;
import net.uk.onetransport.android.county.bucks.locations.SegmentLocationArray;
import net.uk.onetransport.android.county.bucks.locations.SegmentLocationArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetPredefinedSectionLocationsArrayTest
        implements SegmentLocationArrayCallback, OneTransportTest {

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
        PredefinedSectionLocationArray.getSegmentLocationArrayAsync(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onSegmentLocationArrayReady(int i,
                                            SegmentLocationArray segmentLocationArray) {
        if (i != 1 || segmentLocationArray == null
                || segmentLocationArray.getSegmentLocations() == null
                || segmentLocationArray.getSegmentLocations().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Predefined section locations array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onSegmentLocationArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Predefined section location array error"));
    }

    private void getPredefinedLocationsArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get predefined section location array");
        SegmentLocationArray segmentLocationArray = PredefinedSectionLocationArray
                .getSegmentLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (segmentLocationArray.getSegmentLocations() == null
                || segmentLocationArray.getSegmentLocations().length == 0) {
            runnerTask.report("BUCKS get predefined section location array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get predefined section location array ... PASSED.", COLOUR_PASSED);
        }
    }
}
