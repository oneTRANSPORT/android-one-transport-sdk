package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch.SketchRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.node.NodeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorDeleteBeforeTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsLatestDataVectorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsLatestTravelSummaryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryDeleteBeforeTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.travelsummary.BcsTravelSummaryRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector.BcsConfigVectorRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector.BcsDataVectorRetrieveTest;

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
//            new DeviceRetrieveTest(),
//            new TrafficGroupRetrieveTest(),
//            new CvsDeviceDeleteTest(),
//            new CvsTrafficGroupDeleteTest(),
//            new CvsDeviceInsertTest(),
//            new CvsTrafficGroupInsertTest(),
//            new CvsDeviceQueryTest(),
//            new CvsTrafficGroupQueryTest(),
//            new CvsSyncAdapterTest(),
            // BitCarrier Silverstone
            new NodeRetrieveTest(),
            new BcsNodeDeleteTest(),
            new BcsNodeInsertTest(),
            new BcsNodeQueryTest(),
            new BcsNodeIntervalQueryTest(),
            new BcsDataVectorRetrieveTest(),
            new BcsDataVectorInsertTest(),
            new BcsDataVectorDeleteTest(),
            new BcsDataVectorInsertTest(),
            new BcsDataVectorQueryTest(),
            new BcsLatestDataVectorQueryTest(),
            new BcsDataVectorIntervalQueryTest(),
            new BcsDataVectorDeleteBeforeTest(),
            new BcsConfigVectorRetrieveTest(),
            new BcsConfigVectorDeleteTest(),
            new BcsConfigVectorInsertTest(),
            new BcsConfigVectorQueryTest(),
            new BcsConfigVectorIntervalQueryTest(),
            new BcsTravelSummaryRetrieveTest(),
            new BcsTravelSummaryInsertTest(),
            new BcsTravelSummaryDeleteTest(),
            new BcsTravelSummaryInsertTest(),
            new BcsTravelSummaryQueryTest(),
            new BcsLatestTravelSummaryQueryTest(),
            new BcsTravelSummaryIntervalQueryTest(),
            new BcsTravelSummaryDeleteBeforeTest(),
            new SketchRetrieveTest(),
            new BcsSketchDeleteTest(),
            new BcsSketchInsertTest(),
            new BcsSketchQueryTest(),
            new BcsSketchIntervalQueryTest(),
            new BcsSyncAdapterTest(),

//            new ApplicationEntityDeleteTest()
    };
}
