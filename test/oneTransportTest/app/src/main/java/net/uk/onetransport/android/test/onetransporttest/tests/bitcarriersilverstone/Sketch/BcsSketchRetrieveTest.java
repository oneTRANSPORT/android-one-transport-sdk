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
package net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.SketchRetriever;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.SketchRetrieverLoader;
import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class BcsSketchRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Sketch>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getSketchArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BCS get sketch array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<Sketch>> onCreateLoader(int id, Bundle args) {
        return new SketchRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<Sketch>> loader, RetrieverResult<Sketch> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Sketch array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<Sketch>> loader) {
        // Nothing needs to be done.
    }

    private void getSketchArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BCS get sketch array");
        SketchRetriever sketchRetriever = new SketchRetriever(runnerTask.getContext());
        ArrayList<Sketch> sketches = sketchRetriever.retrieve();
        if (sketches == null || sketches.size() == 0) {
            Log.i("BCSSketch", "Error no data in feed");
            runnerTask.report("BCS get sketch array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("BCS get sketch array ... PASSED.", COLOUR_PASSED);
        }
    }

}
