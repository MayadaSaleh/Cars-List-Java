package com.example.javaapp.modules.carsList;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.javaapp.R;
import com.example.javaapp.databinding.ActivityMainBinding;
import com.example.javaapp.utils.ActivityUtils;

import dagger.android.support.DaggerAppCompatActivity;

public class CarsListActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState == null) {
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), new CarsListFragment(), binding.container.getId(), false);
        }
    }
}
