package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksSegmentLocationDeleteTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteSegmentLocations();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS segment location delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteSegmentLocations() throws Exception {
        runnerTask.setCurrentTest("BUCKS segment location delete");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_SEGMENT_LOCATION);
        Cursor cursor = BucksContentHelper.getSegmentLocations(context);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("BUCKS segment location delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS segment location delete ... FAILED.", COLOUR_FAILED);
    }
}
