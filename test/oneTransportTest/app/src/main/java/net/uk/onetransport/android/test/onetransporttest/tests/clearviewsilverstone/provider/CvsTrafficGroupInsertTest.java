package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroup;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class CvsTrafficGroupInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficGroups(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS traffic group insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficGroups(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS traffic group insert");
        Context context = runnerTask.getContext();
        ArrayList<TrafficGroup> trafficGroups = new TrafficGroupRetriever(context).retrieve();
        if (trafficGroups == null || trafficGroups.size() == 0) {
            runnerTask.report("CVS traffic group insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        CvsContentHelper.insertTrafficGroupsIntoProvider(context, trafficGroups);
        Cursor cursor = CvsContentHelper.getTraffic(context);
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
