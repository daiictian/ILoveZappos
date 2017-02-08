package com.zappos.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vishal on 03/02/17.
 */
public class Product implements Parcelable{

        private String productName;
        private String originalPrice;
        private String price;
        private String brandName;
        private String thumbnailImageUrl;

        private String styleId;
        private String colorId;
        private String productUrl;
        private String percentOff;
        private String productId;
    private Parcel out;
    private int flags;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    private Product(Parcel in) {
        productName = in.readString();
        originalPrice = in.readString();
        price = in.readString();
        brandName = in.readString();
        thumbnailImageUrl = in.readString();

        styleId = in.readString();
        colorId = in.readString();
        productUrl = in.readString();
        percentOff = in.readString();
        productId = in.readString();


    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        this.out = out;
        this.flags = flags;
        out.writeString(productName);
        out.writeString(originalPrice);
        out.writeString(price);
        out.writeString(brandName);
        out.writeString(thumbnailImageUrl);

        out.writeString(styleId);
        out.writeString(colorId);
        out.writeString(productUrl);
        out.writeString(percentOff);
        out.writeString(productId);
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
