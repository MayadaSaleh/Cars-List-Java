package com.example.javaapp.utils.binding_utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import javax.inject.Inject;

public class BindingAdapters {

    @Inject
    public BindingAdapters() {
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl)
                .into(imageView);

    }
}