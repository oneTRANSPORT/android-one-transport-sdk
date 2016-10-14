package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.events.Event;
import net.uk.onetransport.android.county.bucks.events.EventRetriever;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksEventInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertEvents(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS event insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertEvents(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS event insert");
        Context context = runnerTask.getContext();
        // This is only needed if new CIs are created during the running of the tests.
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_EVENT);
        Event[] events = new EventRetriever(context).retrieve();
        if (events == null || events.length == 0) {
            runnerTask.report("BUCKS event insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        // Insert the same thing twice to check for a working UNIQUE ON CONFLICT IGNORE index.
        BucksContentHelper.insertIntoProvider(context, events);
        BucksContentHelper.insertIntoProvider(context, events);
        Event[] events1 = BucksContentHelper.getEvents(context);
        if (events.length == events1.length) {
            runnerTask.report("BUCKS event insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS event insert ... FAILED.", COLOUR_FAILED);
    }
}
