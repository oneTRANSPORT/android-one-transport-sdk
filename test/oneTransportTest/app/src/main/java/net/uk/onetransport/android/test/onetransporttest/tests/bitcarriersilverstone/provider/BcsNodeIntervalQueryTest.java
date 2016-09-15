package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsNodeIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        nodeQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS node interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void nodeQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS node interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Cursor cursor = BcsContentHelper.getNodeCursor(runnerTask.getContext(), oldest, newest);
        Node[] nodes = BcsContentHelper.getNodes(runnerTask.getContext(), oldest, newest);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount()==nodes.length) {
                runnerTask.report("BCS node interval query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS node interval query ... FAILED.", COLOUR_FAILED);
    }
}
