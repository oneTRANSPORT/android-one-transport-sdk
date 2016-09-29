package net.uk.onetransport.android.test.onetransporttest.tests.northants.provider;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class AdapterObserver extends ContentObserver {

    private OneTransportTest oneTransportTest;

    public AdapterObserver(Handler handler, OneTransportTest oneTransportTest) {
        super(handler);
        this.oneTransportTest = oneTransportTest;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        oneTransportTest.adapterFinished = true;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        oneTransportTest.adapterFinished = true;
    }
}
