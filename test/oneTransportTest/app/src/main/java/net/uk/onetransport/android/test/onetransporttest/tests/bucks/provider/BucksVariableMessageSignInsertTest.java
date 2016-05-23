package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignArray;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksVariableMessageSignInsertTest implements OneTransportTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        insertVariableMessageSigns();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS variable message sign insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVariableMessageSigns() throws Exception {
        runnerTask.setCurrentTest("BUCKS variable message sign insert");
        VariableMessageSignArray variableMessageSignArray = VariableMessageSignArray
                .getVariableMessageSignArray(runnerTask.getContext());
        if (variableMessageSignArray.getVariableMessageSigns() == null
                || variableMessageSignArray.getVariableMessageSigns().length == 0) {
            runnerTask.report("BUCKS variable message sign insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        Context context = runnerTask.getContext();
        BucksContentHelper.insertIntoProvider(context,
                variableMessageSignArray.getVariableMessageSigns());
        Cursor cursor = BucksContentHelper.getVariableMessageSigns(context);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                runnerTask.report("BUCKS variable message sign insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BUCKS variable message sign insert ... FAILED.", COLOUR_FAILED);
    }
}
