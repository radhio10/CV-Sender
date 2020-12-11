package com.radhio.cvsender.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.radhio.cvsender.Info.CVSenderInfo;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ServiceGenerator instance = null;

    public static ServiceGenerator getInstance() {
        if (instance == null) {
            instance = new ServiceGenerator();
        }
        return instance;
    }

    public static final String API_BASE_URL = CVSenderInfo.BASE_URL;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(6, TimeUnit.SECONDS);

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    protected static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    protected static Retrofit retrofit = builder.build();

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static <S> S CreateService(Class<S> serviceClass, final String authToken) {
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }

}

