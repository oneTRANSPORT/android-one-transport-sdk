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
import net.uk.onetransport.android.county.herts.roadworks.Roadworks;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsRoadworksIntervalQueryTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        roadWorksQuery(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS road works interval query");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void roadWorksQuery(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS road works interval query");
        long oldest = 0L;
        long newest = System.currentTimeMillis() / 1000L;
        Context context = runnerTask.getContext();
        Roadworks[] roadworkses = HertsContentHelper.getRoadworks(context, oldest, newest);
        Roadworks[] roadworkses1 = HertsContentHelper.getRoadworks(context);
        if (roadworkses.length != roadworkses1.length) {
            runnerTask.report("HERTS road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        oldest = newest;
        newest++;
        roadworkses = HertsContentHelper.getRoadworks(context, oldest, newest);
        if (roadworkses.length > 0) {
            runnerTask.report("HERTS road works interval query ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("HERTS road works interval query ... PASSED.", COLOUR_PASSED);
    }
}
