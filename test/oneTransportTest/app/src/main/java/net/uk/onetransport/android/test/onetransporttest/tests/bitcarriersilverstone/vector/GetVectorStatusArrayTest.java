package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.traveltime.TravelSummaryArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatusArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatusArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetVectorStatusArrayTest extends OneTransportTest
        implements VectorStatusArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getVectorStatusArray();
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BCS get vector status array");
        this.dougalCallback = dougalCallback;
        VectorStatusArray.getVectorStatusArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onVectorStatusArrayReady(int i, VectorStatusArray vectorStatusArray) {
        if (i != 1 || vectorStatusArray == null || vectorStatusArray.getVectorStatuses() == null
                || vectorStatusArray.getVectorStatuses().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Vector status array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onVectorStatusArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Vector status array error"));
    }

    private void getVectorStatusArray() throws Exception {
        runnerTask.setCurrentTest("BCS get vector status array");
        VectorStatusArray vectorStatusArray = VectorStatusArray.getVectorStatusArray(
                runnerTask.getContext());
        if (vectorStatusArray.getVectorStatuses() == null
                || vectorStatusArray.getVectorStatuses().length == 0) {
            runnerTask.report("BCS get vector status array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get vector status array ... PASSED.", COLOUR_PASSED);
        }
    }
}
