package net.uk.onetransport.android.county.bucks.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.carparks.CarParkArray;
import net.uk.onetransport.android.county.bucks.locations.SegmentLocationArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContentHelper;
import net.uk.onetransport.android.county.bucks.provider.BucksDbHelper;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;
import net.uk.onetransport.android.county.bucks.roadworks.RoadWorksArray;
import net.uk.onetransport.android.county.bucks.trafficflow.TrafficFlowArray;
import net.uk.onetransport.android.county.bucks.variablemessagesigns.VariableMessageSignArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BucksSyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String ACCOUNT = "dummyaccount";
    private static final String EXTRAS_VMS = "net.uk.onetransport.android.county.bucks.sync.VMS";
    private static final String EXTRAS_CAR_PARKS =
            "net.uk.onetransport.android.county.bucks.sync.CAR_PARKS";
    private static final String EXTRAS_TRAFFIC_FLOW =
            "net.uk.onetransport.android.county.bucks.sync.TRAFFIC_FLOW";
    private static final String EXTRAS_ROAD_WORKS =
            "net.uk.onetransport.android.county.bucks.sync.ROAD_WORKS";

    private Context context;

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
            // Link segments.
            if (extras.getBoolean(EXTRAS_TRAFFIC_FLOW, false)) {
                try {
                    SegmentLocationArray segmentLocationArray = SegmentLocationArray
                            .getSegmentLocationArray(context);
                    BucksContentHelper.deleteFromProvider(context,
                            BucksContentHelper.DATA_TYPE_SEGMENT_LOCATION);
                    BucksContentHelper.insertIntoProvider(context,
                            segmentLocationArray.getSegmentLocations());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Car parks.
            if (extras.getBoolean(EXTRAS_CAR_PARKS, false)) {
                try {
                    CarParkArray carParkArray = CarParkArray.getCarParkArray(context);
                    BucksContentHelper.deleteFromProvider(context,
                            BucksContentHelper.DATA_TYPE_CAR_PARK);
                    BucksContentHelper.insertIntoProvider(context, carParkArray.getCarParks());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Traffic flows.
            if (extras.getBoolean(EXTRAS_TRAFFIC_FLOW, false)) {
                try {
                    TrafficFlowArray trafficFlowArray = TrafficFlowArray.getTrafficFlowArray(context);
                    BucksContentHelper.deleteFromProvider(context,
                            BucksContentHelper.DATA_TYPE_TRAFFIC_FLOW);
                    BucksContentHelper.insertIntoProvider(context, trafficFlowArray.getTrafficFlows());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Variable message signs.
            if (extras.getBoolean(EXTRAS_VMS, false)) {
                try {
                    VariableMessageSignArray variableMessageSignArray = VariableMessageSignArray
                            .getVariableMessageSignArray(context);
                    BucksContentHelper.deleteFromProvider(context, BucksContentHelper.DATA_TYPE_VMS);
                    BucksContentHelper.insertIntoProvider(context,
                            variableMessageSignArray.getVariableMessageSigns());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Road works.
            if (extras.getBoolean(EXTRAS_ROAD_WORKS, false)) {
                try {
                    RoadWorksArray roadWorksArray = RoadWorksArray.getRoadWorksArray(context);
                    BucksContentHelper.deleteFromProvider(context,
                            BucksContentHelper.DATA_TYPE_ROAD_WORKS);
                    BucksContentHelper.insertIntoProvider(context, roadWorksArray.getRoadWorks());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Signal refresh complete.
            try {
                BucksContentHelper.refreshLastUpdated(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // TODO    Remove this, diagnostics only.
        BucksDbHelper bucksDbHelper = new BucksDbHelper(context);
        SQLiteDatabase sqLiteDatabase = bucksDbHelper.getReadableDatabase();
        File dbFile = new File(sqLiteDatabase.getPath());
        File exportDb = new File("/sdcard/bucks-db");
        copy(dbFile, exportDb);
        // TODO    --------------------------------------------------
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

    public static void refresh(Context context, boolean variableMessageSigns, boolean carParks,
                               boolean trafficFlow, boolean roadWorks) {
        Account account = BucksSyncAdapter.getAccount(context);
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        settingsBundle.putBoolean(EXTRAS_VMS, variableMessageSigns);
        settingsBundle.putBoolean(EXTRAS_CAR_PARKS, carParks);
        settingsBundle.putBoolean(EXTRAS_TRAFFIC_FLOW, trafficFlow);
        settingsBundle.putBoolean(EXTRAS_ROAD_WORKS, roadWorks);
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
        BucksProvider.initialise(context);
    }
}
