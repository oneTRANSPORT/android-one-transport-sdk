package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsTravelSummaryDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTravelSummaries(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS travel summary delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTravelSummaries(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS travel summary delete");
        Context context = runnerTask.getContext();
        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_TRAVEL_SUMMARY);
        Cursor cursor = BcsContentHelper.getTravelSummaryCursor(context);
        TravelSummary[] travelSummaries = BcsContentHelper.getTravelSummaries(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 && travelSummaries.length == 0) {
                runnerTask.report("BCS travel summary delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS travel summary delete ... FAILED.", COLOUR_FAILED);
    }
}
