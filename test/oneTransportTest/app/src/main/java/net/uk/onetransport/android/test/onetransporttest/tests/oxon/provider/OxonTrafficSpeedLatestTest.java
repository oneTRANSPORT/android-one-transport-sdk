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
package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficSpeedLatestTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficSpeedQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic speed latest query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficSpeedQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic speed latest query");
        Context context = runnerTask.getContext();
        TrafficSpeed[] trafficSpeeds = OxonContentHelper.getLatestTrafficSpeeds(context);
        if (trafficSpeeds.length > 0) {
            runnerTask.report("OXON traffic speed latest query ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic speed latest query ... FAILED.", COLOUR_FAILED);
    }
}
