package com.example.projetofinalpw3.retrofit;

import com.example.projetofinalpw3.dto.HistoriaSocialDTO;
import com.example.projetofinalpw3.dto.TipoUsuarioDTO;
import com.example.projetofinalpw3.dto.TokenDTO;
import com.example.projetofinalpw3.dto.UsuarioCadDTO;
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
    Call<Usuario> cadastroUsuario(@Body UsuarioCadDTO usu);

    @GET("/usuario/pesquisa/{email}")
    Call<TipoUsuarioDTO> pesquisaUsuarioPorEmail(@Header("Authorization") String authorization, @Path("email") String email);

    @GET("/usuario/pesquisa/leitorvinculado/{email}")
    Call<List<Usuario>> pesquisaUsuariosVinculadosPorEmail(@Header("Authorization") String authorization, @Path("email") String email);

    @PUT("/usuario/edita/leitor/{email}")
    Call<UsuarioEditarDTO> editaUsuarioLeitor(@Header("Authorization") String authorization, @Path("email") String email, @Body UsuarioEditarDTO usu);
    
    @DELETE("/usuario/exclui/{email}")
    Call<Usuario> deletaUsuarioLeitor(@Header("Authorization") String authorization, @Path("email") String email);

    @POST("/historiasocial")
    Call<HistoriaSocial> cadastroHistoriaSocial(@Header("Authorization") String authorization, @Body HistoriaSocial histSocial);

    @GET("/historiasocial/pesquisa/historiasproprias/{email}")
    Call<List<HistoriaSocial>> pesquisaHistoriasPropriasPorEmail(@Header("Authorization") String authorization, @Path("email") String email);

    @DELETE("/historiasocial/{id}")
    Call<HistoriaSocial> deletaHistoriaPropria(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("/historiasocial/pesquisa/historiapropria/{id}")
    Call<HistoriaSocial> pesquisaHistoriaPropriaId(@Header("Authorization") String authorization, @Path("id") Long id);
}
