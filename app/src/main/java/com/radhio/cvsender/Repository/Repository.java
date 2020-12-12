package com.radhio.cvsender.Repository;

import com.radhio.cvsender.EndPoints.IAuthToken;
import com.radhio.cvsender.EndPoints.IFetchId;
import com.radhio.cvsender.EndPoints.IFileUpload;
import com.radhio.cvsender.Models.AccessToken;
import com.radhio.cvsender.Models.Cv;
import com.radhio.cvsender.Models.CvFile;
import com.radhio.cvsender.Models.CvFileUpload;
import com.radhio.cvsender.Service.ServiceGenerator;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class Repository {

    public void GetAccessToken(String username, String password, Callback<AccessToken> callback)
    {
        IAuthToken service =  ServiceGenerator.getInstance().getRetrofit().create(IAuthToken.class);
        service.GetToken(username,password).enqueue(callback);
    }

    public void GetFileTokenId(String Token, final Cv cv, final Callback<CvFileUpload> cvFileUploadCallback)
    {
        IFetchId service = ServiceGenerator.CreateService(IFetchId.class, Token);
        service.GetFileId(cv).enqueue(cvFileUploadCallback);
    }

    public void UploadFile(String Token, String fileTokenId, final MultipartBody.Part cvFile, final Callback<CvFile> cvFileCallback)
    {
        IFileUpload service = ServiceGenerator.CreateService(IFileUpload.class, Token);
        service.UpdateFile(fileTokenId,cvFile).enqueue(cvFileCallback);
    }

}
