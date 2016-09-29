package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonRoadWorksIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadWorksQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON road works interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadWorksQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON road works interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        RoadWorks[] roadWorkses = OxonContentHelper.getRoadWorks(context, oldest, newest);
        RoadWorks[] roadWorkses1 = OxonContentHelper.getRoadWorks(context);
        if (roadWorkses.length != roadWorkses1.length) {
            runnerTask.report("OXON road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        roadWorkses = OxonContentHelper.getRoadWorks(context, oldest, newest);
        if (roadWorkses.length > 0) {
            runnerTask.report("OXON road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("OXON road works interval query ... PASSED.", COLOUR_PASSED);
    }
}
