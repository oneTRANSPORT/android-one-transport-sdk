package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorksArray;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksRoadWorksInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertRoadWorks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS road works insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertRoadWorks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS road works insert");
        Context context = runnerTask.getContext();
        RoadWorksArray roadWorksArray = RoadWorksArray.getRoadWorksArray(context);
        if (roadWorksArray.getRoadWorks() == null || roadWorksArray.getRoadWorks().length == 0) {
            runnerTask.report("BUCKS road works insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, roadWorksArray.getRoadWorks());
        Cursor cursor = BucksContentHelper.getRoadWorks(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS road works insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS road works insert ... FAILED.", COLOUR_FAILED);
    }
}
