package net.uk.onetransport.android.test.onetransporttest.tests.northants.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.events.Event;
import net.uk.onetransport.android.county.northants.events.EventRetriever;
import net.uk.onetransport.android.county.northants.events.EventRetrieverLoader;
import net.uk.onetransport.android.county.northants.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsEventRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Event>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getEventArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS get event array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<Event>> onCreateLoader(int id, Bundle args) {
        return new EventRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<Event>> loader, RetrieverResult<Event> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("NORTHANTS event array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<Event>> loader) {
        // Nothing needs to be done.
    }

    private void getEventArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS get event array");
        Event[] events = new EventRetriever(getContext()).retrieve();
        if (events == null || events.length == 0) {
            runnerTask.report("NORTHANTS get event array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("NORTHANTS get event array ... PASSED.", COLOUR_PASSED);
        }
    }
}
