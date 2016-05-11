package net.uk.onetransport.android.test.onetransporttest.tests.ae;

import com.interdigital.android.dougal.resource.ApplicationEntity;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.RunnerTask;

public class ApplicationEntityCreateTest implements ApplicationEntityTest {
    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        createApplicationEntity();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("Create AE");
        ApplicationEntity applicationEntity = new ApplicationEntity(AE_ID, APP_NAME, APPLICATION_ID,
                BASE_URL, PATH_CREATE, false);
        applicationEntity.setExpiryTime("20240101T000000");
        applicationEntity.createAsync(USER_NAME, PASSWORD, dougalCallback);
    }

    private void createApplicationEntity() throws Exception {
        runnerTask.setCurrentTest("Create AE");
        ApplicationEntity applicationEntity = new ApplicationEntity(AE_ID, APP_NAME, APPLICATION_ID,
                BASE_URL, PATH_CREATE, false);
        applicationEntity.setExpiryTime("20240101T000000");
        applicationEntity.create(USER_NAME, PASSWORD);
        if (applicationEntity.getCreationTime() == null) {
            runnerTask.report("Create application entity ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("Create application entity ... PASSED.", COLOUR_PASSED);
        }
    }
}
