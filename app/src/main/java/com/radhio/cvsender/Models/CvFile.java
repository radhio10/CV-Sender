package com.radhio.cvsender.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class CvFile {
    @SerializedName("tsync_id")
    private String tsyncID;

    @SerializedName("path")
    private String path;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("code")
    private String code;

    @SerializedName("file")
    private String file;

    @SerializedName("date_created")
    private String dateCreated;

    @SerializedName("success")
    private boolean success;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    public String getTsyncID() {
        return tsyncID;
    }

    public void setTsyncID(String tsyncID) {
        this.tsyncID = tsyncID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
