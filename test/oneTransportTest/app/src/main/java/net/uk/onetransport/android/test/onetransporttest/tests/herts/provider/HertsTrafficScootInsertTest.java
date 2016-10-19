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
import net.uk.onetransport.android.county.herts.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.herts.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsTrafficScootInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficScoot(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS traffic scoot insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficScoot(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS traffic scoot insert");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context,
                HertsContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        if (trafficScoots == null || trafficScoots.length == 0) {
            runnerTask.report("HERTS traffic scoot insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        HertsContentHelper.insertIntoProvider(context, trafficScoots);
        HertsContentHelper.insertIntoProvider(context, trafficScoots);
        TrafficScoot[] trafficScoots1 = HertsContentHelper.getTrafficScoots(context);
        if (trafficScoots.length == trafficScoots1.length) {
            runnerTask.report("HERTS traffic scoot insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS traffic scoot insert ... FAILED.", COLOUR_FAILED);
    }
}
