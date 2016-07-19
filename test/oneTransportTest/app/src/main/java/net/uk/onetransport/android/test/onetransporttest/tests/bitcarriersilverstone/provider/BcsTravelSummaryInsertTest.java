package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummaryRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class BcsTravelSummaryInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTravelSummaries(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS travel summary insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTravelSummaries(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS travel summary insert");
        Context context = runnerTask.getContext();
        ArrayList<TravelSummary> travelSummaries = new TravelSummaryRetriever().retrieve(context);
        if (travelSummaries == null || travelSummaries.size() == 0) {
            runnerTask.report("BCS travel summary insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.insertTravelSummariesIntoProvider(context, travelSummaries);
        Cursor cursor = BcsContentHelper.getTravelSummaries(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BCS travel summary insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS travel summary insert ... FAILED.", COLOUR_FAILED);
    }
}
