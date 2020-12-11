package com.radhio.cvsender.EndPoints;

import com.radhio.cvsender.Models.CV;
import com.radhio.cvsender.Models.CVFileUpload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public interface IFetchId {
    @Headers("Content-Type: application/json")
    @POST("api/v0/recruiting-entities/")
    Call<CVFileUpload> GetFileId(@Body String body);
}
