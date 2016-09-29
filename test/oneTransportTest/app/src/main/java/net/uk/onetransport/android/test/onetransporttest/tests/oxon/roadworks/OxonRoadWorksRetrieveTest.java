package net.uk.onetransport.android.test.onetransporttest.tests.oxon.roadworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.generic.RetrieverResult;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorks;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorksRetriever;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorksRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonRoadWorksRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<RoadWorks>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getRoadWorksArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON get road works array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<RoadWorks>> onCreateLoader(int id, Bundle args) {
        return new RoadWorksRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<RoadWorks>> loader,
                               RetrieverResult<RoadWorks> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("OXON road works array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<RoadWorks>> loader) {
        // Nothing needs to be done.
    }

    private void getRoadWorksArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON get road works array");
        RoadWorks[] roadWorkses = new RoadWorksRetriever(getContext()).retrieve();
        if (roadWorkses == null || roadWorkses.length == 0) {
            runnerTask.report("OXON get road works array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("OXON get road works array ... PASSED.", COLOUR_PASSED);
        }
    }
}
