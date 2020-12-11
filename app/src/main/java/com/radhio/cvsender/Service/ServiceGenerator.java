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

    private static OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    protected Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(okHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

    public Retrofit getRetrofit() {
        return retrofit;
    }

//    public static <S> S CreateService(Class<S> serviceClass, final String authToken) {
//        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
//        if (!httpClient.interceptors().contains(interceptor)) {
//            httpClient.addInterceptor(interceptor);
//            builder.client(httpClient.build());
//            retrofit = builder.build();
//        }
//        return retrofit.create(serviceClass);
//    }

}

