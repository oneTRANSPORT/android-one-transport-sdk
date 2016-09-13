package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksCarParkInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertCarParks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS car park insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertCarParks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS car park insert");
        Context context = runnerTask.getContext();
        CarPark[] carParks = new CarParkRetriever(context).retrieve();
        if (carParks == null || carParks.length == 0) {
            runnerTask.report("BUCKS car park insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, carParks);
        Cursor cursor = BucksContentHelper.getCarParks(context);
        if (cursor != null) {
            if (cursor.getCount() == carParks.length) {
                runnerTask.report("BUCKS car park insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS car park insert ... FAILED.", COLOUR_FAILED);
    }
}
