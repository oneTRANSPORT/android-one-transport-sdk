package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupArray;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsTrafficGroupInsertTest extends OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertTrafficGroups();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("CVS traffic group insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficGroups() throws Exception {
        runnerTask.setCurrentTest("CVS traffic group insert");
        Context context = runnerTask.getContext();
        TrafficGroupArray trafficGroupArray = TrafficGroupArray.getTrafficGroupArray(context);
        if (trafficGroupArray.getTrafficGroups() == null
                || trafficGroupArray.getTrafficGroups().length == 0) {
            runnerTask.report("CVS traffic group insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        CvsContentHelper.insertIntoProvider(context, trafficGroupArray.getTrafficGroups());
        Cursor cursor = CvsContentHelper.getDevices(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("CVS traffic group insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS traffic group insert ... FAILED.", COLOUR_FAILED);
    }
}
