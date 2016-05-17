package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.locations.PredefinedLinkLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedSectionLocationArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedTrLocationArray;
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
        PredefinedTrLocationArray predefinedTrLocationArray = PredefinedTrLocationArray
                .getPredefinedTrLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedTrLocationArray.getPredefinedTrLocations() == null
                || predefinedTrLocationArray.getPredefinedTrLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        BucksContentHelper.insertIntoProvider(context,
                predefinedTrLocationArray.getPredefinedTrLocations());

        PredefinedLinkLocationArray predefinedLinkLocationArray = PredefinedLinkLocationArray
                .getPredefinedLinkLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedLinkLocationArray.getPredefinedLinkLocations() == null
                || predefinedLinkLocationArray.getPredefinedLinkLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context,
                predefinedLinkLocationArray.getPredefinedLinkLocations());

        PredefinedSectionLocationArray predefinedSectionLocationArray = PredefinedSectionLocationArray
                .getPredefinedSectionLocationArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (predefinedSectionLocationArray.getPredefinedSectionLocations() == null
                || predefinedSectionLocationArray.getPredefinedSectionLocations().length == 0) {
            runnerTask.report("BUCKS segment location insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context,
                predefinedSectionLocationArray.getPredefinedSectionLocations());
        Cursor cursor = BucksContentHelper.getSegmentLocations(context);
        if (cursor == null
                || cursor.getCount() != predefinedLinkLocationArray.getPredefinedLinkLocations().length
                + predefinedSectionLocationArray.getPredefinedSectionLocations().length
                + predefinedTrLocationArray.getPredefinedTrLocations().length) {
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
