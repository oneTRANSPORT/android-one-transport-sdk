//package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;
//
//import android.database.Cursor;
//
//import com.interdigital.android.dougal.resource.callback.DougalCallback;
//
//import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
//import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
//import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
//import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
//
//public class BcsVectorStatusQueryTest extends OneTransportTest {
//
//    @Override
//    public void start(RunnerTask runnerTask) throws Exception {
//        vectorStatusQuery(runnerTask);
//    }
//
//    public void startAsync(DougalCallback dougalCallback) {
//        ((RunnerFragment) dougalCallback).setCurrentTest("BCS vector status query");
//        dougalCallback.getResponse(null, new Exception("Not implemented"));
//    }
//
//    private void vectorStatusQuery(RunnerTask runnerTask) throws Exception {
//        runnerTask.setCurrentTest("BCS vector status query");
//        Cursor cursor = BcsContentHelper.getVectorStatuses(runnerTask.getContext());
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                runnerTask.report("BCS vector status query ... PASSED.", COLOUR_PASSED);
//                cursor.close();
//                return;
//            }
//            cursor.close();
//        }
//        runnerTask.report("BCS vector status query ... FAILED.", COLOUR_FAILED);
//    }
//}
