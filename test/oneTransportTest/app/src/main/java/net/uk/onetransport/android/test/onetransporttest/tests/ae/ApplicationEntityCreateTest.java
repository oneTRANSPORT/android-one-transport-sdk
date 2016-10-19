/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.uk.onetransport.android.test.onetransporttest.tests.ae;

import com.interdigital.android.dougal.resource.ApplicationEntity;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.RunnerTask;

public class ApplicationEntityCreateTest extends ApplicationEntityTest {

    private RunnerTask runnerTask;

    @Override
    public void start(RunnerTask runnerTask) throws Exception {
        this.runnerTask = runnerTask;
        createApplicationEntity();
    }

    public void startAsync(DougalCallback dougalCallback) {
        runnerTask.setCurrentTest("Create AE");
        ApplicationEntity applicationEntity = new ApplicationEntity(getAeId(), APP_NAME,
                APPLICATION_ID, BASE_URL, PATH_CREATE, false);
        applicationEntity.setExpiryTime("20240101T000000");
        applicationEntity.createAsync(TOKEN, dougalCallback);
    }

    private void createApplicationEntity() throws Exception {
        runnerTask.setCurrentTest("Create AE");
        ApplicationEntity applicationEntity = new ApplicationEntity(getAeId(), APP_NAME,
                APPLICATION_ID, BASE_URL, PATH_CREATE, false);
        applicationEntity.setExpiryTime("20240101T000000");
        applicationEntity.create(TOKEN);
        if (applicationEntity.getCreationTime() == null) {
            runnerTask.report("Create application entity ... FAILED.", COLOUR_FAILED);
        } else {
            runnerTask.report("Create application entity ... PASSED.", COLOUR_PASSED);
        }
    }
}
