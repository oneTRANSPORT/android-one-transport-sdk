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

import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonCarParkInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertCarParks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON car park insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertCarParks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON car park insert");
        Context context = runnerTask.getContext();
        // This is only needed if new CIs are created during the running of the tests.
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_CAR_PARK);
        CarPark[] carParks = new CarParkRetriever(context).retrieve();
        if (carParks == null || carParks.length == 0) {
            runnerTask.report("OXON car park insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        // Insert the same thing twice to check for a working UNIQUE ON CONFLICT IGNORE index.
        OxonContentHelper.insertIntoProvider(context, carParks);
        OxonContentHelper.insertIntoProvider(context, carParks);
        CarPark[] carParks1 = OxonContentHelper.getCarParks(context);
        if (carParks.length == carParks1.length) {
            runnerTask.report("OXON car park insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON car park insert ... FAILED.", COLOUR_FAILED);
    }
}
