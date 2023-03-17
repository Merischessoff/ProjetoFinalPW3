package com.example.projetofinalpw3.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

}
