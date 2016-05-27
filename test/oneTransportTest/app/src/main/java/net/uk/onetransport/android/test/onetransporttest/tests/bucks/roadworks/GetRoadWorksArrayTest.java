package net.uk.onetransport.android.test.onetransporttest.tests.bucks.roadworks;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.roadworks.RoadWorksArray;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorksArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetRoadWorksArrayTest extends OneTransportTest
        implements RoadWorksArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getRoadWorksArray();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS get road works array");
        this.dougalCallback = dougalCallback;
        RoadWorksArray.getRoadWorksArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onRoadWorksArrayReady(int i, RoadWorksArray roadWorksArray) {
        if (i != 1 || roadWorksArray == null
                || roadWorksArray.getRoadWorks() == null
                || roadWorksArray.getRoadWorks().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Road works array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onRoadWorksArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Road works array error"));
    }

    private void getRoadWorksArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get road works array");
        RoadWorksArray roadWorksArray = RoadWorksArray.getRoadWorksArray(runnerTask.getContext());
        if (roadWorksArray.getRoadWorks() == null || roadWorksArray.getRoadWorks().length == 0) {
            runnerTask.report("BUCKS get road works array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get road works array ... PASSED.", COLOUR_PASSED);
        }
    }
}
