package com.example.javaapp.data.base_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseResponse<T> {

    @SerializedName("data")
    @Expose
    private List<T> data;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("error")
    @Expose
    private Error error;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}

