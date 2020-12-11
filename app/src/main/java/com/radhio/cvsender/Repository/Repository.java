package com.radhio.cvsender.Repository;

import com.radhio.cvsender.EndPoints.IAuthToken;
import com.radhio.cvsender.EndPoints.IFetchId;
import com.radhio.cvsender.Models.AccessToken;
import com.radhio.cvsender.Models.CV;
import com.radhio.cvsender.Models.CVFileUpload;
import com.radhio.cvsender.Service.ServiceGenerator;

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

    public void GetFileTokenId(String Token, final CV cv, final Callback<CVFileUpload> cvFileUploadCallback)
    {
        IFetchId service = ServiceGenerator.CreateService(IFetchId.class, Token);
        service.GetFileId(cv).enqueue(cvFileUploadCallback);
    }

}
