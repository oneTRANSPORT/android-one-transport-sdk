package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsDataVectorDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteDataVectors(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS data vector delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteDataVectors(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS data vector delete");
        Context context = runnerTask.getContext();
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_DATA_VECTOR);
        Cursor cursor = BcsContentHelper.getDataVectors(context);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("BCS data vector delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS data vector delete ... FAILED.", COLOUR_FAILED);
    }
}
