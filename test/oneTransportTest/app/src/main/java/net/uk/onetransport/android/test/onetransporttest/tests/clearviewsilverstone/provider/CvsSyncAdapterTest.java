/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider;

import android.content.Context;
import android.database.Cursor;
import android.os.SystemClock;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceRetriever;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsProviderModule;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroup;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroupRetriever;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.AdapterObserver;

import java.util.ArrayList;

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
        CvsContentHelper.deleteFromProvider(context, CvsContentHelper.DATA_TYPE_TRAFFIC);
        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
                LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

        CvsProviderModule.refresh(context, true, true);
        // Now block until the adapter finishes?  Will the observer run?
        // The observer should modify adapterFinished.
        while (!adapterFinished) {
            SystemClock.sleep(1000L);
        }
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        ArrayList<Device> devices = new DeviceRetriever(context).retrieve();
        Cursor cursor = CvsContentHelper.getDeviceCursor(context);
        if (cursor != null) {
            if (cursor.getCount() != devices.size()) {
                runnerTask.report("CVS sync adapter ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        context.getContentResolver().unregisterContentObserver(adapterObserver);
        ArrayList<TrafficGroup> trafficGroups = new TrafficGroupRetriever(context).retrieve();
        int count = 0;
        for (TrafficGroup trafficGroup : trafficGroups) {
            count += trafficGroup.getTraffic().length;
        }
        cursor = CvsContentHelper.getTrafficItemCursor(context);
        if (cursor != null) {
            if (cursor.getCount() != count) {
                runnerTask.report("CVS sync adapter ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS sync adapter ... PASSED.", COLOUR_PASSED);
    }
}
