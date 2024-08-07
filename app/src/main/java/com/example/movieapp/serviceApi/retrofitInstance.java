package com.example.movieapp.serviceApi;
import com.google.gson.Gson;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class retrofitInstance {


    private static movieService movieAPI = null;
    public static movieService getRetrofit() {
        if(movieAPI == null){
             Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/").
                     addConverterFactory(GsonConverterFactory.create())
                     .build();
            movieAPI = retrofit.create(movieService.class);
        }

        return movieAPI;
    }

}
