package com.example.projetofinalpw3.retrofit;

import com.example.projetofinalpw3.dto.TipoUsuarioDTO;
import com.example.projetofinalpw3.dto.TokenDTO;
import com.example.projetofinalpw3.dto.UsuarioDTO;
import com.example.projetofinalpw3.dto.UsuarioLoginDTO;
import com.example.projetofinalpw3.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Header;


public interface APIInterface {

    //@GET("/api/unknown")
    //Call<MultipleResource> doGetListResources();

    @POST("/login")
    Call<TokenDTO> login(@Body UsuarioLoginDTO usu);

    @POST("/usuario")
    Call<Usuario> cadastroUsuario(@Body UsuarioDTO usu);

    @GET("/usuario/email/{email}")
    Call<TipoUsuarioDTO> pesquisaUsuarioPorEmail(@Header("Authorization") String authorization, @Path("email") String email);

    //@FormUrlEncoded
    //@POST("/api/users?")
   // Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
