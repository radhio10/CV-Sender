package com.radhio.cvsender.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhio.cvsender.Models.AccessToken;
import com.radhio.cvsender.Models.CVFileUpload;
import com.radhio.cvsender.Repository.Repository;
import com.radhio.cvsender.Session.UserSession;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class InputViewModel extends AndroidViewModel {

    private Repository fetchIdRepository;
    private MutableLiveData<CVFileUpload> cvFileUploadMutableLiveData;

    public InputViewModel(@NonNull Application application) {
        super(application);
        this.fetchIdRepository = new Repository();
    }

    public LiveData<CVFileUpload> GetFileTokenId(String cv, Context context)
    {
        UserSession session = new UserSession(context);
        if(cvFileUploadMutableLiveData == null)
        {
            cvFileUploadMutableLiveData = new MutableLiveData<>();
            FetchFileTokenId(session.getAuthToken(),cv);
        }
        return cvFileUploadMutableLiveData;
    }

    private void FetchFileTokenId(AccessToken token, String cv) {
        fetchIdRepository.GetFileTokenId(token.getToken(), cv, new Callback<CVFileUpload>() {
            CVFileUpload cvFileUpload = new CVFileUpload();
            @Override
            public void onResponse(@NotNull Call<CVFileUpload> call, @NotNull Response<CVFileUpload> response) {
                int response1 = response.code();
                if (response.isSuccessful() && response.body() != null) {
                    cvFileUpload = response.body();
                    cvFileUploadMutableLiveData.setValue(cvFileUpload);
                }
                else if (response.code() == 400) {
                    cvFileUpload.setMessage("Bad Request!");
                    cvFileUploadMutableLiveData.setValue(cvFileUpload);
                }
                else {
                    cvFileUpload.setMessage("Sever Error!");
                    cvFileUploadMutableLiveData.setValue(cvFileUpload);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CVFileUpload> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    cvFileUpload.setMessage("Request Timed Out, Abort");
                } else {
                    cvFileUpload.setMessage("Unknown Error Occurred");
                }
                cvFileUploadMutableLiveData.setValue(cvFileUpload);
            }
        });
    }
}
