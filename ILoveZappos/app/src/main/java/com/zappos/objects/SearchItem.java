package com.zappos.objects;

import com.zappos.databinding.BindingProduct;

/**
 * Created by vishal on 04/02/17.
 */
public class SearchItem {
    private String searchItem;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;
    public String getSearchItem() {
        return searchItem;
    }

    public SearchItem(String item, Product product){
        this.searchItem = item;
        this.product = product;
    }
    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }


}
