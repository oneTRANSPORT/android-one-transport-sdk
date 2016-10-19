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
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.bucks.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.bucks.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficSpeedInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficSpeed(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS traffic speed insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficSpeed(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic speed insert");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        if (trafficSpeeds == null || trafficSpeeds.length == 0) {
            runnerTask.report("BUCKS traffic speed insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, trafficSpeeds);
        BucksContentHelper.insertIntoProvider(context, trafficSpeeds);
        TrafficSpeed[] trafficSpeeds1 = BucksContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length == trafficSpeeds1.length) {
            runnerTask.report("BUCKS traffic speed insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS traffic speed insert ... FAILED.", COLOUR_FAILED);
    }
}
