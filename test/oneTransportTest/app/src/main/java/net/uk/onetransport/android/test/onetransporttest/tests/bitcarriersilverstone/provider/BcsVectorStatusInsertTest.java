package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatus;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorStatusRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class BcsVectorStatusInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertVectorStatuses(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS vector status insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVectorStatuses(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS vector status insert");
        Context context = runnerTask.getContext();
        ArrayList<VectorStatus> vectorStatuses = new VectorStatusRetriever().retrieve(context);
        if (vectorStatuses == null || vectorStatuses.size() == 0) {
            runnerTask.report("BCS vector status insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.insertVectorStatusesIntoProvider(context, vectorStatuses);
        Cursor cursor = BcsContentHelper.getVectorStatuses(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BCS vector status insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS vector status insert ... FAILED.", COLOUR_FAILED);
    }
}
