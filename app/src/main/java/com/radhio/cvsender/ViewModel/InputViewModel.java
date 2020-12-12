package com.radhio.cvsender.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhio.cvsender.Models.AccessToken;
import com.radhio.cvsender.Models.Cv;
import com.radhio.cvsender.Models.CvFileUpload;
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
    private MutableLiveData<CvFileUpload> cvFileUploadMutableLiveData;

    public InputViewModel(@NonNull Application application) {
        super(application);
        this.fetchIdRepository = new Repository();
    }

    public LiveData<CvFileUpload> GetFileTokenId(Cv cv, Context context)
    {
        UserSession session = new UserSession(context);
        if(cvFileUploadMutableLiveData == null)
        {
            cvFileUploadMutableLiveData = new MutableLiveData<>();
            FetchFileTokenId(session.getAuthToken(),cv);
        }
        return cvFileUploadMutableLiveData;
    }

    private void FetchFileTokenId(AccessToken token, Cv cv) {
        fetchIdRepository.GetFileTokenId(token.getToken(), cv, new Callback<CvFileUpload>() {
            CvFileUpload cvFileUpload = new CvFileUpload();
            @Override
            public void onResponse(@NotNull Call<CvFileUpload> call, @NotNull Response<CvFileUpload> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cvFileUpload = response.body();
                    cvFileUploadMutableLiveData.setValue(cvFileUpload);
                    cvFileUpload.setSuccess(true);
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
            public void onFailure(@NotNull Call<CvFileUpload> call, @NotNull Throwable t) {
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
