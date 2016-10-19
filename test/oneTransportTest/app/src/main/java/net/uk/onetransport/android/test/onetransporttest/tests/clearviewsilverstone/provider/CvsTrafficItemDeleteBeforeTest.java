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

import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper;
import net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContract;
import net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficItem;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class CvsTrafficItemDeleteBeforeTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteBeforeTrafficItems(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS traffic item delete before");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteBeforeTrafficItems(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS traffic item delete before");
        Context context = runnerTask.getContext();
        Cursor cursor = CvsContentHelper.getTrafficItemCursor(context);
        TrafficItem[] trafficItems = CvsContentHelper.getTrafficItems(context);
        int creationTime = 0;
        if (cursor != null) {
            if (cursor.getCount() == 0 || trafficItems.length == 0) {
                runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            if (cursor.moveToFirst()) {
                creationTime = cursor.getInt(cursor.getColumnIndex(CvsContract
                        .ClearviewSilverstoneTraffic.COLUMN_CREATION_TIME));
            }
            cursor.close();
        }

        CvsContentHelper.deleteFromProviderBeforeTime(context,
                CvsContentHelper.DATA_TYPE_TRAFFIC, creationTime);
        cursor = CvsContentHelper.getTrafficItemCursor(context);
        trafficItems = CvsContentHelper.getTrafficItems(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 || trafficItems.length == 0) {
                runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        CvsContentHelper.deleteFromProviderBeforeTime(context,
                CvsContentHelper.DATA_TYPE_TRAFFIC, System.currentTimeMillis() / 1000L);
        cursor = CvsContentHelper.getTrafficItemCursor(context);
        trafficItems = CvsContentHelper.getTrafficItems(context);
        if (cursor != null) {
            if (cursor.getCount() > 0 || trafficItems.length > 0) {
                runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("CVS traffic item delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("CVS traffic item delete before ... PASSED.", COLOUR_PASSED);
    }
}
