package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonVariableMessageSignLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        variableMessageSignQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON variable message sign latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void variableMessageSignQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON variable message sign latest query");
        Context context = runnerTask.getContext();
        VariableMessageSign[] variableMessageSigns = OxonContentHelper
                .getLatestVariableMessageSigns(context);
        if (variableMessageSigns.length > 0) {
            runnerTask.report("OXON variable message sign latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON variable message sign latest query ... FAILED.", COLOUR_FAILED);
    }
}
