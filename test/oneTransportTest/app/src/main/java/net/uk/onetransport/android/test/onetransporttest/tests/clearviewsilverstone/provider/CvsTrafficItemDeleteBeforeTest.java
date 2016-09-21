package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficItem;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsTrafficItemDeleteBeforeTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteBeforeTrafficItems(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS traffic item delete before");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteBeforeTrafficItems(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS traffic item delete before");
        Context context = runnerTask.getContext();
        Cursor cursor = CvsContentHelper.getTrafficItemCursor(context);
        TrafficItem[] trafficItems = CvsContentHelper.getTrafficItems(context);
        int creationTime = 0;
        if (cursor != null) {
            if (cursor.getCount() == 0 || trafficItems.length == 0) {
                runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            if (cursor.moveToFirst()) {
                creationTime = cursor.getInt(cursor.getColumnIndex(CvsContract
                        .ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME));
            }
            cursor.close();
        }

        CvsContentHelper.deleteFromProviderBeforeTime(context,
                CvsContentHelper.DATA_TYPE_TRAFFIC, creationTime);
        cursor = CvsContentHelper.getTrafficItemCursor(context);
        trafficItems = CvsContentHelper.getTrafficItems(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 || trafficItems.length == 0) {
                runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        CvsContentHelper.deleteFromProviderBeforeTime(context,
                CvsContentHelper.DATA_TYPE_TRAFFIC, System.currentTimeMillis() / 1000L);
        cursor = CvsContentHelper.getTrafficItemCursor(context);
        trafficItems = CvsContentHelper.getTrafficItems(context);
        if (cursor != null) {
            if (cursor.getCount() > 0 || trafficItems.length > 0) {
                runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("CVS traffic item delete before ... PASSED.", COLOUR_PASSED);
    }
}
