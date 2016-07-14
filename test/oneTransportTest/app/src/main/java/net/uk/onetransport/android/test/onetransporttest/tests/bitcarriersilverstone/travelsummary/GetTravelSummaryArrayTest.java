package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.travelsummary;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.traveltime.TravelSummaryArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.traveltime.TravelSummaryArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetTravelSummaryArrayTest extends OneTransportTest
        implements TravelSummaryArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getTravelSummaryArray();
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BCS get travel summary array");
        this.dougalCallback = dougalCallback;
        TravelSummaryArray.getTravelSummaryArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onTravelSummaryArrayReady(int i, TravelSummaryArray travelSummaryArray) {
        if (i != 1 || travelSummaryArray == null || travelSummaryArray.getTravelSummaries() == null
                || travelSummaryArray.getTravelSummaries().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Travel summary array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onTravelSummaryArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Travel summary array error"));
    }

    private void getTravelSummaryArray() throws Exception {
        runnerTask.setCurrentTest("BCS get travel summary array");
        TravelSummaryArray travelSummaryArray = TravelSummaryArray.getTravelSummaryArray(
                runnerTask.getContext());
        if (travelSummaryArray.getTravelSummaries() == null
                || travelSummaryArray.getTravelSummaries().length == 0) {
            runnerTask.report("BCS get travel summary array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get travel summary array ... PASSED.", COLOUR_PASSED);
        }
    }
}
