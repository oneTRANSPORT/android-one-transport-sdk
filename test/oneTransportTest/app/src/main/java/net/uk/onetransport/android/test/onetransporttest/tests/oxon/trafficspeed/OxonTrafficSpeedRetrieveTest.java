package net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficspeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.generic.RetrieverResult;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeedRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficSpeedRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TrafficSpeed>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTrafficSpeedArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON get traffic speed array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<TrafficSpeed>> onCreateLoader(int id, Bundle args) {
        return new TrafficSpeedRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<TrafficSpeed>> loader,
                               RetrieverResult<TrafficSpeed> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("OXON traffic speed array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<TrafficSpeed>> loader) {
        // Nothing needs to be done.
    }

    private void getTrafficSpeedArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON get traffic speed array");
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(getContext()).retrieve();
        if (trafficSpeeds == null || trafficSpeeds.length == 0) {
            runnerTask.report("OXON get traffic speed array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("OXON get traffic speed array ... PASSED.", COLOUR_PASSED);
        }
    }
}
