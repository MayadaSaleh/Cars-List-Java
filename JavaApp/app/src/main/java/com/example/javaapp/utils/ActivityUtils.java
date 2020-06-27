package com.example.javaapp.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, Boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.commitAllowingStateLoss();
    }
}