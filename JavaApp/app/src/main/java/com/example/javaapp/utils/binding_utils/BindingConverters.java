package com.example.javaapp.utils.binding_utils;


import android.view.View;

import androidx.databinding.BindingConversion;

public class BindingConverters {

    @BindingConversion
    public static int booleanToVisibility(Boolean isVisible) {
        if (isVisible) {
            return View.VISIBLE;
        } else {
            return View.GONE;
        }
    }
}