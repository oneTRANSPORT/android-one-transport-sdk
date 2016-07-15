package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceArray;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsProviderModule;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.AdapterObserver;

public class CvsSyncAdapterTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        startSync(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS sync adapter");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void startSync(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS sync adapter");
        Context context = runnerTask.getContext();
        // The sync adapter should do this anyway, but just setting the pre-condition for the test.
        CvsContentHelper.deleteFromProvider(context, CvsContentHelper.DATA_TYPE_DEVICE);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        CvsProviderModule.refresh(context, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        DeviceArray deviceArray = DeviceArray.getDeviceArray(context);
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        Cursor cursor = CvsContentHelper.getDevices(context);
        if (cursor != null) {
            if (cursor.getCount() == deviceArray.getDevices().length) {
                runnerTask.report("CVS sync adapter ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS sync adapter ... FAILED.", COLOUR_FAILED);
    }
}
