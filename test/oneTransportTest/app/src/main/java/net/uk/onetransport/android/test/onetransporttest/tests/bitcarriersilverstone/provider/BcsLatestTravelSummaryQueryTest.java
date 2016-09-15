package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsLatestTravelSummaryQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        latestTravelSummaryQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS latest travel summary query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void latestTravelSummaryQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS latest travel summary query");
        Cursor cursor = BcsContentHelper.getLatestTravelSummaryCursor(runnerTask.getContext());
        TravelSummary[] travelSummaries = BcsContentHelper.getLatestTravelSummaries(
                runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == travelSummaries.length) {
                runnerTask.report("BCS latest travel summary query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS latest travel summary query ... FAILED.", COLOUR_FAILED);
    }
}
