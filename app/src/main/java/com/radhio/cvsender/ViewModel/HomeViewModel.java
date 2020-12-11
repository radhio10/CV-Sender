package com.radhio.cvsender.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhio.cvsender.Models.AccessToken;
import com.radhio.cvsender.Repository.TokenRepository;
import com.radhio.cvsender.Session.UserSession;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private TokenRepository tokenRepository;
    private MutableLiveData<AccessToken> accessTokenLiveData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.tokenRepository = new TokenRepository();
    }

    public LiveData<AccessToken> GetAccessToken(String username, String password , Context context)
    {
        if(accessTokenLiveData == null)
        {
            accessTokenLiveData = new MutableLiveData<>();
            FetchAccessToken(username,password,context);
        }
        return accessTokenLiveData;
    }

    private void FetchAccessToken(String username, String password, Context context) {
        Call<AccessToken> accessTokenCallback = tokenRepository.getAPI().GetToken(username,password);
        accessTokenCallback.enqueue(new Callback<AccessToken>() {
            AccessToken accessToken = new AccessToken();
            UserSession session = new UserSession(context);
            @Override
            public void onResponse(@NotNull Call<AccessToken> call, @NotNull Response<AccessToken> response) {
                if (response.isSuccessful() && response.body() != null) {
                    accessToken = response.body();
                    accessToken.setAuthenticated(true);
                    accessTokenLiveData.setValue(accessToken);
                    session.setAuthToken(accessToken);
                } else if (response.code() == 401) {
                    accessToken.setAuthenticated(false);
                    accessToken.setMessage("Invalid Credentials");
                    accessTokenLiveData.setValue(accessToken);
                } else {
                    accessToken.setAuthenticated(false);
                    accessToken.setMessage("Invalid Authentication");
                    accessTokenLiveData.setValue(accessToken);
                }
            }

            @Override
            public void onFailure(@NotNull Call<AccessToken> call, @NotNull Throwable t) {
                accessToken.setAuthenticated(false);
                if (t instanceof SocketTimeoutException) {
                    accessToken.setMessage("Request Timed Out, Abort");
                } else {
                    accessToken.setMessage("Unknown Error Occurred");
                }
                accessTokenLiveData.setValue(accessToken);
            }
        });
    }
}