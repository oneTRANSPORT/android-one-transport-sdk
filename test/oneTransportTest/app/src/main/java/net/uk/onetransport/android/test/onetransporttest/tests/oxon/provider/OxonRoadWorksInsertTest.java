package net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.oxon.provider.OxonContentHelper;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorks;
import net.uk.onetransport.android.county.oxon.roadworks.RoadWorksRetriever;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class OxonRoadWorksInsertTest extends OneTransportTest {

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        insertRoadWorks(runnerTask);
    }

    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("OXON road works insert");
        dougalCallback.getResponse(null, new Exception("Not implemented"));
    }

    private void insertRoadWorks(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("OXON road works insert");
        Context context = runnerTask.getContext();
        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_ROAD_WORKS);
        RoadWorks[] roadWorkses = new RoadWorksRetriever(context).retrieve();
        if (roadWorkses == null || roadWorkses.length == 0) {
            runnerTask.report("OXON road works insert ... FAILED.", COLOUR_FAILED);
            return;
        }
        OxonContentHelper.insertIntoProvider(context, roadWorkses);
        OxonContentHelper.insertIntoProvider(context, roadWorkses);
        RoadWorks[] roadWorkses1 = OxonContentHelper.getRoadWorks(context);
        if (roadWorkses.length == roadWorkses1.length) {
            runnerTask.report("OXON road works insert ... PASSED.", COLOUR_PASSED);
            return;
        }
        runnerTask.report("OXON road works insert ... FAILED.", COLOUR_FAILED);
    }
}
