package com.radhio.cvsender.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.radhio.cvsender.Models.CV;
import com.radhio.cvsender.Models.CVFileUpload;
import com.radhio.cvsender.Models.Cv_file;
import com.radhio.cvsender.R;
import com.radhio.cvsender.Session.UserSession;
import com.radhio.cvsender.Utils.Generator;
import com.radhio.cvsender.ViewModel.HomeViewModel;
import com.radhio.cvsender.ViewModel.InputViewModel;

/**
 * Created by Azmia Hoque Radhio on 12/10/2020.
 */

public class InputFragment extends Fragment {
    InputViewModel inputViewModel;
    Button submitButton;
    EditText name,email,phone,fullAddress,nameOfUniversity,graduationYear,cgpa,experienceInMonths,
            currentWorkPlaceName,expectedSalary,fieldBuzzReference,githubProjectUrl;
    Spinner applyingIn;
    NavController navController;
    private Object value;
    String[] applyPosition = {"Choose Position", "Mobile", "Backend"};
    private String rule;
    private String userName,userEmail, userPhone, userFullAddress, userUniversity, userGraduationYear,
     userCGPA, userExperience, userWorkPlaceName, userSalary, userFieldBuzzRef, userProjectUrl;
    UserSession session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_input, container, false);
        session = new UserSession(requireContext());
        inputViewModel = new ViewModelProvider(this).get(InputViewModel.class);
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
        ArrayAdapter<String> zoneAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, applyPosition);
        zoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        applyingIn.setAdapter(zoneAdapter);
        applyingIn.setSelection(0, false);
        applyingIn.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = parent.getItemAtPosition(position);
                if (value != null) {
                    value = value.toString();
                    rule = null;
                    if (!(value.equals("Choose Position"))) {
                        rule = value.toString();
                        Toast.makeText(getActivity(), rule, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        value = null;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitForm()) {
                    ValidationSucceed();
                }
            }
        });
    }

    private void ValidationSucceed() {
        CV cv = new CV();
        int length = Generator.tsyncIDGenerator().length();
        cv.setTsync_id(Generator.tsyncIDGenerator());
        cv.setName(userName);
        cv.setEmail(userEmail);
        cv.setPhone(userPhone);
        cv.setFull_address(userFullAddress);
        cv.setName_of_university(userUniversity);
        int graduationYear = Integer.parseInt(userGraduationYear);
        cv.setGraduation_year(graduationYear);
        double CGPA = Double.parseDouble(userCGPA);
        cv.setCgpa(CGPA);
        int experience = Integer.parseInt(userExperience);
        cv.setExperience_in_months(experience);
        cv.setApplying_in(rule);
        cv.setCurrent_work_place_name(userWorkPlaceName);
        int salary = Integer.parseInt(userSalary);
        cv.setExpected_salary(salary);
        cv.setField_buzz_reference(userFieldBuzzRef);
        cv.setGithub_project_url(userProjectUrl);
        cv.setCv_file(new Cv_file());
        Cv_file cvFile1 = cv.getCv_file();
        cvFile1.setTsync_id(Generator.tsyncIDGenerator());
        long creationTime= Generator.onSpotTime();
        cv.setOn_spot_update_time(creationTime);
        if (session.getCreationTime() == 0){
            cv.setOn_spot_creation_time(creationTime);
            session.setCreationTime(creationTime);
        }
        else {
            long oldCreationTIme = session.getCreationTime();
            cv.setOn_spot_creation_time(oldCreationTIme);
        }
        SubmitData(cv);
    }

    public void SubmitData(CV cv) {
        inputViewModel.GetFileTokenId(cv,getContext()).observe(getViewLifecycleOwner(), new Observer<CVFileUpload>() {
            @Override
            public void onChanged(CVFileUpload cvFileUpload) {
                if (cvFileUpload.getMessage().equals("")){
//                    navController.navigate(R.id.binaryFileFragment);
                    Toast.makeText(getActivity(), cvFileUpload.getId(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), cvFileUpload.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean submitForm() {
        boolean valid = true;
         userName = name.getText().toString().trim();
         userEmail = email.getText().toString().trim();
         String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
         userPhone = phone.getText().toString().trim();
         userFullAddress = fullAddress.getText().toString().trim();
         userUniversity = nameOfUniversity.getText().toString().trim();
         userGraduationYear = graduationYear.getText().toString().trim();
         userCGPA = cgpa.getText().toString().trim();
         userExperience = experienceInMonths.getText().toString().trim();
         userWorkPlaceName = currentWorkPlaceName.getText().toString().trim();
         userSalary = expectedSalary.getText().toString().trim();
         userFieldBuzzRef = fieldBuzzReference.getText().toString().trim();
         userProjectUrl = githubProjectUrl.getText().toString().trim();
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
            nameOfUniversity.setError("Enter a valid University");
            nameOfUniversity.requestFocus();
            valid = false;
        } else if (userGraduationYear.length() == 0 || !validGraduationYear(userGraduationYear)) {
            graduationYear.setError("Enter a valid GraduationYear");
            graduationYear.requestFocus();
            valid = false;
        } else if (userCGPA.length() == 0 || !validCGPA(userCGPA)) {
            cgpa.setError("Enter a valid CGPA");
            cgpa.requestFocus();
            valid = false;
        } else if (userExperience.length() == 0 || !validMonth(userExperience)) {
            experienceInMonths.setError("Enter a valid Experience");
            experienceInMonths.requestFocus();
            valid = false;
        } else if (userWorkPlaceName.length() == 0 || userWorkPlaceName.length()>=512) {
            currentWorkPlaceName.setError("Enter a valid WorkPlaceName");
            currentWorkPlaceName.requestFocus();
            valid = false;
        } else if (userSalary.length() == 0 || !validSalary(userSalary)) {
            expectedSalary.setError("Enter a valid Salary");
            expectedSalary.requestFocus();
            valid = false;
        }else if (userFieldBuzzRef.length() == 0 || userFieldBuzzRef.length()>=256) {
            fieldBuzzReference.setError("Enter a valid FieldBuzzRef");
            fieldBuzzReference.requestFocus();
            valid = false;
        }else if (userProjectUrl.length() == 0 || userProjectUrl.length()>=512 || !validUrl(userProjectUrl)) {
            githubProjectUrl.setError("Enter a valid ProjectUrl");
            githubProjectUrl.requestFocus();
            valid = false;
        }else if (!validRule()){
            Toast.makeText(getActivity(), "Enter a valid Position", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

    private boolean validGraduationYear(String year){
        boolean valid = false;
        int validYear = Integer.parseInt(year);
        if (validYear >=2015 && validYear <=2020){
            valid = true;
        }
        return valid;
    }

    private boolean validCGPA(String cgpa){
        boolean valid = false;
        double validYear = Double.parseDouble(cgpa);
        if (validYear >=2.0 && validYear <=4.0){
            valid = true;
        }
        return valid;
    }

    private boolean validMonth(String month){
        boolean valid = false;
        int validYear = Integer.parseInt(month);
        if (validYear >=0 && validYear <=100){
            valid = true;
        }
        return valid;
    }

    private boolean validSalary(String salary){
        boolean valid = false;
        int validYear = Integer.parseInt(salary);
        if (validYear >=15000 && validYear <=60000){
            valid = true;
        }
        return valid;
    }

    private boolean validUrl(String url){
        return Patterns.WEB_URL.matcher(url).matches();
    }

    private boolean validRule() {
        boolean valid = false;
        if (rule != null) {
            valid = true;
        }
        return valid;
    }
}