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
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.carparks.CarPark;
import net.uk.onetransport.android.county.bucks.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.bucks.events.Event;
import net.uk.onetransport.android.county.bucks.events.EventRetriever;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.provider.BucksProviderModule;
import net.uk.onetransport.android.county.bucks.roadworks.Roadworks;
import net.uk.onetransport.android.county.bucks.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.bucks.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.bucks.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.bucks.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.bucks.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.bucks.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksSyncAdapterTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        startSync(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS sync adapter");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void startSync(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS sync adapter");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_CAR_PARK);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_EVENT);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_ROADWORKS);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_VMS);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        BucksProviderModule.refresh(context, true, true, true, true, true, true, true, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        CarPark[] carParks = new CarParkRetriever(context).retrieve();
        CarPark[] carParks1 = BucksContentHelper.getCarParks(context);
        if (carParks.length != carParks1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        Event[] events = new EventRetriever(context).retrieve();
        Event[] events1 = BucksContentHelper.getEvents(context);
        if (events.length != events1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        Roadworks[] roadWorkses = new RoadworksRetriever(context).retrieve();
        Roadworks[] roadWorkses1 = BucksContentHelper.getRoadworks(context);
        if (roadWorkses.length != roadWorkses1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        TrafficFlow[] trafficFlows1 = BucksContentHelper.getTrafficFlows(context);
        if (trafficFlows.length != trafficFlows1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        TrafficQueue[] trafficQueues1 = BucksContentHelper.getTrafficQueues(context);
        if (trafficQueues.length != trafficQueues1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        TrafficScoot[] trafficScoots1 = BucksContentHelper.getTrafficScoots(context);
        if (trafficScoots.length != trafficScoots1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        TrafficSpeed[] trafficSpeeds1 = BucksContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length != trafficSpeeds1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
        TrafficTravelTime[] trafficTravelTimes1 = BucksContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length != trafficTravelTimes1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        VariableMessageSign[] variableMessageSigns1 = BucksContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length != variableMessageSigns1.length) {
            runnerTask.report("BUCKS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("BUCKS sync adapter ... PASSED.", COLOUR_PASSED);
    }
}
