package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonVariableMessageSignDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteVariableMessageSigns(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON variable message sign delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteVariableMessageSigns(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON variable message sign delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_VMS);
        VariableMessageSign[] variableMessageSigns = OxonContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length == 0) {
            runnerTask.report("OXON variable message sign delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON variable message sign delete ... FAILED.", COLOUR_FAILED);
    }
}
