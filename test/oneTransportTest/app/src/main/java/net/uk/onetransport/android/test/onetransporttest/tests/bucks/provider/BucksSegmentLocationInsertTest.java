package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedLinkLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedSectionLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedTrLocationArray;
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
        SegmentLocationArray predefinedTrLocationArray = PredefinedTrLocationArray
                .getSegmentLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedTrLocationArray.getSegmentLocations() == null
                || predefinedTrLocationArray.getSegmentLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        BucksContentHelper.insertIntoProvider(context, predefinedTrLocationArray.getSegmentLocations());

        SegmentLocationArray predefinedLinkLocationArray = PredefinedLinkLocationArray
                .getSegmentLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedLinkLocationArray.getSegmentLocations() == null
                || predefinedLinkLocationArray.getSegmentLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context,
                predefinedLinkLocationArray.getSegmentLocations());

        SegmentLocationArray predefinedSectionLocationArray = PredefinedSectionLocationArray
                .getSegmentLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedSectionLocationArray.getSegmentLocations() == null
                || predefinedSectionLocationArray.getSegmentLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context,
                predefinedSectionLocationArray.getSegmentLocations());
        Cursor cursor = BucksContentHelper.getSegmentLocations(context);
        if (cursor == null
                || cursor.getCount() != predefinedLinkLocationArray.getSegmentLocations().length
                + predefinedSectionLocationArray.getSegmentLocations().length
                + predefinedTrLocationArray.getSegmentLocations().length) {
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
