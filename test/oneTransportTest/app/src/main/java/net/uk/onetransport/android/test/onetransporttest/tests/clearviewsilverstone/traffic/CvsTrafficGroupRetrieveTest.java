package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.traffic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroup;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupRetriever;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class CvsTrafficGroupRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TrafficGroup>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTrafficGroupArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS get traffic group array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<TrafficGroup>> onCreateLoader(int id, Bundle args) {
        return new TrafficGroupRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<TrafficGroup>> loader,
                               RetrieverResult<TrafficGroup> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Traffic group array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<TrafficGroup>> loader) {
        // Nothing needs to be done.
    }

    private void getTrafficGroupArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS get traffic group array");
        TrafficGroupRetriever trafficGroupRetriever = new TrafficGroupRetriever(runnerTask.getContext());
        ArrayList<TrafficGroup> trafficGroups = trafficGroupRetriever.retrieve();
        if (trafficGroups == null || trafficGroups.size() == 0) {
            runnerTask.report("CVS get traffic group array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("CVS get traffic group array ... PASSED.", COLOUR_PASSED);
        }
    }
}
