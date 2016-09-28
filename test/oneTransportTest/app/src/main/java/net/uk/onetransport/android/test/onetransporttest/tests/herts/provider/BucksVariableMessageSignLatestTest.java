package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVariableMessageSignLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        variableMessageSignQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("Bucks variable message sign latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void variableMessageSignQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("Bucks variable message sign latest query");
        Context context = runnerTask.getContext();
        VariableMessageSign[] variableMessageSigns = BucksContentHelper
                .getLatestVariableMessageSigns(context);
        if (variableMessageSigns.length > 0) {
            runnerTask.report("Bucks variable message sign latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("Bucks variable message sign latest query ... FAILED.", COLOUR_FAILED);
    }
}
