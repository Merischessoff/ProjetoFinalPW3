package com.example.projetofinalpw3.retrofit;

import com.example.projetofinalpw3.dto.TokenDTO;
import com.example.projetofinalpw3.dto.UsuarioDTO;
import com.example.projetofinalpw3.dto.UsuarioLoginDTO;
import com.example.projetofinalpw3.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APIInterface {

    //@GET("/api/unknown")
    //Call<MultipleResource> doGetListResources();

    @POST("/login")
    Call<TokenDTO> login(@Body UsuarioLoginDTO usu);

    @POST("/usuario")
    Call<Usuario> cadastroUsuario(@Body UsuarioDTO usu);

    //@GET("/api/users?")
    //Call<UserList> doGetUserList(@Query("page") String page);

    //@FormUrlEncoded
    //@POST("/api/users?")
   // Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
