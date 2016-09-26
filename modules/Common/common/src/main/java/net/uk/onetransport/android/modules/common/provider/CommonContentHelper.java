package net.uk.onetransport.android.modules.common.provider;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedContract;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;

public class CommonContentHelper {

    protected static final String CREATION_INTERVAL_SELECTION =
            CommonBaseColumns.COLUMN_CREATION_TIME + " >= ? AND "
                    + CommonBaseColumns.COLUMN_CREATION_TIME + " <= ?";
    protected static final String CREATED_BEFORE =
            CommonBaseColumns.COLUMN_CREATION_TIME + " < ?";

    protected static String[] interval(long oldest, long newest) {
        return new String[]{String.valueOf(oldest), String.valueOf(newest)};
    }

    public static Cursor getLastUpdated(@NonNull Context context) {
        return context.getContentResolver().query(LastUpdatedProviderModule.LAST_UPDATED_URI,
                new String[]{
                        LastUpdatedContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS
                }, null, null, null);
    }
}
