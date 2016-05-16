package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowArray;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficFlowInsertTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertTrafficFlow();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS traffic flow insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficFlow() throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic flow insert");
        TrafficFlowArray trafficFlowArray = TrafficFlowArray.getTrafficFlowArray(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD);
        if (trafficFlowArray.getTrafficFlows() == null || trafficFlowArray.getTrafficFlows().length == 0) {
            runnerTask.report("BUCKS traffic flow insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        trafficFlowArray.insertIntoProvider(context);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(BucksProvider.TRAFFIC_FLOW_URI, new String[]{
                BucksContract.TrafficFlow._ID,
                BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE,
                BucksContract.TrafficFlow.COLUMN_VEHICLE_FLOW,
                BucksContract.TrafficFlow.COLUMN_AVERAGE_VEHICLE_SPEED,
                BucksContract.TrafficFlow.COLUMN_TRAVEL_TIME,
                BucksContract.TrafficFlow.COLUMN_FREE_FLOW_SPEED,
                BucksContract.TrafficFlow.COLUMN_FREE_FLOW_TRAVEL_TIME
        }, null, null, BucksContract.TrafficFlow.COLUMN_LOCATION_REFERENCE);
        if (cursor != null && cursor.getCount() > 0) {
            runnerTask.report("BUCKS traffic flow insert ... PASSED.", COLOUR_PASSED);
        } else {
            runnerTask.report("BUCKS traffic flow insert ... FAILED.", COLOUR_FAILED);
        }
        cursor.close();
    }
}
