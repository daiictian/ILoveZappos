package com.zappos.love.ilovezappos;


import android.app.ProgressDialog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<SearchItem> list = new ArrayList<SearchItem>();
    FloatingActionButton fab;
    private ProgressDialog mProgressDialog;

    public FloatingActionButton getFab(){
        return fab;
    }
    HomeFragment f1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Querying..");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "An item has been added to cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if (savedInstanceState == null) {
            f1= HomeFragment.getInstance(MainActivity.this, mProgressDialog);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.dashboard_content, f1);
            fragmentTransaction.addToBackStack("Home");
            fragmentTransaction.commit();

        }

    }

    public SearchView getSearchView(){
        return searchView;
    }
    SearchView searchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.search_item);
        //SearchView searchView = (SearchView)item.getActionView();
        searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                f1 = HomeFragment.getInstance(MainActivity.this, mProgressDialog);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.dashboard_content, f1);
                fragmentTransaction.addToBackStack("Home");
                fragmentTransaction.commit();
                searchView.clearFocus();
                if (query.length() > 0) {

                    mProgressDialog.show();
                    new ZapposRestClient().search(query, f1);


                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });



        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        // Return true to allow the action view to expand

                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        // When the action view is collapsed, reset the query

                        // Return true to allow the action view to collapse
                        return true;
                    }
                });
        return true;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "On back pressed", Toast.LENGTH_SHORT).show();
        if(getSupportFragmentManager().getFragments().size() != 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


}
