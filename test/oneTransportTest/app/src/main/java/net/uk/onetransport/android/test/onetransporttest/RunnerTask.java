package net.uk.onetransport.android.test.onetransporttest;

import android.content.Context;
import android.os.AsyncTask;

import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch.GetSketchArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.travelsummary.GetTravelSummaryArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector.GetVectorStatusArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark.GetCarParkArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadWorksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVmsBoxQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVmsQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.roadworks.GetRoadWorksArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficflow.GetTrafficFlowArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.vms.GetVariableMessageSignArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.device.GetDeviceArrayTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.traffic.GetTrafficGroupArrayTest;

public class RunnerTask extends AsyncTask<Void, Object[], Void>
        implements DougalCallback {

    private Context context;
    private ReportAdapter reportAdapter;
    private OneTransportTest[] oneTransportTests = {
//            new ApplicationEntityCreateTest(),
            new GetCarParkArrayTest(),
            new GetVariableMessageSignArrayTest(),
            new GetTrafficFlowArrayTest(),
            new GetRoadWorksArrayTest(),
            new BucksCarParkDeleteTest(),
            new BucksVariableMessageSignDeleteTest(),
            new BucksTrafficFlowDeleteTest(),
            new BucksRoadWorksDeleteTest(),
            new BucksCarParkInsertTest(),
            new BucksVariableMessageSignInsertTest(),
            new BucksTrafficFlowInsertTest(),
            new BucksRoadWorksInsertTest(),
            new BucksVmsQueryTest(),
            new BucksTrafficFlowQueryTest(),
            new BucksCarParkBoxQueryTest(),
            new BucksVmsBoxQueryTest(),
            new BucksTrafficFlowBoxQueryTest(),
            new BucksRoadWorksBoxQueryTest(),
            new BucksSyncAdapterTest(),
            // Clearview Silverstone.
            new GetDeviceArrayTest(),
            new GetTrafficGroupArrayTest(),
            new CvsDeviceDeleteTest(),
            new CvsTrafficGroupDeleteTest(),
            new CvsDeviceInsertTest(),
            new CvsTrafficGroupInsertTest(),
            new CvsDeviceQueryTest(),
            new CvsTrafficGroupQueryTest(),
            new CvsSyncAdapterTest(),
            // BitCarrier Silverstone
            // TODO    Turns out this data has expired from the CSE.  Need to add manually
            // TODO    from Owen's previous copy.
//            new GetNodeArrayTest(),
//            new GetVectorArrayTest(),
//            new GetMetaVectorArrayTest(),
//            new GetRouteArrayTest(),
//            new GetTravelTimesArrayTest,
//            new GetSketchArrayTest(),
//            new GetCityArrayTest(),
//            new GetZoneArrayTest(),GetVectorArrayTest
            // This data is still available.
            new GetTravelSummaryArrayTest(),
            new GetVectorStatusArrayTest(),
            new GetSketchArrayTest(),

//            new ApplicationEntityDeleteTest()
    };
    private int testNum = 0;
    private String currentTest;

    public RunnerTask(Context context, ReportAdapter reportAdapter) {
        this.context = context;
        this.reportAdapter = reportAdapter;
    }

    public void report(String text, int colour) {
        publishProgress(new Object[][]{{text, colour}});
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Create an installation id if needed.
        net.uk.onetransport.android.county.bucks.authentication.CredentialHelper
                .initialiseCredentials(context, OneTransportTest.USER_NAME,
                        OneTransportTest.PASSWORD, "installation-id");
        net.uk.onetransport.android.modules.clearviewsilverstone.authentication.CredentialHelper
                .initialiseCredentials(context, OneTransportTest.USER_NAME,
                        OneTransportTest.PASSWORD, "installation-id");
        net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper
                .initialiseCredentials(context, OneTransportTest.USER_NAME,
                        OneTransportTest.PASSWORD, "installation-id");
        // One synchronous test run and one asynchronous.
        reportAdapter.setNumTests(oneTransportTests.length * 2);
        publishProgress(new Object[][]{{"", 0x0}, {"Starting tests...", 0xffffff00}, {"", 0x0}});
        for (int i = 0; i < oneTransportTests.length && !isCancelled(); i++) {
            try {
                oneTransportTests[i].setContext(context);
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

    public Context getContext() {
        return context;
    }
}
