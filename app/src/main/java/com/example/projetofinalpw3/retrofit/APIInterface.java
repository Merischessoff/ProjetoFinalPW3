package com.example.projetofinalpw3.retrofit;

import com.example.projetofinalpw3.dto.UsuarioDTO;
import com.example.projetofinalpw3.model.Usuario;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface APIInterface {

    //@GET("/api/unknown")
    //Call<MultipleResource> doGetListResources();

    @POST("/login")
    Call<String> login(@Body UsuarioDTO usu);

    //@GET("/api/users?")
    //Call<UserList> doGetUserList(@Query("page") String page);

    //@FormUrlEncoded
    //@POST("/api/users?")
   // Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
