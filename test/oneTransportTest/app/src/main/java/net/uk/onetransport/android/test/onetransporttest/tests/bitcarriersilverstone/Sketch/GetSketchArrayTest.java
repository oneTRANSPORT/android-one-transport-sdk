package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch.SketchRetrieverLoader;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class GetSketchArrayTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Sketch>> {

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
        ((RunnerTask) dougalCallback).getLoaderManager().initLoader(0, null, this);
    }

//    @Override
//    public void onSketchArrayReady(int i, SketchArray sketchArray) {
//        if (i != 1 || sketchArray == null || sketchArray.getSketches() == null
//                || sketchArray.getSketches().length == 0) {
//            dougalCallback.getResponse(null, new Throwable("Sketch array error"));
//        } else {
//            // Just send any valid resource.
//            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
//                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
//        }
//    }
//
//    @Override
//    public void onSketchArrayError(int i, Throwable throwable) {
//        dougalCallback.getResponse(null, new Throwable("Sketch array error"));
//    }

    @Override
    public Loader<RetrieverResult<Sketch>> onCreateLoader(int id, Bundle args) {
        return new SketchRetrieverLoader(runnerTask.getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<Sketch>> loader, RetrieverResult<Sketch> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Sketch array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<Sketch>> loader) {
        // Nothing needs to be done.
    }

    private void getSketchArray() throws Exception {
        runnerTask.setCurrentTest("BCS get sketch array");
        SketchRetriever sketchRetriever = new SketchRetriever();
        ArrayList<Sketch> sketches = sketchRetriever.retrieve(runnerTask.getContext());
        if (sketches == null || sketches.size() == 0) {
            runnerTask.report("BCS get sketch array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get sketch array ... PASSED.", COLOUR_PASSED);
        }
    }

}
