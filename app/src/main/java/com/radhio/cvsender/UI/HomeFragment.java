package com.radhio.cvsender.UI;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.Toast;

import com.radhio.cvsender.Session.UserSession;
import com.radhio.cvsender.ViewModel.HomeViewModel;
import com.radhio.cvsender.R;

import java.util.Objects;


/**
 * Created by Azmia Hoque Radhio on 12/10/2020.
 */

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    Button loginButton;
    NavController navController;
    ProgressDialog progressDialog;
    private EditText usernameEditText, passwordEditText;
    UserSession session;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        usernameEditText = view.findViewById(R.id.user_name);
        passwordEditText = view.findViewById(R.id.user_password);
        loginButton = view.findViewById(R.id.log_in_button);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new UserSession(requireContext());
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        if (session.getUserName() != null && session.getUserPassword() != null){
            usernameEditText.setText(session.getUserName());
            passwordEditText.setText(session.getUserPassword());
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (validateUsername(username) && validatePassword(password)) {
                    mViewModel.GetAccessToken(username, password, getContext()).observe(getViewLifecycleOwner(), accessToken -> {
                        showDialog();
                        if (accessToken.isAuthenticated()) {
                            hideDialog();
                            session.setUserName(username);
                            session.setUserPassword(password);
                            navController.navigate(R.id.inputFragment);
                            Toast.makeText(getContext(), "You have successfully logged in!", Toast.LENGTH_SHORT).show();
                        } else {
                            hideDialog();
                            session.setUserName(username);
                            session.setUserPassword(password);
                            Toast.makeText(getContext(), accessToken.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void showDialog(){
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    private void hideDialog(){
        if (progressDialog.isShowing()){
            progressDialog.hide();
        }
    }
    private boolean validatePassword(String pass) {
        if (pass.isEmpty()) {
            passwordEditText.setError("Please! Enter Your Password Here");
            return false;
        } else {
            passwordEditText.setError(null);
            return true;
        }
    }

    private boolean validateUsername(String usernameInput) {
        if (usernameInput.isEmpty()) {
            usernameEditText.setError("Please! Enter Your Username Here");
            return false;
        } else {
            usernameEditText.setError(null);
            return true;
        }
    }

}