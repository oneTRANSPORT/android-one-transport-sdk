package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.device.DeviceRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsHistoryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsHistorySensorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.traffic.TrafficGroupRetrieveTest;

public class TestList {

    public OneTransportTest[] oneTransportTests = {
//            new ApplicationEntityCreateTest(),
//            new GetCarParkArrayTest(),
//            new GetVariableMessageSignArrayTest(),
//            new GetTrafficFlowArrayTest(),
//            new GetRoadWorksArrayTest(),
//            new BucksCarParkDeleteTest(),
//            new BucksVariableMessageSignDeleteTest(),
//            new BucksTrafficFlowDeleteTest(),
//            new BucksRoadWorksDeleteTest(),
//            new BucksCarParkInsertTest(),
//            new BucksVariableMessageSignInsertTest(),
//            new BucksTrafficFlowInsertTest(),
//            new BucksRoadWorksInsertTest(),
//            new BucksVmsQueryTest(),
//            new BucksTrafficFlowQueryTest(),
//            new BucksCarParkBoxQueryTest(),
//            new BucksVmsBoxQueryTest(),
//            new BucksTrafficFlowBoxQueryTest(),
//            new BucksRoadWorksBoxQueryTest(),
//            new BucksSyncAdapterTest(),
            // Clearview Silverstone.
            new DeviceRetrieveTest(),
            new TrafficGroupRetrieveTest(),
            new CvsDeviceDeleteTest(),
            new CvsTrafficGroupDeleteTest(),
            new CvsDeviceInsertTest(),
            new CvsTrafficGroupInsertTest(),
            new CvsDeviceQueryTest(),
            new CvsTrafficGroupQueryTest(),
            new CvsHistoryQueryTest(),
            new CvsHistorySensorQueryTest(),
            new CvsSyncAdapterTest(),
            // BitCarrier Silverstone
//            new NodeRetrieveTest(),
//            new BcsNodeDeleteTest(),
//            new BcsNodeInsertTest(),
//            new BcsNodeQueryTest(),
//            new BcsNodeIntervalQueryTest(),
//            new VectorRetrieveTest(),
//            new BcsVectorDeleteTest(),
//            new BcsVectorInsertTest(),
//            new BcsVectorQueryTest(),
//            new BcsVectorIntervalQueryTest(),
//            new TravelSummaryRetrieveTest(),
//            new BcsTravelSummaryDeleteTest(),
//            new BcsTravelSummaryInsertTest(),
//            new BcsTravelSummaryQueryTest(),
//            new BcsTravelSummaryIntervalQueryTest(),
//            new SketchRetrieveTest(),
//            new BcsSketchDeleteTest(),
//            new BcsSketchInsertTest(),
//            new BcsSketchQueryTest(),
//            new BcsSketchIntervalQueryTest(),
//            new BcsSyncAdapterTest(),

//            new ApplicationEntityDeleteTest()
    };
}
