package com.example.movieapp.serviceApi;
import com.google.gson.Gson;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class retrofitInstance {

    private static Retrofit retrofit = null;
    private static final String baseUrl = "https://api.themoviedb.org/3/";
    public static movieService getRetrofit() {
        if(retrofit == null){
              retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl).
                     addConverterFactory(GsonConverterFactory.create())
                     .build();
        }
        return retrofit.create(movieService.class);
    }
}
