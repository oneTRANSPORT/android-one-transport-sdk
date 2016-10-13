package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsRoadworksDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteRoadWorks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS road works delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteRoadWorks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS road works delete");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_ROADWORKS);
        Roadworks[] roadWorkses = HertsContentHelper.getRoadworks(context);
        if (roadWorkses.length == 0) {
            runnerTask.report("HERTS road works delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS road works delete ... FAILED.", COLOUR_FAILED);
    }
}
