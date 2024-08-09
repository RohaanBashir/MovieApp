package com.example.movieapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends AndroidViewModel {


    private movieRepository repository;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new movieRepository(application);
    }

    //live data
    public MutableLiveData<ArrayList<Movie>> getPopularMovies(){
        return repository.GetPopularMovies();
    }
}
