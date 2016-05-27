package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksRoadWorksDeleteTest extends OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteRoadWorks();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS road works delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteRoadWorks() throws Exception {
        runnerTask.setCurrentTest("BUCKS road works delete");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_ROAD_WORKS);
        Cursor cursor = BucksContentHelper.getRoadWorks(context);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("BUCKS road works delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS road works delete ... FAILED.", COLOUR_FAILED);
    }
}
