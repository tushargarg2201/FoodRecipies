package com.codingwithtushar.foodrecipies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingwithtushar.foodrecipies.Models.recipes;
import com.codingwithtushar.foodrecipies.ViewModel.RecipeListViewModel;
import com.codingwithtushar.foodrecipies.adapters.OnRecipeListener;
import com.codingwithtushar.foodrecipies.adapters.RecipeListAdapter;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener{

    private static final String TAG = "RecipeListActivity";
    private RecipeListViewModel recipeListViewModel = null;
    private RecyclerView recyclerListView;
    private RecipeListAdapter recipeListAdapter;
    private OnRecipeListener onRecipeListener;
    private SearchView searchView;
    private ProgressBar progressBar;
    private int pageNumber = 1;
    private String searchQuery = "Cookies";
    private boolean isSearchQueryExhausted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerListView = findViewById(R.id.recycler_list_view);
        searchView = findViewById(R.id.search_view);
        recipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        progressBar = findViewById(R.id.progress_bar);

        initRecyclerView();
        initSearchView();
        subcribeObservers();
        isQueryExhausted();

    }

    private void initSearchView() {
        recipeListViewModel.searchRecipeApi(searchQuery, pageNumber);
        searchView.setFocusable(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                isSearchQueryExhausted = false;
                progressBar.setVisibility(View.VISIBLE);
                recipeListViewModel.searchRecipeApi(query, 1);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initRecyclerView() {
      recipeListAdapter = new RecipeListAdapter(this);
      recyclerListView.setLayoutManager(new LinearLayoutManager(this));
      recyclerListView.setAdapter(recipeListAdapter);
      recyclerListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
              super.onScrollStateChanged(recyclerView, newState);
              if (!recyclerListView.canScrollVertically(1)) {
                 startNextPageQuery();
              }
          }
      });
    }

    private void startNextPageQuery() {
        if (!isSearchQueryExhausted) {
            progressBar.setVisibility(View.VISIBLE);
            pageNumber = pageNumber + 1;
            recipeListViewModel.searchRecipeApi(searchQuery, pageNumber);
        }
    }

    private void subcribeObservers() {
        recipeListViewModel.getRecipiesList().observe(this, new Observer<List<recipes>>() {
            @Override
            public void onChanged(List<recipes> recipes) {
                Log.d(TAG, "onChanged: called and recipes is--->" + recipes.size());
                if (recipes != null && recipes.size() > 0) {
                    progressBar.setVisibility(View.GONE);
                    recipeListAdapter.setRecipeList(recipes);
                }
            }
        });
    }

    private void isQueryExhausted() {
        recipeListViewModel.isQueryExhausted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                isSearchQueryExhausted = true;
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onChanged: QueryExhausted called");
                recipeListAdapter.setQueryExhausted();
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);
        List<recipes> recipesList = recipeListAdapter.getRecipesList();
        intent.putExtra("recipe", recipesList.get(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }
}
