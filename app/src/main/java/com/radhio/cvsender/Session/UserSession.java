package com.radhio.cvsender.Session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.radhio.cvsender.Models.AccessToken;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class UserSession {
    private SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static final String PREF_NAME = "UserSessionPref";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String AUTH_TOKEN = "authToken";

    // Constructor
    @SuppressLint("CommitPrefEdits")
    public UserSession(Context context){
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor =  pref.edit();
    }

    public void setUserName(String userName) {
        editor.putString(KEY_USER_NAME, userName);
        editor.commit();
    }

    public String  getUserName() {
        return pref.getString(KEY_USER_NAME, null);
    }


    public void setUserPassword(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public String  getUserPassword() {
        return pref.getString(KEY_PASSWORD, null);
    }

    public void setAuthToken(AccessToken authToken) {
        Gson gson = new Gson();
        String json = gson.toJson(authToken);
        editor.putString(AUTH_TOKEN, json);
        editor.commit();
    }

    public AccessToken  getAuthToken() {
        Gson gson = new Gson();
        String authToken = pref.getString(AUTH_TOKEN, null);
        if(authToken != null)
        {
            return gson.fromJson(authToken, AccessToken.class);
        }
        AccessToken accessToken = new AccessToken();
        accessToken.setAuthenticated(false);
        return accessToken;
    }
}
