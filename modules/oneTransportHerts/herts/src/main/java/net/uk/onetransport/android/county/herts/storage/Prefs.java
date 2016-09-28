package net.uk.onetransport.android.county.herts.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public static final String FILE_NAME = "one_transport_bucks_prefs";
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
