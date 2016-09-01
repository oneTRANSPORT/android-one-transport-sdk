package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch.GetSketchArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.travelsummary.GetTravelSummaryArrayTest;

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
//            new GetDeviceArrayTest(),
//            new GetTrafficGroupArrayTest(),
//            new CvsDeviceDeleteTest(),
//            new CvsTrafficGroupDeleteTest(),
//            new CvsDeviceInsertTest(),
//            new CvsTrafficGroupInsertTest(),
//            new CvsDeviceQueryTest(),
//            new CvsTrafficGroupQueryTest(),
//            new CvsHistoryQueryTest(),
//            new CvsHistorySensorQueryTest(),
//            new CvsSyncAdapterTest(),
            // BitCarrier Silverstone
            new GetTravelSummaryArrayTest(),
            new GetSketchArrayTest(),
            new BcsSketchDeleteTest(),
            new BcsSketchInsertTest(),
            new BcsSketchQueryTest(),
            new BcsTravelSummaryDeleteTest(),
            new BcsTravelSummaryInsertTest(),
            new BcsTravelSummaryQueryTest(),
            new BcsSyncAdapterTest(),

//            new ApplicationEntityDeleteTest()
    };
}
