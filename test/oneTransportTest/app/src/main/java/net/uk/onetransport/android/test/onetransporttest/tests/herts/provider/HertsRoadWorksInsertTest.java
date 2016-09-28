package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.herts.provider.HertsContentHelper;
import net.uk.onetransport.android.county.herts.roadworks.RoadWorks;
import net.uk.onetransport.android.county.herts.roadworks.RoadWorksRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class HertsRoadWorksInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertRoadWorks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("HERTS road works insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertRoadWorks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("HERTS road works insert");
        Context context = runnerTask.getContext();
        HertsContentHelper.deleteFromProvider(context, HertsContentHelper.DATA_TYPE_ROAD_WORKS);
        RoadWorks[] roadWorkses = new RoadWorksRetriever(context).retrieve();
        if (roadWorkses == null || roadWorkses.length == 0) {
            runnerTask.report("HERTS road works insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        HertsContentHelper.insertIntoProvider(context, roadWorkses);
        HertsContentHelper.insertIntoProvider(context, roadWorkses);
        RoadWorks[] roadWorkses1 = HertsContentHelper.getRoadWorks(context);
        if (roadWorkses.length == roadWorkses1.length) {
            runnerTask.report("HERTS road works insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("HERTS road works insert ... FAILED.", COLOUR_FAILED);
    }
}
