package net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficqueue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.generic.RetrieverResult;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueueRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficQueueRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TrafficQueue>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTrafficQueueArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS get traffic queue array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<TrafficQueue>> onCreateLoader(int id, Bundle args) {
        return new TrafficQueueRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<TrafficQueue>> loader,
                               RetrieverResult<TrafficQueue> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("Bucks traffic queue array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<TrafficQueue>> loader) {
        // Nothing needs to be done.
    }

    private void getTrafficQueueArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS get traffic queue array");
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(getContext()).retrieve();
        if (trafficQueues == null || trafficQueues.length == 0) {
            runnerTask.report("BUCKS get traffic queue array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get traffic queue array ... PASSED.", COLOUR_PASSED);
        }
    }
}
