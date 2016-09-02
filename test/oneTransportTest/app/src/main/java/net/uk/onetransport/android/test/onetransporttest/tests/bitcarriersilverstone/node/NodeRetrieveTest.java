package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.node;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeRetrieverLoader;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class NodeRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Node>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getNodeArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS get node array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<Node>> onCreateLoader(int id, Bundle args) {
        return new NodeRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<Node>> loader, RetrieverResult<Node> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Node array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<Node>> loader) {
        // Nothing needs to be done.
    }

    private void getNodeArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS get node array");
        NodeRetriever nodeRetriever = new NodeRetriever(runnerTask.getContext());
        ArrayList<Node> nodes = nodeRetriever.retrieve();
        if (nodes == null || nodes.size() == 0) {
            runnerTask.report("BCS get node array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get node array ... PASSED.", COLOUR_PASSED);
        }
    }
}
