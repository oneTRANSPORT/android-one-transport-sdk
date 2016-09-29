package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonVariableMessageSignInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertVariableMessageSign(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON variable message sign insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVariableMessageSign(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON variable message sign insert");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_VMS);
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        if (variableMessageSigns == null || variableMessageSigns.length == 0) {
            runnerTask.report("OXON variable message sign insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        OxonContentHelper.insertIntoProvider(context, variableMessageSigns);
        OxonContentHelper.insertIntoProvider(context, variableMessageSigns);
        VariableMessageSign[] variableMessageSigns1 = OxonContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length == variableMessageSigns1.length) {
            runnerTask.report("OXON variable message sign insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON variable message sign insert ... FAILED.", COLOUR_FAILED);
    }
}
