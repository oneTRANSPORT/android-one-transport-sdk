package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonEventLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        eventQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON event latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void eventQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON event latest query");
        Context context = runnerTask.getContext();
        Event[] events = OxonContentHelper.getLatestEvents(context);
        if (events.length > 0) {
            runnerTask.report("OXON event latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON event latest query ... FAILED.", COLOUR_FAILED);
    }
}
