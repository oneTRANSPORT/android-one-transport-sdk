package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;


public class BcsVectorInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertVectors(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS vector insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVectors(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS vector insert");
        Context context = runnerTask.getContext();
        ArrayList<Vector> vectors = new VectorRetriever(context).retrieve();
        if (vectors == null || vectors.size() == 0) {
            runnerTask.report("BCS vector insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.insertVectorsIntoProvider(context, vectors);
        Cursor cursor = BcsContentHelper.getVectors(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BCS vector insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS vector insert ... FAILED.", COLOUR_FAILED);
    }
}
