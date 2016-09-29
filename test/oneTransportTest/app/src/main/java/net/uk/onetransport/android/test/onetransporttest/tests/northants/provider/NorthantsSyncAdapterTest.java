package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.carparks.CarPark;
import net.uk.onetransport.android.county.northants.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.northants.events.Event;
import net.uk.onetransport.android.county.northants.events.EventRetriever;
import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.provider.NorthantsProviderModule;
import net.uk.onetransport.android.county.northants.roadworks.RoadWorks;
import net.uk.onetransport.android.county.northants.roadworks.RoadWorksRetriever;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.northants.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.northants.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.northants.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.northants.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.northants.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.northants.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.northants.trafficspeed.TrafficSpeedRetriever;
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
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_EVENT);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_ROAD_WORKS);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_VMS);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        NorthantsProviderModule.refresh(context, true, true, true, true, true, true, true, true, true);
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
        Event[] events = new EventRetriever(context).retrieve();
        Event[] events1 = NorthantsContentHelper.getEvents(context);
        if (events.length != events1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        RoadWorks[] roadWorkses = new RoadWorksRetriever(context).retrieve();
        RoadWorks[] roadWorkses1 = NorthantsContentHelper.getRoadWorks(context);
        if (roadWorkses.length != roadWorkses1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        TrafficFlow[] trafficFlows1 = NorthantsContentHelper.getTrafficFlows(context);
        if (trafficFlows.length != trafficFlows1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        TrafficQueue[] trafficQueues1 = NorthantsContentHelper.getTrafficQueues(context);
        if (trafficQueues.length != trafficQueues1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        TrafficScoot[] trafficScoots1 = NorthantsContentHelper.getTrafficScoots(context);
        if (trafficScoots.length != trafficScoots1.length) {
            runnerTask.report("NORTHANTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        TrafficSpeed[] trafficSpeeds1 = NorthantsContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length != trafficSpeeds1.length) {
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
