package com.example.giantbombapi.network;



import com.example.giantbombapi.models.AllVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiantBombApi {

    @GET("videos/?format=json&")
    Call<AllVideos> getAllVideos(
            @Query("api_key") String apiKey
    );

}
