package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksRoadworksIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadWorksQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("Bucks road works interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadWorksQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("Bucks road works interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        Roadworks[] roadWorkses = BucksContentHelper.getRoadworks(context, oldest, newest);
        Roadworks[] roadWorkses1 = BucksContentHelper.getRoadworks(context);
        if (roadWorkses.length != roadWorkses1.length) {
            runnerTask.report("Bucks road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        roadWorkses = BucksContentHelper.getRoadworks(context, oldest, newest);
        if (roadWorkses.length > 0) {
            runnerTask.report("Bucks road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("Bucks road works interval query ... PASSED.", COLOUR_PASSED);
    }
}
