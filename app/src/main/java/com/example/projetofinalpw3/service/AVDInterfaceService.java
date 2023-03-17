package com.example.projetofinalpw3.service;

import com.example.projetofinalpw3.model.AtividadeDeVidaDiaria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AVDInterfaceService {

    @GET("atividadedevidadiaria/{id}/json")
    Call<AtividadeDeVidaDiaria> buscarAVDId(@Path("id") Long id);
}
