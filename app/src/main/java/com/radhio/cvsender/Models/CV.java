package com.radhio.cvsender.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class CV
{
    private String tsync_id;

    private String field_buzz_reference;

    private long on_spot_update_time;

    private int expected_salary;

    private String applying_in;

    private int graduation_year;

    private String github_project_url;

    private long on_spot_creation_time;

    private String name_of_university;

    private Cv_file cv_file;

    private int experience_in_months;

    private String full_address;

    private String current_work_place_name;

    private String phone;

    private String name;

    private double cgpa;

    private String email;

    public String getTsync_id ()
    {
        return tsync_id;
    }

    public void setTsync_id (String tsync_id)
    {
        this.tsync_id = tsync_id;
    }

    public String getField_buzz_reference ()
    {
        return field_buzz_reference;
    }

    public void setField_buzz_reference (String field_buzz_reference)
    {
        this.field_buzz_reference = field_buzz_reference;
    }


    public String getApplying_in ()
    {
        return applying_in;
    }

    public void setApplying_in (String applying_in)
    {
        this.applying_in = applying_in;
    }

    public String getGithub_project_url ()
    {
        return github_project_url;
    }

    public void setGithub_project_url (String github_project_url)
    {
        this.github_project_url = github_project_url;
    }

    public String getName_of_university ()
    {
        return name_of_university;
    }

    public void setName_of_university (String name_of_university)
    {
        this.name_of_university = name_of_university;
    }

    public Cv_file getCv_file ()
    {
        return cv_file;
    }

    public void setCv_file (Cv_file cv_file)
    {
        this.cv_file = cv_file;
    }

    public String getFull_address ()
    {
        return full_address;
    }

    public void setFull_address (String full_address)
    {
        this.full_address = full_address;
    }

    public String getCurrent_work_place_name ()
    {
        return current_work_place_name;
    }

    public void setCurrent_work_place_name (String current_work_place_name)
    {
        this.current_work_place_name = current_work_place_name;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public long getOn_spot_update_time() {
        return on_spot_update_time;
    }

    public void setOn_spot_update_time(long on_spot_update_time) {
        this.on_spot_update_time = on_spot_update_time;
    }

    public int getExpected_salary() {
        return expected_salary;
    }

    public void setExpected_salary(int expected_salary) {
        this.expected_salary = expected_salary;
    }

    public long getOn_spot_creation_time() {
        return on_spot_creation_time;
    }

    public void setOn_spot_creation_time(long on_spot_creation_time) {
        this.on_spot_creation_time = on_spot_creation_time;
    }

    public int getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(int graduation_year) {
        this.graduation_year = graduation_year;
    }

    public int getExperience_in_months() {
        return experience_in_months;
    }

    public void setExperience_in_months(int experience_in_months) {
        this.experience_in_months = experience_in_months;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [tsync_id = "+tsync_id+", field_buzz_reference = "+field_buzz_reference+", on_spot_update_time = "+on_spot_update_time+", expected_salary = "+expected_salary+", applying_in = "+applying_in+", graduation_year = "+graduation_year+", github_project_url = "+github_project_url+", on_spot_creation_time = "+on_spot_creation_time+", name_of_university = "+name_of_university+", cv_file = "+cv_file+", experience_in_months = "+experience_in_months+", full_address = "+full_address+", current_work_place_name = "+current_work_place_name+", phone = "+phone+", name = "+name+", cgpa = "+cgpa+", email = "+email+"]";
    }
}


