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
package net.uk.onetransport.android.modules.clearviewsilverstone.authentication;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    // TODO    Does this belong in the Common module?

    // Need to add installation id request header and extract new session token
    // from response header.
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        // TODO Need to add the installation id here.
        Request newRequest = request.newBuilder()
                .addHeader("X-Bananaman", "Legend")
                .build();
        Response response = chain.proceed(newRequest);
        // TODO Need to extract the session token here.
        Log.i("AuthenticationInt", "Header code = " + response.header("X-M2M-RSC"));
        return response;
    }

}
