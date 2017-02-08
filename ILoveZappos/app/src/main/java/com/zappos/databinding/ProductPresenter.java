package com.zappos.databinding;

/**
 * Created by vishal on 04/02/17.
 */
public class ProductPresenter {

    private ProductPageContract.View view;

    public ProductPresenter(ProductPageContract.View view) {

        this.view = view;
    }
    public void onShowData(BindingProduct bindingProduct) {
        view.showData(bindingProduct);
    }
}
