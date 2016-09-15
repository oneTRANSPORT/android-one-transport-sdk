package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsTravelSummaryQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        travelSummaryQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS travel summary query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void travelSummaryQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS travel summary query");
        Cursor cursor = BcsContentHelper.getTravelSummaryCursor(runnerTask.getContext());
        TravelSummary[] travelSummaries = BcsContentHelper.getTravelSummaries(runnerTask.getContext());
        if (cursor != null) {
            if (cursor.getCount() > 0 && cursor.getCount() == travelSummaries.length) {
                runnerTask.report("BCS travel summary query ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS travel summary query ... FAILED.", COLOUR_FAILED);
    }
}
