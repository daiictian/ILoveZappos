package com.zappos.love.ilovezappos;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.zappos.databinding.BindingProduct;
import com.zappos.databinding.ProductPageContract;

import com.zappos.databinding.ProductPresenter;
import com.zappos.love.ilovezappos.databinding.ProductFragment2Binding;

import com.zappos.objects.Product;

import static com.zappos.love.ilovezappos.R.drawable.cart;

/**
 * Created by vishal on 08/02/17.
 */
public class ProductFragment extends Fragment implements ProductPageContract.View{

    private static MainActivity mActivity;
    private static Product mProduct;

    public static Fragment getInstance(MainActivity activity, Product product ){
        mActivity = activity;
        mProduct = product;
        return new ProductFragment();
    }




    TextView textView = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        textView = (TextView)mActivity.findViewById(R.id.textfab);

         View view = null;
        final ProductFragment2Binding binding = DataBindingUtil.inflate(inflater, R.layout.product_fragment_2,container,false);
        view = binding.getRoot();

        ProductPresenter productPresenter = new ProductPresenter(this);
        final BindingProduct bindingProduct = BindingProduct.getInstance(mActivity);



        Intent intent = mActivity.getIntent();

        bindingProduct.setBrandName(mProduct.getBrandName());
        bindingProduct.setPrice("Final Price \n" + mProduct.getPrice());
        bindingProduct.setOriginalPrice("Original Price \n" + mProduct.getOriginalPrice());
        bindingProduct.setProductName(mProduct.getProductName());
        bindingProduct.setProductImage(mProduct.getThumbnailImageUrl());
        bindingProduct.setPercentOff("Discount " + mProduct.getPercentOff());

        binding.setProduct(bindingProduct);
        binding.setPresenter(productPresenter);

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    getFragmentManager().popBackStack(null, mActivity.getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                    return true;
                } else {
                    return false;
                }
            }
        });

        ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
        anim.setFillBefore(true);
        anim.setFillAfter(true);
        anim.setFillEnabled(true);
        anim.setDuration(300);

        anim.setInterpolator(new OvershootInterpolator());



        mActivity.getFab().setBackgroundResource(R.drawable.cart);
        mActivity.getFab().startAnimation(anim);
        textView.setText("1");
        textView.setVisibility(View.VISIBLE);


        return view;

    }

    @Override
    public void onStop() {
        if(textView != null) {
            textView.setVisibility(View.INVISIBLE);
        }
      //  mActivity.getSupportFragmentManager().popBackStack();
        super.onStop();
    }

    @Override
    public void showData(BindingProduct product) {

    }
}
