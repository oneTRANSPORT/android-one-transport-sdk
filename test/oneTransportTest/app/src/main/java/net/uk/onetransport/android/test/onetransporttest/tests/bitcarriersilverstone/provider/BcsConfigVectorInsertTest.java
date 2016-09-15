package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.VectorRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;


public class BcsConfigVectorInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertConfigVectors(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS config vector insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertConfigVectors(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS config vector insert");
        Context context = runnerTask.getContext();
        ArrayList<Vector> vectors = new VectorRetriever(context).retrieve();
        if (vectors == null || vectors.size() == 0) {
            runnerTask.report("BCS config vector insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.insertConfigVectorsIntoProvider(context, vectors);
        Cursor cursor = BcsContentHelper.getConfigVectorCursor(context);
        Vector[] vectors1 = BcsContentHelper.getConfigVectors(context);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == vectors1.length) {
                runnerTask.report("BCS config vector insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS config vector insert ... FAILED.", COLOUR_FAILED);
    }
}
