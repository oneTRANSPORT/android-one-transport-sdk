package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonRoadworksDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteRoadworks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON road works delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteRoadworks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON road works delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_ROADWORKS);
        Roadworks[] roadworkses = OxonContentHelper.getRoadworks(context);
        if (roadworkses.length == 0) {
            runnerTask.report("OXON road works delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON road works delete ... FAILED.", COLOUR_FAILED);
    }
}
