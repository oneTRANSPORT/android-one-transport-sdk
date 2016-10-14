package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.northants.provider.NorthantsContentHelper;
import net.uk.onetransport.android.county.northants.roadworks.Roadworks;
import net.uk.onetransport.android.county.northants.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class NorthantsRoadworksInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertRoadworks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("NORTHANTS road works insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertRoadworks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("NORTHANTS road works insert");
        Context context = runnerTask.getContext();
        NorthantsContentHelper.deleteFromProvider(context, NorthantsContentHelper.DATA_TYPE_ROADWORKS);
        Roadworks[] roadworkses = new RoadworksRetriever(context).retrieve();
        if (roadworkses == null || roadworkses.length == 0) {
            runnerTask.report("NORTHANTS road works insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        NorthantsContentHelper.insertIntoProvider(context, roadworkses);
        NorthantsContentHelper.insertIntoProvider(context, roadworkses);
        Roadworks[] roadworkses1 = NorthantsContentHelper.getRoadworks(context);
        if (roadworkses.length == roadworkses1.length) {
            runnerTask.report("NORTHANTS road works insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("NORTHANTS road works insert ... FAILED.", COLOUR_FAILED);
    }
}
