package com.radhio.cvsender.EndPoints;

import com.radhio.cvsender.Models.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public interface IAuthToken {
    @FormUrlEncoded
    @POST("api/login")
    Call<AccessToken> GetToken(@Field("username") String username, @Field("password") String password);
}
