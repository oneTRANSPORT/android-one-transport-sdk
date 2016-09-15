package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;


import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsConfigVectorDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteConfigVectors(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS config vector delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteConfigVectors(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS config vector delete");
        Context context = runnerTask.getContext();
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_CONFIG_VECTOR);
        Cursor cursor = BcsContentHelper.getConfigVectorCursor(context);
        Vector[] vectors = BcsContentHelper.getConfigVectors(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 && vectors.length == 0) {
                runnerTask.report("BCS config vector delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS config vector delete ... FAILED.", COLOUR_FAILED);
    }
}
