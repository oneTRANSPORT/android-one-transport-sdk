package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.travelsummary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummaryRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummaryRetrieverLoader;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class GetTravelSummaryArrayTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<TravelSummary>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getTravelSummaryArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS get travel summary array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<TravelSummary>> onCreateLoader(int id, Bundle args) {
        return new TravelSummaryRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<TravelSummary>> loader,
                               RetrieverResult<TravelSummary> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Travel summary array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<TravelSummary>> loader) {
        // Nothing needs to be done.
    }

    private void getTravelSummaryArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS get travel summary array");
        TravelSummaryRetriever travelSummaryRetriever = new TravelSummaryRetriever(
                runnerTask.getContext());
        ArrayList<TravelSummary> travelSummaries = travelSummaryRetriever.retrieve();
        if (travelSummaries == null || travelSummaries.size() == 0) {
            runnerTask.report("BCS get travel summary array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get travel summary array ... PASSED.", COLOUR_PASSED);
        }
    }

}
