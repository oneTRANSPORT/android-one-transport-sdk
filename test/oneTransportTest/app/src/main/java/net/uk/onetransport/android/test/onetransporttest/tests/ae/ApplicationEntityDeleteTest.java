package net.uk.onetransport.android.test.onetransporttest.tests.ae;

import com.interdigital.android.dougal.resource.ApplicationEntity;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.RunnerTask;

public class ApplicationEntityDeleteTest extends ApplicationEntityTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        deleteApplicationEntity();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("Delete AE");
        ApplicationEntity applicationEntity = getApplicationEntity();
        applicationEntity.deleteAsync(USER_NAME, PASSWORD, dougalCallback);
    }

    private void deleteApplicationEntity() throws Exception {
        runnerTask.setCurrentTest("Delete AE");
        ApplicationEntity applicationEntity = getApplicationEntity();
        applicationEntity.delete(USER_NAME, PASSWORD);
        runnerTask.report("Delete application entity ... PASSED", COLOUR_PASSED);
    }

    private ApplicationEntity getApplicationEntity() {
        return new ApplicationEntity(getAeId(), APP_NAME, APPLICATION_ID, BASE_URL,
                PATH_CREATE, false);
    }
}
