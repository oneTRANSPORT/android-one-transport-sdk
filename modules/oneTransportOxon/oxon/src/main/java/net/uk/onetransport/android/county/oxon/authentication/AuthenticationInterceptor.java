package net.uk.onetransport.android.county.oxon.authentication;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    // Need to add installation id request header and extract new session token
    // from response header.
    @Override
    public Response intercept(Chain chain) throws IOException {
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
