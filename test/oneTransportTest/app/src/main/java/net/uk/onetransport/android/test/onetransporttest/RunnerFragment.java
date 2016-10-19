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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
import net.uk.onetransport.android.test.onetransporttest.tests.TestList;

public class RunnerFragment extends Fragment implements DougalCallback {

    private ReportAdapter reportAdapter;
    private TestList testList;
    private int testNum;
    private String currentTest;
    private int loaderId = 0;

    public RunnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        reportAdapter = new ReportAdapter(getContext(), getResources());
        testList = new TestList();
        new RunnerTask(getContext().getApplicationContext(), reportAdapter, this, testList)
                .execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runner, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(reportAdapter);
        return view;
    }

    public void startAsync() {
        testNum = 0;
        testList = new TestList();
        testList.oneTransportTests[testNum].startAsync(this);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        reportAdapter.incNumCompleted();
        if (resource != null) {
            report(currentTest + " async task PASSED.", OneTransportTest.COLOUR_PASSED);
        } else if (throwable != null) {
            if (throwable.getMessage().equals("Not implemented")) {
                report(currentTest + " async task not implemented.",
                        OneTransportTest.COLOUR_NOT_IMPLEMENTED);
            } else {
                report(currentTest + " async task EXCEPTION " + throwable.getMessage(),
                        OneTransportTest.COLOUR_FAILED);
            }
        } else {
            report(currentTest + " async task PASSED.", OneTransportTest.COLOUR_PASSED);
        }
        if (testNum < testList.oneTransportTests.length) {
            testList.oneTransportTests[testNum] = null;
        }
        testNum++;
        if (testNum < testList.oneTransportTests.length) {
            testList.oneTransportTests[testNum].startAsync(this);
        }
    }

    public void setCurrentTest(String currentTest) {
        this.currentTest = currentTest;
    }

    public int getUniqueLoaderId() {
        return loaderId++;
    }

    private void report(String text, int colour) {
        reportAdapter.addChunk(text, colour);
    }

}
