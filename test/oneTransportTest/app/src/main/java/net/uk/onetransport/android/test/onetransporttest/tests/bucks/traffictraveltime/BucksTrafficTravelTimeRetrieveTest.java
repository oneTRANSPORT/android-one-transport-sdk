package net.uk.onetransport.android.test.onetransporttest.tests.bucks.traffictraveltime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.generic.RetrieverResult;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTimeRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficTravelTimeRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TrafficTravelTime>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTrafficTravelTimeArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS get traffic travel time array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<TrafficTravelTime>> onCreateLoader(int id, Bundle args) {
        return new TrafficTravelTimeRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<TrafficTravelTime>> loader,
                               RetrieverResult<TrafficTravelTime> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("Bucks traffic travel time array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<TrafficTravelTime>> loader) {
        // Nothing needs to be done.
    }

    private void getTrafficTravelTimeArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS get traffic travel time array");
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(getContext()).retrieve();
        if (trafficTravelTimes == null || trafficTravelTimes.length == 0) {
            runnerTask.report("BUCKS get traffic travel time array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get traffic travel time array ... PASSED.", COLOUR_PASSED);
        }
    }
}
