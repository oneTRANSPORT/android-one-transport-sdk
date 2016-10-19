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
package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficScootIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        trafficScootQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("Bucks traffic scoot interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void trafficScootQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("Bucks traffic scoot interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        TrafficScoot[] trafficScoots = BucksContentHelper.getTrafficScoots(context, oldest, newest);
        TrafficScoot[] trafficScoots1 = BucksContentHelper.getTrafficScoots(context);
        if (trafficScoots.length != trafficScoots1.length) {
            runnerTask.report("Bucks traffic scoot interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        trafficScoots = BucksContentHelper.getTrafficScoots(context, oldest, newest);
        if (trafficScoots.length > 0) {
            runnerTask.report("Bucks traffic scoot interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("Bucks traffic scoot interval query ... PASSED.", COLOUR_PASSED);
    }
}
