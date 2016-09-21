package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficItem;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsLatestTrafficItemQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        latestTrafficItemQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS latest traffic item query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void latestTrafficItemQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS latest traffic item query");
        Cursor cursor = CvsContentHelper.getLatestTrafficItemCursor(runnerTask.getContext());
        TrafficItem[] trafficItems = CvsContentHelper.getLatestTrafficItems(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == trafficItems.length) {
                runnerTask.report("CVS latest traffic item query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CSV latest traffic item query ... FAILED.", COLOUR_FAILED);
    }
}
