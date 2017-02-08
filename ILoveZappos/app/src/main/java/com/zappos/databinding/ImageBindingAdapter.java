package com.zappos.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by vishal on 04/02/17.
 */
public class ImageBindingAdapter {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (!url.equals("")) {
            Picasso.with(imageView.getContext()).load(url).resize(200, 200).into(imageView);
        }
    }
}
