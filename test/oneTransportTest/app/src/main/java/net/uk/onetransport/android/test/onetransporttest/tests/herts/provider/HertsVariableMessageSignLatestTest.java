package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsVariableMessageSignLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        variableMessageSignQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS variable message sign latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void variableMessageSignQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS variable message sign latest query");
        Context context = runnerTask.getContext();
        VariableMessageSign[] variableMessageSigns = HertsContentHelper
                .getLatestVariableMessageSigns(context);
        if (variableMessageSigns.length > 0) {
            runnerTask.report("HERTS variable message sign latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS variable message sign latest query ... FAILED.", COLOUR_FAILED);
    }
}
