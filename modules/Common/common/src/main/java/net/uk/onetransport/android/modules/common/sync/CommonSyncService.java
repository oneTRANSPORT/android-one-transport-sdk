package net.uk.onetransport.android.modules.common.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CommonSyncService extends Service {

    private static CommonSyncAdapter commonSyncAdapter = null;
    private static final Object syncAdapterLock = new Object();

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (syncAdapterLock) {
            if (commonSyncAdapter == null) {
                commonSyncAdapter = new CommonSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return commonSyncAdapter.getSyncAdapterBinder();
    }
}

