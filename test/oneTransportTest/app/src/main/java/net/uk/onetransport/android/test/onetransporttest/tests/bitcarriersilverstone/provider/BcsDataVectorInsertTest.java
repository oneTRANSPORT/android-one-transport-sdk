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

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.VectorRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;


public class BcsDataVectorInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertDataVectors(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS data vector insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertDataVectors(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS data vector insert");
        Context context = runnerTask.getContext();
        ArrayList<Vector> vectors = new VectorRetriever(context).retrieve();
        if (vectors == null || vectors.size() == 0) {
            runnerTask.report("BCS data vector insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BcsContentHelper.insertDataVectorsIntoProvider(context, vectors);
        Cursor cursor = BcsContentHelper.getDataVectorCursor(context);
        Vector[] vectors1 = BcsContentHelper.getDataVectors(context);
        if (cursor != null) {
            if (cursor.getCount() > 0 || cursor.getCount() == vectors1.length) {
                runnerTask.report("BCS data vector insert ... PASSED.", COLOUR_PASSED);
                cursor.close();
                return;
            }
            cursor.close();
        }
        runnerTask.report("BCS data vector insert ... FAILED.", COLOUR_FAILED);
    }
}
