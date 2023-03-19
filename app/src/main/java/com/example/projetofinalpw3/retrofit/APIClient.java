package com.example.projetofinalpw3.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIClient {
    private static APIInterface apiService;

      public APIClient(String baseUrl) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(APIInterface.class);
      }

        public APIInterface getServices(){
            return apiService;
        }
}


