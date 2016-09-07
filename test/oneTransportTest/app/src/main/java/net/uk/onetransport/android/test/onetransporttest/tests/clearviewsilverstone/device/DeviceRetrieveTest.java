package net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.device;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.clearviewsilverstone.device.Device;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceRetriever;
import net.uk.onetransport.android.modules.clearviewsilverstone.device.DeviceRetrieverLoader;
import net.uk.onetransport.android.modules.clearviewsilverstone.generic.RetrieverResult;
import net.uk.onetransport.android.test.onetransporttest.RunnerFragment;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;
import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

import java.util.ArrayList;

public class DeviceRetrieveTest extends OneTransportTest
        implements LoaderManager.LoaderCallbacks<RetrieverResult<Device>> {

    private DougalCallback dougalCallback;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        getDeviceArray(runnerTask);
    }

    @Override
    public void startAsync(DougalCallback dougalCallback) {
        ((RunnerFragment) dougalCallback).setCurrentTest("CVS get device array");
        this.dougalCallback = dougalCallback;
        ((Fragment) dougalCallback).getLoaderManager().initLoader(
                ((RunnerFragment) dougalCallback).getUniqueLoaderId(), null, this);
    }

    @Override
    public Loader<RetrieverResult<Device>> onCreateLoader(int id, Bundle args) {
        return new DeviceRetrieverLoader(((RunnerFragment) dougalCallback).getContext());
    }

    @Override
    public void onLoadFinished(Loader<RetrieverResult<Device>> loader, RetrieverResult<Device> data) {
        if (data.getExceptions().size() > 0 || data.getTs().size() == 0) {
            dougalCallback.getResponse(null, new Throwable("Device array error"));
        } else {
            // Just send any valid resource.
            dougalCallback.getResponse(new Resource("aeid", "resourceId", "resourceName",
                    Types.RESOURCE_TYPE_APPLICATION_ENTITY, "baseUrl", "path"), null);
        }
    }

    @Override
    public void onLoaderReset(Loader<RetrieverResult<Device>> loader) {
        // Nothing needs to be done.
    }

    private void getDeviceArray(RunnerTask runnerTask) throws Exception {
        runnerTask.setCurrentTest("CVS get device array");
        DeviceRetriever deviceRetriever = new DeviceRetriever(runnerTask.getContext());
        ArrayList<Device> devices = deviceRetriever.retrieve();
        if (devices == null || devices.size() == 0) {
            runnerTask.report("CVS get device array ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("CVS get device array ... PASSED.", COLOUR_PASSED);
        }
    }
}
