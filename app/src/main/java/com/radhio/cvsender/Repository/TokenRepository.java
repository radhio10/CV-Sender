package com.radhio.cvsender.Repository;

import com.radhio.cvsender.EndPoints.IAuthToken;
import com.radhio.cvsender.Service.ServiceGenerator;

import retrofit2.Retrofit;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class TokenRepository {
    private Retrofit retrofit;

    public TokenRepository() {
        this.retrofit = ServiceGenerator.getInstance().getRetrofit();
    }
    public IAuthToken getAPI() {
        return retrofit.create(IAuthToken.class);
    }
}
