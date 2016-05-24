package net.uk.onetransport.android.test.onetransporttest.tests;

import android.content.Context;

import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.test.onetransporttest.R;
import net.uk.onetransport.android.test.onetransporttest.RunnerTask;

public abstract class OneTransportTest {

    public static final String USER_NAME = "pthomas";
    public static final String PASSWORD = "EKFYGUCC";
    //    String USER_NAME = "android";
//    String PASSWORD = "DompAdtem9";
    public static final String CSE_BASE = "cse-05.onetransport.uk.net";
    //    String CSE_ID = "ONET-CSE-05";
    public static final String CSE_NAME = "ONETCSE05";

    public static final String BASE_URL = "https://" + CSE_BASE + "/";

    public static final int COLOUR_PASSED = 0xff80ff80;
    public static final int COLOUR_NOT_IMPLEMENTED = 0xffcc80ff;
    public static final int COLOUR_FAILED = 0xffff8080;

    //    String AE_ID = "C-ONETRANSPORT-TEST";
    public static final String APP_NAME = "C-ONETRANSPORT-TEST-APP";

//    String NAME = "ONETRANSPORT-TEST";
//    String APPLICATION_ID = "C-ONETRANSPORT-TEST-APP-ID";
//    String BASE_URL_CSE = BASE_URL + CSE_NAME + "/";

    private Context context;

    public abstract void start(RunnerTask runnerTask) throws Exception;

    public abstract void startAsync(DougalCallback dougalCallback);

    public String getAeId() {
        return context.getString(R.string.bucks_ae_id);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}