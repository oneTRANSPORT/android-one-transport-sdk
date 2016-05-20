package net.uk.onetransport.android.county.bucks.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String ACCOUNT = "dummyaccount";

    private ContentResolver contentResolver; // TODO Do we need this?

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        contentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        contentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient providerClient, SyncResult syncResult) {
        if (authority.equals(BucksProvider.AUTHORITY)) { // TODO Has this been initialised?  Maybe getAuthority()?
            // TODO Transfer data here.
            Log.i("SyncAdapter", "Transferring data ...");
        }
    }

    // We don't use an account for authentication, but the framework requires this to be configured.
    // Should be called in the starting activity or application on create.
    public static Account getAccount(Context context) {
        String accountType = context.getString(R.string.sync_account_type);
        Account newAccount = new Account(ACCOUNT, accountType);
        AccountManager accountManager = (AccountManager) context.getSystemService(
                Context.ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(newAccount, null, null);
        return newAccount;
    }
}
