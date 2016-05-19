package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksCarParkBoxQueryTest implements OneTransportTest {

    private static final double MIN_LATITUDE = 51.35;
    private static final double MIN_LONGITUDE = -0.82;
    private static final double MAX_LATITUDE = 51.98;
    private static final double MAX_LONGITUDE = -0.73;

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        carParkBoxQuery();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS car park box query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void carParkBoxQuery() throws Exception {
        runnerTask.setCurrentTest("BUCKS car park box query");
        Context context = runnerTask.getContext();
        Cursor cursor = BucksContentHelper.getCarParks(context, MIN_LATITUDE, MIN_LONGITUDE,
                MAX_LATITUDE, MAX_LONGITUDE);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    double latitude = cursor.getDouble(cursor.getColumnIndex(
                            BucksContract.CarPark.COLUMN_LATITUDE));
                    double longitude = cursor.getDouble(cursor.getColumnIndex(
                            BucksContract.CarPark.COLUMN_LONGITUDE));
                    if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE
                            || longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
                        runnerTask.report("BUCKS car park box query ... FAILED.", COLOUR_FAILED);
                        cursor.close();
                        return;
                    }
                    cursor.moveToNext();
                }
                runnerTask.report("BUCKS car park box query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS car park box query ... FAILED.", COLOUR_FAILED);
    }
}
