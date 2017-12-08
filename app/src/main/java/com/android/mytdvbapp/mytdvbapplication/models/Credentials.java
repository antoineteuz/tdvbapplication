package com.android.mytdvbapp.mytdvbapplication.models;

import com.android.mytdvbapp.mytdvbapplication.helper.Constants;

/**
 * Created by antoinepelletier on 04/12/2017.
 */

public class Credentials {

    private String apiKey;
    private String email;
    private String password;

    public Credentials(String email, String password) {
        this.apiKey = Constants.API_KEY;
        this.email = email;
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "api key='" + apiKey + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credentials that = (Credentials) o;

        if (!email.equals(that.email)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}