package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocationArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
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
                .getPredefinedVmsLocationArray(runnerTask.getContext());
        if (predefinedVmsLocationArray.getPredefinedVmsLocations() == null
                || predefinedVmsLocationArray.getPredefinedVmsLocations().length == 0) {
            runnerTask.report("BUCKS vms location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        BucksContentHelper.insertIntoProvider(context,
                predefinedVmsLocationArray.getPredefinedVmsLocations());
        Cursor cursor = BucksContentHelper.getVmsLocations(context);
        if (cursor != null) {
            if (cursor.getCount() == predefinedVmsLocationArray.getPredefinedVmsLocations().length) {
                runnerTask.report("BUCKS vms location insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS vms location insert ... FAILED.", COLOUR_FAILED);
    }
}
