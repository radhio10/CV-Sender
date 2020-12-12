package com.radhio.cvsender.Models;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class Cv
{
    @SerializedName("tsync_id")
    private String tsyncID;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("full_address")
    private String fullAddress;
    @SerializedName("name_of_university")
    private String nameOfUniversity;
    @SerializedName("graduation_year")
    private int graduationYear;
    @SerializedName("cgpa")
    private double cGpa;
    @SerializedName("experience_in_months")
    private int experienceInMonths;
    @SerializedName("current_work_place_name")
    private String currentWorkPlaceName;
    @SerializedName("applying_in")
    private String applyingIn;
    @SerializedName("expected_salary")
    private int expectedSalary;
    @SerializedName("field_buzz_reference")
    private String fieldBuzzReference;
    @SerializedName("github_project_url")
    private String githubProjectUrl;
    @SerializedName("cv_file")
    private CvFile cvFile;
    @SerializedName("on_spot_update_time")
    private long onSpotUpdateTime;
    @SerializedName("on_spot_creation_time")
    private long onSpotCreationTime;

    public Cv(String tsyncID, String name, String email, String phone, String fullAddress, String nameOfUniversity, int graduationYear, double cGpa, int experienceInMonths, String currentWorkPlaceName, String applyingIn, int expectedSalary, String fieldBuzzReference, String githubProjectUrl, CvFile cvFile, long onSpotUpdateTime, long onSpotCreationTime) {
        this.tsyncID = tsyncID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fullAddress = fullAddress;
        this.nameOfUniversity = nameOfUniversity;
        this.graduationYear = graduationYear;
        this.cGpa = cGpa;
        this.experienceInMonths = experienceInMonths;
        this.currentWorkPlaceName = currentWorkPlaceName;
        this.applyingIn = applyingIn;
        this.expectedSalary = expectedSalary;
        this.fieldBuzzReference = fieldBuzzReference;
        this.githubProjectUrl = githubProjectUrl;
        this.cvFile = cvFile;
        this.onSpotUpdateTime = onSpotUpdateTime;
        this.onSpotCreationTime = onSpotCreationTime;
    }
}


