package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsVariableMessageSignInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertVariableMessageSign(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS variable message sign insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVariableMessageSign(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS variable message sign insert");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_VMS);
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        if (variableMessageSigns == null || variableMessageSigns.length == 0) {
            runnerTask.report("HERTS variable message sign insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        HertsContentHelper.insertIntoProvider(context, variableMessageSigns);
        HertsContentHelper.insertIntoProvider(context, variableMessageSigns);
        VariableMessageSign[] variableMessageSigns1 = HertsContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length == variableMessageSigns1.length) {
            runnerTask.report("HERTS variable message sign insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS variable message sign insert ... FAILED.", COLOUR_FAILED);
    }
}
