package com.zappos.love.ilovezappos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zappos.adapters.RecyclerAdapter;
import com.zappos.objects.Product;
import com.zappos.objects.Products;
import com.zappos.objects.SearchItem;
import com.zappos.ws.client.ZapposRestClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vishal on 08/02/17.
 */
public class HomeFragment extends Fragment implements Callback {


    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<SearchItem> list = new ArrayList<SearchItem>();
    private  SearchView searchView;

    private static ProgressDialog mProgressDialog;
    @Override
    public void onDetach() {
        super.onDetach();


        FrameLayout layout =(FrameLayout) getActivity().findViewById(R.id.dashboard_content);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();



        layout.setLayoutParams(params);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;

        view = inflater.inflate(R.layout.home_fragment, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        /*SearchItem s1 = new SearchItem("nike");
        list.add(s1);
        */
        recyclerAdapter = new RecyclerAdapter(list, mActivity);
        recyclerView.setAdapter(recyclerAdapter);
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

        return view;
    }

    private static MainActivity mActivity;
    private static Product mProduct;

    public static HomeFragment getInstance(MainActivity activity, ProgressDialog progressDialog ){
        mActivity = activity;
        mProgressDialog = progressDialog;

        return new HomeFragment();
    }




    @Override
    public void onResponse(Call call, Response response) {
        mProgressDialog.dismiss();
        System.out.println("Response code is " + response.code());
        Products products = (Products)response.body();
        ArrayList<SearchItem> list = new ArrayList<SearchItem>();
        for(Product p : products.getResults()){
            list.add(new SearchItem(p.getProductName(),p));
        }

        recyclerAdapter.addFilter(list);

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }
}
