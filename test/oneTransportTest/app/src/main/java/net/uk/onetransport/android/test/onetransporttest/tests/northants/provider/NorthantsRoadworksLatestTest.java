package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsRoadworksLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadworksQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS road works latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadworksQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS road works latest query");
        Context context = runnerTask.getContext();
        Roadworks[] roadworkses = NorthantsContentHelper.getLatestRoadworks(context);
        if (roadworkses.length > 0) {
            runnerTask.report("NORTHANTS road works latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS road works latest query ... FAILED.", COLOUR_FAILED);
    }
}
