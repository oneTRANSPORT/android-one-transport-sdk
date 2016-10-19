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
package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsVariableMessageSignIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        variableMessageSignQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS variable message sign interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void variableMessageSignQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS variable message sign interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        VariableMessageSign[] variableMessageSigns = HertsContentHelper.getVariableMessageSigns(context,
                oldest, newest);
        VariableMessageSign[] variableMessageSigns1 = HertsContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length != variableMessageSigns1.length) {
            runnerTask.report("HERTS variable message sign interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        variableMessageSigns = HertsContentHelper.getVariableMessageSigns(context, oldest, newest);
        if (variableMessageSigns.length > 0) {
            runnerTask.report("HERTS variable message sign interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("HERTS variable message sign interval query ... PASSED.", COLOUR_PASSED);
    }
}
