package net.uk.onetransport.android.modules.common.provider;

import android.provider.BaseColumns;

public class CommonContract {

    public static final String CREATE_LAST_UPDATED_TABLE =
            "CREATE TABLE IF NOT EXISTS " + LastUpdated.TABLE_NAME + " ("
                    + LastUpdated.COLUMN_LAST_UPDATE_MILLIS + " INTEGER NOT NULL"
                    + ");";
    public static final String INIT_LAST_UPDATED = "insert into "
            + CommonContract.LastUpdated.TABLE_NAME + " ("
            + CommonContract.LastUpdated.COLUMN_LAST_UPDATE_MILLIS + ") values (0);";

    private CommonContract() {
    }

    public static final class LastUpdated implements BaseColumns {
        public static final String TABLE_NAME = "last_updated";
        public static final String COLUMN_LAST_UPDATE_MILLIS = "last_update_millis";
    }

}
