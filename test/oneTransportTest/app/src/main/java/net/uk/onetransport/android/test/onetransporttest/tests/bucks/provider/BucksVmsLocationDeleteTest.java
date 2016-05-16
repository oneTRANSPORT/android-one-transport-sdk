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

public class BucksVmsLocationDeleteTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteVmsLocations();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS vms location delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteVmsLocations() throws Exception {
        runnerTask.setCurrentTest("BUCKS vms location delete");
        Context context = runnerTask.getContext();
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.delete(BucksProvider.VMS_LOCATION_URI, null, null);
        Cursor cursor = contentResolver.query(BucksProvider.VMS_LOCATION_URI, new String[]{
                BucksContract.VmsLocation._ID
        }, null, null, BucksContract.VmsLocation.COLUMN_NAME);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("BUCKS vms location delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS vms location delete ... FAILED.", COLOUR_FAILED);
    }
}
