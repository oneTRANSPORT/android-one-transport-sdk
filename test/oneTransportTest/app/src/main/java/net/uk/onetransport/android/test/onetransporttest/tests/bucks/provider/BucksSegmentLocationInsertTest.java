package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.SegmentLocationArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksSegmentLocationInsertTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertSegmentLocations();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS segment location insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertSegmentLocations() throws Exception {
        runnerTask.setCurrentTest("BUCKS segment location insert");
        SegmentLocationArray segmentLocationArray = SegmentLocationArray
                .getSegmentLocationArray(runnerTask.getContext());
        if (segmentLocationArray.getSegmentLocations() == null
                || segmentLocationArray.getSegmentLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        BucksContentHelper.insertIntoProvider(context, segmentLocationArray.getSegmentLocations());

        Cursor cursor = BucksContentHelper.getSegmentLocations(context);
        if (cursor == null || cursor.getCount() != segmentLocationArray.getSegmentLocations().length) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            if (cursor != null) {
                cursor.close();
            }
            return;
        }
        cursor.close();

        runnerTask.report("BUCKS segment location insert ... PASSED.", COLOUR_PASSED);
    }
}
