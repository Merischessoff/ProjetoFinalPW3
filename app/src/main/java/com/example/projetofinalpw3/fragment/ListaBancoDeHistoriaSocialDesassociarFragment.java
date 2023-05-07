package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.adapter.MyAdapterListaBancoHistoriaSocialAssociar;
import com.example.projetofinalpw3.model.BancoDeHistoriaSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ListaBancoDeHistoriaSocialDesassociarFragment extends Fragment {

    private RecyclerView recyclerView;
    private View root;

    private String token;
    private String emailUsuarioLeitor;
    private String idUsuarioLeitor;
    private String emailUsuarioResponsavel;
    private List<HistoriaSocial> historias = new ArrayList<HistoriaSocial>();
    private String nomeUsuario;
    private List<BancoDeHistoriaSocial> bancoDeHistorias = new ArrayList<BancoDeHistoriaSocial>();
    private APIInterface apiInterface;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_banco_historia_social_lista_associar, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewAssociarHistoria);
        Bundle bundle = getArguments();
        Intent intent = getActivity().getIntent();
        emailUsuarioLeitor = bundle.getString("emailUsuarioLeitor");
        idUsuarioLeitor = bundle.getString("ID");
        token = intent.getStringExtra("token");
        emailUsuarioResponsavel = intent.getStringExtra("email");
        nomeUsuario = bundle.getString("nome");
        carregaHistoriasSociais();
        return root;
    }
    private void carregaHistoriasSociais(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<BancoDeHistoriaSocial>> call2 = apiInterface.pesquisaBancoDeHistoriaUsuarioAssociado(token, emailUsuarioLeitor);

        call2.enqueue(new Callback<List<BancoDeHistoriaSocial>>() {
            @Override
            public void onResponse(Call<List<BancoDeHistoriaSocial>> call, Response<List<BancoDeHistoriaSocial>> response) {
                bancoDeHistorias = response.body();
                MyAdapterListaBancoHistoriaSocialAssociar myAdapterListHistAssociar =
                        new MyAdapterListaBancoHistoriaSocialAssociar(getActivity().getBaseContext(), bancoDeHistorias, token, idUsuarioLeitor, emailUsuarioLeitor, emailUsuarioResponsavel, nomeUsuario);
                recyclerView.setAdapter(myAdapterListHistAssociar);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<BancoDeHistoriaSocial>> call, Throwable t) {
                call.cancel();
                Log.e("onFailure", "ListaHistoriaSocialFragment " + t.getMessage());
            }
        });



    }
}