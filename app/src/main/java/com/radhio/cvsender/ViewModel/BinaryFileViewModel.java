package com.radhio.cvsender.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhio.cvsender.Models.AccessToken;
import com.radhio.cvsender.Models.CvFile;
import com.radhio.cvsender.Repository.Repository;
import com.radhio.cvsender.Session.UserSession;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azmia Hoque Radhio on 12/13/2020.
 */
public class BinaryFileViewModel extends AndroidViewModel {
    private Repository uploadFileRepository;
    private MutableLiveData<CvFile> cvFileMutableLiveData;

    public BinaryFileViewModel(@NonNull Application application) {
        super(application);
        this.uploadFileRepository = new Repository();
    }

    public LiveData<CvFile> UploadFile(String fileTokenId, final MultipartBody.Part cvFile, Context context)
    {
        UserSession session = new UserSession(context);
        if(cvFileMutableLiveData == null)
        {
            cvFileMutableLiveData = new MutableLiveData<>();
            UploadCvFile(session.getAuthToken(),fileTokenId, cvFile);
        }
        return cvFileMutableLiveData;
    }

    private void UploadCvFile(AccessToken authToken, String fileTokenId, MultipartBody.Part cvFile) {
        uploadFileRepository.UploadFile(authToken.getToken(), fileTokenId, cvFile, new Callback<CvFile>() {
            CvFile cvFile = new CvFile();
            @Override
            public void onResponse(@NotNull Call<CvFile> call, @NotNull Response<CvFile> response) {
                int code = response.code();
                if (response.isSuccessful() && response.body() != null) {
                    cvFile = response.body();
                    cvFileMutableLiveData.setValue(cvFile);
                    cvFile.setSuccess(true);
                }
                else if (response.code() == 400) {
                    cvFile.setMessage("Bad Request!");
                    cvFileMutableLiveData.setValue(cvFile);
                }
                else {
                    cvFile.setMessage("Sever Error!");
                    cvFileMutableLiveData.setValue(cvFile);
                }
            }

            @Override
                public void onFailure(@NotNull Call<CvFile> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    cvFile.setMessage("Request Timed Out, Abort");
                } else {
                    cvFile.setMessage("Unknown Error Occurred");
                }
                cvFileMutableLiveData.setValue(cvFile);
            }
        });

    }
}
