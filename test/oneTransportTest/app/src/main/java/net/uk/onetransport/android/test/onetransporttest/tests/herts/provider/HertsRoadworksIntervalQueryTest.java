package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsRoadworksIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadWorksQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS road works interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadWorksQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS road works interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        Roadworks[] roadworkses = HertsContentHelper.getRoadworks(context, oldest, newest);
        Roadworks[] roadworkses1 = HertsContentHelper.getRoadworks(context);
        if (roadworkses.length != roadworkses1.length) {
            runnerTask.report("HERTS road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        roadworkses = HertsContentHelper.getRoadworks(context, oldest, newest);
        if (roadworkses.length > 0) {
            runnerTask.report("HERTS road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("HERTS road works interval query ... PASSED.", COLOUR_PASSED);
    }
}
