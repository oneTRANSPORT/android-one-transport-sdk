package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.carparks.CarPark;
import net.uk.onetransport.android.county.herts.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.herts.events.Event;
import net.uk.onetransport.android.county.herts.events.EventRetriever;
import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.provider.HertsProviderModule;
import net.uk.onetransport.android.county.herts.roadworks.RoadWorks;
import net.uk.onetransport.android.county.herts.roadworks.RoadWorksRetriever;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.herts.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.herts.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.herts.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.herts.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.herts.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.herts.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.herts.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsSyncAdapterTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        startSync(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS sync adapter");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void startSync(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS sync adapter");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_CAR_PARK);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_EVENT);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_ROAD_WORKS);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_VMS);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        HertsProviderModule.refresh(context, true, true, true, true, true, true, true, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        CarPark[] carParks = new CarParkRetriever(context).retrieve();
        CarPark[] carParks1 = HertsContentHelper.getCarParks(context);
        if (carParks.length != carParks1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        Event[] events = new EventRetriever(context).retrieve();
        Event[] events1 = HertsContentHelper.getEvents(context);
        if (events.length != events1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        RoadWorks[] roadWorkses = new RoadWorksRetriever(context).retrieve();
        RoadWorks[] roadWorkses1 = HertsContentHelper.getRoadWorks(context);
        if (roadWorkses.length != roadWorkses1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        TrafficFlow[] trafficFlows1 = HertsContentHelper.getTrafficFlows(context);
        if (trafficFlows.length != trafficFlows1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        TrafficQueue[] trafficQueues1 = HertsContentHelper.getTrafficQueues(context);
        if (trafficQueues.length != trafficQueues1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        TrafficScoot[] trafficScoots1 = HertsContentHelper.getTrafficScoots(context);
        if (trafficScoots.length != trafficScoots1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        TrafficSpeed[] trafficSpeeds1 = HertsContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length != trafficSpeeds1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
        TrafficTravelTime[] trafficTravelTimes1 = HertsContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length != trafficTravelTimes1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        VariableMessageSign[] variableMessageSigns1 = HertsContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length != variableMessageSigns1.length) {
            runnerTask.report("HERTS sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("HERTS sync adapter ... PASSED.", COLOUR_PASSED);
    }
}
