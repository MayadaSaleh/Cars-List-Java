package com.example.javaapp.modules.carsList;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.javaapp.R;
import com.example.javaapp.databinding.FragmentCarsListBinding;
import com.example.javaapp.di.ViewModelFactory;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CarsListFragment extends DaggerFragment {

    @Inject
    ViewModelFactory providerFactory;
    private CarsListViewModel viewModel;

    private FragmentCarsListBinding binding;
    private CarsListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity(), providerFactory)
                .get(CarsListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCarsListBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new CarsListAdapter(viewModel);
        binding.searchResultsRecyclerView.setAdapter(adapter);
        binding.searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.searchResultsRecyclerView.setNestedScrollingEnabled(false);

        subscribeObservers();
    }

    private void subscribeObservers() {

        viewModel.getFirstList().observe(getViewLifecycleOwner(), carsListResult -> {
            adapter.setFirstCarsList(carsListResult.getCarsItems());
            adapter.setIsLoadMore(carsListResult.getLoadMore());
            adapter.notifyDataSetChanged();
        });

        viewModel.getNewCars().observe(getViewLifecycleOwner(), newList -> {
            adapter.addNewCars(newList.getCarsItems());
            adapter.setIsLoadMore(newList.getLoadMore());
        });

        viewModel.getErrorMessages().observe(getViewLifecycleOwner(), this::showErrorDialog);

    }

    private void showErrorDialog(String error) {
        if (getActivity() != null) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(error)
                    .setPositiveButton(android.R.string.yes,
                            (dialog, whichButton) -> dialog.dismiss())
                    .setNegativeButton(android.R.string.no, null)
                    .show();
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

}