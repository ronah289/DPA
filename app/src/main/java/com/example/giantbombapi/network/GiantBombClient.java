package com.example.giantbombapi.network;

import com.example.giantbombapi.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GiantBombClient {

    private static Retrofit retrofit = null;

    public static GiantBombApi getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.GIANT_BOMB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GiantBombApi.class);

    }

}
