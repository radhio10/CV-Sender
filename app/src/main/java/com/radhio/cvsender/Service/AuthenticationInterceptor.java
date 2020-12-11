package com.radhio.cvsender.Service;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class AuthenticationInterceptor implements Interceptor {
    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        if(authToken != null && !authToken.isEmpty())
        {
                builder.header("Authorization", "Token "+authToken);
        }
        Request request = builder.build();
        return chain.proceed(request);
    }
}
