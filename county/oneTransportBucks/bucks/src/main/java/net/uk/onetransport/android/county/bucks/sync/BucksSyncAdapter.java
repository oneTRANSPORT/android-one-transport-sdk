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
import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.county.bucks.locations.PredefinedVmsLocationArray;
import net.uk.onetransport.android.county.bucks.locations.SegmentLocationArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowArray;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignArray;

public class BucksSyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String ACCOUNT = "dummyaccount";

    private Context context;
//    private String cseBaseUrl;
    // TODO Need to find a proper authentication solution.
//    private String userName;
//    private String password;

    public BucksSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        initialise(context);
    }

    public BucksSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        initialise(context);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient providerClient, SyncResult syncResult) {
        if (authority.equals(BucksProvider.AUTHORITY)) {
            Log.i("SyncAdapter", "Transferring data ...");
//
//            // Link segments.
//            try {
//                SegmentLocationArray segmentLocationArray = SegmentLocationArray
//                        .getSegmentLocationArray(context, aeId, userName, password);
//                BucksContentHelper.deleteFromProvider(context,
//                        BucksContentHelper.DATA_TYPE_SEGMENT_LOCATION);
//                BucksContentHelper.insertIntoProvider(context,
//                        segmentLocationArray.getSegmentLocations());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            // Variable message sign locations.
//            try {
//                PredefinedVmsLocationArray predefinedVmsLocationArray = PredefinedVmsLocationArray
//                        .getPredefinedVmsLocationArray(context, aeId, userName, password);
//                BucksContentHelper.deleteFromProvider(context,
//                        BucksContentHelper.DATA_TYPE_VMS_LOCATION);
//                BucksContentHelper.insertIntoProvider(context,
//                        predefinedVmsLocationArray.getPredefinedVmsLocations());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            // Car parks.
//            try {
//                CarParkArray carParkArray = CarParkArray.getCarParkArray(context, aeId, userName, password);
//                BucksContentHelper.deleteFromProvider(context,
//                        BucksContentHelper.DATA_TYPE_CAR_PARK);
//                BucksContentHelper.insertIntoProvider(context, carParkArray.getCarParks());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            // Traffic flows.
//            try {
//                TrafficFlowArray trafficFlowArray = TrafficFlowArray.getTrafficFlowArray(context, aeId,
//                        userName, password);
//                BucksContentHelper.deleteFromProvider(context,
//                        BucksContentHelper.DATA_TYPE_TRAFFIC_FLOW);
//                BucksContentHelper.insertIntoProvider(context, trafficFlowArray.getTrafficFlows());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            // Variable message signs.
//            try {
//                VariableMessageSignArray variableMessageSignArray = VariableMessageSignArray
//                        .getVariableMessageSignArray(context, aeId, userName, password);
//                BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_VMS);
//                BucksContentHelper.insertIntoProvider(context,
//                        variableMessageSignArray.getVariableMessageSigns());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void refresh(Context context) {
        Account account = BucksSyncAdapter.getAccount(context);
        Bundle settingsBundle = new Bundle();
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

    private void initialise(Context context) {
        this.context = context;
//        cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
//        userName = context.getString(R.string.one_transport_user_name);
//        password = context.getString(R.string.one_transport_password);
        BucksProvider.initialise(context);
    }
}
