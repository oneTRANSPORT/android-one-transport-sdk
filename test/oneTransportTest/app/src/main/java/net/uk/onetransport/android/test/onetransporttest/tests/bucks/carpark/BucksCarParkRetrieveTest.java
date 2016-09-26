package net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.bucks.carparks.CarParkRetrieverLoader;
import net.uk.onetransport.android.county.bucks.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksCarParkRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<CarPark>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getCarParkArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS get car park array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<CarPark>> onCreateLoader(int id, Bundle args) {
        return new CarParkRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<CarPark>> loader, RetrieverResult<CarPark> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("Bucks car park array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<CarPark>> loader) {
        // Nothing needs to be done.
    }

    private void getCarParkArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS get car park array");
        CarPark[] carParks = new CarParkRetriever(getContext()).retrieve();
        if (carParks == null || carParks.length == 0) {
            runnerTask.report("BUCKS get car park array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get car park array ... PASSED.", COLOUR_PASSED);
        }
    }
}