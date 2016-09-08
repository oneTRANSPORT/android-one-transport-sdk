package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.VectorRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.VectorRetrieverLoader;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class BcsConfigVectorRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Vector>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getConfigVectorArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS get config vector array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<Vector>> onCreateLoader(int id, Bundle args) {
        return new VectorRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<Vector>> loader, RetrieverResult<Vector> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Config vector array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<Vector>> loader) {
        // Nothing needs to be done.
    }

    private void getConfigVectorArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS get config vector array");
        VectorRetriever vectorRetriever = new VectorRetriever(runnerTask.getContext());
        ArrayList<Vector> configVectors = vectorRetriever.retrieve();
        if (configVectors == null || configVectors.size() == 0) {
            runnerTask.report("BCS get config vector array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get config vector array ... PASSED.", COLOUR_PASSED);
        }
    }
}
