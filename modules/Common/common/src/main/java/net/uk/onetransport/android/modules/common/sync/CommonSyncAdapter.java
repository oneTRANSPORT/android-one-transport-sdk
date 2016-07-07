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
import net.uk.onetransport.android.modules.common.provider.CommonProvider;
import net.uk.onetransport.android.modules.common.provider.ProviderModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

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
        ArrayList<ProviderModule> providerModules = new ArrayList<>();
        CommonProvider.addModules(context, providerModules);

        for (ProviderModule module : providerModules) {
            if (!cancelled) {
                module.onPerformSync(account, extras, authority, providerClient, syncResult);
            }
        }

        // TODO    Remove this, diagnostics only.
        if (!cancelled) {
            CommonDbHelper commonDbHelper = new CommonDbHelper(context, providerModules);
            SQLiteDatabase sqLiteDatabase = commonDbHelper.getReadableDatabase();
            File dbFile = new File(sqLiteDatabase.getPath());
            File exportDb = new File("/sdcard/bucks-db");
            copy(dbFile, exportDb);
            Log.i("BucksSyncAdapter", "Copying DB.");
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

    // TODO    Remove this too.
    private void copy(File src, File dst) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
            // Transfer bytes from in to out
            byte[] buf = new byte[8192];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // TODO    ----------------------------------------

}
