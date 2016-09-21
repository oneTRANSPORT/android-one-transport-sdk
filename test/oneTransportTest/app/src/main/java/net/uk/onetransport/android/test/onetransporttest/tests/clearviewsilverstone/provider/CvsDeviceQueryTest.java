package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsDeviceQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deviceQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS device query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deviceQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS device query");
        Cursor cursor = CvsContentHelper.getDeviceCursor(runnerTask.getContext());
        Device[] devices = CvsContentHelper.getDevices(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == devices.length) {
                runnerTask.report("CVS device query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS device query ... FAILED.", COLOUR_FAILED);
    }
}
