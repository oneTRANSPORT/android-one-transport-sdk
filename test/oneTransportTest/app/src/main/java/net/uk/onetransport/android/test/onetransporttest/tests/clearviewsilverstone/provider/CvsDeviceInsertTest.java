package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceRetriever;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class CvsDeviceInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertDevices(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS device insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertDevices(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS device insert");
        Context context = runnerTask.getContext();
        DeviceRetriever deviceRetriever = new DeviceRetriever(runnerTask.getContext());
        ArrayList<Device> devices = deviceRetriever.retrieve();
        if (devices == null || devices.size() == 0) {
            runnerTask.report("CVS device insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        CvsContentHelper.insertDevicesIntoProvider(context, devices);
        Cursor cursor = CvsContentHelper.getDevices(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("CVS device insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS device insert ... FAILED.", COLOUR_FAILED);
    }
}
