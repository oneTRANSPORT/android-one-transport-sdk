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
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlow;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsTrafficFlowIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficFlowQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS traffic flow interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficFlowQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS traffic flow interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficFlow[] trafficFlows = NorthantsContentHelper.getTrafficFlows(context, oldest, newest);
        TrafficFlow[] trafficFlows1 = NorthantsContentHelper.getTrafficFlows(context);
        if (trafficFlows.length != trafficFlows1.length) {
            runnerTask.report("NORTHANTS traffic flow interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficFlows = NorthantsContentHelper.getTrafficFlows(context, oldest, newest);
        if (trafficFlows.length > 0) {
            runnerTask.report("NORTHANTS traffic flow interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("NORTHANTS traffic flow interval query ... PASSED.", COLOUR_PASSED);
    }
}
