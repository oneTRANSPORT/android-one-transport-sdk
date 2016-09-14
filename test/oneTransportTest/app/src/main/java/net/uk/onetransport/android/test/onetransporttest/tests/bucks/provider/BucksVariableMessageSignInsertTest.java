package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

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
        insertVariableMessageSigns(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS VMS insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVariableMessageSigns(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS VMS insert");
        Context context = runnerTask.getContext();
        VariableMessageSign[] variableMessageSigns=new VariableMessageSignRetriever(context)
                .retrieve();
        if (variableMessageSigns == null || variableMessageSigns.length == 0) {
            runnerTask.report("BUCKS VMS insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, variableMessageSigns);
        Cursor cursor = BucksContentHelper.getVariableMessageSigns(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS VMS insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS VMS insert ... FAILED.", COLOUR_FAILED);
    }
}
