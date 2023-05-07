package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.adapter.MyAdapterListaBancoHistoriaSocial;
import com.example.projetofinalpw3.model.BancoDeHistoriaSocial;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaBancoDeHistoriaSocialFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private String emailUsuarioVinculado;
    private String token;

    private List<BancoDeHistoriaSocial> historias = new ArrayList<BancoDeHistoriaSocial>();
    private APIInterface apiInterface;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_banco_de_historias_lista, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewBancoHistSocList);
        Bundle bundle = getArguments();

        Intent intent = getActivity().getIntent();
        emailUsuarioVinculado = intent.getStringExtra("email");
        token = intent.getStringExtra("token");

        carregaHistoriasSociais();

        return root;
    }

    private void carregaHistoriasSociais(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<BancoDeHistoriaSocial>> call = apiInterface.pesquisaBancoDeHistorias(token);

        call.enqueue(new Callback<List<BancoDeHistoriaSocial>>() {
            @Override
            public void onResponse(Call<List<BancoDeHistoriaSocial>> call, Response<List<BancoDeHistoriaSocial>> response) {
                historias = response.body();
                MyAdapterListaBancoHistoriaSocial myAdapterListaBancoHistorias = new MyAdapterListaBancoHistoriaSocial(getActivity().getBaseContext(),historias, token, emailUsuarioVinculado);
                recyclerView.setAdapter(myAdapterListaBancoHistorias);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(layoutManager);
                Log.e("onResponse", "ListaBancoHistoriaSocialFragment " + response.body());
                Log.e("onResponse ", "lista tamanho = " + historias.size());
            }

            @Override
            public void onFailure(Call<List<BancoDeHistoriaSocial>> call, Throwable t) {
                call.cancel();
                Log.e("onFailure", "ListaBancoHistoriaSocialFragment " + t.getMessage());
            }
        });


    }

}