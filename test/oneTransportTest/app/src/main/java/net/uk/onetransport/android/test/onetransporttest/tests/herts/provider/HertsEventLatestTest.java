package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.events.Event;
import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsEventLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        eventQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS event latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void eventQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS event latest query");
        Context context = runnerTask.getContext();
        Event[] events = HertsContentHelper.getLatestEvents(context);
        if (events.length > 0) {
            runnerTask.report("HERTS event latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS event latest query ... FAILED.", COLOUR_FAILED);
    }
}
