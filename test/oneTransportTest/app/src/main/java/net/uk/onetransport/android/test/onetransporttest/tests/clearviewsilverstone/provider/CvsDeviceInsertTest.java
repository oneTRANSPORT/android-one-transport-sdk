package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorksArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsDeviceInsertTest extends OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertDevices();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("CVS device insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertDevices() throws Exception {
        runnerTask.setCurrentTest("CVS device insert");
        Context context = runnerTask.getContext();
        DeviceArray deviceArray = DeviceArray.getDeviceArray(context);
        if (deviceArray.getDevices() == null || deviceArray.getDevices().length == 0) {
            runnerTask.report("CVS device insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        CvsContentHelper.insertIntoProvider(context, deviceArray.getDevices());
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
