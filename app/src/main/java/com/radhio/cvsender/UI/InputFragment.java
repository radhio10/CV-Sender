package com.radhio.cvsender.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.radhio.cvsender.R;
import com.radhio.cvsender.Utils.InputFilterMinMax;

import java.util.Objects;

/**
 * Created by Azmia Hoque Radhio on 12/10/2020.
 */

public class InputFragment extends Fragment {
    Button submitButton;
    EditText name,email,phone,fullAddress,nameOfUniversity,graduationYear,cgpa,experienceInMonths,
            currentWorkPlaceName,expectedSalary,fieldBuzzReference,githubProjectUrl;
    Spinner applyingIn;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_input, container, false);
        submitButton = view.findViewById(R.id.submit_button);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        fullAddress = view.findViewById(R.id.full_address);
        nameOfUniversity = view.findViewById(R.id.name_of_university);
        graduationYear = view.findViewById(R.id.graduation_year);
        cgpa = view.findViewById(R.id.cgpa);
        experienceInMonths = view.findViewById(R.id.experience_in_months);
        currentWorkPlaceName = view.findViewById(R.id.current_work_place_name);
        expectedSalary = view.findViewById(R.id.expected_salary);
        fieldBuzzReference = view.findViewById(R.id.field_buzz_reference);
        githubProjectUrl = view.findViewById(R.id.github_project_url);
        applyingIn = view.findViewById(R.id.applying_in);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
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

    private boolean submitForm() {
        boolean valid = true;
        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String userPhone = phone.getText().toString().trim();
        String userFullAddress = fullAddress.getText().toString().trim();
        String userUniversity = nameOfUniversity.getText().toString().trim();
        String userGraduationYear = graduationYear.getText().toString().trim();
        String userCGPA = cgpa.getText().toString().trim();
        String userExperience = experienceInMonths.getText().toString().trim();
        String userWorkPlaceName = currentWorkPlaceName.getText().toString().trim();
        String userSalary = expectedSalary.getText().toString().trim();
        String userFieldBuzzRef = fieldBuzzReference.getText().toString().trim();
        String userProjectUrl = githubProjectUrl.getText().toString().trim();
        if (userName.length() == 0 || userName.length()>=256) {
            name.setError("Enter a valid Name");
            name.requestFocus();
            valid = false;
        } else if (userEmail.length() == 0 || userEmail.length()>=256 || !userEmail.matches(emailPattern)) {
            email.setError("Enter a valid Email");
            email.requestFocus();
            valid = false;

        } else if (userPhone.length() == 0 || userPhone.length()>=14) {
            phone.setError("Enter a valid phone number");
            phone.requestFocus();
            valid = false;

        }else if (userFullAddress.length() == 0 || userFullAddress.length()>=512) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        } else if (userUniversity.length() == 0 || userUniversity.length()>=256) {
            fullAddress.setError("Enter a valid University");
            fullAddress.requestFocus();
            valid = false;
        } else if (userGraduationYear.length() == 0 || !validGraduationYear(userGraduationYear)) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        } else if (userFullAddress.length() == 0 || userFullAddress.length()>=512) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        } else if (userFullAddress.length() == 0 || userFullAddress.length()>=512) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        } else if (userFullAddress.length() == 0 || userFullAddress.length()>=512) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        } else if (userFullAddress.length() == 0 || userFullAddress.length()>=512) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        }else if (userFullAddress.length() == 0 || userFullAddress.length()>=512) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        }else if (userFullAddress.length() == 0 || userFullAddress.length()>=512) {
            fullAddress.setError("Enter a valid Address");
            fullAddress.requestFocus();
            valid = false;
        }
        return valid;

    }

    private boolean validGraduationYear(String year){
        boolean valid = false;
        int validYear = Integer.parseInt(year);
        if (validYear >=2015 || validYear <=2020){
            valid = true;
        }
        return valid;
    }
}