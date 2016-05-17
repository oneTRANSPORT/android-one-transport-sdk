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

public class BucksCarParkDeleteTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteCarParks();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS car park delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteCarParks() throws Exception {
        runnerTask.setCurrentTest("BUCKS car park delete");
        Context context = runnerTask.getContext();
        CarParkArray.deleteFromProvider(context);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(BucksProvider.CAR_PARK_URI, new String[]{
                BucksContract.CarPark._ID
        }, null, null, BucksContract.CarPark.COLUMN_CAR_PARK_IDENTITY);
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                runnerTask.report("BUCKS car park delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS car park delete ... FAILED.", COLOUR_FAILED);
    }
}
