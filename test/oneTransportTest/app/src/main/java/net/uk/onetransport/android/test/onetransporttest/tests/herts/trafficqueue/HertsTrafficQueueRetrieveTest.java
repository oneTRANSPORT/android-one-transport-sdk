package net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficqueue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.generic.RetrieverResult;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueueRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficQueueRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TrafficQueue>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTrafficQueueArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS get traffic queue array");
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
            dougalCallback.getResponse(null, new Throwable("HERTS traffic queue array error"));
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
        runnerTask.setCurrentTest("HERTS get traffic queue array");
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(getContext()).retrieve();
        if (trafficQueues == null || trafficQueues.length == 0) {
            runnerTask.report("HERTS get traffic queue array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("HERTS get traffic queue array ... PASSED.", COLOUR_PASSED);
        }
    }
}
