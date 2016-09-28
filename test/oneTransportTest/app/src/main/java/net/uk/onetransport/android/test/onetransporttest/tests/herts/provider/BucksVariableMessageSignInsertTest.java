package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVariableMessageSignInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertVariableMessageSign(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS variable message sign insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVariableMessageSign(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS variable message sign insert");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_VMS);
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        if (variableMessageSigns == null || variableMessageSigns.length == 0) {
            runnerTask.report("BUCKS variable message sign insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, variableMessageSigns);
        BucksContentHelper.insertIntoProvider(context, variableMessageSigns);
        VariableMessageSign[] variableMessageSigns1 = BucksContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length == variableMessageSigns1.length) {
            runnerTask.report("BUCKS variable message sign insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS variable message sign insert ... FAILED.", COLOUR_FAILED);
    }
}
