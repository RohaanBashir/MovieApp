package com.example.movieapp.model;
import android.content.Context;
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
    private MutableLiveData<List<Movie>> MoviesLiveData;
    private Context context;

    public movieRepository(Context context){
        this.context = context;
        this.movies = new ArrayList<>();
        this.MoviesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> GetPopularMovies(){

        movieService movie = retrofitInstance.getRetrofit();
        Call<Result> call = movie.getPopularMovies(context.getString(R.string.apikey));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if(result != null && result.getResults() != null){
                    movies = result.getResults();
                    MoviesLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {

            }
        });
        return MoviesLiveData;
    }

}
