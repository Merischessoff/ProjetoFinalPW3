package com.example.projetofinalpw3.service;

import com.example.projetofinalpw3.model.AtividadeDeVidaDiaria;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import com.example.projetofinalpw3.model.Usuario;

public interface LoginInterfaceService {
    @POST("login")
    Call<Usuario> createPost(@Body Usuario usu);
}
