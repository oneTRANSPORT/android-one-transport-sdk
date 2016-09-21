package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsDeviceDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteDevices(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS device delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteDevices(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS device delete");
        Context context = runnerTask.getContext();
        CvsContentHelper.deleteFromProvider(context, CvsContentHelper.DATA_TYPE_DEVICE);
        Cursor cursor = CvsContentHelper.getDeviceCursor(context);
        Device[] devices = CvsContentHelper.getDevices(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 && devices.length == 0) {
                runnerTask.report("CVS device delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS device delete ... FAILED.", COLOUR_FAILED);
    }
}
