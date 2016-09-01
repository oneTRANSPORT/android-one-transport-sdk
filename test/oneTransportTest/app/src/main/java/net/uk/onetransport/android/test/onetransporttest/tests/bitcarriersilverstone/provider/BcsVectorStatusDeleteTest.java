//package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;
//
//import android.content.Context;
//import android.database.Cursor;
//
//import com.interdigital.android.dougal.resource.callback.DougalCallback;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
//import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
//import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
//import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
//
//public class BcsVectorStatusDeleteTest extends OneTransportTest {
//
//    @Override
//    public void start(RunnerTask runnerTask) throws Exception {
//        deleteVectorStatuses(runnerTask);
//    }
//
//    public void startAsync(DougalCallback dougalCallback) {
//        ((RunnerFragment) dougalCallback).setCurrentTest("BCS vector status delete");
//        dougalCallback.getResponse(null, new Exception("Not implemented"));
//    }
//
//    private void deleteVectorStatuses(RunnerTask runnerTask) throws Exception {
//        runnerTask.setCurrentTest("BCS vector status delete");
//        Context context = runnerTask.getContext();
//        BcsContentHelper.deleteFromProvider(context, BcsContentHelper.DATA_TYPE_VECTOR_STATUS);
//        Cursor cursor = BcsContentHelper.getVectorStatuses(context);
//        if (cursor != null) {
//            if (cursor.getCount() == 0) {
//                runnerTask.report("BCS vector status delete ... PASSED.", COLOUR_PASSED);
//                cursor.close();
//                return;
//            }
//            cursor.close();
//        }
//        runnerTask.report("BCS vector status delete ... FAILED.", COLOUR_FAILED);
//    }
//}
