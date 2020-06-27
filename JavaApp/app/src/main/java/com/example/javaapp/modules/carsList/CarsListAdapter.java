package com.example.javaapp.modules.carsList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaapp.databinding.ListItemCarBinding;
import com.example.javaapp.databinding.ListItemFooterBinding;

import java.util.ArrayList;
import java.util.List;

public class CarsListAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Boolean isLoadMore = false;
    private List<CarListItemBinder> cars = new ArrayList<>();
    private static final int LIST_VIEW_TYPE = 0;
    private static final int FOOTER_VIEW_TYPE = 1;
    private CarsListViewModel viewModel;

    CarsListAdapter(CarsListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    void setIsLoadMore(Boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    void setFirstCarsList(List<CarListItemBinder> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == LIST_VIEW_TYPE) {
            return new CarViewHolder(
                    ListItemCarBinding.inflate(
                            LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        } else {
            return new FooterViewHolder(
                    ListItemFooterBinding.inflate(
                            LayoutInflater.from(viewGroup.getContext()),
                            viewGroup,
                            false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (i == cars.size() - 1 && isLoadMore) {
            viewModel.onLoadMore();
        }
        if (holder instanceof CarViewHolder) {
            ((CarViewHolder) holder).binding.setBinder(cars.get(i));
        }
    }

    @Override
    public int getItemCount() {
        if (isLoadMore) {
            return cars.size() + 1;
        } else {
            return cars.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == cars.size() && isLoadMore) {
            return FOOTER_VIEW_TYPE;
        } else {
            return LIST_VIEW_TYPE;
        }
    }

    private static class CarViewHolder extends RecyclerView.ViewHolder {
        private ListItemCarBinding binding;

        private CarViewHolder(@NonNull ListItemCarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    private static class FooterViewHolder extends RecyclerView.ViewHolder {
        private FooterViewHolder(@NonNull ListItemFooterBinding binding) {
            super(binding.getRoot());
        }
    }

    void addNewCars(List<CarListItemBinder> cars) {
        int oldSize = this.cars.size();
        this.cars.addAll(cars);
        notifyItemRangeChanged(oldSize, cars.size());
    }
}