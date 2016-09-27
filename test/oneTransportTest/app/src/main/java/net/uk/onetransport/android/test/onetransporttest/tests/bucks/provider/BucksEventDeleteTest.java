package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.events.Event;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksEventDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteEvents(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS event delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteEvents(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS event delete");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_EVENT);
        Event[] events = BucksContentHelper.getEvents(context);
        if (events.length == 0) {
            runnerTask.report("BUCKS event delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS event delete ... FAILED.", COLOUR_FAILED);
    }
}
