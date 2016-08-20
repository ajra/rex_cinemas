package com.rexcinemas.api;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
/*
    http://www.rexcinemas.com.sg/demo/web/movies.php?r_method=movies
*/
    @GET("movies.php?r_method=movies")
    public Call<String> getNowShowingMovies();

    @GET("movie.php")
    public Call<String> getMovieTimes(@Query("m_movie") String moviename);

    @GET("movies-list-all-cinemas.php")
    public Call<String> getAllCinemas();

}
