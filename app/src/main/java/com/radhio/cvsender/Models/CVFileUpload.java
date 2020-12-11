package com.radhio.cvsender.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class CVFileUpload {
    private String tsync_id;

    private String id;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTsync_id ()
    {
        return tsync_id;
    }

    public void setTsync_id (String tsync_id)
    {
        this.tsync_id = tsync_id;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "ClassPojo [tsync_id = "+tsync_id+", id = "+id+"]";
    }
}
