package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.events.EventRetriever;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonEventInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertEvents(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON event insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertEvents(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON event insert");
        Context context = runnerTask.getContext();
        // This is only needed if new CIs are created during the running of the tests.
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_EVENT);
        Event[] events = new EventRetriever(context).retrieve();
        if (events == null || events.length == 0) {
            runnerTask.report("OXON event insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        // Insert the same thing twice to check for a working UNIQUE ON CONFLICT IGNORE index.
        OxonContentHelper.insertIntoProvider(context, events);
        OxonContentHelper.insertIntoProvider(context, events);
        Event[] events1 = OxonContentHelper.getEvents(context);
        if (events.length == events1.length) {
            runnerTask.report("OXON event insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON event insert ... FAILED.", COLOUR_FAILED);
    }
}
