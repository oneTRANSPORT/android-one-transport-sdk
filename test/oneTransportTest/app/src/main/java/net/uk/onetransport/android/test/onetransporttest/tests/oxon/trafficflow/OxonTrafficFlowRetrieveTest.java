package net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficflow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.generic.RetrieverResult;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlowRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficFlowRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TrafficFlow>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTrafficFlowArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON get traffic flow array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<TrafficFlow>> onCreateLoader(int id, Bundle args) {
        return new TrafficFlowRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<TrafficFlow>> loader,
                               RetrieverResult<TrafficFlow> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("OXON traffic flow array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<TrafficFlow>> loader) {
        // Nothing needs to be done.
    }

    private void getTrafficFlowArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON get traffic flow array");
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(getContext()).retrieve();
        if (trafficFlows == null || trafficFlows.length == 0) {
            runnerTask.report("OXON get traffic flow array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("OXON get traffic flow array ... PASSED.", COLOUR_PASSED);
        }
    }
}
