package com.zappos.databinding;

/**
 * Created by vishal on 04/02/17.
 */
public interface ProductPageContract {

    public interface Presenter{

        void onShowProduct(BindingProduct product);


    }

    public interface View {
        void showData(BindingProduct product);
    }
}
