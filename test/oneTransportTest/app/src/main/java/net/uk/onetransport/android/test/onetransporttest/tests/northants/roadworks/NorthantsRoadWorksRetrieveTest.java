package net.uk.onetransport.android.test.onetransporttest.tests.northants.roadworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.generic.RetrieverResult;
import net.uk.onetransport.android.county.northants.roadworks.Roadworks;
import net.uk.onetransport.android.county.northants.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.northants.roadworks.RoadworksRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsRoadworksRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Roadworks>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getRoadworksArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS get road works array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<Roadworks>> onCreateLoader(int id, Bundle args) {
        return new RoadworksRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<Roadworks>> loader,
                               RetrieverResult<Roadworks> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("NORTHANTS road works array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<Roadworks>> loader) {
        // Nothing needs to be done.
    }

    private void getRoadworksArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS get road works array");
        Roadworks[] roadworkses = new RoadworksRetriever(getContext()).retrieve();
        if (roadworkses == null || roadworkses.length == 0) {
            runnerTask.report("NORTHANTS get road works array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("NORTHANTS get road works array ... PASSED.", COLOUR_PASSED);
        }
    }
}
