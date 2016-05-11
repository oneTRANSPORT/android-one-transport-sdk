package net.uk.onetransport.android.test.onetransporttest;

import android.os.AsyncTask;

import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
import net.uk.onetransport.android.test.onetransporttest.tests.ae.ApplicationEntityCreateTest;
import net.uk.onetransport.android.test.onetransporttest.tests.ae.ApplicationEntityDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark.GetCarParkArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.locations.GetPredefinedLocationsArrayTest;

public class RunnerTask extends AsyncTask<Void, Object[], Void>
        implements DougalCallback {

    private ReportAdapter reportAdapter;
    private OneTransportTest[] oneTransportTests = {
            new ApplicationEntityCreateTest(),
            new GetCarParkArrayTest(),
            new GetPredefinedLocationsArrayTest(),
            new ApplicationEntityDeleteTest()
    };
    private int testNum = 0;
    private String currentTest;

    public RunnerTask(ReportAdapter reportAdapter) {
        this.reportAdapter = reportAdapter;
    }

    public void report(String text, int colour) {
        publishProgress(new Object[][]{{text, colour}});
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // One synchronous test run and one asynchronous.
        reportAdapter.setNumTests(oneTransportTests.length * 2);
        publishProgress(new Object[][]{{"", 0x0}, {"Starting tests...", 0xffffff00}, {"", 0x0}});
        for (int i = 0; i < oneTransportTests.length && !isCancelled(); i++) {
            try {
                oneTransportTests[i].start(this);
            } catch (Exception exception) {
                publishProgress(new Object[][]{{currentTest + " EXCEPTION " + exception.getMessage(),
                        OneTransportTest.COLOUR_FAILED}});
                exception.printStackTrace();
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
        oneTransportTests[testNum].startAsync(this);
    }

    public void setCurrentTest(String currentTest) {
        this.currentTest = currentTest;
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
        testNum++;
        if (testNum < oneTransportTests.length) {
            oneTransportTests[testNum].startAsync(this);
        }
    }
}
