package net.uk.onetransport.android.modules.common.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import net.uk.onetransport.android.modules.common.R;
import net.uk.onetransport.android.modules.common.provider.CommonDbHelper;
import net.uk.onetransport.android.modules.common.provider.OneTransportProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommonSyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String ACCOUNT = "dummyaccount";

    private Context context;
    private boolean cancelled = false;

    public CommonSyncAdapter(Context context, boolean autoInitialize) {
        this(context, autoInitialize, false);
    }

    public CommonSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        this.context = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient providerClient, SyncResult syncResult) {
        // Only execute each module once.
        // TODO    Or should we execute by URI?
        ProviderModule previousModule = null;
        for (ProviderModule module : OneTransportProvider.providerModules) {
            if (!cancelled && module != previousModule) {
                module.onPerformSync(account, extras, authority, providerClient, syncResult);
            }
            previousModule = module;
        }
    }

    @Override
    public void onSyncCanceled() {
        super.onSyncCanceled();
        cancelled = true;
    }

    @Override
    public void onSyncCanceled(Thread thread) {
        super.onSyncCanceled(thread);
        cancelled = true;
    }

    public static void refresh(Context context, Bundle bundle) {
        Account account = CommonSyncAdapter.getAccount(context);
        Bundle settingsBundle = new Bundle(bundle);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(account, context.getString(R.string.provider_authority),
                settingsBundle);
        Log.i("CommonSyncAdapter", "Common refresh called");
    }

    // We don't use an account for authentication, but the framework requires this to be configured.
    private static Account getAccount(Context context) {
        String accountType = context.getString(R.string.sync_account_type);
        Account newAccount = new Account(ACCOUNT, accountType);
        AccountManager accountManager = (AccountManager) context.getSystemService(
                Context.ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(newAccount, null, null);
        return newAccount;
    }
}
