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
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.carparks.CarPark;
import net.uk.onetransport.android.county.northants.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.provider.NorthantsProviderModule;
import net.uk.onetransport.android.county.northants.roadworks.Roadworks;
import net.uk.onetransport.android.county.northants.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.northants.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.northants.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.northants.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsSyncAdapterTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        startSync(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS sync adapter");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void startSync(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS sync adapter");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_CAR_PARK);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_ROADWORKS);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_VMS);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        NorthantsProviderModule.refresh(context, true, true, true, true, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        CarPark[] carParks = new CarParkRetriever(context).retrieve();
        CarPark[] carParks1 = NorthantsContentHelper.getCarParks(context);
        if (carParks.length != carParks1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        Roadworks[] roadworkses = new RoadworksRetriever(context).retrieve();
        Roadworks[] roadworkses1 = NorthantsContentHelper.getRoadworks(context);
        if (roadworkses.length != roadworkses1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        TrafficFlow[] trafficFlows1 = NorthantsContentHelper.getTrafficFlows(context);
        if (trafficFlows.length != trafficFlows1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
        TrafficTravelTime[] trafficTravelTimes1 = NorthantsContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length != trafficTravelTimes1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        VariableMessageSign[] variableMessageSigns1 = NorthantsContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length != variableMessageSigns1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("NORTHANTS sync adapter ... PASSED.", COLOUR_PASSED);
    }
}
