package com.radhio.cvsender.EndPoints;

import com.radhio.cvsender.Models.CvFile;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Azmia Hoque Radhio on 12/13/2020.
 */
public interface IFileUpload {
    @Multipart
    @PUT("/api/file-object/{FILE_TOKEN_ID}/")
    Call<CvFile> UpdateFile(@Path(value = "FILE_TOKEN_ID") String fileTokenId, @Part MultipartBody.Part imageFile);
}
