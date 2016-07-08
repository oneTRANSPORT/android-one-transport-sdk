package net.uk.onetransport.android.modules.clearviewsilverstone.authentication;

import android.content.Context;

import com.interdigital.android.dougal.resource.Resource;

import net.uk.onetransport.android.modules.clearviewsilverstone.R;
import net.uk.onetransport.android.modules.clearviewsilverstone.storage.Prefs;

import okhttp3.Credentials;
import okhttp3.Interceptor;

public class CredentialHelper {

    // TODO    Does this belong in Common?
    // TODO    Encrypt before storage and decrypt on retrieval?
    private CredentialHelper() {
    }

    public synchronized static void initialiseCredentials(Context context, String aeId, String sessionToken,
                                                          String installationId) {
        Prefs.putAeId(context, aeId);
        Prefs.putSessionToken(context, sessionToken);
        Prefs.putInstallationId(context, installationId);
        Resource.addInterceptors(context.getString(R.string.clearview_base_url),
                new Interceptor[]{new AuthenticationInterceptor()});
    }

    public static String getBasicAuth(Context context) {
        return Credentials.basic(getAeId(context), getSessionToken(context));
    }

    public static void refreshSessionToken(Context context, String sessionToken) {
        Prefs.putSessionToken(context, sessionToken);
    }

    public static String getAeId(Context context) {
        return Prefs.getAeId(context);
    }

    public static String getSessionToken(Context context) {
        return Prefs.getSessionToken(context);
    }

}
