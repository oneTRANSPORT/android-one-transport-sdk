package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsVariableMessageSignDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteVariableMessageSigns(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS variable message sign delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteVariableMessageSigns(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS variable message sign delete");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_VMS);
        VariableMessageSign[] variableMessageSigns = HertsContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length == 0) {
            runnerTask.report("HERTS variable message sign delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS variable message sign delete ... FAILED.", COLOUR_FAILED);
    }
}