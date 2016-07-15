package net.uk.onetransport.android.test.onetransporttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
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
    private TestList testList = new TestList();
    private int testNum = 0;
    private String currentTest;
    private LoaderManager loaderManager;
    private int loaderId = 0;

    public RunnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        reportAdapter = new ReportAdapter(getContext(), getResources());
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
//        getLoaderManager().destroyLoader(loaderId);
        testList.oneTransportTests[testNum] = null;
        testNum++;
        if (testNum < testList.oneTransportTests.length) {
            testList.oneTransportTests[testNum].startAsync(this);
        }
    }

    public void setCurrentTest(String currentTest) {
        this.currentTest = currentTest;
    }

    public LoaderManager getLoaderManager() {
        if (loaderManager == null) {
            loaderManager = getLoaderManager();
        }
        return loaderManager;
    }

    public int getUniqueLoaderId() {
        return loaderId++;
    }

    private void report(String text, int colour) {
        reportAdapter.addChunk(text, colour);
    }

}
