package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.device;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetDeviceArrayTest extends OneTransportTest implements DeviceArrayCallback {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getDeviceArray();
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("CVS get device array");
        this.dougalCallback = dougalCallback;
        DeviceArray.getDeviceArrayAsync(runnerTask.getContext(), this, 1);
    }

    @Override
    public void onDeviceArrayReady(int i, DeviceArray deviceArray) {
        if (i != 1 || deviceArray == null || deviceArray.getDevices() == null
                || deviceArray.getDevices().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Device array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onDeviceArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Device array error"));
    }

    private void getDeviceArray() throws Exception {
        runnerTask.setCurrentTest("CVS get device array");
        DeviceArray deviceArray = DeviceArray.getDeviceArray(runnerTask.getContext());
        if (deviceArray.getDevices() == null || deviceArray.getDevices().length == 0) {
            runnerTask.report("CVS get device array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("CVS get device array ... PASSED.", COLOUR_PASSED);
        }
    }
}
