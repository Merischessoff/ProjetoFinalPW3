package com.example.projetofinalpw3.util;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.dto.DadosMensagem;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIUtil {
    private static APIInterface apiInterface;

    public static void cadastraHistoriaSocial(String token, HistoriaSocial historiaSocial, View v){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<HistoriaSocial> call = apiInterface.cadastroHistoriaSocial(token, historiaSocial);
        call.enqueue(new Callback<HistoriaSocial>() {
            @Override
            public void onResponse(Call<HistoriaSocial> call, Response<HistoriaSocial> response) {
                Snackbar.make(v, "História salva com sucesso!", Snackbar.LENGTH_LONG)
                        .setTextColor(Color.BLUE).show();
                Navigation.findNavController(v).navigate(R.id.nav_fragment_historia_social_List);
            }
            @Override
            public void onFailure(Call<HistoriaSocial> call, Throwable t) {
                call.cancel();
                Log.e("post api", "entrou no onFailure" + t.getMessage());
            }
        });

    }

    public static void vincularBancoHistoria(String token, Bundle bundle, View v){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<DadosMensagem> call = apiInterface.vincularUsuarioBancoDeHistorias(token, bundle.getString("id"), bundle.getString("idUsuarioLeitor"));
        call.enqueue(new Callback<DadosMensagem>() {
            @Override
            public void onResponse(Call<DadosMensagem> call, Response<DadosMensagem> response) {
                Log.e("onResponse", "Response vincularBancoHistoria" + response.body());
                Navigation.findNavController(v).navigate(R.id.nav_lista_banco_historias_associar_fragment, bundle);

            }
            @Override
            public void onFailure(Call<DadosMensagem> call, Throwable t) {
                call.cancel();
                Log.e("post api", "entrou no onFailure" + t.getMessage());
            }
        });
    }

    public static void desvincularBancoHistoria(String token, Bundle bundle, View v){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<DadosMensagem> call = apiInterface.desvincularUsuarioBancoDeHistorias(token, bundle.getString("id"), bundle.getString("idUsuarioLeitor"));
        call.enqueue(new Callback<DadosMensagem>() {
            @Override
            public void onResponse(Call<DadosMensagem> call, Response<DadosMensagem> response) {
                Navigation.findNavController(v).navigate(R.id.nav_lista_banco_historias_desassociar_fragment, bundle);
                Log.e("onResponse", "sucesso " + response.body());
            }
            @Override
            public void onFailure(Call<DadosMensagem> call, Throwable t) {
                call.cancel();
                Log.e("post api", "entrou no onFailure" + t.getMessage());
            }
        });
    }

    public static void vincularHistoria(String token, Bundle bundle, View v){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<DadosMensagem> call = apiInterface.vincularUsuarioHistorias(token, bundle.getString("id"), bundle.getString("idUsuarioLeitor"));
        call.enqueue(new Callback<DadosMensagem>() {
            @Override
            public void onResponse(Call<DadosMensagem> call, Response<DadosMensagem> response) {
                Log.e("vincularHistoria", "sucesso " + response.body());
                Navigation.findNavController(v).navigate(R.id.nav_lista_historias_associar_fragment, bundle);

            }
            @Override
            public void onFailure(Call<DadosMensagem> call, Throwable t) {
                call.cancel();
                Log.e("post api", "entrou no onFailure" + t.getMessage());
            }
        });
    }

    public void desvincularHistoria(String token, Bundle bundle, View v) {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<DadosMensagem> call = apiInterface.desvincularUsuarioHistorias(token, bundle.getString("id"), bundle.getString("idUsuarioLeitor"));
        call.enqueue(new Callback<DadosMensagem>() {
            @Override
            public void onResponse(Call<DadosMensagem> call, Response<DadosMensagem> response) {
                Navigation.findNavController(v).navigate(R.id.nav_lista_historias_desassociar_fragment, bundle);
                Log.e("onResponse", "Response desvincularHistoria" + response.body());
            }
            @Override
            public void onFailure(Call<DadosMensagem> call, Throwable t) {
                call.cancel();
                Log.e("post api", "entrou no onFailure" + t.getMessage());
            }
        });
    }
}
