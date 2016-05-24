package net.uk.onetransport.android.county.bucks.authentication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request.newBuilder()
                .addHeader("X-Bananaman", "Legend")
                .build();
        Response response = chain.proceed(newRequest);
        return response;
    }
}
