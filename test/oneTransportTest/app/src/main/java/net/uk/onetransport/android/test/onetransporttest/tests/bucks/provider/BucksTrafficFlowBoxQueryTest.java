package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficFlowBoxQueryTest implements OneTransportTest {

    private static final double MIN_LATITUDE = 51.35;
    private static final double MIN_LONGITUDE = -0.82;
    private static final double MAX_LATITUDE = 51.98;
    private static final double MAX_LONGITUDE = -0.73;

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        trafficFlowBoxQuery();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS traffic flow box query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficFlowBoxQuery() throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic flow box query");
        Context context = runnerTask.getContext();
        Cursor cursor = BucksContentHelper.getTrafficFlowJoinLocations(context, MIN_LATITUDE, MIN_LONGITUDE,
                MAX_LATITUDE, MAX_LONGITUDE);
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    double to_latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                    double to_longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                    double from_latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                    double from_longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                        runnerTask.report("BUCKS car park box query ... FAILED.", COLOUR_FAILED);
                    if (to_latitude < MIN_LATITUDE || to_latitude > MAX_LATITUDE
                            || to_longitude < MIN_LONGITUDE || to_longitude > MAX_LONGITUDE
                            ||from_latitude < MIN_LATITUDE || from_latitude > MAX_LATITUDE
                            || from_longitude < MIN_LONGITUDE || from_longitude > MAX_LONGITUDE) {
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
