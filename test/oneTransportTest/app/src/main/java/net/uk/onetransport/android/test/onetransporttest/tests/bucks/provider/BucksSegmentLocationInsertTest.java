package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedLinkLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedSectionLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedTrLocationArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;
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
        PredefinedTrLocationArray predefinedTrLocationArray = PredefinedTrLocationArray
                .getPredefinedTrLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedTrLocationArray.getPredefinedTrLocations() == null
                || predefinedTrLocationArray.getPredefinedTrLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        predefinedTrLocationArray.insertIntoProvider(context);
        ContentResolver contentResolver = context.getContentResolver();

        PredefinedLinkLocationArray predefinedLinkLocationArray = PredefinedLinkLocationArray
                .getPredefinedLinkLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedLinkLocationArray.getPredefinedLinkLocations() == null
                || predefinedLinkLocationArray.getPredefinedLinkLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        predefinedLinkLocationArray.insertIntoProvider(context);

        PredefinedSectionLocationArray predefinedSectionLocationArray = PredefinedSectionLocationArray
                .getPredefinedSectionLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedSectionLocationArray.getPredefinedSectionLocations() == null
                || predefinedSectionLocationArray.getPredefinedSectionLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        predefinedSectionLocationArray.insertIntoProvider(context);

        Cursor cursor = contentResolver.query(BucksProvider.SEGMENT_LOCATION_URI, new String[]{
                BucksContract.SegmentLocation._ID,
                BucksContract.SegmentLocation.COLUMN_LOCATION_ID,
                BucksContract.SegmentLocation.COLUMN_TO_LATITUDE,
                BucksContract.SegmentLocation.COLUMN_TO_LONGITUDE,
                BucksContract.SegmentLocation.COLUMN_FROM_LATITUDE,
                BucksContract.SegmentLocation.COLUMN_FROM_LONGITUDE,
                BucksContract.SegmentLocation.COLUMN_TO_DESCRIPTOR,
                BucksContract.SegmentLocation.COLUMN_FROM_DESCRIPTOR,
                BucksContract.SegmentLocation.COLUMN_TPEG_DIRECTION
        }, null, null, BucksContract.SegmentLocation.COLUMN_LOCATION_ID);
        if (cursor == null || cursor.getCount() == 0) {
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
