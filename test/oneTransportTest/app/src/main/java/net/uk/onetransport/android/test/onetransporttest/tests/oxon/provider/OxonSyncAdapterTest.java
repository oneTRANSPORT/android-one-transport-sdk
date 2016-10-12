package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.carparks.CarPark;
import net.uk.onetransport.android.county.oxon.carparks.CarParkRetriever;
import net.uk.onetransport.android.county.oxon.events.Event;
import net.uk.onetransport.android.county.oxon.events.EventRetriever;
import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.provider.OxonProviderModule;
import net.uk.onetransport.android.county.oxon.roadworks.Roadworks;
import net.uk.onetransport.android.county.oxon.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlow;
import net.uk.onetransport.android.county.oxon.trafficflow.TrafficFlowRetriever;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueue;
import net.uk.onetransport.android.county.oxon.trafficqueue.TrafficQueueRetriever;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScoot;
import net.uk.onetransport.android.county.oxon.trafficscoot.TrafficScootRetriever;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeed;
import net.uk.onetransport.android.county.oxon.trafficspeed.TrafficSpeedRetriever;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTime;
import net.uk.onetransport.android.county.oxon.traffictraveltime.TrafficTravelTimeRetriever;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign;
import net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSignRetriever;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonSyncAdapterTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        startSync(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON sync adapter");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void startSync(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON sync adapter");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_CAR_PARK);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_EVENT);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_ROADWORKS);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_TRAFFIC_FLOW);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_TRAFFIC_QUEUE);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_TRAFFIC_SCOOT);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_TRAFFIC_SPEED);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_TRAFFIC_TRAVEL_TIME);
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_VMS);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        OxonProviderModule.refresh(context, true, true, true, true, true, true, true, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        CarPark[] carParks = new CarParkRetriever(context).retrieve();
        CarPark[] carParks1 = OxonContentHelper.getCarParks(context);
        if (carParks.length != carParks1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        Event[] events = new EventRetriever(context).retrieve();
        Event[] events1 = OxonContentHelper.getEvents(context);
        if (events.length != events1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        Roadworks[] roadworkses = new RoadworksRetriever(context).retrieve();
        Roadworks[] roadworkses1 = OxonContentHelper.getRoadworks(context);
        if (roadworkses.length != roadworkses1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficFlow[] trafficFlows = new TrafficFlowRetriever(context).retrieve();
        TrafficFlow[] trafficFlows1 = OxonContentHelper.getTrafficFlows(context);
        if (trafficFlows.length != trafficFlows1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficQueue[] trafficQueues = new TrafficQueueRetriever(context).retrieve();
        TrafficQueue[] trafficQueues1 = OxonContentHelper.getTrafficQueues(context);
        if (trafficQueues.length != trafficQueues1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficScoot[] trafficScoots = new TrafficScootRetriever(context).retrieve();
        TrafficScoot[] trafficScoots1 = OxonContentHelper.getTrafficScoots(context);
        if (trafficScoots.length != trafficScoots1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficSpeed[] trafficSpeeds = new TrafficSpeedRetriever(context).retrieve();
        TrafficSpeed[] trafficSpeeds1 = OxonContentHelper.getTrafficSpeeds(context);
        if (trafficSpeeds.length != trafficSpeeds1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        TrafficTravelTime[] trafficTravelTimes = new TrafficTravelTimeRetriever(context).retrieve();
        TrafficTravelTime[] trafficTravelTimes1 = OxonContentHelper.getTrafficTravelTimes(context);
        if (trafficTravelTimes.length != trafficTravelTimes1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        VariableMessageSign[] variableMessageSigns = new VariableMessageSignRetriever(context).retrieve();
        VariableMessageSign[] variableMessageSigns1 = OxonContentHelper.getVariableMessageSigns(context);
        if (variableMessageSigns.length != variableMessageSigns1.length) {
            runnerTask.report("OXON sync adapter ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("OXON sync adapter ... PASSED.", COLOUR_PASSED);
    }
}
