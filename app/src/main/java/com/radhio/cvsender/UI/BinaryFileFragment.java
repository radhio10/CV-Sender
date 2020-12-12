package com.radhio.cvsender.UI;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.radhio.cvsender.Models.CvFile;
import com.radhio.cvsender.Models.CvFileUpload;
import com.radhio.cvsender.R;
import com.radhio.cvsender.Session.UserSession;
import com.radhio.cvsender.Utils.FileDetailHelper;
import com.radhio.cvsender.ViewModel.BinaryFileViewModel;
import com.radhio.cvsender.ViewModel.InputViewModel;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Azmia Hoque Radhio on 12/10/2020.
 */

public class BinaryFileFragment extends Fragment {
    public static final int PICK_FILE_PDF = 171;
    LinearLayout fileLinearLayout;
    TextView filePath;
    Button pdfSubmitButton;
    UserSession session;
    BinaryFileViewModel binaryFileViewModel;
    String fileTokenID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_binary_file, container, false);
        session = new UserSession(requireContext());
        binaryFileViewModel = new ViewModelProvider(this).get(BinaryFileViewModel.class);
        assert getArguments() != null;
        fileTokenID = BinaryFileFragmentArgs.fromBundle(getArguments()).getFileId();
//        fileTokenID = Integer.parseInt(fileToken);
        fileLinearLayout = view.findViewById(R.id.linear);
        filePath = view.findViewById(R.id.file_path);
        pdfSubmitButton = view.findViewById(R.id.pdf_submit);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fileLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPDF = new Intent(Intent.ACTION_GET_CONTENT);
                intentPDF.setType("application/pdf");
                intentPDF.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intentPDF, PICK_FILE_PDF);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_PDF && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri pdfUri = data.getData();
                assert pdfUri != null;
                String a = pdfUri.getPath();
                File file = new File(Objects.requireNonNull(pdfUri.getPath()));
                //RequestBody filePart = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                String size = file.getName();
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                String filename = FileDetailHelper.getFileDetailFromUri(getContext(), pdfUri).fileName;
                long filesize = FileDetailHelper.getFileDetailFromUri(getContext(), pdfUri).fileSize;
                String path = FileDetailHelper.getFileDetailFromUri(getContext(), pdfUri).path;
                filePath.setText(file.getName());

                filePath.setVisibility(View.VISIBLE);
                fileLinearLayout.setVisibility(View.INVISIBLE);
                pdfSubmitButton.setEnabled(true);
                pdfSubmitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SubmitFile(fileTokenID,filePart);
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


    private static String getFileSizeKiloBytes(File file) {
        long size = file.length();
        return (double) size / 1024 + "  kb";
    }


}