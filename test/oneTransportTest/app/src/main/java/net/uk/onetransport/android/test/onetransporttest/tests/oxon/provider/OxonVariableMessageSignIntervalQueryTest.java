package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonVariableMessageSignIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        variableMessageSignQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON variable message sign interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void variableMessageSignQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON variable message sign interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        VariableMessageSign[] variableMessageSigns = OxonContentHelper.getVariableMessageSigns(context,
                oldest, newest);
        VariableMessageSign[] variableMessageSigns1 = OxonContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length != variableMessageSigns1.length) {
            runnerTask.report("OXON variable message sign interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        variableMessageSigns = OxonContentHelper.getVariableMessageSigns(context, oldest, newest);
        if (variableMessageSigns.length > 0) {
            runnerTask.report("OXON variable message sign interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("OXON variable message sign interval query ... PASSED.", COLOUR_PASSED);
    }
}
