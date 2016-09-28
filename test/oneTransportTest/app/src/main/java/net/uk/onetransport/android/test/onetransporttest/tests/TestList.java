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
            new HertsCarParkRetrieveTest(),
            new HertsEventRetrieveTest(),
            new HertsRoadWorksRetrieveTest(),
            new HertsTrafficFlowRetrieveTest(),
            new HertsTrafficQueueRetrieveTest(),
            new HertsTrafficScootRetrieveTest(),
            new HertsTrafficSpeedRetrieveTest(),
            new HertsTrafficTravelTimeRetrieveTest(),
            new HertsVariableMessageSignRetrieveTest(),

            new HertsCarParkInsertTest(), // Also query test.
            new HertsEventInsertTest(),
            new HertsRoadWorksInsertTest(),
            new HertsTrafficFlowInsertTest(),
            new HertsTrafficQueueInsertTest(),
            new HertsTrafficScootInsertTest(),
            new HertsTrafficSpeedInsertTest(),
            new HertsTrafficTravelTimeInsertTest(),
            new HertsVariableMessageSignInsertTest(),

            new HertsCarParkIntervalQueryTest(),
            new HertsEventIntervalQueryTest(),
            new HertsRoadWorksIntervalQueryTest(),
            new HertsTrafficFlowIntervalQueryTest(),
            new HertsTrafficQueueIntervalQueryTest(),
            new HertsTrafficScootIntervalQueryTest(),
            new HertsTrafficSpeedIntervalQueryTest(),
            new HertsTrafficTravelTimeIntervalQueryTest(),
            new HertsVariableMessageSignIntervalQueryTest(),

            new HertsCarParkLatestTest(),
            new HertsEventLatestTest(),
            new HertsRoadWorksLatestTest(),
            new HertsTrafficFlowLatestTest(),
            new HertsTrafficQueueLatestTest(),
            new HertsTrafficScootLatestTest(),
            new HertsTrafficSpeedLatestTest(),
            new HertsTrafficTravelTimeLatestTest(),
            new HertsVariableMessageSignLatestTest(),

            new HertsCarParkDeleteTest(),
            new HertsEventDeleteTest(),
            new HertsRoadWorksDeleteTest(),
            new HertsTrafficFlowDeleteTest(),
            new HertsTrafficQueueDeleteTest(),
            new HertsTrafficScootDeleteTest(),
            new HertsTrafficSpeedDeleteTest(),
            new HertsTrafficTravelTimeDeleteTest(),
            new HertsVariableMessageSignDeleteTest(),

            new HertsSyncAdapterTest(),

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
