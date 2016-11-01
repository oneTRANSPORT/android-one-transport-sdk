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
package net.uk.onetransport.android.test.onetransporttest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
import net.uk.onetransport.android.test.onetransporttest.tests.TestList;

public class RunnerTask extends AsyncTask<Void, Object[], Void> {

    private Context context;
    private ReportAdapter reportAdapter;
    private String currentTest;
    private RunnerFragment fragment;
    private TestList testList;
    private long testStartTime;

    public RunnerTask(Context context, ReportAdapter reportAdapter, RunnerFragment fragment,
                      TestList testList) {
        this.context = context;
        this.reportAdapter = reportAdapter;
        this.fragment = fragment;
        this.testList = testList;
    }

    public void report(String text, int colour) {
        publishProgress(new Object[][]{{text, colour}});
        Log.i("RunnerTask", currentTest + " Completion time "
                + ((SystemClock.elapsedRealtime() - testStartTime) / 1000));
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Create an installation id if needed.
        String clientAeId = getContext().getString(R.string.client_ae_id);
        String token = getContext().getString(R.string.token);
        net.uk.onetransport.android.county.bucks.authentication.CredentialHelper
                .initialiseCredentials(context, clientAeId, token, "installation-id");
        net.uk.onetransport.android.county.herts.authentication.CredentialHelper
                .initialiseCredentials(context, clientAeId, token, "installation-id");
        net.uk.onetransport.android.county.northants.authentication.CredentialHelper
                .initialiseCredentials(context, clientAeId, token, "installation-id");
        net.uk.onetransport.android.county.oxon.authentication.CredentialHelper
                .initialiseCredentials(context, clientAeId, token, "installation-id");
        net.uk.onetransport.android.modules.clearviewsilverstone.authentication.CredentialHelper
                .initialiseCredentials(context, clientAeId, token, "installation-id");
        net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper
                .initialiseCredentials(context, clientAeId, token, "installation-id");
        // One synchronous test run and one asynchronous.
        reportAdapter.setNumTests(testList.oneTransportTests.length * 2);
        publishProgress(new Object[][]{{"", 0x0}, {"Starting tests...", 0xffffff00}, {"", 0x0}});
        for (int i = 0; i < testList.oneTransportTests.length && !isCancelled(); i++) {
            try {
                testStartTime = SystemClock.elapsedRealtime();
                testList.oneTransportTests[i].setContext(context);
                testList.oneTransportTests[i].start(this);
            } catch (Exception exception) {
                publishProgress(new Object[][]{{currentTest + " EXCEPTION " + exception.getMessage(),
                        OneTransportTest.COLOUR_FAILED}});
                exception.printStackTrace();
                Log.i("RunnerTask", currentTest + " Exception time "
                        + ((SystemClock.elapsedRealtime() - testStartTime) / 1000));
            }
            reportAdapter.incNumCompleted();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[]... chunks) {
        for (int i = 0; i < chunks.length; i++) {
            reportAdapter.addChunk((String) chunks[i][ReportAdapter.TEXT],
                    (Integer) chunks[i][ReportAdapter.COLOUR]);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Asynchronous requests.
        fragment.startAsync();
    }

    public void setCurrentTest(String currentTest) {
        this.currentTest = currentTest;
    }

    public Context getContext() {
        return context;
    }

}
