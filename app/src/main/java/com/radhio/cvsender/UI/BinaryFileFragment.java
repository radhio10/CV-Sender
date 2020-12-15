package com.radhio.cvsender.UI;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.radhio.cvsender.Models.CvFile;
import com.radhio.cvsender.R;
import com.radhio.cvsender.Session.UserSession;
import com.radhio.cvsender.Utils.FileUtils;
import com.radhio.cvsender.ViewModel.BinaryFileViewModel;

import java.io.File;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Azmia Hoque Radhio on 12/10/2020.
 */

public class BinaryFileFragment extends Fragment {
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    private static final long MINIMUM_FILE_SIZE = 4194304;
    LinearLayout fileLinearLayout;
    TextView filePath;
    Button pdfSubmitButton;
    UserSession session;
    BinaryFileViewModel binaryFileViewModel;
    String fileTokenID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_binary_file, container, false);
        session = new UserSession(requireContext());
        binaryFileViewModel = new ViewModelProvider(this).get(BinaryFileViewModel.class);
        assert getArguments() != null;
        fileTokenID = BinaryFileFragmentArgs.fromBundle(getArguments()).getFileId();
        fileLinearLayout = view.findViewById(R.id.linear);
        filePath = view.findViewById(R.id.file_name);
        pdfSubmitButton = view.findViewById(R.id.pdf_submit);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fileLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }
                Intent intentPDF = new Intent(Intent.ACTION_GET_CONTENT);
                intentPDF.setType("application/pdf");
                intentPDF.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intentPDF, FILE_PICKER_REQUEST_CODE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri pdfUri = data.getData();
                assert pdfUri != null;
                File file = FileUtils.getFile(getContext(), pdfUri);
                long size = file.length();
                RequestBody requestFile =
                        RequestBody.create(file,
                                MediaType.parse(Objects.requireNonNull(requireActivity().getApplicationContext().getContentResolver().getType(pdfUri))));
                MultipartBody.Part filePart =
                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                filePath.setText(file.getName());
                pdfSubmitButton.setEnabled(true);
                pdfSubmitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (size<=MINIMUM_FILE_SIZE){
                            SubmitFile(fileTokenID,filePart);
                        }
                        else {
                            Toast.makeText(getActivity(), "Maximum file size 4MB", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
            else {
                filePath.setVisibility(View.INVISIBLE);
                fileLinearLayout.setVisibility(View.VISIBLE);
                pdfSubmitButton.setEnabled(false);
            }
        }

    }

    private void SubmitFile(String fileTokenID, MultipartBody.Part filePart) {
        binaryFileViewModel.UploadFile(fileTokenID,filePart,getContext()).observe(getViewLifecycleOwner(), new Observer<CvFile>() {
            @Override
            public void onChanged(CvFile cvFile) {
                if (cvFile.isSuccess()){
                    Toast.makeText(getActivity(), cvFile.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), cvFile.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1001) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "not granted", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }
        }
    }
}