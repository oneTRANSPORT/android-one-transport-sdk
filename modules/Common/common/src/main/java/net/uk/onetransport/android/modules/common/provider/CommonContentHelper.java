/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
