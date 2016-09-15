package net.uk.onetransport.android.modules.common.provider;

public class CommonContentHelper {

    protected static final String CREATION_INTERVAL_SELECTION =
            CommonBaseColumns.COLUMN_CREATION_TIME + " >= ? AND "
                    + CommonBaseColumns.COLUMN_CREATION_TIME + " <= ?";
    protected static final String CREATED_BEFORE =
            CommonBaseColumns.COLUMN_CREATION_TIME + " < ?";

    protected static String[] interval(long oldest, long newest) {
        return new String[]{String.valueOf(oldest), String.valueOf(newest)};
    }
}
