package com.zappos.databinding;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.zappos.love.ilovezappos.BR;

/**
 * Created by vishal on 04/02/17.
 */
public class BindingProduct extends BaseObservable {

    private static volatile BindingProduct mInstance;
    private String productName;
    private String originalPrice;
    private String price;
    private String brandName;
    public ObservableField<Drawable> profileImage;
    private Bindable bindable;

    private String percentOff;
    private String productImageURL;
    @Bindable
    public String getProductImage() {
        return productImageURL;
    }
    private Context context;



    public void setProductImage(String productImage) {
        this.productImageURL = productImage;
        notifyPropertyChanged(BR.productImage);
    }

    @Bindable
    public String getProductImageURL(){
        return productImageURL;
    }

    private BindingProduct(Context context){


    }
    public static BindingProduct getInstance(Context context){

        if(mInstance == null){
            mInstance = new BindingProduct(context);
        }

        return mInstance;
    }



    @Bindable
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        notifyPropertyChanged(BR.productName);
    }

    @Bindable
    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
        notifyPropertyChanged(BR.originalPrice);
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
        notifyPropertyChanged(BR.brandName);
    }


    @Bindable
    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
        notifyPropertyChanged(BR.percentOff);
    }


}
