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

import net.uk.onetransport.android.county.northants.events.Event;
import net.uk.onetransport.android.county.northants.events.EventRetriever;
import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsEventInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertEvents(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS event insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertEvents(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS event insert");
        Context context = runnerTask.getContext();
        // This is only needed if new CIs are created during the running of the tests.
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_EVENT);
        Event[] events = new EventRetriever(context).retrieve();
        if (events == null || events.length == 0) {
            runnerTask.report("NORTHANTS event insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        // Insert the same thing twice to check for a working UNIQUE ON CONFLICT IGNORE index.
        NorthantsContentHelper.insertIntoProvider(context, events);
        NorthantsContentHelper.insertIntoProvider(context, events);
        Event[] events1 = NorthantsContentHelper.getEvents(context);
        if (events.length == events1.length) {
            runnerTask.report("NORTHANTS event insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS event insert ... FAILED.", COLOUR_FAILED);
    }
}
