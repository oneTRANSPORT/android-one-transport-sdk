package net.uk.onetransport.android.modules.common.provider.lastupdated;

import android.provider.BaseColumns;

public class LastUpdatedContract {

    public static final String CREATE_LAST_UPDATED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + LastUpdated.TABLE_NAME + " ("
                    + LastUpdated.COLUMN_LAST_UPDATE_MILLIS + " INTEGER NOT NULL"
                    + ");";
    public static final String INIT_LAST_UPDATED = "insert into "
            + LastUpdatedContract.LastUpdated.TABLE_NAME + " ("
            + LastUpdatedContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS + ") values (0);";

    private LastUpdatedContract() {
    }

    public static final class LastUpdated implements BaseColumns {
        public static final String TABLE_NAME = "last_updated";
        public static final String COLUMN_LAST_UPDATE_MILLIS = "last_update_millis";
    }

}
