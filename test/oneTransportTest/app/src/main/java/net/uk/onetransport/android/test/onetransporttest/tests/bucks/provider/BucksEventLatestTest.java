package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.events.Event;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksEventLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        eventQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("Bucks event latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void eventQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("Bucks event latest query");
        Context context = runnerTask.getContext();
        Event[] events = BucksContentHelper.getLatestEvents(context);
        if (events.length > 0) {
            runnerTask.report("Bucks event latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("Bucks event latest query ... FAILED.", COLOUR_FAILED);
    }
}
