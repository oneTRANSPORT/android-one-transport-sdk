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
package net.uk.onetransport.android.modules.bitcarriersilverstone.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public static final String FILE_NAME = "one_transport_bitcarriersilverstone_prefs";
    public static final String KEY_INSTALLATION_ID = "pref_installation_id";
    public static final String KEY_AE_ID = "pref_ae_id";
    public static final String KEY_SESSION_TOKEN = "pref_session_token";

    public static String getInstallationId(Context context) {
        return getString(context, KEY_INSTALLATION_ID);
    }

    public static void putInstallationId(Context context, String installationId) {
        putString(context, KEY_INSTALLATION_ID, installationId);
    }

    public static String getAeId(Context context) {
        return getString(context, KEY_AE_ID);
    }

    public static void putAeId(Context context, String aeId) {
        putString(context, KEY_AE_ID, aeId);
    }

    public static String getSessionToken(Context context) {
        return getString(context, KEY_SESSION_TOKEN);
    }

    public static void putSessionToken(Context context, String sessionToken) {
        putString(context, KEY_SESSION_TOKEN, sessionToken);
    }

    private static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, null);
    }

    private static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).commit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

}
