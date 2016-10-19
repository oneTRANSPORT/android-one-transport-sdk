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
package net.uk.onetransport.android.county.bucks.authentication;

import android.content.Context;

import com.interdigital.android.dougal.resource.Resource;

import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.storage.Prefs;

import okhttp3.Credentials;
import okhttp3.Interceptor;

public class CredentialHelper {

    // TODO    Encrypt before storage and decrypt on retrieval?
    private CredentialHelper() {
    }

    public synchronized static void initialiseCredentials(Context context, String aeId, String sessionToken,
                                                          String installationId) {
        Prefs.putAeId(context, aeId);
        Prefs.putSessionToken(context, sessionToken);
        Prefs.putInstallationId(context, installationId);
//        Resource.addInterceptors(context.getString(R.string.bucks_cse_base_url),
//                new Interceptor[]{new AuthenticationInterceptor()});
    }

//    public static String getBasicAuth(Context context) {
//        return Credentials.basic(getAeId(context), getSessionToken(context));
//    }

    public static String getBearerAuth(Context context) {
        return "Bearer " + getSessionToken(context);
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
