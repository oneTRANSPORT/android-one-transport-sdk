package net.uk.onetransport.android.test.onetransporttest.tests.herts.roadworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.generic.RetrieverResult;
import net.uk.onetransport.android.county.herts.roadworks.RoadWorks;
import net.uk.onetransport.android.county.herts.roadworks.RoadWorksRetriever;
import net.uk.onetransport.android.county.herts.roadworks.RoadWorksRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsRoadWorksRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<RoadWorks>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getRoadWorksArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS get road works array");
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
            dougalCallback.getResponse(null, new Throwable("HERTS road works array error"));
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
        runnerTask.setCurrentTest("HERTS get road works array");
        RoadWorks[] roadWorkses = new RoadWorksRetriever(getContext()).retrieve();
        if (roadWorkses == null || roadWorkses.length == 0) {
            runnerTask.report("HERTS get road works array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("HERTS get road works array ... PASSED.", COLOUR_PASSED);
        }
    }
}
