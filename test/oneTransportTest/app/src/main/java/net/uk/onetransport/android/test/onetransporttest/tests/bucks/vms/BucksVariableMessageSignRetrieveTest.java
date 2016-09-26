package net.uk.onetransport.android.test.onetransporttest.tests.bucks.vms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.generic.RetrieverResult;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignRetrieverLoader;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVariableMessageSignRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<VariableMessageSign>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getVariableMessageSignArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS get VMS array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<VariableMessageSign>> onCreateLoader(int id, Bundle args) {
        return new VariableMessageSignRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<VariableMessageSign>> loader,
                               RetrieverResult<VariableMessageSign> data) {
        if (data.getExceptions().size() > 0 || data.getContent() == null) {
            dougalCallback.getResponse(null, new Throwable("Bucks VMS array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<VariableMessageSign>> loader) {
        // Nothing needs to be done.
    }

    private void getVariableMessageSignArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS get VMS array");
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(getContext())
                .retrieve();
        if (variableMessageSigns == null || variableMessageSigns.length == 0) {
            runnerTask.report("BUCKS get VMS array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get VMS array ... PASSED.", COLOUR_PASSED);
        }
    }
}