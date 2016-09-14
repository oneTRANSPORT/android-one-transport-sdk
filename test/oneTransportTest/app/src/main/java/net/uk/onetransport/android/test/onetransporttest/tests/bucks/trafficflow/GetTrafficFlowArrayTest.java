//package net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficflow;
//
//import com.interdigital.android.dougal.Types;
//import com.interdigital.android.dougal.resource.Resource;
//import com.interdigital.android.dougal.resource.callback.DougalCallback;
//
//import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
//import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
//import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
//
//public class GetTrafficFlowArrayTest extends OneTransportTest
//        implements TrafficFlowArrayCallback {
//
//    private DougalCallback dougalCallback;
//
//    @Override
//    public void start(RunnerTask runnerTask) throws Exception {
//        getTrafficFlowArray(runnerTask);
//    }
//
//    public void startAsync(DougalCallback dougalCallback) {
//        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS get traffic flow array");
//        this.dougalCallback = dougalCallback;
//        TrafficFlowArray.getTrafficFlowArrayAsync(((RunnerFragment) dougalCallback).getContext(), this, 1);
//    }
//
//    @Override
//    public void onTrafficFlowArrayReady(int i,
//                                        TrafficFlowArray trafficFlowArray) {
//        if (i != 1 || trafficFlowArray == null
//                || trafficFlowArray.getTrafficFlows() == null
//                || trafficFlowArray.getTrafficFlows().length == 0) {
//            dougalCallback.getResponse(null, new Throwable("Trafic flow array error"));
//        } else {
//            // Just send any valid resource.
//            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
//                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
//        }
//    }
//
//    @Override
//    public void onTrafficFlowArrayError(int i, Throwable throwable) {
//        dougalCallback.getResponse(null, new Throwable("Traffic flow array error"));
//    }
//
//    private void getTrafficFlowArray(RunnerTask runnerTask) throws Exception {
//        runnerTask.setCurrentTest("BUCKS get traffic flow array");
//        TrafficFlowArray trafficFlowArray = TrafficFlowArray
//                .getTrafficFlowArray(runnerTask.getContext());
//        if (trafficFlowArray.getTrafficFlows() == null
//                || trafficFlowArray.getTrafficFlows().length == 0) {
//            runnerTask.report("BUCKS get traffic flow array ... FAILED.", COLOUR_FAILED);
//        } else {
//            runnerTask.report("BUCKS get traffic flow array ... PASSED.", COLOUR_PASSED);
//        }
//    }
//
//}
