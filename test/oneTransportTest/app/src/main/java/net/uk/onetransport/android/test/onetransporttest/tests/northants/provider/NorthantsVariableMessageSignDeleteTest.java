package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsVariableMessageSignDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteVariableMessageSigns(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS variable message sign delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteVariableMessageSigns(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS variable message sign delete");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_VMS);
        VariableMessageSign[] variableMessageSigns = NorthantsContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length == 0) {
            runnerTask.report("NORTHANTS variable message sign delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS variable message sign delete ... FAILED.", COLOUR_FAILED);
    }
}
