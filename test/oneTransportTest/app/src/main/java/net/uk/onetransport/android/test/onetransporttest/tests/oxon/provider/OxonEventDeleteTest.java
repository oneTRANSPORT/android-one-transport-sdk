package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonEventDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteEvents(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON event delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteEvents(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON event delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_EVENT);
        Event[] events = OxonContentHelper.getEvents(context);
        if (events.length == 0) {
            runnerTask.report("OXON event delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON event delete ... FAILED.", COLOUR_FAILED);
    }
}
