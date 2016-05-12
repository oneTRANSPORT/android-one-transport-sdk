package net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficflow;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowArray;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetTrafficFlowArrayTest
        implements TrafficFlowArrayCallback, OneTransportTest {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getTrafficFlowArray();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS get traffic flow array");
        this.dougalCallback = dougalCallback;
        TrafficFlowArray.getTrafficFlowArrayAsync(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onTrafficFlowArrayReady(int i,
                                        TrafficFlowArray trafficFlowArray) {
        if (i != 1 || trafficFlowArray == null
                || trafficFlowArray.getTrafficFlows() == null
                || trafficFlowArray.getTrafficFlows().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Trafic flow array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onTrafficFlowArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Traffic flow array error"));
    }

    private void getTrafficFlowArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get traffic flow array");
        TrafficFlowArray trafficFlowArray = TrafficFlowArray
                .getTrafficFlowArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (trafficFlowArray.getTrafficFlows() == null
                || trafficFlowArray.getTrafficFlows().length == 0) {
            runnerTask.report("BUCKS get traffic flow array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get traffic flow array ... PASSED.", COLOUR_PASSED);
        }
    }

}
