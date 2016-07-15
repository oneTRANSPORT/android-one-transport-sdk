package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsTrafficGroupQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficGroupQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS traffic group query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficGroupQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS traffic group query");
        Cursor cursor = CvsContentHelper.getTraffic(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("CVS traffic group query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS traffic group query ... FAILED.", COLOUR_FAILED);
    }
}
