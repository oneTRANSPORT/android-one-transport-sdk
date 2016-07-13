package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.node.NodeArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.vector.VectorArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.vector.VectorArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetVectorArrayTest extends OneTransportTest implements VectorArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getVectorArray();
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BCS get vector array");
        this.dougalCallback = dougalCallback;
        VectorArray.getVectorArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onVectorArrayReady(int i, VectorArray vectorArray) {
        if (i != 1 || vectorArray == null || vectorArray.getVectors() == null
                || vectorArray.getVectors().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Vector array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onVectorArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Vector array error"));
    }

    private void getVectorArray() throws Exception {
        runnerTask.setCurrentTest("BCS get vector array");
        VectorArray vectorArray = VectorArray.getVectorArray(runnerTask.getContext());
        if (vectorArray.getVectors() == null || vectorArray.getVectors().length == 0) {
            runnerTask.report("BCS get vector array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get vector array ... PASSED.", COLOUR_PASSED);
        }
    }
}
