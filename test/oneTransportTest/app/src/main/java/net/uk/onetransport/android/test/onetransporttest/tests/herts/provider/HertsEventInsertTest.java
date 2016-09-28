package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.events.Event;
import net.uk.onetransport.android.county.herts.events.EventRetriever;
import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsEventInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertEvents(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS event insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertEvents(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS event insert");
        Context context = runnerTask.getContext();
        // This is only needed if new CIs are created during the running of the tests.
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_EVENT);
        Event[] events = new EventRetriever(context).retrieve();
        if (events == null || events.length == 0) {
            runnerTask.report("HERTS event insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        // Insert the same thing twice to check for a working UNIQUE ON CONFLICT IGNORE index.
        HertsContentHelper.insertIntoProvider(context, events);
        HertsContentHelper.insertIntoProvider(context, events);
        Event[] events1 = HertsContentHelper.getEvents(context);
        if (events.length == events1.length) {
            runnerTask.report("HERTS event insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS event insert ... FAILED.", COLOUR_FAILED);
    }
}
