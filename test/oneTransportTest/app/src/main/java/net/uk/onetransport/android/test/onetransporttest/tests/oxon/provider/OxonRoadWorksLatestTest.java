package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonRoadworksLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadworksQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON road works latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadworksQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON road works latest query");
        Context context = runnerTask.getContext();
        Roadworks[] roadworkses = OxonContentHelper.getLatestRoadworks(context);
        if (roadworkses.length > 0) {
            runnerTask.report("OXON road works latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON road works latest query ... FAILED.", COLOUR_FAILED);
    }
}
