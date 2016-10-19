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
package net.uk.onetransport.android.test.onetransporttest.tests;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.RunnerTask;

public abstract class OneTransportTest {

    public static final String CLIENT_AE_ID = "C-Y249T25lVHJhbnNwb3J0VGVzdCxvdT1yb290";
    public static final String TOKEN = "010FYzsgPMbnxKRn";
    public static final String CSE_BASE = "cse-01.onetransport.uk.net";
    public static final String CSE_NAME = "ONETCSE01";

    public static final String BASE_URL = "https://" + CSE_BASE + "/";

    public static final int COLOUR_PASSED = 0xff80ff80;
    public static final int COLOUR_NOT_IMPLEMENTED = 0xffcc80ff;
    public static final int COLOUR_FAILED = 0xffff8080;

    //    String AE_ID = "C-ONETRANSPORT-TEST";
    public static final String APP_NAME = "C-ONETRANSPORT-TEST-APP";

//    String NAME = "ONETRANSPORT-TEST";
//    String APPLICATION_ID = "C-ONETRANSPORT-TEST-APP-ID";
//    String BASE_URL_CSE = BASE_URL + CSE_NAME + "/";

    public boolean adapterFinished = false;

    private Context context;

    public abstract void start(RunnerTask runnerTask) throws Exception;

    public abstract void startAsync(DougalCallback dougalCallback);

    public String getAeId() {
        return CLIENT_AE_ID;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}