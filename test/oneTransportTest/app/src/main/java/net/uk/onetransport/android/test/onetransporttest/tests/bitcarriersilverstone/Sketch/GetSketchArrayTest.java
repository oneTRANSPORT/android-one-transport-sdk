package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.VectorArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetSketchArrayTest extends OneTransportTest implements SketchArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getSketchArray();
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BCS get sketch array");
        this.dougalCallback = dougalCallback;
        SketchArray.getSketchArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onSketchArrayReady(int i, SketchArray sketchArray) {
        if (i != 1 || sketchArray == null || sketchArray.getSketches() == null
                || sketchArray.getSketches().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Sketch array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onSketchArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Sketch array error"));
    }

    private void getSketchArray() throws Exception {
        runnerTask.setCurrentTest("BCS get sketch array");
        SketchArray sketchArray = SketchArray.getSketchArray(runnerTask.getContext());
        if (sketchArray.getSketches() == null || sketchArray.getSketches().length == 0) {
            runnerTask.report("BCS get sketch array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get sketch array ... PASSED.", COLOUR_PASSED);
        }
    }
}
