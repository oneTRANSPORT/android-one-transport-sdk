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
package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider;

import android.content.Context;
import android.database.Cursor;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BcsTravelSummaryDeleteBeforeTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        deleteBeforeTravelSummaries(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS travel summary delete before");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void deleteBeforeTravelSummaries(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS travel summary delete before");
        Context context = runnerTask.getContext();
        Cursor cursor = BcsContentHelper.getTravelSummaryCursor(context);
        TravelSummary[] travelSummaries = BcsContentHelper.getTravelSummaries(context);
        int creationTime = 0;
        if (cursor != null) {
            if (cursor.getCount() == 0 || travelSummaries.length == 0) {
                runnerTask.report("BCS travel summary delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            if (cursor.moveToFirst()) {
                creationTime = cursor.getInt(cursor.getColumnIndex(BcsContract
                        .BitCarrierSilverstoneTravelSummary.COLUMN_CREATION_TIME));
            }
            cursor.close();
        }

        BcsContentHelper.deleteFromProviderBeforeTime(context,
                BcsContentHelper.DATA_TYPE_TRAVEL_SUMMARY, creationTime);
        cursor = BcsContentHelper.getTravelSummaryCursor(context);
        travelSummaries = BcsContentHelper.getTravelSummaries(context);
        if (cursor != null) {
            if (cursor.getCount() == 0 || travelSummaries.length == 0) {
                runnerTask.report("BCS travel summary delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("BCS travel summary delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.deleteFromProviderBeforeTime(context,
                BcsContentHelper.DATA_TYPE_TRAVEL_SUMMARY, System.currentTimeMillis() / 1000L);
        cursor = BcsContentHelper.getTravelSummaryCursor(context);
        travelSummaries = BcsContentHelper.getTravelSummaries(context);
        if (cursor != null) {
            if (cursor.getCount() > 0 || travelSummaries.length>0) {
                runnerTask.report("BCS travel summary delete before ... FAILED.", COLOUR_FAILED);
                cursor.close();
                return;
            }
            cursor.close();
        } else {
            runnerTask.report("BCS travel summary delete before ... FAILED.", COLOUR_FAILED);
            return;
        }
        runnerTask.report("BCS travel summary delete before ... PASSED.", COLOUR_PASSED);
    }
}
