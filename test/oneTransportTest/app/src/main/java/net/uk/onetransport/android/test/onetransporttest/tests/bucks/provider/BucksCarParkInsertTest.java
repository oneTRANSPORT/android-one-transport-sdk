package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;
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
        carParkArray.insertIntoProvider(context);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(BucksProvider.CAR_PARK_URI, new String[]{
                BucksContract.CarPark._ID,
                BucksContract.CarPark.CAR_PARK_IDENTITY,
                BucksContract.CarPark.TOTAL_PARKING_CAPACITY,
                BucksContract.CarPark.ALMOST_FULL_DECREASING,
                BucksContract.CarPark.ALMOST_FULL_INCREASING,
                BucksContract.CarPark.FULL_DECREASING,
                BucksContract.CarPark.FULL_INCREASING,
                BucksContract.CarPark.ENTRANCE_FULL,
                BucksContract.CarPark.RADIUS,
                BucksContract.CarPark.LATITUDE,
                BucksContract.CarPark.LONGITUDE
        }, null, null, BucksContract.CarPark.CAR_PARK_IDENTITY);
        if (cursor != null && cursor.getCount() > 0) {
            runnerTask.report("BUCKS car park insert ... PASSED.", COLOUR_PASSED);
        } else {
            runnerTask.report("BUCKS car park insert ... PASSED.", COLOUR_FAILED);
        }
        cursor.close();
        contentResolver.delete(BucksProvider.CAR_PARK_URI, null, null);
    }
}
