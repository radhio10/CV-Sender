package com.radhio.cvsender.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */

public class CvFileUpload
{
    @SerializedName("tsync_id")
    private String tsyncID;

    @SerializedName("field_buzz_reference")
    private String fieldBuzzReference;

    @SerializedName("on_spot_update_time")
    private String onSpotUpdateTime;

    @SerializedName("expected_salary")
    private String expectedSalary;

    @SerializedName("applying_in")
    private String applyingIn;

    @SerializedName("graduation_year")
    private String graduationYear;

    @SerializedName("github_project_url")
    private String githubProjectUrl;

    @SerializedName("on_spot_creation_time")
    private String on_spot_creation_time;

    @SerializedName("name_of_university")
    private String nameOfUniversity;

    @SerializedName("cv_file")
    private CvFile cvFile;

    @SerializedName("experience_in_months")
    private String experienceInMonths;

    @SerializedName("full_address")
    private String fullAddress;

    @SerializedName("current_work_place_name")
    private String currentWorkPlaceName;

    @SerializedName("message")
    private String message;

    @SerializedName("phone")
    private String phone;

    @SerializedName("success")
    private boolean success;

    @SerializedName("name")
    private String name;

    @SerializedName("cgpa")
    private String cgpa;

    @SerializedName("email")
    private String email;

    public String getTsyncID() {
        return tsyncID;
    }

    public void setTsyncID(String tsyncID) {
        this.tsyncID = tsyncID;
    }

    public String getFieldBuzzReference() {
        return fieldBuzzReference;
    }

    public void setFieldBuzzReference(String fieldBuzzReference) {
        this.fieldBuzzReference = fieldBuzzReference;
    }

    public String getOnSpotUpdateTime() {
        return onSpotUpdateTime;
    }

    public void setOnSpotUpdateTime(String onSpotUpdateTime) {
        this.onSpotUpdateTime = onSpotUpdateTime;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getApplyingIn() {
        return applyingIn;
    }

    public void setApplyingIn(String applyingIn) {
        this.applyingIn = applyingIn;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getGithubProjectUrl() {
        return githubProjectUrl;
    }

    public void setGithubProjectUrl(String githubProjectUrl) {
        this.githubProjectUrl = githubProjectUrl;
    }

    public String getOn_spot_creation_time() {
        return on_spot_creation_time;
    }

    public void setOn_spot_creation_time(String on_spot_creation_time) {
        this.on_spot_creation_time = on_spot_creation_time;
    }

    public String getNameOfUniversity() {
        return nameOfUniversity;
    }

    public void setNameOfUniversity(String nameOfUniversity) {
        this.nameOfUniversity = nameOfUniversity;
    }

    public CvFile getCvFile() {
        return cvFile;
    }

    public void setCvFile(CvFile cvFile) {
        this.cvFile = cvFile;
    }

    public String getExperienceInMonths() {
        return experienceInMonths;
    }

    public void setExperienceInMonths(String experienceInMonths) {
        this.experienceInMonths = experienceInMonths;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCurrentWorkPlaceName() {
        return currentWorkPlaceName;
    }

    public void setCurrentWorkPlaceName(String currentWorkPlaceName) {
        this.currentWorkPlaceName = currentWorkPlaceName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


