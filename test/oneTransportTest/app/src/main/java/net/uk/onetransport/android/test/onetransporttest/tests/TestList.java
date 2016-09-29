package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.oxon.carpark.OxonCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.event.OxonEventRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadWorksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadWorksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadWorksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadWorksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.roadworks.OxonRoadWorksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficflow.OxonTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficqueue.OxonTrafficQueueRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficscoot.OxonTrafficScootRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficspeed.OxonTrafficSpeedRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.traffictraveltime.OxonTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.vms.OxonVariableMessageSignRetrieveTest;

public class TestList {

    public OneTransportTest[] oneTransportTests = {
//            new ApplicationEntityCreateTest(),
            // Buckinghamshire.
//            new BucksCarParkRetrieveTest(),
//            new BucksEventRetrieveTest(),
//            new BucksRoadWorksRetrieveTest(),
//            new BucksTrafficFlowRetrieveTest(),
//            new BucksTrafficQueueRetrieveTest(),
//            new BucksTrafficScootRetrieveTest(),
//            new BucksTrafficSpeedRetrieveTest(),
//            new BucksTrafficTravelTimeRetrieveTest(),
//            new BucksVariableMessageSignRetrieveTest(),
//
//            new BucksCarParkInsertTest(), // Also query test.
//            new BucksEventInsertTest(),
//            new BucksRoadWorksInsertTest(),
//            new BucksTrafficFlowInsertTest(),
//            new BucksTrafficQueueInsertTest(),
//            new BucksTrafficScootInsertTest(),
//            new BucksTrafficSpeedInsertTest(),
//            new BucksTrafficTravelTimeInsertTest(),
//            new BucksVariableMessageSignInsertTest(),
//
//            new BucksCarParkIntervalQueryTest(),
//            new BucksEventIntervalQueryTest(),
//            new BucksRoadWorksIntervalQueryTest(),
//            new BucksTrafficFlowIntervalQueryTest(),
//            new BucksTrafficQueueIntervalQueryTest(),
//            new BucksTrafficScootIntervalQueryTest(),
//            new BucksTrafficSpeedIntervalQueryTest(),
//            new BucksTrafficTravelTimeIntervalQueryTest(),
//            new BucksVariableMessageSignIntervalQueryTest(),
//
//            new BucksCarParkLatestTest(),
//            new BucksEventLatestTest(),
//            new BucksRoadWorksLatestTest(),
//            new BucksTrafficFlowLatestTest(),
//            new BucksTrafficQueueLatestTest(),
//            new BucksTrafficScootLatestTest(),
//            new BucksTrafficSpeedLatestTest(),
//            new BucksTrafficTravelTimeLatestTest(),
//            new BucksVariableMessageSignLatestTest(),
//
//            new BucksCarParkDeleteTest(),
//            new BucksEventDeleteTest(),
//            new BucksRoadWorksDeleteTest(),
//            new BucksTrafficFlowDeleteTest(),
//            new BucksTrafficQueueDeleteTest(),
//            new BucksTrafficScootDeleteTest(),
//            new BucksTrafficSpeedDeleteTest(),
//            new BucksTrafficTravelTimeDeleteTest(),
//            new BucksVariableMessageSignDeleteTest(),
//
//            new BucksSyncAdapterTest(),

            // Hertfordshire.
//            new HertsCarParkRetrieveTest(),
//            new HertsEventRetrieveTest(),
//            new HertsRoadWorksRetrieveTest(),
//            new HertsTrafficFlowRetrieveTest(),
//            new HertsTrafficQueueRetrieveTest(),
//            new HertsTrafficScootRetrieveTest(),
//            new HertsTrafficSpeedRetrieveTest(),
//            new HertsTrafficTravelTimeRetrieveTest(),
//            new HertsVariableMessageSignRetrieveTest(),
//
//            new HertsCarParkInsertTest(), // Also query test.
//            new HertsEventInsertTest(),
//            new HertsRoadWorksInsertTest(),
//            new HertsTrafficFlowInsertTest(),
//            new HertsTrafficQueueInsertTest(),
//            new HertsTrafficScootInsertTest(),
//            new HertsTrafficSpeedInsertTest(),
//            new HertsTrafficTravelTimeInsertTest(),
//            new HertsVariableMessageSignInsertTest(),
//
//            new HertsCarParkIntervalQueryTest(),
//            new HertsEventIntervalQueryTest(),
//            new HertsRoadWorksIntervalQueryTest(),
//            new HertsTrafficFlowIntervalQueryTest(),
//            new HertsTrafficQueueIntervalQueryTest(),
//            new HertsTrafficScootIntervalQueryTest(),
//            new HertsTrafficSpeedIntervalQueryTest(),
//            new HertsTrafficTravelTimeIntervalQueryTest(),
//            new HertsVariableMessageSignIntervalQueryTest(),
//
//            new HertsCarParkLatestTest(),
//            new HertsEventLatestTest(),
//            new HertsRoadWorksLatestTest(),
//            new HertsTrafficFlowLatestTest(),
//            new HertsTrafficQueueLatestTest(),
//            new HertsTrafficScootLatestTest(),
//            new HertsTrafficSpeedLatestTest(),
//            new HertsTrafficTravelTimeLatestTest(),
//            new HertsVariableMessageSignLatestTest(),
//
//            new HertsCarParkDeleteTest(),
//            new HertsEventDeleteTest(),
//            new HertsRoadWorksDeleteTest(),
//            new HertsTrafficFlowDeleteTest(),
//            new HertsTrafficQueueDeleteTest(),
//            new HertsTrafficScootDeleteTest(),
//            new HertsTrafficSpeedDeleteTest(),
//            new HertsTrafficTravelTimeDeleteTest(),
//            new HertsVariableMessageSignDeleteTest(),
//
//            new HertsSyncAdapterTest(),

            // Northamptonshire.
//            new NorthantsCarParkRetrieveTest(),
//            new NorthantsEventRetrieveTest(),
//            new NorthantsRoadWorksRetrieveTest(),
//            new NorthantsTrafficFlowRetrieveTest(),
//            new NorthantsTrafficQueueRetrieveTest(),
//            new NorthantsTrafficScootRetrieveTest(),
//            new NorthantsTrafficSpeedRetrieveTest(),
//            new NorthantsTrafficTravelTimeRetrieveTest(),
//            new NorthantsVariableMessageSignRetrieveTest(),
//
//            new NorthantsCarParkInsertTest(), // Also query test.
//            new NorthantsEventInsertTest(),
//            new NorthantsRoadWorksInsertTest(),
//            new NorthantsTrafficFlowInsertTest(),
//            new NorthantsTrafficQueueInsertTest(),
//            new NorthantsTrafficScootInsertTest(),
//            new NorthantsTrafficSpeedInsertTest(),
//            new NorthantsTrafficTravelTimeInsertTest(),
//            new NorthantsVariableMessageSignInsertTest(),
//
//            new NorthantsCarParkIntervalQueryTest(),
//            new NorthantsEventIntervalQueryTest(),
//            new NorthantsRoadWorksIntervalQueryTest(),
//            new NorthantsTrafficFlowIntervalQueryTest(),
//            new NorthantsTrafficQueueIntervalQueryTest(),
//            new NorthantsTrafficScootIntervalQueryTest(),
//            new NorthantsTrafficSpeedIntervalQueryTest(),
//            new NorthantsTrafficTravelTimeIntervalQueryTest(),
//            new NorthantsVariableMessageSignIntervalQueryTest(),
//
//            new NorthantsCarParkLatestTest(),
//            new NorthantsEventLatestTest(),
//            new NorthantsRoadWorksLatestTest(),
//            new NorthantsTrafficFlowLatestTest(),
//            new NorthantsTrafficQueueLatestTest(),
//            new NorthantsTrafficScootLatestTest(),
//            new NorthantsTrafficSpeedLatestTest(),
//            new NorthantsTrafficTravelTimeLatestTest(),
//            new NorthantsVariableMessageSignLatestTest(),
//
//            new NorthantsCarParkDeleteTest(),
//            new NorthantsEventDeleteTest(),
//            new NorthantsRoadWorksDeleteTest(),
//            new NorthantsTrafficFlowDeleteTest(),
//            new NorthantsTrafficQueueDeleteTest(),
//            new NorthantsTrafficScootDeleteTest(),
//            new NorthantsTrafficSpeedDeleteTest(),
//            new NorthantsTrafficTravelTimeDeleteTest(),
//            new NorthantsVariableMessageSignDeleteTest(),
//
//            new NorthantsSyncAdapterTest(),

            // Oxfordshire.
            new OxonCarParkRetrieveTest(),
            new OxonEventRetrieveTest(),
            new OxonRoadWorksRetrieveTest(),
            new OxonTrafficFlowRetrieveTest(),
            new OxonTrafficQueueRetrieveTest(),
            new OxonTrafficScootRetrieveTest(),
            new OxonTrafficSpeedRetrieveTest(),
            new OxonTrafficTravelTimeRetrieveTest(),
            new OxonVariableMessageSignRetrieveTest(),

            new OxonCarParkInsertTest(), // Also query test.
            new OxonEventInsertTest(),
            new OxonRoadWorksInsertTest(),
            new OxonTrafficFlowInsertTest(),
            new OxonTrafficQueueInsertTest(),
            new OxonTrafficScootInsertTest(),
            new OxonTrafficSpeedInsertTest(),
            new OxonTrafficTravelTimeInsertTest(),
            new OxonVariableMessageSignInsertTest(),

            new OxonCarParkIntervalQueryTest(),
            new OxonEventIntervalQueryTest(),
            new OxonRoadWorksIntervalQueryTest(),
            new OxonTrafficFlowIntervalQueryTest(),
            new OxonTrafficQueueIntervalQueryTest(),
            new OxonTrafficScootIntervalQueryTest(),
            new OxonTrafficSpeedIntervalQueryTest(),
            new OxonTrafficTravelTimeIntervalQueryTest(),
            new OxonVariableMessageSignIntervalQueryTest(),

            new OxonCarParkLatestTest(),
            new OxonEventLatestTest(),
            new OxonRoadWorksLatestTest(),
            new OxonTrafficFlowLatestTest(),
            new OxonTrafficQueueLatestTest(),
            new OxonTrafficScootLatestTest(),
            new OxonTrafficSpeedLatestTest(),
            new OxonTrafficTravelTimeLatestTest(),
            new OxonVariableMessageSignLatestTest(),

            new OxonCarParkDeleteTest(),
            new OxonEventDeleteTest(),
            new OxonRoadWorksDeleteTest(),
            new OxonTrafficFlowDeleteTest(),
            new OxonTrafficQueueDeleteTest(),
            new OxonTrafficScootDeleteTest(),
            new OxonTrafficSpeedDeleteTest(),
            new OxonTrafficTravelTimeDeleteTest(),
            new OxonVariableMessageSignDeleteTest(),

            new OxonSyncAdapterTest(),

            // Clearview Silverstone.
//            new CvsDeviceRetrieveTest(),
//            new CvsDeviceDeleteTest(),
//            new CvsDeviceInsertTest(),
//            new CvsDeviceQueryTest(),
//            new CvsDeviceIntervalQueryTest(),
//            new CvsTrafficGroupRetrieveTest(),
//            new CvsTrafficGroupInsertTest(),
//            new CvsTrafficItemDeleteTest(),
//            new CvsTrafficGroupInsertTest(),
//            new CvsTrafficItemQueryTest(),
//            new CvsLatestTrafficItemQueryTest(),
//            new CvsTrafficItemIntervalQueryTest(),
//            new CvsTrafficItemDeleteBeforeTest(),
//            new CvsSyncAdapterTest(),
            // BitCarrier Silverstone
//            new BcsNodeRetrieveTest(),
//            new BcsNodeDeleteTest(),
//            new BcsNodeInsertTest(),
//            new BcsNodeQueryTest(),
//            new BcsNodeIntervalQueryTest(),
//            new BcsDataVectorRetrieveTest(),
//            new BcsDataVectorInsertTest(),
//            new BcsDataVectorDeleteTest(),
//            new BcsDataVectorInsertTest(),
//            new BcsDataVectorQueryTest(),
//            new BcsLatestDataVectorQueryTest(),
//            new BcsDataVectorIntervalQueryTest(),
//            new BcsDataVectorDeleteBeforeTest(),
//            new BcsConfigVectorRetrieveTest(),
//            new BcsConfigVectorDeleteTest(),
//            new BcsConfigVectorInsertTest(),
//            new BcsConfigVectorQueryTest(),
//            new BcsConfigVectorIntervalQueryTest(),
//            new BcsTravelSummaryRetrieveTest(),
//            new BcsTravelSummaryInsertTest(),
//            new BcsTravelSummaryDeleteTest(),
//            new BcsTravelSummaryInsertTest(),
//            new BcsTravelSummaryQueryTest(),
//            new BcsLatestTravelSummaryQueryTest(),
//            new BcsTravelSummaryIntervalQueryTest(),
//            new BcsTravelSummaryDeleteBeforeTest(),
//            new BcsSketchRetrieveTest(),
//            new BcsSketchDeleteTest(),
//            new BcsSketchInsertTest(),
//            new BcsSketchQueryTest(),
//            new BcsSketchIntervalQueryTest(),
//            new BcsSyncAdapterTest(),

//            new ApplicationEntityDeleteTest()
    };
}
