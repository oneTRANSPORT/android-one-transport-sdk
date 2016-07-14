package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.node;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.NodeArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetNodeArrayTest extends OneTransportTest implements NodeArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getNodeArray();
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BCS get node array");
        this.dougalCallback = dougalCallback;
        NodeArray.getNodeArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onNodeArrayReady(int i, NodeArray nodeArray) {
        if (i != 1 || nodeArray == null || nodeArray.getNodes() == null
                || nodeArray.getNodes().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Node array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onNodeArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Node array error"));
    }

    private void getNodeArray() throws Exception {
        runnerTask.setCurrentTest("BCS get node array");
        NodeArray nodeArray = NodeArray.getNodeArray(runnerTask.getContext());
        if (nodeArray.getNodes() == null || nodeArray.getNodes().length == 0) {
            runnerTask.report("BCS get node array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get node array ... PASSED.", COLOUR_PASSED);
        }
    }
}
