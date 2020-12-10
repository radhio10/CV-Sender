package com.radhio.cvsender.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.radhio.cvsender.R;

/**
 * Created by Azmia Hoque Radhio on 12/10/2020.
 */

public class InputFragment extends Fragment {
    Button submitButton;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_input, container, false);
        submitButton = view.findViewById(R.id.submit_button);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.binaryFileFragment);
            }
        });
    }
}