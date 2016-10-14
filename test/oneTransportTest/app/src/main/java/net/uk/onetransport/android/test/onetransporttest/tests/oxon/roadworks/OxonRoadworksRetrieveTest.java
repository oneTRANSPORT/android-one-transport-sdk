package net.uk.onetransport.android.test.onetransporttest.tests.oxon.roadworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.generic.RetrieverResult;
import net.uk.onetransport.android.county.oxon.roadworks.Roadworks;
import net.uk.onetransport.android.county.oxon.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.oxon.roadworks.RoadworksRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonRoadworksRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Roadworks>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getRoadworksArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON get road works array");
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
            dougalCallback.getResponse(null, new Throwable("OXON road works array error"));
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
        runnerTask.setCurrentTest("OXON get road works array");
        Roadworks[] roadworkses = new RoadworksRetriever(getContext()).retrieve();
        if (roadworkses == null || roadworkses.length == 0) {
            runnerTask.report("OXON get road works array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("OXON get road works array ... PASSED.", COLOUR_PASSED);
        }
    }
}
