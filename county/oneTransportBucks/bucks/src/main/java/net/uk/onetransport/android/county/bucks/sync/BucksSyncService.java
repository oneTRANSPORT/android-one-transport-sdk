package net.uk.onetransport.android.county.bucks.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BucksSyncService extends Service {

    private static BucksSyncAdapter bucksSyncAdapter = null;
    private static final Object syncAdapterLock = new Object();

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (syncAdapterLock) {
            if (bucksSyncAdapter == null) {
                bucksSyncAdapter = new BucksSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return bucksSyncAdapter.getSyncAdapterBinder();
    }
}
