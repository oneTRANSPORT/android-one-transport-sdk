package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.roadworks.Roadworks;
import net.uk.onetransport.android.county.bucks.roadworks.RoadworksRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class BucksRoadworksInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertRoadWorks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("BUCKS road works insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertRoadWorks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("BUCKS road works insert");
        Context context = runnerTask.getContext();
        BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_ROADWORKS);
        Roadworks[] roadWorkses = new RoadworksRetriever(context).retrieve();
        if (roadWorkses == null || roadWorkses.length == 0) {
            runnerTask.report("BUCKS road works insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        BucksContentHelper.insertIntoProvider(context, roadWorkses);
        BucksContentHelper.insertIntoProvider(context, roadWorkses);
        Roadworks[] roadWorkses1 = BucksContentHelper.getRoadworks(context);
        if (roadWorkses.length == roadWorkses1.length) {
            runnerTask.report("BUCKS road works insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("BUCKS road works insert ... FAILED.", COLOUR_FAILED);
    }
}
