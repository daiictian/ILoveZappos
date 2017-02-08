package com.zappos.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zappos.love.ilovezappos.MainActivity;
import com.zappos.love.ilovezappos.ProductFragment;
import com.zappos.love.ilovezappos.R;
import com.zappos.objects.SearchItem;

import java.util.ArrayList;

/**
 * Created by vishal on 04/02/17.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.SearchItemHolder> {
    ArrayList<SearchItem> searchItems = new ArrayList<SearchItem>();
    private static Context context;
    public RecyclerAdapter(ArrayList<SearchItem> items, Context context){
        this.searchItems = items;
        this.context = context;
    }
    @Override
    public SearchItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new SearchItemHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchItemHolder holder, int position) {
        holder.textView.setText(searchItems.get(position).getSearchItem());

    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    public  class SearchItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView ;
        public SearchItemHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.itemRow);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startProductPage(getAdapterPosition());


                }
            });



        }




        @Override
        public void onClick(View v) {

        }
    }
    private  void startProductPage(int location){


        MainActivity mainActivity = (MainActivity)context;

       Fragment f1= ProductFragment.getInstance(mainActivity, RecyclerAdapter.this.searchItems.get(location).getProduct());

        FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack("Product");
        fragmentTransaction.replace(R.id.dashboard_content, f1, "Product");
        fragmentTransaction.commit();

    }

    public void addFilter(ArrayList<SearchItem> list){
        this.searchItems = new ArrayList<SearchItem>();
        searchItems.addAll(list);
        notifyDataSetChanged();
    }
}
