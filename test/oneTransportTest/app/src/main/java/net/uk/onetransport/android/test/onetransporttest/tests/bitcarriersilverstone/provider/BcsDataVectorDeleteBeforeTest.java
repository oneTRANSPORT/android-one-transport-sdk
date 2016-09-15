package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsDataVectorDeleteBeforeTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteBeforeDataVectors(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS data vector delete before");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteBeforeDataVectors(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS data vector delete before");
        Context context = runnerTask.getContext();
        Cursor cursor = BcsContentHelper.getDataVectorCursor(context);
        Vector[] vectors = BcsContentHelper.getDataVectors(context);
        int creationTime = 0;
        if (cursor != null) {
            if (cursor.getCount() == 0 || vectors.length == 0) {
                runnerTask.report("BCS data vector delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            if (cursor.moveToFirst()) {
                creationTime = cursor.getInt(cursor.getColumnIndex(BcsContract
                        .BitCarrierSilverstoneDataVector.COLUMN_CREATION_TIME));
            }
            cursor.close();
        }

        BcsContentHelper.deleteFromProviderBeforeTime(context,
                BcsContentHelper.DATA_TYPE_DATA_VECTOR, creationTime);
        cursor = BcsContentHelper.getDataVectorCursor(context);
        vectors = BcsContentHelper.getDataVectors(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 || vectors.length == 0) {
                runnerTask.report("BCS data vector delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("BCS data vector delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.deleteFromProviderBeforeTime(context,
                BcsContentHelper.DATA_TYPE_DATA_VECTOR, System.currentTimeMillis() / 1000L);
        cursor = BcsContentHelper.getDataVectorCursor(context);
        vectors = BcsContentHelper.getDataVectors(context);
        if (cursor != null) {
            if (cursor.getCount() > 0 || vectors.length > 0) {
                runnerTask.report("BCS data vector delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("BCS data vector delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("BCS data vector delete before ... PASSED.", COLOUR_PASSED);
    }
}
