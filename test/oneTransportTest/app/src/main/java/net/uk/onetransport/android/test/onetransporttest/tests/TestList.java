package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch.GetSketchArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.node.GetNodeArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsVectorStatusDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsVectorStatusInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsVectorStatusQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.travelsummary.GetTravelSummaryArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector.GetVectorArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector.GetVectorStatusArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark.GetCarParkArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVmsBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVmsQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.roadworks.GetRoadWorksArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficflow.GetTrafficFlowArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.vms.GetVariableMessageSignArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.device.GetDeviceArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsHistoryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsHistorySensorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.traffic.GetTrafficGroupArrayTest;

public class TestList {

    public OneTransportTest[] oneTransportTests = {
//            new ApplicationEntityCreateTest(),
            new GetCarParkArrayTest(),
            new GetVariableMessageSignArrayTest(),
            new GetTrafficFlowArrayTest(),
            new GetRoadWorksArrayTest(),
            new BucksCarParkDeleteTest(),
            new BucksVariableMessageSignDeleteTest(),
            new BucksTrafficFlowDeleteTest(),
            new BucksRoadWorksDeleteTest(),
            new BucksCarParkInsertTest(),
            new BucksVariableMessageSignInsertTest(),
            new BucksTrafficFlowInsertTest(),
            new BucksRoadWorksInsertTest(),
            new BucksVmsQueryTest(),
            new BucksTrafficFlowQueryTest(),
            new BucksCarParkBoxQueryTest(),
            new BucksVmsBoxQueryTest(),
            new BucksTrafficFlowBoxQueryTest(),
            new BucksRoadWorksBoxQueryTest(),
            new BucksSyncAdapterTest(),
            // Clearview Silverstone.
            new GetDeviceArrayTest(),
            new GetTrafficGroupArrayTest(),
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
            // TODO    Turns out this data has expired from the CSE.  Need to add manually
            // TODO    from Owen's previous copy.
            // TODO    Update this lot.
//            new GetNodeArrayTest(),
//            new GetVectorArrayTest(),
//            new GetMetaVectorArrayTest(),
//            new GetRouteArrayTest(),
//            new GetTravelTimesArrayTest,
//            new GetCityArrayTest(),
//            new GetZoneArrayTest(),

            // This data is still available.
            new GetTravelSummaryArrayTest(),
            new GetVectorStatusArrayTest(),
            new GetSketchArrayTest(),
            new BcsSketchDeleteTest(),
            new BcsSketchInsertTest(),
            new BcsSketchQueryTest(),
            new BcsTravelSummaryDeleteTest(),
            new BcsTravelSummaryInsertTest(),
            new BcsTravelSummaryQueryTest(),
            new BcsVectorStatusDeleteTest(),
            new BcsVectorStatusInsertTest(),
            new BcsVectorStatusQueryTest(),
            new BcsSyncAdapterTest(),

//            new ApplicationEntityDeleteTest()
    };
}
