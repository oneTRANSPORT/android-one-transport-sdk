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

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsDeviceDeleteTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteDevices(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS device delete");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteDevices(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS device delete");
        Context context = runnerTask.getContext();
        CvsContentHelper.deleteFromProvider(context, CvsContentHelper.DATA_TYPE_DEVICE);
        Cursor cursor = CvsContentHelper.getDeviceCursor(context);
        Device[] devices = CvsContentHelper.getDevices(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 && devices.length == 0) {
                runnerTask.report("CVS device delete ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("CVS device delete ... FAILED.", COLOUR_FAILED);
    }
}
