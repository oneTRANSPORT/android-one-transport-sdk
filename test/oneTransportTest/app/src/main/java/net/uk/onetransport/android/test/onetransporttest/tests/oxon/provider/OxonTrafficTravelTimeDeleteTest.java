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
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonTrafficTravelTimeDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteTrafficTravelTime(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON traffic travel time delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteTrafficTravelTime(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON traffic travel time delete");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context,
                OxonContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        TrafficTravelTime[] trafficTravelTimes = OxonContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length == 0) {
            runnerTask.report("OXON traffic travel time delete ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON traffic travel time delete ... FAILED.", COLOUR_FAILED);
    }
}
