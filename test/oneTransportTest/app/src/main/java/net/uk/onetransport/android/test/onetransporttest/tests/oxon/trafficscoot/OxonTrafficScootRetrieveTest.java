package net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficscoot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.generic.RetrieverResult;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScootRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficScootRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TrafficScoot>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTrafficScootArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON get traffic scoot array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<TrafficScoot>> onCreateLoader(int id, Bundle args) {
        return new TrafficScootRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<TrafficScoot>> loader,
                               RetrieverResult<TrafficScoot> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("OXON traffic scoot array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<TrafficScoot>> loader) {
        // Nothing needs to be done.
    }

    private void getTrafficScootArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON get traffic scoot array");
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(getContext()).retrieve();
        if (trafficScoots == null || trafficScoots.length == 0) {
            runnerTask.report("OXON get traffic scoot array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("OXON get traffic scoot array ... PASSED.", COLOUR_PASSED);
        }
    }
}