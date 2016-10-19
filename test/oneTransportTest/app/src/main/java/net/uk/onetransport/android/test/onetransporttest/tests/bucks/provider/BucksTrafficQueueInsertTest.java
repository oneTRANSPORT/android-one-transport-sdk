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
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksTrafficQueueInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertTrafficQueue(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS traffic queue insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertTrafficQueue(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS traffic queue insert");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context,
                BucksContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        if (trafficQueues == null || trafficQueues.length == 0) {
            runnerTask.report("BUCKS traffic queue insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, trafficQueues);
        BucksContentHelper.insertIntoProvider(context, trafficQueues);
        TrafficQueue[] trafficQueues1 = BucksContentHelper.getTrafficQueues(context);
        if (trafficQueues.length == trafficQueues1.length) {
            runnerTask.report("BUCKS traffic queue insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS traffic queue insert ... FAILED.", COLOUR_FAILED);
    }
}
