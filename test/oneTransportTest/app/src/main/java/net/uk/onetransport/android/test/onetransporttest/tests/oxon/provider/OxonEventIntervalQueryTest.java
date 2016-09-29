package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonEventIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        eventQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON event interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void eventQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON event interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        Event[] events = OxonContentHelper.getEvents(context, oldest, newest);
        Event[] events1 = OxonContentHelper.getEvents(context);
        if (events.length != events1.length) {
            runnerTask.report("OXON event interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        events = OxonContentHelper.getEvents(context, oldest, newest);
        if (events.length > 0) {
            runnerTask.report("OXON event interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("OXON event interval query ... PASSED.", COLOUR_PASSED);
    }
}
