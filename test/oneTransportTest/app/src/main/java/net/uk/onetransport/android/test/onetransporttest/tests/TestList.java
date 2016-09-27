package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark.BucksCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.event.BucksEventRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.roadworks.BucksRoadWorksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficflow.BucksTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficqueue.BucksTrafficQueueRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficscoot.BucksTrafficScootRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficspeed.BucksTrafficSpeedRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.traffictraveltime.BucksTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.vms.BucksVariableMessageSignRetrieveTest;

public class TestList {

    public OneTransportTest[] oneTransportTests = {
//            new ApplicationEntityCreateTest(),
            new BucksCarParkRetrieveTest(),
            new BucksEventRetrieveTest(),
            new BucksRoadWorksRetrieveTest(),
            new BucksTrafficFlowRetrieveTest(),
            new BucksTrafficQueueRetrieveTest(),
            new BucksTrafficScootRetrieveTest(),
            new BucksTrafficSpeedRetrieveTest(),
            new BucksTrafficTravelTimeRetrieveTest(),
            new BucksVariableMessageSignRetrieveTest(),

            new BucksCarParkInsertTest(), // Also query test.
            new BucksEventInsertTest(),
            new BucksRoadWorksInsertTest(),
            new BucksTrafficFlowInsertTest(),
            new BucksTrafficQueueInsertTest(),
            new BucksTrafficScootInsertTest(),
            new BucksTrafficSpeedInsertTest(),
            new BucksTrafficTravelTimeInsertTest(),
            new BucksVariableMessageSignInsertTest(),

            new BucksCarParkIntervalQueryTest(),
            new BucksEventIntervalQueryTest(),
            new BucksRoadWorksIntervalQueryTest(),
            new BucksTrafficFlowIntervalQueryTest(),
            new BucksTrafficQueueIntervalQueryTest(),
            new BucksTrafficScootIntervalQueryTest(),
            new BucksTrafficSpeedIntervalQueryTest(),
            new BucksTrafficTravelTimeIntervalQueryTest(),
            new BucksVariableMessageSignIntervalQueryTest(),

            new BucksCarParkLatestTest(),
            new BucksEventLatestTest(),
            new BucksRoadWorksLatestTest(),
            new BucksTrafficFlowLatestTest(),
            new BucksTrafficQueueLatestTest(),
            new BucksTrafficScootLatestTest(),
            new BucksTrafficSpeedLatestTest(),
            new BucksTrafficTravelTimeLatestTest(),
            new BucksVariableMessageSignLatestTest(),

            new BucksCarParkDeleteTest(),
            new BucksEventDeleteTest(),
            new BucksRoadWorksDeleteTest(),
            new BucksTrafficFlowDeleteTest(),
            new BucksTrafficQueueDeleteTest(),
            new BucksTrafficScootDeleteTest(),
            new BucksTrafficSpeedDeleteTest(),
            new BucksTrafficTravelTimeDeleteTest(),
            new BucksVariableMessageSignDeleteTest(),

            new BucksSyncAdapterTest(),

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
