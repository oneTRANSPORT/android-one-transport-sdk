package net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public class AdapterObserver extends ContentObserver {

    private BucksSyncAdapterTest bucksSyncAdapterTest;

    public AdapterObserver(Handler handler, BucksSyncAdapterTest bucksSyncAdapterTest) {
        super(handler);
        this.bucksSyncAdapterTest = bucksSyncAdapterTest;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        bucksSyncAdapterTest.adapterFinished = true;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        bucksSyncAdapterTest.adapterFinished = true;
    }
}
