package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVariableMessageSignDeleteTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteVariableMessageSigns();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS variable message sign delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteVariableMessageSigns() throws Exception {
        runnerTask.setCurrentTest("BUCKS variable message sign delete");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_VMS);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(BucksProvider.VARIABLE_MESSAGE_SIGN_URI,
                new String[]{
                        BucksContract.VariableMessageSign._ID
                }, null, null, BucksContract.VariableMessageSign.COLUMN_LOCATION_REFERENCE);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("BUCKS variable message sign delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS variable message sign delete ... FAILED.", COLOUR_FAILED);
    }
}
