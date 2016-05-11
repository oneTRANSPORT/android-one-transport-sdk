package net.uk.onetransport.android.test.onetransporttest.tests;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.RunnerTask;

public interface OneTransportTest {

    String USER_NAME = "pthomas";
    String PASSWORD = "EKFYGUCC";
    //    String USER_NAME = "android";
//    String PASSWORD = "DompAdtem9";
    String CSE_BASE = "cse-02.onetransport.uk.net";
    String CSE_ID = "ONET-CSE-02";
    String CSE_NAME = "ONETCSE02";

    String BASE_URL = "https://" + CSE_BASE + "/";

    int COLOUR_PASSED = 0xff80ff80;
    int COLOUR_NOT_IMPLEMENTED = 0xffcc80ff;
    int COLOUR_FAILED = 0xffff8080;

    String AE_ID = "C-ONETRANSPORT-TEST";
    String APP_NAME = "C-ONETRANSPORT-TEST-APP";

    void start(RunnerTask runnerTask) throws Exception;

    void startAsync(DougalCallback dougalCallback);
}