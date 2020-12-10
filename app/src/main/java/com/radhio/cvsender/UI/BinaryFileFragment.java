package com.radhio.cvsender.UI;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.radhio.cvsender.R;
import com.radhio.cvsender.Utils.FileDetailHelper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azmia Hoque Radhio on 12/10/2020.
 */

public class BinaryFileFragment extends Fragment {
    public static final int PICK_FILE_PDF = 171;
    LinearLayout fileLinearLayout;
    TextView filePath;
    Button pdfSubmitBUtton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_binary_file, container, false);
        fileLinearLayout = view.findViewById(R.id.linear);
        filePath = view.findViewById(R.id.file_path);
        pdfSubmitBUtton = view.findViewById(R.id.pdf_submit);
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
                String filename = FileDetailHelper.getFileDetailFromUri(getContext(), pdfUri).fileName;
                filePath.setText(filename);
                filePath.setVisibility(View.VISIBLE);
                fileLinearLayout.setVisibility(View.INVISIBLE);
                pdfSubmitBUtton.setEnabled(true);
            }
            else {
                filePath.setVisibility(View.INVISIBLE);
                fileLinearLayout.setVisibility(View.VISIBLE);
                pdfSubmitBUtton.setEnabled(false);
            }
        }

    }
}