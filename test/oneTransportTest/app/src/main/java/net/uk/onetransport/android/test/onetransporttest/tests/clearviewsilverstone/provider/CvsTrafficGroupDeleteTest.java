package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsTrafficGroupDeleteTest extends OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteTrafficGroups();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("CVS traffic group delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficGroups() throws Exception {
        runnerTask.setCurrentTest("CVS traffic group delete");
        Context context = runnerTask.getContext();
        CvsContentHelper.deleteFromProvider(context, CvsContentHelper.DATA_TYPE_TRAFFIC);
        Cursor cursor = CvsContentHelper.getTraffic(context);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("CVS traffic group delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS traffic group delete ... FAILED.", COLOUR_FAILED);
    }
}
