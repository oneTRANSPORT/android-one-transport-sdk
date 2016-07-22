package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsHistorySensorQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        historySensorQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS history sensor query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void historySensorQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS history sensor query");
        Cursor cursor = CvsContentHelper.getHistoryBySensor(runnerTask.getContext(), 1748);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("CVS history sensor query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS history sensor query ... FAILED.", COLOUR_FAILED);
    }
}
