package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksRoadWorksBoxQueryTest extends OneTransportTest {

    private static final double MIN_LATITUDE = 51.39322;
    private static final double MIN_LONGITUDE = -2.59358168;
    private static final double MAX_LATITUDE = 53.580616;
    private static final double MAX_LONGITUDE = 0.267483;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadWorksBoxQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS road works box query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadWorksBoxQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS road works box query");
        Context context = runnerTask.getContext();
        Cursor cursor = BucksContentHelper.getRoadWorks(context, MIN_LATITUDE,
                MIN_LONGITUDE, MAX_LATITUDE, MAX_LONGITUDE);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    double latitude = cursor.getDouble(cursor.getColumnIndex(
                            BucksContract.RoadWorks.COLUMN_LATITUDE));
                    double longitude = cursor.getDouble(cursor.getColumnIndex(
                            BucksContract.RoadWorks.COLUMN_LONGITUDE));
                    if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE
                            || longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
                        runnerTask.report("BUCKS road works box query ... FAILED.", COLOUR_FAILED);
                        cursor.close();
                        return;
                    }
                    cursor.moveToNext();
                }
                runnerTask.report("BUCKS road works box query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS road works box query ... FAILED.", COLOUR_FAILED);
    }
}
