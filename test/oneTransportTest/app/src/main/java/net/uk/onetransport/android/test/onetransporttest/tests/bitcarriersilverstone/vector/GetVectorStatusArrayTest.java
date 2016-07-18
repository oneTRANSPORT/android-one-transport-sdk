package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatus;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatusRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatusRetrieverLoader;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class GetVectorStatusArrayTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<VectorStatus>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getVectorStatusArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS get vector status array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<VectorStatus>> onCreateLoader(int id, Bundle args) {
        return new VectorStatusRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<VectorStatus>> loader,
                               RetrieverResult<VectorStatus> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Vector status array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<VectorStatus>> loader) {
        // Nothing needs to be done.
    }

    private void getVectorStatusArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS get vector status array");
        VectorStatusRetriever vectorStatusRetriever = new VectorStatusRetriever();
        ArrayList<VectorStatus> vectorStatuses = vectorStatusRetriever.retrieve(
                runnerTask.getContext());
        if (vectorStatuses == null || vectorStatuses.size() == 0) {
            runnerTask.report("BCS get vector status array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get vector status array ... PASSED.", COLOUR_PASSED);
        }
    }
}
