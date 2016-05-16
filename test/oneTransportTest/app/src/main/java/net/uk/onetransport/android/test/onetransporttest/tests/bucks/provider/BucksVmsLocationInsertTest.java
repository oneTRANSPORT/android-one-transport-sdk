package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocationArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVmsLocationInsertTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertVmsLocations();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS vms location insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVmsLocations() throws Exception {
        runnerTask.setCurrentTest("BUCKS vms location insert");
        PredefinedVmsLocationArray predefinedVmsLocationArray = PredefinedVmsLocationArray
                .getPredefinedVmsLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedVmsLocationArray.getPredefinedVmsLocations() == null
                || predefinedVmsLocationArray.getPredefinedVmsLocations().length == 0) {
            runnerTask.report("BUCKS vms location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        predefinedVmsLocationArray.insertIntoProvider(context);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(BucksProvider.VMS_LOCATION_URI, new String[]{
                BucksContract.VmsLocation._ID,
                BucksContract.VmsLocation.COLUMN_NAME,
                BucksContract.VmsLocation.COLUMN_LOCATION_ID,
                BucksContract.VmsLocation.COLUMN_LATITUDE,
                BucksContract.VmsLocation.COLUMN_LONGITUDE,
                BucksContract.VmsLocation.COLUMN_DESCRIPTOR,
                BucksContract.VmsLocation.COLUMN_TPEG_DIRECTION
        }, null, null, BucksContract.VmsLocation.COLUMN_NAME);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS vms location insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS vms location insert ... FAILED.", COLOUR_FAILED);
    }
}
