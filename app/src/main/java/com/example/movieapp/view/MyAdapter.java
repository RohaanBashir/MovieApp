package com.example.movieapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieapp.R;
import com.example.movieapp.databinding.MovieItemLayoutBinding;

import com.example.movieapp.model.Movie;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MyAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout using data binding
        MovieItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context.getApplicationContext()),
                R.layout.movie_item_layout,
                parent,
                false
        );
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Bind movie data to the views
        holder.movielistbinding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MovieItemLayoutBinding movielistbinding;

        public MyViewHolder(MovieItemLayoutBinding binding) {
            super(binding.getRoot());
            this.movielistbinding = binding;

            // Set up click listener if needed
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle item click event here
                }
            });
        }
    }
}
