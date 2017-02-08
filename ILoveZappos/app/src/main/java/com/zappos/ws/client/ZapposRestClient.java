package com.zappos.ws.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zappos.objects.Product;
import com.zappos.objects.Products;
import com.zappos.objects.SearchItem;

import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vishal on 04/02/17.
 */
public class ZapposRestClient {
    private static final String BASE_URL = "https://api.zappos.com";
    private final String KEY = "b743e26728e16b81da139182bb2094357c31d331";
    private Client client;

    public ZapposRestClient(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(Client.class);
    }

    public void search(String key, Callback response){
        Map<String, String> map = new HashMap<String, String>();
        map.put("term", key);
        map.put("key",KEY );
        Call<Products> call = client.getProducts(map);
        call.enqueue(response);

    }
}
