package com.example.javaapp.data.base_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }
}
