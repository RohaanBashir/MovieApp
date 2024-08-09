package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationSet;

import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.ViewModel;
import com.example.movieapp.view.MyAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Movie> movieArrayList;
    private MyAdapter adapter;
    private ViewModel viewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Initialize the movie list
        movieArrayList = new ArrayList<>();

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        // Set up RecyclerView and Adapter
        adapter = new MyAdapter(this,movieArrayList);
        recyclerView = binding.recyclerview;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        // Observe changes in movie data
        viewModel.getPopularMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                if (movies != null) {
                    movieArrayList.clear();
                    movieArrayList.addAll(movies);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // Set up SwipeRefreshLayout if used
        SwipeRefreshLayout swipeRefreshLayout = binding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getPopularMovies();
                swipeRefreshLayout.setRefreshing(false); // Stop the refresh animation
            }
        });

    }


}
