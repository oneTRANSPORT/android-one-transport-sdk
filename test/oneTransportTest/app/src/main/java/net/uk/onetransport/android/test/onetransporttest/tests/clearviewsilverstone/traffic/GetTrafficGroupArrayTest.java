package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.traffic;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetTrafficGroupArrayTest extends OneTransportTest
        implements TrafficGroupArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getTrafficGroupArray();
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("CVS get traffic group array");
        this.dougalCallback = dougalCallback;
        TrafficGroupArray.getTrafficGroupArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onTrafficGroupArrayReady(int i, TrafficGroupArray trafficGroupArray) {
        if (i != 1 || trafficGroupArray == null || trafficGroupArray.getTrafficGroups() == null
                || trafficGroupArray.getTrafficGroups().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Traffic group array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onTrafficGroupArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Traffic group array error"));
    }

    private void getTrafficGroupArray() throws Exception {
        runnerTask.setCurrentTest("CVS get traffic group array");
        TrafficGroupArray trafficGroupArray = TrafficGroupArray.getTrafficGroupArray(
                runnerTask.getContext());
        if (trafficGroupArray.getTrafficGroups() == null
                || trafficGroupArray.getTrafficGroups().length == 0) {
            runnerTask.report("CVS get traffic group array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("CVS get traffic group array ... PASSED.", COLOUR_PASSED);
        }
    }
}
