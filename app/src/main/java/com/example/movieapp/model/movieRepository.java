package com.example.movieapp.model;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.movieapp.R;
import com.example.movieapp.serviceApi.movieService;
import com.example.movieapp.serviceApi.retrofitInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class movieRepository {

    private ArrayList<Movie> movies;
    private MutableLiveData<ArrayList<Movie>> MoviesLiveData;
    private Context context;

    public movieRepository(Context context){
        this.context = context;
        this.movies = new ArrayList<>();
        this.MoviesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Movie>> GetPopularMovies(){

        movieService movie = retrofitInstance.getRetrofit();
        Call<Result> call = movie.getPopularMovies("962a95aaee622de3e8cd6336cebaff13");
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {

                if(response.isSuccessful()){
                    Result result = response.body();
                    assert result != null;
                    movies = result.getResults();
                    MoviesLiveData.setValue(movies);
                    System.out.println("No Error");

                }
                else{
                    System.out.println("Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable throwable) {
                System.out.println("Failure");
            }
        });
        System.out.println("End of the call");
        return MoviesLiveData;
    }

}
