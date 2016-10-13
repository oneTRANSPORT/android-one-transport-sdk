package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksRoadworksLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadWorksQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("Bucks road works latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadWorksQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("Bucks road works latest query");
        Context context = runnerTask.getContext();
        Roadworks[] roadWorkses = BucksContentHelper.getLatestRoadworks(context);
        if (roadWorkses.length > 0) {
            runnerTask.report("Bucks road works latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("Bucks road works latest query ... FAILED.", COLOUR_FAILED);
    }
}
