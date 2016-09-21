package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficItem;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsTrafficItemDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficItems(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS traffic item delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficItems(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS traffic item delete");
        Context context = runnerTask.getContext();
        CvsContentHelper.deleteFromProvider(context, CvsContentHelper.DATA_TYPE_TRAFFIC);
        Cursor cursor = CvsContentHelper.getTrafficItemCursor(context);
        TrafficItem[] trafficItems = CvsContentHelper.getTrafficItems(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 && trafficItems.length == 0) {
                runnerTask.report("CVS traffic item delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS traffic item delete ... FAILED.", COLOUR_FAILED);
    }
}
