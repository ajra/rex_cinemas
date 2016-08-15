package com.rexcinemas.api;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface API {

    @GET("movies.php")
    public Call<String> getNowShowingMovies();

    @GET("movie_shows.php")
    public Call<String> getMovieTimes();

}
