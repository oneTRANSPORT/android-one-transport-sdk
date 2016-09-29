package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.herts.carpark.HertsCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.event.HertsEventRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadWorksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadWorksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadWorksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadWorksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficQueueDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficQueueInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficQueueIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficQueueLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.roadworks.HertsRoadWorksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficflow.HertsTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficqueue.HertsTrafficQueueRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficscoot.HertsTrafficScootRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficspeed.HertsTrafficSpeedRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.traffictraveltime.HertsTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.vms.HertsVariableMessageSignRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.carpark.NorthantsCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.event.NorthantsEventRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsEventDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsEventInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsEventIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsEventLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadWorksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadWorksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadWorksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadWorksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficQueueDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficQueueInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficQueueIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficQueueLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficScootDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficScootInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficScootIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficScootLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficSpeedDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficSpeedInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficSpeedIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficSpeedLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.roadworks.NorthantsRoadWorksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.trafficflow.NorthantsTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.trafficqueue.NorthantsTrafficQueueRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.trafficscoot.NorthantsTrafficScootRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.trafficspeed.NorthantsTrafficSpeedRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.traffictraveltime.NorthantsTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.vms.NorthantsVariableMessageSignRetrieveTest;

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
            new NorthantsCarParkRetrieveTest(),
            new NorthantsEventRetrieveTest(),
            new NorthantsRoadWorksRetrieveTest(),
            new NorthantsTrafficFlowRetrieveTest(),
            new NorthantsTrafficQueueRetrieveTest(),
            new NorthantsTrafficScootRetrieveTest(),
            new NorthantsTrafficSpeedRetrieveTest(),
            new NorthantsTrafficTravelTimeRetrieveTest(),
            new NorthantsVariableMessageSignRetrieveTest(),

            new NorthantsCarParkInsertTest(), // Also query test.
            new NorthantsEventInsertTest(),
            new NorthantsRoadWorksInsertTest(),
            new NorthantsTrafficFlowInsertTest(),
            new NorthantsTrafficQueueInsertTest(),
            new NorthantsTrafficScootInsertTest(),
            new NorthantsTrafficSpeedInsertTest(),
            new NorthantsTrafficTravelTimeInsertTest(),
            new NorthantsVariableMessageSignInsertTest(),

            new NorthantsCarParkIntervalQueryTest(),
            new NorthantsEventIntervalQueryTest(),
            new NorthantsRoadWorksIntervalQueryTest(),
            new NorthantsTrafficFlowIntervalQueryTest(),
            new NorthantsTrafficQueueIntervalQueryTest(),
            new NorthantsTrafficScootIntervalQueryTest(),
            new NorthantsTrafficSpeedIntervalQueryTest(),
            new NorthantsTrafficTravelTimeIntervalQueryTest(),
            new NorthantsVariableMessageSignIntervalQueryTest(),

            new NorthantsCarParkLatestTest(),
            new NorthantsEventLatestTest(),
            new NorthantsRoadWorksLatestTest(),
            new NorthantsTrafficFlowLatestTest(),
            new NorthantsTrafficQueueLatestTest(),
            new NorthantsTrafficScootLatestTest(),
            new NorthantsTrafficSpeedLatestTest(),
            new NorthantsTrafficTravelTimeLatestTest(),
            new NorthantsVariableMessageSignLatestTest(),

            new NorthantsCarParkDeleteTest(),
            new NorthantsEventDeleteTest(),
            new NorthantsRoadWorksDeleteTest(),
            new NorthantsTrafficFlowDeleteTest(),
            new NorthantsTrafficQueueDeleteTest(),
            new NorthantsTrafficScootDeleteTest(),
            new NorthantsTrafficSpeedDeleteTest(),
            new NorthantsTrafficTravelTimeDeleteTest(),
            new NorthantsVariableMessageSignDeleteTest(),

            new NorthantsSyncAdapterTest(),

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
