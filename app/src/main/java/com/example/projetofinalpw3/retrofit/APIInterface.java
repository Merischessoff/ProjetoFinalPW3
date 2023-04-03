package com.example.projetofinalpw3.retrofit;

import com.example.projetofinalpw3.dto.TipoUsuarioDTO;
import com.example.projetofinalpw3.dto.TokenDTO;
import com.example.projetofinalpw3.dto.UsuarioDTO;
import com.example.projetofinalpw3.dto.UsuarioEditarDTO;
import com.example.projetofinalpw3.dto.UsuarioLoginDTO;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.model.TipoUsuario;
import com.example.projetofinalpw3.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Header;


public interface APIInterface {

    @POST("/login")
    Call<TokenDTO> login(@Body UsuarioLoginDTO usu);

    @POST("/usuario/cadastro")
    Call<Usuario> cadastroUsuario(@Body Usuario usu);

    @GET("/usuario/pesquisa/{email}")
    Call<TipoUsuarioDTO> pesquisaUsuarioPorEmail(@Header("Authorization") String authorization, @Path("email") String email);

    @GET("/usuario/pesquisa/leitorvinculado/{email}")
    Call<List<Usuario>> pesquisaUsuariosVinculadosPorEmail(@Header("Authorization") String authorization, @Path("email") String email);

    @PUT("/usuario/edita/leitor/{email}")
    Call<UsuarioEditarDTO> editaUsuarioLeitor(@Header("Authorization") String authorization, @Path("email") String email, @Body UsuarioEditarDTO usu);

    @DELETE("/usuario/exclui/{email}")
    Call<Usuario> deletaUsuarioLeitor(@Header("Authorization") String authorization, @Path("email") String email);

    @POST("/usuario/cadastro")
    Call<Usuario> cadastroHistoriaSocial(@Header("Authorization") String authorization, @Body HistoriaSocial histSocial);
}
