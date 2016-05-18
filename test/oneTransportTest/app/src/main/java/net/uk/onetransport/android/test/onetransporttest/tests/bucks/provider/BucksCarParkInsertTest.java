package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksCarParkInsertTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertCarParks();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS car park insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertCarParks() throws Exception {
        runnerTask.setCurrentTest("BUCKS car park insert");
        CarParkArray carParkArray = CarParkArray.getCarParkArray(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD);
        if (carParkArray.getCarParks() == null || carParkArray.getCarParks().length == 0) {
            runnerTask.report("BUCKS car park insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        BucksContentHelper.insertIntoProvider(context, carParkArray.getCarParks());
        Cursor cursor = BucksContentHelper.getCarParks(context);
        if (cursor != null) {
            if (cursor.getCount() == carParkArray.getCarParks().length) {
                runnerTask.report("BUCKS car park insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS car park insert ... FAILED.", COLOUR_FAILED);
    }
}
