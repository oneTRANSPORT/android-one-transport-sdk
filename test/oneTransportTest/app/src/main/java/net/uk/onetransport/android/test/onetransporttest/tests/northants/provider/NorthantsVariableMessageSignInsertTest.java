/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsVariableMessageSignInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertVariableMessageSign(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS variable message sign insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertVariableMessageSign(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS variable message sign insert");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_VMS);
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        if (variableMessageSigns == null || variableMessageSigns.length == 0) {
            runnerTask.report("NORTHANTS variable message sign insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        NorthantsContentHelper.insertIntoProvider(context, variableMessageSigns);
        NorthantsContentHelper.insertIntoProvider(context, variableMessageSigns);
        VariableMessageSign[] variableMessageSigns1 = NorthantsContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length == variableMessageSigns1.length) {
            runnerTask.report("NORTHANTS variable message sign insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS variable message sign insert ... FAILED.", COLOUR_FAILED);
    }
}
