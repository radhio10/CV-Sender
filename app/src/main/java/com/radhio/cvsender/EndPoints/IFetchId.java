package com.radhio.cvsender.EndPoints;

import com.radhio.cvsender.Models.Cv;
import com.radhio.cvsender.Models.CvFileUpload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public interface IFetchId {
    @Headers({
            "Accept: */*",
            "Content-Type: application/json"
    })
    @POST("api/v1/recruiting-entities/")
    Call<CvFileUpload> GetFileId(@Body Cv cv);
}
