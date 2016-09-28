package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.events.Event;
import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsEventDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteEvents(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS event delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteEvents(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS event delete");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_EVENT);
        Event[] events = HertsContentHelper.getEvents(context);
        if (events.length == 0) {
            runnerTask.report("HERTS event delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS event delete ... FAILED.", COLOUR_FAILED);
    }
}
