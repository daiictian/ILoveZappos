package com.zappos.ws.client;

import com.zappos.objects.Product;
import com.zappos.objects.Products;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Callback;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by vishal on 04/02/17.
 */
public interface Client {

    @GET("/Search")
    public Call<Products> getProducts(@QueryMap Map<String, String> options);


}
