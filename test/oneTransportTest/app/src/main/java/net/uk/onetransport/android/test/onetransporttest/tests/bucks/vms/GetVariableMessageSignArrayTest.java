package net.uk.onetransport.android.test.onetransporttest.tests.bucks.vms;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignArray;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignArrayCallback;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class GetVariableMessageSignArrayTest
        implements VariableMessageSignArrayCallback, OneTransportTest {

    private RunnerTask runnerTask;
    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        getVariableMessageSignArray();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("BUCKS get variable message sign array");
        this.dougalCallback = dougalCallback;
        VariableMessageSignArray.getVariableMessageSignArrayAsync(AE_ID, BASE_URL_CSE,
                USER_NAME, PASSWORD, this, 1);
    }

    @Override
    public void onVariableMessageSignArrayReady(int i,
                                                VariableMessageSignArray variableMessageSignArray) {
        if (i != 1 || variableMessageSignArray == null
                || variableMessageSignArray.getVariableMessageSigns() == null
                || variableMessageSignArray.getVariableMessageSigns().length == 0) {
            dougalCallback.getResponse(null, new Throwable("Variable message sign array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onVariableMessageSignArrayError(int i, Throwable throwable) {
        dougalCallback.getResponse(null, new Throwable("Variable message sign array error"));
    }

    private void getVariableMessageSignArray() throws Exception {
        runnerTask.setCurrentTest("BUCKS get variable message sign array");
        VariableMessageSignArray variableMessageSignArray = VariableMessageSignArray
                .getVariableMessageSignArray(AE_ID, BASE_URL_CSE, USER_NAME, PASSWORD);
        if (variableMessageSignArray.getVariableMessageSigns() == null
                || variableMessageSignArray.getVariableMessageSigns().length == 0) {
            runnerTask.report("BUCKS get variable message sign array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BUCKS get variable message sign array ... PASSED.", COLOUR_PASSED);
        }
    }

}
