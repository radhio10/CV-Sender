package com.radhio.cvsender.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhio.cvsender.Models.AccessToken;
import com.radhio.cvsender.Repository.Repository;
import com.radhio.cvsender.Session.UserSession;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private Repository tokenRepository;
    private MutableLiveData<AccessToken> accessTokenMutableLiveData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.tokenRepository = new Repository();
    }

    public LiveData<AccessToken> GetAccessToken(String username, String password , Context context)
    {
        if(accessTokenMutableLiveData == null)
        {
            accessTokenMutableLiveData = new MutableLiveData<>();
            FetchAccessToken(username,password,context);
        }
        return accessTokenMutableLiveData;
    }

    private void FetchAccessToken(String username, String password, Context context) {
        tokenRepository.GetAccessToken(username, password, new Callback<AccessToken>() {
            AccessToken accessToken = new AccessToken();
            UserSession session = new UserSession(context);
            @Override
            public void onResponse(@NotNull Call<AccessToken> call, @NotNull Response<AccessToken> response) {
                if (response.isSuccessful() && response.body() != null) {
                    accessToken = response.body();
                    accessToken.setAuthenticated(true);
                    accessTokenMutableLiveData.setValue(accessToken);
                    session.setAuthToken(accessToken);
                } else if (response.code() == 401) {
                    accessToken.setAuthenticated(false);
                    accessToken.setMessage("Invalid Credentials");
                    accessTokenMutableLiveData.setValue(accessToken);
                } else {
                    accessToken.setAuthenticated(false);
                    accessToken.setMessage("Invalid Authentication");
                    accessTokenMutableLiveData.setValue(accessToken);
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
                accessTokenMutableLiveData.setValue(accessToken);
            }
        });
    }
}